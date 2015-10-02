// Selection Sort
import java.lang.Object;
import java.util.Arrays;

class manish1 {

public static int getMin(int[] list) {
	int min_index = 0;
	for (int i = 0 ; i < list.length ; i++){
		if (list[i] < list[min_index])
			min_index = i;
	}
	return min_index;
}

public static int[] selectionSort (int[] list) {
	for (int i = 0 ; i < list.length ; i++){
		int min_index = getMin(list);
		int temp = list[i];
		list[i] = list[min_index];
		list[min_index] = list[i];
	}
	return list;
}
 
  public static void main(String[] args){
  	int[] list = {3,6,2,6,9,5,2,56,8,0,3};
  	int[] sorted = selectionSort(list);
  	for (int i = 0 ; i < list.length ; i++){
  		System.out.println(sorted[i]);
  	}
  }
}