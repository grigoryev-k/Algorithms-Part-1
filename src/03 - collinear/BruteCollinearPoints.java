import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {
	private Point[] ps;
	private List<LineSegment> segs;
	
	public BruteCollinearPoints(Point[] points) {
		if (points == null) {
			throw new NullPointerException();
		}
		ps = points;
		segs = new ArrayList<>();
		
	}

	public int numberOfSegments() {
		
	}
	
	public LineSegment[] segments() {
		
	}
}
