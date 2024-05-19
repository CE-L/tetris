package tetris;

import character.AbstractCharacter;
import damage.Damage;
import dataStructure.LimitedQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Player {

    private String name;
    private AbstractCharacter character;

    private Board field; //40,20
    private LimitedQueue<Minos> hold; //크기 1
    private Queue<Minos> next;
    private Minos now;


    private Damage recievedDamage;
    private Damage sendDamage;
    private Damage dangerDamge;

    public Player(String n,AbstractCharacter c) {
        this.name = n;
        this.character = c;
        /*if (this.character==) {

        }else*/
        this.field = new Board();
        this.hold = new LimitedQueue<>(1);

        this.next = new LinkedList<>();
        this.next.addAll(List.of(Minos.T.randomBag()));
        this.next.addAll(List.of(Minos.T.randomBag()));

        this.now = this.next.remove();
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

    public Minos getNow() {
        return now;
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
}
