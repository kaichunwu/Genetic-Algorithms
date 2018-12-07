package ga.alg;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import ga.gene.*;

public class SurviveTest {

	@Test
	public void test() {
		Point[] ps = new Point[10];
		Random r = new Random();
		for (int i = 0; i < ps.length; i++) {
			ps[i] = new Point(r.nextInt(10),r.nextInt(10));
		}
		
		byte[][][] bts = new byte[100][10][8];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 8; k++) {
					bts[i][j][k] = 0;
				}
			}
		}
		Generation gen = new Generation(bts,ps);
		int[] sur = Survive.execute(gen);
		// survive a half
		assertEquals(sur.length, 50);
	}

}
