import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class intersectionPolygons {

	public static boolean checkPoint(int x, int y, int x1, int y1, int x2, int y2) {
		int slopeY = (y2-y1);
		int slopeX = (x2-x1);
		double intersect = 0;
		if(slopeX != 0){
			double intercept = y1 - slopeY/slopeX * x1;
			intersect = (slopeY/slopeX * x) + intercept;
			
		}
		else if (slopeX == 0){
			intersect = x;
		}
		
		if(x > x1 && x < x2){
			return true;
		}
		else{
			return false;
		}
		
	}
	public static int checkAllSegments (int x, int y, int [][] matrix){
		int seg = 0;
		for(int i = 0; i < matrix.length; i++){
			if (checkPoint(x, y, matrix[i][0], matrix[i][1], matrix[i][2], matrix[i][3])){
				seg++;	
			}
		}
		return seg;
	}
	public static ArrayList<int[]> checkAllPoints 
	(int minX, int minY, int maxX, int maxY, int[][] matrix){
	ArrayList<int[]> pts = new ArrayList<int[]>();
	for(int i = minX; i < maxX; i++){
		for(int j = minY+1; j < maxY; j++){
			if(checkAllSegments(i, j, matrix) % 2 == 1){
				int a [] = {i,j};
				pts.add(a);
			}
		}
	}
	return pts;
	}
	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc = new Scanner(new File("test3.txt"));
		
		int size = Integer.parseInt(sc.nextLine());
		
		int poly1 [][] = new int [size][4]; 
		
		int minX = 20; 
		int minY = 20;
		int maxX = 0;
		int maxY = 0;
		
		for(int i = 0; i < size; i++){
			String a = sc.nextLine();
			String b [] = a.split(" ");
			int seg [] = new int [4];
			for(int x = 0; x < b.length; x++){
				
				seg[x] = Integer.parseInt(b[x]);
				
				if(x % 2 == 0 && maxX < Integer.parseInt(b[x])){
					maxX = Integer.parseInt(b[x]);
				}
				else if(x % 2 == 1 && maxY < Integer.parseInt(b[x])){
					maxY = Integer.parseInt(b[x]);
				}
				
				if(x % 2 == 0 && minX > Integer.parseInt(b[x])){
					minX = Integer.parseInt(b[x]);
				}
				else if(x % 2 == 1 && minY > Integer.parseInt(b[x])){
					minY = Integer.parseInt(b[x]);
				}
				
			}
			for(int x = 0; x < seg.length; x++){
				poly1 [i][x] = seg[x];
			}
		}

		int size2 = Integer.parseInt(sc.nextLine());
				
		int poly2 [][] = new int [size2][4];
		
		int minX2 = 20; 
		int minY2= 20;
		int maxX2 = 0;
		int maxY2 = 0;
		
		for(int i = 0; i < size2; i++){
			String a = sc.nextLine();
			String b [] = a.split(" ");
			int seg [] = new int [4];
			for(int x = 0; x < b.length; x++){
				seg[x] = Integer.parseInt(b[x]);
				if(x % 2 == 0 && maxX2 < Integer.parseInt(b[x])){
					maxX2 = Integer.parseInt(b[x]);
				}
				else if(x % 2 == 1 && maxY2 < Integer.parseInt(b[x])){
					maxY2 = Integer.parseInt(b[x]);
				}
				
				if(x % 2 == 0 && minX2 > Integer.parseInt(b[x])){
					minX2 = Integer.parseInt(b[x]);
				}
				else if(x % 2 == 1 && minY2 > Integer.parseInt(b[x])){
					minY2 = Integer.parseInt(b[x]);
				}
			}
			
			for(int x = 0; x < seg.length; x++){
				poly2 [i][x] = seg[x];
			}
		}
		ArrayList<int[]> pts = checkAllPoints (minX, minY, maxX, maxY, poly1);
		
		for(int i = 0; i < pts.size(); i++){
			System.out.println(Arrays.toString(pts.get(i)));
		}
		
		System.out.println("-----------------------------");

		
		
		
	}


}
