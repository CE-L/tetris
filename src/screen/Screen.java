package screen;


import enums.Screens;
import main.Main;
import main.Music;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static sorce.Ingredient.*;

public class Screen extends JFrame {

    //기본 배경 자료 가져오기
    private Image screenImage;
    private Graphics screenGraphic;

    private Image introBackground = new ImageIcon(Main.class.getResource("../sorce/image/introBackground.jpg")).getImage();
    private Image logo = new ImageIcon(Main.class.getResource("../sorce/image/logo.png")).getImage();
    private Image logoImage = logo;
    private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../sorce/image/menuBar.png")));

    //버튼 자료 가져오기
    private ImageIcon exitButtonBasic = new ImageIcon(Main.class.getResource("../sorce/image/exitButtonBasic.png"));
    private ImageIcon exitButtonEntered = new ImageIcon(Main.class.getResource("../sorce/image/exitButtonEntered.png"));

    private ImageIcon singleButtonBasic = new ImageIcon(Main.class.getResource("../sorce/image/singleButton.png"));
    private ImageIcon singleButtonPressed = new ImageIcon(Main.class.getResource("../sorce/image/singleButtonPressed.png"));

    private ImageIcon multiButtonBasic = new ImageIcon(Main.class.getResource("../sorce/image/multiButton.png"));
    private ImageIcon multiButtonPressed = new ImageIcon(Main.class.getResource("../sorce/image/multiButtonPressed.png"));

    private ImageIcon settingButtonBasic = new ImageIcon(Main.class.getResource("../sorce/image/settingButton.png"));
    private ImageIcon settingButtonPressed = new ImageIcon(Main.class.getResource("../sorce/image/settingButtonPressed.png"));

    private ImageIcon creditButtonBasic = new ImageIcon(Main.class.getResource("../sorce/image/creditButton.png"));
    private ImageIcon creditButtonPressed = new ImageIcon(Main.class.getResource("../sorce/image/creditButtonPressed.png"));

    private ImageIcon fourtyLineButtonBasic = new ImageIcon(Main.class.getResource("../sorce/image/fourtyLineButton.png"));
    private ImageIcon fourtyLineButtonPressed = new ImageIcon(Main.class.getResource("../sorce/image/fourtyLineButtonPressed.png"));

    private ImageIcon oneMinuteButtonBasic = new ImageIcon(Main.class.getResource("../sorce/image/oneMinuteButton.png"));
    private ImageIcon oneMinuteButtonPressed = new ImageIcon(Main.class.getResource("../sorce/image/oneMinuteButtonPressed.png"));

    private ImageIcon practiceButtonBasic = new ImageIcon(Main.class.getResource("../sorce/image/practiceButton.png"));
    private ImageIcon practiceButtonPressed = new ImageIcon(Main.class.getResource("../sorce/image/practiceButtonPressed.png"));

    //버튼에 객체 할당
    private JButton exitButton = new JButton(exitButtonBasic);

    private JButton singleButton = new JButton(singleButtonBasic);
    private JButton multiButton = new JButton(multiButtonBasic);
    private JButton settingButton = new JButton(settingButtonBasic);
    private JButton creditButton = new JButton(creditButtonBasic);

    private JButton fourtyLineButton = new JButton(fourtyLineButtonBasic);
    private JButton oneMinuteButton = new JButton(oneMinuteButtonBasic);
    private JButton practiceButton = new JButton(practiceButtonBasic);

    private int mouseX, mouseY;

    private Screens whatScreen = Screens.INTRO;


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

        //기본 배경음악 재생
        Music introMusic = new Music("introMusic.mp3",true);
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
                hideButtons();
                whatScreen = Screens.FOURTYLINE;
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
                hideButtons();
                whatScreen = Screens.FOURTYLINE;
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
                hideButtons();
                whatScreen = Screens.FOURTYLINE;
            }
        });
        add(practiceButton);

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
        screenDraw(screenGraphic);
        g.drawImage(screenImage, 0, 0,null);
    }

    public void screenDraw(Graphics g) {
        g.drawImage(introBackground, 0, 0, null);
        g.drawImage(logoImage,20,10,null);
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
            }
            case MULTI -> hideButtons();
            case SETTING -> hideButtons();
            case CREDIT -> hideButtons();
        }

    }
}
