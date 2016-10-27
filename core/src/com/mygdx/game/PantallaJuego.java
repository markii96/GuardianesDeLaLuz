package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.Arrays;
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
    private Texture texturaPerdiste;

    private SpriteBatch batch;

    private Texto texto; //texto para mostrar el cd de las habilidades

    private Fondo fondo;

    private Estado estado = Estado.JUGANDO;

    private Nivel nivel;

    private String[] heroesId = new String[3];

    private Enemigo[] enemigos;

    private float timer =0;

    float xInicial,yInicial;

    private int cont =0;

    private int limiteEnemigos =2;



    public PantallaJuego(Juego juego) {


        this.juego = juego;
        heroesId[0]="1";
        heroesId[1]="2";
        heroesId[2]="3";

        this.nivel = new Nivel("1",heroesId);

        this.enemigos = new Enemigo[this.nivel.getCantEnemigos()];

        int range = (nivel.getEnemigos().length-1) + 1;
        int range2 = (401);

        int ran =  (int)(Math.random() * range);
        int ran2 = (int)(Math.random() * range2);


        this.enemigos[0] = new Enemigo(nivel.getEnemigos()[ran], 900, ran2, "Cristal");
        cont+=1;

    }

    private int regresaEnemigos() {
        int cont=0;

        try {
            for (int i = 0; i < enemigos.length; i++) {
                if (enemigos[i]!= null)
                cont+=1;
            }
        }
        catch (Exception error){
            return cont;
        }

        return cont;
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
        vista = new StretchViewport(1280,720,camara);

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

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        timer+= Gdx.graphics.getDeltaTime();

        for(int i = 0;i<cont;i++){
            for(int j = 0;j<nivel.getHeroes().length;j++){
                if(nivel.getHeroes()[j].contiene(enemigos[i].getSprite().getX(),enemigos[i].getSprite().getY())){

                    enemigos[i].setEstado(Enemigo.Estado.ATACANDO);
                    nivel.getHeroes()[j].setEstado(Heroe.Estado.ATACANDO);

                    if (timer >=1) {
                        System.out.println(nivel.getHeroes()[0].getVitalidad()+" "+Gdx.graphics.getDeltaTime());

                        if ( nivel.getHeroes()[j].getVitalidad() > 0)nivel.getHeroes()[j].setVitalidad(nivel.getHeroes()[j].getVitalidad() - enemigos[i].getDanoFisico());
                        if ( ;

                        if ( nivel.getHeroes()[j].getVitalidad() == 0){
                            nivel.getHeroes()[j].setEstado(Heroe.Estado.MORIR);
                            nivel.getHeroes()[j].getSprite().setY(1000);
                            enemigos[i].setEstado(Enemigo.Estado.CAMINANDO);

                        }
                        timer = 0;
                    }
                    break;

                }

                if(enemigos[i].contiene(nivel.getHeroes()[j].getSprite().getX(),nivel.getHeroes()[j].getSprite().getY())){
                    enemigos[i].setEstado(Enemigo.Estado.ATACANDO);
                    nivel.getHeroes()[j].setEstado(Heroe.Estado.ATACANDO);

                    if (timer >=1) {
                        System.out.println(nivel.getHeroes()[0].getVitalidad()+" "+Gdx.graphics.getDeltaTime());

                        if ( nivel.getHeroes()[j].getVitalidad() > 0)nivel.getHeroes()[j].setVitalidad(nivel.getHeroes()[j].getVitalidad() - enemigos[i].getDanoFisico());

                        if ( nivel.getHeroes()[j].getVitalidad() == 0){
                            nivel.getHeroes()[j].setEstado(Heroe.Estado.MORIR);
                            nivel.getHeroes()[j].getSprite().setY(1000);
                            enemigos[i].setEstado(Enemigo.Estado.CAMINANDO);
                        }
                        timer = 0;
                    }

                    break;

                }

                if (!enemigos[i].contiene(nivel.getHeroes()[j].getSprite().getX(),nivel.getHeroes()[j].getSprite().getY())){
                    enemigos[i].setEstado(Enemigo.Estado.CAMINANDO);
                    break;
                }
                if(!nivel.getHeroes()[j].contiene(enemigos[i].getSprite().getX(),enemigos[i].getSprite().getY())){
                    enemigos[i].setEstado(Enemigo.Estado.CAMINANDO);
                    break;

                }




            }

        }
        batch.setProjectionMatrix(camara.combined);
        batch.begin();

        fondo.draw(batch);

        int range = (nivel.getEnemigos().length-1) + 1;
        int range2 = (401);

        for (int i = 1; i< this.nivel.getCantEnemigos();i++){
            int ran =  (int)(Math.random() * range);
            int ran2 = (int)(Math.random() * range2);

            if (cont < limiteEnemigos) {
                this.enemigos[i] = new Enemigo(nivel.getEnemigos()[ran], 900, ran2, "Cristal");
                cont+=1;
            }
        }

        nivel.getHeroes()[0].draw(batch);
        nivel.getHeroes()[1].draw(batch);
        nivel.getHeroes()[2].draw(batch);
        nivel.getCristal().draw(batch);

        if(estado == Estado.PERDER){
            batch.draw(texturaPerdiste,200,200);
        }

        for (int i=0; i<regresaEnemigos();i++){
            enemigos[i].draw(batch);
        }


        batch.end();

    }

    public float[] getPosicion1() {
        return posicion;
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


        if(nivel.getHeroes()[0].getEstado()== Heroe.Estado.SELECCIONADO){
            nivel.getHeroes()[0].setEstado(Heroe.Estado.DESELECCIONADO);
        }
        if(nivel.getHeroes()[1].getEstado()== Heroe.Estado.SELECCIONADO){
            nivel.getHeroes()[1].setEstado(Heroe.Estado.DESELECCIONADO);
        }
        if(nivel.getHeroes()[1].getEstado()== Heroe.Estado.SELECCIONADO){
            nivel.getHeroes()[1].setEstado(Heroe.Estado.DESELECCIONADO);
        }


        if (estado == Estado.JUGANDO) {
            if(nivel.getHeroes()[0].contiene(x,y)){
                xInicial = x ;
                yInicial = y;
                nivel.getHeroes()[0].setEstado(Heroe.Estado.SELECCIONADO);

            }else

            if(nivel.getHeroes()[1].contiene(x,y)){

                xInicial = x ;
                yInicial = y;
                nivel.getHeroes()[1].setEstado(Heroe.Estado.SELECCIONADO);
            }else if(nivel.getHeroes()[2].contiene(x,y)){

                xInicial = x ;
                yInicial = y;
                nivel.getHeroes()[2].setEstado(Heroe.Estado.SELECCIONADO);
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


        if (nivel.getHeroes()[0].getEstado() == Heroe.Estado.SELECCIONADO) {
            if(x>=xInicial+5||x<=xInicial-5&&y>=yInicial+5||y<=yInicial-5) {
                nivel.getHeroes()[0].setEstado(Heroe.Estado.CAMINANDO);
                nivel.getHeroes()[0].setxFinal(x-nivel.getHeroes()[0].getMedidax()/6);
                nivel.getHeroes()[0].setyFinal(y);
            }
            if(xInicial<x){
                nivel.getHeroes()[0].setDireccion(true);
            }else{
                nivel.getHeroes()[0].setDireccion(false);
            }
        }

        if (nivel.getHeroes()[1].getEstado() == Heroe.Estado.SELECCIONADO) {
            if(x>=xInicial+5||x<=xInicial-5&&y>=yInicial+5||y<=yInicial-5) {
                nivel.getHeroes()[1].setEstado(Heroe.Estado.CAMINANDO);
                nivel.getHeroes()[1].setxFinal(x-nivel.getHeroes()[1].getMedidax()/6);
                nivel.getHeroes()[1].setyFinal(y);
            }
            if(xInicial<x){
                nivel.getHeroes()[1].setDireccion(true);
            }else{
                nivel.getHeroes()[1].setDireccion(false);
            }
        }

        if (nivel.getHeroes()[2].getEstado() == Heroe.Estado.SELECCIONADO) {
            if(x>=xInicial+5||x<=xInicial-5&&y>=yInicial+5||y<=yInicial-5) {
                nivel.getHeroes()[2].setEstado(Heroe.Estado.CAMINANDO);
                nivel.getHeroes()[2].setxFinal(x -nivel.getHeroes()[2].getMedidax()/6);
                nivel.getHeroes()[2].setyFinal(y);
            }
            if(xInicial<x){
                nivel.getHeroes()[2].setDireccion(true);
            }else{
                nivel.getHeroes()[2].setDireccion(false);
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
