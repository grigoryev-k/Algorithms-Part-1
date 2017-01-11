import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int         size;
    private int         numSites;
    private int         tr;
    private double[]    thresholds;
    private double      meanValue;
    private double      stdDeviation;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        tr = trials;
        thresholds = new double[tr];
        numSites = size * size;
        performTrials();
    }

    private void performTrials() {
        for (int i = 0; i < tr; i++) {
            Percolation perc = new Percolation(size);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, size + 1);
                int col = StdRandom.uniform(1, size + 1);
                perc.open(row, col);
            }
            thresholds[i] = (perc.numberOfOpenSites() * 1.0) / numSites;
        }
        meanValue = StdStats.mean(thresholds);
        stdDeviation = StdStats.stddev(thresholds);
    }

    private void show() {
        System.out.printf("Mean = %f\n", mean());
        System.out.printf("Standart Deviation = %f\n", stddev());
        System.out.printf("95%% Confidence interval = %f , %f\n\n", confidenceLo(), confidenceHi());
    }

    public double mean() {
        // sample mean of percolation threshold
        return meanValue;
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return stdDeviation;
    }

    public double confidenceLo() {
        return (meanValue - (1.96 * stdDeviation / Math.sqrt(tr)));
    }

    public double confidenceHi() {
        return (meanValue + (1.96 * stdDeviation / Math.sqrt(tr)));
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong number of arguments");
        }
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        stats.performTrials();
        stats.show();
    }
}
