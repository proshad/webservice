package com.generic.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * User: proshad
 * Date: 8/19/13
 */
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue
    private int categoryID;
    private String categoryName;
    @Column(length = 1000)
    private String categoryDescription;
    private boolean status = true;
    private int parentCatID = -1;
    private String categoryNote;


    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Product> products;

    public Category() {

    }

    public Category(int catID, String categoryName, String categoryDescription, boolean status, int parentCatID, String categoryNote) {
        this.categoryID = catID;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.status = status;
        this.parentCatID = parentCatID;
        this.categoryNote = categoryNote;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryNote() {
        return categoryNote;
    }

    public void setCategoryNote(String categoryNote) {
        this.categoryNote = categoryNote;
    }

    public int getParentCatID() {
        return parentCatID;
    }

    public void setParentCatID(int parentCatID) {
        this.parentCatID = parentCatID;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
