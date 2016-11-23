package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by marco on 20/11/2016.
 */
public class PantallaMejorar implements Screen {

    private Juego juego;
    Preferences heroes = Gdx.app.getPreferences("Heroes");
    Preferences oro = Gdx.app.getPreferences("Heroes");
    private int id;

    private Texture texturaFondo;
    private Texture texturaHeroe1;
    private Texture texturaHeroe2;
    private Texture texturaHeroe3;
    private Texture texturaHeroe4;
    private Texture texturaBtnBack;
    private Texture texturaBtnMejorar;
    private Texture texturaBtnMejorar1;

    private int idMensaje =0;

    private Texto1 texto;
    private SpriteBatch batch;
    private Stage escena;

    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 1280;
    private final int ALTO_MUNDO = 800;

    private int nivelFisico;
    private int nivelMagico;
    private int costoFisico;
    private int costoMagico;
    private int vitalidad;
    private int costoVitalidad;

    public PantallaMejorar(Juego juego, int idTexto) {

        this.juego = juego;
        this.id = idTexto;

    }



    @Override
    public void show() {

        camara = new OrthographicCamera(ANCHO_MUNDO,ALTO_MUNDO);
        camara.position.set(ANCHO_MUNDO/2,ALTO_MUNDO/2,0);
        camara.update();
        vista = new StretchViewport(ANCHO_MUNDO,ALTO_MUNDO,camara);
        batch = new SpriteBatch();

        float ancho = ANCHO_MUNDO;//Gdx.graphics.getWidth();
        float alto = ALTO_MUNDO;//Gdx.graphics.getHeight();

        texto = new Texto1();

        texturaFondo = new Texture("tienda.png");
        texturaBtnBack = new Texture("atras.png");
        texturaBtnMejorar = new Texture("mejorar1.png");
        texturaBtnMejorar1 = new Texture("mejorar1.png");
        texturaHeroe1 = new Texture("heroe1.png");
        texturaHeroe2 = new Texture("heroe2.png");
        texturaHeroe3 = new Texture("heroe3.png");
        texturaHeroe4 = new Texture("heroe4.png");

        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);
        btnBack.setPosition(150,93);

        TextureRegionDrawable trdBtnMejorar = new TextureRegionDrawable(new TextureRegion(texturaBtnMejorar));
        ImageButton btnMejorar = new ImageButton(trdBtnMejorar);
        btnMejorar.setPosition(650,525);

        TextureRegionDrawable trdBtnMejorar1 = new TextureRegionDrawable(new TextureRegion(texturaBtnMejorar1));
        ImageButton btnMejorar1 = new ImageButton(trdBtnMejorar1);
        btnMejorar1.setPosition(650,350);

        escena = new Stage();

        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new PantallaTaberna(juego));
            }
        });

        btnMejorar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //proceso
                int nivelMejora=0;
                int oroTotal =oro.getInteger("1");

                java.lang.String cadena = heroes.getString("1");
                java.lang.String[] lista = cadena.split("-");
                java.lang.String dato = lista[3];
                int vida1=0;
                int danoFisico1 =0;
                int vitalidad1 =0;
                int costoVitalidad1=0;
                int nivelFisico1=0;
                int costoFisico1=0;

                if(id==1){

                    switch (vida1){
                        case 100: vitalidad1 = 1; break;
                        case 150: vitalidad1 = 2;break;
                        case 200: vitalidad1 = 3; break;
                        case 250: vitalidad1 = 4; break;
                        case 300: vitalidad1 = 5; break;
                    }

                    switch (vitalidad1){

                        case 1: costoVitalidad1 = 250; break;
                        case 2: costoVitalidad1 = 500; break;
                        case 3: costoVitalidad1 = 750; break;
                        case 4: costoVitalidad1 = 1000; break;
                        case 5: costoVitalidad1 = 1250; break;

                    }

                    switch (danoFisico1){
                        case 100: nivelFisico1 = 1;break;
                        case 120: nivelFisico1 = 2;break;
                        case 140: nivelFisico1 = 3;break;
                        case 160: nivelFisico1 = 4;break;
                        case 180: nivelFisico1 = 5;break;
                    }

                    switch (nivelFisico1){

                        case 1: costoFisico1 = 200;break;
                        case 2: costoFisico1 = 400;break;
                        case 3: costoFisico1 = 600;break;
                        case 4: costoFisico1 = 800;break;
                        case 5: ;break;
                    }


                }

                if(id==2){

                }

                if(id==3){

                }

                if(id==4){

                }


                int costoMejora=Integer.valueOf(dato);
                if(nivelMejora>=1 && nivelMejora<=4){

                    if(oroTotal < costoMejora){
                        idMensaje =2;
                    }

                    else{
                        idMensaje=1;
                    }

                }

                else{
                    idMensaje=3;
                }
            }
        });

        btnMejorar1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //proceso
            }
        });

        Gdx.input.setInputProcessor(escena);

        Image imgFondo = new Image(texturaFondo);
        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX,escalaY);

        escena.addActor(imgFondo);

        if (id==1){
            Image imgHeroe1 = new Image(texturaHeroe1);
            imgHeroe1.setScale(.5f);
            imgHeroe1.setPosition(900,350);
            escena.addActor(imgHeroe1);

            java.lang.String cadena1=heroes.getString("1");


            java.lang.String[] lista1 =cadena1.split("-");
            java.lang.String danoFisico = lista1[3];
            int danoFisico1 = (Integer.parseInt(danoFisico));

            java.lang.String vida = lista1[8];
            int vida1 = Integer.parseInt(vida);

            switch (vida1){
                case 100: vitalidad = 1; break;
                case 150: vitalidad = 2;break;
                case 200: vitalidad = 3; break;
                case 250: vitalidad = 4; break;
                case 300: vitalidad = 5; break;
            }

            switch (vitalidad){

                case 1: costoVitalidad = 250; break;
                case 2: costoVitalidad = 500; break;
                case 3: costoVitalidad = 750; break;
                case 4: costoVitalidad = 1000; break;
                case 5: costoVitalidad = 1250; break;

            }

            switch (danoFisico1){
                case 100: nivelFisico = 1;break;
                case 120: nivelFisico = 2;break;
                case 140: nivelFisico = 3;break;
                case 160: nivelFisico = 4;break;
                case 180: nivelFisico = 5;break;
            }

            switch (nivelFisico){

                case 1: costoFisico = 200;break;
                case 2: costoFisico = 400;break;
                case 3: costoFisico = 600;break;
                case 4: costoFisico = 800;break;
                case 5: ;break;
            }
        }

        if(id==2) {
            Image imgHeroe2 = new Image(texturaHeroe2);
            imgHeroe2.setScale(.5f);
            imgHeroe2.setPosition(900,350);
            escena.addActor(imgHeroe2);

            java.lang.String cadena1=heroes.getString("2");


            java.lang.String[] lista1 =cadena1.split("-");
            java.lang.String danoMagico = lista1[4];
            int danoMagico1 = (Integer.parseInt(danoMagico));

            java.lang.String vida = lista1[8];
            int vida1 = Integer.parseInt(vida);

            switch (vida1){
                case 40: vitalidad = 1; break;
                case 90: vitalidad = 2;break;
                case 140: vitalidad = 3; break;
                case 190: vitalidad = 4; break;
                case 240: vitalidad = 5; break;
            }

            switch (vitalidad){

                case 1: costoVitalidad = 250; break;
                case 2: costoVitalidad = 500; break;
                case 3: costoVitalidad = 750; break;
                case 4: costoVitalidad = 1000; break;
                case 5: costoVitalidad = 1250; break;

            }

            switch (danoMagico1){
                case 100: nivelMagico = 1;break;
                case 120: nivelMagico = 2;break;
                case 140: nivelMagico = 3;break;
                case 160: nivelMagico = 4;break;
                case 180: nivelMagico = 5;break;
            }

            switch (nivelMagico){

                case 1: costoMagico = 200;break;
                case 2: costoMagico = 400;break;
                case 3: costoMagico = 600;break;
                case 4: costoMagico = 800;break;
                case 5: ;break;
            }
        }

        if(id==3) {
            Image imgHeroe3 = new Image(texturaHeroe3);
            imgHeroe3.setScale(.5f);
            imgHeroe3.setPosition(900, 350);
            escena.addActor(imgHeroe3);

            java.lang.String cadena1=heroes.getString("3");


            java.lang.String[] lista1 =cadena1.split("-");
            java.lang.String danoFisico = lista1[3];
            int danoFisico1 = (Integer.parseInt(danoFisico));

            java.lang.String vida = lista1[8];
            int vida1 = Integer.parseInt(vida);

            switch (vida1){
                case 50: vitalidad = 1; break;
                case 100: vitalidad = 2;break;
                case 150: vitalidad = 3; break;
                case 200: vitalidad = 4; break;
                case 250: vitalidad = 5; break;
            }

            switch (vitalidad){

                case 1: costoVitalidad = 250; break;
                case 2: costoVitalidad = 500; break;
                case 3: costoVitalidad = 750; break;
                case 4: costoVitalidad = 1000; break;
                case 5: costoVitalidad = 1250; break;

            }

            switch (danoFisico1){
                case 70: nivelFisico = 1;break;
                case 90: nivelFisico = 2;break;
                case 110: nivelFisico = 3;break;
                case 130: nivelFisico = 4;break;
                case 150: nivelFisico = 5;break;
            }

            switch (nivelFisico){

                case 1: costoFisico = 200;break;
                case 2: costoFisico = 400;break;
                case 3: costoFisico = 600;break;
                case 4: costoFisico = 800;break;
                case 5: ;break;
            }

        }

        if(id==4) {
            Image imgHeroe4 = new Image(texturaHeroe4);
            imgHeroe4.setScale(.5f);
            imgHeroe4.setPosition(900, 350);
            escena.addActor(imgHeroe4);

            java.lang.String cadena1=heroes.getString("4");


            java.lang.String[] lista1 =cadena1.split("-");
            java.lang.String danoFisico = lista1[3];
            int danoFisico1 = (Integer.parseInt(danoFisico));

            java.lang.String vida = lista1[8];
            int vida1 = Integer.parseInt(vida);

            switch (vida1){
                case 70: vitalidad = 1; break;
                case 120: vitalidad = 2;break;
                case 170: vitalidad = 3; break;
                case 230: vitalidad = 4; break;
                case 280: vitalidad = 5; break;
            }

            switch (vitalidad){

                case 1: costoVitalidad = 250; break;
                case 2: costoVitalidad = 500; break;
                case 3: costoVitalidad = 750; break;
                case 4: costoVitalidad = 1000; break;
                case 5: costoVitalidad = 1250; break;

            }

            switch (danoFisico1){
                case 150: nivelFisico = 1;break;
                case 170: nivelFisico = 2;break;
                case 190: nivelFisico = 3;break;
                case 210: nivelFisico = 4;break;
                case 230: nivelFisico = 5;break;
            }

            switch (nivelFisico){

                case 1: costoFisico = 200;break;
                case 2: costoFisico = 400;break;
                case 3: costoFisico = 600;break;
                case 4: costoFisico = 800;break;
                case 5: ;break;
            }

        }


        escena.addActor(btnBack);
        escena.addActor(btnMejorar1);
        escena.addActor(btnMejorar);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escena.setViewport(vista);
        escena.draw();

        batch.begin();

        batch.end();

        batch.begin();

        //mostrar texto

        if(id==1){

            texto.mostrarMensaje(batch,java.lang.String.valueOf(costoFisico),450,350);
            texto.mostrarMensaje(batch,java.lang.String.valueOf(costoVitalidad),450,500);

        }

        if(id==2){

            texto.mostrarMensaje(batch,java.lang.String.valueOf(costoMagico),450,350);
            texto.mostrarMensaje(batch,java.lang.String.valueOf(costoVitalidad),450,500);

        }

        if(id==3){

            texto.mostrarMensaje(batch,java.lang.String.valueOf(costoFisico),450,350);
            texto.mostrarMensaje(batch,java.lang.String.valueOf(costoVitalidad),450,500);

        }

        if(id==4){

            texto.mostrarMensaje(batch,java.lang.String.valueOf(costoFisico),450,350);
            texto.mostrarMensaje(batch,java.lang.String.valueOf(costoVitalidad),450,500);

        }

        if(idMensaje==1){
            texto.mostrarMensaje(batch,"Se hizo la mejora",200,200);
        }

        if(idMensaje==2){
            texto.mostrarMensaje(batch,"No tienes oro suficiente",200,200);
        }

        if(idMensaje==3){
            texto.mostrarMensaje(batch,"Mejora al máximo",200,200);
        }

        if(idMensaje==0){
            texto.mostrarMensaje(batch,"",0,0);
        }

        batch.end();


    }

    @Override
    public void resize(int width, int height) {
        vista.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
