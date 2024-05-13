package character;

import enums.Difficulty;

public abstract class AbstractCharacter {
    protected String name;
    protected String description;

    protected String skillName;
    protected String skillDescription;
    protected int coolTime;
    protected Difficulty skillDifficulty;

    public abstract void skill();
}