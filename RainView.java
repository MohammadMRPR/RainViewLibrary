
package com.rainlib;

import android.content.Context;
import android.graphics.*;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.Random;

public class RainView extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean isRunning = false;
    private SurfaceHolder holder;
    private Paint paint;
    private RainDrop[] drops;
    private int width, height;

    public RainView(Context context) {
        super(context);
        holder = getHolder();
        paint = new Paint();
        drops = new RainDrop[150];
        for (int i = 0; i < drops.length; i++) {
            drops[i] = new RainDrop();
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            if (!holder.getSurface().isValid()) continue;
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            for (RainDrop drop : drops) {
                drop.update(height);
                drop.draw(canvas, paint);
            }
            holder.unlockCanvasAndPost(canvas);
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {}
        }
    }

    public void resume() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {}
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.width = w;
        this.height = h;
    }

    private class RainDrop {
        float x, y, speed, length;

        RainDrop() {
            Random rand = new Random();
            x = rand.nextInt(1080);
            y = rand.nextInt(1920);
            speed = 10 + rand.nextFloat() * 20;
            length = 10 + rand.nextFloat() * 20;
        }

        void update(int h) {
            y += speed;
            if (y > h) y = 0;
        }

        void draw(Canvas canvas, Paint paint) {
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(3);
            canvas.drawLine(x, y, x, y + length, paint);
        }
    }
}
