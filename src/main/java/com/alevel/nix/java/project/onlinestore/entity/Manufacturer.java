package com.alevel.nix.java.project.onlinestore.entity;

import javax.persistence.*;
import java.util.List;

@Embeddable
public class Manufacturer {



    @Column(name = "company_name", nullable = false)
    private String companyName;

    private String series;

    private String modelName;

    @Column(name = "product_name", nullable = false)
    private String productName;






    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


}
