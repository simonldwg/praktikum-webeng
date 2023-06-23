package com.shopmeowmeow.model;

import io.ebean.annotation.DbEnumValue;

public enum CatGender {
    MALE("Male"),
    FEMALE("Female");

    private final String displayName;
    CatGender(final String displayName) {
        this.displayName = displayName;
    }

    @DbEnumValue
    public String getDisplayName() {
        return this.displayName;
    }
}
