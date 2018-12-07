package ga.gene;

import java.util.Arrays;

import ga.alg.Rank;

public class Generation {

	// phenotype
	private Point[][] population;
	// fitness rate
	private double[] props;
	// genotype
	private byte[][][] geno;
	
	public Generation(Point[][] population, double[] props) {
		this.population = population;
		this.props = props;
		geno(population);
	}
	/**
	 * generate generation by genotype and target points
	 * @param bts
	 * @param ps
	 */
	public Generation(byte[][][] bts, Point[] ps) {
		this.geno = bts;
		pheno(bts);
		props(this.population, ps);
	}
	/**
	 * generate generation by genetype and target points
	 * @param population
	 * @param ps
	 */
	public Generation(Point[][] population, Point[] ps) {
		this.population = population;
		props(population, ps);
		geno(population);
	}

	/**
	 * generate genetype
	 * @param population2
	 */
	private void geno(Point[][] population2) {
		geno = new byte[population2.length][population2[0].length][8];
		for (int i = 0; i < population2.length; i++) {
			for (int j = 0; j < population2[0].length; j++) {
				geno[i][j] = population2[i][j].toByte();
			}
		}
	}
	/**
	 * generate phenotype
	 * @param bts
	 */
	private void pheno(byte[][][] bts) {
		population = new Point[bts.length][bts[0].length];
		for (int i = 0; i < bts.length; i++) {
			for (int j = 0; j < bts[0].length; j++) {
				population[i][j] = Point.toPoint(bts[i][j]);
			}
		}
	}

	/**
	 * generate fitness rate
	 * @param population2
	 * @param ps
	 */
	private void props(Point[][] population2, Point[] ps) {
		this.props = Rank.execute(ps, population2);
	}
	/**
	 * change generation size
	 * @param sur
	 */
	public void changeSize(int[] sur) {
		Point[][] population2 = new Point[sur.length][];
		double[] props2 = new double[sur.length];
		byte[][][] bts = new byte[sur.length][][];
		int index = 0;
		for(int i : sur) {
			population2[index] = population[i];
			props2[index] = props[i];
			bts[index] = geno[i];
			index++;
		}
		this.population = population2;
		this.props = Rank.recalculate(props2);
		this.geno = bts;
	}

	public Point[][] getPopulation() {
		return population;
	}


	public double[] getProps() {
		return props;
	}

	public byte[][][] getGeno() {
		return geno;
	}

	public void clear() {
		this.population = null;
		this.geno = null;
		this.props = null;
	}
	
	public void change(byte[][][] bts, Point[] ps) {
		this.geno = null;
		this.geno = bts;
		pheno(bts);
		props(this.population, ps);
	}	
	/**
	 * return the best individual
	 * @return
	 */
	public Point[] max() {
		double[] d = new double[props.length];
		System.arraycopy(props, 0, d, 0, props.length);
		Arrays.sort(d);
		for (int i = 0; i < props.length; i++) {
			if(props[i]==d[props.length-1]) {
				return population[i];
			}			
		}
		return population[0];
	}
}
