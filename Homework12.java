import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

public class Homework12 {

private ArrayList<RectangularShape> rect;
	/* Finish the constructor and create any necessary instance
	 * variables. The constructor should create and save an
	 * ArrayList of RectangularShape values
	 */
	public Homework12() {
		rect = new ArrayList<RectangularShape>();
	}

	/* Adds the parameter s to the ArrayList
	 */
	public void addShape(RectangularShape s) {
			rect.add(s);
	}

	/* Returns the number of RectangularShape objects
	 * in the ArrayLists that intersect the
	 * parameter r
	 */
	public int problem1(Rectangle2D r) {
		int c = 0;
		for(RectangularShape recta: rect){
			if(recta.intersects(r)){
				c++;
			}
		}
		return c;
	}

	/* Returns the number of RectangularShape objects
	 * in the ArrayLists that do NOT contain the
	 * parameter p
	 */
	public int problem2(Point2D p) {
			int c = 0;
			for(int i=0; i<rect.size(); i++){
				if(rect.get(i).contains(p)==false){
					c++;
				}
			}
			return c;
	}

	/* Returns the number of Ellipse2D objects that
	 * are in the ArrayList
	 */

	public int problem3() {
			int c=0;
			for(RectangularShape r: rect){
				if (r instanceof Ellipse2D){
					c++;
				}
			}
			return c;
	}

	/* Returns true if any of the Rectangle2D objects
	 * in the ArrayList intersects the parameter Line2D
	 */
	public boolean problem4(Line2D l) {
			boolean c = false;
			ArrayList<Rectangle2D> rectan = new ArrayList<Rectangle2D>();
			for (int i = 0; i< rect.size(); i++){
				rectan.add(rect.get(i).getFrame());
			}
			for (Rectangle2D re: rectan){
				if (l.intersects(re)){
					c=true;
				}
			}
			return c;
	}

	/* Return the total area of the RectangularShape objects in
	 * the AraryList
	 * For this problem, you may assume that the only shapes
	 * that have area are rectangles and ellipses
	 * Note that not all ellipses are circles
	 * If two objects intersect, the intersected area
	 * counts for both (i.e. do not account for intersection)
	 */
	public double problem5() {
		double totalArea = 0;

		Rectangle2D r1 = new Rectangle2D.Double();
		Ellipse2D e1 = new Ellipse2D.Double();

		for(int i = 0; i<rect.size(); i++){
			RectangularShape r = rect.get(i);
			if(r1.getClass() == r.getClass()){
				totalArea = totalArea + r.getWidth() * r.getHeight();
			}
			if(e1.getClass() == r.getClass()){
				totalArea = totalArea + Math.PI * (r.getHeight()/2) * (r.getWidth()/2);
			}
		}
		return totalArea;
}
	public static void main(String[] args) {
		boolean passed = true;

		Homework12 hw12 = new Homework12();
		hw12.addShape(new Ellipse2D.Double(20, 20, 10, 10));
		hw12.addShape(new Rectangle2D.Double(30, 30, 20, 20));
		hw12.addShape(new Rectangle2D.Double(40, 20, 20, 40));

		Rectangle2D  r = new Rectangle2D.Double(25, 25, 10, 10);
		if (hw12.problem1(r) == 2) {
			System.out.println("Pass 1");
		} else {
			System.out.println("Fail 1");
			passed = false;
		}

		Point2D p = new Point2D.Double(45, 35);
		if (hw12.problem2(p) == 1) {
			System.out.println("Pass 2");
		} else {
			System.out.println("Fail 2");
			passed = false;
		}

		if (hw12.problem3() == 1) {
			System.out.println("Pass 3");
		} else {
			System.out.println("Fail 3");
			passed = false;
		}

		Line2D l1 = new Line2D.Double(0, 0, 10, 10);
		Line2D l2 = new Line2D.Double(35, 35, 45, 45);
		if (hw12.problem4(l1)) {
			System.out.println("Fail 4.1");
			passed = false;
		} else if (!hw12.problem4(l2)) {
			System.out.println("Fail 4.2");
			passed = false;
		} else {
			System.out.println("Pass 4");
		}

		if (closeEnough(hw12.problem5(), 1278.53981634)) {
			System.out.println("Pass 5");
		} else {
			System.out.println("Fail 5");
			passed = false;
		}

		if (passed) {
			System.out.println("All Pass");
		} else {
			System.out.println("Fail");
		}

	}

	private static boolean closeEnough(double a, double b) {
		return Math.abs(a - b) < 0.00001;
	}
}
