package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by LUISRICARDO on 11/22/2016.
 */
public class PantallaFin implements Screen {
    public PantallaFin(Juego juego) {
        this.juego = juego;
    }
    private static final int ANCHO_MUNDO = 	1280 ;
    private static final int ALTO_MUNDO = 	800 ;
    private Texture fondo;
    private SpriteBatch batch;
    private OrthographicCamera camara;
    private FitViewport vista;
    private Juego juego;
    private int cont = 0;
    private Sprite fondoSp;
    @Override
    public void show() {
        fondo = new Texture("end_game.png");
        fondoSp = new Sprite(fondo);
        //fondoSp.setScale(1,1.2f);
        batch = new SpriteBatch();
        camara = new OrthographicCamera(ANCHO_MUNDO,ALTO_MUNDO);
        camara.position.set(ANCHO_MUNDO/2,ALTO_MUNDO/2,0);
        camara.update();
        vista = new FitViewport(ANCHO_MUNDO,ALTO_MUNDO);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cont++;
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        fondoSp.draw(batch);
        batch.end();
        if(cont>500){
            juego.setScreen(new MenuPrincipal(juego));
            this.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        vista.update(width,height);
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
        fondo.dispose();
        batch.dispose();
    }
}
