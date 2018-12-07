package ga.gene;


public class Point implements Comparable<Point>{

	private int x;
	private int y;
	private int[] rgb = new int[3];
	
	public Point(int x, int y , int[] rgb) {
		super();
		this.x = x;
		this.y = y;
		this.rgb = rgb;
	}
		
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p, double dev) {
		super();
		this.x = (int) (p.getX()*dev);
		this.y = (int) (p.getY()*dev);
	}

	public void show () {
		System.out.println("x : "+x+" y : "+y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int[] getRgb() {
		return rgb;
	}

	public void setRgb(int[] rgb) {
		this.rgb = rgb;
	}
	
	/**
	 * calculate the distance between two points
	 * @param p1
	 * @return
	 */
	public double distance(Point p1) {
		double dis = Math.sqrt((x-p1.getX())*(x-p1.getX())+(y-p1.getY())*(y-p1.getY()));
		return dis;
	}
	
	public static double distance(Point p1, Point p2) {
		double dis = Math.sqrt((p2.getX()-p1.getX())*(p2.getX()-p1.getX())+(p2.getY()-p1.getY())*(p2.getY()-p1.getY()));
		return dis;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "] :(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")";
	}

	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		if(this.x>o.getX()) {
			return 1;
		}else if(this.x<o.getX()) {
			return -1;
		}else {
			if(this.y>o.getY()) {
				return 1;
			}else if(this.y<o.getY()) {
				return -1;
			}else {
				return 0;
			}
		}
	}
	/**
	 * change point to a byte array
	 * @return byte array
	 */
	public byte[] toByte() {
		 byte[] result = new byte[8];
		 result[0] = (byte) ((x >> 24) & 0xFF);  
		 result[1] = (byte) ((x >> 16) & 0xFF);  
		 result[2] = (byte) ((x >> 8) & 0xFF);  
		 result[3] = (byte) (x & 0xFF);
		 result[4] = (byte) ((y >> 24) & 0xFF);  
		 result[5] = (byte) ((y >> 16) & 0xFF);  
		 result[6] = (byte) ((y >> 8) & 0xFF);  
		 result[7] = (byte) (y & 0xFF); 
		 return result;  
	}
	/**
	 * change byte array to point
	 * @param bytes
	 * @return point
	 */
	public static Point toPoint(byte[] bytes) {
		int x = 0;
		int y = 0;
		for (int i = 0; i < 4; i++) {
			int shift = (4-1-i)*8;
			x += (bytes[i] & 0x000000FF)<<shift;
		}
		for (int i = 0; i < 4; i++) {
			int shift = (4-1-i)*8;
			y += (bytes[i+4] & 0x000000FF)<<shift;
		}
		return new Point(x,y);
	}

}
