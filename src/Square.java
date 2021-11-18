public class Square {
    private final String name;
    private int cost;
    private int index;
    private int rent;
    private Player owner;

    public Square(String name, int cost, int index, int rent) {
        this.name = name;
        this.cost = cost;
        this.index = index;
        this.owner = null;
        this.rent = rent;
    }

    public Square(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getRent() {
        return rent;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Square{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", rent=" + rent +
                ", owner=" + (owner != null ? owner.getName() : null) +
                ", owner-money=" + (owner != null ? owner.getMoney() : null) +
                '}';
    }
}
