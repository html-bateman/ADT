class SingleLinkedList<E>{	
	class LinkedNode<E>{
		LinkedNode<E> next;
		E data;
		
		LinkedNode(E data){
			this.data=data;
			//this.next=null; //field will be initialized with default value if it is not explicitly intilizied
		}

		LinkedNode(E data,LinkedNode<E> head){
			this(data);
			this.next=head.next;//connect current node to next node
			head.next=this; //connect head node to current node
		}
	}

	LinkedNode<E> head;
	int size;
	
	SingleLinkedList(){
		head = new LinkedNode<E>(null);
		size = 0;
	}

	SingleLinkedList(E data){
		this();
		LinkedNode newNode = new LinkedNode<E>(data,head);
		size++;
	}

	void add(E data){
		//beware of the format,the upper initialization is unsual, but it works
		LinkedNode<E> newNode = new LinkedNode<E>(data,head);
		size++;
	}
	
	//the stucture is modifed,it won't cause problem for just printing
	//but it will cause problem for the following code since the DATA STRUCTURE is already CHANGED!!	
	void BUGGEDprint(){
		for(int k=0;k<size;k++) {System.out.println(head.next.data); head=head.next;}
	}

	void print(){
		LinkedNode<E> curr = head.next;
		for(int k=size-1;k>=0;k--) {System.out.println(curr.data);curr=curr.next;}
	}
	@SuppressWarnings("unchecked")
	void printReverse(){
		LinkedNode<E> curr = head.next;
		Object[] arr = new Object[size];
                for(int k=size-1;k>=0;k--) {arr[k]=(Object)curr.data;curr=curr.next;}
		for(int k=0;k<arr.length;k++){System.out.println((E)arr[k]);}
	}
	
	//but it will cause problem for return since the data structure should remain the original state
	E get(int n){
		LinkedNode<E> current = head.next;
		for(int k=size-1;k>=0;k--){
			if(k==n&&current!=null){return current.data;}
			if(current!=null) current=current.next;
		}
		return null;
	}

	E getFirst(){
		return head.next.data;
	}

	public static void main(String[] args){
		SingleLinkedList<Integer> sll = new SingleLinkedList<>(1);
		sll.add(2);
		sll.add(3);
		sll.add(4);
		System.out.println(	sll.getFirst());
		SingleLinkedList<String> slls = new SingleLinkedList<>("Shenmmu");
		slls.add("data");
		slls.add("sturcture");
		sll.print();
		slls.print();
		System.out.println(sll.get(0)+slls.get(0));
		System.out.println(slls.getFirst());
		sll.printReverse();
		slls.printReverse();
	}
	
	
}
