package ga;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ga.graph.*;
import ga.alg.*;
import ga.gene.*;


public class Driver {
	
	private static Logger log = Logger.getLogger(Driver.class);
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		PropertyConfigurator.configure("resource/Log/log4j.properties");
		log.info("Log is begining>>>>");
		try {
			Readimage ri = new Readimage(5);
			Point[] ps = ri.getImagePixelToArray("resource/target.png");
			ri.writeImage(ps, "resource/compress.png");
			log.info("Points number : "+ps.length);
			log.info("Width is : "+ri.getWidth());
			log.info("Height is : "+ri.getHeight());
			ga(ps, ri);
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	/**
	 * GA main function
	 * log for each generation and output picture each 100 generation
	 * @param ps
	 * @param ri
	 * @throws Exception
	 */
	public static void ga(Point[] ps, Readimage ri) throws Exception{
		Init init = new Init(1000, ps.length, ri.getWidth(), ri.getHeight(), ps);
		Generation gen = init.getGeneration();
		for (int i = 0; i < 5000; i++) {
			Survive.execute(gen);
			byte[][][] child = Breed.execute(gen);
			gen.clear();
			gen = null;
			gen = new Generation(child,ps);
			child = null;
			Point[] p = gen.max();
			if(i%99==0) {
				String fileN = "resource/gen"+i+".png";				
				new Thread((()->ri.writeImage(p, fileN))).start();				
			}
			log.info("Generation :"+i);
			log.info("Distance to target is :"+Fitness.calDis(ps, p));
		}
		log.info("Last Population: "+gen.getPopulation().length);
		System.out.println(Fitness.calDis(ps, gen.max()));
		System.out.println(gen.getPopulation().length);
		ri.writeImage(gen.max(), "resource/result.png");
	}
	/**
	 * see points
	 * @param ps
	 * @throws Exception
	 */
	public static void write(Point[] ps) throws Exception {		
		
		String fileName = "resource/write.txt";
		BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
		out.write("Target :\n");
		for (Point p : ps) {
			out.write(p.toString());
			out.newLine();
		}
		out.close();
	}
}
