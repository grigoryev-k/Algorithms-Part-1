package collinear;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
	// private Point[] ps;
	private List<LineSegment> segs;

	public BruteCollinearPoints(Point[] points) {
		if (points == null) {
			throw new NullPointerException();
		}
		// ps = points;
		segs = new ArrayList<>();

	}

	public int numberOfSegments() {
		return segs.size();
	}

	public LineSegment[] segments() {
		return segs.toArray(new LineSegment[segs.size()]);
	}

	public static void choose(int n, int k) {
		List<Integer[]> l = new ArrayList<>();
		Integer[] combination = new Integer[k];

		for (int i = 0; i < k; i++) {
			combination[i] = i + 1;
		}
		System.out.println(Arrays.toString(combination));
		int count = 1;
		while (combination[0] < (n - k + 1)) {
			if (combination[k - 1] < n) {
				combination[k - 1]++;
			} else {
				int value = 0;
				for (int j = k - 2; j >= 0; j--) {
					if (combination[j] < (combination[j + 1] - 1)) {
						value = combination[j];
						for (int b = j; b < k; b++) {
							combination[b] = ++value;
						}
						break;
					}
				}
			}
			count++;
			System.out.println(Arrays.toString(combination));
		}
		System.out.println("Count: " + count + " Comb: " + comb(n, k));
		// for (Integer[] row : l) {
		// System.out.println(Arrays.toString(row));
		// }

	}

	public static long comb(int n, int k) {
	    return fact(n) / (fact(k) * fact(n - k));
	}

	public static long fact(long n) {
	    if (n == 1) {
	        return 1;
        }
        else {
	        return n * fact(n - 1);
        }
    }
	public static void main(String[] args) {
		choose(7, 3);
	}
}
