import java.util.Arrays;

public class Board {
    Square[] squares = new Square[40];

    public Board() {
        squares[0] = new Square("Go");
        for (int i = 1; i < squares.length; i++) {
            squares[i] = new Square(String.format("Square #%d", i), (60 + 20 * ((i - 1)/2)), i, (10 + 5 * (i - 1)));
        }
    }

    public Square getSquare(int index) {
        for (Square square : squares) {
            if (index == square.getIndex()) return square;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Board{" +
                "squares=" + Arrays.toString(squares) +
                '}';
    }
}
