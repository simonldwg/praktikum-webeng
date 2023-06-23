package com.shopmeowmeow.model;

import io.ebean.annotation.DbEnumValue;

public enum CatColor {
    WHITE("White"),
    BLACK("Black"),
    ORANGE("Orange"),
    BROWN("Brown"),
    BLONDE("Blonde");

    private final String displayName;
    CatColor(String displayName) {
        this.displayName = displayName;
    }

    @DbEnumValue
    public String getDisplayName() {
        return this.displayName;
    }
}
