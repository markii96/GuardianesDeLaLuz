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
		/*Gdx.app.log("PreferenciasLeidas",p.getString("1"));*/

		Preferences preferenciasNiveles = Gdx.app.getPreferences("Niveles");
		Preferences preferenciasValidacion = Gdx.app.getPreferences("Validacion");
		Preferences preferenciasHeroes = Gdx.app.getPreferences("Heroes");
		Preferences preferenciasHabilidades = Gdx.app.getPreferences("Habilidades");
		Preferences preferenciasEnemigos = Gdx.app.getPreferences("Enemigos");
		//if(!preferenciasValidacion.get().toString().equals("{}")){
			Preferences p = Gdx.app.getPreferences("Enemigos");
			preferenciasHeroes.putString("1","1-Ehtoas-Guerrero-100-0-Corto-2-0-100-2-0-1-1-0-1-1-Uno de los guardianes de la luz, su propósito es ayudar a la gente y salvar la mayor cantidad de inocentes posibles-Glad.png-Glad.png-1728,576");
			preferenciasHeroes.putString("2","2-Althalas-Mago-0-100-Largo-2-0-40-2-0-1-1-0-2-1-Uno de los guardianes de la luz, acabó sus estudios en el Circulo de Hechiceros y se unio en la lucha contra la oscuridad-mago_caminando.png-mago.png-346,173");
			preferenciasHeroes.putString("3","3-Orina-Arquero-70-0-Largo-3-0-50-2-0-1-1-0-3-1-Dejó su pueblo para salvar la luz del mundo-arquero_caminando.png-arquero.png-1152,576");
			preferenciasHeroes.flush();
			preferenciasNiveles.putString("1","1-Tuorial-Emprende la nueva aventura-1,2-100-1000-1-campo.png-10");
			preferenciasNiveles.flush();
			preferenciasHabilidades.putString("1","1-Puñalazo-5-uno-1-punialazo.png-punialazo_anim.png");
			preferenciasHabilidades.putString("2","2-Misil Arcano-4-uno-1-misil_arcano.png-misil_arcano_anim.png");
			preferenciasHabilidades.putString("3","3-Triple Flecha-6-AoE-1-triple_flecha.png-triple_flecha_anim.png");
			preferenciasEnemigos.putString("1","1-crawler-500-2-2-10-corto-uno de los peones de Cthulhu, enviado a encontrar los cristales de luz-crawler.png-crawler_caminando.png-1152,288-crawler_atacando.png-1728,288");
			preferenciasEnemigos.putString("2","2-soulEater-800-1-2-15-corto-enviado de la oscuridad a arrasar con la vida orgánica del planeta-soulEater.png-soulEater_caminando.png-1152,576-soulEater_atacando-1728,288");
			preferenciasEnemigos.flush();
			preferenciasValidacion.putString("1", "1");
			preferenciasValidacion.flush();
			//Gdx.app.log("lol",p.get().toString());

		//}
	}

    public void create () {

		Preferencias();
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
		btnJugar.setPosition(ancho*17/10 - btnJugar.getWidth(), 0.50f*alto);
		btnJugar.setWidth(btnJugar.getWidth()*.35f);
		btnJugar.setHeight(btnJugar.getHeight()*.35f);
		escena.addActor(btnJugar);

		// Opciones
		TextureRegionDrawable trBtnOpciones = new TextureRegionDrawable(new TextureRegion(texturaBtnOpciones));
		ImageButton btnOpciones = new ImageButton( trBtnOpciones );
		btnOpciones.setPosition(ancho*17/10 - btnOpciones.getWidth(), 0.275f*alto);
		btnOpciones.setWidth(btnOpciones.getWidth()*.35f);
		btnOpciones.setHeight(btnOpciones.getHeight()*.35f);
		escena.addActor(btnOpciones);

		//Acerca de
		TextureRegionDrawable trBtnAcercaDe = new TextureRegionDrawable(new TextureRegion(texturaBtnAcercaDe));
		ImageButton btnAcercaDe = new ImageButton( trBtnAcercaDe );
		btnAcercaDe.setPosition(ancho*17/10 - btnAcercaDe.getWidth(), 0.05f*alto);
		btnAcercaDe.setWidth(btnAcercaDe.getWidth()*.35f);
		btnAcercaDe.setHeight(btnAcercaDe.getHeight()*.35f);
		escena.addActor(btnAcercaDe);

		//agregar titulo
		/*Image imgTitulo = new Image(texturaTitulo);
		imgTitulo.setPosition(ancho/2 -imgTitulo.getWidth()/2, 0.8f*alto);
		escena.addActor(imgTitulo);*/

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
		assetManager.load("fondo.png", Texture.class);
		//Textura de botones
		assetManager.load("jugar.png", Texture.class);
		assetManager.load("opciones.png", Texture.class);
		assetManager.load("acerca de.png", Texture.class);
		assetManager.load("menu.png", Texture.class);

		assetManager.finishLoading();
		texturaFondo=assetManager.get("fondo.png");

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
