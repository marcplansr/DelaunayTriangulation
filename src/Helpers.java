
public class Helpers {

	public static void main(String[] args) {
		
	}

	
	/**
	 * Helper function that calculates the determinant of a given array
	 * @param array
	 * @return
	 */
	public static double determinant(double[][] array) {
		if (array.length < 3) {
			return array[0][0] * array[1][1] - array[1][0] * array[0][1];
		} else {
			double result = 0;

			for (int col = 0; col < array[0].length; ++col) {
				int sign = 1;
				if (col % 2 != 0) {
					sign = -1;
				}
				if (array[0][col] != 0) {
					result += sign * array[0][col]
							* determinant(trimArray(array, 0, col));
				}
			}
			return result;
		}
	}

	// Returns a new array cloned from the one passed as argument,
	// but without the given row and column to be trimmed
	public static double[][] trimArray(double[][] arrayToTrim, int rowToTrim,
			int colToTrim) {
		double[][] trimmedArray = new double[arrayToTrim.length - 1][];
		int i = 0;
		for (int row = 0; row < arrayToTrim.length; ++row) {
			if (row != rowToTrim) {
				double[] newRow = new double[arrayToTrim[row].length - 1];
				trimmedArray[i] = newRow;
				int j = 0;
				for (int col = 0; col < arrayToTrim[row].length; ++col) {
					if (col != colToTrim) {
						trimmedArray[i][j] = arrayToTrim[row][col];
						++j;
					}
				}
				++i;
			}
		}
		return trimmedArray;
	}


	/**
	 * Returns a new array which is a copy of the one passed as argument
	 * @param arrayToClone
	 * @return
	 */
	public static double[][] cloneArray(double[][] arrayToClone) {
		double[][] newArray = new double[arrayToClone.length][];
		for (int row = 0; row < newArray.length; ++row) {
			double[] newRow = new double[arrayToClone[row].length];
			newArray[row] = newRow;
			for (int col = 0; col < newArray[row].length; ++col) {
				newArray[row][col] = arrayToClone[row][col];
			}
		}
		return newArray;
	}


	/**
	 * Method to round a double num with a given decimal places
	 * @param numToRound
	 * @param decimalPlaces
	 * @return
	 */
	public static double myRound(double numToRound, int decimalPlaces) {
		double prec = Math.pow(10, decimalPlaces);
		double roundedNum = Math.round(numToRound * prec) / prec;
		return roundedNum;
	}

	/**
	 * factorial recursive calculation
	 * 
	 * @param num
	 * @return num factorial
	 */
	public static int factorial(int num) {
		if (num == 0) {
			return 1;
		} else {
			return num * factorial(num - 1);
		}
	}

	/**
	 * factorial division recursive calculation
	 * 
	 * @param dividend
	 *            and divisor
	 * @return dividend factorial divided by divisor factorial
	 */
	public static int factorialDiv(int dividend, int divisor) {
		if (dividend == divisor) {
			return 1;
		} else {
			return dividend * factorialDiv(dividend - 1, divisor);
		}
	}

	/**
	 * calculates k-combinations of distinct n elements in set
	 * 
	 * @param elements
	 *            in set, elements in combination
	 * @return number of possible combinations
	 */
	public static int numCombinations(int setElements,
			int combinationElements) {
		return factorialDiv(setElements, (setElements - combinationElements))
				/ factorial(combinationElements);
	}

	/**
	 * TODO: REMOVE
	 * @param firstPoint
	 * @param secondPoint
	 * @param thirdPoint
	 * @return true if given points lay in a clockwise circle, false otherwise
	 */
//	public static boolean oldClockwise(MyVectorDC firstPoint,
//			MyVectorDC secondPoint, MyVectorDC thirdPoint) {
//		MyVectorDC side12 = new MyVectorDC(
//				secondPoint.getCoordX() - firstPoint.getCoordX(),
//				secondPoint.getCoordY() - firstPoint.getCoordY(),
//				secondPoint.getCoordZ() - firstPoint.getCoordZ());
//		MyVectorDC side13 = new MyVectorDC(
//				thirdPoint.getCoordX() - firstPoint.getCoordX(),
//				thirdPoint.getCoordY() - firstPoint.getCoordY(),
//				thirdPoint.getCoordZ() - firstPoint.getCoordZ());
//		if (side12.getAngleClockwise(side13) < 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	/**
	 * Returns true if given three 2D points lay clockwise, and false otherwise;
	 * starting on first point, passing through the second one and ending on the
	 * third.
	 * 
	 * @param firstPoint
	 * @param secondPoint
	 * @param thirdPoint
	 * @return
	 */
//	public static boolean clockwise(MyVectorDC firstPoint,
//			MyVectorDC secondPoint, MyVectorDC thirdPoint) {
//		double angle = MyAngles.twoVectAngle2D(firstPoint.getCoordX(),
//				firstPoint.getCoordY(), secondPoint.getCoordX(),
//				secondPoint.getCoordY(), thirdPoint.getCoordX(),
//				thirdPoint.getCoordY());
//		return (angle < 0);
//	}


	/**
	 * Returns true if point with coordX and coordY lies in segment defined by
	 * first point and second point, false otherwise.
	 * 
	 * @param coordX1
	 * @param coordY1
	 * @param coordX2
	 * @param coordY2
	 * @param coordX
	 * @param coordY
	 * @return
	 */
	public static boolean inSegment(double coordX1, double coordY1,
			double coordX2, double coordY2, double coordX, double coordY) {

		double tx = (coordX - coordX1) / (coordX2 - coordX1);
		double ty = (coordY - coordY1) / (coordY2 - coordY1);

		if (coordX1 == coordX2 && coordY1 != coordY2 && coordX == coordX1) {
			return (ty > 0 && ty < 1);
		}

		if (coordY1 == coordY2 && coordX1 != coordX2 && coordY == coordY) {
			return (tx > 0 && tx < 1);
		}

		if (tx == ty) {
			return (tx > 0 && tx < 1 && ty > 0 && ty < 1);
		}

		return false;
	}

}
