public class Park {
    // Внутренний класс "Attraction"
    class Attraction {
        private String name;
        private String workingHours;
        private double cost;

        // Конструктор класса Attraction
        public Attraction(String name, String workingHours, double cost) {
            this.name = name;
            this.workingHours = workingHours;
            this.cost = cost;
        }
        // Метод для вывода информации об аттракционе
        public void printAttractionInfo() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + cost);
            System.out.println(); // Для удобства добавляем пустую строку
        }
    }
    // Массив аттракционов
    private Attraction[] attractions;
    private int count; // Счётчик аттракционов
    // Конструктор класса Park
    public Park(int numberOfAttractions) {
        attractions = new Attraction[numberOfAttractions];
        count = 0; // Изначально 0 аттракционов
    }
    // Метод для добавления аттракциона в парк
    public void addAttraction(String name, String workingHours, double cost) {
        if (count < attractions.length) {
            attractions[count] = new Attraction(name, workingHours, cost);
            count++;
        } else {
            System.out.println("Ошибка: достигнуто максимальное количество аттракционов.");
        }
    }

    // Метод для вывода информации обо всех аттракционах
    public void printAllAttractions() {
        for (int i = 0; i < count; i++) {
            attractions[i].printAttractionInfo();
        }
    }
}
