package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

/**
 * Created by marco on 08/09/2016.
 */
public class Tutorial implements Screen {

    private final Juego juego;
    private Texture texturaFondo;
    private Stage escena;
    private Texture heroe;
    private Texture enemigo;
    private Texture texturaBoton;

    //CAMARA virtual
    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 800;
    private final int ALTO_MUNDO = 480;


    private Texture texturaVida;
    private Texture texturaVida1;
    private Texture texturaHabilidad;
    private Texture texturaHabilidad1;

    public Tutorial(Juego juego) {
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
        texturaHabilidad = new Texture("aumentoarmadura.png");
        texturaVida = new Texture("vida.png");
        texturaVida1 = new Texture("vida.png");
        texturaFondo = new Texture("nivel 1.png");
        texturaHabilidad1 = new Texture("aumentodano.png");
        heroe = new Texture("Glad.png");
        enemigo = new Texture("villano1.png");
        texturaBoton = new Texture("back.png");

        TextureRegionDrawable trdDano = new TextureRegionDrawable(new TextureRegion(texturaHabilidad1));
        Image imgHabilidad1 = new Image(trdDano);

        TextureRegionDrawable trdArmadura = new TextureRegionDrawable(new TextureRegion(texturaHabilidad));
        Image imgHabilidad = new Image(trdArmadura);

        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBoton));
        ImageButton btnBack = new ImageButton(trdBtnBack);


        TextureRegionDrawable trdHeroe = new TextureRegionDrawable(new TextureRegion(heroe));
        Image imgHeroe = new Image(trdHeroe);

        TextureRegionDrawable trdVida = new TextureRegionDrawable(new TextureRegion(texturaVida));
        Image imgVida = new Image(trdVida);

        TextureRegionDrawable trdVida1 = new TextureRegionDrawable(new TextureRegion(texturaVida1));
        Image imgVida1 = new Image(trdVida1);

        TextureRegionDrawable trdEnemigo = new TextureRegionDrawable(new TextureRegion(enemigo));
        Image imgEnemigo = new Image(trdEnemigo);

        escena = new Stage();

        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //regresar al menu principal
                juego.setScreen(new MenuPrincipal(juego));

            }
        });

        Gdx.input.setInputProcessor(escena);


        Image imgFondo = new Image(texturaFondo);
        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX,escalaY);

        escena.addActor(imgFondo);
        imgVida.setScale(.3f,.3f);
        imgVida1.setScale(.3f,.3f);
        imgHeroe.setScale(.7f,.7f);
        imgHabilidad.setScale(.7f,.7f);
        imgHabilidad1.setScale(.7f,.7f);
        imgEnemigo.setScale(.7f,.7f);
        imgEnemigo.setPosition(550,75);
        imgHeroe.setPosition(100,75);
        imgVida.setPosition(150,210);
        imgVida1.setPosition(600,210);
        imgHabilidad.setPosition(15,390);
        imgHabilidad1.setPosition(165
                ,390);
        escena.addActor(imgHeroe);
        escena.addActor(imgEnemigo);
        escena.addActor(btnBack);
        escena.addActor(imgVida);
        escena.addActor(imgVida1);
        escena.addActor(imgHabilidad);
        escena.addActor(imgHabilidad1);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //
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
