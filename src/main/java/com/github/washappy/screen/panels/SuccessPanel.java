package com.github.washappy.screen.panels;

import com.github.washappy.enums.Screens;
import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.recources.MultiPanelResources;
import com.github.washappy.tetris.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.github.washappy.screen.panels.FourtyLinePanel.FINAL_TIME;
import static com.github.washappy.screen.panels.FourtyLinePanel.msToString;

public class SuccessPanel extends AbstractPanel{
    public static String GAME_OVER_MESSAGE = "";

    private final MultiPanelResources resources = new MultiPanelResources();
    private final JButton backButton = new JButton(resources.backButtonBasic);

    public static Game game = new Game();

    public SuccessPanel(){
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
                ((FourtyLinePanel)Navigator.INSTANCE.getCurrentPanel()).setStartTime(System.currentTimeMillis());
                ((FourtyLinePanel)Navigator.INSTANCE.getCurrentPanel()).setClearedLine(0);
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
        g.setFont(new Font("Ariel",Font.BOLD,100));
        g.drawString("Success",150,600);
        g.setFont(new Font("Ariel",Font.BOLD,30));


        g.drawString(msToString(FINAL_TIME),400,450);
        return g;
    }


    @Override
    public Screens getScreen() {
        return Screens.SUCCESS;
    }
}
