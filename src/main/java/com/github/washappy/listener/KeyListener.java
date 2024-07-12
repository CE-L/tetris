package com.github.washappy.listener;



import com.github.washappy.Music;
import com.github.washappy.character.ExampleCharacter;
import com.github.washappy.enums.Direction;
import com.github.washappy.enums.Rotates;
import com.github.washappy.enums.Screens;
import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.panels.FourtyLinePanel;
import com.github.washappy.tetris.Player;
import com.github.washappy.tetris.mino.Move;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.github.washappy.screen.Screen2.NOWPLAYER;

public class KeyListener extends KeyAdapter {

    private Player player;

    @Override
    public void keyPressed(KeyEvent event){
        if (Navigator.INSTANCE.getCurrentScreen() == null || Navigator.INSTANCE.getCurrentScreen().inGame==0) {
            return;
        }
        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                if (NOWPLAYER.getField().move(new Move(Direction.LEFT))) {
                    new Music("rotate.mp3",false).start();
                }
            }
            case KeyEvent.VK_RIGHT -> {
                if (NOWPLAYER.getField().move(new Move(Direction.RIGHT))){
                    new Music("rotate.mp3",false).start();
                }
            }
            case KeyEvent.VK_UP -> {
                if (NOWPLAYER.getField().rotate(Rotates.CLOCKWISE)) {
                    new Music("rotate.mp3",false).start();
                }
            }
            case KeyEvent.VK_DOWN -> {
                NOWPLAYER.getField().move(new Move(Direction.DOWN,-1));
            }
            case KeyEvent.VK_SHIFT -> {
                NOWPLAYER.hold();
            }
            case KeyEvent.VK_SPACE -> {
                NOWPLAYER.drop();
            }
            case KeyEvent.VK_Z -> {
                NOWPLAYER.getField().rotate(Rotates.COUNTERCLOCKWISE);
            }
            case KeyEvent.VK_A -> {
                NOWPLAYER.getField().rotate(Rotates.HUNDREDWEIGHT);
            }
            case KeyEvent.VK_R -> {
                NOWPLAYER = new Player("user", new ExampleCharacter());
                if (Navigator.INSTANCE.getCurrentScreen() == Screens.FOURTY_LINE) {
                    ((FourtyLinePanel)Navigator.INSTANCE.getCurrentPanel()).clearedLine =0;
                    ((FourtyLinePanel)Navigator.INSTANCE.getCurrentPanel()).startTime = System.currentTimeMillis();
                    ((FourtyLinePanel)Navigator.INSTANCE.getCurrentPanel()).totalTime = 0;
                }
            }
            case KeyEvent.VK_ESCAPE -> {
                if (Navigator.INSTANCE.getCurrentScreen()!=Screens.INTRO) {
                    Navigator.INSTANCE.popScreen();
                }
            }
            default -> {
                //nothing
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent event){
        if (Navigator.INSTANCE.getCurrentScreen().inGame==0) {
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
            }
            case KeyEvent.VK_DOWN -> {
                new Music("rotate.mp3",false).start();
            }
            case KeyEvent.VK_SHIFT -> {
            }
            case KeyEvent.VK_SPACE -> {
                new Music("rotate.mp3",false).start();
            }
            case KeyEvent.VK_Z -> {
                new Music("rotate.mp3",false).start();
            }
            case KeyEvent.VK_A -> {
                new Music("rotate.mp3",false).start();
            }
        }
    }
    public KeyListener(){
        this.player = NOWPLAYER;
    }
}
