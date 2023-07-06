package com.shopmeowmeow.transfer;

import com.shopmeowmeow.model.CatColor;
import com.shopmeowmeow.model.CatGender;
import com.shopmeowmeow.model.CatHairstyle;
import io.ebean.annotation.Length;
import io.ebean.annotation.NotNull;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Cats")
public class Cat {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public CatColor getColor() {
        return color;
    }

    public void setColor(CatColor color) {
        this.color = color;
    }

    public CatHairstyle getHairstyle() {
        return hairstyle;
    }

    public void setHairstyle(CatHairstyle hairstyle) {
        this.hairstyle = hairstyle;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Cat(String name, String description, int age, CatGender gender, CatColor color, CatHairstyle hairstyle, float price) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.hairstyle = hairstyle;
        this.price = price;
        this.description = description;
        this.gender = gender;
        this.addedAt = new Date(System.currentTimeMillis());
        this.reserved = false;
    }

    public Cat() {
        this.addedAt = new Date(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, columnDefinition = "BIGINT auto_increment")
    private Long id;
    @NotNull @Length(30)
    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    private CatColor color;
    @Enumerated(EnumType.STRING)
    private CatHairstyle hairstyle;
    private float price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public CatGender getGender() {
        return gender;
    }

    public void setGender(CatGender gender) {
        this.gender = gender;
    }
    @Enumerated(EnumType.STRING)
    private CatGender gender;

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }

    private Date addedAt;

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    private boolean reserved;

}
