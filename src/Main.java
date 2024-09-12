import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Задача 1
        // Создаем массив слов (с повторениями)
        String[] wordsArray = {
                "помидор", "огурец", "ветчина", "сыр", "укроп", "сыр", "капуста",
                "чеснок", "сыр", "кукуруза", "капуста", "горошек", "сыр", "кукуруза",
                "укроп", "кукуруза", "чеснок", "кукуруза"
        };

        // Создаем экземпляр класса WordCounter
        WordCounter wordCounter = new WordCounter();

        // Подсчитываем уникальные слова
        Map<String, Integer> wordCount = wordCounter.countWords(wordsArray);

        // Выводим список уникальных слов и их количество
        System.out.println("Уникальные слова и их количество:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Задача 2
        // Создаем экземпляр телефонного справочника
        PhoneBook phoneBook = new PhoneBook();

        // Добавляем записи
        phoneBook.add("Бондарева", "+375 29 579-36-56");
        phoneBook.add("Троцкий", "+375 29 572-05-87");
        phoneBook.add("Вольпова", "+375 29 376-05-83");
        phoneBook.add("Троцкий", "+375 44 557-89-21");
        phoneBook.add("Вольпова", "+375 25 678-24-32");
        phoneBook.add("Потепко", "+375 29 527-05-23");
        phoneBook.add("Вольпова", "+375 29 395-83-65");
        phoneBook.add("Мельник", "+375 29 300-58-77");
        phoneBook.add("Анацко", "+375 25 629-65-34");
        phoneBook.add("Королинский", "+375 29 345-76-52");

        // Получаем и отображаем номера телефонов,которые соответствуют фамилиям
        System.out.println("Номера телефонов для фамилии Бондарева: " + phoneBook.get("Бондарева"));
        System.out.println("Номера телефонов для фамилии Троцкий: " + phoneBook.get("Троцкий"));
        System.out.println("Номера телефонов для фамилии Вольпова: " + phoneBook.get("Вольпова"));
        System.out.println("Номера телефонов для фамилии Даниленко: " + phoneBook.get("Даниленко"));
        // "Даниленко" нет в справочнике
        System.out.println("Номера телефонов для фамилии Потепко: " + phoneBook.get("Потепко"));
        System.out.println("Номера телефонов для фамилии Мельник: " + phoneBook.get("Мельник"));
        System.out.println("Номера телефонов для фамилии Анацко: " + phoneBook.get("Анацко"));
        System.out.println("Номера телефонов для фамилии Королинский: " + phoneBook.get("Королинский"));
    }
}