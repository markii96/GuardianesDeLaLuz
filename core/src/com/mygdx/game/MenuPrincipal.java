package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
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
	private static final int ANCHO_MUNDO = 	1280 ;
	private static final int ALTO_MUNDO = 	800 ;
	private final Juego juego;
    private Stage escena;
	private Texture texturaFondo;
	//camara virtual
	private OrthographicCamera camara;
	private Viewport vista;

	String validacion;

	//textura para el titulo
	private Texture texturaTitulo;
	private Texture texturaBtnJugar;
	private Texture texturaBtnOpciones;
	private Texture texturaBtnAcercaDe;
	private Music music;
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
			preferenciasHeroes.putString("1","1-Ehtoas-Guerrero-100-0-Corto-2-0-100-2-0-1-1-0-2-1-Uno de los guardianes de la luz, su propósito es ayudar a la gente y salvar la mayor cantidad de inocentes posibles-Glad.png-Glad.png-518,173-glad_atacando.png");
			preferenciasHeroes.putString("2","2-Althalas-Mago-100-100-Largo-2-0-40-2-0-1-1-0-1-1-Uno de los guardianes de la luz, acabó sus estudios en el Circulo de Hechiceros y se unio en la lucha contra la oscuridad-mago_caminando.png-mago.png-346,173-mago_atacando.png");
			preferenciasHeroes.putString("3","3-Irina-Arquero-70-0-Largo-3-0-50-2-0-1-1-0-3-1-Dejó su pueblo para salvar la luz del mundo-arquero_caminando.png-arquero.png-346,173-arquera_atacando.png");
			preferenciasHeroes.putString("4","4-Robin-Asesino-150-0-Corto-2-0-70-2-0-1-1-0-4-1-Asesino a sueldo, un día se encuentra con los guardianes y se une a ellos. Tal vez no es la mejor de sus decisiones pero pone el pan en la mesa-robin_caminando.png-robin.png-288,576-robin_atacando.png");
			preferenciasHeroes.flush();
			preferenciasNiveles.putString("1","1-Tuorial-Emprende la nueva aventura-1,2-100-1000-1-campo.png-7");
			preferenciasNiveles.putString("2","2-La Ciudad Abandonada-Llegas a lo que fue alguna vez la ciudad mas rica del reino-1,2-120-1300-0-ciudad.png-9");
			preferenciasNiveles.putString("3","3-Bosque por siempre verde-la vegetacion te rodea, pero lo enemigos están cerca-1,2,3-200-1600-0-bosque.png-10");
			preferenciasNiveles.putString("4","4-Nieve-Te acerca a la base del enemigo, el peligro te rodea y los enemigos te acechan-1,2,3-250-1800-0-nieve.png-14");
			preferenciasNiveles.flush();
			preferenciasHabilidades.putString("1","1-Bola de Fuego-4-uno-1-bola_fuego.png-bola_fuego_anim.png-20");
			preferenciasHabilidades.putString("2","2-Puñalazo-5-uno-1-punialazo.png-punialazo_anim.png-25");
			preferenciasHabilidades.putString("3","3-Triple Flecha-6-AoE-1-triple_flecha.png-triple_flecha_anim.png-15");
			preferenciasHabilidades.flush();
			preferenciasEnemigos.putString("1","1-Dhole-500-1-2-5-corto-uno de los peones de Cthulhu, enviado a encontrar los cristales de luz-crawler.png-crawler_caminando.png-346,87-crawler_atacando.png-518,86");
			preferenciasEnemigos.putString("2","2-Azathoth-600-1-2-7-corto-enviado de la oscuridad a arrasar con la vida orgánica del planeta-soulEater.png-soulEater_caminando.png-346,173-soulEater_atacando.png-518,172");
			preferenciasEnemigos.putString("3","3-Meatball-250-2-3-4-corto-sus despreciable forma es su castigo, encomendado a defender lo que alguna vez fue tuyo-meatball.png-meatball_caminando.png-346,173-meatball_atacando.png-518,172");

			preferenciasEnemigos.flush();
			preferenciasValidacion.putString("1", "1");
			preferenciasValidacion.putString("2", "1");
			preferenciasValidacion.flush();
			//Gdx.app.log("lol",p.getString("2").toString());

		validacion = preferenciasValidacion.getString("2");

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
		btnJugar.setPosition(/*ancho*17/10 - btnJugar.getWidth()*/780, 0.50f*alto);
		btnJugar.setWidth(btnJugar.getWidth()*.5f);
		btnJugar.setHeight(btnJugar.getHeight()*.5f);
		escena.addActor(btnJugar);

		// Opciones
		TextureRegionDrawable trBtnOpciones = new TextureRegionDrawable(new TextureRegion(texturaBtnOpciones));
		ImageButton btnOpciones = new ImageButton( trBtnOpciones );
		btnOpciones.setPosition(780, 0.275f*alto);
		btnOpciones.setWidth(btnOpciones.getWidth()*.5f);
		btnOpciones.setHeight(btnOpciones.getHeight()*.5f);
		escena.addActor(btnOpciones);

		//Acerca de
		TextureRegionDrawable trBtnAcercaDe = new TextureRegionDrawable(new TextureRegion(texturaBtnAcercaDe));
		ImageButton btnAcercaDe = new ImageButton( trBtnAcercaDe );
		btnAcercaDe.setPosition(780, 0.05f*alto);
		btnAcercaDe.setWidth(btnAcercaDe.getWidth()*.5f);
		btnAcercaDe.setHeight(btnAcercaDe.getHeight()*.5f);
		escena.addActor(btnAcercaDe);

		//registrar listener
		btnJugar.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("Clicked", "Tap sobre el boton jugar");
                //cambiar pantalla
                	juego.setScreen(new PantallaMapa(juego));
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
		//
		music = Gdx.audio.newMusic(Gdx.files.internal("The_Trip_to_the_Market.mp3"));
		music.setLooping(true);

		if(validacion.equals("1")) {
			music.play();
		}

		else{
			music.pause();
		}
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
		music.dispose();

	}
}
