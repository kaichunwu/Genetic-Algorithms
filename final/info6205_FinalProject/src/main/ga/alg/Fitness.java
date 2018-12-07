package ga.alg;

import ga.gene.Point;

public class Fitness {
	
	/**
	 * To calculate fitness
	 * it is reverse of the total distance for all points between
	 * generation and target
	 * @param individual an individual's information
	 * @return fitness
	 */
	public static double calculate(Point[] ps,Point[] individual) {
		double distance = 0;
		for (int i = 0; i < individual.length; i++) {
			distance += Point.distance(ps[i], individual[i]);
//			distance += ps[i].distance(individual[i]);
		}
		return 10000/distance;
	}
	
	public static double calDis(Point[] ps,Point[] individual) {
		double distance = 0;
		for (int i = 0; i < individual.length; i++) {
			distance += Point.distance(ps[i], individual[i]);
//			distance += ps[i].distance(individual[i]);
		}
		return distance;
	}

}
