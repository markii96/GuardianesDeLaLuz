package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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

/**
 * Created by marco on 20/11/2016.
 */
public class PantallaMejorar implements Screen {

    private Juego juego;
    private int id;

    private Texture texturaFondo;
    private Texture texturaHeroe1;
    private Texture texturaHeroe2;
    private Texture texturaHeroe3;
    private Texture texturaHeroe4;
    private Texture texturaBtnBack;
    private Texture texturaBtnMejorar;
    private Texture texturaBtnMejorar1;

    private Texto texto;
    private SpriteBatch batch;
    private Stage escena;

    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 1280;
    private final int ALTO_MUNDO = 800;

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

        texto = new Texto();

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
        }

        if(id==2) {
            Image imgHeroe2 = new Image(texturaHeroe2);
            imgHeroe2.setScale(.5f);
            imgHeroe2.setPosition(900,350);
            escena.addActor(imgHeroe2);
        }

        if(id==3) {
            Image imgHeroe3 = new Image(texturaHeroe3);
            imgHeroe3.setScale(.5f);
            imgHeroe3.setPosition(900,350);
            escena.addActor(imgHeroe3);
        }
        if(id==4) {
            Image imgHeroe4 = new Image(texturaHeroe4);
            imgHeroe4.setScale(.5f);
            imgHeroe4.setPosition(900,350);
            escena.addActor(imgHeroe4);
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

        //mostrar oro

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
