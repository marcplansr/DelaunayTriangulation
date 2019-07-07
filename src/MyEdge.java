import java.util.ArrayList;

public class MyEdge {
	
	private MyVector origin;
	private MyVector neighborPoint;
	private ArrayList<MyTriangle > neighborTriangles;
	
	
	/**
	 * Two vertices and a neighbor triangles array constructor
	 */
	public MyEdge(MyVector origin, MyVector neighborPoint,
			ArrayList<MyTriangle> neighborTriangles) {
		this.origin = origin;
		this.neighborPoint = neighborPoint;
		this.neighborTriangles = neighborTriangles;
	}
	
	
	/**
	 * Two vertices constructor
	 */
	public MyEdge(MyVector origin, MyVector neighborPoint) {
		this.origin = origin;
		this.neighborPoint = neighborPoint;
		this.neighborTriangles = new ArrayList<MyTriangle>();
		
	}
	
	
	public final MyVector getNeighborPoint() {
		return neighborPoint;
	}

	
	public final void setNeighborPoint(MyVector neighborPoint) {
		this.neighborPoint = neighborPoint;
	}

	
	public final ArrayList<MyTriangle> getNeighborTriangles() {
		return neighborTriangles;
	}

	
	public final void setNeighborTriangles(
			ArrayList<MyTriangle> neighborTriangles) {
		this.neighborTriangles = neighborTriangles;
	}
	
	
	/**
	 * @param newTriangle
	 */
	public void addNeighborTriangle(MyTriangle newTriangle) {
		if (neighborTriangles.size() <= 1) {
			neighborTriangles.add(newTriangle);
		} else {
			System.out.println("ERROR! neighborTriangles "
					+ "too large! No room for a new Triangle!");
			System.out.println("EDGE: \n" + this);
			System.out.println("TRIANGLE: \n" + newTriangle.toString() + "\n");
			System.out.println("MyEdge.java, addNeighborTriangle");
		}
	}
	
	
	/**
	 * @param triangleToRemove
	 */
	public void removeNeighborTriangle(MyTriangle triangleToRemove) {
		if (neighborTriangles.size() > 2) {
			System.out.println("ERROR! neighborTriangles too large!");
			System.out.println("MyEdgeDC.java, removeNeighborTriangle");
		} else {
			if (neighborTriangles.get(0) == triangleToRemove) {
				neighborTriangles.remove(0);
			} else if (neighborTriangles.get(1) == triangleToRemove) {
				neighborTriangles.remove(1);
			} else {
				System.out.println("triangleToremove not found!");
			}
		}
	}
	

	public  String toString() {
		String output = "" + origin.getVectorId() + 
				" - " + neighborPoint.getVectorId() + ": \n";
		output += origin + "\n" + neighborPoint;
		return output;	
	}
}
