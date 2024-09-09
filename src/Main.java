public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
    }

    public static void task1() {
        Employee employee = new Employee("Котова Ольга Ивановна", "QA Automation", "kotova.ivan@gmail.com", "80296673257", 1500, 28);
        employee.printEmployeeInfo();
    }

    public static void task2() {
        Employee[] employeesArray = new Employee[5];
        employeesArray[0] = new Employee("Потепко Анатолий Николаевич", "QA Engineer Java", "potepko@gmail.com", "80295485367",1900, 29);
        employeesArray[1] = new Employee("Матюшев Максим Павлович", "QA Lead", "matyushev@gmail.com", "80295776542",2500, 35);
        employeesArray[2] = new Employee("Иванова Анна Викторовна", "QA Engineer Java", "ivanova@gmail.com", "80295899821",2400, 37);
        employeesArray[3] = new Employee("Белова Алина Сергеевна", "QA Lead", "belova@gmail.com", "80296654321",1500, 27);
        employeesArray[4] = new Employee("Свинтицкий Виктор Александрович", "QA Engineer Java", "svintitsky@gmail.com", "80296748680",4000, 43);
        // вывод массива в консоль
        for (Employee employee : employeesArray) {
            employee.printEmployeeInfo();
        }
    }

    public static void task3() {
// Пример использования класса Park
        Park сhelyuskintsevPark = new Park(3); // Создаем парк с местом для 3 аттракционов
        сhelyuskintsevPark.addAttraction("Колесо обозрения", "09:30 - 21:00", 15);
        сhelyuskintsevPark.addAttraction("Колобок", "10:30 - 21:00", 12);
        сhelyuskintsevPark.addAttraction("Американские горки", "11:30 - 21:00", 14);
// Отображение информацию обо всех аттракционах
        сhelyuskintsevPark.printAllAttractions();
    }
}