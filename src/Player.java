public class Player {
    private final String name;
    private int money;
    private boolean bankrupt;
    Piece piece;
    Die die1;
    Die die2;
    Board board;


    public Player(String name, Board board, Die one, Die two) {
        this.name = name;
        this.money = 1500;
        piece = new Piece(board.getSquare(0));
        this.die1 = one;
        this.die2 = two;
        this.board = board;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public void setBankrupt() {
        bankrupt = !bankrupt;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void getsPayed(int amount) {
        this.money += amount;
    }

    public void pay(int amount) {
        this.money -= amount;
    }

    public void move () {
        int currentPos = piece.getPosition().getIndex();
        int newPos = currentPos + throwDice();
        if (newPos >= 40) {
            int passedGoAmount = 200;
            if (newPos == 40) {
                passedGoAmount = 400;
            }
            getsPayed(passedGoAmount);
            System.out.printf("%s got %d$ for landing on/passing Go %n", getName(), passedGoAmount);
        }
        piece.setPosition(board.getSquare(newPos % 40));
    }

    private int throwDice() {
        return die1.getFaceValue() + die2.getFaceValue();
    }

    public void tryToBuy() {
        Square position = piece.getPosition();
        int price = position.getCost();
        if (money > price && position.getOwner() == null && !position.getName().equals("Go")) {
            pay(price);
            position.setOwner(this);
            System.out.printf("%s bought %s for %d$ %n", getName(), position.getName(), position.getCost());
        } else if (position.getOwner() != null && position.getOwner() != this) {
            int rent = position.getRent();
            pay(rent);
            position.getOwner().getsPayed(rent);
            System.out.printf("%s paid %d$ rent to %s %n", getName(), rent, position.getOwner().getName());
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", piece position=" + piece.getPosition().getIndex() +
                '}';
    }
}
