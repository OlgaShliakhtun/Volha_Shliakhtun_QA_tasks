// Создание пользовательского исключения для ошибок преобразования данных массива
public class MyArrayDataException extends Exception {
    public MyArrayDataException(String message) {
        super(message);
    }
}
