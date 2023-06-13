package com.information.informationDesign.model;

public class ExtendedCategory extends TreeNode{
    private String description;

    public ExtendedCategory(String name, String description) {
        super(name);
        this.description = description;
    }

    @Override
    public boolean isExtendedCategory() {
        return true;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
