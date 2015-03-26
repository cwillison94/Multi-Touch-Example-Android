package com.naw.mygame;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    public static final String LOG_TAG = GameThread.class.getSimpleName();

    private boolean running = false;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;

    public GameThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        Canvas canvas;
        while (running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    //update game state
                    //draw canvas on panel
                    this.gamePanel.draw(canvas);

                }
            } finally {
                if (canvas != null) {
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }

    }


    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}

