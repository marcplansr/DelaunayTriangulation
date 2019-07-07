import java.util.ArrayList;

public class MyVector {

	private double coordX;
	private double coordY;
	private double coordZ;
	private int vectorId;
	private ArrayList<MyEdge> neighbors;
	
	/**
	 * Constructors
	 */
	public MyVector(double coordX, double coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.coordZ = 0;
		vectorId = -1;
		neighbors = new ArrayList<MyEdge>();
		
	}
	
	public MyVector(double coordX, double coordY, double coordZ) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.coordZ = coordZ;
		vectorId = -1;
		neighbors = new ArrayList<MyEdge>();
	}
		
	/**
	 * Getters and Setters
	 */
	public final double getCoordX() {
		return coordX;
	}
	
	public final void setCoordX(double coordX) {
		this.coordX = coordX;
	}
	
	public final double getCoordY() {
		return coordY;
	}
	
	public final void setCoordY(double coordY) {
		this.coordY = coordY;
	}
	
	public final double getCoordZ() {
		return coordZ;
	}
	
	public final void setCoordZ(double coordZ) {
		this.coordZ = coordZ;
	}
		
	public final int getVectorId() {
		return vectorId;
	}

	public final void setVectorId(int vectorId) {
		this.vectorId = vectorId;
	}
	
	
	/**
	 * Checks if given edge is already in neighbors arrayList. If so, returns
	 * ArrayList index to access given edge. If no edge is found or ArrayList is
	 * empty, returns -1
	 * 
	 * @param edgeIndex
	 * @return neighbors ArrayList index to access edge
	 */
	public int getEdgeIndex(MyVector neighborPoint) {
		for (int i = 0; i < neighbors.size(); i++) {
			if (neighbors.get(i).getNeighborPoint() == neighborPoint) {
				return i;
			}
		}
		return -1;
	}
	
	
	/**
	 * @param edgeIndexInArray
	 * @return
	 */
	public final MyEdge getEdge(int edgeIndexInArray) {
		return neighbors.get(edgeIndexInArray);
	}
	
	
	/**
	 * @param newNeighbor
	 */
	public void addNeighbor(MyEdge newNeighbor) {
		neighbors.add(newNeighbor);
	}
	
	
	/**
	 * @param indexOfNeighborToRemove
	 */
	public void removeNeighborbyIndex(int indexOfNeighborToRemove) {
		neighbors.remove(indexOfNeighborToRemove);
	}
	
		
	/**
	 * @param indexInNeighborArray
	 * @param newTriangle
	 */
	public void addTriangleInEdge(int indexInNeighborArray,
			MyTriangle newTriangle) {
		neighbors.get(indexInNeighborArray).addNeighborTriangle(newTriangle);
	}
	
	
	/**
	 * @param otherVector
	 * @return
	 */
	public boolean sameCoords(MyVector otherVector) {
		return ((getCoordX() == otherVector.getCoordX())
				&& (getCoordY() == otherVector.getCoordY())
				&& (getCoordZ() == otherVector.getCoordZ()));
	}
	
	
	
	
	
	
	

	/**
	 * formats given string so that numbers are aligned
	 * @param stringToFormat
	 * @return
	 */
	private String formatStr(double doubleToFormat) {
		String output = "" + String.format("%.2f", doubleToFormat);
		if (output.length() == 3) {
			output = "    " + output;
		} else if (output.length() == 4) {
			output = "   " + output;
		} else if (output.length() == 5) {
			output = "  " + output;
		} else if (output.length() == 6) {
			output = " " + output;
		}
		return output;
	}
	
	
	/**
	 * formats given string so that toString method
	 * prints numbers aligned
	 * @param stringToFormat
	 * @return
	 */
	private String formatStr(String stringToFormat) {
		String output = "" + stringToFormat;
		if (output.length() == 1) {
			output = "   " + output;
		} else if (output.length() == 2) {
			output = "  " + output;
		} else if (output.length() == 3) {
			output = " " + output;
		}
		return output;
	}
	
	
	
	
	public String toString() {
		return "(" + formatStr(getCoordX()) + ", " + 
				formatStr(getCoordY()) + ", " + formatStr(getCoordZ()) + 
				", " + formatStr("" + getVectorId()) + ")";
	}

}
