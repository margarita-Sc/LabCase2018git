package Fase2;
import java.util.Queue;
import java.util.LinkedList;

public class BSTNode {
	//Integer key;
	Usuario elem;
	
	//reference to its parent
	BSTNode parent;
	//reference to its left child
	BSTNode left;
	//reference to its right child
	BSTNode right;
	
	//Constructor
	public BSTNode(Usuario elem) {
		this.elem=elem;
	}
		
	//returns the depth of the invoking node
	//an iterative version
	public int getDepth() {
		int depth=0;
		//we need to define a variable node,
		//that allows us to ascend through its ancestors
		//until we reach the root
		BSTNode node=this.parent;
		while (node!=null) {
			depth++;
			node=node.parent;
		}
		return depth;
	}
	
	//A recursive version to obtain the depth of the invoking node
	public int getRecDepth1() {
		if (this.parent==null) return 0;
		else return this.parent.getRecDepth2();
	}
		
	//Another recursive version 	using an auxiliary method
	//I think that this is more elegant than the previous one
	public int getRecDepth2() {
		return getRecDepth2(this);
	}
	protected static int getRecDepth2(BSTNode node) {
		if (node==null) return 0;
		else return 1 + getRecDepth2(node.parent);
	}
	
	//returns the number of the subtree than hangs from the invoking node
	public int getSize() {
		return getSize(this);
	}
	protected static int getSize(BSTNode node) {
		if (node==null) return 0;
		else return 1 + getSize(node.left)+getSize(node.right);
	}
	
	//a recursive version that does not use an auxiliary method
	//I don't like it, but it's right
	public int getSize2() {
		int result;
		if (this.left==null && this.right==null) 
			result=1;
		else if (this.left!=null && this.right!=null) 
			result= 1 + this.left.getSize2()+this.right.getSize2();
		else if (this.left!=null)
			result= 1 + this.left.getSize2();
		else //(this.right!=null)
			result= 1 + this.right.getSize2();
		return result;
	}

	

	//return the height of the node (which is the longest path 
	//from the node to any leaf)
	public int getHeight() {
		return getHeight(this);
	}
	protected static int getHeight(BSTNode node) {
		if (node==null) return 0;
		else return 1 + Math.max(getHeight(node.left), getHeight(node.right));
	}

	//shows the nodes of the subtree in pre-order
	public void showPreOrder() {
		showPreOrder(this);
		System.out.println();
	}
	protected static void showPreOrder(BSTNode node) {
		if (node!=null) {
			System.out.print(node.elem.username+"\t");
			showPreOrder(node.left);
			showPreOrder(node.right);
			
		}
	}
	
	//shows the nodes of the subtree in in-order
		public void showInOrder() {
			showInOrder(this);
			System.out.println();
		}
		protected static void showInOrder(BSTNode node) {
			if (node!=null) {
				showInOrder(node.left);
				System.out.print(node.elem.username+"\t");
				showInOrder(node.right);
				
			}
		}

		//shows the nodes of the subtree in in-order
		public void showPostOrder() {
			showPostOrder(this);
			System.out.println();
		}
		protected static void showPostOrder(BSTNode node) {
			if (node!=null) {
				showPostOrder(node.left);
				showPostOrder(node.right);
				System.out.print(node.elem.fullName+"\t");
			}
		}

	
	public void showLevels() {
		//we define a queue of BSTNodes.
		//Queue is an interface
		//LinkedList is the implementation
		//Java allows us to create collections of generic types
		//with <>... in our case, we choose BSTNode.
		Queue <BSTNode>queue = new LinkedList<BSTNode> ();
		//we add the current node
		queue.add(this);
		
		while (!queue.isEmpty()) {
			BSTNode current=queue.poll();
			System.out.print(current.elem.username+"\t");
			//now, we add the its two children
			if (current.left!=null) queue.add(current.left);
			if (current.right!=null) queue.add(current.right);
			
		}
	}

	}
