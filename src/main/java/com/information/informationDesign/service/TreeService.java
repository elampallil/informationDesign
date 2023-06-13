package com.information.informationDesign.service;

import com.information.informationDesign.model.TreeNode;

public interface TreeService {
    TreeNode getRoot();
    boolean addNode(String parentName, String nodeName,String nodeDescription);

    boolean deleteNode(String nodeToDelete);

    TreeNode getNodeDescription(String nodeName);
}
