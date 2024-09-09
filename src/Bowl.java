public class Bowl {
    private int food;

    public Bowl(int foodAmount) {
        this.food = Math.max(0, foodAmount); // Количество еды не может быть отрицательным
    }

    public int getFood() {
        return food;
    }

    public void reduceFood(int amount) {
        food = Math.max(0, food - amount);
    }

    public void addFood(int amount) {
        food += amount;
        System.out.println("В миску добавлено " + amount + " еды. Всего еды в миске: " + food);
    }
}
