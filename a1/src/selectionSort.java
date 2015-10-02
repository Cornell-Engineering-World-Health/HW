
public class selectionSort {	 
    public static int[] doSelectionSort(int[] array){
         
        for (int i = 0; i < array.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j] < array[index])
                    index = j;
      
            int temp = array[index]; 
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }
     
    public static void main(String args[]){

    }

}
