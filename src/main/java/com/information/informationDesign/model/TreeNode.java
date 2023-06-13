package com.information.informationDesign.model;

import java.util.ArrayList;
import java.util.List;

public abstract  class TreeNode {

    private String name;
    private List<TreeNode> children;

    public TreeNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public boolean removeChild(TreeNode child) {
        return children.remove(child);
    }

    public List<TreeNode> getChildren() {
        return children;
    }
    public abstract boolean isExtendedCategory();

    public abstract String getDescription();
}
