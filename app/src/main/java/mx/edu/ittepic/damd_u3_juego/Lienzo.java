package mx.edu.ittepic.damd_u3_juego;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

public class Lienzo extends View {

    Imagen fondo, nave, gameover, intentar, puntero, misil01, punteroMisil, alien01, alien02, alien03, puntero01, puntero02, fuego01, fuego02, fuego03, win;
    Palma isla01, isla02, isla03, isla04,isla05,isla06, isla07,isla08,isla09;
    String puntuacion;
    int puntaje = 0;

    MediaPlayer player, playerwinner, playergameover;

    MainActivity inicio;

    public Lienzo(Context context) {
        super(context);


        puntuacion = "Score: 0";

        fondo = new Imagen(R.drawable.fondo, 0, 0, this, fondo);

        nave = new Imagen(R.drawable.naveimg, 750, 2000,this, nave);

        gameover = new Imagen(R.drawable.gameover, 450,650,this, gameover);
        gameover.hacerVisible(false);

        intentar = new Imagen(R.drawable.tryagain, 600, 1800,this, intentar);
        intentar.hacerVisible(false);

        win = new Imagen(R.drawable.winner,450,650,this,win);
        win.hacerVisible(false);




        isla01 = new Palma(R.drawable.islaimg,0,0,this);
        isla01.moverPalma(20);
        isla02 = new Palma(R.drawable.islaimg,200,-100,this);
        isla02.moverPalma(5);
        isla03 = new Palma(R.drawable.islaimg,410,-200,this);
        isla03.moverPalma(15);
        isla04 = new Palma(R.drawable.islaimg,600,-150,this);
        isla04.moverPalma(22);
        isla05 = new Palma(R.drawable.islaimg,800,-300,this);
        isla05.moverPalma(10);
        isla06 = new Palma(R.drawable.islaimg,1000,-100,this);
        isla06.moverPalma(2);
        isla07 = new Palma(R.drawable.islaimg,1200,0,this);
        isla07.moverPalma(17);
        isla08 = new Palma(R.drawable.islaimg,1400,-300,this);
        isla08.moverPalma(10);
        isla09 = new Palma(R.drawable.islaimg,1600,-100,this);
        isla09.moverPalma(5);


        alien01 = new Imagen(R.drawable.alienimg, 720,150,this, alien01);
        alien01.moverAlien(20);
        puntero01 = alien01;
        fuego01 = new Imagen(R.drawable.balaimg,855,150, this, alien01);
        fuego01.moverFuego(40);



        alien02 = new Imagen(R.drawable.alienimg, 100,-250,this, alien02);
        alien02.moverAlien(30);
        puntero02 = alien02;
        fuego02 = new Imagen(R.drawable.balaimg,250,-250,this, alien02);
        fuego02.moverFuego(40);


        alien03 = new Imagen(R.drawable.alienimg, 1400,-250,this, alien03);
        alien03.moverAlien(30);
        puntero02 = alien03;
        fuego03 = new Imagen(R.drawable.balaimg,1525,-255,this, alien03);
        fuego03.moverFuego(40);



        misil01 = new Imagen(R.drawable.misilimg,850,2300,this, misil01);

        puntero = null; //Para que pueda ser puntero tiene que declararse a nulo
        punteroMisil = null;



        inicio = (MainActivity) context;

        player = MediaPlayer.create(inicio, R.raw.soundjuego);
        player.start();

        playerwinner = MediaPlayer.create(inicio, R.raw.soundgameover);

        playergameover = MediaPlayer.create(inicio,R.raw.soundover);

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                player.start();
            }
        });

    }

    @Override
    protected void onDraw(Canvas c){
        super.onDraw(c);
        Paint p = new Paint();

        fondo.pintar(c, p);

        isla01.pintarPalma(c,p);
        isla02.pintarPalma(c,p);
        isla03.pintarPalma(c,p);
        isla04.pintarPalma(c,p);
        isla05.pintarPalma(c,p);
        isla06.pintarPalma(c,p);
        isla07.pintarPalma(c,p);
        isla08.pintarPalma(c,p);
        isla09.pintarPalma(c,p);

        misil01.pintar(c,p);


        fuego01.pintar(c,p);
        fuego02.pintar(c,p);
        fuego03.pintar(c,p);

        alien01.pintar(c,p);
        alien02.pintar(c,p);
        alien03.pintar(c,p);



        nave.pintar(c,p);


        p.setTextSize(100);
        p.setColor(Color.WHITE);
        c.drawText(puntuacion,50,100,p);



        gameover.pintar(c,p);
        intentar.pintar(c,p);

        win.pintar(c,p);


        if(nave.colision(alien01)==true || nave.colision(alien02)==true || nave.colision(alien03)==true){
            nave.hacerVisible(false);
            alien01.hacerVisible(false);
            alien02.hacerVisible(false);
            alien03.hacerVisible(false);
            fuego01.hacerVisible(false);
            fuego02.hacerVisible(false);
            fuego03.hacerVisible(false);
            gameover.hacerVisible(true);
            intentar.hacerVisible(true);

            player.stop();
            playergameover.start();

        }


        if(fuego01.colision(nave)==true || fuego02.colision(nave)==true || fuego03.colision(nave)==true){

            nave.hacerVisible(false);
            alien01.hacerVisible(false);
            alien02.hacerVisible(false);
            alien03.hacerVisible(false);
            fuego01.hacerVisible(false);
            fuego02.hacerVisible(false);
            fuego03.hacerVisible(false);
            gameover.hacerVisible(true);
            intentar.hacerVisible(true);

            player.stop();
            playergameover.start();

        }


        if(misil01.colision(alien01)==true){
            alien01.hacerVisible(false);
            alien01 = new Imagen(R.drawable.alienimg, 720,-250,this, alien01);
            alien01.moverAlien(20);

            misil01 = new Imagen(R.drawable.misilimg,-50,2600,this, misil01);

            fuego01 = new Imagen(R.drawable.balaimg,855,-255, this, alien01);
            fuego01.moverFuego(60);

            puntaje = puntaje +100;
            puntuacion = "Score: " + puntaje;
        }

        if(misil01.colision(alien02)==true){
            alien02.hacerVisible(false);
            alien02 = new Imagen(R.drawable.alienimg, 100,-250,this, alien02);
            alien02.moverAlien(20);

            misil01 = new Imagen(R.drawable.misilimg,-50,2600,this, misil01);

            fuego02 = new Imagen(R.drawable.balaimg,250,-250,this, alien02);
            fuego02.moverFuego(60);

            puntaje = puntaje +100;
            puntuacion = "Score: " + puntaje;

        }

        if(misil01.colision(alien03)==true){
            alien03.hacerVisible(false);

            alien03 = new Imagen(R.drawable.alienimg, 1400,-250,this, alien03);
            alien03.moverAlien(20);
            misil01 = new Imagen(R.drawable.misilimg,-50,2600,this, misil01);

            fuego03 = new Imagen(R.drawable.balaimg,1525,-255,this, alien03);
            fuego03.moverFuego(60);

            puntaje = puntaje +100;
            puntuacion = "Score: " + puntaje;
        }

        if (puntaje == 500){

            nave.hacerVisible(false);
            alien01.hacerVisible(false);
            alien02.hacerVisible(false);
            alien03.hacerVisible(false);
            fuego01.hacerVisible(false);
            fuego02.hacerVisible(false);
            fuego03.hacerVisible(false);
            win.hacerVisible(true);
            intentar.hacerVisible(true);

            player.pause();
            playerwinner.start();

        }

    }


    public boolean onTouchEvent(MotionEvent e){

        float xp = e.getX();
        float yp = e.getY();

        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (nave.estaEnArea(xp,yp)){
                    puntero = nave;
                }
                if (intentar.estaEnArea(xp,yp)){
                    cargarPantalla();
                }

                break;

            case MotionEvent.ACTION_MOVE:
                if (puntero!=null){
                    if(nave.estaEnArea(xp,yp)){
                        nave.mover(xp);
                    }
                }

                break;

            case MotionEvent.ACTION_UP:

                if (puntero!=null){
                    if(nave.estaEnArea(xp,yp)){

//                        if(misil01.visible=true && ){
                            misil01 = new Imagen(R.drawable.misilimg,xp,2050,this, misil01);
                            misil01.moverMisil(25);
                            punteroMisil = misil01;

                        //}

                    }
                }

               // puntero = null;
                break;
        }
        invalidate();
        return true;
    }

    private void cargarPantalla() {

        Intent pantalla = new Intent(inicio,MainActivity.class);
        inicio.startActivity(pantalla);

        playerwinner.stop();

    }


}
