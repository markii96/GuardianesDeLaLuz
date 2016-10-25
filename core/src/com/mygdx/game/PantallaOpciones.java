package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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
    private Texture botonConfiguracion;
    private Texture botonHistoria;
    private Texture botonMejoras;

    //CAMARA virtual
    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 800;
    private final int ALTO_MUNDO = 480;

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


        botonConfiguracion = new Texture("configuracion.png");
        botonHistoria = new Texture("historia.png");
        botonInstrucciones = new Texture("instrucciones.png");

        texturaFondo = new Texture("fondo_opciones.png");
        texturaBtnBack = new Texture("back.png");
        botonMejoras = new Texture("mejoras.png");


        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);

        TextureRegionDrawable trdConfiguracion = new TextureRegionDrawable(new TextureRegion(botonConfiguracion));
        ImageButton btnConfiguracion = new ImageButton(trdConfiguracion);
        btnConfiguracion.setPosition(ancho/2 -btnConfiguracion.getWidth()/2, 0.7f*alto);

        TextureRegionDrawable trdHistoria = new TextureRegionDrawable(new TextureRegion(botonHistoria));
        ImageButton btnHistoria = new ImageButton(trdHistoria);
        btnHistoria.setPosition(ancho/2-btnHistoria.getWidth()/2,0.5f*alto);

        TextureRegionDrawable trdInstrucciones = new TextureRegionDrawable(new TextureRegion(botonInstrucciones));
        ImageButton btnInstrucciones = new ImageButton(trdInstrucciones);
        btnInstrucciones.setPosition(ancho/2-btnInstrucciones.getWidth()/2,0.3f*alto);
/*
        TextureRegionDrawable trdBtnMejoras = new TextureRegionDrawable(new TextureRegion(botonMejoras));
        ImageButton btnMejoras = new ImageButton(trdBtnMejoras);
        btnMejoras.setPosition(0,.5f*alto);
*/
        escena = new Stage();


        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new MenuPrincipal(juego));
            }
        });
        Gdx.input.setInputProcessor(escena);


        Image imgFondo = new Image(texturaFondo);
        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX,escalaY);

        escena.addActor(imgFondo);
        escena.addActor(btnBack);
        escena.addActor(btnConfiguracion);
        escena.addActor(btnHistoria);
        escena.addActor(btnInstrucciones);
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

    }

    @Override
    public void dispose() {

    }
}
