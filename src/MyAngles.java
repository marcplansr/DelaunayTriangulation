public class MyAngles {

	public static void main(String[] args) {

	}


	/**
	 * Returns 2D angle of given vector from x axis, taking its x and y
	 * components. Returns counterclockwise angle from 0 to 180 degrees if
	 * resulting angle <= 180 degrees, and clockwise negative angle from 0 to
	 * -180 (last excluded) if resulting angle > 180 degrees.
	 * 
	 * @param componentX
	 * @param componentY
	 * @return
	 */
	public static double dualVectAngle2D(double componentX, double componentY) {
		return Math.toDegrees(Math.atan2(componentY, componentX));
	}

	/**
	 * Returns 2D angle vector from x axis, given its x and y components, and a
	 * boolean so clockwise or counterclockwise angle is returned.
	 * 
	 * @param componentX
	 * @param componentY
	 * @param counterclockwise
	 * @return
	 */
	public static double vectAngle2D(double componentX, double componentY,
			boolean counterclockwise) {
		double angle = Math.toDegrees(Math.atan2(componentY, componentX));
		angle = (!counterclockwise && angle > 0) ? angle - 360 : angle;
		angle = (counterclockwise && angle < 0) ? angle + 360 : angle;
		// return angle;
		return Math.abs(angle);
	}

	/**
	 * Returns 2D angle of vector from x axis, given x and y coordinates from
	 * origin and endpoint, and a boolean so clockwise or counterclockwise angle
	 * is returned.
	 * 
	 * @param originCoordX
	 * @param originCoordY
	 * @param endpointCoordX
	 * @param endpointCoordY
	 * @param counterclockwise
	 * @return
	 */
	public static double vectAngle2D(double originCoordX, double originCoordY,
			double endpointCoordX, double endpointCoordY,
			boolean counterclockwise) {
		return vectAngle2D(endpointCoordX - originCoordX,
				endpointCoordY - originCoordY, counterclockwise);
	}

	/**
	 * Returns 2D angle between two vectors, given their x and y components, and
	 * a boolean so clockwise or counterclockwise angle is returned.
	 * 
	 * @param firstVectorComponentX
	 * @param firstVectorComponentY
	 * @param secondVectorComponentX
	 * @param secondVectorComponentY
	 * @param counterclockwise
	 * @return
	 */
	public static double twoVectAngle2D(double firstVectorComponentX,
			double firstVectorComponentY, double secondVectorComponentX,
			double secondVectorComponentY, boolean counterclockwise) {

		double angle = dualVectAngle2D(secondVectorComponentX,
				secondVectorComponentY)
				- dualVectAngle2D(firstVectorComponentX, firstVectorComponentY);
		angle = (!counterclockwise && angle > 0) ? angle - 360 : angle;
		angle = (counterclockwise && angle < 0) ? angle + 360 : angle;
		// return angle;
		return Math.abs(angle);
	}

	/**
	 * Returns 2D angle between vector from origin to first endpoint and vector
	 * from origin to second endpoint, given x and y coordinates from each point
	 * and a boolean so clockwise or counterclockwise angle is returned.
	 * 
	 * @param originCoordX
	 * @param originCoordY
	 * @param firstEndpointCoordX
	 * @param firstEndpointCoordY
	 * @param secondEndpointCoordX
	 * @param secondEndpointCoordY
	 * @param counterclockwise
	 * @return
	 */
	public static double twoVectAngle2D(double originCoordX,
			double originCoordY, double firstEndpointCoordX,
			double firstEndpointCoordY, double secondEndpointCoordX,
			double secondEndpointCoordY, boolean counterclockwise) {

		return twoVectAngle2D(firstEndpointCoordX - originCoordX,
				firstEndpointCoordY - originCoordY,
				secondEndpointCoordX - originCoordX,
				secondEndpointCoordY - originCoordY, counterclockwise);
	}

	/**
	 * Returns smallest 2D angle between vector from origin to first endpoint
	 * and vector from origin to second endpoint, given x and y coordinates from
	 * each point; counterclockwise angle has a positive value from 0 to 180
	 * degrees and clockwise angle has negative value from 0 to -180 (last
	 * excluded).
	 * 
	 * @param firstVectorComponentX
	 * @param firstVectorComponentY
	 * @param secondVectorComponentX
	 * @param secondVectorComponentY
	 * @return
	 */
	public static double twoVectAngle2D(double firstVectorComponentX,
			double firstVectorComponentY, double secondVectorComponentX,
			double secondVectorComponentY) {

		double angle = dualVectAngle2D(secondVectorComponentX,
				secondVectorComponentY)
				- dualVectAngle2D(firstVectorComponentX, firstVectorComponentY);
		angle = (angle > 180) ? angle - 360 : angle;
		angle = (angle < -180) ? angle + 360 : angle;
		angle = (angle == -180) ? 180 : angle;
		return angle;
	}


	/**
	 * Returns smallest 2D angle between two vectors, given x and y coordinates
	 * from each point; counterclockwise angle has a positive value from 0 to
	 * 180 degrees and clockwise angle has negative value from 0 to -180 (last
	 * excluded).
	 * 
	 * @param originCoordX
	 * @param originCoordY
	 * @param firstEndpointCoordX
	 * @param firstEndpointCoordY
	 * @param secondEndpointCoordX
	 * @param secondEndpointCoordY
	 * @return
	 */
	public static double twoVectAngle2D(double originCoordX,
			double originCoordY, double firstEndpointCoordX,
			double firstEndpointCoordY, double secondEndpointCoordX,
			double secondEndpointCoordY) {

		return twoVectAngle2D(firstEndpointCoordX - originCoordX,
				firstEndpointCoordY - originCoordY,
				secondEndpointCoordX - originCoordX,
				secondEndpointCoordY - originCoordY);
	}
}
