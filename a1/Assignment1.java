import java.io.*;
import java.util.*;

public class Assignment1 {

	private static int[][] polygon1;
	private static int[][] polygon2;
	private static int[][] matrix;
	private static int maxX, maxY, minX, minY;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		getIntersections();
		ArrayList<int[]> firstPoints = getPolygonPoints(polygon1, minX, maxX, minY, maxY);
		System.out.println("The first polygon's points");
		for(int[] p : firstPoints) {
			System.out.print(p[0] + " ");
			System.out.print(p[1]);
			System.out.println("");
		}
	
		ArrayList<int[]> secondPoints = getPolygonPoints(polygon2, 0, 20, 0, 20);
		System.out.println("Second Polygon's Points");
		for(int[] p : secondPoints) {
			System.out.print(p[0] + " ");
			System.out.print(p[1]);
			System.out.println("");
		}

		ArrayList<int[]> commonPoints = checkSecondPolygon(polygon2, firstPoints);
		System.out.println("The Common points");
		for(int[] p : commonPoints) {
			System.out.print(p[0] + " ");
			System.out.print(p[1]);
			System.out.println("");
		}

		int[] array = new int[5];
		for(int i = 0; i < 5; i++) {
			array[i] = 5 - i;
		}
		System.out.println("The sorted arrray");
		int[] sorted = selectionSort(array);
		for(int i : sorted) {
			System.out.print(i + " ");
		}

		System.out.println("\nThe determinant");
		System.out.println(determinant());
	}

	public static int[] selectionSort(int[] array) {
		int first;
		int temp;
		for(int i = 0; i < array.length - 1; i ++) {
			first = i;
			for(int j = i; j < array.length; j ++) {
				if(array[j] < array[first]) {
					first = j;
				}
			}
			temp = array[first];
			array[first] = array[i];
			array[i] = temp;
		}

		return array;
	}

	public static int determinant() throws FileNotFoundException, IOException {
		FileReader fr = new FileReader("test2.txt");
		BufferedReader br = new BufferedReader(fr);
		int n = Integer.parseInt(br.readLine());
		matrix = new int[n][n];
		for(int i = 0; i < n; i++) {
			String[] curr = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(curr[j]);
			}
		}

		return determinantHelper(n, matrix);
	}

	public static int determinantHelper(int n, int[][] matrix) {
		int sum = 0;
		if(n == 1) {
			sum = matrix[0][0];
		}
		else if(n == 2) {
			sum = (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
		}
		else {
			int[][] next = new int[n-1][n-1];
			for(int i = 0; i < n; i ++){
				
				int row = 0;
				int col = 0;

				for(int j = 1; j < n; j++) {
					col = 0;
					for(int k = 0; k < n; k++) {
						if(k != i) {
							next[row][col] = matrix[j][k];
							col ++;
						}
					}
					row ++;
				}
				sum = sum + (int)(matrix[0][i] * Math.pow((-1), i) * determinantHelper(n-1, next));
			}
		}

		return sum;
	}

	public static void getIntersections() throws FileNotFoundException, IOException {
		FileReader fr = new FileReader("test3.txt");
		BufferedReader br = new BufferedReader(fr);

		maxX = 0;
		maxY = 0;
		minX = 20;
		minY = 20;

		int edges = Integer.parseInt(br.readLine());
		polygon1 = new int[edges][4];
		for(int i = 0; i < edges; i++) {
			String[] curr = br.readLine().split(" ");
			for(int j = 0; j < 4; j++) {
				polygon1[i][j] = Integer.parseInt(curr[j]);
				if(j == 0 || j == 2) {
					if(polygon1[i][j] > maxX)
						maxX = polygon1[i][j];
					if(polygon1[i][j] < minX)
						minX = polygon1[i][j];
				}
				if(j == 1 || j == 3) {
					if(polygon1[i][j] > maxY)
						maxY = polygon1[i][j];
					if(polygon1[i][j] < minY)
						minY = polygon1[i][j];
				}
			}
		}

		edges = Integer.parseInt(br.readLine());
		polygon2 = new int[edges][4];
		for(int i = 0; i < edges; i++) {
			String[] curr = br.readLine().split(" ");
			for(int j = 0; j < 4; j++) {
				polygon2[i][j] = Integer.parseInt(curr[j]);
			}
		}
	}

	public static ArrayList<int[]> getPolygonPoints(int[][] polygon, int minX, int maxX, int minY, int maxY) {
		ArrayList<int[]> points = new ArrayList<int[]>();
		for(int i = minX + 1; i < maxX; i++) {
			for(int j = minY + 1; j < maxY; j++) {
				int count = 0;
				int[] temp = new int[2];
				for(int[] segment : polygon) {
					//the point to test
					temp[0] = i;
					temp[1] = j;
					if(!checkVertices(polygon, temp)) {
						count += intersect(temp, segment);
					}
				}
				if(count%2 == 1)
					points.add(temp);
			}
		}
		return points;
	}

	public static ArrayList<int[]> checkSecondPolygon(int[][] polygon, ArrayList<int[]> firstPoints) {
		ArrayList<int[]> points = new ArrayList<int[]>();
		for(int[] point : firstPoints) {
			int temp = 0;
			int count = 0;
			for(int[] segment : polygon) {
				if(!checkVertices(polygon, point)) {
					count += (intersect(point, segment));
				}
			}
			if(count%2 == 1)
				points.add(point);
		}
		return points;
	}

	public static int intersect(int[] point, int[] line) {
		if(line[0] - line[2] == 0) {
			return 0;
		}
		else if(point[0] >= line[0] && point[0] < line[2]) {
			double rise = line[3] - line[1];
			double run = line[2] - line[0];
			double intercept = line[1] - (line[0] * rise / run);
			if((point[0] * rise / run + intercept) == point[1]) {
				return -1;
			}
			if((point[0] * rise / run + intercept) < point[1]) {
				return 1;
			}
		}
		return 0;
	}

	public static boolean checkVertices(int[][] polygon, int[] point) {
		for(int[] vertex : polygon) {
			if(point[0] == vertex[0] && point[1] == vertex[1])
				return true;
			if(point[0] == vertex[2] && point[1] == vertex[3])
				return true;
		}
		return false;
	}
}