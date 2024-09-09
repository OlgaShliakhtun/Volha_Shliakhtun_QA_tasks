public class Main {
    public static void main(String[] args) {
        // Корректный массив 4x4
        String[][] correctArray = {
                {"32", "1", "76", "4"},
                {"41", "12", "14", "8"},
                {"63", "53", "1", "2"},
                {"34", "13", "19", "5"}
        };

        // Массив некорректного размера
        String[][] incorrectArraySize = {
                {"35", "36"},
                {"21", "20"}
        };

        // Массив имеет ячейку с неверными данными
        String[][] incorrectArrayData = {
                {"54", "61", "66", "1"},
                {"22", "33", "44", "58"},
                {"6", "2", "qa", "31"},
                {"2", "8", "98", "5"}
        };

        // Попытка обработки корректного массива и вывод результата
        try {
            System.out.println("Сумма элементов нормально заполненного массива: "
                    + ArrayProcessor.processArray(correctArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        // Попытка обработки массива некорректного размера и вывод результата
        try {
            System.out.println("Сумма элементов массива неправильного размера: "
                    + ArrayProcessor.processArray(incorrectArraySize));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        // Попытка обработки массива содержащего ячейку с неверными данными и вывод результата
        try {
            System.out.println("Сумма элементов массива с неправильными данными: "
                    + ArrayProcessor.processArray(incorrectArrayData));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }
}