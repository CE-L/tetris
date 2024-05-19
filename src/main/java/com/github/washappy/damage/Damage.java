package com.github.washappy.damage;


import com.github.washappy.enums.Row;

import java.util.ArrayList;

public class Damage {
    private int amount;
    private ArrayList<Row> damage;

    public ArrayList<Row> addDamage() {
        for (int i=0;i<amount;i++){
            damage.add(Row.A.getRandomRow());
        }
        return damage;
    }

    public Damage(int a) {
        this.amount = a;
        this.damage = new ArrayList<>();
    }
}
