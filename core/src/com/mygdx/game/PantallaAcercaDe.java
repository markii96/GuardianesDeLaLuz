package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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


public class PantallaAcercaDe implements Screen {

    private final Juego juego;
    private Stage escena;
    private Texture texturaBtnBack;
    private Texture texturaMarco;
    private Texture texturaHector;
    private Texture texturaJosep;
    private Texture texturaRichard;

    private Texto texto;
    private SpriteBatch batch;
    //1
    private Texture texturaFondo;


    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 1280;
    private final int ALTO_MUNDO = 800;


    public PantallaAcercaDe(Juego juego){

        this.juego = juego;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        // camara

        texto = new Texto();



        camara = new OrthographicCamera(ANCHO_MUNDO,ALTO_MUNDO);
        camara.position.set(ANCHO_MUNDO/2,ALTO_MUNDO/2,0);
        camara.update();
        vista = new StretchViewport(ANCHO_MUNDO,ALTO_MUNDO,camara);

        float ancho = ANCHO_MUNDO;//Gdx.graphics.getWidth();
        float alto = ALTO_MUNDO;//Gdx.graphics.getHeight();
        //2
        texturaFondo = new Texture("bosque.png");
        texturaBtnBack = new Texture("atras.png");
        texturaMarco = new Texture("marco1.jpg");
        texturaHector = new Texture("hector1.jpg");
        texturaJosep = new Texture("josep1.jpg");
        texturaRichard = new Texture("ricardo1.jpg");

        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);
        //btnBack.setPosition(ancho/3-btnBack.getWidth()/2,alto*0.05f);

        TextureRegionDrawable trdMarco = new TextureRegionDrawable(new TextureRegion(texturaMarco));
        ImageButton btnMarco = new ImageButton(trdMarco);
        btnMarco.setPosition(150,125);
        //btnMarco.setScale(2f);

        TextureRegionDrawable trdHector = new TextureRegionDrawable(new TextureRegion(texturaHector));
        ImageButton btnHector = new ImageButton(trdHector);
        btnHector.setPosition(400,125);
        //btnHector.setScale(2f);

        TextureRegionDrawable trdRichard = new TextureRegionDrawable(new TextureRegion(texturaRichard));
        ImageButton btnRichard = new ImageButton(trdRichard);
        //btnRichard.setScale(2f);
        btnRichard.setPosition(650,125);

        TextureRegionDrawable trdJosep = new TextureRegionDrawable(new TextureRegion(texturaJosep));
        ImageButton btnJosep = new ImageButton(trdJosep);
        btnJosep.setPosition(900,125);
        //btnJosep.setScale(2f);
        
        escena = new Stage();

        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //regresar al menu principal
                juego.setScreen(new MenuPrincipal(juego));

            }
        });

        btnMarco.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //regresar al menu principal
                Gdx.app.log("Clicked", "marco");
                juego.setScreen(new PantallaCreadores(juego,0));

            }
        });

        btnHector.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked", "hector");
                //regresar al menu principal
                juego.setScreen(new PantallaCreadores(juego,1));

            }
        });

        btnRichard.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked", "ricardo");
                //regresar al menu principal
                juego.setScreen(new PantallaCreadores(juego,2));

            }
        });

        btnJosep.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Gdx.app.log("Clicked", "josep");
                //regresar al menu principal
                juego.setScreen(new PantallaCreadores(juego,3));

            }
        });

        Gdx.input.setInputProcessor(escena);

        //3 el fondo
        Image imgFondo = new Image(texturaFondo);
        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX,escalaY);

        escena.addActor(imgFondo);
        escena.addActor(btnBack);
        escena.addActor(btnHector);
        escena.addActor(btnMarco);
        escena.addActor(btnJosep);
        escena.addActor(btnRichard);





    }




    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);
        escena.setViewport(vista);
        batch.begin();
        escena.draw();



        batch.end();

        batch.begin();
        texto.mostrarMensaje(batch,"Proyecto de desarrollo de videojuegos",600,600);
        texto.mostrarMensaje(batch,"ITESM Campus Estado de México",600,500);

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
        texturaBtnBack.dispose();
        texturaFondo.dispose();
        texturaHector.dispose();
        texturaJosep.dispose();
        texturaMarco.dispose();
        texturaRichard.dispose();
    }
}
