import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class determinant {

	public static int determinant(int a[][], int n){
		int det = 0, sign = 1, p = 0, q = 0;

		if(n==1){
			det = a[0][0];
		}
		else{
			int b[][] = new int[n-1][n-1];
			for(int x = 0 ; x < n ; x++){
				p=0;q=0;
				for(int i = 1;i < n; i++){
					for(int j = 0; j < n;j++){
						if(j != x){
							b[p][q++] = a[i][j];
							if(q % (n-1) == 0){
								p++;
								q=0;
							}
						}
					}
				}
				det = det + a[0][x] * determinant(b, n-1) * sign;
				sign = -sign;
			}
		}
		return det;
	}
	public static void main(String args[]) throws FileNotFoundException{
		Scanner sc = new Scanner(new File("test2.txt"));
		
		int size = Integer.parseInt(sc.nextLine());
		int matrix [][] = new int [size][size]; 
		for(int i = 0; i < size; i++){
			String a = sc.nextLine();
			String b [] = a.split(" ");
			int seg [] = new int [size];
			for(int x = 0; x < b.length; x++){
				seg[x] = Integer.parseInt(b[x]);
			}
			for(int x = 0; x < seg.length; x++){
				matrix [i][x] = seg[x];
			}
		}
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(determinant(matrix, size));
	}

}
