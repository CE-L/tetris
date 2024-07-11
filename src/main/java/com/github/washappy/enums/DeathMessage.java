package com.github.washappy.enums;

import java.util.Random;

public enum DeathMessage {

    A("그거 그렇게 하는 거 아닌데..."),
    B("소.. 솔직히.. 이것도 못 깨면 안된다고 생각해요..."),
    C("설마 죽어서 이 메세지를 보는 사람이 있을까요..?"),
    D("깨비 아깝닭"),
    E("혹시.. 손가락이 잘렸냐....?"),
    F("ㅋ"),
    ;

    private static final Random random = new Random();
    private String message;

    private DeathMessage(String m) {
        this.message = m;
    }

    public String getMessage() {
        return message;
    }

    public DeathMessage getRandom() {
        DeathMessage[] deathMessages = values();
        return deathMessages[random.nextInt(deathMessages.length)];
    }
}
