import java.util.ArrayList;

public class MergeSortMyVector {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * @param toSort
	 * @return
	 */
	public static ArrayList<MyVector> sortVertices(ArrayList<MyVector> toSort) {
		ArrayList<MyVector> sortedVertices = mergeSortMyVector(toSort);
		sortedVertices = resetVectorId(sortedVertices);
		return sortedVertices;
	}
	

	/**
	 * @param pointArray
	 * @return
	 */
	public static ArrayList<MyVector> mergeSortMyVector(
			ArrayList<MyVector> pointArray) {
		
		if(pointArray.size() < 2) {
			return pointArray;
			
		} else {
			int mid = pointArray.size() / 2;
			ArrayList<MyVector> left = new ArrayList<MyVector>();
			ArrayList<MyVector> right = new ArrayList<MyVector>();
			left.addAll(pointArray.subList(0, mid));
			right.addAll(pointArray.subList(mid, pointArray.size()));
			left =  mergeSortMyVector(left);
			right =  mergeSortMyVector(right);
			return mergeMyVector(left, right);
		}
	}
	
	
	/**
	 * @param left
	 * @param right
	 * @return
	 */
	private static ArrayList<MyVector> mergeMyVector(
			ArrayList<MyVector> left, ArrayList<MyVector> right) {
		ArrayList<MyVector> merged = new ArrayList<MyVector>();
		int i = 0;
		int j = 0;
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
		merged.addAll(left.subList(i, left.size()));
		merged.addAll(right.subList(j, right.size()));
		return merged;
	}
	
	
	/**
	 * @param toReset
	 * @return
	 */
	public static ArrayList<MyVector> resetVectorId(ArrayList<MyVector> toReset) {
		for (int i = 0; i < toReset.size(); i++) {
			toReset.get(i).setVectorId(i);
		}
		return toReset;
	}

	

}
