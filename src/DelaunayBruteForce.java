import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author x
 *
 */
public class DelaunayBruteForce {
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

	}

	
	/**
	 * Brute force algorithm to compute all triangles
	 * that satisfy Delaunay triangulation conditions
	 * @param pointSet array of points (MyVector)
	 * @return Delaunay triangles set
	 */
	public static ArrayList<MyTriangle> getDelunay(
			ArrayList<MyVector> pointSet) {
		
		/* initialize data sets */
		ArrayList<MyTriangle> triangleSet = new ArrayList<MyTriangle>();
		ArrayList<MyTriangle> delaunaySet = new ArrayList<MyTriangle>();
		ArrayList<MyVector[]> overlaps = new ArrayList<MyVector[]>();

		/* computes all possible triangles */
		triangleSet = getTriangles(pointSet); 

		/* for each possible triangle checks if satisfies Delaunay condition */
		for (int i = 0; i < triangleSet.size(); i++) {
			MyTriangle currentTriangle = triangleSet.get(i).cloneTriangle();
			boolean addTriangle = true;
			int j = 0;
			while ((j < pointSet.size()) && (addTriangle)) {
				addTriangle = (currentTriangle.contains(pointSet.get(j)) || 
						currentTriangle.isDelaunay(pointSet.get(j)) == 1);
				if (!currentTriangle.contains(pointSet.get(j)) && 
						currentTriangle.isDelaunay(pointSet.get(j)) == 2) {
					MyVector[] newOverlap = {currentTriangle.getVertex1(), 
							currentTriangle.getVertex2(), currentTriangle.getVertex3(),
							pointSet.get(j)};
					overlaps.add(newOverlap);
				}
				j++;
			}
			if (addTriangle) {
				delaunaySet.add(currentTriangle);
			}
		}
		if (overlaps.size() > 0) {
			ArrayList<MyVector[]> holes = removeDuplicateOverlaps(overlaps);
			for (int i = 0; i < holes.size(); i++) {
				ArrayList<MyTriangle> newTriangles = getOverlapTriangles(holes.get(i));
				delaunaySet.addAll(newTriangles);
			}
		}
		return delaunaySet;		
	}
	
	
	/**
	 * @param hole
	 * @return
	 */
	public static ArrayList<MyTriangle> getOverlapTriangles(MyVector[] hole) {
		ArrayList<MyTriangle> holeTriangles = new ArrayList<MyTriangle>();
		double[] angles = new double[3];
		
		for (int i = 1; i < hole.length; i++) {
			int j = (i < 3) ? i + 1 : 1;
			angles[i - 1] = Math.abs(MyAngles.twoVectAngle2D(
					hole[0].getCoordX(), hole[0].getCoordY(),
					hole[i].getCoordX(), hole[i].getCoordY(),
					hole[j].getCoordX(), hole[j].getCoordY()));
		}
		if (angles[0] > angles[1] && angles[0] > angles[2]) {
			holeTriangles.add(new MyTriangle(hole[0], hole[2], hole[3]));
			holeTriangles.add(new MyTriangle(hole[0], hole[3], hole[1]));
		} else if (angles[1] > angles[0] && angles[1] > angles[2]) {
			holeTriangles.add(new MyTriangle(hole[0], hole[1], hole[2]));
			holeTriangles.add(new MyTriangle(hole[0], hole[3], hole[1]));
		} else {
			holeTriangles.add(new MyTriangle(hole[0], hole[1], hole[2]));
			holeTriangles.add(new MyTriangle(hole[0], hole[2], hole[3]));
		}
		return holeTriangles;
	}
		
	
	/**
	 * @param overlaps
	 * @return
	 */
	public static ArrayList<MyVector[]> removeDuplicateOverlaps(ArrayList<MyVector[]> overlaps) {
		ArrayList<MyVector[]> output = new ArrayList<MyVector[]>();
		
		output.add(overlaps.get(0));
		if (overlaps.size() == 4) {
			return output;
		}
				
		for (int i = 1; i < overlaps.size(); i++) {
			boolean add = true;
			innerloop:
				for (int j = 0; j < output.size(); j++) {
					if (medianCoordsX(overlaps.get(i)) == medianCoordsX(output.get(j)) && 
							medianCoordsY(overlaps.get(i)) == medianCoordsY(output.get(j))) {
						add = false;
						break innerloop;
					}
				}
			if (add) {
				output.add(overlaps.get(i));
			}
		}
		return output;	
	}
	
	
	/**
	 * @param points
	 * @return
	 */
	public static double medianCoordsX(MyVector[] points) {
		double numerator = 0;
		for (int i = 0; i < points.length; i++) {
			numerator += points[i].getCoordX();
		}	
		return numerator / points.length;
	}
	
	
	/**
	 * @param points
	 * @return
	 */
	public static double medianCoordsY(MyVector[] points) {
		double numerator = 0;
		for (int i = 0; i < points.length; i++) {
			numerator += points[i].getCoordY();
		}	
		return numerator / points.length;
	}
		
	
	/**
	 * Computes all possible triangle combinations
	 * @param pointSet ArrayList of points (MyVector)
	 * @return ArrayList of triangles (MyTriangle)
	 */
	public static ArrayList<MyTriangle> getTriangles(ArrayList<MyVector> pointSet) {
		
		ArrayList<MyTriangle> triangleSet = new ArrayList<MyTriangle>();
		int points = pointSet.size();

		/* Nested iterations to compute all possible triangle combinations */
		for (int i = 0; i < points - 2; ++i) {
			for (int j = i + 1; j < points - 1; ++j) {
				for (int k = j + 1; k < points; ++k) {
					MyTriangle addTriangle = new MyTriangle(
							pointSet.get(i), 
							pointSet.get(j),
							pointSet.get(k));
					triangleSet.add(addTriangle);
				}
			}
		}
		return triangleSet;
	}
	
	
	
	

}
