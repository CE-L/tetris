package com.github.washappy.screen;




import com.github.washappy.Music;
import com.github.washappy.character.ExampleCharacter;
import com.github.washappy.enums.Screens;
import com.github.washappy.listener.KeyListener;
import com.github.washappy.screen.recources.IntroPanelResources;
import com.github.washappy.tetris.Game;
import com.github.washappy.tetris.Player;
import com.github.washappy.tetris.mino.AbstactMino;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Arrays;

import static com.github.washappy.Ingredient.SCREEN_HEIGHT;
import static com.github.washappy.Ingredient.SCREEN_WIDTH;

public class Screen extends JFrame {

    //기본 배경 자료 가져오기
    private Image screenImage;
    private Graphics screenGraphic;
    private final IntroPanelResources resources = new IntroPanelResources();

    private Image logoImage = resources.logo;
    private final JLabel menuBar = new JLabel(resources.menuBarIcon);

    private Image boardImage = null;
    private Image holdImage = null;
    private Image holdMinoImage = null;
    private Image nextImage = null;
    private Image[] nextMinoImages = new Image[5];

    //버튼에 객체 할당
    private JButton exitButton = new JButton(resources.exitButtonBasic);

    private JButton singleButton = new JButton(resources.singleButtonBasic);
    private JButton multiButton = new JButton(resources.multiButtonBasic);
    private JButton settingButton = new JButton(resources.settingButtonBasic);
    private JButton creditButton = new JButton(resources.creditButtonBasic);

    private JButton fourtyLineButton = new JButton(resources.fourtyLineButtonBasic);
    private JButton oneMinuteButton = new JButton(resources.oneMinuteButtonBasic);
    private JButton practiceButton = new JButton(resources.practiceButtonBasic);

    private JButton backButton = new JButton(resources.backButtonBasic);

    //칸마다 객체 할당
    private Image[][] fieldImages = new Image[10][40];

    private int mouseX;
    private int mouseY;

    private Music introMusic = new Music("introMusic.mp3",true);

    private static Screens whatScreen = Screens.INTRO;
    private Graphics2D graphics2D;
    public static Game game = new Game();
    public static Player NOWPLAYER;


    public Screen() {
        //기본 창 설정
        setUndecorated(true);
        setTitle("Ytri_S");
        setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(new Color(0,0,0,0));
        setLayout(null);
        //NOWPLAYER = new Player("user",new ExampleCharacter());

        addKeyListener(new KeyListener());

        //기본 배경음악 재생
        introMusic.start();

        //exit 버튼 생성
        exitButton.setBounds(1245,0,30,30);
        setPaintSetting(exitButton);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(resources.exitButtonEntered);
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(resources.exitButtonBasic);
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                try {
                    Thread.sleep(235);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        add(exitButton);

        //single 버튼 생성
        singleButton.setBounds(1050,300,200,200);
        setPaintSetting(singleButton);
        singleButton.setOpaque(false);
        singleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                singleButton.setIcon(resources.singleButtonPressed);
                singleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                singleButton.setIcon(resources.singleButtonBasic);
                singleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                whatScreen = Screens.SINGLE;
                changeScreen(whatScreen);
            }
        });
        add(singleButton);

        //multi 버튼 생성
        multiButton.setBounds(825,400,200,200);
        setPaintSetting(multiButton);

        multiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                multiButton.setIcon(resources.multiButtonPressed);
                multiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                multiButton.setIcon(resources.multiButtonBasic);
                multiButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                whatScreen = Screens.MULTI;
                changeScreen(whatScreen);
            }
        });
        add(multiButton);

        //setting 버튼 생성
        settingButton.setBounds(600,500,200,200);
        setPaintSetting(settingButton);
        settingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                settingButton.setIcon(resources.settingButtonPressed);
                settingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                settingButton.setIcon(resources.settingButtonBasic);
                settingButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                whatScreen = Screens.SETTING;
                changeScreen(whatScreen);
            }
        });
        add(settingButton);

        //credit 버튼 생성
        creditButton.setBounds(1125,575,143,144);
        setPaintSetting(creditButton);
        creditButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                creditButton.setIcon(resources.creditButtonPressed);
                creditButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                creditButton.setIcon(resources.creditButtonBasic);
                creditButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                whatScreen = Screens.CREDIT;
                changeScreen(whatScreen);
            }
        });
        add(creditButton);

        //40라인 버튼 생성
        fourtyLineButton.setVisible(false);
        fourtyLineButton.setBounds(500,250,200,200);
        setPaintSetting(fourtyLineButton);
        fourtyLineButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                fourtyLineButton.setIcon(resources.fourtyLineButtonPressed);
                fourtyLineButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                fourtyLineButton.setIcon(resources.fourtyLineButtonBasic);
                fourtyLineButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                whatScreen = Screens.FOURTY_LINE;
                changeScreen(whatScreen);
            }
        });
        add(fourtyLineButton);

        //1분 버튼 생성
        oneMinuteButton.setVisible(false);
        oneMinuteButton.setBounds(300,450,200,200);
        setPaintSetting(oneMinuteButton);
        oneMinuteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                oneMinuteButton.setIcon(resources.oneMinuteButtonPressed);
                oneMinuteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                oneMinuteButton.setIcon(resources.oneMinuteButtonBasic);
                oneMinuteButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                whatScreen = Screens.ONE_MINUTE;
                changeScreen(whatScreen);
            }
        });
        add(oneMinuteButton);

        //연습 버튼 생성
        practiceButton.setVisible(false);
        practiceButton.setBounds(700,450,200,200);
        setPaintSetting(practiceButton);
        practiceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                practiceButton.setIcon(resources.practiceButtonPressed);
                practiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                practiceButton.setIcon(resources.practiceButtonBasic);
                practiceButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                whatScreen = Screens.PRACTICE;
                changeScreen(whatScreen);
            }
        });
        add(practiceButton);

        //뒤로가기 버튼 생성
        backButton.setVisible(false);
        backButton.setBounds(0,30,143,144);
        setPaintSetting(backButton);
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
                whatScreen = Screens.INTRO;
                changeScreen(whatScreen);
            }
        });
        add(backButton);

        //메뉴바 생성
        menuBar.setBounds(0,0,1280,30);
        menuBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x-mouseX,y-mouseY);
            }
        });
        add(menuBar);
    }

    private void setPaintSetting(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
    }

    private void playButtonOn(){
        Music buttonEnteredMusic = new Music("buttonOn.mp3",false);
        buttonEnteredMusic.start();
    }

    private void playButtonClick(){
        Music buttonPressedMusic = new Music("buttonClick.mp3",false);
        buttonPressedMusic.start();
    }

    @Override
    public void paint(Graphics g) {
        screenImage = createImage(SCREEN_WIDTH,SCREEN_HEIGHT);
        screenGraphic = screenImage.getGraphics();
        this.graphics2D = (Graphics2D)screenGraphic;
        screenDraw(graphics2D);
        g.drawImage(screenImage, 0, 0,null);


    }

    public void screenDraw(Graphics2D g) {
        g.drawImage(resources.introBackground, 0, 0, null);
        g.drawImage(logoImage,20,10,null);
        g.drawImage(boardImage,400,320,null);
        g.drawImage(holdImage,AbstactMino.SOLO_X-120,AbstactMino.SOLO_Y,null);
        g.drawImage(holdMinoImage,AbstactMino.SOLO_X-120,AbstactMino.SOLO_Y+40,null);
        //TODO next

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

        /*g.setColor(Color.white);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(new Font("Ariel",Font.BOLD,30));
        g.drawString("안녕",20,702);*/
        paintComponents(g);
        this.repaint();
    }

    public void hideButtons() {
        singleButton.setVisible(false);
        multiButton.setVisible(false);
        settingButton.setVisible(false);
        creditButton.setVisible(false);

        fourtyLineButton.setVisible(false);
        oneMinuteButton.setVisible(false);
        practiceButton.setVisible(false);

        backButton.setVisible(false);

        logoImage = null;
        boardImage = null;
        holdImage = null;
        nextImage = null;

        NOWPLAYER = null;

        holdMinoImage = null;
        for (Image[] i : fieldImages) {
            Arrays.fill(i,new IntroPanelResources().noMino);
        }
    }

    public void changeScreen(Screens s){
        switch (s) {
            case INTRO -> {
                hideButtons();
                singleButton.setVisible(true);
                multiButton.setVisible(true);
                settingButton.setVisible(true);
                creditButton.setVisible(true);
                logoImage = resources.logo;
            }
            case SINGLE -> {
                hideButtons();
                fourtyLineButton.setVisible(true);
                oneMinuteButton.setVisible(true);
                practiceButton.setVisible(true);
                backButton.setVisible(true);
            }
            case MULTI -> {
                hideButtons();
                backButton.setVisible(true);
            }
            case SETTING -> {
                hideButtons();
                backButton.setVisible(true);
            }
            case CREDIT -> {
                hideButtons();
                backButton.setVisible(true);
            }

            case FOURTY_LINE -> {
                hideButtons();
                backButton.setVisible(true);
                gameStart();
            }
            case ONE_MINUTE -> {
                hideButtons();
                backButton.setVisible(true);
            }
            case PRACTICE -> {
                hideButtons();
                backButton.setVisible(true);
                gameStart();
            }
        }

    }

    public void gameStart() {
        game.screenDraw(graphics2D);
        setFocusable(true);
        boardImage = resources.boardImage;
        holdImage = resources.holdImage;
        //nextImage = TODO
        for (Image[] i : fieldImages) {
            Arrays.fill(i,new IntroPanelResources().noMino);
        }
        NOWPLAYER = new Player("user", new ExampleCharacter());
    }

    public void updateField() {

        IntroPanelResources recources = new IntroPanelResources();

        AbstactMino[][] board = NOWPLAYER.getField().getField();

        int[][] temp = new int[10][40];

        int x = 0;
        //System.out.println(NOWPLAYER.getField().getNowMino().getMino().getNumber());
        //System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
        for (AbstactMino[] i : board) {
            int y = 0;
            for (AbstactMino j : i) {
                if (j == null) {
                    fieldImages[x][y] = recources.noMino;
                    temp[x][y] = 0;
                } else {
                    temp[x][y] = j.getMino().getNumber();
                    fieldImages[x][y] = switch (j.getMino()) {
                        case T -> recources.tMino;
                        case I -> recources.iMino;
                        case O -> recources.oMino;
                        case J -> recources.jMino;
                        case L -> recources.lMino;
                        case S -> recources.sMino;
                        case Z -> recources.zMino;
                        default -> recources.noMino;
                    };
                }
                y+=1;
            }
            x+=1;
        }

        if (NOWPLAYER.getHold()==null || NOWPLAYER.getHold().peek()==null) {
            holdMinoImage = recources.no;
        } else {
            holdMinoImage = switch (NOWPLAYER.getHold().peek()) {
                case I -> recources.i;
                case T -> recources.t;
                case O -> recources.o;
                case L -> recources.l;
                case J -> recources.j;
                case S -> recources.s;
                case Z -> recources.z;
                default -> recources.no;
            };
        }

        //TODO nextMinoImage

        /*for (int i = 0; i<temp.length; i++) {
            System.out.println(Arrays.toString(temp[i]));
        }
        System.out.println("------------------------");*/
    }

    public static Screens getWhatScreen() {
        return whatScreen;
    }
}
