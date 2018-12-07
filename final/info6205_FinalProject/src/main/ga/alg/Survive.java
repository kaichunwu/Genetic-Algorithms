package ga.alg;

import java.util.Arrays;

import ga.gene.Generation;

public class Survive {

	/**
	 * survive to half of the population
	 * @param gen
	 * @return survived individuals
	 */
	public static int[] execute(Generation gen){
		double[] props = gen.getProps();
		double[] sorted = new double [props.length];
		System.arraycopy(props, 0, sorted, 0, props.length);
		Arrays.sort(sorted);
		double mid = sorted[sorted.length/2];
		int index = 0;
		int[] sur = new int[props.length];
		for (int i = 0; i < props.length; i++) {
			if(props[i]>=mid) {
				sur[index] = i;
				index++;
				if(index==props.length/2) {
					break;
				}
			}			
		}
		int[] survives = new int[index];
		System.arraycopy(sur, 0, survives, 0, index);;
		gen.changeSize(survives);
		return survives;
	}
}
