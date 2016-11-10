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
 * Created by marco on 04/11/2016.
 */
public class PantallaHistoria implements Screen {

    private Juego juego;
    private Stage escena;
    private Texture texturaFondo;
    private Texto texto;
    private Texture texturaBtnBack;
    private Texture texturaBtnSig;

    private int idTexto =0;

    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 800;
    private final int ALTO_MUNDO = 480;

    public PantallaHistoria(Juego juego){
        this.juego = juego;
    }

    @Override
    public void show() {

        camara = new OrthographicCamera(ANCHO_MUNDO,ALTO_MUNDO);
        camara.position.set(ANCHO_MUNDO/2,ALTO_MUNDO/2,0);
        camara.update();
        vista = new StretchViewport(ANCHO_MUNDO,ALTO_MUNDO,camara);
        float ancho = ANCHO_MUNDO;//Gdx.graphics.getWidth();
        float alto = ALTO_MUNDO;//Gdx.graphics.getHeight();
        texturaFondo = new Texture("fondo.png");
        texturaBtnBack = new Texture("atras.png");
        //texturaBtnSig = new Texture("siguiente.png");
        Image imgFondo = new Image(texturaFondo);


        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);
        /*
        TextureRegionDrawable trdBtnSig = new TextureRegionDrawable(new TextureRegion(texturaBtnSig));
        ImageButton btnSig = new ImageButton(trdBtnSig);
        btnSig.setPosition(ANCHO_MUNDO-50,0);

*/

        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                juego.setScreen(new PantallaOpciones(juego));

            }
        });

        /*
        btnSig.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                idTexto++;

            }
        });
*/

        escena = new Stage();

        switch (idTexto){


            //escena.addActor(textoPantalla0);

            case 0:



                break;
            case 1:

                 break;
            case 2:

                //desaparecer boton siguiente

                break;

        }


        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX,escalaY);

        escena.addActor(imgFondo);
        escena.addActor(btnBack);
//        escena.addActor(btnSig);



    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escena.setViewport(vista);
        escena.draw();

    }

    @Override
    public void resize(int width, int height) {

        vista.update(width,height);
        System.out.println("");//hola

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
