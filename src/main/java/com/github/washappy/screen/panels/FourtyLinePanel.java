package com.github.washappy.screen.panels;

import com.github.washappy.character.ExampleCharacter;
import com.github.washappy.enums.Direction;
import com.github.washappy.enums.Screens;
import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.recources.InGamePanelResources;
import com.github.washappy.screen.recources.IntroPanelResources;
import com.github.washappy.tetris.Board;
import com.github.washappy.tetris.Game;
import com.github.washappy.tetris.Player;
import com.github.washappy.tetris.mino.AbstactMino;
import com.github.washappy.tetris.mino.Minos;
import com.github.washappy.tetris.mino.Move;
import com.github.washappy.tetris.mino.NowMino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Queue;

import static com.github.washappy.screen.Screen2.NOWPLAYER;

public class FourtyLinePanel extends AbstractPanel{

    private final InGamePanelResources resources = new InGamePanelResources();
    private final JButton backButton = new JButton(resources.backButtonBasic);

    public static Game game = new Game();

    private Image[][] fieldImages = new Image[10][40];
    private Image[] nextMinoImages = new Image[5];

    private Image boardImage = null;
    private Image holdImage = null;
    private Image holdMinoImage = null;
    private Image nextImage = null;

    public int clearedLine = 0;
    public long startTime = 0;
    public long totalTime = 0;

    public static long FINAL_TIME = 0;

    public String time;
    public int gravity = 1;

    public FourtyLinePanel(){

        clearedLine = 0;
        startTime = System.currentTimeMillis();
        totalTime = 0;

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

        //game.screenDraw();

        Navigator.INSTANCE.frame.setFocusable(true);
        boardImage = resources.boardImage;
        holdImage = resources.holdImage;
        nextImage = resources.nextImage;
        for (Image[] i : fieldImages) {
            Arrays.fill(i,new IntroPanelResources().noMino);
        }
        Arrays.fill(nextMinoImages, new IntroPanelResources().noMino);
    }

    @Override
    public Graphics2D screenDraw(Graphics2D g) {
        game.screenDraw(g);

        g.drawImage(boardImage, Board.SOLO_BOARD_PLACE[0], Board.SOLO_BOARD_PLACE[1], null);   //400,320 -> 550 220
        g.drawImage(holdImage, Board.SOLO_HOLD_PLACE[0], Board.SOLO_HOLD_PLACE[1], null); // 280,350  -> 430,250
        g.drawImage(holdMinoImage,Board.SOLO_HOLD_MINO_PLACE[0], Board.SOLO_HOLD_MINO_PLACE[1], null); //280,390 -> 430,290
        g.drawImage(nextImage,Board.SOLO_NEXT_PLACE[0], Board.SOLO_NEXT_PLACE[1], null);

        if (NOWPLAYER!=null) {
            updateField();
        }

        if (NOWPLAYER!=null) {
            NowMino temp = NOWPLAYER.getField().getBottom();
            int[][] coor = temp.getCoordinates();
            int r = 0;
            for (int[] one : coor) {
                NOWPLAYER.ghostMinos[r] = new NowMino(temp.getMino(), one[0], one[1]);
                r += 1;
            }
            for (AbstactMino i : NOWPLAYER.ghostMinos) {
                Image mino = resources.gMino;
                        /*switch (i.getMino()) {
                            case T -> resources.tMino;
                            case I -> resources.iMino;
                            case O -> resources.oMino;
                            case J -> resources.jMino;
                            case L -> resources.lMino;
                            case S -> resources.sMino;
                            case Z -> resources.zMino;
                            default -> resources.noMino;
                        };*/
                g.drawImage(mino, Board.SOLO_FIELD_MINO_PLACE[0] + 20 * i.getX(), Board.SOLO_FIELD_MINO_PLACE[1] - 20 * i.getY(), null);
            }
        }

        int x = 0;
        for (Image[] i : fieldImages) {
            int y = 0;
            for (Image j : i) {
                g.drawImage(fieldImages[x][y],Board.SOLO_FIELD_MINO_PLACE[0]+20*x,Board.SOLO_FIELD_MINO_PLACE[1] -20*y,null); //400,700 -> 550,600
                y+=1;
            }
            x+=1;
        }

        int b = 0;
        for (Image i : nextMinoImages) {
            g.drawImage(i,Board.SOLO_NEXT_MINO_PLACE[0], Board.SOLO_NEXT_MINO_PLACE[1]+ 70*b,null); // 600,300 -> 750,200
            b+=1;
        }


        int stringX = 200;
        int stringY = 350;

        g.setColor(Color.white);
        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(new Font("Ariel",Font.BOLD,30));
        g.drawString("지운 줄 수 : "+Integer.toString(clearedLine),stringX,stringY);
        long finalTime = System.currentTimeMillis()- startTime;

        g.drawString("시간 : "+msToString(finalTime),stringX,stringY+60);
        double pps = (double)NOWPLAYER.placedMinoCount/(finalTime/1000);
        if (Math.round(pps * 100) / 100.0 >100) {
            g.drawString("1초당 블럭수 : ??", stringX, stringY + 120);
        } else {
            g.drawString("1초당 블럭수 : "+Double.toString(Math.round(pps * 100) / 100.0), stringX, stringY + 120);
        }

        if (NOWPLAYER.loopCount%120==0) {
            if (!(NOWPLAYER.getField().move(new Move(Direction.DOWN, -1)))) {
                NOWPLAYER.drop();
            }
        }

        NOWPLAYER.loopCount+=1;

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

        if (NOWPLAYER.getNext()==null) {
            Arrays.fill(nextMinoImages,new IntroPanelResources().noMino);
        } else {
            Queue<Minos> next = NOWPLAYER.getNext();
            for(int i=0; i<5; i++) {
                nextMinoImages[i] = switch ((Minos)next.toArray()[i]) {
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
    }

    public void success() {
        FINAL_TIME = totalTime;
        Navigator.INSTANCE.stackScreen(Screens.SUCCESS);
        //System.out.println("success");
        //System.out.println(FINAL_TIME);
    }

    @Override
    public Screens getScreen() {
        return Screens.FOURTY_LINE;
    }

    public static String msToString(long t) {
        if (t/60000>0) {
            return t / 60000 + ":" + (t - (t / 60000) * 60000) / 1000 + "." + t % 1000;
        } else {
            return  (t - (t / 60000) * 60000) / 1000 + "." + t % 1000;
        }
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setClearedLine(int clearedLine) {
        this.clearedLine = clearedLine;
    }
}
