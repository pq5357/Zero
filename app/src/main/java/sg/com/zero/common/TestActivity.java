package sg.com.zero.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Arrays;
import java.util.Random;

import sg.com.zero.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MySurface mySurface = new MySurface(this);
        setContentView(mySurface);

    }

    private class MySurface extends SurfaceView implements SurfaceHolder.Callback {
        private int[] people = new int[100];

        private int height;
        private int width;

        private int cellWidth = 10;
        private int cellSpace = 5;

        private SurfaceHolder holder;
        private Paint paint;
        Random random = new Random(System.currentTimeMillis());

        public MySurface(Context context) {
            super(context);
            for (int i = 0; i < 100; i++) {
                people[i] = 100;
            }
            paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            getHolder().addCallback(this);
        }

        private void drawPeople(int[] integers) {
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            for (int i = 0; i < integers.length; i++) {
                drawPerson(canvas, integers[i], i);
            }
            holder.unlockCanvasAndPost(canvas);
        }

        private void drawPerson(Canvas canvas, Integer person, int position) {
            canvas.drawRect((cellWidth + cellSpace) * position, height - person,
                    (cellWidth + cellSpace) * position + cellWidth, height, paint);
        }


        private void alloc() {
            for (int i = 0; i < people.length; i++) {
                if (people[i] > 0) {
                    people[i]--;
                    people[random(i)]++;
                }
            }
        }

        private int random(int positon) {
            final int r = random.nextInt(100);
            return r != positon ? r : random(positon);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            height = getHeight();
            width = getWidth();

            this.holder = holder;
            new Thread() {
                @Override
                public void run() {
                    int times = 0;
                    while (1000000 >= ++times) {
                        alloc();
                        if (times % 5 == 0)
                            drawPeople(people);
                        Log.i("lyj", "run: " + times + " " + height + " " + width);
                    }
                    Arrays.sort(people);
                    drawPeople(people);
                    Log.i("lyj", "total: " + " \n" + Arrays.toString(people));
                }
            }.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }
}
