package com.github.washappy.character;


import com.github.washappy.enums.Difficulty;

public abstract class AbstractCharacter {
    protected String name;
    protected String description;

    protected String skillName;
    protected String skillDescription;
    protected int coolTime;
    protected Difficulty skillDifficulty;

    public abstract void skill();
}