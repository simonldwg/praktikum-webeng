package com.shopmeowmeow.model;

import io.ebean.annotation.DbEnumValue;

public enum CatHairstyle {
    LONG ("Long"),
    SHORT("Short"),
    WEIRD("Weird"),
    NORMAL("Normal"),
    NO_HAIR("No hair");

    private final String displayName;
    CatHairstyle(final String displayName) {
        this.displayName = displayName;
    }

    @DbEnumValue
    public String getDisplayName() {
        return this.displayName;
    }
}
