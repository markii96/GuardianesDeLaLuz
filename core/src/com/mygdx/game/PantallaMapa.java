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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//import com.sun.org.apache.xpath.internal.operations.String;

import java.util.ArrayList;
import java.util.Map;

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
    private Texture textura5;

    private Texture texturaMoneda;

    private Texto texto;
    private Texto1 texto1;
    /*
    private Texture texturaCara1;
    private Texture texturaCara2;
    private Texture texturaCara3;
    private Texture texturaCara3_1;
    private Texture texturaCara4;
    */

    private Texture texturaBtnTaberna;
    Preferences niveles = Gdx.app.getPreferences("Niveles");
    Preferences oro = Gdx.app.getPreferences("Oro");

    private java.lang.String cadena1=niveles.getString("1");
    private java.lang.String cadena2= niveles.getString("2");
    private java.lang.String cadena3=niveles.getString("3");
    private java.lang.String cadena4= niveles.getString("4");
    private java.lang.String cadena5 = niveles.getString("5");

    private String guardarOro = oro.getString("1");

    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 1280;
    private final int ALTO_MUNDO = 800;


    private SpriteBatch batch;

    private String lineaHeroe;
    private String[] datosHeroe;
    private ArrayList<String> idHeroes = new ArrayList<String>();
    private ArrayList<String> heroesSeleccionados = new ArrayList<String>();
    private ArrayList<ImageButton> botonesHeroes =new ArrayList<ImageButton>();
    private ArrayList<Image> flechas = new ArrayList<Image>();
    private int cont;
    private int posicion=0;
    public PantallaMapa(Juego juego){
        this.juego = juego;
    }

    @Override
    public void show() {

        texto = new Texto();
        texto1 = new Texto1();
        batch = new SpriteBatch();

        camara = new OrthographicCamera(ANCHO_MUNDO,ALTO_MUNDO);
        camara.position.set(ANCHO_MUNDO/2,ALTO_MUNDO/2,0);
        camara.update();
        vista = new StretchViewport(ANCHO_MUNDO,ALTO_MUNDO,camara);

        float ancho = ANCHO_MUNDO;//Gdx.graphics.getWidth();
        float alto = ALTO_MUNDO;//Gdx.graphics.getHeight();

        texturaFondo = new Texture("mapa.png");
        texturaBtnTaberna = new Texture("botonTABERNA.png");
        texturaBtnBack = new Texture("atras.png");
        textura1 = new Texture("1.png");
        textura2 = new Texture("2.png");
        textura3 = new Texture("3.png");
        textura4 = new Texture("4.png");
        textura5 = new Texture("5.png");
        texturaMoneda = new Texture("moneda.png");

        Image imgMoneda = new Image(texturaMoneda);
        imgMoneda.setPosition(950,650);

        Preferences p = Gdx.app.getPreferences("Heroes");

        Map heroes = p.get();
        int pos = 0;
        float xInicial =300;
        float yInicial = 30;
        for(int j = 0;j<heroes.size();j++){
            String idHeroe = (j+1)+"";
            lineaHeroe = heroes.get(idHeroe).toString();
            datosHeroe = lineaHeroe.split("-");
            if(datosHeroe[15].equals("1")){
                idHeroes.add(idHeroe);
                TextureRegionDrawable trdCara1 = new TextureRegionDrawable(new TextureRegion(new Texture("cara"+(j+1)+".png")));
                botonesHeroes.add( new ImageButton(trdCara1));
                botonesHeroes.get(pos).setSize(96,96);
                botonesHeroes.get(pos).setPosition(xInicial,yInicial);
                xInicial +=125;
                pos++;

            }

        }
        //fin seleccion de heroes

        TextureRegionDrawable trd5 = new TextureRegionDrawable(new TextureRegion(textura5));
        ImageButton btn5 = new ImageButton(trd5);
        btn5.setPosition(1150,250);

        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);

        TextureRegionDrawable trdBtnTaberna = new TextureRegionDrawable(new TextureRegion(texturaBtnTaberna));
        ImageButton btnTaberna = new ImageButton(trdBtnTaberna);
        btnTaberna.setPosition(1000,20);
        btnTaberna.setScale(1,.7f);


        TextureRegionDrawable trd1 = new TextureRegionDrawable(new TextureRegion(textura1));
        ImageButton btn1 = new ImageButton(trd1);
        btn1.setPosition(75,480);

        TextureRegionDrawable trd2 = new TextureRegionDrawable(new TextureRegion(textura2));
        ImageButton btn2 = new ImageButton(trd2);
        btn2.setPosition(470,475);

        TextureRegionDrawable trd3 = new TextureRegionDrawable(new TextureRegion(textura3));
        ImageButton btn3 = new ImageButton(trd3);
        btn3.setPosition(800,400);

        TextureRegionDrawable trd4 = new TextureRegionDrawable(new TextureRegion(textura4));
        ImageButton btn4 = new ImageButton(trd4);
        btn4.setPosition(950,500);

        Gdx.input.setCatchBackKey(true);


        //listeners seleccion de heroes

        for(int i=0;i<botonesHeroes.size();i++){
            botonesHeroes.get(i).addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    for (int z = 0; z < botonesHeroes.size(); z++) {
                        if(botonesHeroes.get(z).isPressed()){
                            if(heroesSeleccionados.size()<3) {
                                if(!heroesSeleccionados.contains(idHeroes.get(z))) {
                                    heroesSeleccionados.add(idHeroes.get(z));
                                    flechas.add(new Image(new Texture("flechaSeleccion.png")));
                                    flechas.get(posicion).setPosition(botonesHeroes.get(z).getX(), botonesHeroes.get(z).getY() + (botonesHeroes.get(z).getHeight() + 10));
                                    escena.addActor(flechas.get(posicion));
                                    posicion++;
                                }
                            }
                        }
                    }
                }
            });
        }

        escena = new Stage();

        //fin listeners seleccion de heroes
        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new MenuPrincipal(juego));


            }
        });

        btnTaberna.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new PantallaTaberna(juego));
            }
        });

        btn1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(heroesSeleccionados.size()==3){
                    juego.setScreen(new PantallaJuego(juego,"1",heroesSeleccionados));
                }

            }
        });
        btn2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(heroesSeleccionados.size()==3){
                    juego.setScreen(new PantallaJuego(juego,"2",heroesSeleccionados));
                }
            }
        });
        btn3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(heroesSeleccionados.size()==3){
                    juego.setScreen(new PantallaJuego(juego,"3",heroesSeleccionados));
                }
            }
        });
        btn4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(heroesSeleccionados.size()==3){
                    juego.setScreen(new PantallaJuego(juego,"4",heroesSeleccionados));
                }
            }
        });

        btn5.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(heroesSeleccionados.size()==3) {
                    juego.setScreen(new PantallaJuego(juego, "5", heroesSeleccionados));
                }
            }
        });

        Gdx.input.setInputProcessor(escena);

        Image imgFondo = new Image(texturaFondo);

        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX,escalaY);
        escena.addActor(imgFondo);

        java.lang.String[] lista1 = cadena1.split("-");
        java.lang.String[] lista2 = cadena2.split("-");
        java.lang.String[] lista3 = cadena3.split("-");
        java.lang.String[] lista4 = cadena4.split("-");
        java.lang.String[] lista5 = cadena5.split("-");





        int num1= Integer.parseInt(lista1[6]);
        int num2= Integer.parseInt(lista2[6]);
        int num3= Integer.parseInt(lista3[6]);
        int num4= Integer.parseInt(lista4[6]);

        int num5= Integer.parseInt(lista5[6]);




        if(num1==1)
            escena.addActor(btn1);
        if(num2==1)
            escena.addActor(btn2);
        if(num3==1)
            escena.addActor(btn3);
        if(num4==1)
            escena.addActor(btn4);
        if(num5==1)
            escena.addActor(btn5);

        escena.addActor(btnBack);
        escena.addActor(btnTaberna);
        escena.addActor(imgMoneda);
        for(int i =0;i<botonesHeroes.size();i++){
            escena.addActor(botonesHeroes.get(i));
        }

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escena.setViewport(vista);

        int cantidadOro = Integer.parseInt(guardarOro);

        escena.draw();
        batch.begin();

        texto.mostrarMensaje(batch,"Selecciona a 3 heroes",ANCHO_MUNDO*.23f,ALTO_MUNDO*.18f);
        texto1.mostrarMensaje(batch,cantidadOro+"",ANCHO_MUNDO*.8f,ALTO_MUNDO*.9375f);
        //System.out.println(ANCHO_MUNDO*.39f+","+ALTO_MUNDO*.25f);
        //System.out.println(ANCHO_MUNDO*.8f+","+ALTO_MUNDO*.9375f);


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
