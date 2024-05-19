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

    private final Image introBackground = new ImageIcon(getClass().getResource("/images/introBackground.jpg")).getImage();
    private final Image logo = new ImageIcon(getClass().getResource("/images/logo.png")).getImage();
    private Image logoImage = logo;
    private final JLabel menuBar = new JLabel(new ImageIcon(getClass().getResource("/images/menuBar.png")));

    //버튼 자료 가져오기
    private final ImageIcon exitButtonBasic = new ImageIcon(getClass().getResource("/images/exitButtonBasic.png"));
    private final ImageIcon exitButtonEntered = new ImageIcon(getClass().getResource("/images/exitButtonEntered.png"));

    private final ImageIcon singleButtonBasic = new ImageIcon(getClass().getResource("/images/singleButton.png"));
    private final ImageIcon singleButtonPressed = new ImageIcon(getClass().getResource("/images/singleButtonPressed.png"));

    private final ImageIcon multiButtonBasic = new ImageIcon(getClass().getResource("/images/multiButton.png"));
    private final ImageIcon multiButtonPressed = new ImageIcon(getClass().getResource("/images/multiButtonPressed.png"));

    private final ImageIcon settingButtonBasic = new ImageIcon(getClass().getResource("/images/settingButton.png"));
    private final ImageIcon settingButtonPressed = new ImageIcon(getClass().getResource("/images/settingButtonPressed.png"));

    private final ImageIcon creditButtonBasic = new ImageIcon(getClass().getResource("/images/creditButton.png"));
    private final ImageIcon creditButtonPressed = new ImageIcon(getClass().getResource("/images/creditButtonPressed.png"));

    private final ImageIcon fourtyLineButtonBasic = new ImageIcon(getClass().getResource("/images/fourtyLineButton.png"));
    private final ImageIcon fourtyLineButtonPressed = new ImageIcon(getClass().getResource("/images/fourtyLineButtonPressed.png"));

    private final ImageIcon oneMinuteButtonBasic = new ImageIcon(getClass().getResource("/images/oneMinuteButton.png"));
    private final ImageIcon oneMinuteButtonPressed = new ImageIcon(getClass().getResource("/images/oneMinuteButtonPressed.png"));

    private final ImageIcon practiceButtonBasic = new ImageIcon(getClass().getResource("/images/practiceButton.png"));
    private final ImageIcon practiceButtonPressed = new ImageIcon(getClass().getResource("/images/practiceButtonPressed.png"));

    private final ImageIcon backButtonBasic = new ImageIcon(getClass().getResource("/images/backButton.png"));
    private final ImageIcon backButtonPressed = new ImageIcon(getClass().getResource("/images/backButtonPressed.png"));

    //버튼에 객체 할당
    private JButton exitButton = new JButton(exitButtonBasic);

    private JButton singleButton = new JButton(singleButtonBasic);
    private JButton multiButton = new JButton(multiButtonBasic);
    private JButton settingButton = new JButton(settingButtonBasic);
    private JButton creditButton = new JButton(creditButtonBasic);

    private JButton fourtyLineButton = new JButton(fourtyLineButtonBasic);
    private JButton oneMinuteButton = new JButton(oneMinuteButtonBasic);
    private JButton practiceButton = new JButton(practiceButtonBasic);

    private JButton backButton = new JButton(backButtonBasic);

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
        NOWPLAYER = new Player("user",new ExampleCharacter());

        addKeyListener(new KeyListener());

        //기본 배경음악 재생
        introMusic.start();

        //exit 버튼 생성
        exitButton.setBounds(1245,0,30,30);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitButtonEntered);
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonOn.mp3",false);
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(exitButtonBasic);
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("buttonClick.mp3",false);
                buttonPressedMusic.start();
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
        singleButton.setBorderPainted(false);
        singleButton.setContentAreaFilled(false);
        singleButton.setFocusPainted(false);
        singleButton.setOpaque(false);
        singleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                singleButton.setIcon(singleButtonPressed);
                singleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonOn.mp3",false);
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                singleButton.setIcon(singleButtonBasic);
                singleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("buttonClick.mp3",false);
                buttonPressedMusic.start();
                whatScreen = Screens.SINGLE;
                changeScreen(whatScreen);
            }
        });
        add(singleButton);

        //multi 버튼 생성
        multiButton.setBounds(825,400,200,200);
        multiButton.setBorderPainted(false);
        multiButton.setContentAreaFilled(false);
        multiButton.setFocusPainted(false);
        multiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                multiButton.setIcon(multiButtonPressed);
                multiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonOn.mp3",false);
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                multiButton.setIcon(multiButtonBasic);
                multiButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("buttonClick.mp3",false);
                buttonPressedMusic.start();
                whatScreen = Screens.MULTI;
                changeScreen(whatScreen);
            }
        });
        add(multiButton);

        //setting 버튼 생성
        settingButton.setBounds(600,500,200,200);
        settingButton.setBorderPainted(false);
        settingButton.setContentAreaFilled(false);
        settingButton.setFocusPainted(false);
        settingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                settingButton.setIcon(settingButtonPressed);
                settingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonOn.mp3",false);
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                settingButton.setIcon(settingButtonBasic);
                settingButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("buttonClick.mp3",false);
                buttonPressedMusic.start();
                whatScreen = Screens.SETTING;
                changeScreen(whatScreen);
            }
        });
        add(settingButton);

        //credit 버튼 생성
        creditButton.setBounds(1125,575,143,144);
        creditButton.setBorderPainted(false);
        creditButton.setContentAreaFilled(false);
        creditButton.setFocusPainted(false);
        creditButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                creditButton.setIcon(creditButtonPressed);
                creditButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonOn.mp3",false);
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                creditButton.setIcon(creditButtonBasic);
                creditButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("buttonClick.mp3",false);
                buttonPressedMusic.start();
                whatScreen = Screens.CREDIT;
                changeScreen(whatScreen);
            }
        });
        add(creditButton);

        //40라인 버튼 생성
        fourtyLineButton.setVisible(false);
        fourtyLineButton.setBounds(500,250,200,200);
        fourtyLineButton.setBorderPainted(false);
        fourtyLineButton.setContentAreaFilled(false);
        fourtyLineButton.setFocusPainted(false);
        fourtyLineButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                fourtyLineButton.setIcon(fourtyLineButtonPressed);
                fourtyLineButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonOn.mp3",false);
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                fourtyLineButton.setIcon(fourtyLineButtonBasic);
                fourtyLineButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("buttonClick.mp3",false);
                buttonPressedMusic.start();
                whatScreen = Screens.FOURTY_LINE;
                changeScreen(whatScreen);
            }
        });
        add(fourtyLineButton);

        //1분 버튼 생성
        oneMinuteButton.setVisible(false);
        oneMinuteButton.setBounds(300,450,200,200);
        oneMinuteButton.setBorderPainted(false);
        oneMinuteButton.setContentAreaFilled(false);
        oneMinuteButton.setFocusPainted(false);
        oneMinuteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                oneMinuteButton.setIcon(oneMinuteButtonPressed);
                oneMinuteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonOn.mp3",false);
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                oneMinuteButton.setIcon(oneMinuteButtonBasic);
                oneMinuteButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("buttonClick.mp3",false);
                buttonPressedMusic.start();
                whatScreen = Screens.ONE_MINUTE;
                changeScreen(whatScreen);
            }
        });
        add(oneMinuteButton);

        //연습 버튼 생성
        practiceButton.setVisible(false);
        practiceButton.setBounds(700,450,200,200);
        practiceButton.setBorderPainted(false);
        practiceButton.setContentAreaFilled(false);
        practiceButton.setFocusPainted(false);
        practiceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                practiceButton.setIcon(practiceButtonPressed);
                practiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonOn.mp3",false);
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                practiceButton.setIcon(practiceButtonBasic);
                practiceButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("buttonClick.mp3",false);
                buttonPressedMusic.start();
                whatScreen = Screens.PRACTICE;
                changeScreen(whatScreen);
            }
        });
        add(practiceButton);

        //뒤로가기 버튼 생성
        backButton.setVisible(false);
        backButton.setBounds(0,30,143,144);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(backButtonPressed);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonOn.mp3",false);
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(backButtonBasic);
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("buttonClick.mp3",false);
                buttonPressedMusic.start();
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

    public void paint(Graphics g) {
        screenImage = createImage(SCREEN_WIDTH,SCREEN_HEIGHT);
        screenGraphic = screenImage.getGraphics();
        this.graphics2D = (Graphics2D)screenGraphic;
        screenDraw(graphics2D);
        g.drawImage(screenImage, 0, 0,null);
    }

    public void screenDraw(Graphics2D g) {
        g.drawImage(introBackground, 0, 0, null);
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
                logoImage = logo;
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
