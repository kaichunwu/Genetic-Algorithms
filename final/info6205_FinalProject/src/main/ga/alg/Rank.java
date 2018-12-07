package ga.alg;

import ga.gene.Point;

public class Rank {
	
	/**
	 * calculate fitness rate
	 * which means individual fitness in which rate of the general fitness
	 * @param ps
	 * @param population
	 * @return
	 */
	public static double[] execute(Point[] ps,Point[][] population) {
		double[] fits = new double[population.length];
		double[] props = new double[population.length];
		double sum = 0;
		for (int i = 0; i < population.length; i++) {
			fits[i] = Fitness.calculate(ps,population[i]);
			sum+=fits[i];
		}
		for (int i = 0; i < population.length; i++) {
			props[i] = fits[i]/sum;
		}
		return props;
	}
	
	public static double[] adapt(Point[] ps,Point[][] population) {
		double[] fits = new double[population.length];
		for (int i = 0; i < population.length; i++) {
			fits[i] = Fitness.calculate(ps,population[i]);
		}
	
		return fits;
	}
	/**
	 * recalculate the fitness rate
	 * @param props
	 * @return
	 */
	public static double[] recalculate(double[] props) {
		double sum = 0;
		for (int i = 0; i < props.length; i++) {
			sum += props[i];			
		}
		for (int i = 0; i < props.length; i++) {
			props[i] = props[i]/sum;			
		}
		return props;
	}
}
