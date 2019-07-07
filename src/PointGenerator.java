import java.util.ArrayList;
import java.util.Random;

/**
 * @author x
 *
 */
public class PointGenerator {
	
	static final int POINTS_LENGTH = MyDraw.SCREEN_LENGTH;
	static final int POINTS_HEIGHT = MyDraw.SCREEN_HEIGHT;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public static ArrayList<MyVector> importVertices(int numberOfPoints) {
		return PointGenerator.createPoints(
				numberOfPoints, POINTS_LENGTH, POINTS_HEIGHT,
				new MyVector(MyDraw.SCREEN_LENGTH / 2, MyDraw.SCREEN_HEIGHT / 2));
		
	}
	

	/**
	 * @param pointNum
	 * @param length
	 * @param height
	 * @param center
	 * @return
	 */
	public static ArrayList<MyVector> createPoints(int pointNum, 
		double length, double height, MyVector center) {

	    if (pointNum == 1) {
		ArrayList<MyVector> outputPoints = new ArrayList<MyVector>();
		outputPoints.add(randomCenter(length, height, center));
		//			outputPoints.add(center);
		return outputPoints;
	    }

	    int pointNum1 = pointNum / 2;	
	    int pointNum2 = pointNum / 2;	
	    if (pointNum % 2 != 0 && Math.round(Math.random()) == 0) {	
		pointNum1++;
	    } else if (pointNum % 2 != 0) {
		pointNum2++;
	    }

	    MyVector center1;
	    MyVector center2;
	    if (height > length) {
		center1 = new MyVector(
			center.getCoordX(), center.getCoordY() - height / 4);
		center2 = new MyVector(
			center.getCoordX(), center.getCoordY() + height / 4);
		height = height / 2;
	    } else {
		center1 = new MyVector(
			center.getCoordX() - length / 4, center.getCoordY());
		center2 = new MyVector(
			center.getCoordX() + length / 4, center.getCoordY());
		length = length / 2;
	    }

	    return mergePoints(
		    createPoints(pointNum1, length, height, center1),
		    createPoints(pointNum2, length, height, center2));
	}
	
	
	/**
	 * @param array1
	 * @param array2
	 * @return
	 */
	private static ArrayList<MyVector> mergePoints(
		ArrayList<MyVector> array1, ArrayList<MyVector> array2) {
	    ArrayList<MyVector> output = new ArrayList<MyVector>();
	    output.addAll(array1);
	    output.addAll(array2);
	    return output;
	}
	
	
	/**
	 * @param length
	 * @param height
	 * @param center
	 * @return
	 */
	private static MyVector randomCenter(
		double length, double height, MyVector center) {

	    Random rand = new Random();

	    int max = (int)(length * 1000 / 3);
	    int min = max * -1;
	    max++;

	    double deltaX = (rand.nextInt(max-min) + min) / 1000.0;

	    max = (int)(height * 1000 / 3);
	    min = max * -1;
	    max++;

	    double deltaY = (rand.nextInt(max-min) + min) / 1000.0;

	    return new MyVector(center.getCoordX() + deltaX, 
		    center.getCoordY() + deltaY);
	}
	
	
	
	


}
