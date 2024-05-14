public class BinaryTree<E extends Comparable<E>>{
	TreeNode<E> root;
	public BinaryTree(E value){
		root = new TreeNode<E>(value,null);
	}
	
	public class TreeNode<E>{
		E value;
		TreeNode<E> parent;
		TreeNode<E> left;
		TreeNode<E> right;

		public TreeNode(E value,TreeNode<E> parent){
			this.value = value;
			this.parent = parent;
			this.left = null;
			this.right = null;
		}
		
		public void addLeftChild(E value){
			this.left = new TreeNode<E>(value,this);
		}

		public void addRightChild(E value){
			this.right = new TreeNode<E>(value,this);
		}

		public TreeNode<E> smallestOnRight(){
			if(this.right==null){System.out.println("No right node.");return this;}
			if(this.right.left==null){System.out.println("No left node for right subtree.");return this.right;}
			TreeNode<E> current = this.right.left;	
			while(current.left!=null) current=current.left;
			System.out.println("the smallest node on right subTree is:"+current.value);
			return current;
		}
	}

	public void addLeftChild(E value){
			TreeNode<E> node = new TreeNode<E>(value,root);
			node.parent.left = node;
		}

	public void addLeftChild(E value,TreeNode<E> current){
		TreeNode<E> node = new TreeNode<E>(value,current);
		node.parent.left = node;
	}

	public void addRightChild(E value){
			TreeNode<E> node  = new TreeNode<E>(value,root);
			node.parent.right = node;
		}

	public void addRightChild(E value,TreeNode<E> current){
		TreeNode<E> node = new TreeNode<E>(value,current);
		node.parent.right = node;
	}

	public void preOrder(TreeNode<E> node){
			if(node!=null){
				System.out.println(node.value);//vist yourself
				preOrder(node.left);//vist all your left subtree
				preOrder(node.right);//vist all your right subtree
			}
		}
	
	public void preOrder(){
		java.util.Stack<TreeNode<E>> stack = new java.util.Stack<>();
		stack.push(this.root);
		while(!stack.isEmpty()){
			TreeNode<E> node = stack.pop();
			System.out.println("Visit node:"+node.value);
			if(node.right!=null) stack.push(node.right);
			if(node.left!=null) stack.push(node.left);
		}
	}
	
	public void inOrder(TreeNode<E> current){
		if(current!=null){
			inOrder(current.left);
			System.out.println(current.value);
			inOrder(current.right);
		}
	}

public TreeNode<E> getNode(E value, TreeNode<E> node) {
    if (node != null) {
        if (node.value.equals(value)) {
            return node;
        }
        TreeNode<E> leftResult = getNode(value, node.left);
        if (leftResult != null) {
            return leftResult; // Return the result if found in the left subtree
        }
        TreeNode<E> rightResult = getNode(value, node.right);
        if (rightResult != null) {
            return rightResult; // Return the result if found in the right subtree
        }
    }
    return null; // Return null if the value is not found in the current subtree
}
	
	
	public void levelOrder(){
		java.util.Queue<TreeNode<E>> q = new java.util.LinkedList<TreeNode<E>>();
		q.add(root);
		while(!q.isEmpty()){
			TreeNode<E> current = q.remove();
			if(current!=null){
				System.out.println("Node:"+current.value);
				q.add(current.left);
				q.add(current.right);		
			}
		}
	}

	public TreeNode<E> getNode(E value){	
		java.util.Queue<TreeNode<E>> q = new java.util.LinkedList<TreeNode<E>>();
		q.add(root);
		while(!q.isEmpty()){
			TreeNode<E> current = q.remove();
			if(current!=null){
				if(current.value.equals(value)) return current;
				q.add(current.left);
				q.add(current.right);		
			}
		}
		return null;	
	}

	public void searchNode(E value){
		TreeNode<E> current = root;
		while(current!=null){
			int compare=current.value.compareTo(value);
			if(compare<0) current=current.right;
			if(compare>0) current=current.left;
			if(compare==0) {System.out.println("Found the node "+value+" at:"+current.toString());return;}	
		}
		System.out.println("No such node "+value);
	}

	
	public TreeNode<E> searchedNode(E value){
		TreeNode<E> current = root;
		while(current!=null){
			int compare=current.value.compareTo(value);
			if(compare<0) current=current.right;
			if(compare>0) current=current.left;
			if(compare==0) {System.out.println("Found the node "+value+" at:"+current.toString());return current;}	
		}
		System.out.println("No such node "+value);
		return null;
	}

	//delete the the node and its subnodes
	public void deleteSubTree(E value){	
		TreeNode<E> current = root;
		while(current!=null){
			int compare=current.value.compareTo(value);
			if(compare<0) current=current.right;
			if(compare>0) current=current.left;
			if(compare==0) {
				System.out.println("Found the node "+value+" at:"+current.toString());
				if(current.parent.right==current) {current.parent.right = null; current.parent=null;}  
				else if(current.parent.left==current) {current.parent.left = null; current.parent=null;}
				return;
			}	
		}
		System.out.println("No such node "+value);
	}
	
	public void deleteNode(E value){
		TreeNode<E> toDelete = this.searchedNode(value);
		//the node does not has sub node
		if(toDelete.right==null&&toDelete.left==null){this.deleteSubTree(value); return;}
		//the node has right sub node
		if(toDelete.right!=null){
			TreeNode<E> newToDelete = toDelete.smallestOnRight();
			toDelete.value = newToDelete.value;//replace the value
			if(newToDelete.right!=null){
				newToDelete.parent.right=newToDelete.right;
				newToDelete.right.parent=newToDelete.parent;
				newToDelete.right = null;
				newToDelete.parent=null;
				return;
			}
			if(newToDelete.parent.left==newToDelete){
				newToDelete.parent.left=null;
				newToDelete.parent=null;
			}else{
				newToDelete.parent.right=null;
				newToDelete.parent=null;
			}
			return;
		}
		//the node only has left sub node
		if(toDelete.right==null&&toDelete.left!=null){
			// the node is a right child of its parent
			if(toDelete.parent.right==toDelete){
				toDelete.parent.right=toDelete.left;
				toDelete.left.parent=toDelete.parent;
				toDelete.left = null;
				toDelete.parent = null;
				return;
			}
			//the node is a left child of its parent
			if(toDelete.parent.left==toDelete){
				toDelete.parent.left=toDelete.left;
				toDelete.left.parent=toDelete.parent;
				toDelete.left=null;
				toDelete.parent=null;
				return;
			}
		}
	}

	public void insertNode(E value){
		TreeNode<E> node = new TreeNode<E>(value,null);
		TreeNode<E> current = root;
		boolean inserted = false;
		while(!inserted){
			System.out.println("---------loop start----------");
			System.out.println("At node:"+current.value);
			if(current.value.compareTo(node.value)>0) current=current.left;
			else if(current.value.compareTo(node.value)<0) current=current.right;
			System.out.println("At node:"+current.value);
			if(current.left==null&&current.right==null) {
				node.parent = current;
				if(node.value.compareTo(current.value)>0) node.parent.right = node; 
				if(node.value.compareTo(current.value)<0) node.parent.left = node;
				inserted = true;
				System.out.println(value+" is inserted");
				System.out.println("Inserted node:"+node.value+" Parent:"+node.parent.value+" Left:"+node.left+" Right:"+node.right);
				break;
			}
			if(current.left==null&&current.right!=null){
				if(node.value.compareTo(current.value)<0){
				 System.out.println("the left child is null");
				node.parent = current;
				node.parent.left = node;
				inserted = true;		
				System.out.println(value+" is inserted");
				break;
				}
			}
			if(current.left!=null&&current.right==null){
				if(node.value.compareTo(current.value)>0){	
					System.out.println("the right child is null");
					node.parent = current;
					node.parent.right = node;
					inserted = true;
					System.out.println(value+" is inserted");
					break;
				}
			}

			/* insert in the middle of two nodes, does not work
			if(node.value.compareTo(current.value)<0&&node.value.compareTo(current.left.value)>0){
				node.parent = current;
				node.left = current.left;
				node.parent.left = node;
			}
			if(node.value.compareTo(current.value)>0&&node.value.compareTo(current.right.value)<0){
				node.parent = current;
				node.right = current.right;
				node.parent.right = node;
			}
			*/
			System.out.println("---------current loop ended---------");
		}
	}


	public boolean insert(E toInsert){
		TreeNode<E> curr = root;
		int comp = toInsert.compareTo(curr.value);
		while(comp<0&&curr.left!=null||comp>0&&curr.right!=null){
			if(comp<0) curr = curr.left; else curr = curr.right;
			comp = toInsert.compareTo(curr.value);
		}
		if(comp<0) curr.addLeftChild(toInsert);
		else if(comp>0) curr.addRightChild(toInsert);
		else return false;//found the same node
		return true;
	}

	public void createBalancedTree(java.util.ArrayList<E> list){
		int midIndex = (list.size()-1)/2;
		E rootValue = list.get(midIndex);	
		root.value = rootValue;
		this.insertMid(list,0,list.size()-1);
	}

	public void insertMid(java.util.ArrayList<E> list,int start,int end){
		int midIndex=0;
		//when there is no middle element like (0,1),insert straightforward.
		if(end-1==start){
			this.insert(list.get(start));
			System.out.println("Node:"+list.get(start)+" is inserted.");
			this.insert(list.get(end));
			System.out.println("Node:"+list.get(end)+" is inserted.");
			return;
		}
		else if(end==start){
			this.insert(list.get(end));
			System.out.println("Node:"+list.get(end)+" is inserted.");
			return;
		}
		else if(end<start) return;
		//the false operation does not crash the program
		//else midIndex=(end-start/2);
		else midIndex=((end-start)/2)+start; //beware the index, the later half of every binary list does not start at index 0
		this.insert(list.get(midIndex));
		System.out.println("Node:"+list.get(midIndex)+" is inserted.");
		insertMid(list,start,midIndex-1);
		insertMid(list,midIndex+1,end);
	}


	
	public static void main(String[] args){
		BinaryTree<Integer> IntTree = new BinaryTree<>(1);
		IntTree.addLeftChild(2);
		IntTree.addRightChild(3);
		IntTree.preOrder(IntTree.root);
		IntTree.inOrder(IntTree.root);
		IntTree.addLeftChild(4,IntTree.getNode(2,IntTree.root));
		IntTree.addRightChild(5,IntTree.getNode(2,IntTree.root));
		IntTree.addLeftChild(6,IntTree.getNode(3,IntTree.root));
		IntTree.addRightChild(7,IntTree.getNode(3,IntTree.root));
		IntTree.preOrder(IntTree.root);	
		IntTree.levelOrder();
		IntTree.getNode(4).addLeftChild(8);
		IntTree.getNode(4).addRightChild(9);
		IntTree.levelOrder();
		BinaryTree<String> Cities = new BinaryTree<>("Essen");
		Cities.addLeftChild("Beijing");
		Cities.addRightChild("Montreal");
		Cities.getNode("Beijing").addLeftChild("Arga");
		Cities.getNode("Beijing").addRightChild("Chicago");
		Cities.getNode("Montreal").addLeftChild("Lagos");
		Cities.getNode("Montreal").addRightChild("Quito");
		Cities.levelOrder();
		Cities.searchNode("Beijing");
		Cities.searchNode("Nanking");
		BinaryTree<Integer> insertTest = new BinaryTree<>(2);
		insertTest.addLeftChild(1);
		insertTest.addRightChild(10);
		insertTest.insertNode(3);
		insertTest.insertNode(4);
		insertTest.insertNode(12);
		insertTest.levelOrder();
		insertTest.preOrder(insertTest.root);
		insertTest.preOrder();
		BinaryTree<Integer> ucsdInsert = new BinaryTree<>(2);
		ucsdInsert.insert(1);
		ucsdInsert.insert(10);
		ucsdInsert.insert(3); 
		ucsdInsert.insert(4); 
		ucsdInsert.insert(12);
		ucsdInsert.deleteNode(10);
		ucsdInsert.levelOrder();
		System.out.println("--------delete test---------");
		BinaryTree<Integer> deleteTest=new BinaryTree<>(20);
		deleteTest.insert(10); 
		deleteTest.insert(30);
		deleteTest.insert(5);
		deleteTest.insert(15);
		deleteTest.insert(25);
		deleteTest.insert(12);
		deleteTest.deleteNode(10);
		deleteTest.levelOrder();
		System.out.println("________balanced tree test__________");
		BinaryTree<Integer> intBalanced = new BinaryTree<>(-1);
		java.util.ArrayList<Integer> list = new java.util.ArrayList<>(){{
			add(1);add(2);add(3);add(4);add(5);add(6); }};
		intBalanced.createBalancedTree(list);
		intBalanced.levelOrder();
		
		BinaryTree<String> stringBalanced = new BinaryTree<>("");
		String[] array = {"a","at","ate","ear","east","eat"};
		java.util.ArrayList<String> stringList = new java.util.ArrayList<>();
		for(int i=0;i<array.length;i++) stringList.add(array[i]);
		System.out.println("List to store in tree:");
		for(int k=0;k<stringList.size();k++) System.out.println(stringList.get(k));
		stringBalanced.createBalancedTree(stringList);
		stringBalanced.levelOrder();
		
		BinaryTree<String> citiesBalanced = new BinaryTree<>("");
		String[] cities = {"Argo","Beijing","Chicago","Essen","Lagos","Montreal","Quito"};	
		java.util.ArrayList<String> cityList = new java.util.ArrayList<>();
		for(int i=0;i<cities.length;i++) cityList.add(cities[i]);
		System.out.println("List to store in tree:");
		for(int k=0;k<cityList.size();k++) System.out.println(cityList.get(k));
		citiesBalanced.createBalancedTree(cityList);
		citiesBalanced.levelOrder();
	

	}
	

	

	
}
