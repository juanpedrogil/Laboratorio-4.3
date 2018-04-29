package com.example.juanpedrog.laboratorio43;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Posiciones> posicionesMinute=new ArrayList<Posiciones>();
    private List<Posiciones> posicionesSeconds=new ArrayList<Posiciones>();
    private List<Posiciones> posicionesHours=new ArrayList<Posiciones>();
    private int[] numeros={1,2,3,4,5,6,7,8,9,10,11,12};
    private Rect rect = new Rect();
    private int radio=0;
    public static int index = 0;
    public static int indexMinute=0;
    public static int indexHour=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PapelView papel = new PapelView(this);

        setContentView(papel);
    }


    private class PapelView extends View {

        public PapelView(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
// Objeto Paint
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);

            // Pintar el canvas
            canvas.drawPaint(paint);

            // Obtener dimensiones
            int ancho = canvas.getWidth();
            int alto = canvas.getHeight();
            paint.setColor(Color.WHITE);
            paint.setTextSize(50);
            paint.setAntiAlias(true);

            // Texto
            //canvas.drawText("ancho" + ancho + "altura" + alto, 30, 30, paint);

            // Lineas
            paint.setColor(Color.WHITE);
            //canvas.drawLine(0, 40, ancho, 40, paint);
            //canvas.drawLine(20, 0, 20, alto, paint);

            paint.setTextSize(50);

            int min = Math.min(alto,ancho);
            radio=min/2-30;

            for (int n : numeros) {
                String tmp = String.valueOf(n);
                paint.getTextBounds(tmp, 0, tmp.length(), rect);
                double angle = Math.PI / 6 * (n - 3);
                int x = (int) (ancho / 2 + Math.cos(angle) * radio - rect.width() / 2)-15;
                int y = (int) (ancho / 2 + Math.sin(angle) * radio - rect.width() / 2)+80;
                canvas.drawText(tmp, x, y, paint);

            }
            for (int n : numeros) {
                String tmp = String.valueOf(n);
                paint.getTextBounds(tmp, 0, tmp.length(), rect);
                double angle = Math.PI / 6 * (n - 3);
                int x = (int) (ancho / 2 + Math.cos(angle) * radio - rect.width() / 2)-60;
                int y = (int) (ancho / 2 + Math.sin(angle) * radio - rect.width() / 2)+140;
                posicionesHours.add(new Posiciones(x,y));
            }
            for(int i=0;i<60;i++){
                double angle = Math.PI / 30 * (i - 15);
                int x = (int) (ancho / 2 + Math.cos(angle) * radio - rect.width() / 2)-20;
                int y = (int) (ancho / 2 + Math.sin(angle) * radio - rect.width() / 2)+100;
                posicionesSeconds.add(new Posiciones(x,y));
            }
            for(int i=0;i<60;i++){
                double angle = Math.PI / 30 * (i - 15);
                int x = (int) (ancho / 2 + Math.cos(angle) * radio - rect.width() / 2);
                int y = (int) (ancho / 2 + Math.sin(angle) * radio - rect.width() / 2)+80;
                posicionesMinute.add(new Posiciones(x,y));
            }
            if(MainActivity.index>59){
                MainActivity.index=1;
                MainActivity.indexMinute++;
            }else{
                MainActivity.index++;
            }
            if(MainActivity.indexMinute>59){
                MainActivity.indexMinute=1;
                MainActivity.indexHour++;
            }
            if(MainActivity.indexHour>11){
                MainActivity.indexHour=1;
            }
            canvas.drawLine(259, 332, posicionesSeconds.get(MainActivity.index).x, posicionesSeconds.get(MainActivity.index).y, paint);
            canvas.drawLine(259, 332, posicionesMinute.get(MainActivity.indexMinute).x, posicionesMinute.get(MainActivity.indexMinute).y, paint);
            canvas.drawLine(259, 332, posicionesHours.get(MainActivity.indexHour).x, posicionesHours.get(MainActivity.indexHour).y, paint);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    PapelView papel = new PapelView(MainActivity.this);

                    setContentView(papel);
                }
            }, 100);
        }



    }
}
