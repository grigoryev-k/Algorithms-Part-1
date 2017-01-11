import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF topConnections;
    private WeightedQuickUnionUF bottomConnections;
    private int                  size;
    private boolean[]            openSites;
    private boolean              percolationFlag;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be greater than zero");
        }
        size = n;
        topConnections = new WeightedQuickUnionUF(size * size + 1);
        bottomConnections = new WeightedQuickUnionUF(size * size + 1);
        openSites = new boolean[size * size + 1];
        percolationFlag = false;
    }

    private void check(int row, int col) {
        if ((row < 1) || (row > size) || (col < 1) || (col > size)) {
            throw new IndexOutOfBoundsException("row is " + row + ", col is " + col + ", size is " + size);
        }
    }

    private int getSite(int row, int col) {
        check(row, col);
        return (row - 1) * size + col;
    }

    private boolean connect(int fRow, int fCol, int sRow, int sCol) {
        if (isOpen(fRow, fCol) && isOpen(sRow, sCol)) {
            topConnections.union(getSite(fRow, fCol), getSite(sRow, sCol));
            bottomConnections.union(getSite(fRow, fCol), getSite(sRow, sCol));
            return true;
        }
        return false;
    }

    private boolean connectLeft(int row, int col) {
        return connect(row, col, row, col - 1);
    }

    private boolean connectRight(int row, int col) {
        return connect(row, col, row, col + 1);
    }

    private boolean connectUpper(int row, int col) {
        return connect(row, col, row - 1, col);
    }

    private boolean connectBottom(int row, int col) {
        return connect(row, col, row + 1, col);
    }

    public void open(int row, int col) {

        check(row, col);
        if (!isOpen(row, col)) {
            int site = getSite(row, col);
            openSites[site] = true;

            if (size == 1) {
                topConnections.union(0, site);
                percolationFlag = true;
                return;
            }

            boolean inLeftMostColumn = (col == 1);
            boolean inRightMostColumn = (col == size);
            boolean inFirstRow = (row == 1);
            boolean inLastRow = (row == size);

            if (inFirstRow) {
                if (inLeftMostColumn) {
                    connectBottom(row, col);
                    connectRight(row, col);
                } else if (inRightMostColumn) {
                    connectBottom(row, col);
                    connectLeft(row, col);
                } else {
                    connectBottom(row, col);
                    connectLeft(row, col);
                    connectRight(row, col);
                }
                topConnections.union(0, site);
            } else if (inLastRow) {
                if (inLeftMostColumn) {
                    connectUpper(row, col);
                    connectRight(row, col);
                } else if (inRightMostColumn) {
                    connectUpper(row, col);
                    connectLeft(row, col);
                } else {
                    connectUpper(row, col);
                    connectLeft(row, col);
                    connectRight(row, col);
                }
                bottomConnections.union(0, site);
            } else {
                if (inLeftMostColumn) {
                    connectUpper(row, col);
                    connectBottom(row, col);
                    connectRight(row, col);
                } else if (inRightMostColumn) {
                    connectUpper(row, col);
                    connectBottom(row, col);
                    connectLeft(row, col);
                } else {
                    connectUpper(row, col);
                    connectBottom(row, col);
                    connectRight(row, col);
                    connectLeft(row, col);
                }
            }
            if (topConnections.connected(0, site) && bottomConnections.connected(0, site)) {
                percolationFlag = true;
            }
        }
    }

    public boolean isOpen(int row, int col) {
        check(row, col);
        return openSites[getSite(row, col)];
    }

    public boolean isFull(int row, int col) {
        check(row, col);
        int site = getSite(row, col);
        return topConnections.connected(0, site);
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (boolean b : openSites) {
            if (b) {
                count++;
            }
        }
        return count;
    }

    public boolean percolates() {
        return percolationFlag;
    }
}
