package ga.gene;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class PointTest {
	
	@Test
	public void testToByteAndToPoint0() {
		Point p0 = new Point(40, 50);
		byte[] b0 = p0.toByte();
		Point result = Point.toPoint(b0);
		assertEquals(p0.getX(), result.getX());
		assertEquals(p0.getY(), result.getY());
	}
	
	@Test
	public void testToByteAndToPoint1() {
		Point p1 = new Point(0, 0);
		byte[] b1 = p1.toByte();
		Point result = Point.toPoint(b1);
		assertEquals(p1.getX(), result.getX());
		assertEquals(p1.getY(), result.getY());
	}
	
	@Test
	public void testToByteAndToPoint2() {
		Point p2 = new Point(10, 78);
		byte[] b2 = p2.toByte();
		Point result = Point.toPoint(b2);
		assertEquals(p2.getX(), result.getX());
		assertEquals(p2.getY(), result.getY());
	}
	

	@Test
	public void testPoint() throws Exception{
		Point p1 = new Point(3, 4);
		Point p2 = new Point(5, 6);
		Point p3 = new Point(3, 4);
		Point p4 = new Point(10, 0);
		
		double d1 = Point.distance(p1, p2);
		double d2 = Point.distance(p3, p4);
	   
	    assertEquals(Double.valueOf(2.8284), d1, 1.0);
	    assertEquals(Double.valueOf(8.0623), d2, 1.0);

	}

}
