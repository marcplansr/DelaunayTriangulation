import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Test01 {
	
	static ArrayList<MyVector> vertices;
	static ArrayList<MyTriangle> triangleSolution;
	static ArrayList<MyTriangle> triangleIncremental;
	static ArrayList<MyTriangle> errors;
	
//	static final int X_OFFSET = -75;
//	static final int Y_OFFSET = -75;
//	static final int X_OFFSET = -15;
//	static final int Y_OFFSET = -35;
		
	public static void main(String[] args) throws FileNotFoundException {
		vertices = new ArrayList<MyVector>();
		triangleSolution =  new ArrayList<MyTriangle>();
		errors =  new ArrayList<MyTriangle>();
		extractData("rnd_1000");
		
//		vertices = scaleVertices(vertices, -10, 30, 370, 600); //rnd_10
		vertices = scaleVertices(vertices, 75, 300, 700); //rnd_100...
		
		vertices = MergeSortMyVector.sortVertices(vertices);
		vertices = DelaunayIncremental.updateVertices(vertices);
		
		
		triangleIncremental = DelaunayIncremental.getDelaunay(vertices);
		
		triangleSolution = MergeSortMyTriangle.sortTriangles(triangleSolution);
		triangleIncremental = MergeSortMyTriangle.sortTriangles(triangleIncremental);
		
		if (sameTriangles(triangleSolution, triangleIncremental)) {
			System.out.println("***");
			System.out.println("Test ok!!!");
			System.out.println("***");
		} else {
			System.out.println("***");
			System.out.println("Test failed!!!");
			System.out.println("***");
		}
		
		MyDraw.main(args);
	}
	
	
	
	
	
	
	/**
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public static void extractData(String fileName) 
			throws FileNotFoundException{
		fileName = "data/test01/" + fileName + ".csv";
		File file = new File(fileName);
		Scanner input = new Scanner(file);
		while (input.hasNextLine()) {			
			String newLine = input.nextLine();
			String[] lineArray = newLine.split(",");
			MyVector[] currentTriangle = new MyVector[3];
			for (int i = 0; i < 3; i++) {
				currentTriangle[i] = new MyVector(
						Double.parseDouble(lineArray[i + i]), 
						Double.parseDouble(lineArray[i + i + 1]));
				boolean addToVertices = true;
				if (vertices.size() > 2) {
					innerloop:
						for (int j = 0; j < vertices.size(); j++) {
							if (currentTriangle[i].sameCoords(vertices.get(j))) {
								currentTriangle[i] = (vertices.get(j));
								addToVertices = false;
								break innerloop;
							}
						}
				}
				if (addToVertices ) {
					vertices.add(currentTriangle[i]);
				}
			}
			triangleSolution.add(new MyTriangle(currentTriangle[0],
					currentTriangle[1], currentTriangle[2]));
		}
		input.close();
	}
	
	
	/**
	 * @param vertices
	 * @param offset
	 * @param upperBound
	 * @param newUpperBound
	 * @return
	 */
	public static ArrayList<MyVector> scaleVertices(ArrayList<MyVector> vertices,
			int offset, int upperBound, int newUpperBound) {
		for (int i = 0; i < vertices.size(); i++) {
			vertices.get(i).setCoordX((vertices.get(i).getCoordX() - offset) 
					* newUpperBound / upperBound);
			vertices.get(i).setCoordY((vertices.get(i).getCoordY() - offset) 
					* newUpperBound / upperBound);
		}
		return vertices;
	}
	
	
	/**
	 * @param vertices
	 * @param offsetX
	 * @param offsetY
	 * @param upperBound
	 * @param newUpperBound
	 * @return
	 */
	public static ArrayList<MyVector> scaleVertices(ArrayList<MyVector> vertices,
			int offsetX, int offsetY, int upperBound, int newUpperBound) {
		for (int i = 0; i < vertices.size(); i++) {
			vertices.get(i).setCoordX((vertices.get(i).getCoordX() - offsetX) 
					* newUpperBound / upperBound);
			vertices.get(i).setCoordY((vertices.get(i).getCoordY() - offsetY) 
					* newUpperBound / upperBound);
		}
		return vertices;
	}
	
	
	public static boolean sameTriangles(ArrayList<MyTriangle> firstArray,
			ArrayList<MyTriangle> secondArray) {
		boolean output = true;
		if (firstArray.size() != secondArray.size()) {
			System.out.println("Error, arrays do not have same size!");
			System.out.println("First array size: " + firstArray.size());
			System.out.println("Second array size: " + secondArray.size());
			return false;
		}
		for (int i = 0; i < firstArray.size(); i++) {
			if (firstArray.get(i).sameVertices(secondArray.get(i))) {
				System.out.println(i + ": ok!");
			} else {
				errors.add(secondArray.get(i));
				System.out.println(i + ": error!");
				output = false;
			}
		}
		return output;
	}
	
	
	/**
	 * @param vertexToCheck
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean notInVertices(MyVector vertexToCheck) {
		for (int i = 0; i < vertices.size(); i++) {
			if (vertexToCheck.sameCoords(vertices.get(i))) {
				return false;
			}
		}
		return true;
	}


}
