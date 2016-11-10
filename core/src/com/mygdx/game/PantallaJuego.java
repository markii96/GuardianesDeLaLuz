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

    private int enemigosEliminados =0;
    private int heroesEliminados =0;

    private Sprite btnAtras;



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

        int range = (nivel.getEnemigos().length-1) + 1;
        int range2 = (401);

        int ran =  (int)(Math.random() * range);
        int ran2 = (int)(Math.random() * range2);

        int bandera = 0;

        if (enemigosEliminados >= 5){
            estado =Estado.GANAR;
        }

        if (heroesEliminados >= 3){
            estado =Estado.PERDER;
        }

        timer+= Gdx.graphics.getDeltaTime();

        for(int i = 0;i<cont;i++){
            for(int j = 0;j<nivel.getHeroes().length;j++){
                if(nivel.getHeroes()[j].contiene(enemigos[i].getSprite().getBoundingRectangle())){
                    //if(nivel.getHeroes()[j].getEstado()!=Heroe.Estado.CAMINANDO||nivel.getHeroes()[j].getEstado()!=Heroe.Estado.SELECCIONADO) {
                    if(nivel.getHeroes()[j].getEstado()==Heroe.Estado.CAMINANDO) {
                        enemigos[i].setEstado(Enemigo.Estado.ATACANDO);
                        nivel.getHeroes()[j].setEstado(Heroe.Estado.ATACANDO);
                    }

                    if (timer >=1) {

                        if ( nivel.getHeroes()[j].getVitalidad() > 0)
                            nivel.getHeroes()[j].setVitalidad(nivel.getHeroes()[j].getVitalidad() - enemigos[i].getDanoFisico());
                        if ( enemigos[i].getVitalidad() > 0)
                            enemigos[i].setVitalidad(enemigos[i].getVitalidad()-nivel.getHeroes()[j].getDanoFisico());

                        if ( nivel.getHeroes()[j].getVitalidad() <= 0){
                            nivel.getHeroes()[j].getSprite().setY(1000);
                            nivel.getHeroes()[j].setEstado(Heroe.Estado.MORIR);
                            heroesEliminados++;

                            enemigos[i].setEstado(Enemigo.Estado.CAMINANDO);

                        }

                        if (enemigos[i].getVitalidad() <= 0 ){
                            enemigos[i].getSprite().setY(1000);
                            enemigos[i].setEstado(Enemigo.Estado.MORIR);
                            enemigos[i] = new Enemigo(nivel.getEnemigos()[ran], 1100, ran2, "Cristal");
                            enemigosEliminados++;
                            nivel.getHeroes()[j].setEstado(Heroe.Estado.PARADO);
                        }

                        timer = 0;
                    }

                    bandera = 1;
                }

                if (bandera==0) {

                    if (!enemigos[i].contiene(nivel.getHeroes()[j].getSprite().getX(), nivel.getHeroes()[j].getSprite().getY())) {
                        enemigos[i].setEstado(Enemigo.Estado.CAMINANDO);

                    }
                    if (!nivel.getHeroes()[j].contiene(enemigos[i].getSprite().getX(), enemigos[i].getSprite().getY())) {
                        enemigos[i].setEstado(Enemigo.Estado.CAMINANDO);
                    }
                }




            }

        }
        batch.setProjectionMatrix(camara.combined);
        batch.begin();

        fondo.draw(batch);



        range = (nivel.getEnemigos().length-1) + 1;
        range2 = (401);

        for (int i = 1; i< this.nivel.getCantEnemigos();i++){
            ran =  (int)(Math.random() * range);
            ran2 = (int)(Math.random() * range2);

            if (cont < limiteEnemigos) {
                this.enemigos[i] = new Enemigo(nivel.getEnemigos()[ran], 1100, ran2, "Cristal");
                cont+=1;
            }
        }

        nivel.getHeroes()[0].draw(batch);
        nivel.getHeroes()[1].draw(batch);
        nivel.getHeroes()[2].draw(batch);
        nivel.getCristal().draw(batch);

        if(estado == Estado.PERDER){
            texturaPerdiste = new Texture("perdiste.png");
            batch.draw(texturaPerdiste,400,200);
        }

        if(estado == Estado.GANAR){
            texturaPerdiste = new Texture("ganaste.png");
            batch.draw(texturaPerdiste,400,200);
        }

        for (int i=0; i<regresaEnemigos();i++){
            enemigos[i].draw(batch);
        }
        if(estado== Estado.GANAR || estado == Estado.PERDER){
            Texture back = new Texture("back.png");
            btnAtras = new Sprite(back);
            btnAtras.setScale(.3f);
            btnAtras.setX(400);
            btnAtras.setY(50);
            btnAtras.draw(batch);
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
        batch.dispose();
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
        if(estado == Estado.PERDER || estado == Estado.GANAR) {
            if (x <= btnAtras.getX() + btnAtras.getWidth() && x >= btnAtras.getX() && y <= btnAtras.getY() + btnAtras.getHeight() && y >= btnAtras.getY()) {
                System.out.println("lol");
                juego.setScreen(new MenuPrincipal(juego));
                return true;
            }
        }

        if(nivel.getHeroes()[0].getEstado()== Heroe.Estado.SELECCIONADO){
            nivel.getHeroes()[0].setEstado(Heroe.Estado.DESELECCIONADO);
        }
        if(nivel.getHeroes()[1].getEstado()== Heroe.Estado.SELECCIONADO){
            nivel.getHeroes()[1].setEstado(Heroe.Estado.DESELECCIONADO);
        }
        if(nivel.getHeroes()[2].getEstado()== Heroe.Estado.SELECCIONADO){
            nivel.getHeroes()[2].setEstado(Heroe.Estado.DESELECCIONADO);
        }


        if (estado == Estado.JUGANDO) {
            for (int i = 0; i < nivel.getHeroes().length; i++) {
                if (nivel.getHeroes()[i].contiene(x, y)) {
                    xInicial = x;
                    yInicial = y;
                    nivel.getHeroes()[i].setEstado(Heroe.Estado.SELECCIONADO);
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        Vector3 v = new Vector3(screenX, screenY, 0);
        camara.unproject(v);
        float x = v.x;
        float y = v.y;

        for (int i = 0; i < nivel.getHeroes().length; i++){
            if (nivel.getHeroes()[i].getEstado() == Heroe.Estado.SELECCIONADO) {
                //para saber si picamos cerca o lejos
                if (x >= xInicial + 5 || x <= xInicial - 5 && y >= yInicial + 5 || y <= yInicial - 5) {
                    nivel.getHeroes()[i].setEstado(Heroe.Estado.CAMINANDO);
                    nivel.getHeroes()[i].setxFinal(x - nivel.getHeroes()[0].getMedidax() / 6);
                    nivel.getHeroes()[i].setyFinal(y);
                    for(int z=0;z<regresaEnemigos();z++) {
                        if (enemigos[z].getSprite().getBoundingRectangle().contains(x, y)) ;
                            nivel.getHeroes()[i].setyFinal(enemigos[z].getSprite().getY());
                        //setear x para saber de que lado llegar
                    }
                }
                if (xInicial < x) {
                    nivel.getHeroes()[i].setDireccion(true);
                } else {
                    nivel.getHeroes()[i].setDireccion(false);
                }
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
