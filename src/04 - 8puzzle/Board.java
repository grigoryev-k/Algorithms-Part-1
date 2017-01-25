public class Board {
    private Board goal;
    private int[][] board;
    private int moves;
    private int dim;

    public Board(int[][] blocks) {
	// construct a board from an n-by-n array of blocks
	assert blocks[0].length == blocks.length;
	board = blocks;
	dim = board.length;
    }

    // (where blocks[i][j] = block in row i, column j)
    public int dimension() {
	// board dimension n
	return dim;
    }

    public int hamming() {
	// number of blocks out of place
	int num = 0;
	for (int i = 1; i < (dim * dim); i++) {
	    int row = (i - 1) / dim;
	    int col = (i - 1) - (row * dim);
	    if (i != board[row][col])
		++num;
	}
	return num + moves;
    }

    public int manhattan() {
	int num = 0;
	// int[] m = new int[dim * dim - 1];
	for (int i = 0; i < dim; i++) {
	    for (int j = 0; j < dim; j++) {
		int cur = board[i][j];
		if (cur == 0)
		    continue;
		int row = (cur - 1) / dim;
		int col = (cur - 1) - (row * dim);
		int tmp = Math.abs(row - i) + Math.abs(col - j);
		// m[cur - 1] = tmp;
		num += tmp;
	    }
	}
	// for (int i : m) {
	// System.out.print(i + " ");
	// }
	// System.out.println();
	return num;
	// sum of Manhattan distances between blocks and goal

    }

    public boolean isGoal() {
	return false;
	// is this board the goal board?

    }

    public Board twin() {
	return goal;
	// a board that is obtained by exchanging any pair of blocks
    }

    public boolean equals(Object y) {
	return false;
	// does this board equal y?
    }

    public Iterable<Board> neighbors() {
	return null;
	// all neighboring boards
    }

    public String toString() {
	long maxDigits = Math.round(Math.log10(dim * dim));
	StringBuilder sb = new StringBuilder();
	sb.append(dim + System.getProperty("line.separator"));
	for (int[] row : board) {
	    // sb.append(Arrays.toString(row));
	    for (int block : row) {
		sb.append(String.format("%" + maxDigits + "d ", block));
	    }
	    sb.append(System.getProperty("line.separator"));
	}

	return new String(sb);
	// string representation of this board (in the output format specified
	// below)
    }

    public static void main(String[] args) {
	// unit tests (not graded)
	int[][] blocks = new int[][] { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };
	Board b = new Board(blocks);
	System.out.println(b.hamming());
	System.out.println(b.manhattan());
	System.out.println(b.toString());
	// b.getGoalBoard(b.dimension()).toString();
    }
}
