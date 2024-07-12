package com.github.washappy.screen.panels;

import com.github.washappy.enums.Screens;
import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.recources.CreditPanelResources;
import com.github.washappy.tetris.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BookPanel extends AbstractPanel{
    private final CreditPanelResources resources = new CreditPanelResources();
    private final JButton backButton = new JButton(resources.backButtonBasic);
    public static Game game = new Game();

    private Image keyBoardImage = resources.keyBoard;

    public BookPanel(){
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
    }

    @Override
    public Graphics2D screenDraw(Graphics2D g) {
        game.screenDraw(g);

        g.setColor(Color.white);
        g.setFont(new Font("Ariel",Font.BOLD,30));

        g.drawImage(keyBoardImage, 200, 150,null);

        return g;
    }

    @Override
    public Screens getScreen() {
        return Screens.BOOK;
    }
}
