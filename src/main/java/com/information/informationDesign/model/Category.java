package com.information.informationDesign.model;

public class Category extends TreeNode{
    public Category(String name) {
        super(name);
    }

    @Override
    public boolean isExtendedCategory() {
        return false;
    }

    @Override
    public String getDescription() {
        return "";
    }
}
