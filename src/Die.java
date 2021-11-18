import java.util.Random;

public class Die {
    private int faceValue;
    private final Random random = new Random();

    public int getFaceValue() {
        throwDie();
        return faceValue;
    }

    private void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    private void throwDie () {
        int MIN = 1;
        int MAX = 6;
        setFaceValue(random.nextInt(MAX) + MIN);
    }

    @Override
    public String toString() {
        return "Die{" +
                "faceValue=" + faceValue +
                '}';
    }
}
