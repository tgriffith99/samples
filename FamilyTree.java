package codesample.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import codesample.tree.Tree;
import codesample.tree.TreeNode;

@ManagedBean
@SessionScoped
public class FamilyTree implements Serializable {
	
	/*
	 * Author: Tim Griffith
	 * Date: 03-27-2016
	 * 
	 * JSF Message Bean for implementation of Family Tree web application
	 */

	private static final long serialVersionUID = 1L;

	private Tree myTree;
	private TreeNode currentNode;
	private int currentNodeValue;
	private String name;
	private String birthYear;
	private String deathYear;

	@SuppressWarnings("unused")
	@PostConstruct
	public void init() {
		
		/*
		 * Build a temporary tree with sample data for application use
		 */
		
		myTree = new Tree(1,"John Smith",1895,1941);
		TreeNode node1=new TreeNode(2,"John Smith Jr",1905,1980,myTree.getRoot());
		TreeNode node2=new TreeNode(3,"Jane Smith-Johnson",1907,1978,myTree.getRoot());
		TreeNode node3=new TreeNode(4,"John Smith III",1927,1994,node1);
		TreeNode node4=new TreeNode(5,"Bill Johnson",1930,1998,node2);
		TreeNode node5=new TreeNode(6,"Ben Johnson",1933,0,node2);
		TreeNode node6=new TreeNode(7,"Bob Johnson",1930,0,node2);
		this.setCurrentNode(myTree.getRoot());
		this.setCurrentNodeValue(currentNode.getValue());
	}

	public Tree getMyTree() {
		return myTree;
	}

	public void setMyTree(Tree myTree) {
		this.myTree = myTree;
	}

	public TreeNode getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(TreeNode currentNode) {
		this.currentNode = currentNode;
	}
	
	public List<TreeNode> getAllDescendants() {
		return myTree.getAllNodeDescendants(currentNode);
	}

	public List<TreeNode> getTreeList() {
		return myTree.getAllNodes();
	}
	
	public List<TreeNode> getLivingDescendants() {
		List<TreeNode> nodeList = myTree.getAllNodeDescendants(currentNode).stream().filter(u -> u.getDeathYear() == 0).collect(Collectors.toList());
		Collections.sort(nodeList);
		return nodeList;
	}

	public int getCurrentNodeValue() {
		return currentNodeValue;
	}

	public void setCurrentNodeValue(int currentNodeValue) {
		this.currentNodeValue = currentNodeValue;
	}
	
	public void changeCurrentNode(ValueChangeEvent e) {
		int newValue = Integer.parseInt(e.getNewValue().toString());
		this.setCurrentNode(myTree.find(newValue));
		this.setCurrentNodeValue(currentNode.getValue());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getDeathYear() {
		return deathYear;
	}

	public void setDeathYear(String deathYear) {
		this.deathYear = deathYear;
	}
	
	/*
	 * Method to add a child entry to an individual with validation
	 */
	@SuppressWarnings("unused")
	public String addChild() {
		boolean isError=false;
		int birthYear=0;
		int deathYear=0;
		if (this.name.equals("")) {
			isError=true;
			sendInfoMessage("Name can not be blank!");
		}
		if (this.birthYear.matches("^[12][0-9]{3}$")) {
			birthYear = Integer.parseInt(this.birthYear);
			if (birthYear<=currentNode.getBirthYear()) {
				isError=true;
				sendInfoMessage("Child can not be born before parent!");
			}
		} else {
			isError=true;
			sendInfoMessage("Birth year must be a valid 4-digit year!");
		}
		if (!this.deathYear.equals("")) {
			if (this.deathYear.matches("^[12][0-9]{3}$")) {
				deathYear = Integer.parseInt(this.deathYear);
				if (deathYear<birthYear) {
					isError=true;
					sendInfoMessage("Year of Death can not be before Birth Year!");
				}
			} else {
				isError=true;
				sendInfoMessage("Year of Death must be a valid 4-digit year!");
			}
		}
		if (isError) {
			return "";
		} else {
			TreeNode newNode = new TreeNode(myTree.getAvailableValue(),this.name,birthYear,deathYear,currentNode);
			this.name="";
			this.birthYear="";
			this.deathYear="";
			 try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
		return "";
	}
	
	public void sendInfoMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage( null, new FacesMessage(message));
	}
	
}
