package character;

import enums.Difficulty;

public class ExampleCharacter extends AbstractCharacter{

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
