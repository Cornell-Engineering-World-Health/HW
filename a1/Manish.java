import java.io.*; 
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.lang.Object;
import java.awt.geom.Point2D;
import java.awt.Point;

class Manish {
  public static void main(String[] args) throws FileNotFoundException, IOException  {

    String filePath = "test3.txt";
    String line = null;

    FileReader fileReader = new FileReader(filePath);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    int numEdges = Integer.parseInt(bufferedReader.readLine());
    int[][] poly1 = new int[numEdges][4];
    int i = 0;
    int numEdges2 = 0;
    int max = 0;
    while ((line = bufferedReader.readLine()) != null){
      if (i == numEdges){
        numEdges2 = Integer.parseInt(line); 
        break;
      }
      else {
        String[] oneline = line.split(" ");
        for (int c = 0 ;  c < 4 ; c ++){
          int d = Integer.parseInt(oneline[c]);
          poly1[i][c] = d;
          if (d > max)
            max = d;
        }
        i++;
      }
    }
    int[][] poly2 = new int[numEdges2][4];
    int l = 0;
    while ((line = bufferedReader.readLine()) != null){
      if (l < numEdges2){
        String[] oneline = line.split(" ");
        for (int c = 0 ;  c < 4 ; c ++){
          int d = Integer.parseInt(oneline[c]);
          poly2[l][c] = d;
          if (d > max)
            max = d;
        }
        l++;
      }
    }
    bufferedReader.close();  
    
    int crossings = 0;
    ArrayList<Point> p1cross = new ArrayList<Point>();
    for (int x = 0; x < max ; x++){
      for (int y = 0; y < max ; y++){
        for (int n = 0 ; n < numEdges ; n ++){
          if(x >= poly1[n][0] && x <= poly1[n][2]){
            int slope = (poly1[n][1] - poly1[n][3])/(poly1[n][0] - poly1[n][2]);
            int y_int = poly1[n][1] - slope*poly1[n][0];
            int slope_int = slope*x + y_int;
            if (y > slope_int)
              crossings ++;      
          }
        }
        if ((crossings % 2) != 0)
          p1cross.add(new Point(x, y));
        crossings = 0;
      }
    }
//for (int p = 0 ; p < p1cross.size() ; p ++)
  //    System.out.println("(" + p1cross.get(p).x + "," + p1cross.get(p).y + ")");
    
    ArrayList<Point> p2cross = new ArrayList<Point>();
    for (int q = 0 ; q < p1cross.size() ; q++){
      for (int n = 0 ; n < numEdges2 ; n++){
        if (poly2[n][0] != poly2[n][2] && p1cross.get(q).x >= poly2[n][0]
           && p1cross.get(q).x <= poly2[n][2]){
          int slope = (poly2[n][1] - poly2[n][3])/(poly2[n][0] - poly2[n][2]);
          int y_int = poly2[n][1] - slope*poly2[n][0];
          int slope_int = slope*p1cross.get(q).x + y_int;
          if (p1cross.get(q).y > slope_int)
              crossings ++;
          System.out.println(poly2[n][0] +" "+poly2[n][1]+" "+poly2[n][2]+" "+poly2[n][3]);
          System.out.println(slope_int+" "+p1cross.get(q).y);
      }
      if ((crossings % 2) != 0)
        p2cross.add(p1cross.get(q));
      crossings = 0;
    }
    for (int p = 0 ; p < p2cross.size() ; p ++)
      System.out.println("(" + p2cross.get(p).x + "," + p2cross.get(p).y + ")");
  }
}
}