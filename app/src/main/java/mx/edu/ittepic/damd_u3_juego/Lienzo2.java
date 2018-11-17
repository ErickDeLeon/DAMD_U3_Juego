package mx.edu.ittepic.damd_u3_juego;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Lienzo2 extends View {

    Imagen2 fondo, titulo, inicio, puntero;
    Main2Activity puntero2;
    MediaPlayer player;



    public Lienzo2 (Context context){
        super(context);

        fondo = new Imagen2(R.drawable.fondo, 0, 0, this);
        titulo = new Imagen2(R.drawable.title02, 120, 170,this);
        inicio = new Imagen2(R.drawable.playgame, 700, 1400,this);

        puntero2 = (Main2Activity) context;

        player = MediaPlayer.create(puntero2, R.raw.soundinicio);
        player.start();

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                player.start();
            }
        });
    }

    protected void onDraw(Canvas c){
        super.onDraw(c);
        Paint p = new Paint();

        fondo.pintar(c, p);
        titulo.pintar(c,p);
        inicio.pintar(c,p);
    }

    public boolean onTouchEvent(MotionEvent e){

        float xp = e.getX();
        float yp = e.getY();

        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(inicio.estaEnArea(xp,yp)){
                        iniciarJuego();
                }

                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                puntero = null;
                break;
        }

        invalidate();
        return true;

    }

    private void iniciarJuego() {

        Intent pantalla = new Intent(puntero2,MainActivity.class);
        puntero2.startActivity(pantalla);
        player.stop();


    }

}
