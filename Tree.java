package codesample.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class Tree {
	
	/*
	 * Author: Tim Griffith
	 * Date: 03-27-2016
	 * 
	 * Defines the root Tree from which all TreeNode objects are based
	 * and the methods to act on them
	 * 
	 */
	
	private TreeNode root;

	public Tree(int value,String name,int birthYear,int deathYear) {
		this.root = new TreeNode(value,name,birthYear,deathYear,null);
	}
	
	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	/*
	 * Find all descendants of a particular node
	 */
	public List<TreeNode> getAllNodeDescendants(TreeNode currentNode) {
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
    	Queue<TreeNode> queue = new java.util.LinkedList<TreeNode>();
        queue.offer(currentNode);
        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.poll();
            if (currentNode.getValue()!=node.getValue()) {
            	nodeList.add(node);	
            }
            if (!node.getChildren().isEmpty()) {
            	Iterator<TreeNode> iterator = node.getChildren().iterator();
            	while (iterator.hasNext()) queue.offer((TreeNode)iterator.next());
            }
        }
        return nodeList;
	}
	
	/*
	 * Returns all TreeNode objects in the entire tree
	 * includes all sub nodes
	 */
	public List<TreeNode> getAllNodes() {
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
    	Queue<TreeNode> queue = new java.util.LinkedList<TreeNode>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.poll();
            	nodeList.add(node);	
            if (!node.getChildren().isEmpty()) {
            	Iterator<TreeNode> iterator = node.getChildren().iterator();
            	while (iterator.hasNext()) queue.offer((TreeNode)iterator.next());
            }
        }
        return nodeList;
	}
	
	/*
	 * conduct a breadth search to find a TreeNode object by value
	 */
	public TreeNode find(int value) {
    	Queue<TreeNode> queue = new java.util.LinkedList<TreeNode>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.poll();
            	if (node.getValue()==value) {
            		return node;
            	}
            if (!node.getChildren().isEmpty()) {
            	Iterator<TreeNode> iterator = node.getChildren().iterator();
            	while (iterator.hasNext()) queue.offer((TreeNode)iterator.next());
            }
        }
        return null;
	}
	
	/*
	 * Get the next available value to use for a new TreeNode
	 */
	public int getAvailableValue() {
		int highValue = 0;
    	Queue<TreeNode> queue = new java.util.LinkedList<TreeNode>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.poll();
            	if (node.getValue()>highValue) {
            		highValue = node.getValue();
            	}
            if (!node.getChildren().isEmpty()) {
            	Iterator<TreeNode> iterator = node.getChildren().iterator();
            	while (iterator.hasNext()) queue.offer((TreeNode)iterator.next());
            }
        }
        return highValue + 1;
	}

}
