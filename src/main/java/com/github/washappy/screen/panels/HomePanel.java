package com.github.washappy.screen.panels;

import com.github.washappy.Music;
import com.github.washappy.enums.Screens;
import com.github.washappy.screen.IntroScreenRecources;
import com.github.washappy.tetris.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static com.github.washappy.Ingredient.SCREEN_HEIGHT;
import static com.github.washappy.Ingredient.SCREEN_WIDTH;

public class HomePanel extends JPanel {




    //기본 배경 자료 가져오기
    private Image screenImage;
    private Graphics screenGraphic;
    private final IntroScreenRecources recources= new IntroScreenRecources();

    private Image logoImage = recources.logo;
    private final JLabel menuBar = new JLabel(recources.menuBarIcon);

    private Image boardImage = null;
    private Image holdImage = null;
    private Image holdMinoImage = null;
    private Image nextImage = null;
    private Image[] nextMinoImages = new Image[5];

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

    //칸마다 객체 할당
    private Image[][] fieldImages = new Image[10][40];

    private int mouseX;
    private int mouseY;

    private Music introMusic = new Music("introMusic.mp3",true);

    private static Screens whatScreen = Screens.INTRO;
    private Graphics2D graphics2D;
    public static tetris.Game game = new tetris.Game();
    public static Player NOWPLAYER;


    public HomePanel(){

        setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
//        setVisible(true);
        setBackground(new Color(0,0,0,0));
//        setLayout(null);


        //기본 배경음악 재생
        introMusic.start();

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
                //changeScreen(whatScreen);
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
                //changeScreen(whatScreen);
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
                //changeScreen(whatScreen);
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
                //changeScreen(whatScreen);
            }
        });
        add(creditButton);

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
                //changeScreen(whatScreen);
            }
        });
        add(backButton);


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
        screenImage = createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
        screenGraphic = screenImage.getGraphics();
        this.graphics2D = (Graphics2D) screenGraphic;
        screenDraw(graphics2D);
        g.drawImage(screenImage, 0, 0, null);
    }

    public void screenDraw(Graphics2D g) {
        g.drawImage(recources.introBackground, 0, 0, null);
        g.drawImage(logoImage, 20, 10, null);

        int x = 0;
        for (Image[] i : fieldImages) {
            int y = 0;
            for (Image j : i) {
                g.drawImage(fieldImages[x][y], 400 + 20 * x, 700 - 20 * y, null);
                y += 1;
            }
            x += 1;
        }

        paintComponents(g);
        this.repaint();
    }

}
