import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * @author x
 * Delaunay Main class
 */
public class Main {
	
	static ArrayList<MyVector> vertices;
	static ArrayList<MyTriangle> triangles;


	public static void main(String[] args) throws FileNotFoundException {
		
		long time0;
		long time1;
		
//		vertices = DataIO.importVertices("pointSet");
		vertices = PointGenerator.importVertices(100);
		
//		time0 = System.currentTimeMillis();
//		triangles = DelaunayBruteForce.getDelunay(vertices);
//		time1 = System.currentTimeMillis();
//		System.out.println(time1 - time0 + " ms");
//		MyDraw.main(args);

		time0 = System.currentTimeMillis();
		vertices = MergeSortMyVector.sortVertices(vertices);
		vertices = DelaunayIncremental.updateVertices(vertices);
		triangles = DelaunayIncremental.getDelaunay(vertices);
		time1 = System.currentTimeMillis();
		System.out.println(time1 - time0 + " ms");
		
		MyDraw.main(args);
		

//		MyPrint.printVertices(vertices);
//		DataIO.exportCSV(vertices);
	}

}
