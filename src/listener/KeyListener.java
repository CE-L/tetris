package listener;

import dataStructure.LimitedQueue;
import enums.Screens;
import main.Music;
import screen.Screen;
import tetris.AbstactMino;
import tetris.Board;
import tetris.Minos;
import tetris.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static screen.Screen.NOWPLAYER;

public class KeyListener extends KeyAdapter {

    private Player player;
    private Board board;

    @Override
    public void keyPressed(KeyEvent event){
        if (Screen.game == null || Screen.getWhatScreen().inGame==0) {
            return;
        }
        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                new Music("rotate.mp3",false).start();

            }
            case KeyEvent.VK_RIGHT -> {
                new Music("rotate.mp3",false).start();
            }
            case KeyEvent.VK_UP -> {
                new Music("rotate.mp3",false).start();
            }
            case KeyEvent.VK_DOWN -> {
                new Music("rotate.mp3",false).start();
            }
            case KeyEvent.VK_SHIFT -> {

            }
            case KeyEvent.VK_SPACE -> {

            }
            case KeyEvent.VK_Z -> {
                new Music("rotate.mp3",false).start();
            }
            case KeyEvent.VK_A -> {

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent event){
        if (Screen.game == null) {
            return;
        }
        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {

            }
            case KeyEvent.VK_RIGHT -> {

            }
            case KeyEvent.VK_UP -> {

            }
            case KeyEvent.VK_DOWN -> {

            }
            case KeyEvent.VK_SHIFT -> {

            }
            case KeyEvent.VK_SPACE -> {

            }
            case KeyEvent.VK_Z -> {

            }
            case KeyEvent.VK_A -> {

            }
        }
    }
    public KeyListener(){
        this.player = NOWPLAYER;
        this.board = player.getField();
    }
}
