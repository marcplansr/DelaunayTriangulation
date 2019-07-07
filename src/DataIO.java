import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author x
 *
 */
public class DataIO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * @return
	 * @throws FileNotFoundException
	 */
	public static ArrayList<MyVector> importVertices(String fileName) 
			throws FileNotFoundException {
		ArrayList<ArrayList<MyVector>> allVertices = extractVectorsCSV(fileName);
		return allVertices.get(0);
	}
	
	
	/**
	 * Returns an ArrayList of MyVector ArrayLists; constructing MyVector
	 * objects with coords from given cvs file. First ArrayList contains 
	 * all vertices. The rest of them contain vertices constrains info, 
	 * so that each ArrayList represent consecutive connected vertices.
	 * Same as previous method but vertices are represented by a simplified
	 * type to be used in brute force algorithm
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static ArrayList<ArrayList<MyVector>> extractVectorsCSV(String fileName) 
			throws FileNotFoundException{
		ArrayList<ArrayList<MyVector>> vectorArray = new 
				ArrayList<ArrayList<MyVector>>();
		vectorArray.add(new ArrayList<MyVector>());
		
		fileName = "data/" + fileName + ".csv";
		File file = new File(fileName);
		Scanner input = new Scanner(file);
		int i = 0;
		while (input.hasNextLine()) {
			String newLine = input.nextLine();
			String[] lineArray = newLine.split(",");
			if (lineArray.length > 1) { 
				MyVector newVector = new MyVector(
						Double.parseDouble(lineArray[0]), 
						Double.parseDouble(lineArray[1]), 
						Double.parseDouble(lineArray[2]));
				vectorArray.get(0).add(newVector);
				if (i > 0) {
					vectorArray.get(i).add(newVector);
				}
			} else {
				vectorArray.add(new ArrayList<MyVector>());
				i++;
			}
		}
		input.close();
		return vectorArray;
	}
	

	/**
	 * @param toExport
	 */
	public static void exportCSV(ArrayList<MyVector> toExport) {
		String dataStr = "";
		for (MyVector currentPoint : toExport) {
			dataStr += currentPoint.getCoordX() + ", " +
					currentPoint.getCoordY() + ", " + 
					currentPoint.getCoordZ() + "\n";
		}
		
		dataStr =  dataStr.substring(0, dataStr.length() - 1);
		
		try(PrintWriter out = new PrintWriter("data/pointSet.csv")){
		    out.println(dataStr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
