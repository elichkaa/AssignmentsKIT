/*
 * @author ufszm
 */

public class MagicSquare {
    private static final int THIRD_POWER = 3;
    private final int[][] square;
    private final int magicNumber;

    public MagicSquare(int[][] square) {
        this.square = square;
        this.magicNumber = calculateMagicNumber(square.length);
    }

    public static String showMagicNumbers(int k) {
        int[] magicNumbers = new int[k];
        for (int i = 1; i <= k; i++) {
            magicNumbers[i - 1] = calculateMagicNumber(i);
        }
        return createMagicNumbersString(magicNumbers);
    }

    public boolean isMagicSquare() {
        return hasMagicRowsAndColumns() && hasMagicDiagonals();
    }

    public boolean isSemimagicSquare() {
        return hasMagicRowsAndColumns() && !hasMagicDiagonals();
    }

    public void complement() {
        for (int i = 0; i < this.square.length; i++) {
            for (int j = 0; j < this.square.length; j++) {
                int complementTerm = (int) Math.pow(this.square.length, 2) + 1;
                this.square[i][j] = complementTerm - this.square[i][j];
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.square.length; i++) {
            for (int j = 0; j < this.square.length; j++) {
                sb.append(this.square[i][j]);
                if (j != this.square.length - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    private static int calculateMagicNumber(int n) {
        return (int) (Math.pow(n, THIRD_POWER) + n) / 2;
    }

    private static String createMagicNumbersString(int[] magicNumbers) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < magicNumbers.length - 1; i++) {
            output.append(magicNumbers[i]).append(",");
        }
        output.append(magicNumbers[magicNumbers.length - 1]);
        return output.toString();
    }

    private boolean hasMagicRowsAndColumns() {
        int rowsSum = 0;
        int columnsSum = 0;
        for (int i = 0; i < this.square.length; i++) {
            for (int j = 0; j < this.square.length; j++) {
                rowsSum += this.square[i][j];
                columnsSum += this.square[j][i];
            }
            if (rowsSum != this.magicNumber || columnsSum != this.magicNumber) {
                return false;
            }
            rowsSum = 0;
            columnsSum = 0;
        }
        return true;
    }

    private boolean hasMagicDiagonals() {
        int leftDiagonalSum = 0;
        int rightDiagonalSum = 0;
        for (int i = 0; i < this.square.length; i++) {
            for (int j = 0; j < this.square.length; j++) {
                //left diagonal
                if (j == i) {
                    leftDiagonalSum += this.square[i][j];
                }
                //right diagonal
                if (i + j == this.square.length - 1) {
                    rightDiagonalSum += this.square[i][j];
                }
            }
        }
        return leftDiagonalSum == rightDiagonalSum && leftDiagonalSum == this.magicNumber;
    }
}
