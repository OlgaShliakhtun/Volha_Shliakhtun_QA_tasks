public class Cat extends Animal {
    static int catCount = 0; // Счетчик котов
    private String name;
    private boolean isFull;

    public Cat(String name) {
        super();
        this.name = name;
        this.isFull = false; // Изначально кот голоден
        catCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= 200) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }

    public void eat(Bowl bowl) {
        if (bowl.getFood() >= 10) {
            bowl.reduceFood(10); // Предполагается, что коту нужно 10 единиц еды
            isFull = true;
            System.out.println(name + " покушал из миски.");
        } else {
            System.out.println(name + " не может покушать, в миске недостаточно еды.");
        }
    }

    public boolean isFull() {
        return isFull;
    }

    public String getName() {
        return name;
    }
}
