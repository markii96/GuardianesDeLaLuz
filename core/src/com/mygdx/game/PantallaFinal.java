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
 * Created by marco on 22/11/2016.
 */
public class PantallaFinal implements Screen {

    private Juego juego;

    private Texture texturaFondo;
    private Texture texturaBtnBack;

    private Texto texto;

    private Stage escena;

    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 1280;
    private final int ALTO_MUNDO = 800;

    private SpriteBatch batch;

    public PantallaFinal(Juego juego){

        this.juego = juego;

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

        texturaBtnBack = new Texture("atras.png");

        TextureRegionDrawable trdBrnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBrnBack);


        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new MenuPrincipal(juego));
            }
        });

        Gdx.input.setInputProcessor(escena);

        texturaFondo = new Texture("end game.png");
        Image imgFondo = new Image(texturaFondo);

        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX,escalaY);

        escena.addActor(imgFondo);
        escena.addActor(btnBack);

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

        //texto.mostrarMensaje(batch,"",300,600);

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