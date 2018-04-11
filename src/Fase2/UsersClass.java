package Fase2;

import Fase1.LstRequest;

public class UsersClass implements IBSTree {
BSTNode root;
public UserClass(BSTNode root){
	this.root=root;
}
//search the node whose key is key and 
//returns its associated value
public String findUser(String key) {
	return findUser(root, key);
}

/**
 * The method searches the key into the subtree 
 * that hangs down from currentNode
 * Note: It would be more correct 
 * for the method to be in the BSTNode class
 * @param currentNode
 * @param key
 * @return
 */
private static String findUser(BSTNode currentNode, String key) {
	String result=null;
	if (currentNode == null) {
		//System.out.prinltn(key + " does not exist!");
	} else {
		if (key.equals(currentNode.elem.fullName))
			result= currentNode.elem.fullName;
		else if (key.compareTo(currentNode.elem.fullName) < 0)
			result=findUser(currentNode.left, key);
		else
			result=findUser(currentNode.right, key);
	}
	return result;
}

//creates a new node and inserts it into the tree
//first, it must search its right position into the tree
//the new nodes must always be inserted as leaves
public void insertUser(Usuario element) {
	BSTNode newNode=new BSTNode (element);
	if (root==null) root=newNode;
	else insertUser(newNode, root);
}

/**
 * This method first looks for (recursively) the right position and then
 * inserts the new node.
 * Note: It would be more correct 
 * for the method to be in the BSTNode class
 * @param newNode 
 * @param currentNode node where you start the search
 * 
 */
protected static void insertUser(BSTNode newNode, BSTNode currentNode) {
	if (currentNode==null) return;
	String username=newNode.elem.username;
	if (username.compareTo(currentNode.elem.username)==0) {
		System.out.println("The user already exists.");
		return;
	}
	if (username.compareTo(currentNode.elem.username)<0) {
		if (currentNode.left==null) {
			//the method already finds the right position
			currentNode.left=newNode;
			newNode.parent=currentNode;
		} else {
			//the method must continue searching...
			insertUser(newNode,currentNode.left);
		}
	} else {
		if (currentNode.right==null) {
			//the method already finds the right position
			currentNode.right=newNode;
			newNode.parent=currentNode;
		} else {
			//the method must continue searching...
			insertUser(newNode,currentNode.right);
		}
	}
	
}


//searches the node whose key is key and removes it from the tree
//the method uses a recursive method that returns the modified tree
public void removeUser(String key) {
	root=removeUser(key,root);
}

/**
 * 
 * @param key
 * @param currentNode
 * @return
 */
private BSTNode removeUser(String key, BSTNode currentNode) {
	if (currentNode == null) {
		System.out.println("Cannot remove: The key doesn't exist");
		return null;
	}
	
	if (key.compareTo(currentNode.elem.username)<0) {
		currentNode.left=removeUser(key,currentNode.left);
		return currentNode;
	}
	
	if (key.compareTo(currentNode.elem.username)>0) {
		currentNode.right=removeUser(key,currentNode.right);
		return currentNode;

	}
	
	//Here, it means that key.compareTo(node.Key)==0
	//That is, we already have found it: node is the node to remove.
	//First case: the node is a leaf.
	if (currentNode.left==null && currentNode.right==null) {
		return null;
	}

	//Second case is one the node only has a child: left or right
	if (currentNode.left==null)  {
		currentNode.right.parent = currentNode.parent;
		return currentNode.right;
	}
	
	if (currentNode.right==null) {
		currentNode.left.parent = currentNode.parent;
		return currentNode.left;
	}
	
	//Third case: node has two childs.
	//We can replace its value by the maximum value in its left child or
	//by the minimum value in its right child

	BSTNode successorNode = currentNode.right;
	while (successorNode.left!=null) {
		successorNode=successorNode.left;
	}
	//replace its key and its elem by the successorNodes's key and elem
	currentNode.elem=successorNode.elem;
	currentNode.elem.username=successorNode.elem.username;
	
	//Finally, we must remove the sucesorNode (which is one of the two previous cases)
	currentNode.right=removeUser(successorNode.elem.username,currentNode.right);
	return currentNode;

	 
	
}


	 
public void showPreorder() {
	if (root!=null) root.showPreOrder();
	else System.out.println("tree is empty");
}

public void showInorder() {
	 if (root!=null) root.showInOrder();
	else System.out.println("tree is empty");
}

public void showPostorder() {
	if (root!=null) root.showPostOrder();
	else System.out.println("tree is empty");
}

public int getSize() {
	return BSTNode.getSize(root);
}

public int getHeight() {
	return BSTNode.getHeight(root);
}
public void show(){
	
}
public String extremeUsers(){
	String result=null;
	return result;
}
public void showLevel() {
	if (root!=null) {
		root.showLevels();
	}
}


public BSTNode getPredecessor(BSTNode node) {
	if (node==null) return null;

	BSTNode predecessor=node.left;
	while (predecessor.right!=null) {
		predecessor=predecessor.right;
	}
	return predecessor;
}

//returns its successor node 
//(which is the most-left node of its right child)
public BSTNode getSuccesor(BSTNode node) {
	if (node==null) return null;
	
	BSTNode successor=node.right;
	while (successor.left!=null) {
		successor=successor.left;
	}
	return successor;
}

//returns the smallest key
//The smallest key is the key of the most-left node 
public String getSmallestKey() {
	String result;
	if (root!=null) {
		BSTNode mostLeft=root;
		while (mostLeft.left!=null) {
			mostLeft=mostLeft.left;
		}
		result=mostLeft.elem.username;
	} else {
		//we could throw an exception 
		System.out.println("error: tree is empty");
		//or any value such as -1
		result= "";
	}
	return result;
}


//returns the largest key
//The largest key is the key of the most-right node 
public String getMaximumKey() {
	String result;
	if (root!=null) {
		BSTNode mostRight=root;
		while (mostRight.right!=null) {
			mostRight=mostRight.right;
		}
		result=mostRight.elem.username;
	} else {
		//we could throw an exception 
		System.out.println("error: tree is empty");
		//or any value such as -1
		result= "";
	}
	return result;
}

public void complaint(String username){
	if (findUser(username)!= null);
	
}

public static int getFactorSize(BSTNode node) {
	if (node==null) return 0;
	else return Math.abs(BSTNode.getSize(node.right)-BSTNode.getSize(node.left));
}
public static int getFactorHeight(BSTNode node) {
	if (node==null) return 0;
	else return Math.abs(BSTNode.getHeight(node.right)-BSTNode.getHeight(node.left));
}

//checks if the tree is size balanced
public boolean isSizeBalalanced() {
	return isSizeBalanced(root);
}
//checks if the subtree than hangs down from node 
//is size balanced
public static boolean isSizeBalanced(BSTNode node) {
	if (node==null) return true;
	return getFactorSize(node)<=1 && isSizeBalanced(node.left) && isSizeBalanced(node.right);
}

//checks if the tree is height balanced (AVL
public boolean isHeightBalalanced() {
	return isHeightBalalanced(root);
}
//checks if the subtree than hangs down from node 
//is height balanced
public static boolean isHeightBalalanced(BSTNode node) {
		if (node==null) return true;
		return getFactorSize(node)<=1 && isHeightBalalanced(node.left) 
				&& isHeightBalalanced(node.right);
	}


		public static void main(String args[]) {
		Usuario user1=new Usuario("isegura", "Isabel", 20, "mujer",3);
		Usuario user2=new Usuario("asegura", "Maria", 45, "mujer",1);
		Usuario user3=new Usuario("bsegura", "Isabel", 28, "mujer",2);
		Usuario user4=new Usuario("csegura", "Carlos", 23, "hombre",4);
		Usuario user5=new Usuario("dsegura", "Julio", 70 ,"hombre",0);
		Usuario user6=new Usuario("esegura", "Andrea", 67, "mujer",7);
		
		
		UsersClass one = new UsersClass();
		one.insertUser(user1);
		one.insertUser(user2);
		one.insertUser(user3);
		one.insertUser(user4);
		one.insertUser(user5);
		one.insertUser(user6);
		
		
		System.out.println(one.findUser("bsegura"));
		System.out.println(one.extremeUsers());
		System.out.println(one.getSize());
		System.out.println(one.root);
	}
		}
