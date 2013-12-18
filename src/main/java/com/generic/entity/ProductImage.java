package com.generic.entity;

import javax.persistence.*;

/**
 * User: Proshad
 * Date: 12/5/13
 */
@Entity
@Table(name="ServiceImage")
public class ProductImage {
    @Id
    @GeneratedValue
    private int productImageID;
    @Column(length = 500)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID")
    private Product product;

    public ProductImage() {
    }

    public ProductImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getProductImageID() {
        return productImageID;
    }

    public void setProductImageID(int productImageID) {
        this.productImageID = productImageID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
