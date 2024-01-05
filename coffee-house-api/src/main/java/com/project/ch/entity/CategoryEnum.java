package com.project.ch.entity;

public enum CategoryEnum {

    COFFEE_CLASSIC("Coffee Classic"),
    COFFEE_SPECIALTY("Coffee Specialty"),
    SYRUP("Syrup"),
    TOPPING("Topping"),
    MILK("Milk"),
    FOOD("Food"),
    PASTRIES("Pastries"),
    DESSERTS("Desserts");

    private final String dbValue;

    CategoryEnum(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

}
