import java.util.ArrayList;

/**
 * @author x
 *
 */
public class MyTriangle {
	
	private MyVector[] verticesArray;
	boolean triangle;
	

	/**
	 * Two vertices triangle constructor
	 */
	public MyTriangle(MyVector vertex1, MyVector vertex2) {
		verticesArray = new MyVector[3];
		if (vertex1.getVectorId() > vertex2.getVectorId()) {
			verticesArray[0] = vertex2;
			verticesArray[1] = vertex1;
		} else {
			verticesArray[0] = vertex1;
			verticesArray[1] = vertex2;
		}
		triangle = false;
	}
	
	
	/**
	 * Three vertices triangle constructor
	 */
	public MyTriangle(MyVector vertex1, MyVector vertex2, MyVector vertex3) {
		verticesArray = new MyVector[3];
		verticesArray[0] = vertex1;
		verticesArray[1] = vertex2;
		verticesArray[2] = vertex3;
		if (vertex1.getVectorId() != -1 && vertex2.getVectorId() != -1 && 
				vertex3.getVectorId() != -1) {
			for (int i = 1; i < verticesArray.length; i++) {
				innerloop:
					for (int j = i; j > 0; j--) {
						if (verticesArray[j].getVectorId() < 
								verticesArray[j - 1].getVectorId()) {
							MyVector tempVector = verticesArray[j];
							verticesArray[j] = verticesArray[j - 1];
							verticesArray[j - 1] = tempVector;		
						} else {
							break innerloop;
						}
					}
			}
		}
		triangle = true;
	}
	 
	
	/**
	 * Getters and Setters
	 */
	public final MyVector getVertex1() {
		return verticesArray[0];
	}
	
	
	public final void setVertex1(MyVector vertex1) {
		verticesArray[0] = vertex1;
	}

	
	public final MyVector getVertex2() {
		return verticesArray[1];
	}

	
	public final void setVertex2(MyVector vertex2) {
		verticesArray[1] = vertex2;
	}

	
	public final MyVector getVertex3() {
		return verticesArray[2];
	}

	
	public final void setVertex3(MyVector vertex3) {
		verticesArray[2] = vertex3;
	}

	
	public final MyVector[] getVerticesArray() {
		return verticesArray;
	}

	
	public final void setVerticesArray(MyVector[] verticesArray) {
		this.verticesArray = verticesArray;
	}

	
	public final boolean isTriangle() {
		return triangle;
	}

	
	public final void setTriangle(boolean triangle) {
		this.triangle = triangle;
	}
	
	
	public MyTriangle cloneTriangle() {
		return new MyTriangle (this.getVertex1(),
				this.getVertex2(), this.getVertex3());
	}
	
	
	/**
	 * Arranges vertices in triangle according so that vertices 
	 * lay in increasing order; first vertex is the one with 
	 * lower id, and third vertex the one with a higher id
	 */
	public void arrangeVertices() {
		if (getVertex1().getVectorId() != -1 && getVertex2().getVectorId() != -1 && 
				getVertex3().getVectorId() != -1) {
			for (int i = 1; i < verticesArray.length; i++) {
				innerloop:
					for (int j = i; j > 0; j--) {
						if (verticesArray[j].getVectorId() < 
								verticesArray[j - 1].getVectorId()) {
							MyVector tempVector = verticesArray[j];
							verticesArray[j] = verticesArray[j - 1];
							verticesArray[j - 1] = tempVector;		
						} else {
							break innerloop;
						}
					}
			}
		}
	}
	
	
	/**
	 * Checks if this triangle is higher than given other triangle.
	 * This is done by comparing their vertices id, so that the 
	 * triangle with the higher first vertex id will also the
	 * higher triangle. Comparison starts at first vertex and ends
	 * at the last one, if necessary
	 * @param otherTriangle
	 * @return
	 */
	public boolean isHigher(MyTriangle otherTriangle) {
		if (this.getVertex1().getVectorId() != otherTriangle.
				getVertex1().getVectorId()) {
			return (this.getVertex1().getVectorId() > otherTriangle.
					getVertex1().getVectorId());
		} else if (this.getVertex2().getVectorId() != otherTriangle.
				getVertex2().getVectorId()) {
			return (this.getVertex2().getVectorId() > otherTriangle.
					getVertex2().getVectorId());
		} else {
			return (this.getVertex3().getVectorId() > otherTriangle.
					getVertex3().getVectorId());
		}
	}
	
	
	/**
	 * Updates vertices neighbors array by adding current triangle nfo
	 */
	public void plugTriangle() {
		int k = 1;
		for (int i = 0; i < 3; i++) {
			k--;
			for (int j = 0; j < 2; j++) {
				k++;
				k = (k > 2) ? 0 : k;
				int neighborsArrayIndex = getVerticesArray()[i].getEdgeIndex(
						getVerticesArray()[k]);
				if (neighborsArrayIndex == -1) {
					MyEdge newEdge = new MyEdge (
							getVerticesArray()[i],
							getVerticesArray()[k], 
							new ArrayList<MyTriangle>());
					
					newEdge.addNeighborTriangle(this);
					getVerticesArray()[i].addNeighbor(newEdge);
				} else {
					getVerticesArray()[i].addTriangleInEdge(
							neighborsArrayIndex, this);
				}
			}
		}
	}
	
	
	/**
	 * 
	 */
	public void unplugTriangle() {
		int k = 1;
		for (int i = 0; i < 3; i++) {
			k--;
			for (int j = 0; j < 2; j++) {
				k++;
				k = (k > 2) ? 0 : k;
				int neighborsArrayIndex = 
						getVerticesArray()[i].getEdgeIndex(
								getVerticesArray()[k]);
				MyEdge currentEdge = 
						getVerticesArray()[i].getEdge(neighborsArrayIndex);
				ArrayList<MyTriangle> neighborsArray = 
						currentEdge.getNeighborTriangles();
				
				if ((neighborsArray.size() == 1) &&
						(neighborsArray.get(0) == this)) {
					getVerticesArray()[i].removeNeighborbyIndex(
							neighborsArrayIndex);
				} else if ((neighborsArray.size() == 2) &&
						(neighborsArray.get(0) == this)) {
					neighborsArray.remove(0);
				} else if ((neighborsArray.size() == 2) &&
						(neighborsArray.get(1) == this)) {
					neighborsArray.remove(1);
				}
			}
		}
	}

	
	/**
	 * Checks if point is one of the vertices of the 
	 * triangle by comparing if they have same coordinates
	 * @param 	point
	 * @return 	true if point is a vertex, false otherwise
	 */
	public boolean contains (MyVector point) {
		return ((getVertex1().equals(point)) ||
				(getVertex2().equals(point)) ||
				(getVertex3().equals(point)));
	}
	
	
	/**
	 * Checks if triangle vertices lay clockwise
	 * @return 	true if the lay clockwise, false otherwise
	 */
	public boolean isClockwise() {
		double angle = MyAngles.twoVectAngle2D(
				getVertex1().getCoordX(), getVertex1().getCoordY(), 
				getVertex2().getCoordX(), getVertex2().getCoordY(),
				getVertex3().getCoordX(), getVertex3().getCoordY());
//		System.out.println("*" + angle);
		return (angle < 0);
	}
		
	
	/**
	 * Checks if triangle is Delaunay by finding out
	 * if given point is inside its circumscribed circle
	 * @param point
	 * @return false if point lays outside, true otherwise
	 */
	public int isDelaunay (MyVector point) {
		double[][] myArray = new double[4][4];
		
		for (int i = 0; i < 3; i++) {
			myArray[i][0] = verticesArray[i].getCoordX();
			myArray[i][1] = verticesArray[i].getCoordY();
			myArray[i][2] = Math.pow(verticesArray[i].getCoordX(), 2) +
					Math.pow(verticesArray[i].getCoordY(), 2);
			myArray[i][3] = 1;
		}
		myArray[3][0] = point.getCoordX();
		myArray[3][1] = point.getCoordY();
		myArray[3][2] = Math.pow(point.getCoordX(), 2) +
				Math.pow(point.getCoordY(), 2);
		myArray[3][3] = 1;
		if (((isClockwise()) && (Helpers.determinant(myArray) > 0)) || 
				((!isClockwise()) && (Helpers.determinant(myArray) < 0))) {
			return 1;
		} else if (Helpers.determinant(myArray) == 0) {
			return 2;
		} else {
			return 0;
		}
	}
	
	
	/**
	 * @param otherTriangle
	 * @return
	 */
	public boolean sameVertices(MyTriangle otherTriangle) {
		return ((this.getVertex1().sameCoords(otherTriangle.getVertex1()) ||
						this.getVertex1().sameCoords(otherTriangle.getVertex2()) ||
						this.getVertex1().sameCoords(otherTriangle.getVertex3())) &&
				(this.getVertex2().sameCoords(otherTriangle.getVertex1()) ||
						this.getVertex2().sameCoords(otherTriangle.getVertex2()) ||
						this.getVertex2().sameCoords(otherTriangle.getVertex3())) &&
				(this.getVertex3().sameCoords(otherTriangle.getVertex1()) ||
						this.getVertex3().sameCoords(otherTriangle.getVertex2()) ||
						this.getVertex3().sameCoords(otherTriangle.getVertex3())));
	}
	
	
	public String toString() {
		return "vertex1: " + getVertex1().toString() + "\n" + 
				"vertex2: " + getVertex2().toString() + "\n" +
				"vertex3: " + getVertex3().toString();
	}
	
	

}
