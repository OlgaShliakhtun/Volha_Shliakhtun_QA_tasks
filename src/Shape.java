public interface Shape {
    double getArea(); // Метод для расчета площади

    double getPerimeter(); // Метод для расчета периметра

    String getFillColor(); // Метод для получения цвета заливки

    String getBorderColor(); // Метод для получения цвета границы

    // Дефолтный метод для расчета периметра
    default double calculatePerimeter() {
        return getPerimeter();
    }

    // Дефолтный метод для расчета площади
    default double calculateArea() {
        return getArea();
    }

    // Дефолтный метод для вывода характеристик фигуры
    default void printCharacteristics() {
        System.out.printf("Периметр: %.2f, Площадь: %.2f, Цвет фона: %s, Цвет границ: %s%n",
                calculateArea(), calculatePerimeter(), getFillColor(), getBorderColor());
    }
}
