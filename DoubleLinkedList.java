class DoubleLinkedList<E>{
	class LinkedNode<E>{
		LinkedNode<E> next;
		LinkedNode<E> prev;
		E data;
		LinkedNode(E data){
			this.data = data;
		}
	}
	LinkedNode<E> head;
	LinkedNode<E> tail;
	static int size;
	DoubleLinkedList(){
		head = new LinkedNode<E>(null);
		tail = new LinkedNode<E>(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}
	void addFront(E data){
		LinkedNode<E> node = new LinkedNode<>(data);	
		node.next = head.next;
		node.prev = head;
		node.next.prev = node;
		node.prev.next = node;
		size++;
	}
	void addLast(E data){
		LinkedNode<E> node = new LinkedNode<E>(data);
		node.next = tail;
		node.prev = tail.prev;
		node.prev.next = node;
		node.next.prev = node;
		size++; 
	}
	void print(){
		LinkedNode<E> current = head.next;
		for(int k=0;k<size;k++){
			System.out.println(current.data);
			current=current.next;
		}
	}
	int size(){
		return size;
	}
	E get(int index){
		LinkedNode<E> current = head.next;
                for(int k=0;k<size;k++){
			if(index==k) return current.data;
                        current=current.next;
                  }
		return null;
	}
	void remove(int index){
		LinkedNode<E> current = head.next;
		for(int k=0;k<size;k++){
			if(k==index){
				current.prev.next = current.next;
				current.next.prev = current.prev;
				current.next = null;	
				current.prev = null;
				size--;
				return;
			}
			current=current.next;
		}
	}
	public static void main(String[] args){
		DoubleLinkedList<Integer> IntList =  new DoubleLinkedList<>();
		IntList.addFront(1);
		IntList.addFront(2);
		IntList.addFront(3);
		IntList.print();
		System.out.println("The first Elment of IntList is: "+IntList.get(0));
		DoubleLinkedList<String> StringList = new DoubleLinkedList<>();
		StringList.addLast("Self-affirmatation");
		StringList.addLast("Purpose");
		StringList.addLast("Purposeful");
		StringList.print();
		System.out.println("The last Element of StringList is: "+StringList.get(size-1));
		StringList.remove(0);
		StringList.print();
		DoubleLinkedList<Object> ObjectList = new DoubleLinkedList<>();
		ObjectList.addLast(1);
		ObjectList.addLast(1.23);
		ObjectList.addLast("Data Structure");
		ObjectList.print();
	}
}
