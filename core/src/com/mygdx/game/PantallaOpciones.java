package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by marco on 01/09/2016.
 */
public class PantallaOpciones implements Screen {

    private final Juego juego;
    private Stage escena;
    private Texture texturaBtnBack;
    private Texture texturaFondo;
    private Texture botonInstrucciones;
    private Texture botonSonidoOn;
    private Texture botonSonidoOff;
    private Texture botonHistoria;
    private Texture botonMejoras;
    private int banderaSonido = 1;
    Preferences validacion = Gdx.app.getPreferences("Validacion");

    //CAMARA virtual
    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 1280;
    private final int ALTO_MUNDO = 800;

    public PantallaOpciones(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {

        // camara

        camara = new OrthographicCamera(ANCHO_MUNDO,ALTO_MUNDO);
        camara.position.set(ANCHO_MUNDO/2,ALTO_MUNDO/2,0);
        camara.update();
        vista = new StretchViewport(ANCHO_MUNDO,ALTO_MUNDO,camara);



        float ancho = ANCHO_MUNDO;//Gdx.graphics.getWidth();
        float alto = ALTO_MUNDO;//Gdx.graphics.getHeight();


        //botonConfiguracion = new Texture("configuracion.png");
        botonHistoria = new Texture("historia.png");
        botonInstrucciones = new Texture("instrucciones.png");
        botonSonidoOn = new Texture("on.png");
        botonSonidoOff = new Texture("off.png");
        texturaFondo = new Texture("fondo_opciones.png");
        texturaBtnBack = new Texture("atras.png");
        botonMejoras = new Texture("mejoras.png");


        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);

        TextureRegionDrawable trdHistoria = new TextureRegionDrawable(new TextureRegion(botonHistoria));
        ImageButton btnHistoria = new ImageButton(trdHistoria);
        btnHistoria.setPosition(ancho/2-btnHistoria.getWidth()/2,0.4f*alto);

        TextureRegionDrawable trdInstrucciones = new TextureRegionDrawable(new TextureRegion(botonInstrucciones));
        ImageButton btnInstrucciones = new ImageButton(trdInstrucciones);
        //btnInstrucciones.setScale(.1f,.1f);
        btnInstrucciones.setPosition(ancho/2-btnInstrucciones.getWidth()/2,0.2f*alto);


        TextureRegionDrawable trdSonidoOn = new TextureRegionDrawable(new TextureRegion(botonSonidoOn));
        final ImageButton btnSonidoOn = new ImageButton(trdSonidoOn);
        //btnSonidoOn.setPosition(1100,0);

        TextureRegionDrawable trdSonidoOff = new TextureRegionDrawable(new TextureRegion(botonSonidoOff));
        final ImageButton btnSonidoOff = new ImageButton(trdSonidoOff);
        //btnSonidoOff.setPosition(900,0);



        String bandera = validacion.getString("2");

        if(bandera=="1"){
            btnSonidoOff.setPosition(1100,0);
            btnSonidoOn.setPosition(2000,2000);

        }

        else {
            btnSonidoOff.setPosition(2000,2000);
            btnSonidoOn.setPosition(1100,0);

        }

        escena = new Stage();

        btnInstrucciones.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new PantallaInstrucciones(juego));
            }
        });

        btnHistoria.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //regresar al menu principal
                Gdx.app.log("Cliced","Tap sobre instrucciones");
                juego.setScreen(new PantallaHistoria(juego));

            }
        });

        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //regresar al menu principal
                juego.setScreen(new MenuPrincipal(juego));
            }
        });

        btnSonidoOn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnSonidoOff.setPosition(1100,0);
                banderaSonido --;
                System.out.println("clicked on");
                validacion.putString("2","1");

                btnSonidoOn.setPosition(2000,2000);


            }
        });

        btnSonidoOff.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnSonidoOn.setPosition(1100,0);

                banderaSonido ++;

                System.out.println("clicked off");
                validacion.putString("2","0");

                btnSonidoOff.setPosition(2000,2000);
            }
        });



        Gdx.input.setInputProcessor(escena);

        Image imgFondo = new Image(texturaFondo);
        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX,escalaY);
        //btnInstrucciones.setScale(.4f);
        Gdx.app.getPreferences("Validacion");

        //if(Gdx.app.getPreferences("Validacion"))



        escena.addActor(imgFondo);

        //escena.addActor(btnConfiguracion);
        escena.addActor(btnHistoria);
        escena.addActor(btnInstrucciones);
        escena.addActor(btnBack);


            escena.addActor(btnSonidoOn);


            escena.addActor(btnSonidoOff);



      //  escena.addActor(btnMejoras);

    }

    @Override
    public void render(float delta) {



        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escena.setViewport(vista);
        escena.draw();
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
        dispose();
    }

    @Override
    public void dispose() {
        texturaBtnBack.dispose();
        botonHistoria.dispose();
        texturaFondo.dispose();

    }
}
