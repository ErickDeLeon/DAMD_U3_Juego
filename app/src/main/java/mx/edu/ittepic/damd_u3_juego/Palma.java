package mx.edu.ittepic.damd_u3_juego;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;

public class Palma {


    Bitmap icono;
    float x,y;
    int desplazamiento;
    CountDownTimer timer;

    public Palma(int recurso,int _x, int _y, final Lienzo l){

        icono = BitmapFactory.decodeResource(l.getResources(),recurso);
        x = _x;
        y = _y;

        timer = new CountDownTimer(1000,100) {
            @Override
            public void onTick(long millisUntilFinished) {

                y+=desplazamiento;

                if(y>=l.getHeight()+120){
                    y = -120;
                }
                l.invalidate();

            }

            @Override
            public void onFinish() {
                start();
            }
        };
    }

    public void pintarPalma(Canvas c, Paint p){

        c.drawBitmap(icono,x,y,p);

    }

    public void moverPalma(int desplaza){
        desplazamiento = desplaza;
        timer.start();
    }


}
