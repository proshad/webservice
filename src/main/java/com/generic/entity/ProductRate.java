package com.generic.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: proshad
 * Date: 9/22/13
 */
@Entity
public class ProductRate {
    @Id
    @GeneratedValue
    private int productRateID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="productID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rateID")
    private Rate rate;

    public ProductRate() {
    }



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public int getProductRateID() {
        return productRateID;
    }

    public void setProductRateID(int productRateID) {
        this.productRateID = productRateID;
    }
}
