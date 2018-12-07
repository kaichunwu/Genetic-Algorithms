package ga.alg;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ga.gene.Point;

import static org.junit.Assert.assertEquals;
class RankTest {

	@Test
	void testRank() throws Exception {

		double a = 1;
		double b = 2;
		double c = 3;
		double d = 4;
		double[] test = { a, b, c, d };
		double[] tt = Rank.recalculate(test);
		double result = 0.0;
		for (int i = 0; i < tt.length; i++) {
			result += tt[i];
		}
		assertEquals(1.0, result, 0.0);
	}
	
	@Test
	void testExecute() throws Exception {
		
		Point[] p1list = {new Point(7,8), new Point(9,10)};
		Point[][] p2list = {{new Point(1,2), new Point(3,4)}, {new Point(5,6), new Point(7,8)}};
		double[] tt = Rank.execute(p1list, p2list);
		double executeResult = 0.0;
		for (int i = 0; i < tt.length; i++) {
			executeResult += tt[i];	
		}
		assertEquals(1.0, executeResult, 0.0);
	
	}

}
