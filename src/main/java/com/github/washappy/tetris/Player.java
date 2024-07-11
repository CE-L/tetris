package com.github.washappy.tetris;


import com.github.washappy.character.AbstractCharacter;
import com.github.washappy.damage.Damage;
import com.github.washappy.dataStructure.LimitedQueue;
import com.github.washappy.enums.Screens;
import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.panels.FourtyLinePanel;
import com.github.washappy.tetris.mino.AbstactMino;
import com.github.washappy.tetris.mino.Minos;
import com.github.washappy.tetris.mino.NowMino;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Player {

    private String name;
    private AbstractCharacter character;

    private Board field; //40,10
    private LimitedQueue<Minos> hold; //크기 1
    private Queue<Minos> next;

    private boolean isHolded = false;


    private Damage recievedDamage;
    private Damage sendDamage;
    private Damage dangerDamge;

    public NowMino ghost;
    public AbstactMino[] ghostMinos=new AbstactMino[4];

    public Player(String n, AbstractCharacter c) {
        this.name = n;
        this.character = c;
        /*if (this.com.github.washappy.com.github.washappy.character==) {

        }else*/
        this.field = new Board();
        this.hold = new LimitedQueue<>(1);

        this.next = new LinkedList<>();
        this.next.addAll(List.of(Minos.T.randomBag()));
        this.next.addAll(List.of(Minos.T.randomBag()));

        getNextMino();
    }

    public String getName() {
        return name;
    }

    public AbstractCharacter getCharacter() {
        return character;
    }

    public Board getField() {
        return field;
    }

    public LimitedQueue<Minos> getHold() {
        return hold;
    }

    public Queue<Minos> getNext() {
        return next;
    }

    public Damage getRecievedDamage() {
        return recievedDamage;
    }

    public Damage getSendDamage() {
        return sendDamage;
    }

    public Damage getDangerDamge() {
        return dangerDamge;
    }

    public boolean hold() {
        if (hold.isEmpty()) {
            hold.add(field.getNowMino().getMino());
            field.nowDelete();

            Minos nextMino = next.remove();
            field.setNowMino(new NowMino(nextMino));
            if (next.size()<=7) {
                next.addAll(List.of(Minos.T.randomBag()));
            }
            field.nowMinoSummon(nextMino,0);
            isHolded = true;
            return true;
        } else {
            if (!isHolded) {
                field.nowDelete();

                Minos wasHold = hold.remove();
                hold.add(field.getNowMino().getMino());
                field.setNowMino(new NowMino(wasHold));

                field.nowDelete();
                field.nowMinoSummon(field.getNowMino());

                isHolded = true;
                return true;
            }
            return false;
        }
    }

    public void getNextMino() {
        field.setNowMino(new NowMino(next.remove()));
        if (next.size()<=7) {
            next.addAll(List.of(Minos.T.randomBag()));
        }
        field.nowMinoSummon(field.getNowMino());
    }

    public void drop() {
        int clearedLine = field.drop();
        if (Navigator.INSTANCE.getCurrentScreen()== Screens.FOURTY_LINE) {
            ((FourtyLinePanel)Navigator.INSTANCE.getCurrentPanel()).clearedLine += clearedLine;
            if (((FourtyLinePanel) Navigator.INSTANCE.getCurrentPanel()).clearedLine >=40) {
                ((FourtyLinePanel) Navigator.INSTANCE.getCurrentPanel()).totalTime = System.currentTimeMillis() - ((FourtyLinePanel) Navigator.INSTANCE.getCurrentPanel()).startTime;
                ((FourtyLinePanel)Navigator.INSTANCE.getCurrentPanel()).success();
            }
        }
        getNextMino();
        isHolded = false;
    }

    public void reset(){
        field = new Board();
    }
}
