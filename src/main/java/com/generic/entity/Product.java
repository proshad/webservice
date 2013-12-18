package com.generic.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * User: proshad
 * Date: 9/14/13
 */
@Entity
@Table(name="Service")
public class Product implements Serializable {
    @Id
    @GeneratedValue
    private int productID;
    private String productName;
    @Column(length = 1000)
    private String productDescription;
    private String productNote;
    private boolean status = true;
    private int noOfTimeSlot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryID")
    private Category category;

    @OneToMany(mappedBy="product",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductRate> productRates;

    @OneToMany(mappedBy="product",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EmployeeProduct> employeeProducts;

    @OneToMany(mappedBy="product",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductImage> productImages;


    public Product(){

    }

    public Product(int productID, String productName, String productDescription, String productNote, boolean status, int noOfTimeSlot) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productNote = productNote;
        this.status = status;
        this.noOfTimeSlot = noOfTimeSlot;
    }

    public Set<ProductRate> getProductRates() {
        return productRates;
    }

    public void setProductRates(Set<ProductRate> productRates) {
        this.productRates = productRates;
    }

    public Set<EmployeeProduct> getEmployeeProducts() {
        return employeeProducts;
    }

    public void setEmployeeProducts(Set<EmployeeProduct> employeeProducts) {
        this.employeeProducts = employeeProducts;
    }

    public Set<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductNote() {
        return productNote;
    }

    public void setProductNote(String productNote) {
        this.productNote = productNote;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNoOfTimeSlot() {
        return noOfTimeSlot;
    }

    public void setNoOfTimeSlot(int noOfTimeSlot) {
        this.noOfTimeSlot = noOfTimeSlot;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
