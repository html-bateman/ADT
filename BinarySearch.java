public class BinarySearch{
	public static int binarySearch(int toFind, int[] array){
		int start = 0;
		int end = array.length-1;
		int mid = (end-start)/2;
		//System.out.println("log mid is :"+mid);
		boolean finded = false;
		while(!finded){
			if(array[mid]>toFind){
				end = mid-1;
			}
			if(array[mid]<toFind){
				start = mid+1;
			}	
			if(end<start) break;
			mid = (end - start)/2 + start;
			if(array[mid]==toFind){
				finded = true;
				System.out.println("Find the number:"+toFind+" at: "+mid);
				return mid;
			}
		}
		System.out.println("No such number!");
		return -1;
	}
	public static String binarySearch(String toFind, String[] array){
		int low = 0;
		int high = array.length-1;
		while(high>=low){
			int mid = (high - low)/2 + low;	
			if(array[mid].compareTo(toFind)>0) high=mid-1;
			if(array[mid].compareTo(toFind)<0) low=mid+1;
			if(array[mid].compareTo(toFind)==0){
				System.out.println("Find the String:"+toFind+" at: "+mid);
				return array[mid];
			}
		}
		System.out.println("No such word!");
		return "";
	}

public static void main(String args[]){
	int[] array = {-1,0,1,2,3,4,5,6,7,8,9,99};
	binarySearch(-1,array);
	binarySearch(11,array);
	binarySearch(2,array);
	binarySearch(3,array);
	binarySearch(4,array);
	binarySearch(5,array);
	binarySearch(6,array);
	binarySearch(7,array);
	binarySearch(8,array);
	binarySearch(99,array);
	binarySearch(100,array);
	String[] arr = {"Acadia Bay","Beijing","Chicago","Dartmoutn","Fancisco","Georgetown"};
	binarySearch("Acadia Bay",arr);
	binarySearch("wassup",arr);
	binarySearch("Beijing",arr);
	binarySearch("Fancisco",arr);
	binarySearch("Georgetown",arr);
}

}
