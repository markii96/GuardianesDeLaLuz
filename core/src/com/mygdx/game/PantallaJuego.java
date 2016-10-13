package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.javafx.scene.paint.GradientUtils;

import java.awt.Point;


public class PantallaJuego implements Screen, InputProcessor {
    private final Juego juego;

    private OrthographicCamera camara;
    private Viewport vista;

    private Texture texturaFondo;
    private Texture texturaGuerrero;
    private Texture texturaTorre;
    private Texture texturaPerdiste;

    private SpriteBatch batch;

    private Fondo fondo;

    private Array<Guerrero> guerrero;

    private Estado estado = Estado.JUGANDO;

    public PantallaJuego(Juego juego) {

        this.juego = juego;
    }

    @Override
    public void show() {

        inicializarCamara();
        cargarTexturas();
        crearEscena();

        Gdx.input.setInputProcessor(this);


    }

    private void inicializarCamara() {

        camara = new OrthographicCamera(1280,800);
        camara.position.set(1280/2,800/2,0);
        camara.update();
        vista = new StretchViewport(1280,800,camara);

    }

    private void cargarTexturas() {

        texturaFondo = new Texture("campo.png");
        texturaGuerrero = new Texture("Glad.png");
        texturaTorre = new Texture("torre.png");

    }

    private void crearEscena() {

        batch = new SpriteBatch();
        fondo = new Fondo(texturaFondo);

        //Guerrero
        guerrero = new Array<Guerrero>(1);
        Guerrero g = new Guerrero(texturaGuerrero,200,200);
        guerrero.add(g);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);
        batch.begin();

        fondo.draw(batch);

        for(Guerrero g:
                guerrero) {
            g.draw(batch);
            }


        if(estado == Estado.PERDER){

            batch.draw(texturaPerdiste,200,200);

        }



        batch.end();


    }

    @Override
    public void resize(int width, int height) {

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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        //si toco alguna habilidad
        Vector3 v = new Vector3(screenX,screenY,0);
        camara.unproject(v);

        float x =v.x;
        float y = v.y;

        if(estado == Estado.JUGANDO){

            //espacio para poner si las habilidades don tocadas


        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        //if(guerrero.g)

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public enum Estado{
        JUGANDO,
        GANAR,
        PERDER,
        HACIENDONADA,
        GOLPEAR,
        GOLPEADO,
        CAMINANDO
    }

}
