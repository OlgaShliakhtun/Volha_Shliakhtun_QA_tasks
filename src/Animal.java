public class Animal {
    private static int count = 0;

    public Animal() {
        count++;
    }

    public static int getCount() {
        return count;
    }

    public void run(int distance) {
        throw new UnsupportedOperationException("Метод run должен быть переопределен в классе-наследнике");
    }

    public void swim(int distance) {
        throw new UnsupportedOperationException("Метод swim должен быть переопределен в классе-наследнике");
    }
}
