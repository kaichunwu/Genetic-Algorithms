package ga.alg;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class SelectionTest {

	@Test
	public void mutSizeTest() {
		int[] mut = Selection.selectMutId(1000);
		// 80 percentage of selection to mutation
		assertTrue(mut.length-800<30);
	}
	
	@Test
	public void selectTest() {
		double[] props = new double[10];
		Random r = new Random();
		for (int i = 0; i < props.length; i++) {
			props[i] = r.nextDouble();
		}
		double[] cp = new double[10];
		System.arraycopy(props, 0, cp, 0, 10);
		Arrays.sort(cp);
		int s = Selection.selectId(props);
		// select good one
		assertTrue(cp[0]<props[s]);
	}

}
