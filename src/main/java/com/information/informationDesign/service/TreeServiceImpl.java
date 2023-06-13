package com.information.informationDesign.service;

import com.information.informationDesign.model.Category;
import com.information.informationDesign.model.ExtendedCategory;
import com.information.informationDesign.model.TreeNode;
import com.information.informationDesign.utils.TreeUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.information.informationDesign.utils.TreeUtils.*;

@Service
public class TreeServiceImpl implements TreeService{

    private TreeNode root;

    public TreeServiceImpl() {
        // Create the tree structure
        TreeNode taxi = new Category("Taxi");

        TreeNode bus = new Category("Bus");
        TreeNode hotelShuttle = new Category("Hotel shuttle");
        TreeNode freelanceServiceProvider = new Category("Freelance service provider");

        TreeNode hotelTransfer = new Category("Hotel transfer");
        hotelTransfer.addChild(taxi);
        hotelTransfer.addChild(bus);
        hotelTransfer.addChild(hotelShuttle);
        hotelTransfer.addChild(freelanceServiceProvider);

        TreeNode checkInOut = new Category("Checkin+out");

        TreeNode room = new ExtendedCategory("Room", "This is a room category. It represents different types of rooms available.");

        TreeNode hotelAccommodation = new Category("Hotel accommodation");
        hotelAccommodation.addChild(room);
        hotelAccommodation.addChild(checkInOut);

        TreeNode customerComplaint = new ExtendedCategory("Customer complaint", "This is a customer complaint category.");
        customerComplaint.addChild(hotelTransfer);
        customerComplaint.addChild(hotelAccommodation);

        root = customerComplaint;
    }

    public TreeNode getRoot() {
        return root;
    }

    @Override
    public boolean addNode(String parentName, String nodeName, String nodeDescription) {
        TreeNode parent = findNodeByName(getRoot(), parentName);
        if(!isNodeAlreadyExistInTheTree(getRoot(),nodeName) && parent != null){
            TreeNode newNode;
            if (nodeDescription != null) {
                newNode = new ExtendedCategory(nodeName, nodeDescription);
            } else {
                newNode = new Category(nodeName);
            }
            parent.addChild(newNode);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteNode(String nodeName) {
        TreeNode nodeToDelete = findNodeByName(getRoot(), nodeName);
        TreeNode parentNode = findParentNode(getRoot(), nodeName);
        if (nodeToDelete != null && parentNode != null) {
            parentNode.removeChild(nodeToDelete);
            return true;
        }
        return false;
    }

    @Override
    public TreeNode getNodeDescription(String nodeName) {
        return TreeUtils.findNodeByName(getRoot(), nodeName);
    }


}
