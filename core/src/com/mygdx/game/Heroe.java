package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;

import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Created by Josep on 17/10/16.
 */
public class Heroe {
    private float b;
    private Estado estado;
    private Sprite sprite;
    private float[] posicion = new float[2];

    float pendiente;
    float x,y;
    public float xFinal, yFinal;

    private String nombre;
    private String clase;
    private int danoFisico;
    private int danoMagico;
    private String alcance;
    private float velocidadAatque;
    private int curacion;
    private int vitalidad;
    private float velocidadMovimiento;
    private int exp;
    private int nivel;
    private int armadura;
    private int precio;
    private Habilidad[] habilidades;
    private Boolean disponibilidad;
    private String descripcion;
    private Texture textura;
    private String img;
    float[] cambioPosicion = new float[2];

    // Animación
    private Animation animacion;    // Caminando
    private float timerAnimacion;   // tiempo para calcular el frame

    public Heroe(String id, int x, int y) {

        String dato;
        String[] datos;

        Preferences p = Gdx.app.getPreferences("Heroes");
        dato = p.getString(id);

        datos = dato.split("-");

        this.nombre = datos[1];
        this.clase = datos[2];
        this.estado = Estado.PARADO;
        this.danoFisico = Integer.parseInt(datos[3]);
        this.danoMagico = Integer.parseInt(datos[4]);
        this.alcance = datos[5];
        this.velocidadAatque = Float.parseFloat(datos[6]);
        this.curacion = Integer.parseInt(datos[7]);
        this.vitalidad = Integer.parseInt(datos[8]);
        this.velocidadMovimiento = Float.parseFloat(datos[9]);
        this.exp = Integer.parseInt(datos[10]);
        this.nivel = Integer.parseInt(datos[11]);
        this.armadura = Integer.parseInt(datos[12]);
        this.precio = Integer.parseInt(datos[13]);

        String[] hab = datos[14].split(",");
        this.habilidades = new Habilidad[hab.length];

        for (int i =0; i< hab.length; i++){
            habilidades[i] = new Habilidad(hab[i]);

            this.x = posicion[0];
            this.y = posicion[1];
        }

        if (datos[15]=="1") this.disponibilidad = true;
        else this.disponibilidad = false;
        this.descripcion = datos[16];
        this.textura = new Texture (datos[17]);
        this.posicion[0] = x;
        this.posicion[1] = y;
        this.img = datos[18];

        String[] medidas = datos[19].split(",");
        int medidax = Integer.parseInt(medidas[0])/4;
        int mediday = Integer.parseInt(medidas[1]);

        Gdx.app.log("prueba",medidas[1]);


        TextureRegion texturaCompleta = new TextureRegion(this.textura);
        // La divide en frames de 16x32 (ver marioSprite.png)
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(medidax,mediday);
        // Crea la animación con tiempo de 0.25 segundos entre frames.
        Animation animacion = new Animation(0.25f, texturaPersonaje[0][3],
                texturaPersonaje[0][2], texturaPersonaje[0][1]);
        // Animación infinita
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        // Inicia el timer que contará tiempo para saber qué frame se dibuja
        timerAnimacion = 0;
        // Crea el sprite cuando para el personaje quieto (idle)
        this.sprite = new Sprite(texturaPersonaje[0][0]);    // quieto
        this.sprite.setX(this.posicion[0]);
        this.sprite.setY(this.posicion[1]);
        this.sprite.setScale(.3f);
    }


    private void actualizar(){
        //estados

        pendiente = ((yFinal-posicion[0])/(xFinal-posicion[1]));
        b = (pendiente*posicion[0])-posicion[1];

        switch (estado){


            case PARADO:break;
            case CAMINANDO:

                if(posicion[0] >= xFinal && posicion[1]>=yFinal){
                    posicion[0] -= 2;
                    posicion[1] -=2;
                    sprite.setPosition(posicion[0],posicion[1]);

                }
                if(posicion[0] >= xFinal && posicion[1]<=yFinal){
                    posicion[0] -= 2;
                    posicion[1] +=2;
                    sprite.setPosition(posicion[0],posicion[1]);
                }
                if(posicion[0] <= xFinal && posicion[1]>=yFinal){
                    posicion[0] += 2;
                    posicion[1] -=2;
                    sprite.setPosition(posicion[0],posicion[1]);
                }
                if(posicion[0] <= xFinal && posicion[1]<=yFinal){
                    posicion[0] += 2;
                    posicion[1] +=2;
                    sprite.setPosition(posicion[0],posicion[1]);
                }

                if(this.posicion[0]==xFinal && this.posicion[1] == yFinal){
                    estado = Estado.PARADO;
                }
        }


    }

    public void draw(SpriteBatch batch) {
        actualizar();
        sprite.draw(batch);
    }

    public boolean contiene(float x, float y){
        return sprite.getBoundingRectangle().contains(x,y);
    }

    public void mover(float x, float y){

    }


    public Estado getEstado() {
        return estado;
    }

    public enum Estado{
        ATACANDO,
        GOLPEADO,
        CAMINANDO,
        PARADO,
        MORIR,
        USANDOHABILDAD,
        SELECCIONADO //ESTA SE USARA PARA MOSTRAR LAS HABILIDADES DEL HEROE
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float[] getPosicion() {
        return posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClase() {
        return clase;
    }

    public int getDanoFisico() {
        return danoFisico;
    }

    public int getDanoMagico() {
        return danoMagico;
    }

    public String getAlcance() {
        return alcance;
    }

    public float getVelocidadAatque() {
        return velocidadAatque;
    }

    public int getCuracion() {
        return curacion;
    }

    public int getVitalidad() {
        return vitalidad;
    }

    public float getVelocidadMovimiento() {
        return velocidadMovimiento;
    }

    public int getExp() {
        return exp;
    }

    public int getNivel() {
        return nivel;
    }

    public int getArmadura() {
        return armadura;
    }

    public int getPrecio() {
        return precio;
    }

    public Habilidad[] getHabilidades() {
        return habilidades;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Texture getTextura() {
        return textura;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setPosicion(float[] posicionInicial) {
        this.posicion = posicionInicial;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void setDanoFisico(int danoFisico) {
        this.danoFisico = danoFisico;
    }

    public void setDanoMagico(int danoMagico) {
        this.danoMagico = danoMagico;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public void setVelocidadAatque(float velocidadAatque) {
        this.velocidadAatque = velocidadAatque;
    }

    public void setCuracion(int curacion) {
        this.curacion = curacion;
    }

    public void setVitalidad(int vitalidad) {
        this.vitalidad = vitalidad;
    }

    public void setVelocidadMovimiento(float velocidadMovimiento) {
        this.velocidadMovimiento = velocidadMovimiento;
    }

   // public void setExp(int exp) {
    //    this.exp = exp;
    //}


    public void setyFinal(float yFinal1){ this.yFinal = yFinal1;}

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setHabilidades(Habilidad[] habilidades) {
        this.habilidades = habilidades;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTextura(Texture textura) {
        this.textura = textura;
    }
/*
    public void render(SpriteBatch batch) {
        timerAnimacion += Gdx.graphics.getDeltaTime();
        // Obtiene el frame que se debe mostrar (de acuerdo al timer)
        TextureRegion region = animacion.getKeyFrame(timerAnimacion);
        batch.draw(region, sprite.getX(), sprite.getY());
    }

*/
    /*  codigo para correr Sprites
    private EstadoMovimiento estadoMovimiento=EstadoMovimiento.INICIANDO;

	public Personaje(Texture textura) {
        // Lee la textura como región
        TextureRegion texturaCompleta = new TextureRegion(textura);
        // La divide en 4 frames de 32x64
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(32,64);
        // Crea la animación con tiempo de 0.25 segundos entre frames.
        animacion = new Animation(0.25f,texturaPersonaje[0][1],
                texturaPersonaje[0][2], texturaPersonaje[0][3] );

        // Animación infinita
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        // Inicia el timer que contará tiempo para saber qué frame se dibuja
        timerAnimacion = 0;
        // Crea el sprite con el personaje quieto (idle)
        sprite = new Sprite(texturaPersonaje[0][0]);    // QUIETO
        sprite.setPosition(300,800);    // Posición inicial	///////mover aqui
    }

public void render(SpriteBatch batch) {
        // Dibuja el personaje dependiendo del estadoMovimiento
        switch (estadoMovimiento) {
            case MOV_DERECHA:
            case MOV_IZQUIERDA:
                timerAnimacion += Gdx.graphics.getDeltaTime();
                TextureRegion region = animacion.getKeyFrame(timerAnimacion);		//preguntar
                if (estadoMovimiento==EstadoMovimiento.MOV_IZQUIERDA) {
                    if (!region.isFlipX()) {
                        region.flip(true,false);
                    }
                } else {
                    if (region.isFlipX()) {
                        region.flip(true,false);
                    }
                }
                batch.draw(region,sprite.getX(),sprite.getY());
                break;
            case QUIETO:
            case INICIANDO:
                sprite.draw(batch); // Dibuja el sprite
                break;
        }
    }

public void actualizar(TiledMap mapa) {
        switch (estadoMovimiento) {
            case MOV_DERECHA:
            case MOV_IZQUIERDA:
                moverHorizontal(mapa);
                break;
        }


        }

    }






    */


}
