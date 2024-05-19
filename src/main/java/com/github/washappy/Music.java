package com.github.washappy;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Music extends Thread{

    private Player player;
    private boolean isLoop;
    private File file;
    private FileInputStream fis;
    private BufferedInputStream bis;

    public Music(String name, boolean isLoop) {
        try {
            this.isLoop = isLoop;
            file = new File(getClass().getResource("/musics/"+name).toURI());
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getTime() {
        if (player == null)
            return 0;
        return player.getPosition(); //0.01초까지
    }

    public  void close() {
        isLoop = false;
        player.close();
        this.interrupt();
    }

    @Override
    public void run() {
        try {
            do {
                player.play();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
            } while (isLoop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

/**
 * Thanks to, Mark DiAngelo
 * From: http://soundbible.com/2068-Woosh.html
 *
 * Distributor: Stop worrying about musics & free sound effects with Mewpot’
 * https://www.mewpot.com🎫 추천인 코드: mewc.at/ref/ao29
 * 위의 URL을 통해 구독하시면 2개월 추가 혜택! 저와 함께 뮤팟 혜택을 받아요~🎁
 */