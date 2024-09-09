public class Main {
    public static void main(String[] args) {
        // Задача 1
        printThreeWords();

        // Задача 2
        checkSumSign();

        // Задача 3
        printColor();

        // Задача 4
        compareNumbers();

        // Задача 5
        System.out.println(isSumInRange(27, 3));

        // Задача 6
        printNumberSign(-9);

        // Задача 7
        System.out.println(isNegative(-5));

        // Задача 8
        printStringMultipleTimes("GitHub — это облачная платформа для хостинга IT-проектов и совместной разработки.", 3);

        // Задача 9
        System.out.println(isLeapYear(2024));

        // Задача 10
        // Инициализация массива с элементами 0 и 1
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.print("Исходный массив: [");
        printArray(array);
        System.out.println("]");
        swapValues(array);
        System.out.print("Преобразованный массив: [");
        printArray(array);
        System.out.println("]");

        // Задача 11
        // Создаем пустой целочисленный массив длиной 100
        int[] array1 = new int[100];
        // Вызываем метод для заполнения массива значениями от 1 до 100
        fillArray(array1);
        // Выводим заполненный массив
        System.out.print("Заполненный массив: [");
        printArray(array1);
        System.out.println("]");

        // Задача 12
        int[] array2 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.print("Исходный массив: [");
        printArray(array2);
        System.out.println("]");
        // Вызов метода для умножения чисел меньше 6 на 2
        multiplyLessThanSix(array);
        // Вывод массива после преобразования
        System.out.print("Преобразованный массив: [");
        printArray(array2); // Вызов метода для печати массива
        System.out.println("]");

        // Задача 13
        int size = 7; // Размер квадратного массива
        int[][] array3 = new int[size][size]; // Создаем квадратный массив
        fillDiagonal(array3); // Заполняем диагональные элементы единицами
        printDoubleArray(array3); // Выводим массив на экран

        // Задача 14
        int len = 6;
        int initialValue = 8;
        int[] resultArray = createArray(len, initialValue);
        System.out.println(java.util.Arrays.toString(resultArray));
    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
        int a = 9;
        int b = -7;
        int sum = a + b;


        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {
        int value = 91; // Задаем любое значение для переменной value

        if (value <= 0) {
            System.out.println("Красный");
        } else if ( value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        int a = 76; // Объявление переменной a
        int b = 35;  // Объявление переменной b
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    public static void printNumberSign(int number) {
        if (number >= 0) {
            System.out.println("Положительное число");
        } else {
            System.out.println("Отрицательное число");
        }
    }

    public static boolean isNegative(int number) {
        return number < 0;
    }

    public static void printStringMultipleTimes(String str, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void swapValues(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1; // Если элемент равен 0, заменяем его на 1
            } else {
                array[i] = 0; // Если элемент равен 1, заменяем его на 0
            }
        }
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
    }

    public static void fillArray(int[] array) {
        // Заполняем массив значениями от 1 до 100
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1; // Заполняем массив значениями от 1 до 100
        }
    }

    // Метод для умножения чисел, меньше 6, на 2
    public static void multiplyLessThanSix(int[] array) {
        for (int i = 0; i < array.length; i++) { // Проходим по всем элементам массива
            if (array[i] < 6) { // Проверяем, меньше ли элемент 6
                array[i] *= 2; // Умножаем элемент на 2
            }
        }
    }

    public static void fillDiagonal(int[][] array) {
        for (int i = 0; i < array.length; i++) { // Проходим по строкам
            array[i][i] = 1; // Устанавливаем элемент главной диагонали в 1
            array[i][array.length - 1 - i] = 1; // Устанавливаем элемент побочной диагонали в 1
        }
    }
    public static void printDoubleArray(int[][] array) {
        for (int i = 0; i < array.length; i++) { // Проходим по строкам
            for (int j = 0; j < array[i].length; j++) { // Проходим по столбцам
                System.out.print(array[i][j] + " "); // Печатаем элемент с пробелом
            }
            System.out.println(); // Печатаем новую строку после каждой строки массива
        }
    }
    public  static int[] createArray(int len, int initialValue) {
        // Проверка на положительную длину массива
        if (len < 0) {
            throw new IllegalArgumentException("Length must be non-negative");
        }
        // Создание массива указанной длины
        int[] array = new int[len];
        // Заполнение массива значением initialValue
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        return array;
    }
}