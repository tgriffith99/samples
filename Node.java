package codesample.tree;

import java.util.List;

public interface Node {

	/*
	 * Author: Tim Griffith
	 * Date: 03-27-2016
	 * 
	 * Basic Node interface for TreeNode
	 */
	
	int getValue();
    String getName();
    int getBirthYear();
    int getDeathYear();
    List<TreeNode> getChildren();
	int compareTo(TreeNode otherNode);
}