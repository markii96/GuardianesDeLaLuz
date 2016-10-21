package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

//solo es una de las pantallas de la aplicacion
public class MenuPrincipal implements Screen {
	private static final int ANCHO_MUNDO = 	800 ;
	private static final int ALTO_MUNDO = 	480 ;
	private final Juego juego;
    private Stage escena;
	private Texture texturaFondo;
	//camara virtual
	private OrthographicCamera camara;
	private Viewport vista;

	//textura para el titulo
	private Texture texturaTitulo;
	private Texture texturaBtnJugar;
	private Texture texturaBtnOpciones;
	private Texture texturaBtnAcercaDe;
	private final AssetManager assetManager = new AssetManager();

    public MenuPrincipal(Juego juego) {

        this.juego = juego;

    }

	public void Preferencias(){
		Preferences preferencias = Gdx.app.getPreferences("Niveles");

		preferencias.putString("1","1-Tuorial-Emprende la nueva aventura-1,2-100-1000-1-nivel 1.png-JUGANDO");
		preferencias.flush();
		Preferences p = Gdx.app.getPreferences("Niveles");
		Gdx.app.log("PreferenciasLeidas",p.getString("1"));

	}


    public void create () {

		//Preferencias();
		cargarTexturas();
		escena = new Stage();

		//habilitar el manejo de eventos
		Gdx.input.setInputProcessor(escena);

		//calcular ancho y alto de la pantalla fisica
		//ahora con la pantalla virtual
		float ancho = ANCHO_MUNDO;//Gdx.graphics.getWidth();
		float alto = ALTO_MUNDO;// Gdx.graphics.getHeight();

		Image imgFondo=new Image(texturaFondo);
		float escalaX = ancho / imgFondo.getWidth();
		float escalaY = alto / imgFondo.getHeight();
		imgFondo.setScale(escalaX,escalaY);
		escena.addActor(imgFondo);

		//agregar botones
		//jugar
		TextureRegionDrawable trBtnJugar = new TextureRegionDrawable(new TextureRegion(texturaBtnJugar));
		ImageButton btnJugar = new ImageButton( trBtnJugar );
		btnJugar.setPosition(ancho - btnJugar.getWidth(), 0.4f*alto);
		escena.addActor(btnJugar);

		// Opciones
		TextureRegionDrawable trBtnOpciones = new TextureRegionDrawable(new TextureRegion(texturaBtnOpciones));
		ImageButton btnOpciones = new ImageButton( trBtnOpciones );
		btnOpciones.setPosition(ancho/6 - btnOpciones.getWidth()/2, 0.4f*alto);
		escena.addActor(btnOpciones);

		//Acerca de
		TextureRegionDrawable trBtnAcercaDe = new TextureRegionDrawable(new TextureRegion(texturaBtnAcercaDe));
		ImageButton btnAcercaDe = new ImageButton( trBtnAcercaDe );
		btnAcercaDe.setPosition(ancho/2 - btnAcercaDe.getWidth()/2, 0.05f*alto);
		escena.addActor(btnAcercaDe);

		//agregar titulo
		Image imgTitulo = new Image(texturaTitulo);
		imgTitulo.setPosition(ancho/2 -imgTitulo.getWidth()/2, 0.8f*alto);
		escena.addActor(imgTitulo);

		//registrar listener
		btnJugar.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("Clicked", "Tap sobre el boton jugar");
                //cambiar pantalla
                juego.setScreen(new PantallaJuego(juego));
			}
		});

		btnOpciones.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("Clicked", "Tap sobre el boton opciones");
                //Cambiar pantalla
                juego.setScreen(new PantallaOpciones(juego));
			}
		});

		btnAcercaDe.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("Clicked", "Tap sobre el boton Acerca de");
                //Cambiar pantalla
                juego.setScreen(new PantallaAcercaDe(juego));
			}
		});


	}

	private void cargarTexturas() {
		//Textura de fondo
		assetManager.load("fondo.jpeg", Texture.class);
		//Textura de botones
		assetManager.load("jugar.png", Texture.class);
		assetManager.load("opciones.png", Texture.class);
		assetManager.load("acerca de.png", Texture.class);
		assetManager.load("menu.png", Texture.class);

		assetManager.finishLoading();
		texturaFondo=assetManager.get("fondo.jpeg");

		//cuando termina, llemos las texturas
		texturaBtnJugar = assetManager.get("jugar.png");
		texturaBtnOpciones = assetManager.get("opciones.png");
		texturaBtnAcercaDe = assetManager.get("acerca de.png");
		texturaTitulo = assetManager.get("menu.png");
	}



	public void render () {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		escena.setViewport(vista);
		escena.draw();
	}

	@Override
	public void show() {

		//camara
		camara=new OrthographicCamera(ANCHO_MUNDO,ALTO_MUNDO);
		camara.position.set(ANCHO_MUNDO/2,ALTO_MUNDO/2,0);
		camara.update();
		vista= new FitViewport(ANCHO_MUNDO,ALTO_MUNDO,camara);

		//equivale a create
		create();
	}

	@Override
	public void render(float delta) {
		//equivale a render()
		render();
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
		dispose();
	}

	@Override
	public void dispose () {
		texturaFondo.dispose();
		texturaBtnAcercaDe.dispose();
		texturaTitulo.dispose();
		texturaBtnOpciones.dispose();
		texturaBtnJugar.dispose();
	}
}
