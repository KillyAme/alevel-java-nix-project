package com.alevel.nix.java.project.onlinestore;

import com.alevel.nix.java.project.onlinestore.entity.User;
import com.alevel.nix.java.project.onlinestore.entity.enums.Role;
import com.alevel.nix.java.project.onlinestore.entity.request.CategoryRequest;
import com.alevel.nix.java.project.onlinestore.entity.request.SaveProductRequest;
import com.alevel.nix.java.project.onlinestore.entity.request.UserRequest;
import com.alevel.nix.java.project.onlinestore.entity.response.BasketResponse;
import com.alevel.nix.java.project.onlinestore.entity.response.CategoryResponse;
import com.alevel.nix.java.project.onlinestore.entity.response.ProductResponse;
import com.alevel.nix.java.project.onlinestore.entity.response.UserResponse;
import com.alevel.nix.java.project.onlinestore.exception.UserNotFoundException;
import com.alevel.nix.java.project.onlinestore.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OnlineStoreApplicationTests {

    private final UserRepository userRepository;


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    public OnlineStoreApplicationTests(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Test
    void contextLoads() {
        assertNotNull(rest);
        assertNotEquals(0, port);
    }

    @Test
    void testCreateUser() {
        String name = "test name";
        String email = "test email";
        String password = "test password";
        String phone = "test phone";
        ResponseEntity<UserResponse> userResponseEntity = createUserResponse(name, email, password, phone);
        assertEquals(HttpStatus.CREATED, userResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, userResponseEntity.getHeaders().getContentType());
        UserResponse responseBody = userResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(name, responseBody.getUserName());
        assertEquals(email, responseBody.getEmail());
        assertEquals(phone, responseBody.getPhone());
        assertNotNull(responseBody.getId());
    }

    private ResponseEntity<UserResponse> createUserResponse(String name, String email, String password, String phone) {
        UserRequest requestBody = new UserRequest(name, email, password, phone);
        return rest.postForEntity("/users", requestBody, UserResponse.class);
    }

    @Test
    void getUserTest() {
        String username = "test name";
        String email = "test email";
        String password = "test password";
        String phone = "test phone";
        UserResponse userResponse = createUserResponse(username, email, password, phone).getBody();
        assertNotNull(userResponse);
        Long id = userResponse.getId();
        var url = "/users/" + id;
        ResponseEntity<UserResponse> responseEntity = rest.withBasicAuth(username, password).getForEntity(url, UserResponse.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        UserResponse entityBody = responseEntity.getBody();
        assertNotNull(entityBody);
        assertEquals(username, entityBody.getUserName());
        assertEquals(email, entityBody.getEmail());
        assertEquals(phone, entityBody.getPhone());
        UserResponse body = rest.withBasicAuth(username, password).getForEntity(url, UserResponse.class).getBody();
        assertNotNull(body);
        assertEquals(entityBody.getId(), body.getId());
        assertEquals(entityBody.getEmail(), body.getEmail());
        assertEquals(entityBody.getUserName(), body.getUserName());
        assertEquals(entityBody.getPhone(), body.getPhone());
    }

    @Test
    void updateUserTest() {
        String username = "test name";
        String email = "test email";
        String password = "test password";
        String phone = "test phone";
        UserResponse userResponse = createUserResponse(username, email, password, phone).getBody();
        assertNotNull(userResponse);
        Long id = userResponse.getId();
        var url = "/users/" + id;
        String usernameUpdate = "Update name";
        String emailUpdate = "Update email";
        String passwordUpdate = "password Update";
        String phoneUpdate = "Update phone";
        UserRequest request = new UserRequest(usernameUpdate, emailUpdate, passwordUpdate, phoneUpdate);
        rest.withBasicAuth(username, password).put(url, request);
        ResponseEntity<UserResponse> responseEntity = rest.withBasicAuth(usernameUpdate, passwordUpdate).getForEntity(url, UserResponse.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        UserResponse body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals(usernameUpdate, body.getUserName());
        assertEquals(emailUpdate, body.getEmail());
        assertEquals(phoneUpdate, body.getPhone());
        assertEquals(id, body.getId());
    }

    @Test
    void getUserBasketTest() {
        String username = "test name";
        String email = "test email";
        String password = "test password";
        String phone = "test phone";
        UserResponse userResponse = createUserResponse(username, email, password, phone).getBody();
        assertNotNull(userResponse);
        Long id = userResponse.getId();
        var url = "/users/" + id + "/basket";
        ResponseEntity<BasketResponse> basketResponseResponseEntity = rest.withBasicAuth(username, password).getForEntity(url, BasketResponse.class);
        assertEquals(HttpStatus.OK, basketResponseResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, basketResponseResponseEntity.getHeaders().getContentType());
        BasketResponse body = basketResponseResponseEntity.getBody();
        assertNotNull(body);
        assertEquals(body.getAmount(), 0L);
        assertTrue(body.getListProducts().isEmpty());
    }

    @Test
    void createCategoryTest() {
        String name = "test category";
        CategoryRequest request = new CategoryRequest();
        request.setName(name);
        ResponseEntity<UserResponse> userResponse = createUserResponse("admin", "AdminMail", "admin", "67098");
        UserResponse body = userResponse.getBody();
        assertNotNull(body);
        createAdmin(body);
        ResponseEntity<CategoryResponse> entity = rest.withBasicAuth("admin", "admin").postForEntity("/categories", request, CategoryResponse.class);
        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());
        CategoryResponse entityBody = entity.getBody();
        assertNotNull(entityBody);
        assertEquals(name, entityBody.getName());
        assertNotNull(entityBody.getId());
    }

    @Test
    void getCategoryByIdTest() {
        ResponseEntity<CategoryResponse> category = createCategory();
        CategoryResponse categoryBody = category.getBody();
        assertNotNull(categoryBody);
        Long id = categoryBody.getId();
        ResponseEntity<CategoryResponse> responseEntity = rest.getForEntity("/categories/" + id, CategoryResponse.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        CategoryResponse entityBody = responseEntity.getBody();
        assertNotNull(entityBody);
        assertEquals(categoryBody.getName(), entityBody.getName());
        assertEquals(categoryBody.getId(), categoryBody.getId());
    }

    public ResponseEntity<CategoryResponse> createCategory() {
        String name = "test category";
        CategoryRequest request = new CategoryRequest();
        request.setName(name);
        ResponseEntity<UserResponse> userResponse = createUserResponse("admin", "AdminMail", "admin", "67098");
        UserResponse body = userResponse.getBody();
        assertNotNull(body);
        createAdmin(body);
        return rest.withBasicAuth("admin", "admin").postForEntity("/categories", request, CategoryResponse.class);
    }


    private User createAdmin(UserResponse body) {
        User admin = userRepository.findById(body.getId()).orElseThrow(() -> new UserNotFoundException(body.getId()));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);
        return admin;
    }

    @Test
    void createProductTest() {
        String company = "test company";
        String series = "test series";
        String model = "test model";
        String productName = "test product";
        Integer price = 1000;
        String description = "test desc";


        ResponseEntity<CategoryResponse> category = createCategory();
        CategoryResponse categoryBody = category.getBody();
        assertNotNull(categoryBody);
        SaveProductRequest request = new SaveProductRequest(company, series, model, productName, price, description, categoryBody.getName());

        ResponseEntity<ProductResponse> entity = rest.withBasicAuth("admin", "admin").postForEntity("/products", request, ProductResponse.class);
        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());
        ProductResponse body = entity.getBody();
        assertNotNull(body);
        assertEquals(company, body.getCompany());
        assertEquals(series, body.getSeries());
        assertEquals(model, body.getModel());
        assertEquals(productName, body.getProductName());
        assertEquals(price, body.getPrice());
        assertEquals(description, body.getDescription());
        assertNotNull(body.getId());
    }

    @Test
    void getProductByIdTest() {
        String company = "test company";
        String series = "test series";
        String model = "test model";
        String productName = "test product";
        Integer price = 1000;
        String description = "test desc";


        ResponseEntity<CategoryResponse> category = createCategory();
        CategoryResponse categoryBody = category.getBody();
        assertNotNull(categoryBody);
        SaveProductRequest request = new SaveProductRequest(company, series, model, productName, price, description, categoryBody.getName());

        ResponseEntity<ProductResponse> entity = rest.withBasicAuth("admin", "admin").postForEntity("/products", request, ProductResponse.class);
        ProductResponse productBody = entity.getBody();
        assertNotNull(productBody);
        Long id = productBody.getId();
        ResponseEntity<ProductResponse> productEntity = rest.getForEntity("/products/" + id, ProductResponse.class);
        assertEquals(HttpStatus.OK, productEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, productEntity.getHeaders().getContentType());
        ProductResponse productEntityBody = productEntity.getBody();
        assertNotNull(productEntityBody);
        assertEquals(productBody.getId(), productEntityBody.getId());
        assertEquals(productBody.getCompany(), productEntityBody.getCompany());
        assertEquals(productBody.getDescription(), productEntityBody.getDescription());
        assertEquals(productBody.getModel(), productEntityBody.getModel());
        assertEquals(productBody.getPrice(), productEntityBody.getPrice());
        assertEquals(productBody.getProductName(), productEntityBody.getProductName());
        assertEquals(productBody.getSeries(), productEntityBody.getSeries());

    }
}
