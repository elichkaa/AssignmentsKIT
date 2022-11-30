/*
 * @author ufszm
 */

public class Main {
    public static void main(String[] args) {
        String methodName = args[0];
        String parameters = args[1];

        switch (methodName) {
            case "showMagicNumbers" -> {
                int k = Integer.parseInt(parameters);
                String magicNumbers = MagicSquare.showMagicNumbers(k);
                print(magicNumbers);
            }
            case "isMagicSquare?" -> {
                MagicSquare square = createMatrix(parameters);
                if (square.isMagicSquare()) {
                    print("magic square");
                } else if (square.isSemimagicSquare()) {
                    print("semimagic square");
                } else {
                    print("not magical");
                }
            }
            case "complement" -> {
                MagicSquare square = createMatrix(parameters);
                square.complement();
                print(square.toString());
            }
            default -> throw new IllegalArgumentException("Method does not exist.");
        }
    }

    private static MagicSquare createMatrix(String input) {
        int[] numberArrayFromInput = getMatrixElements(input);
        int matrixLength = (int) Math.sqrt(numberArrayFromInput.length);
        int[][] matrix = fillMatrix(numberArrayFromInput,matrixLength);
        return new MagicSquare(matrix);
    }


    //Converts the text input into integer array
    public static int[] getMatrixElements(String input) {
        String numbersWithSpaces = input.replaceAll("[>-]", " ")
                .replaceAll("<", "")
                .trim();
        String[] elements = numbersWithSpaces.split(" ");
        return parseStringArrayToIntArray(elements);
    }

    private static int[] parseStringArrayToIntArray(String[] elements){
        int[] elementsAsInts = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            elementsAsInts[i] = Integer.parseInt(elements[i]);
        }
        return elementsAsInts;
    }

    private static int[][] fillMatrix(int[] inputArray, int matrixLength){
        int counter = 0;
        int[][] matrix = new int[matrixLength][matrixLength];
        for (int i = 0; i < matrixLength; i++) {
            for (int j = 0; j < matrixLength; j++) {
                matrix[i][j] = inputArray[j + counter];
            }
            counter += matrixLength;
        }
        return matrix;
    }

    //In order to improve code readability
    private static void print(String output) {
        System.out.println(output);
    }
}
