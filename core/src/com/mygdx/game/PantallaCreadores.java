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

/**
 * Created by marco on 03/11/2016.
 */
public class PantallaCreadores implements Screen {

    private final Juego juego;
    private Stage escena;
    private int id;

    private Texture texturaBtnBack;
    private Texture texturaMarco;
    private Texture texturaHector;
    private Texture texturaJosep;
    private Texture texturaRichard;

    private SpriteBatch batch;

    private Texture texturaFondo;

    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 1280;
    private final int ALTO_MUNDO = 800;

    private Texto texto;

    public PantallaCreadores(Juego juego, int id){

        this.juego = juego;
        this.id = id;
    }


    @Override
    public void show() {

        batch = new SpriteBatch();

        camara = new OrthographicCamera(ANCHO_MUNDO,ALTO_MUNDO);
        camara.position.set(ANCHO_MUNDO/2,ALTO_MUNDO/2,0);
        camara.update();
        vista = new StretchViewport(ANCHO_MUNDO,ALTO_MUNDO,camara);

        float ancho = ANCHO_MUNDO;//Gdx.graphics.getWidth();
        float alto = ALTO_MUNDO;//Gdx.graphics.getHeight();
        //2
        texturaFondo = new Texture("nieve.png");
        texturaBtnBack = new Texture("atras.png");
        texturaMarco = new Texture("marco.jpg");
        texturaHector = new Texture("hector.jpg");
        texturaJosep = new Texture("josep.jpg");
        texturaRichard = new Texture("ricardo.jpg");

        texto = new Texto();

        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);

        escena = new Stage();

        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //regresar al menu principal
                juego.setScreen(new PantallaAcercaDe(juego));

            }
        });



        Gdx.input.setInputProcessor(escena);

        //3 el fondo
        Image imgFondo = new Image(texturaFondo);
        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX,escalaY);

        Image imgMarco = new Image(texturaMarco);
        Image imgHector = new Image(texturaHector);
        Image imgJosep = new Image(texturaJosep);
        Image imgRichard = new Image(texturaRichard);

        escena.addActor(imgFondo);
        escena.addActor(btnBack);

        if(this.id==0){
            imgMarco.setPosition(900,300);
            imgMarco.setScale(2.5f);
            escena.addActor(imgMarco);

        }

        if(this.id==1){
            imgHector.setPosition(900,300);
            imgHector.setScale(2.5f);
            escena.addActor(imgHector);

        }

        if(this.id==3){

            imgJosep.setPosition(900,300);
            imgJosep.setScale(2.5f);
            escena.addActor(imgJosep);

        }

        if(this.id ==2){

            imgRichard.setPosition(900,300);
            imgRichard.setScale(2.5f);
            escena.addActor(imgRichard);

        }

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);
        escena.setViewport(vista);
        batch.begin();

        escena.draw();

        //texto.mostrarMensaje(batch,"Nombre: Marco Buendia ",120,300);

//1280x800

        batch.end();


        batch.begin();

        switch(id){

            case 0:texto.mostrarMensaje(batch,"Nombre: Marco Buendia",300,450);texto.mostrarMensaje(batch,"Carrera: Sistemas computacionales",425,350);texto.mostrarMensaje(batch,"Correo: marco_isaac_1996@hotmail.com",487,250);break;
            case 1:texto.mostrarMensaje(batch,"Nombre: Hector Medina ",300,450);texto.mostrarMensaje(batch,"Carrera: Animacion y arte digital",425,350);texto.mostrarMensaje(batch,"Correo: hmedinaramirez@gmail.com",487,250);break;
            case 2:texto.mostrarMensaje(batch,"Nombre: Ricardo Gutierrez",300,450);texto.mostrarMensaje(batch,"Carrera: Sistemas computacionales",425,350);texto.mostrarMensaje(batch,"Correo: ricardo.strifeff@gmail.com",487,250);break;
            case 3:texto.mostrarMensaje(batch,"Nombre: Josep Romagosa",300,450);texto.mostrarMensaje(batch,"Carrera: Sistemas computacionales",425,350);texto.mostrarMensaje(batch,"Correo: josep@hotmail.com",487,250);break;

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
