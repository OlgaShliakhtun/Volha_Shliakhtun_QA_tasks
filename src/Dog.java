public class Dog extends Animal {
    static int dogCount = 0; // Счетчик собак
    private String name;

    public Dog(String name) {
        super();
        this.name = name;
        dogCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= 500) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= 10) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м.");
        }
    }

    public String getName() {
        return name;
    }
}
