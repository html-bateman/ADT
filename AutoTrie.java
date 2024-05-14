import java.util.Map;
import java.util.Set;
import java.util.Queue;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;
class Trie{
	Node root;
	Trie(){
		root = new Node();
	}
	class Node{
		String value;
		Map<String,Node> child;
		ArrayList<Node> letters;
		boolean isWord;
		Node(){
			value = "";
			child = new HashMap<>();
			letters = new ArrayList<>();
			isWord = false;
		}
	}
	void insert(String value){
			Node current = root;
			char[] chars = value.toString().toCharArray();
			for(char c: chars){
				current.child.putIfAbsent(String.valueOf(c),new Node());
				current = current.child.get(String.valueOf(c));
			}
			current.value = value;
			current.isWord = true;
	}
	void add(String value){
		Node current = root;
		for(char c:value.toCharArray()){
			boolean isFound = false;
			for(Node letter:current.letters){
				if(letter.value.equals(String.valueOf(c))){
					isFound = true;
					current = current.letters.get(current.letters.indexOf(letter));
				}
			}
			if(!isFound){
				Node node = new Node();
				node.value = String.valueOf(c);
				current.letters.add(node);
				current = current.letters.get(current.letters.indexOf(node));
			}
		}
		current.value = value;
		current.isWord = true;
		
	}

	void find(String value){
		Node current = root;	
		for(char c:value.toCharArray()){
			boolean isLetterFound = false;
			boolean isWordFound = false;
			String alphabet = String.valueOf(c);
			for(Node letter:current.letters){
				System.out.println(letter.value+":"+letter.isWord);//the current node is letter
				if(letter.value.equals(value)&&letter.isWord==true){	
					//the value of the last node was updated to the word,not an anphabet anymore	
					isWordFound=true;
					System.out.println("Found the word.");
					return;
				}
				if(letter.value.equals(alphabet)) {isLetterFound=true;current=letter;break;}
			}
			if(isLetterFound==false){System.out.println("No such word.");return;}
		}
		//did not found the word
		System.out.println("No such word.");		
	}

	void findWordsStartWith(String value){
		Node current = root;
		boolean isLetterFound = false;
		for(char c:value.toCharArray()){
			isLetterFound = false;
			String alphabet = String.valueOf(c);
			for(Node letter:current.letters){
				if(letter.value.equals(alphabet)){
					isLetterFound = true;
					current = letter;
					break;
				}
			}
			if(isLetterFound==false){System.out.println("No such letter.");return;}
		}
		//Traverse(current);
		TraverseQueue(current);	
	}

	void Traverse(Node current){
		if(current!=null){
		for(Node letter:current.letters){
			if(letter.isWord) System.out.println("Found word:"+letter.value);
			Traverse(letter);
		}
		}
	}
	
	void TraverseQueue(Node start){
		Queue<Node> q = new LinkedList<>();
		q.add(start);
		while(!q.isEmpty()){
			Node current = q.poll();
			if(current!=null){
				for(Node letter:current.letters){
					if(letter.isWord) System.out.println("Found word:"+letter.value);
					q.add(letter);
				}
			}
		}
	}

	void search(String value){
			Node current = root;
			char[] chars = value.toCharArray();
			for(char c:chars){
				if(!current.child.containsKey(String.valueOf(c))) {System.out.println("No such node:"+c); return;}
				current = current.child.get(String.valueOf(c));
			}
			System.out.println("Found the node:"+current.value);
	}

	void searchWordsStartWith(String value){	
			Node current = root;
			char[] chars = value.toCharArray();
			for(char c:chars){
				if(!current.child.containsKey(String.valueOf(c))) {System.out.println("No such node:"+c); return;}
				current = current.child.get(String.valueOf(c));
			}
			//iterate(current);
			iterateQueue(current);
	}

	//depth-first
	void iterate(Node current){
		if(current!=null){
			if(current.isWord) System.out.println("Found word:"+current.value);
			Set<String> keys = current.child.keySet();
			for(String k:keys) iterate(current.child.get(k));
		}
	}
	//width-first
	void iterateQueue(Node startNode){
		Queue<Node>  q = new LinkedList<>();
		q.add(startNode);
		while(!q.isEmpty()){
			Node current = q.poll();
			if(current!=null){
				if(current.isWord) System.out.println("Found word:"+current.value);
				Set<String> keys = current.child.keySet();
				for(String key:keys) q.add(current.child.get(key));
			}
		}
	}
	

	public static void main(String[] args){
		Trie trie = new Trie();
		trie.insert("appletree");
		trie.insert("pear");
		trie.search("appletree");
		trie.search("apples");	
		trie.search("pear");
		trie.insert("a");
		trie.insert("at");
		trie.insert("ate");
		trie.insert("ear");
		trie.insert("east");
		trie.insert("eat");
		trie.insert("eats");
		trie.search("eas");
		trie.search("eats");
		trie.search("ate");
		System.out.println("___________");
		trie.add("a");
		trie.add("what");
		trie.add("trie");
		trie.add("ssr");
		trie.find("trie");
		trie.add("ganster");
		trie.find("ganster");
		System.out.println("___________to find gans------");
		trie.find("gans");
		System.out.println("____to find gansters------");
		trie.find("gansters");
		trie.findWordsStartWith("gans");
		System.out.println("----------test traverse---------");
		trie.add("antadfadsfasdf");
		trie.add("antiefasdf");
		trie.add("angryy");
		trie.add("angle");
		trie.add("anti");
		trie.find("anti");
		trie.add("ant");
		trie.add("ankle");
		trie.add("animal");
		trie.findWordsStartWith("an");
		System.out.println("----------test iterate------------");
		trie.insert("bread");
		trie.insert("breathe");
		trie.insert("break");
		trie.insert("breach");
		trie.search("breach");
		trie.searchWordsStartWith("brea");
		System.out.println("-------test width-first search");
		trie.insert("candicate");
		trie.insert("can");
		trie.insert("canada");
		trie.insert("canon");
		trie.insert("cansas");
		trie.insert("cannon");
		trie.searchWordsStartWith("can");
		
	}
}
