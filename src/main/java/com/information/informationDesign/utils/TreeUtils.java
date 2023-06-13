package com.information.informationDesign.utils;

import com.information.informationDesign.model.TreeNode;

public class TreeUtils {

    public static TreeNode findNodeByName(TreeNode currentNode, String parentNode) {
        if (currentNode.getName().equals(parentNode)) {
            return currentNode;
        }

        for (TreeNode child : currentNode.getChildren()) {
            TreeNode foundNode = findNodeByName(child, parentNode);
            if (foundNode != null) {
                return foundNode;
            }
        }

        return null;
    }

    public static boolean isNodeAlreadyExistInTheTree(TreeNode currentNode, String node) {
        TreeNode foundNode = findNodeByName(currentNode, node);
        return foundNode != null;
    }


    // recursively traverses the tree structure starting from the currentNode and checks if any of its children have a matching nodeName.
    // If a child node with the matching name is found, it returns the currentNode, indicating that it is the parent of the desired node.
    public static TreeNode findParentNode(TreeNode currentNode, String node){
        for (TreeNode child : currentNode.getChildren()) {
            if (child.getName().equals(node)) {
                return currentNode;
            }
            TreeNode foundNode = findParentNode(child, node);
            if (foundNode != null) {
                return foundNode;
            }
        }
        return null;
    }
}
