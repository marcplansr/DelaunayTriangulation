import java.util.ArrayList;
import java.util.Arrays;

public class Lab {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyVector v0 = new MyVector(1, 2);
		MyVector v1 = new MyVector(2, 1);
		MyVector v2 = new MyVector(2, 3);
		MyTriangle triangle = new MyTriangle(v0, v1, v2);
		triangle.plugTriangle();
		
		
//		MyVector v3 = new MyVector(3, 2);
//		
//		
//		System.out.println(v0.hashCode());
//		System.out.println(v1.hashCode());
				
//		v0.setVectorId(0);
//		v1.setVectorId(1);
//		v2.setVectorId(2);
//		v3.setVectorId(3);
//		
//		MyVector[] points = {v0, v1, v2, v3};
//		
//		
//		ArrayList<MyTriangle> triangles = getOverlapTriangles(points);
//		System.out.println(triangles.size());
//		System.out.println(triangles);


		
		
		
		
		
//		MyVector v1 = new MyVector(1.62, 2.52);
//		MyVector v2 = new MyVector(2.75, 3.03);
//		MyVector v3 = new MyVector(3.32, 2.94);
//		MyVector v4 = new MyVector(2.93, 1.33);
//		MyVector v5 = new MyVector(8.62, 3.32);
//		MyVector v6 = new MyVector(4.65, 8.33);
//		MyVector v7 = new MyVector(9.32, 2.94);
//		MyVector v8 = new MyVector(0.33, 6.63);
		
//		MyVector[] points1 = {v1, v2, v3, v4};
//		MyVector[] points2 = {v5, v6, v7, v8};
//		MyVector[] points3 = {v4, v1, v3, v7};
//		MyVector[] points4 = {v8, v5, v1, v4};

//		ArrayList<MyVector[]> toClear = new ArrayList<MyVector[]>();
		
		
		
		
		
		

//		for (int i = 0; i < 4; i++) {
//			toClear.add(points1);
//			toClear.add(points2);
//			toClear.add(points3);
//			toClear.add(points4);
//		}
//		
//		System.out.println(points1);
//		System.out.println(points2);
//		System.out.println(points3);
//		System.out.println(points4);
//		System.out.println("***");
//		
//		
//		
//		
//		for (int i = 0; i < toClear.size(); i++) {
//			System.out.println(toClear.get(i));
//		}
//		System.out.println("***");
//		
//		Collections.shuffle(toClear);
//		for (int i = 0; i < toClear.size(); i++) {
//			System.out.println(toClear.get(i));
//		}
//		System.out.println("***");
//		
//		ArrayList<MyVector[]> clean = delDuplicateOverlaps(toClear);
//		
//		System.out.println(clean.size());
//		System.out.println();
//		for (int i = 0; i < clean.size(); i++) {
////			System.out.println(Arrays.toString(clean.get(i)));
//			System.out.println(clean.get(i));
//
//		}
		
		
	}
	
	
	@SuppressWarnings("unused")
	private static ArrayList<MyTriangle> getOverlapTriangles(MyVector[] hole) {
		ArrayList<MyTriangle> holeTriangles = new ArrayList<MyTriangle>();
		double[] angles = new double[3];
		
		for (int i = 1; i < hole.length; i++) {
			int j = (i < 3) ? i + 1 : 1;
			angles[i - 1] = Math.abs(MyAngles.twoVectAngle2D(
					hole[0].getCoordX(), hole[0].getCoordY(),
					hole[i].getCoordX(), hole[i].getCoordY(),
					hole[j].getCoordX(), hole[j].getCoordY()));
		}
		
		System.out.println(Arrays.toString(angles));
		
		
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
	
	
	
	public static ArrayList<MyVector[]> delDuplicateOverlaps(ArrayList<MyVector[]> overlaps) {
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
 
	public static double medianCoordsX(MyVector[] points) {
		double numerator = 0;
		for (int i = 0; i < points.length; i++) {
			numerator += points[i].getCoordX();
		}	
		return numerator / points.length;
	}
 
	public static double medianCoordsY(MyVector[] points) {
		double numerator = 0;
		for (int i = 0; i < points.length; i++) {
			numerator += points[i].getCoordX();
		}	
		return numerator / points.length;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//		int[] a0 = {3, 4, 6, 3, 7, 6, 1, 1, 7, 8, 9, 3, 9, 2, 5};
//		
//		ArrayList<Integer> a1 = new ArrayList<Integer>();
//		
//		for (int i = 0; i < a0.length; i++) {
//			a1.add(a0[i]);
//		}
//		System.out.println(Arrays.toString(a0));
//		System.out.println(Arrays.toString(a1.toArray()));
//		
//		ArrayList<Integer> a2 = new ArrayList<Integer>();
//		
//		
//		a2.add(a1.get(0));
//		
//		for (int i = 1; i < a1.size(); i++) {
//			boolean add = true;
//
//			innerloop:
//				for (int j = 0; j < a2.size(); j++) {
//					if (a1.get(i) == a2.get(j) ) {
//						add = false;
//						break innerloop;
//					}
//				}
//			if (add) {
//				a2.add(a1.get(i));
//			}
//		}
//		
//		System.out.println(Arrays.toString(a2.toArray()));
		
		
		
		
		
//		MyVector v1 = new MyVector(1, 2);
//		MyVector v2 = new MyVector(2, 3);
//		MyVector v3 = new MyVector(3, 2);
//		MyVector v4 = new MyVector(2, 1);
//		
//		v1.setVectorId(1);
//		v2.setVectorId(3);
//		v3.setVectorId(4);
//		v4.setVectorId(2);
//		
//		ArrayList<MyVector> pointSet = new ArrayList<MyVector>();
//		
//		pointSet.add(v1);
//		pointSet.add(v2);
//		pointSet.add(v3);
//		pointSet.add(v4);
//		
//		ArrayList<MyTriangle> allTriangles = DelaunayBruteForce.getTriangles(pointSet);
//		
//		System.out.println(allTriangles.size());
//		System.out.println();
//		for (int i = 0; i < allTriangles.size(); i++) {
//			System.out.println("triangle " + (i + 1) + ": " + allTriangles.get(i).isClockwise());
//			System.out.println(allTriangles.get(i));
//			System.out.println();

		
		
		
		
		
		
//		MyVector v4 = new MyVector(2, 0);
//		MyVector v5 = new MyVector(5, 0);
//		MyVector v6 = new MyVector(7, 0);
//		
//		v1.setVectorId(5);
//		v2.setVectorId(2);
//		v3.setVectorId(4);
//		v4.setVectorId(6);
//		v5.setVectorId(1);
//		v6.setVectorId(3);
//		
//		MyTriangle t1 = new MyTriangle(v1, v2, v3);
//		
//		for (int i = 0; i < t1.getVerticesArray().length; i++) {
//			System.out.println(t1.getVerticesArray()[i]);
//		}
		
		
//		MyVector[] a1 = new MyVector[6];
//		
//		a1[0] = v1;
//		a1[1] = v2;
//		a1[2] = v3;
//		a1[3] = v4;
//		a1[4] = v5;
//		a1[5] = v6;
//		
//		for (int i = 0; i < a1.length; i++) {
//			System.out.print(a1[i].getVectorId() + " ");
//		}
		
//		for (int i = 1; i < a1.length; i++) {
//			innerloop:
//			for (int j = i; j > 0; j--) {
//				if (a1[j].getVectorId() < a1[j-1].getVectorId()) {
//					MyVector tempVector = a1[j];
//					a1[j] = a1[j-1];
//					a1[j-1] = tempVector;		
//				} else {
//					break innerloop;
//				}
//			}
//		}
		
//		System.out.println();
//		
//		for (int i = 0; i < a1.length; i++) {
//			System.out.print(a1[i].getVectorId() + " ");
//		}
		
		
		
//		v1.setVectorId(2);
//		v2.setVectorId(1);
//		v3.setVectorId(3);
//		
//		MyTriangle t1 = new MyTriangle(v3, v2, v3);
//
//		MyVector[] a1 = new MyVector[3];
//		MyVector[] a2 = new MyVector[3];
//		
//		a1[0] = v1;
//		a1[1] = v2;
//		a1[2] = v3;
//		
//		a2[0] = v1;
//		a2[1] = v2;
//		a2[2] = v3;
		
//		a2[0] = a1[0];
//		a2[1] = a1[1];
//		a2[2] = a1[2];
		
//		System.out.println(Arrays.toString(a1));
//		System.out.println(Arrays.toString(a2));
//		System.out.println();
//		
//		a1[0].setCoordX(6);
//		a1[0].setCoordY(6);
//		
//		System.out.println(Arrays.toString(a1));
//		System.out.println(Arrays.toString(a2));
//		System.out.println();
		
//		ArrayList<Vehicle> vehicleArrayList = new ArrayList<Vehicle>();
//		ArrayList<Car> carArrayList = new ArrayList<Car>();
//		
//		Vehicle v1 = new Vehicle("807J");
//		Car c1 = new Car("Red", 150, "546X");
//		
//		
//		System.out.println(c1.getClass());
//		
//		
//		System.out.println(((Vehicle)c1).getClass());
		

		

		




class Vehicle {
	
	private String plate;
	
	public Vehicle(String plate) {
		this.plate = plate;
	}

	public final String getPlate() {
		return plate;
	}

	public final void setPlate(String plate) {
		this.plate = plate;
	}
}

class Car extends Vehicle {
	
	private String color;
	private int maxSpeed;
	
	public Car(String color, int maxSpeed, String plate) {
		super(plate);
		this.color = color;
		this.maxSpeed = maxSpeed;
	}

	public final String getColor() {
		return color;
	}

	public final void setColor(String color) {
		this.color = color;
	}

	public final int getMaxSpeed() {
		return maxSpeed;
	}

	public final void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
//	public String toString() {
//		return color + " " + maxSpeed + "km/h";
//	}

}
}
