package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by marco on 20/11/2016.
 */
public class PantallaTaberna implements Screen {

    private Juego juego;
    Preferences heroes = Gdx.app.getPreferences("Heroes");
    //private int id;

    private Texture texturaFondo;
    private Texture texturaCara1;
    private Texture texturaCara2;
    private Texture texturaCara3;
    private Texture texturaCara3_1;
    private Texture texturaCara4;
    private Texture texturaBtnBack;
    private Texture texturaBtnMejorar;
    private Texture texturaComprar;


    private int dato1;
    private Sprite imagenHeroe;

    private Texto texto;
    private SpriteBatch batch;
    private Stage escena;

    private int idTexto=0;


    java.lang.String cadena = heroes.getString("3");
    java.lang.String[] lista;


    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 1280;
    private final int ALTO_MUNDO = 800;


    public PantallaTaberna(Juego juego){
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


        lista= cadena.split("-");
        java.lang.String dato = lista[15];
        System.out.println(dato+" hola");


        texturaFondo = new Texture("taberna.png");
        texturaCara1 = new Texture("cara1.png");
        texturaCara2 = new Texture("cara2.png");
        texturaCara3 = new Texture("cara3.png");
        texturaCara3_1 = new Texture("cara3_1.png");
        texturaCara4 = new Texture("cara4.png");
        texturaBtnBack = new Texture("atras.png");
        texturaComprar = new Texture("comprar.png");
        texturaBtnMejorar = new Texture("mejorar.png");
        imagenHeroe = new Sprite(new Texture("nada.png"));


        TextureRegionDrawable trdBtnMejorar = new TextureRegionDrawable(new TextureRegion(texturaBtnMejorar));
        final ImageButton btnMejorar = new ImageButton(trdBtnMejorar);
        btnMejorar.setPosition(900,0);

        TextureRegionDrawable trdBtnComprar = new TextureRegionDrawable(new TextureRegion(texturaComprar));
        final ImageButton btnComprar = new ImageButton(trdBtnComprar);
        btnComprar.setPosition(900,0);


        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);

        TextureRegionDrawable trdCara1 = new TextureRegionDrawable(new TextureRegion(texturaCara1));
        ImageButton btnCara1 = new ImageButton(trdCara1);
        btnCara1.setPosition(50,550);


        TextureRegionDrawable trdCara2 = new TextureRegionDrawable(new TextureRegion(texturaCara2));
        ImageButton btnCara2 = new ImageButton(trdCara2);
        btnCara2.setPosition(50,300);

        TextureRegionDrawable trdCara3 = new TextureRegionDrawable(new TextureRegion(texturaCara3));
        ImageButton btnCara3 = new ImageButton(trdCara3);
        //btnCara3.setPosition(350,550);

        TextureRegionDrawable trdCara3_1 = new TextureRegionDrawable(new TextureRegion(texturaCara3_1));
        ImageButton btnCara3_1 = new ImageButton(trdCara3_1);
        //btnCara3_1.setPosition(350,550);

        TextureRegionDrawable trdCara4 = new TextureRegionDrawable(new TextureRegion(texturaCara4));
        ImageButton btnCara4 = new ImageButton(trdCara4);
        btnCara4.setPosition(350,300);


        escena = new Stage();

        dato1 = Integer.parseInt(dato);

        //listeners

        btnComprar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                lista[15] = "1";
                java.lang.String regresa = "";

                for(int i =0; i<lista.length;i++){
                    regresa+=lista[i]+"-";
                }
                heroes.putString("3",regresa);
                heroes.flush();
                juego.setScreen(new PantallaTaberna(juego));


            }
        });

        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked","Tap sobre atras");
                juego.setScreen(new PantallaMapa(juego));
            }
        });

        btnCara1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnMejorar.setPosition(900,0);
                btnComprar.setPosition(2000,2000);
                idTexto = 1;
                imagenHeroe.setPosition(ANCHO_MUNDO*.75f,ALTO_MUNDO*.8f);
                imagenHeroe = new Sprite(new Texture("heroe1.png"));
                escena.addActor(btnMejorar);
            }
        });

        btnCara2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnMejorar.setPosition(900,0);
                btnComprar.setPosition(2000,2000);
                idTexto = 2;
                imagenHeroe.setPosition(ANCHO_MUNDO*.75f,ALTO_MUNDO*.8f);
                imagenHeroe = new Sprite(new Texture("heroe2.png"));
                escena.addActor(btnMejorar);
            }
        });

        btnCara3_1.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnComprar.setPosition(900,0);
                imagenHeroe.setPosition(ANCHO_MUNDO*.75f,ALTO_MUNDO*.8f);
                imagenHeroe = new Sprite(new Texture("heroe3_1.png"));
                idTexto=5;
                btnMejorar.setPosition(2000,2000);

                escena.addActor(btnComprar);
            }
        });

        btnCara3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnComprar.setPosition(2000,2000);
                btnMejorar.setPosition(900,0);
                idTexto = 3;
                imagenHeroe.setPosition(ANCHO_MUNDO*.75f,ALTO_MUNDO*.8f);

                    imagenHeroe = new Sprite(new Texture("heroe3.png"));
                    escena.addActor(btnMejorar);

            }
        });

        btnMejorar.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new PantallaMejorar(juego,idTexto));
            }
        })
                ;

        btnCara4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnComprar.setPosition(2000,2000);
                btnMejorar.setPosition(900,0);
                idTexto = 4;
                imagenHeroe.setPosition(ANCHO_MUNDO*.75f,ALTO_MUNDO*.8f);
                imagenHeroe = new Sprite(new Texture("heroe4.png"));
                escena.addActor(btnMejorar);
            }
        });


        Gdx.input.setInputProcessor(escena);

        Image imgFondo = new Image(texturaFondo);

        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX,escalaY);

        escena.addActor(imgFondo);
        escena.addActor(btnBack);
        escena.addActor(btnCara1);
        escena.addActor(btnCara2);
        escena.addActor(btnCara3);
        escena.addActor(btnCara3_1);

        if(dato1==1) {
            btnCara3_1.setPosition(2000,2000);
            btnCara3.setPosition(350,550);


        }

        if(dato1==0) {
            btnCara3.setPosition(2000,2000);
            btnCara3_1.setPosition(350,550);

        }

        escena.addActor(btnCara4);



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


        imagenHeroe.setScale(.6f);

        imagenHeroe.setX(ANCHO_MUNDO*.7f);
        imagenHeroe.setY(ALTO_MUNDO*.8f);
        imagenHeroe.draw(batch);



        texto.mostrarMensaje(batch,"",0,200);

        if(idTexto==1){
            texto.mostrarMensaje(batch,"Uno de los guardianes de la luz, su propósito es",400,ALTO_MUNDO/4);
            texto.mostrarMensaje(batch,"ayudar a la gente y salvar a la mayor cantidad",380,ALTO_MUNDO/4.8f);
            texto.mostrarMensaje(batch,"de inocentes posibles.",200,ALTO_MUNDO/5.8f);

        }

        if(idTexto==3){
            texto.mostrarMensaje(batch,"Uno de los Guardianes de la luz, dejó su pueblo",400,ALTO_MUNDO/4);
            texto.mostrarMensaje(batch,"para unirse a la aventura para salvar la luz",360,ALTO_MUNDO/4.8f);
            texto.mostrarMensaje(batch,"del mundo.",130,ALTO_MUNDO/5.8f);

        }
        if(idTexto==2){

            texto.mostrarMensaje(batch,"Uno de los Guardianes de la luz, acabó sus",355,ALTO_MUNDO/4);
            texto.mostrarMensaje(batch,"estudios en el Círculo de Hechiceros y se unió",380,ALTO_MUNDO/4.8f);
            texto.mostrarMensaje(batch,"a la lucha contra la oscuridad.",250,ALTO_MUNDO/5.8f);

        }
        if(idTexto==4){

            texto.mostrarMensaje(batch,"Asesino a sueldo. Un día se encuentra con los",370,ALTO_MUNDO/4);
            texto.mostrarMensaje(batch,"guardianes y se une a ellos. Tal vez no es la mejor",400,ALTO_MUNDO/4.8f);
            texto.mostrarMensaje(batch,"de sus decisiones pero pone el pan en la mesa",370,ALTO_MUNDO/5.8f);

        }

        if(idTexto==5){

            texto.mostrarMensaje(batch,"",0,0);

        }

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
