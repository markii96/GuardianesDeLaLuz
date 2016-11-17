package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;



public class PantallaJuego implements Screen, InputProcessor {
    private final Juego juego;
    private OrthographicCamera camara;
    private Viewport vista;
    private Texture texturaFondo;
    private Texture texturaHeroe1;
    private Texture texturaHeroe2;
    private Texture texturaHeroe3;
    private Music music;
    private float[] posicion = new float[2];
    private Texture texturaPerdiste;

    private SpriteBatch batch;

    private Texto texto; //texto para mostrar el cd de las habilidades

    private Fondo fondo;

    private Estado estado = Estado.JUGANDO;

    private Nivel nivel;

    private String[] heroesId = new String[3];

    private Enemigo[] enemigos;

    private float timerHeroes =0;
    private float timerEnemigos =0;

    float xInicial,yInicial;

    private int cont =0;

    private int limiteEnemigos =2;

    private int enemigosEliminados =0;
    private int heroesEliminados =0;

    private Sprite btnAtras;

    private Sprite btnPausa;
    private Sprite btnSalir;

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
        int ran3 = (int)(Math.random() * nivel.getHeroes().size()+1);
        Object objetivo = null;
        switch(ran3){
            case(0):
                objetivo = nivel.getCristal();
                break;
            default:
                objetivo = nivel.getHeroes().get(ran3-1);
                break;
        }
        Gdx.app.log("Create",ran3+"");

        this.enemigos[0] = new Enemigo(nivel.getEnemigos()[ran], 900, ran2, objetivo);
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
        music = Gdx.audio.newMusic(Gdx.files.internal("The_Trip_to_the_Market.mp3"));
        music.setLooping(true);
        music.play();

    }

    private void inicializarCamara() {

        camara = new OrthographicCamera(1280,720);
        camara.position.set(1280/2,720/2,0);
        camara.update();
        vista = new StretchViewport(1280,720,camara);

    }

    private void cargarTexturas() {

        texturaFondo = this.nivel.getTextura();

        texturaHeroe1 = this.nivel.getHeroes().get(0).getTextura();
        texturaHeroe2 = this.nivel.getHeroes().get(1).getTextura();
        texturaHeroe3 = this.nivel.getHeroes().get(2).getTextura();
        btnPausa = new Sprite(new Texture("pausa.png"));
        btnPausa.setX(1020);
        btnPausa.setY(544);

        btnSalir = new Sprite(new Texture("play.png"));
        btnSalir.setX(1280/2);
        btnSalir.setY(800/2);



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
        if(estado == Estado.JUGANDO) {

            int ran = (int) (Math.random() * range);
            int ran2 = (int) (Math.random() * range2);

            int bandera = 0;

            if (enemigosEliminados >= 5) {
                estado = Estado.GANAR;
            }

            if (heroesEliminados >= 3) {
                estado = Estado.PERDER;
            }

            timerHeroes += Gdx.graphics.getDeltaTime();
            timerEnemigos += Gdx.graphics.getDeltaTime();

            for (int i = 0; i < cont; i++) {
                for (int j = 0; j < nivel.getHeroes().size(); j++) {
                    if (nivel.getHeroes().get(j).contiene(enemigos[i].getSprite().getBoundingRectangle()) || enemigos[i].contiene(nivel.getHeroes().get(j).getSprite().getBoundingRectangle())) {
                        //if(nivel.getHeroes().get(j).getEstado()!=Heroe.Estado.CAMINANDO||nivel.getHeroes().get(j).getEstado()!=Heroe.Estado.SELECCIONADO) {
                        if (nivel.getHeroes().get(j).getEstado() == Heroe.Estado.CAMINANDO || nivel.getHeroes().get(j).getEstado() == Heroe.Estado.PARADO) {
                            enemigos[i].setEstado(Enemigo.Estado.ATACANDO);
                            enemigos[i].setObjetivo(nivel.getHeroes().get(j));
                            if(enemigos[i].getSprite().getX()<nivel.getHeroes().get(j).getSprite().getX()){
                                enemigos[i].setDireccion(false);
                            }else{
                                enemigos[i].setDireccion(true);
                            }
                            nivel.getHeroes().get(j).setEstado(Heroe.Estado.ATACANDO);
                        }

                        if (timerHeroes >= 1) {
                            if (enemigos[i].getVitalidad() > 0) {
                                nivel.getHeroes().get(j).getSoundAttack().play(.5f);
                                enemigos[i].setVitalidad(enemigos[i].getVitalidad() - nivel.getHeroes().get(j).getDanoFisico());
                            }
                            timerHeroes = 0;
                        }
                        if (timerEnemigos >= 1.3) {
                            if (nivel.getHeroes().get(j).getVitalidad() > 0) {
                                enemigos[i].getSoundAttack().play(.5f);
                                nivel.getHeroes().get(j).setVitalidad(nivel.getHeroes().get(j).getVitalidad() - enemigos[i].getDanoFisico());
                            }
                            timerEnemigos = 0;
                        }
                        if (nivel.getHeroes().get(j).getVitalidad() <= 0) {
                            nivel.getHeroes().get(j).setEstado(Heroe.Estado.MORIR);
                            nivel.getHeroes().remove(j);

                            heroesEliminados++;
                            //int ran3 = (int)(Math.random() * 4);
                            Object objetivo = nivel.getCristal();
                            // buscar entre los que siguen vivos
                            enemigos[i].setObjetivo(objetivo);
                            enemigos[i].setEstado(Enemigo.Estado.CAMINANDO);
                        }

                        if (enemigos[i].getVitalidad() <= 0) {
                            int ran3 = (int) (Math.random() * nivel.getHeroes().size() + 1);
                            Object objetivo = null;
                            switch (ran3) {
                                case (0):
                                    objetivo = nivel.getCristal();
                                    break;
                                default:
                                    if (!nivel.getHeroes().isEmpty()) {
                                        objetivo = nivel.getHeroes().get(ran3 - 1);
                                    }
                                    break;
                            }
                            enemigos[i].getSprite().setY(1000);
                            enemigos[i].setEstado(Enemigo.Estado.MORIR);
                            enemigos[i] = new Enemigo(nivel.getEnemigos()[ran], 1100, ran2, objetivo);
                            enemigosEliminados++;
                            nivel.getHeroes().get(j).setEstado(Heroe.Estado.PARADO);
                        }

                        bandera = 1;
                    }

                    if (bandera == 0) {

                        if (!enemigos[i].contiene(nivel.getHeroes().get(j).getSprite().getX(), nivel.getHeroes().get(j).getSprite().getY())) {
                            enemigos[i].setEstado(Enemigo.Estado.CAMINANDO);

                        }
                        if (!nivel.getHeroes().get(j).contiene(enemigos[i].getSprite().getX(), enemigos[i].getSprite().getY())) {
                            enemigos[i].setEstado(Enemigo.Estado.CAMINANDO);
                        }
                    }


                }

            }
            batch.setProjectionMatrix(camara.combined);
            batch.begin();

            fondo.draw(batch);

            btnPausa.draw(batch);
            btnSalir.setScale(.01f,.01f);
            btnSalir.draw(batch);
            range = (nivel.getEnemigos().length - 1) + 1;
            range2 = (401);

            for (int i = 1; i < this.nivel.getCantEnemigos(); i++) {
                ran = (int) (Math.random() * range);
                ran2 = (int) (Math.random() * range2);
                int ran3 = (int) (Math.random() * nivel.getHeroes().size() + 1);
                Object objetivo = null;
                switch (ran3) {
                    case (0):
                        objetivo = nivel.getCristal();
                        break;
                    default:
                        if (!nivel.getHeroes().isEmpty())
                            objetivo = nivel.getHeroes().get(ran3 - 1);
                        break;
                }
                if (cont < limiteEnemigos) {
                    this.enemigos[i] = new Enemigo(nivel.getEnemigos()[ran], 1100, ran2, objetivo);
                    cont += 1;
                    Gdx.app.log("Create", ran3 + "");
                }
            }

            for (int i = 0; i < nivel.getHeroes().size(); i++) {
                nivel.getHeroes().get(i).draw(batch);
            }
            nivel.getCristal().draw(batch);

            if (estado == Estado.PERDER) {
                texturaPerdiste = new Texture("perdiste.png");
                batch.draw(texturaPerdiste, 400, 200);
            }

            if (estado == Estado.GANAR) {
                texturaPerdiste = new Texture("ganaste.png");
                batch.draw(texturaPerdiste, 400, 200);
            }

            for (int i = 0; i < regresaEnemigos(); i++) {
                enemigos[i].draw(batch);
            }
            if (estado == Estado.GANAR || estado == Estado.PERDER) {
                Texture back = new Texture("atras.png");
                btnAtras = new Sprite(back);
                btnAtras.setX(400);
                btnAtras.setY(50);
                btnAtras.draw(batch);
            }
            batch.end();
        }else if (estado == Estado.GANAR || estado == Estado.PERDER) {
            batch.begin();
            fondo.draw(batch);
            if (estado == Estado.PERDER) {

                texturaPerdiste = new Texture("perdiste.png");
                batch.draw(texturaPerdiste, 400, 200);
            }else{
                texturaPerdiste = new Texture("ganaste.png");
                batch.draw(texturaPerdiste, 400, 200);
            }
            Texture back = new Texture("atras.png");
            btnAtras = new Sprite(back);
            //btnAtras.setScale(1f);
            btnAtras.setX(400);
            btnAtras.setY(50);
            btnAtras.draw(batch);
            batch.end();
        }else if(estado == Estado.PAUSA){

            batch.begin();
            fondo.draw(batch);
            Texture back = new Texture("atras.png");
            btnAtras = new Sprite(back);
            //btnAtras.setScale(1f);
            btnAtras.setX(400);
            btnAtras.setY(50);
            btnAtras.draw(batch);
            btnSalir.draw(batch);

            btnSalir.setScale(1);

            batch.end();
        }

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
        music.dispose();
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
                juego.setScreen(new MenuPrincipal(juego));
                return true;
            }
        }else{
            if (btnPausa.getBoundingRectangle().contains(x,y)) {
                estado =  Estado.PAUSA;
                return true;
            }
        }
        if(estado == Estado.PAUSA){
            if(btnAtras.getBoundingRectangle().contains(x,y)){
                estado = Estado.JUGANDO;
                return true;
            }

            if(btnSalir.getBoundingRectangle().contains(x,y)){

                juego.setScreen(new MenuPrincipal(juego));

            }



        }

        for (int i=0; i< nivel.getHeroes().size();i++) {

            if (nivel.getHeroes().get(i).getEstado() == Heroe.Estado.SELECCIONADO) {
                nivel.getHeroes().get(i).setEstado(Heroe.Estado.DESELECCIONADO);
            }
        }


        if (estado == Estado.JUGANDO) {
            for (int i = 0; i < nivel.getHeroes().size(); i++) {
                if (nivel.getHeroes().get(i).contiene(x, y)) {
                    xInicial = x;
                    yInicial = y;
                    nivel.getHeroes().get(i).setEstado(Heroe.Estado.SELECCIONADO);
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

        for (int i = 0; i < nivel.getHeroes().size(); i++){
            if (nivel.getHeroes().get(i).getEstado() == Heroe.Estado.SELECCIONADO) {
                //para saber si picamos cerca o lejos
                if (x >= xInicial + 5 || x <= xInicial - 5 && y >= yInicial + 5 || y <= yInicial - 5) {
                    nivel.getHeroes().get(i).setEstado(Heroe.Estado.CAMINANDO);
                    nivel.getHeroes().get(i).setxFinal(x - nivel.getHeroes().get(0).getMedidax() / 6);
                    nivel.getHeroes().get(i).setyFinal(y);
                    for(int z=0;z<regresaEnemigos();z++) {
                        if (enemigos[z].getSprite().getBoundingRectangle().contains(x, y)) {
                            nivel.getHeroes().get(i).setObjetivo(enemigos[z]);
                            break;
                        }else{
                            nivel.getHeroes().get(i).setObjetivo(null);
                        }
                        //setear x para saber de que lado llegar
                    }
                }
                if (xInicial < x) {
                    nivel.getHeroes().get(i).setDireccion(true);
                } else {
                    nivel.getHeroes().get(i).setDireccion(false);
                }
            }
        }

        for (int i = 0; i < nivel.getHeroes().size();i++) {
            if (nivel.getHeroes().get(i).getEstado() == Heroe.Estado.SELECCIONADO) {
                if (x >= xInicial + 5 || x <= xInicial - 5 && y >= yInicial + 5 || y <= yInicial - 5) {
                    nivel.getHeroes().get(i).setEstado(Heroe.Estado.CAMINANDO);
                    nivel.getHeroes().get(i).setxFinal(x - nivel.getHeroes().get(i).getMedidax() / 6);
                    nivel.getHeroes().get(i).setyFinal(y);
                }
                if (xInicial < x) {
                    nivel.getHeroes().get(i).setDireccion(true);
                } else {
                    nivel.getHeroes().get(i).setDireccion(false);
                }
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
        PAUSA

    }


}
