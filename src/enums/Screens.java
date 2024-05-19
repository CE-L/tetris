package enums;

public enum Screens {
    INTRO(0),
    SINGLE(0),
    MULTI(0),
    SETTING(0),
    CREDIT(0),

    FOURTYLINE(1),
    ONEMINUTE(1),
    PRATICE(1);

    public int inGame;

    Screens(int i) {
        this.inGame = i;
    }
}
