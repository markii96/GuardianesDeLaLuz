package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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


public class PantallaAcercaDe implements Screen {

    private final Juego juego;
    private Stage escena;
    private Texture texturaBtnBack;
    private Texture texturaCara;
    //1
    private Texture texturaFondo;


    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 800;
    private final int ALTO_MUNDO = 480;


    public PantallaAcercaDe(Juego juego){

        this.juego = juego;
    }

    @Override
    public void show() {

        // camara

        camara = new OrthographicCamera(ANCHO_MUNDO,ALTO_MUNDO);
        camara.position.set(ANCHO_MUNDO/2,ALTO_MUNDO/2,0);
        camara.update();
        vista = new StretchViewport(ANCHO_MUNDO,ALTO_MUNDO,camara);

        float ancho = ANCHO_MUNDO;//Gdx.graphics.getWidth();
        float alto = ALTO_MUNDO;//Gdx.graphics.getHeight();
        //2
        texturaFondo = new Texture("fondo.jpeg");
        texturaBtnBack = new Texture("back.png");
        texturaCara = new Texture("cara.png");

        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);
        //btnBack.setPosition(ancho/3-btnBack.getWidth()/2,alto*0.05f);


        escena = new Stage();




        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //regresar al menu principal
                juego.setScreen(new MenuPrincipal(juego));

            }
        });

        Gdx.input.setInputProcessor(escena);

        //3 el fondo
        Image imgFondo = new Image(texturaFondo);
        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX,escalaY);

        escena.addActor(imgFondo);
        escena.addActor(btnBack);



        //agregar caras(fotos de cada integrante)
        for(int i = 0; i<2; i++){
            for(int j=0; j<2;j++){
                Image imgCara = new Image(texturaCara);
                imgCara.setPosition(250+j*200,100+i*200);
                escena.addActor(imgCara);
            }
        }

    }




    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(1,0,0,1);
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
