package ga.alg;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class BreedTest {

	@Test
	public void maxTest() {
		double[] props = new double[10];
		Random r = new Random();
		for (int i = 0; i < props.length; i++) {
			props[i] = r.nextDouble();
		}
		double[] cp = new double[10];
		System.arraycopy(props, 0, cp, 0, 10);
		Arrays.sort(cp);
		int c = Breed.copy(0, props);
		assertTrue(props[c]==cp[cp.length-1]);
		c = Breed.copy(1, props);
		assertTrue(props[c]==cp[cp.length-2]);
		c = Breed.copy(2, props);
		assertTrue(props[c]==cp[cp.length-3]);
		c = Breed.copy(3, props);
		assertTrue(props[c]==cp[cp.length-4]);
		c = Breed.copy(4, props);
		assertTrue(props[c]==cp[cp.length-5]);
	}
	
	@Test
	public void notSame() {
		byte[][] bts = new byte[100][8];
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 8; j++) {
				if(j==0||j==1||j==4||j==5) {
					bts[i][j] = 0;
				}else {
					bts[i][j] = (byte) r.nextInt(10);
				}
			}
		}
		byte[][] bts2 = new byte[100][8];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 8; j++) {
				if(j==0||j==1||j==4||j==5) {
					bts2[i][j] = 0;
				}else {
					bts2[i][j] = (byte) r.nextInt(10);
				}
			}
		}
		byte[][] child = Breed.cross(bts, bts2, true, 100);
		assertFalse(child==bts);
		assertFalse(child==bts2);
	}

}
