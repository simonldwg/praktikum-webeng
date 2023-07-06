package com.shopmeowmeow.model;

import io.ebean.annotation.DbEnumValue;

public enum OrderPaymentMethod {
    PAYPAL ("PayPal"),
    BANK_TRANSFER("Bank transfer"),
    CREDIT_CARD("Credit card");

    private final String displayName;
    OrderPaymentMethod(final String displayName) {
        this.displayName = displayName;
    }

    @DbEnumValue
    public String getDisplayName() {
        return this.displayName;
    }
}