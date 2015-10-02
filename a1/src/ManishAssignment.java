
import java.io.*;
import java.util.*;

public class ManishAssignment {

	public static boolean checkPoint(int x, int y, int x1, int y1, int x2, int y2) {
		double slopeY = (y2-y1);
		double slopeX = (x2-x1);
		double intersectX = 0;
		double intersectY = 0;
				
		if(slopeX != 0 && slopeY != 0){
			double intercept = y1 - ((slopeY/slopeX) * x1);
			intersectX = x;
			intersectY = (slopeY/slopeX * x) + intercept;
			if ((intersectX > x1 && intersectX <= x2) && 
					(intersectY >= Math.min(y1, y2) && intersectY <= Math.max(y1, y2)) 
					&& (y > intersectY)){
				return true;
			}
		}
		else if (slopeY == 0){
			intersectX = x;
			intersectY = y;
			if(intersectX > x1 && intersectX < x2 && (y >= y1 || y >= y2)){
				return true;
			}
		}
		else{
			return false;
		}
		return false;
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
		
		
		for(int i = 0; i < size2; i++){
			String a = sc.nextLine();
			String b [] = a.split(" ");
			int seg [] = new int [4];
			for(int x = 0; x < b.length; x++){
				seg[x] = Integer.parseInt(b[x]);
			}
			
			for(int x = 0; x < seg.length; x++){
				poly2 [i][x] = seg[x];
			}
		}
			
		ArrayList<int[]> pts = checkAllPoints (minX, minY, maxX, maxY, poly1);		
		ArrayList<int[]> finalPts = new ArrayList<int[]>();
		
		for(int i = 0; i < pts.size(); i++){
			if (checkAllSegments(pts.get(i)[0], pts.get(i)[1], poly2) % 2 == 1){
				finalPts.add(pts.get(i));
			}
		}
			
		for(int x = 0; x < finalPts.size(); x++){
			System.out.println(Arrays.toString(finalPts.get(x)));
		}

	}

}

