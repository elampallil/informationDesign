package com.information.informationDesign.controller;
import com.information.informationDesign.model.Category;
import com.information.informationDesign.model.ExtendedCategory;
import com.information.informationDesign.model.TreeNode;
import com.information.informationDesign.service.TreeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class TreeControllerTest {

    @Mock
    private TreeService treeService;

    @InjectMocks
    private TreeController treeController;

    @Test
    public void testGetTree() {
        TreeNode root = new Category("Root");
        Mockito.when(treeService.getRoot()).thenReturn(root);

        TreeNode result = treeController.getTree();

        Assert.assertEquals(root, result);
    }

    @Test
    public void testGetNodeDescription_NodeExistsAndHasDescription() {
        String nodeName = "Node1";
        String description = "This is Node1";
        TreeNode node = new ExtendedCategory(nodeName, description);
        Mockito.when(treeService.getNodeDescription(nodeName)).thenReturn(node);

        String response = treeController.getNodeDescription(nodeName);

        Assert.assertEquals(description, response);
    }

    @Test
    public void testGetNodeDescription_NodeExistsAndHasNoDescription() {
        String nodeName = "Node2";
        TreeNode node = new Category(nodeName);
        Mockito.when(treeService.getNodeDescription(nodeName)).thenReturn(node);

        String response = treeController.getNodeDescription(nodeName);

        Assert.assertEquals("The node " + nodeName + " does not have any description", response);
    }
    @Test
    public void testGetNodeDescription_NodeDoesNotExist() {
        String nodeName = "NonExistingNode";
        Mockito.when(treeService.getNodeDescription(nodeName)).thenReturn(null);

        String response = treeController.getNodeDescription(nodeName);

        Assert.assertEquals("The node " + nodeName + " is not present in the tree", response);
    }

    @Test
    public void testAddNode_ValidRequest() {
        String parentName = "Parent";
        String nodeName = "NewNode";
        String nodeDescription = "This is a new node";
        Mockito.when(treeService.addNode(parentName, nodeName, nodeDescription)).thenReturn(true);

        ResponseEntity<String> response = treeController.addNode(parentName, nodeName, nodeDescription);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("Node added successfully.", response.getBody());
    }

    @Test
    public void testAddNode_InvalidRequest() {
        String parentName = "NonExistingParent";
        String nodeName = "NewNode";
        String nodeDescription = "This is a new node";
        Mockito.when(treeService.addNode(parentName, nodeName, nodeDescription)).thenReturn(false);

        ResponseEntity<String> response = treeController.addNode(parentName, nodeName, nodeDescription);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("Parent node not found or is not a category OR the node which you want to create is already existing in the tree", response.getBody());
    }

    @Test
    public void testDeleteNode_ValidRequest() {
        String nodeName = "NodeToDelete";
        Mockito.when(treeService.deleteNode(nodeName)).thenReturn(true);

        ResponseEntity<String> response = treeController.deleteNode(nodeName);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("Node deleted successfully.", response.getBody());
    }

    @Test
    public void testDeleteNode_InValidRequest() {
        String nodeName = "NodeToDelete";
        Mockito.when(treeService.deleteNode(nodeName)).thenReturn(false);

        ResponseEntity<String> response = treeController.deleteNode(nodeName);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("The mentioned node is not present in the tree OR you can not delete first node", response.getBody());
    }
}