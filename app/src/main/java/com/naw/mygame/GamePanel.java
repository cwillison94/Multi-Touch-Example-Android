package com.naw.mygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread thread;

    private float touchX = 0, touchY = 0;


    public GamePanel(Context context) {
        super(context);

        thread = new GameThread(getHolder(), this);

        getHolder().addCallback(this);

        setFocusable(true);


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try{
                thread.join();
                retry = false;
            } catch(InterruptedException e){}

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchX = event.getX();
        touchY = event.getY();
        invalidate();
        Log.d(GameThread.LOG_TAG, String.format("x=%f y=%f", touchX, touchY));
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawCircle(touchX, touchY, 100, new Paint(Color.CYAN));
        super.onDraw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
