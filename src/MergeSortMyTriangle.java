import java.util.ArrayList;

public class MergeSortMyTriangle {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * @param toSort
	 * @return
	 */
	public static ArrayList<MyTriangle> sortTriangles(ArrayList<MyTriangle> toSort) {
		return mergeSortMyTriangle(toSort);
	}

	/**
	 * @param pointArray
	 * @return
	 */
	public static ArrayList<MyTriangle> mergeSortMyTriangle(
			ArrayList<MyTriangle> triangleArray) {
		
		if(triangleArray.size() < 2) {
			triangleArray.get(0).arrangeVertices();
			return triangleArray;
			
		} else {
			int mid = triangleArray.size() / 2;
			ArrayList<MyTriangle> left = new ArrayList<MyTriangle>();
			ArrayList<MyTriangle> right = new ArrayList<MyTriangle>();
			left.addAll(triangleArray.subList(0, mid));
			right.addAll(triangleArray.subList(mid, triangleArray.size()));
			left =  mergeSortMyTriangle(left);
			right =  mergeSortMyTriangle(right);
			return mergeMyTriangle(left, right);
		}
	}
	
	
	/**
	 * @param left
	 * @param right
	 * @return
	 */
	private static ArrayList<MyTriangle> mergeMyTriangle(
			ArrayList<MyTriangle> left, ArrayList<MyTriangle> right) {
		ArrayList<MyTriangle> merged = new ArrayList<MyTriangle>();
		int i = 0;
		int j = 0;
		while(i < left.size() && j < right.size()) {
			if (left.get(i).isHigher(right.get(j))) {
				merged.add(right.get(j));
				j++;
			} else {
				merged.add(left.get(i));
				i++;	
			}
		}
		merged.addAll(left.subList(i, left.size()));
		merged.addAll(right.subList(j, right.size()));
		return merged;
	}
	
	/*
	while(i < left.size() && j < right.size()) {
		if (left.get(i).getCoordX() < right.get(j).getCoordX()) {
			// first point in left half has smallest X coordinate
			merged.add(left.get(i));
			i++;	
		} else if (left.get(i).getCoordX() > right.get(j).getCoordX()) {
			// first point in right half has smallest X coordinate
			merged.add(right.get(j));
			j++;
		} else if (left.get(i).getCoordY() < right.get(j).getCoordY()) {
			// first point in both halves have same x coordinate,
			// but first point in left half has smallest Y coordinate
			merged.add(left.get(i));
			i++;
		} else {
			// rest of cases, both points have same x coordinate,
			// and second half has smallest or same Y coordinate
			merged.add(right.get(j));
			j++;
		}
	}
*/

	

}
