package com.paro.exception;

public class CategoryRestriction extends RuntimeException {
    private static final long serialVersionUID = -694354952032299587L;
    private String category;
    public CategoryRestriction(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }
}