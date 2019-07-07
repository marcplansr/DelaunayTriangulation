import java.util.ArrayList;

public class MyPrint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * Prints an ArrayList of MyVector objects
	 * @param toPrint
	 */
	public static void printVertices(ArrayList<MyVector> toPrint) {
		for (int i = 0; i < toPrint.size(); i++) {
			System.out.println(toPrint.get(i));
		}
	}

	
//	for (int i = 0; i < vertices.size(); i++) {
//	for (int j = 0; j < vertices.get(i).size(); j++) {
//		System.out.println(vertices.get(i).get(j));
//	}
//	System.out.println();
//}
	
	
}
