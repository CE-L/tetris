package com.github.washappy.character;

import com.github.washappy.character.AbstractCharacter;
import com.github.washappy.enums.Difficulty;

public class ExampleCharacter extends AbstractCharacter {

    public ExampleCharacter() {
        this.name = "example";
        this.description = "example description";

        this.skillName = "example skill";
        this.skillDescription = "example skill description";
        this.coolTime = 10;
        this.skillDifficulty = Difficulty.NORMAL;
    }


    @Override
    public void skill() {
        //example
    }
}
