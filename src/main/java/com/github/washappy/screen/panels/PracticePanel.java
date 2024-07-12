package com.github.washappy.screen.panels;

import com.github.washappy.character.ExampleCharacter;
import com.github.washappy.enums.Screens;
import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.recources.IntroPanelResources;
import com.github.washappy.screen.recources.InGamePanelResources;
import com.github.washappy.tetris.Board;
import com.github.washappy.tetris.Game;
import com.github.washappy.tetris.Player;
import com.github.washappy.tetris.mino.AbstactMino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import static com.github.washappy.screen.Screen2.NOWPLAYER;

public class PracticePanel extends AbstractPanel{

    private final InGamePanelResources resources = new InGamePanelResources();
    private final JButton backButton = new JButton(resources.backButtonBasic);

    public static Game game = new Game();

    private Image[][] fieldImages = new Image[10][40];

    private Image boardImage = null;
    private Image holdImage = null;
    private Image holdMinoImage = null;
    private Image nextImage = null;

    public PracticePanel(){
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(resources.backButtonPressed);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(resources.backButtonBasic);
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                Navigator.INSTANCE.popScreen();
            }
        });
    }

    @Override
    public void init() {
        super.init();

        //뒤로가기 버튼 생성
        backButton.setBounds(0,30,143,144);
        setPaintSetting(backButton);
        backButton.setIcon(resources.backButtonBasic);
        panel.add(backButton);

        startGame();
    }

    public void startGame() {

        NOWPLAYER = new Player("user", new ExampleCharacter());

        Navigator.INSTANCE.frame.setFocusable(true);
        boardImage = resources.boardImage;
        holdImage = resources.holdImage;
        nextImage = resources.nextImage;
        for (Image[] i : fieldImages) {
            Arrays.fill(i,new IntroPanelResources().noMino);
        }
    }

    @Override
    public Graphics2D screenDraw(Graphics2D g) {
        game.screenDraw(g);

        g.drawImage(boardImage,400,320,null);
        g.drawImage(holdImage, AbstactMino.SOLO_X-120,AbstactMino.SOLO_Y,null);
        g.drawImage(holdMinoImage,AbstactMino.SOLO_X-120,AbstactMino.SOLO_Y+40,null);
        g.drawImage(nextImage, Board.SOLO_NEXT_PLACE[0], Board.SOLO_NEXT_PLACE[1], null);

        if (NOWPLAYER!=null) {
            updateField();
        }

        int x = 0;
        for (Image[] i : fieldImages) {
            int y = 0;
            for (Image j : i) {
                g.drawImage(fieldImages[x][y],AbstactMino.SOLO_X+20*x,AbstactMino.SOLO_Y+350-20*y,null);
                y+=1;
            }
            x+=1;
        }

        return g;
    }


    public void updateField() {
        AbstactMino[][] board = NOWPLAYER.getField().getField();
        int[][] temp = new int[10][40];

        int x = 0;

        for (AbstactMino[] i : board) {
            int y = 0;
            for (AbstactMino j : i) {
                if (j == null) {
                    fieldImages[x][y] = resources.noMino;
                    temp[x][y] = 0;
                } else {
                    temp[x][y] = j.getMino().getNumber();
                    fieldImages[x][y] = switch (j.getMino()) {
                        case T -> resources.tMino;
                        case I -> resources.iMino;
                        case O -> resources.oMino;
                        case J -> resources.jMino;
                        case L -> resources.lMino;
                        case S -> resources.sMino;
                        case Z -> resources.zMino;
                        default -> resources.noMino;
                    };
                }
                y += 1;
            }
            x += 1;
        }

        if (NOWPLAYER.getHold() == null || NOWPLAYER.getHold().peek() == null) {
            holdMinoImage = resources.no;
        } else {
            holdMinoImage = switch (NOWPLAYER.getHold().peek()) {
                case I -> resources.i;
                case T -> resources.t;
                case O -> resources.o;
                case L -> resources.l;
                case J -> resources.j;
                case S -> resources.s;
                case Z -> resources.z;
                default -> resources.no;
            };
        }
    }

    @Override
    public Screens getScreen() {
        return Screens.PRACTICE;
    }
}