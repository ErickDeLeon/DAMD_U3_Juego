package mx.edu.ittepic.damd_u3_juego;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;

public class Imagen {

    Bitmap icono;
    float x,y;
    boolean visible;
    int desplazamiento;
    CountDownTimer timerMisil, timerAliens, timerFuego;

    public  Imagen(final int recurso, float _x, float _y, final Lienzo l, final Imagen img){

        icono = BitmapFactory.decodeResource(l.getResources(),recurso);
        x = _x;
        y = _y;


        timerMisil = new CountDownTimer(1000,100) {
            @Override
            public void onTick(long millisUntilFinished) {

                y-=desplazamiento;

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

        timerAliens = new CountDownTimer(1000,100) {
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

        timerFuego = new CountDownTimer(1000,100) {
            @Override
            public void onTick(long millisUntilFinished) {

                y+=desplazamiento;

                if(y>=l.getHeight()+120){
                    y = img.getY();
                }
                l.invalidate();

            }

            @Override
            public void onFinish() {
                start();
            }

        };



        visible = true;
    }

    public void pintar (Canvas c, Paint p){

        if (visible){
            c.drawBitmap(icono,x,y,p);
        }
    }

    public  void hacerVisible(boolean v){
        visible = v;
    }

    public boolean estaEnArea(float xp, float yp){

        float x2, y2;

        if(!visible) {
            return false;
        }

        x2 = x+icono.getWidth();
        y2 = y+icono.getHeight();

        if(xp>=x && xp<=x2){
            if(yp>=y && yp<=y2){
                return true;
            }
        }
        return false;
    }


    public void mover(float xp){
        x = xp - (icono.getWidth()/2);
    }

    public void moverMisil(int desplaza){
        desplazamiento = desplaza;
        timerMisil.start();
    }

    public void moverAlien(int desplaza){
        desplazamiento = desplaza;
        timerAliens.start();
    }

    public void moverFuego(int desplaza){
        desplazamiento = desplaza;
        timerFuego.start();
    }




    public boolean colision(Imagen objetoB){

        float x2 = x + icono.getWidth();
        float y2 = y + icono.getHeight();

        if(objetoB.estaEnArea(x2,y)){
            //Revisando caso 1
            return true;
        }

        if(objetoB.estaEnArea(x,y)){
            //Revisando caso 2
            return true;
        }

        if(objetoB.estaEnArea(x2,y2)){
            //Revisando caso 3
            return true;
        }

        if(objetoB.estaEnArea(x,y2)){
            //Revisando caso 4
            return true;
        }

        return false;
    }



    public float getY() {
        return y;
    }
}
