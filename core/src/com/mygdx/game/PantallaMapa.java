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
 * Created by marco on 17/11/2016.
 */
public class PantallaMapa implements Screen {

    private final Juego juego;
    private Stage escena;
    private Texture texturaBtnBack;
    private Texture texturaFondo;
    private Texture textura1;
    private Texture textura2;
    private Texture textura3;
    private Texture textura4;

    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 1280;
    private final int ALTO_MUNDO = 800;




    public PantallaMapa(Juego juego){
        this.juego = juego;
    }

    @Override
    public void show() {

        camara = new OrthographicCamera(ANCHO_MUNDO,ALTO_MUNDO);
        camara.position.set(ANCHO_MUNDO/2,ALTO_MUNDO/2,0);
        camara.update();
        vista = new StretchViewport(ANCHO_MUNDO,ALTO_MUNDO,camara);

        float ancho = ANCHO_MUNDO;//Gdx.graphics.getWidth();
        float alto = ALTO_MUNDO;//Gdx.graphics.getHeight();

        texturaFondo = new Texture("mapa.png");

        texturaBtnBack = new Texture("atras.png");
        textura1 = new Texture("1.png");
        textura2 = new Texture("2.png");
        textura3 = new Texture("3.png");
        textura4 = new Texture("4.png");


        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);


        TextureRegionDrawable trd1 = new TextureRegionDrawable(new TextureRegion(textura1));
        ImageButton btn1 = new ImageButton(trd1);
        btn1.setPosition(250,475);

        TextureRegionDrawable trd2 = new TextureRegionDrawable(new TextureRegion(textura2));
        ImageButton btn2 = new ImageButton(trd2);
        btn2.setPosition(75,250);

        TextureRegionDrawable trd3 = new TextureRegionDrawable(new TextureRegion(textura3));
        ImageButton btn3 = new ImageButton(trd3);
        btn3.setPosition(950,475);

        TextureRegionDrawable trd4 = new TextureRegionDrawable(new TextureRegion(textura4));
        ImageButton btn4 = new ImageButton(trd4);
        btn4.setPosition(950,250);


        escena = new Stage();

        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new MenuPrincipal(juego));

            }
        });

        btn1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked","Tap sobre jugar");
                juego.setScreen(new PantallaJuego(juego));
            }
        });

        Gdx.input.setInputProcessor(escena);
        Image imgFondo = new Image(texturaFondo);

        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX,escalaY);

        escena.addActor(imgFondo);

        escena.addActor(btn1);
        escena.addActor(btn2);
        escena.addActor(btn3);
        escena.addActor(btn4);
        escena.addActor(btnBack);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
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
