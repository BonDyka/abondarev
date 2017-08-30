package ru.job4j.coverage;

/**
 * The class represent object for searching max area coverage by 1 in square
 * matrix of integer filed 0 and 1.
 *
 * @author abondarev.
 * @since 30.08.2017.
 */
public class MaxCoverage {

    /**
     * It's matrix for computing max area coverage by 1.
     */
    private int[][] matrix;

    /**
     * Holds value of upper bound of matrix.
     */
    private int limit;

    /**
     * Contains information cell of matrix was check or not.
     */
    private boolean[][] checked;

    /**
     * The constructor takes as parameter square matrix and based on it construct
     * an instance.
     *
     * @param matrix it's matrix for computing max area coverage by 1.
     */
    public MaxCoverage(int[][] matrix) {
        this.matrix = matrix;
        this.limit = matrix.length;
        this.checked = new boolean[this.limit][this.limit];
    }

    /**
     * Returns value fo max area coverage.
     *
     * @return value fo max area coverage.
     */
    public int areaCoverage() {
        int area = 0;
        for (int i = 0; i < this.limit; i++) {
            for (int j = 0; j < this.limit; j++) {
                int result = countUnion(i, j);
                if (result > area) {
                    area = result;
                }
            }
        }
        return area;
    }

    /**
     * Takes coordinates of cell of matrix, checks nearby cell by contain 1 and
     * computes area for this union.
     *
     * @param i row coordinates.
     * @param j col coordinates.
     * @return value of area of the union.
     */
    private int countUnion(int i, int j) {
        int result = 0;
        if (i >= 0 && j >= 0 && i < this.limit && j < this.limit) {
            if (!this.checked[i][j]) {
                this.checked[i][j] = true;
                if (this.matrix[i][j] == 1) {
                    result++;
                    result += countUnion(i - 1, j);
                    result += countUnion(i, j - 1);
                    result += countUnion(i + 1, j);
                    result += countUnion(i, j + 1);
                }
            }
        }
        return result;
    }
}
