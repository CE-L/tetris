package enums;

import java.util.Random;

public enum Row {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7),
    I(8),
    J(9),
    K(10),
    L(11),
    M(12),
    N(13),
    O(14),
    P(15),
    Q(16),
    R(17),
    S(18),
    T(19);

    private int number;

    Row(int n) {
        this.number = n;
    }

    public int getNumber() {
        return number;
    }

    private Row getRow(int n) {
        Row ret = null;
        ret = switch (n) {
            case 0 -> A;
            case 1 -> B;
            case 2 -> C;
            case 3 -> D;
            case 4 -> E;

            case 5 -> F;
            case 6 -> G;
            case 7 -> H;
            case 8 -> I;
            case 9 -> J;

            case 10 -> K;
            case 11 -> L;
            case 12 -> M;
            case 13 -> N;
            case 14 -> O;

            case 15 -> P;
            case 16 -> Q;
            case 17 -> R;
            case 18 -> S;
            case 19 -> T;
            default -> null;
        };
        return ret;
    }


    public Row getRandomRow() {
        Random random = new Random();
        return getRow(random.nextInt(20));
    }
}
