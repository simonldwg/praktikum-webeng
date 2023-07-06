package com.shopmeowmeow.transfer;

import com.shopmeowmeow.model.OrderPaymentMethod;
import io.ebean.annotation.Length;
import io.ebean.annotation.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Order {
    public Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCats() {
        return cats;
    }

    public void setCats(String cats) {
        this.cats = cats;
    }

    public OrderPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(OrderPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Order(String firstname, String lastname, String address, String city, String zip, String cats, OrderPaymentMethod paymentMethod) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.cats = cats;
        this.paymentMethod = paymentMethod;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, columnDefinition = "BIGINT auto_increment")
    private Long id;
    @NotNull @Length(30)
    private String firstname;
    @NotNull @Length(30)
    private String lastname;
    @NotNull
    private String address;
    @NotNull
    private String city;
    @NotNull
    private String zip;
    @NotNull
    private String cats;


    @Enumerated(EnumType.STRING)
    private OrderPaymentMethod paymentMethod;

}
