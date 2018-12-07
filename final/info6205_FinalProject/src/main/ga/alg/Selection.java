package ga.alg;

import java.util.Random;

public class Selection {

	/**
	 * select the father or mother for next generation
	 * choose two randomly by their fitness rate
	 * select the higher one
	 * @param props
	 * @return
	 */
	public static int selectId(double[] props) {
		Random random = new Random();
		double r = random.nextDouble();
		double d = 0;
		int selectA = 0;
		for (int i = 0; i < props.length; i++) {
			d += props[i];
			if(d>r) {
				selectA = i;
				break;
			}
		}
		r = random.nextDouble();
		d = 0;
		int selectB = 0;
		for (int i = 0; i < props.length; i++) {
			d += props[i];
			if(d>r) {
				selectB = i;
				break;
			}
		}
		return props[selectA]>props[selectB] ? selectA : selectB;
	}
	/**
	 * choose which gene to mutation
	 * @param sizeOfChro
	 * @return
	 */
	public static int selectMut(int sizeOfChro) {
		Random random = new Random();		
		return random.nextInt(sizeOfChro);
	}
	
	private static double rate = 0.2;
	/**
	 * choose which individual to mutation
	 * @param sizeOfPop
	 * @return
	 */
	public static int[] selectMutId(int sizeOfPop) {
		Random random = new Random();
		int[] select = new int[sizeOfPop];
		int j = 0;
		for (int i = 0; i < sizeOfPop; i++) {
			double r = random.nextDouble();	
			if(r>rate) {
				select[j] = i;
				j++;
			}
		}
		int[] res = new int[j];
		System.arraycopy(select, 0, res, 0, j);
		return res;
	}
}
