package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


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
    Preferences validacion = Gdx.app.getPreferences("Validacion");

    private SpriteBatch batch;


    private Texto texto; //texto para mostrar el cd de las habilidades

    private Fondo fondo;
    private ArrayList<seudoSprite> ordenDibujar;

    private Estado estado = Estado.JUGANDO;

    private Nivel nivel;

    private String[] heroesId = new String[3];

    private Enemigo[] enemigos;

    private float timerHeroes =0;
    private float timerEnemigos =0;

    float xInicial,yInicial;

    private int cont =0;

    private int limiteEnemigos;

    private int enemigosEliminados =0;
    private int heroesEliminados =0;

    private Sprite btnAtras;
    private Sprite botonPause;

    private Sprite btnPausa;
    private Sprite btnSalir;
    //habilidades
    private Heroe heroeSel= null;
    private ArrayList<Sprite> botonesHabilidades = new ArrayList<Sprite>();
    private ArrayList<Habilidad> habilidadesUsadas = new ArrayList<Habilidad>();
    private ArrayList<Heroe> heroeHabilidad = new ArrayList<Heroe>();

    public PantallaJuego(Juego juego,String nivelId) {


        this.juego = juego;
        heroesId[0]="1";
        heroesId[1]="2";
        heroesId[2]="3";

        this.nivel = new Nivel(nivelId,heroesId);
        this.limiteEnemigos = nivel.getId()+1;
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
        //Gdx.app.log("Create",ran3+"");

        this.enemigos[0] = new Enemigo(nivel.getEnemigos()[ran], 900, ran2, objetivo);
        cont+=1;

        ordenDibujar = new ArrayList<seudoSprite>();

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
        Gdx.input.setCatchBackKey(true);


        if(validacion.getString("2").equals("0")) {

            music.pause();

        }

        if(validacion.getString("2").equals("1")) {

            music.play();

        }

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

        botonPause= new Sprite(new Texture("pause.png"));

        btnPausa = new Sprite(new Texture("pausa.png"));
        btnPausa.setX(1100);
        btnPausa.setY(545);

        btnSalir = new Sprite(new Texture("exit.png"));
        btnSalir.setX(750);
        btnSalir.setY(200);



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

            if (enemigosEliminados >= nivel.getCantEnemigos()) {
                estado = Estado.GANAR;

            }

            if (heroesEliminados >= 3 || nivel.getCristal().getVitalidad()<=0) {
                estado = Estado.PERDER;
            }


            timerHeroes += Gdx.graphics.getDeltaTime();
            timerEnemigos += Gdx.graphics.getDeltaTime();

            for (int i = 0; i < cont; i++) {
                for (int j = 0; j < nivel.getHeroes().size(); j++) {
                    if (nivel.getHeroes().get(j).contiene(enemigos[i].getSprite().getBoundingRectangle()) || enemigos[i].contiene(nivel.getHeroes().get(j).getSprite().getBoundingRectangle())) {
                        enemigos[i].setEstado(Enemigo.Estado.ATACANDO);
                        enemigos[i].setObjetivo(nivel.getHeroes().get(j));

                        if (nivel.getHeroes().get(j).getEstado() == Heroe.Estado.PARADO ) {
                            nivel.getHeroes().get(j).setEstado(Heroe.Estado.ATACANDO);
                            nivel.getHeroes().get(j).setObjetivo(enemigos[i]);
                            if (heroeSel != null){
                                if (heroeSel.getSprite().getX() < enemigos[i].getSprite().getX()) {
                                    heroeSel.setDireccion(true);
                                } else
                                    heroeSel.setDireccion(false);
                            }
                            if(enemigos[i].getSprite().getX()<nivel.getHeroes().get(j).getSprite().getX()){
                                enemigos[i].setDireccion(false);
                            }else{
                                enemigos[i].setDireccion(true);
                            }

                        }

                        if (timerHeroes >= 1 && nivel.getHeroes().get(j).getEstado()== Heroe.Estado.ATACANDO) {
                            if (enemigos[i].getVitalidad() > 0) {
                                nivel.getHeroes().get(j).getSoundAttack().play(.5f);
                                enemigos[i].setVitalidad(enemigos[i].getVitalidad() - nivel.getHeroes().get(j).getDanoFisico());

                            }
                            timerHeroes = 0;
                        }
                        if (timerEnemigos >= 2.3 && enemigos[i].getEstado() == Enemigo.Estado.ATACANDO) {
                            if (nivel.getHeroes().get(j).getVitalidad() > 0) {
                                enemigos[i].getSoundAttack().play(.5f);
                                nivel.getHeroes().get(j).setVitalidad(nivel.getHeroes().get(j).getVitalidad() - enemigos[i].getDanoFisico());
                            }
                            timerEnemigos = 0;
                        }
                        if (nivel.getHeroes().get(j).getVitalidad() <= 0) {

                            nivel.getHeroes().get(j).setEstado(Heroe.Estado.MORIR);

                            if (heroeSel==nivel.getHeroes().get(j)) {
                                heroeSel = null;
                                botonesHabilidades.clear();
                            }
                            nivel.getHeroes().remove(j);
                            heroesEliminados++;
                            //int ran3 = (int)(Math.random() * 4);
                            Object objetivo = nivel.getCristal();
                            // buscar entre los que siguen vivos
                            enemigos[i].setObjetivo(nivel.getCristal());
                        }

                        if (enemigos[i].getVitalidad() <= 0) {
                            nivel.getHeroes().get(j).setObjetivo(null);
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




                }

            }
            for(int i=0; i<cont;i++){
                if( enemigos[i].getObjetivo()==null){
                    enemigos[i].setObjetivo(nivel.getCristal());
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
                }
            }
            // pinta heroes
            for (int i = 0; i < nivel.getHeroes().size(); i++) {
                //nivel.getHeroes().get(i).draw(batch);
                ordenDibujar.add(nivel.getHeroes().get(i));
            }
            // fin pinta heroes...
            //nivel.getCristal().draw(batch);
            ordenDibujar.add(nivel.getCristal());

            for (int i = 0; i < regresaEnemigos(); i++) {
                //enemigos[i].draw(batch);
                ordenDibujar.add(enemigos[i]);
            }
            if (ordenDibujar.size()!=0) {

                Collections.sort(ordenDibujar);
                for (int l = 0; l < ordenDibujar.size(); l++) {
                   if( ordenDibujar.get(l) instanceof Heroe){
                       ((Heroe) ordenDibujar.get(l)).draw(batch);
                   }else if( ordenDibujar.get(l) instanceof Enemigo){
                        ((Enemigo) ordenDibujar.get(l)).draw(batch);
                    }else {
                       ((Cristal)ordenDibujar.get(l)).draw(batch);
                   }

                }
            }

            ordenDibujar.clear();
            //pintar botones Habilidades
            if(heroeSel != null){

                for(int k =1; k<=heroeSel.getHabilidades().size();k++){
                    botonesHabilidades.get(k-1).setPosition(((k-1)*120)+40,604);
                    botonesHabilidades.get(k-1).draw(batch);
                }
            }
            if(!habilidadesUsadas.isEmpty()){
                for (int k=0;k<habilidadesUsadas.size();k++){
                    if (heroeHabilidad.get(k).getObjetivo()!=null) {
                        habilidadesUsadas.get(k).getSprite().setPosition(heroeHabilidad.get(k).getObjetivo().getSprite().getX(), heroeHabilidad.get(k).getObjetivo().getSprite().getY());
                        habilidadesUsadas.get(k).draw(batch, habilidadesUsadas, heroeHabilidad, k);
                    }
                }
            }

            //ends pintar...
            batch.end();
        }else if (estado == Estado.GANAR || estado == Estado.PERDER) {
            batch.begin();
            fondo.draw(batch);
            if (estado == Estado.PERDER) {

                texturaPerdiste = new Texture("perdiste.png");
                batch.draw(texturaPerdiste, 400, 200);
            }else{
                texturaPerdiste = new Texture("ganaste.png");
                Preferences p = Gdx.app.getPreferences("Niveles");
                String dato = p.getString((nivel.getId()+1)+"");
                String respuesta ="";
                String[] datos = dato.split("-");
                datos[6] = "1";
                for (int i =0; i<datos.length;i++){
                    respuesta += datos[i]+"-";
                }
                p.putString((nivel.getId()+1)+"",respuesta);
                p.flush();
                batch.draw(texturaPerdiste, 400, 200);
            }
            Texture back = new Texture("atras.png");


            btnAtras = new Sprite(back);

            //btnAtras.setScale(1f);
            btnAtras.setX(450);
            btnAtras.setY(200);

            botonPause.setX(500);
            botonPause.setY(700);

            btnAtras.draw(batch);
            batch.end();
        }else if(estado == Estado.PAUSA){

            batch.begin();
            fondo.draw(batch);
            Texture back = new Texture("play.png");
            btnAtras = new Sprite(back);
            //btnAtras.setScale(1f);

            botonPause.setX(350);
            botonPause.setY(500);

            btnAtras.setX(450);
            btnAtras.setY(200);
            btnAtras.draw(batch);
            btnSalir.draw(batch);
            botonPause.draw(batch);
            //btnPausa.draw(batch);

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
        texturaPerdiste.dispose();
        texturaFondo.dispose();
        texturaHeroe1.dispose();
        texturaHeroe2.dispose();
        texturaHeroe3.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
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
        int band = 0;
        camara.unproject(v);
        float x =v.x;
        float y = v.y;
        if(estado == Estado.PERDER || estado == Estado.GANAR) {
            if (x <= btnAtras.getX() + btnAtras.getWidth() && x >= btnAtras.getX() && y <= btnAtras.getY() + btnAtras.getHeight() && y >= btnAtras.getY()) {
                juego.setScreen(new PantallaMapa(juego));
                return true;
            }
        }else{
            if (btnPausa.getBoundingRectangle().contains(x,y)) {
                band=1;
                estado =  Estado.PAUSA;
                return true;
            }
        }
        if(estado == Estado.PAUSA){
            if(btnAtras.getBoundingRectangle().contains(x,y)){
                band=1;
                estado = Estado.JUGANDO;
                return true;
            }

            if(btnSalir.getBoundingRectangle().contains(x,y)){
                band=1;

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
                    heroeSel = nivel.getHeroes().get(i);
                    band=1;
                    xInicial = x;
                    yInicial = y;
                    heroeSel.setEstado(Heroe.Estado.SELECCIONADO);

                    botonesHabilidades.clear();

                    /*if(heroeSel != null){*/

                        for(int k =1; k<=heroeSel.getHabilidades().size();k++){

                            botonesHabilidades.add(heroeSel.getHabilidades().get(k-1).getSprite());
                        }
                    //}

                    break;
                }
            }
        }
        for (int i =0; i<botonesHabilidades.size();i++){
            if(botonesHabilidades.get(i).getBoundingRectangle().contains(x,y)){
                band=1;

                switch (Integer.parseInt(heroeSel.getHabilidades().get(i).getId())){
                    case 4:
                    case 1://bolas de fuego
                        if(heroeSel.getObjetivo()!=null){
                            heroeSel.getObjetivo().setVitalidad(heroeSel.getObjetivo().getVitalidad()-heroeSel.getHabilidades().get(i).getIndice());
                            habilidadesUsadas.add(heroeSel.getHabilidades().get(i));
                            heroeHabilidad.add(heroeSel);
                        }
                        break;

                }
            }
        }

        if (band ==0){
            heroeSel= null;
        }


        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        Vector3 v = new Vector3(screenX, screenY, 0);
        camara.unproject(v);
        float x = v.x;
        float y = v.y;
        int band2=0;

        if (y > 600){
            band2 = 1;
        }


        if (band2 != 1) {
            if (heroeSel != null) {
                //para saber si picamos cerca o lejos
                if (x >= xInicial + 5 || x <= xInicial - 5 && y >= yInicial + 5 || y <= yInicial - 5) {
                    heroeSel.setEstado(Heroe.Estado.CAMINANDO);
                    heroeSel.setObjetivo( null);
                    heroeSel.setxFinal(x - nivel.getHeroes().get(0).getMedidax() / 6);
                    heroeSel.setyFinal(y);
                    for (int z = 0; z < regresaEnemigos(); z++) {
                        if (enemigos[z].getSprite().getBoundingRectangle().contains(x, y)) {
                            heroeSel.setObjetivo(enemigos[z]);
                            if(heroeSel.getSprite().getX()< enemigos[z].getSprite().getX()){
                                heroeSel.setDireccion(true);
                            }else
                                heroeSel.setDireccion(false);
                            break;
                        }
                        //setear x para saber de que lado llegar
                    }
                    if (xInicial < x) {
                        heroeSel.setDireccion(true);
                    } else {
                        heroeSel.setDireccion(false);
                    }
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
