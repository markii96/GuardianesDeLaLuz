package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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
    private Texture texturaHeroe1;
    private Texture texturaHeroe2;
    private Texture texturaHeroe3;

    private float[] posicion = new float[2];
    private Texture texturaTorre;
    private Texture texturaPerdiste;

    private SpriteBatch batch;

    private Texto texto; //texto para mostrar el cd de las habilidades

    private Fondo fondo;

    private Estado estado = Estado.JUGANDO;

    private Nivel nivel;

    private Heroe heroes;

    private String[] heroesId = new String[3];

    private Enemigo[] enemigos;

    float xInicial,yInicial;



    public PantallaJuego(Juego juego) {

        this.juego = juego;
        heroesId[0]="1";
        heroesId[1]="2";
        heroesId[2]="3";

        this.nivel = new Nivel("1",heroesId);

        this.enemigos = new Enemigo[this.nivel.getCantEnemigos()];

        int range = (nivel.getEnemigos().length-1) + 1;


        for (int i = 0; i< this.nivel.getCantEnemigos();i++){
            int ran =  (int)(Math.random() * range);
            this.enemigos[i] = new Enemigo(nivel.getEnemigos()[ran],100,100,"Cristal");
        }

    }




    @Override
    public void show() {

        inicializarCamara();
        cargarTexturas();
        crearEscena();

        Gdx.input.setInputProcessor(this);
        texto =new Texto();
    }

    private void inicializarCamara() {

        camara = new OrthographicCamera(1280,720);
        camara.position.set(1280/2,720/2,0);
        camara.update();
        vista = new StretchViewport(1280,800,camara);

    }

    private void cargarTexturas() {

        texturaFondo = this.nivel.getTextura();
        texturaHeroe1 = this.nivel.getHeroes()[0].getTextura();
        texturaHeroe2 = this.nivel.getHeroes()[1].getTextura();
        texturaHeroe3 = this.nivel.getHeroes()[2].getTextura();

    }

    private void crearEscena() {

        batch = new SpriteBatch();
        fondo = new Fondo(texturaFondo);



        Heroe hero1 = new Heroe("1",230+300,230+300);
        Heroe hero2 = new Heroe("2",230+300,230+300);
        Heroe hero3 = new Heroe("3",230+300,230+300);





    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);
        batch.begin();

        fondo.draw(batch);


        nivel.getHeroes()[0].draw(batch);
        nivel.getHeroes()[1].draw(batch);
        nivel.getHeroes()[2].draw(batch);
        nivel.getCristal().draw(batch);

        if(estado == Estado.PERDER){

            batch.draw(texturaPerdiste,200,200);

        }

        batch.end();

    }

    public float[] getPosicion1() {
        return posicion;
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

        if (estado == Estado.JUGANDO) {


                if(heroes.contiene(x,y)){

                    x = xInicial;
                    y = yInicial;
                    //h.setPosicion(x,y);
            }



        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        Vector3 v = new Vector3(screenX,screenY,0);
        camara.unproject(v);
        float x =v.x;
        float y = v.y;

        //posicion[0] = x;
        //posicion[1] = y;
        if (estado == Estado.JUGANDO) {

            if (xInicial > x + 20 || y > yInicial + 20) {


                    heroes.setEstado(Heroe.Estado.CAMINANDO);
                    System.out.println("perro");
                    heroes.xFinal = x;
                    heroes.yFinal = y;

            }

        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {


        return true;
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
        SELECCIONADO

    }

}
