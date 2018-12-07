package ga.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import ga.gene.Generation;

public class Breed {

	private static int copy = 4;

	/**
	 * breed to next generation
	 * return is genetype
	 * @param gen
	 * @return
	 * @throws Exception
	 */
	public static byte[][][] execute(Generation gen) throws Exception{
		byte[][][] geno = gen.getGeno();
		double[] props = gen.getProps();
		int sizeOfPop = 2 * geno.length;
		Random random = new Random();
		if (random.nextDouble() > 0.99) {
			sizeOfPop--;
		}
		double avg = 0;
		for (int i = 0; i < props.length; i++) {
			avg += props[i];
		}
		avg = avg / props.length;
		boolean m = false;
		int th = 100;
		if (props[copy(0, props)] - avg < 0.001) {
			m = true;
			th = 40;
		} else if (random.nextDouble() > 0.99) {
			m = true;
		}
		byte[][][] nextGeneration = new byte[sizeOfPop][geno[0].length][];
		int[] mutation = Selection.selectMutId(geno.length);
		List<Future<byte[][]>> ls = new ArrayList<>();
		int i = 0;
		while (i < sizeOfPop) {
			if (i < copy) {
				nextGeneration[i] = geno[copy(i, props)];
				i++;
				continue;
			}
			boolean mut = false;
			if (m) {
				for (int j = 0; j < mutation.length; j++) {
					if (i == mutation[j]) {
						mut = true && m;
						break;
					}
				}
			}
			int fa = Selection.selectId(props);
			int mo = Selection.selectId(props);
			
			Future<byte[][]> fu = exector.submit(new CallableTest(geno[fa], geno[mo], mut, th));
			
			i++;
			if (i >= sizeOfPop) {
				ls.add(fu);
				break;
			}
			Future<byte[][]> fu2 = exector.submit(new CallableTest(geno[fa], geno[mo], mut, th));						
			
			i++;
			ls.add(fu);
			ls.add(fu2);
		}
		i = copy;
		for(Future<byte[][]> f : ls) {
			nextGeneration[i] = f.get();
			i++;
		}
		
		return nextGeneration;
	}

	/**
	 * crossover and mutation
	 * for th individual mutation one individual
	 * @param fa
	 * @param mo
	 * @param mut
	 * @param th
	 * @return
	 */
	public static byte[][] cross(byte[][] fa, byte[][] mo, boolean mut, int th) {
		byte[][] child = new byte[fa.length][8];
		Random random = new Random();
		int mutChro[] = new int[mo.length / th];
		for (int i = 0; i < mutChro.length; i++) {
			mutChro[i] = mo.length;
		}
		int index = 0;
		while (mut && index < mutChro.length) {
			mutChro[index] = Selection.selectMut(mo.length);
			index++;
		}
		for (int i = 0; i < mo.length; i++) {
			for (int a : mutChro) {
				if (a == i) {
					mut = true;
					break;
				} else {
					mut = false;
				}
			}		
			int j = 0;
			while (j < 8) {
				double d = random.nextDouble();
				if ((j == 3 || j == 7) && mut && random.nextDouble()>0.9) {
					int x = fa[i][j];
					if(random.nextDouble()>0.5) {
						x = mo[i][j];
					}
					int y = (int) (x * (d+0.5));
					child[i][j] = (byte) (y & 0xFF);
				} else if (d>0.5) {
					child[i][j] = mo[i][j];
				} else {
					child[i][j] = fa[i][j];
				}
				j++;
			}

		}
		return child;
	}

	/**
	 * return the index of the i th best in the population
	 * @param i
	 * @param props
	 * @return
	 */
	public static int copy(int i, double[] props) {
		Map<Integer, Double> map = new HashMap<>();
		int l = props.length;
		for (int j = 0; j < l; j++) {
			map.put(j, props[j]);
		}
		double[] cp = new double[props.length];
		System.arraycopy(props, 0, cp, 0, props.length);
		Arrays.sort(cp);
		int res = 0;
		for (int j = 0; j < map.size(); j++) {
			if (map.get(j) == cp[l - 1 - i]) {
				res = j;
				break;
			}
		}
		return res;
	}
	
	public static ExecutorService exector = new ForkJoinPool();
	
	// Callable class
	private static class CallableTest implements Callable<byte[][]>{
		private byte[][] fa;
		private byte[][] mo;
		private boolean mu;
		private int th;
		@Override
		public byte[][] call() throws Exception {
			return cross(fa, mo, mu, th);
		}
		public CallableTest(byte[][] fa, byte[][] mo, boolean mut, int th){
			this.fa = fa;
			this.mo = mo;
			this.mu = mut;
			this.th = th;
		}
	}
}
