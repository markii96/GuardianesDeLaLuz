package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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

/**
 * Created by marco on 17/11/2016.
 */
public class PantallaInstrucciones implements Screen {


    private int xFinal=600;
    private int posInicialX=200;
    private int yFinal=700;
    private int posInicialY=300;

    private int velocidadMovimiento=1;


    private Estado estado = Estado.REPITIENDO;
    private final Juego juego;
    private Stage escena;
    private Texture texturaBtnBack;
    private Texture texturaBtnSig;
    private Texture texturaFondo;
    private Texto texto;
    private SpriteBatch batch;
    private int id =0;
    private Texture texturaHeroe;
    private Texture texturaMano;



    //CAMARA virtual
    private OrthographicCamera camara;
    private Viewport vista;
    private final int ANCHO_MUNDO = 1280;
    private final int ALTO_MUNDO = 800;

    private Sprite Mano;


    public PantallaInstrucciones(Juego juego) {
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

        texturaFondo = new Texture("nivel1.jpeg");
        texturaBtnBack = new Texture("atras.png");
        texturaHeroe = new Texture("Glad_1.png");
        texturaMano = new Texture("mano.png");
        Mano = new Sprite(new Texture("mano.png"));
        //texturaBtnSig = new Texture("siguiente.png");
        texto = new Texto();



        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);


        escena = new Stage();


        btnBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new PantallaOpciones(juego));
            }
        });

        Gdx.input.setInputProcessor(escena);
        Image imgFondo = new Image(texturaFondo);
        Image imgMano = new Image(texturaMano);
        Image imgHeroe = new Image(texturaHeroe);

        imgMano.setPosition(300,200);
        Mano.setPosition(300,300);


        imgHeroe.setPosition(300,200);

        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX,escalaY);

        escena.addActor(imgFondo);

        escena.addActor(imgHeroe);
        escena.addActor(imgMano);

        escena.addActor(btnBack);


    }

    public void moverMano(){

        System.out.println(Mano.getX()+","+Mano.getY());

        switch (estado) {

            case REPITIENDO:

                if (Mano.getX() >= xFinal)
                    Mano.setX(Mano.getX() - velocidadMovimiento);

                if (Mano.getY() >= yFinal)
                    Mano.setY(Mano.getY() - velocidadMovimiento);

                if (Mano.getX() <= xFinal)
                    Mano.setX(Mano.getX() + velocidadMovimiento);

                if (Mano.getY() <= yFinal)
                    Mano.setY(Mano.getY() + velocidadMovimiento);

                //sprite.setPosition(posicion[0],posicion[1]);
                if (Mano.getX() <= xFinal + 2 && Mano.getX() >= xFinal - 2 && Mano.getY() <= yFinal + 2 && Mano.getY() >= yFinal - 2) {
                    Mano.setY(yFinal);
                    Mano.setX(xFinal);
                    estado = Estado.REINICIANDO;
                }


            case REINICIANDO:
                Mano.setPosition(posInicialX,posInicialY);
        }
    }

    @Override
    public void render(float delta) {
        moverMano();
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);
        escena.setViewport(vista);



        batch.begin();



        escena.draw();

        batch.end();

        batch.begin();
        texto.mostrarMensaje(batch,"Toca y desplaza al heroe para moverte",600,800);

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

    public enum Estado{
        REPITIENDO,
        REINICIANDO
    }
}
