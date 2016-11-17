package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
    private Texture texturaNext;
    private SpriteBatch batch;

    private int idTexto =1;

    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 1280;
    private final int ALTO_MUNDO = 800;

    public PantallaHistoria(Juego juego){
        this.juego = juego;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();

        camara = new OrthographicCamera(ANCHO_MUNDO,ALTO_MUNDO);
        camara.position.set(ANCHO_MUNDO/2,ALTO_MUNDO/2,0);
        camara.update();
        vista = new StretchViewport(ANCHO_MUNDO,ALTO_MUNDO,camara);
        float ancho = ANCHO_MUNDO;//Gdx.graphics.getWidth();
        float alto = ALTO_MUNDO;//Gdx.graphics.getHeight();

        texto = new Texto();

        texturaNext = new Texture("1.png");
        texturaFondo = new Texture("nieve.png");
        texturaBtnBack = new Texture("atras.png");
        texturaBtnSig = new Texture("2.png");
        Image imgFondo = new Image(texturaFondo);


        TextureRegionDrawable trdBtnNext = new TextureRegionDrawable(new TextureRegion(texturaNext));
        ImageButton btnNext = new ImageButton(trdBtnNext);



        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);
/*
        TextureRegionDrawable trdBtnSig = new TextureRegionDrawable(new TextureRegion(texturaBtnSig));
        ImageButton btnSig = new ImageButton(trdBtnSig);
        btnSig.setPosition(1000,0);
*/


        btnNext.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                idTexto++;
            }
        });

        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(idTexto==1) {
                    juego.setScreen(new PantallaOpciones(juego));
                }

                else{
                    idTexto--;
                }
            }
        });

        escena = new Stage();

        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX,escalaY);
        btnNext.setPosition(1000,0);

        escena.addActor(imgFondo);
        escena.addActor(btnBack);
        escena.addActor(btnNext);



    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);
        escena.setViewport(vista);
        escena.draw();


        batch.begin();
        batch.end();

        batch.begin();

        if(idTexto==1) {

            texto.mostrarMensaje(batch, "Erase una vez, hace mucho tiempo en una tierra muy", 650, 600);
            texto.mostrarMensaje(batch, "lejana, un reino que siempre vivia en el temor.", 650, 550);
            texto.mostrarMensaje(batch, "Todos los dias, el reino sufria ataques de enemigos", 650, 500);
            texto.mostrarMensaje(batch, "desconocidos. Estos enemigos eran unos monstruos", 650, 450);
            texto.mostrarMensaje(batch, "grandes y fuertes. Cuando el reino estuvo a punto", 650, 400);
            texto.mostrarMensaje(batch, "de ser destruido, el cielo se abrio y una esfera", 650, 350);
            texto.mostrarMensaje(batch, "de luz salio de entre las nubes, ahuyentando a la", 650, 300);
            texto.mostrarMensaje(batch, "horda adversaria por su gran aura de luz.", 650, 250);
        }

        if(idTexto==2){

            texto.mostrarMensaje(batch, "Cuando el ataque por fin finalizó, la esfera se", 650, 700);
            texto.mostrarMensaje(batch, "acercó a los humanos y sorpresivamente la esfera", 650, 650);
            texto.mostrarMensaje(batch, "hablaba. La esfera explicó que provenía de otro", 650, 600);
            texto.mostrarMensaje(batch, "mundo que ya había sido atacado por las fuerzas", 650, 550);
            texto.mostrarMensaje(batch, "oscuras, y vino para advertir a las siguientes", 650, 500);
            texto.mostrarMensaje(batch, "víctimas. Además también les explicó la razón", 650, 450);
            texto.mostrarMensaje(batch, "de las acciones de los monstruos. ", 650, 400);
            texto.mostrarMensaje(batch, "–Todos esos monstruos vienen de otro mundo para", 650, 250);
            texto.mostrarMensaje(batch, "conquistar el suyo-.", 650, 200);


        }

        if(idTexto==3) {

            texto.mostrarMensaje(batch, "El ángel les explico que Cthulhu había regresado", 650, 500);
            texto.mostrarMensaje(batch, "para conquistar todos los mundos con su oscuridad.", 650, 450);
            texto.mostrarMensaje(batch, "Para detenerlo, se necesitaba del poder de la luz", 650, 400);
            texto.mostrarMensaje(batch, "y de la fuerzo de varios héroes dispuestos a ", 650, 350);
            texto.mostrarMensaje(batch, "salvar la luz de este mundo.", 650, 400);

        }
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

        vista.update(width,height);
        System.out.println("");

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
