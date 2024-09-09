public class Main {
    public static void main(String[] args) {
        // Задача 1
        Dog dog1 = new Dog("Снежок");
        Dog dog2 = new Dog("Беляш");
        Cat cat1 = new Cat("Зефир");
        Cat cat2 = new Cat("Персик");

        //Тестирование способностей к бегу и плаванию
        dog1.run(400); // Собака пытается пробежать 400 метров
        dog1.swim(7);  // Собака пытается проплыть 7 метров

        dog2.run(700); // Собака пытается пробежать 700 метров
        dog2.swim(40);  // Собака пытается проплыть 40 метров

        cat1.run(80); // Кот пытается пробежать 80 метров
        cat1.swim(50);  // Кот пытается плавать (должен вывести, что не может плавать)

        cat2.run(420); // Кот пытается пробежать 420 метров (не должен пробежать так далеко)
        cat2.swim(100); // Кот пытается плавать (должен вывести, что не может плавать)

        // Миска с едой
        Bowl bowl = new Bowl(6);

        // Коты кушают из миски
        cat1.eat(bowl);
        cat2.eat(bowl);

        // Выводим информацию о сытости котов
        System.out.println("Кот " + cat1.getName() + " сытый: " + cat1.isFull());
        System.out.println("Кот " + cat2.getName() + " сытый: " + cat2.isFull());

        // Добавляем еду в миску
        bowl.addFood(20);

        // Коты пытаются покушать снова
        cat1.eat(bowl);
        cat2.eat(bowl);

        // Выводим информацию о сытости котов
        System.out.println("Кот " + cat1.getName() + " сытый: " + cat1.isFull());
        System.out.println("Кот " + cat2.getName() + " сытый: " + cat2.isFull());

        // Выводим общее количество животных
        System.out.println("Общее количество животных:  " + Animal.getCount());

        // Задача 2. Примеры создания фигур
        Shape circle = new Circle(9, "розовый", "зелёный");
        Shape rectangle = new Rectangle(8, 4, "жёлтый", "оранжевый");
        Shape triangle = new Triangle(7, 7, 8, "фиолетовый", "чёрный");

        // Вывод характеристик фигур в консоль
        System.out.println("\nДанные о круге:");
        circle.printCharacteristics();
        System.out.println("\nДанные о прямоугольнике:");
        rectangle.printCharacteristics();
        System.out.println("\nДанные о треугольнике:");
        triangle.printCharacteristics();
    }
}