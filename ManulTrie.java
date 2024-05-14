class ManualTrie<E>{
	Node<E> root;
	ManualTrie(){
		root = new Node<>();
	}
static	class Node<E>{
		E value;
		Node<E> parent;
		Node<E> a;
		Node<E> e;
		Node<E> r;
		Node<E> s;
		Node<E> t;
	
	   	Node(){
			this.value = null;
			this.parent = null;
			this.a = null;
			this.e = null;
			this.r = null;
			this.s = null;
			this.t = null;
		}
	}
	void find(E value){
		Node current = root;
		char[] chars = value.toString().toCharArray();
		for(char c:chars){
			if(c == 'a'&&current.a!=null) current = current.a;
			if(c == 'e'&&current.e!=null) current = current.e;
			if(c == 'r'&&current.r!=null) current = current.r;
			if(c == 's'&&current.s!=null) current = current.s;
			if(c == 't'&&current.t!=null) current = current.t;
		}
		System.out.println(current.value);
	}
		
	public static void main(String[] args){
		ManualTrie<String> Trie = new ManualTrie<String>();
		Trie.root.value = "eat";

		//Node<String> nodea = new Node<>();
		Trie.root.a = new Node<>();
		Trie.root.a.parent = Trie.root;
		Trie.root.a.value = "a";

		Node<String> nodeat = new Node<>();
		Trie.root.a.t = nodeat;
		Trie.root.a.t.parent = Trie.root.a;
		Trie.root.a.t.value = "at";

		Node<String> nodeate = new Node<>();
		Trie.root.a.t.e = nodeate;
		Trie.root.a.t.e.parent = Trie.root.a.t;
		Trie.root.a.t.e.value = "ate";
		

		Node<String> emptye = new Node<>();
		Trie.root.e = emptye;
		Trie.root.e.parent = Trie.root;		

		Node<String> emptya = new Node<>();
		Trie.root.e.a = emptya;
		Trie.root.e.a.parent = Trie.root.e;

		Node<String> ear = new Node<>();
		Trie.root.e.a.r = ear;
		Trie.root.e.a.r.parent = Trie.root.e.a;
		Trie.root.e.a.r.value = "ear";
		
		Node<String> emptys = new Node<>();
		Trie.root.e.a.s = emptys;
		Trie.root.e.a.s.parent = Trie.root.e.a;
		
	
		Node<String> nodeeast = new Node<>();
		Trie.root.e.a.s.t = nodeeast;
		Trie.root.e.a.s.t.parent = Trie.root.e.a.s;
		Trie.root.e.a.s.t.value = "east";
		
		Node<String> nodeeat = new Node<>();
		Trie.root.e.a.t = nodeeat;
		Trie.root.e.a.t.parent = Trie.root.a.t;
		Trie.root.e.a.t.value = "eat";
	
		Trie.find("east");
	}

}
