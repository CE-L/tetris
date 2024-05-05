package enums;

public enum Difficulty {
    EASY(1),
    NORMAL(2),
    HARD(3),
    PRO(4);

    private int num;
    Difficulty(int n) {
        this.num = n;
    }
}
