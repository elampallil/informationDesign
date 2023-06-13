package com.information.informationDesign.controller;
import com.information.informationDesign.model.TreeNode;
import com.information.informationDesign.service.TreeService;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tree")
@Validated
public class TreeController {

    private final TreeService treeService;

    @Autowired
    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @GetMapping("/get")
    public TreeNode getTree() {
        return treeService.getRoot();
    }


    @GetMapping("/node/description/{nodeName}")
    public String getNodeDescription(@PathVariable("nodeName") String nodeName) {
        TreeNode node = treeService.getNodeDescription(nodeName);

        return (node == null) ? "The node " + nodeName + " is not present in the tree" :
                (node.isExtendedCategory()) ? node.getDescription() :
                        "The node " + nodeName + " does not have any description";
    }

    @PostMapping("/node/add")
    public ResponseEntity<String> addNode(@RequestParam("parentName") String parentName ,
                                          @RequestParam("nodeName") String nodeName,
                                          @RequestParam(value = "nodeDescription", required = false)  @Size(max = 10000) String nodeDescription) {
        boolean added = treeService.addNode(parentName, nodeName, nodeDescription);
        return (added) ? ResponseEntity.ok("Node added successfully.") :
                ResponseEntity.badRequest().body("Parent node not found or is not a category OR the node which you want to create is already existing in the tree");
    }

    @DeleteMapping("/node/delete")
    public ResponseEntity<String> deleteNode(@RequestParam("nodeName") String nodeName) {
        boolean deleted = treeService.deleteNode(nodeName);
        return (deleted) ? ResponseEntity.ok("Node deleted successfully.") :
                ResponseEntity.badRequest().body("The mentioned node is not present in the tree OR you can not delete first node");
    }

}
