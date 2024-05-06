package character;

import enums.Difficulty;

public abstract class AbstractCharacter {
    private String name;
    private String description;

    private String skillName;
    private String skillDescription;
    private int coolTime;
    private Difficulty skillDifficulty;
}