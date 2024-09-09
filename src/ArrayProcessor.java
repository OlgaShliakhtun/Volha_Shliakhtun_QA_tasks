public class ArrayProcessor {
    // Метод для обработки двумерного массива
    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        // Проверка на размер массива 4x4
        if (array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException("Размер массива должен быть 4x4.");
        }
        int sum = 0; // Переменная для хранения суммы элементов
        // Цикл по всем элементам массива
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    // Преобразование строки в целое число и добавление к сумме
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    // В случае ошибки преобразования выбрасывается исключение с информацией о ячейке
                    throw new MyArrayDataException("Ошибка преобразования в ячейке [" + i + "][" + j + "]: " + array[i][j]);
                }
            }
        }
        return sum; // Возвращение сумму элементов
    }
}
