package codesample.tree;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TreeNode implements Node,Comparable<TreeNode> {
	
	/*
	 * Author: Tim Griffith
	 * Date: 03-27-2016
	 * 
	 * Implementation of Node class.  Represents one individual with
	 * a TreeNode array list for all children of that individual
	 */
	
	private int value;
	private String name;
	private int birthYear;
	private int deathYear;
	private Node parent;
	private List<TreeNode> children;
	
	public TreeNode(int val, String name,int birthYear, int deathYear, TreeNode parent) {
		this.value = val;
		this.name = name;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
		if (parent!=null) {
			parent.addChild(this);	
		}
		children = new ArrayList<TreeNode>();
	}
	
	@Override
	public int getValue() {
		return value;
	}
	
	public void setValue(int v) {
		this.value = v;
	}

	@Override
	public List<TreeNode> getChildren() {
		return children;
	}
	
	public void addChild(TreeNode node) {
		children.add(node);
		node.setParent(this);
	}

	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		this.name=n;
	}

	@Override
	public int getBirthYear() {
		return birthYear;
	}
	
	public void setBirthYear(int b) {
		this.birthYear=b;
	}

	@Override
	public int getDeathYear() {
		return deathYear;
	}
	
	public void setDeathYear(int d) {
		this.deathYear=d;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	/*
	 * Get the age in years of the person represented
	 * either from the current year or the year of their death
	 */
	public int getAge() {
		if (this.getDeathYear()==0) {
			Calendar dt = new GregorianCalendar();
			return dt.get(Calendar.YEAR)-this.getBirthYear();
		} else {
			return this.getDeathYear()-this.getBirthYear();
		}
	}

	@Override
	public int compareTo(TreeNode otherNode) {
		return this.getBirthYear()-otherNode.getBirthYear();
	}
}
