package com.github.washappy.screen;




import com.github.washappy.Music;
import com.github.washappy.character.ExampleCharacter;
import com.github.washappy.enums.Screens;
import com.github.washappy.listener.KeyListener;
import com.github.washappy.tetris.Player;
import tetris.Game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static com.github.washappy.Ingredient.SCREEN_HEIGHT;
import static com.github.washappy.Ingredient.SCREEN_WIDTH;

public class Screen extends JFrame {

    //기본 배경 자료 가져오기
    private Image screenImage;
    private Graphics screenGraphic;
    private final IntroScreenRecources recources= new IntroScreenRecources();

    private Image logoImage = recources.logo;
    private final JLabel menuBar = new JLabel(recources.menuBarIcon);

    //버튼에 객체 할당
    private JButton exitButton = new JButton(recources.exitButtonBasic);

    private JButton singleButton = new JButton(recources.singleButtonBasic);
    private JButton multiButton = new JButton(recources.multiButtonBasic);
    private JButton settingButton = new JButton(recources.settingButtonBasic);
    private JButton creditButton = new JButton(recources.creditButtonBasic);

    private JButton fourtyLineButton = new JButton(recources.fourtyLineButtonBasic);
    private JButton oneMinuteButton = new JButton(recources.oneMinuteButtonBasic);
    private JButton practiceButton = new JButton(recources.practiceButtonBasic);

    private JButton backButton = new JButton(recources.backButtonBasic);

    private int mouseX;
    private int mouseY;

    private Music introMusic = new Music("introMusic.mp3",true);

    private static Screens whatScreen = Screens.INTRO;
    private Graphics2D graphics2D;
    public static Game game = new Game();
    public static Player NOWPLAYER;

    //네비게이터
    private Navigator nav = new Navigator();


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
        NOWPLAYER = new Player("user",new ExampleCharacter());

        addKeyListener(new KeyListener());

        //기본 배경음악 재생
        introMusic.start();

        //exit 버튼 생성
        exitButton.setBounds(1245,0,30,30);
        setPaintSetting(exitButton);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(recources.exitButtonEntered);
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(recources.exitButtonBasic);
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
                singleButton.setIcon(recources.singleButtonPressed);
                singleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                singleButton.setIcon(recources.singleButtonBasic);
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
                multiButton.setIcon(recources.multiButtonPressed);
                multiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                multiButton.setIcon(recources.multiButtonBasic);
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
                settingButton.setIcon(recources.settingButtonPressed);
                settingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                settingButton.setIcon(recources.settingButtonBasic);
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
                creditButton.setIcon(recources.creditButtonPressed);
                creditButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                creditButton.setIcon(recources.creditButtonBasic);
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
                fourtyLineButton.setIcon(recources.fourtyLineButtonPressed);
                fourtyLineButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                fourtyLineButton.setIcon(recources.fourtyLineButtonBasic);
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
                oneMinuteButton.setIcon(recources.oneMinuteButtonPressed);
                oneMinuteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                oneMinuteButton.setIcon(recources.oneMinuteButtonBasic);
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
                practiceButton.setIcon(recources.practiceButtonPressed);
                practiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                practiceButton.setIcon(recources.practiceButtonBasic);
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
                backButton.setIcon(recources.backButtonPressed);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(recources.backButtonBasic);
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

    public void paint(Graphics g) {
        screenImage = createImage(SCREEN_WIDTH,SCREEN_HEIGHT);
        screenGraphic = screenImage.getGraphics();
        this.graphics2D = (Graphics2D)screenGraphic;
        screenDraw(graphics2D);
        g.drawImage(screenImage, 0, 0,null);
    }

    public void screenDraw(Graphics2D g) {
        g.drawImage(recources.introBackground, 0, 0, null);
        g.drawImage(logoImage,20,10,null);
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
    }

    public void changeScreen(Screens s){
        switch (s) {
            case INTRO -> {
                hideButtons();
                singleButton.setVisible(true);
                multiButton.setVisible(true);
                settingButton.setVisible(true);
                creditButton.setVisible(true);
                logoImage = recources.logo;
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
            }
        }

    }

    public void gameStart() {
        game.screenDraw(graphics2D);
        setFocusable(true);
    }

    public static Screens getWhatScreen() {
        return whatScreen;
    }
}
