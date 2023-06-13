package com.information.informationDesign.utils;

import com.information.informationDesign.model.TreeNode;
import com.information.informationDesign.utils.TreeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class TreeUtilsTest {

    @Mock
    private TreeNode mockRoot;

    @Mock
    private TreeNode mockChild1;

    @Mock
    private TreeNode mockChild2;

    @Mock
    private TreeNode mockChild3;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Configure the mock objects
        when(mockRoot.getName()).thenReturn("Root");
        when(mockChild1.getName()).thenReturn("Node1");
        when(mockChild2.getName()).thenReturn("Node2");
        when(mockChild3.getName()).thenReturn("Node3");

        when(mockRoot.getChildren()).thenReturn(createChildrenList(mockChild1, mockChild2));
        when(mockChild1.getChildren()).thenReturn(createChildrenList(mockChild3));
    }

    @Test
    public void testFindNodeByName_NodeExists() {
        String nodeName = "Node1";

        TreeNode result = TreeUtils.findNodeByName(mockRoot, nodeName);

       Assertions.assertNotNull(result);
        Assertions.assertEquals(nodeName, result.getName());
    }

    @Test
    public void testFindNodeByName_NodeDoesNotExist() {
        String nodeName = "NonExistingNode";

        TreeNode result = TreeUtils.findNodeByName(mockRoot, nodeName);

        Assertions.assertNull(result);
    }

    @Test
    public void testIsNodeAlreadyExistInTheTree_NodeExists() {

        String nodeName = "Node2";


        boolean result = TreeUtils.isNodeAlreadyExistInTheTree(mockRoot, nodeName);


        Assertions.assertTrue(result);
    }

    @Test
    public void testIsNodeAlreadyExistInTheTree_NodeDoesNotExist() {
        String nodeName = "NonExistingNode";

        boolean result = TreeUtils.isNodeAlreadyExistInTheTree(mockRoot, nodeName);

        Assertions.assertFalse(result);
    }

    private List<TreeNode> createChildrenList(TreeNode... children) {
        List<TreeNode> childrenList = new ArrayList<>();
        childrenList.addAll(Arrays.asList(children));
        return childrenList;
    }
}
