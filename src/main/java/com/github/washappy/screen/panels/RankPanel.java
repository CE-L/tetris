package com.github.washappy.screen.panels;

import com.github.washappy.enums.Screens;
import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.recources.CreditPanelResources;
import com.github.washappy.tetris.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RankPanel extends AbstractPanel{
    private final CreditPanelResources resources = new CreditPanelResources();
    private final JButton backButton = new JButton(resources.backButtonBasic);

    public static Game game = new Game();

    public RankPanel(){
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
        g.drawString("만약 칠판에 있는 등수보다 더 빠르다면 ",100,200);
        g.drawString("부원을 불러 신기록을 경신해주세요! ",100,240);

        g.drawString("2분 이내의 기록만 등록이 가능합니다",100,320);
        g.drawString("1둥에게는 스피커 상품이 있어요!",100,360);

        g.drawString("3분 이내라면 도장!",100,440);

        return g;
    }

    @Override
    public Screens getScreen() {
        return Screens.RANK;
    }
}
