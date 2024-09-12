import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    // Хранение телефонных номеров по фамилии
    private Map<String, List<String>> phoneBook;

    // Конструктор
    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    // Метод для добавления записи
    public void add(String lastName, String phoneNumber) {
        // Получаем список номеров телефонов для  фамилии
        List<String> numbers = phoneBook.getOrDefault(lastName, new ArrayList<>());
        // Добавляем новый номер в список
        numbers.add(phoneNumber);
        // Сохраняем обновленный список в телефонный справочник
        phoneBook.put(lastName, numbers);
    }

    // Метод для получения номеров телефонов по фамилии
    public List<String> get(String lastName) {
        return phoneBook.getOrDefault(lastName, new ArrayList<>());
    }
}
