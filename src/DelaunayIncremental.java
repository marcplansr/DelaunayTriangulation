import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author x
 *
 */
public class DelaunayIncremental {

	static double maxX;
	static double minX;
	static double maxY;
	static double minY;
	
	// super triangle size in relation to point area size
	// super triangle length / point area (maxX - minX)
	// super triangle height / point area (maxY - minY)
	static final int LENGTH_RATIO = 300000;
	static final int HEIGHT_RATIO = 400000; 
	
	// in progress only
	static ArrayList<MyVector> vertices;
	static ArrayList<MyTriangle> triangles;
	
	
	public static void main(String[] args) throws FileNotFoundException {
//		vertices = DataIO.importVertices("pointSet");
		vertices = PointGenerator.importVertices(100);
		vertices = MergeSortMyVector.sortVertices(vertices);
		vertices = updateVertices(vertices);
		triangles = getDelaunay(vertices);
		MyDraw.main(args);
	}

	
	/**
	 * @param vertices
	 * @return
	 */
	public static ArrayList<MyTriangle> getDelaunay(ArrayList<MyVector> vertices) {
		
		ArrayList<MyTriangle> triangles = new ArrayList<MyTriangle>();
		ArrayList<MyTriangle> tempTriangles = new ArrayList<MyTriangle>();
		ArrayList<MyVector> tempVertices =  new ArrayList<MyVector>();
		ArrayList<MyVector[]> edges = new ArrayList<MyVector[]>();
		
		for (MyVector vertex : vertices){
			tempVertices.add(vertex);
		}
		triangles.add(superTriangle(vertices.size()));
		for (int i = 0; i < vertices.size(); i++){
			edges.clear();
			tempTriangles.clear();
			for (int j = 0; j < triangles.size(); j++) {
				if (triangles.get(j).isDelaunay(tempVertices.get(i)) > 0) {
					tempTriangles.add(triangles.get(j));
				} else {
					MyVector[] newEdge = new MyVector[2];
					newEdge[0] = triangles.get(j).getVertex1();
					newEdge[1] = triangles.get(j).getVertex2();
					edges.add(newEdge);
					newEdge = new MyVector[2];
					newEdge[0] = triangles.get(j).getVertex2();
					newEdge[1] = triangles.get(j).getVertex3();
					edges.add(newEdge);
					newEdge = new MyVector[2];
					newEdge[0] = triangles.get(j).getVertex3();
					newEdge[1] = triangles.get(j).getVertex1();
					edges.add(newEdge);
					triangles.get(j).unplugTriangle();
				}
			}
			edges = removeDupEdges(edges);
			MyTriangle newTriangle;
			for (int j = 0; j < edges.size(); j++) {
				newTriangle = new MyTriangle(tempVertices.get(i), 
						edges.get(j)[0], edges.get(j)[1]);
				newTriangle.plugTriangle();
				tempTriangles.add(newTriangle);
			}
			triangles.clear();
			for (MyTriangle currentTriangle : tempTriangles) {
				triangles.add(currentTriangle);
			}
		}
		tempTriangles.clear();
		for (int i = 0; i < triangles.size(); i++) {
			MyTriangle currentTriangle = triangles.get(i);
			if (currentTriangle.getVertex1().getVectorId() < vertices.size() &&
					currentTriangle.getVertex2().getVectorId() < vertices.size() &&
					currentTriangle.getVertex3().getVectorId() < vertices.size()) {
				tempTriangles.add(triangles.get(i));
			} else {
				currentTriangle.unplugTriangle();
			}
		}
		triangles.clear();
		for (MyTriangle currentTriangle : tempTriangles) {
			triangles.add(currentTriangle);
		}
		return triangles;
	}
	
	
	/**
	 * calculates super triangle that contains
	 * set of points whose limits are defined by
	 * global vars max min X and Y
	 */
	private static MyTriangle superTriangle(int size) {
		double centerX = minX + (maxX - minX) / 2;
		double centerY = minY + (maxY - minY) / 2;
		MyVector vertex1 = new MyVector(
				centerX - (maxX - minX) * LENGTH_RATIO / 2, 
				centerY - (maxY - minY) * HEIGHT_RATIO / 3);
		MyVector vertex2 = new MyVector(
				centerX + (maxX - minX) * LENGTH_RATIO / 2, 
				centerY - (maxY - minY) * HEIGHT_RATIO / 3);
		MyVector vertex3 = new MyVector(
				centerX, centerY + (maxY - minY) * HEIGHT_RATIO * 2 / 3);
		vertex1.setVectorId(size);
		vertex2.setVectorId(size + 1);
		vertex3.setVectorId(size + 2);
		MyTriangle output = new MyTriangle(vertex1, vertex2, vertex3);
		output.plugTriangle();
		return output;
	}
	
	
	
	
	
	
	/**
	 * @param vertices
	 * @return
	 */
	public static ArrayList<MyVector> updateVertices(ArrayList<MyVector> vertices) {
		minX = vertices.get(0).getCoordX();
		maxX = vertices.get(vertices.size() - 1).getCoordX();
		for (int i = 0; i < vertices.size(); i++) {
			vertices.get(i).setVectorId(i);
			double coordY = vertices.get(i).getCoordY();
			if (i == 0) {
				minY = coordY;
				maxY = coordY;
			} else {
				minY = (coordY < minY) ? coordY : minY;
				maxY = (coordY > maxY) ? coordY : maxY;
			}
		}
		return vertices;
	}

	
	
	/**
	 * @param edgesToFilter
	 * @return
	 */
	private static ArrayList<MyVector[]> removeDupEdges(
			ArrayList<MyVector[]> edgesToFilter) {
		ArrayList<MyVector[]> outputEdges = new ArrayList<MyVector[]>();
		boolean add;
		for (int i = 0; i < edgesToFilter.size(); ++i) {
			add = true;
			inloop:
			for (int j = 0; j < edgesToFilter.size(); ++j) {
				if ((i != j) && sameEdge(edgesToFilter.get(i), 
						edgesToFilter.get(j))) {
					add = false;
					break inloop;
				}
			}
			if (add) {
				outputEdges.add(edgesToFilter.get(i));
			}
		}
		return outputEdges;
	}
	
	
	/**
	 * @param firstEdge
	 * @param secondEdge
	 * @return
	 */
	private static boolean sameEdge (MyVector[] firstEdge, 
			MyVector[] secondEdge) {
		return (firstEdge[0].getVectorId() ==
				secondEdge[0].getVectorId() && 
				firstEdge[1].getVectorId() ==
				secondEdge[1].getVectorId()) ||
				(firstEdge[0].getVectorId() ==
				secondEdge[1].getVectorId() && 
				firstEdge[1].getVectorId() ==
				secondEdge[0].getVectorId());
	}
	
	
	
}
