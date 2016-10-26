package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.StringBuilder;

/**
 * Created by LUISRICARDO on 10/20/2016.
 */
public class Enemigo {


    private Sprite sprite;
    private int[] posicionInicial = new int[2];


    private String nombre;
    private int vitalidad;
    private float velocidadMovimiento;
    private float velocidadAtaque;
    private int danoFisico;
    private String alcance;
    private Estado estado;
    private String objetivo;
    private String descripcion;
    private Texture textura;


    private int medidax, mediday;
    private Animation animacion;    // Caminando
    private float timerAnimacion;

    private Texture texturaAtacando;
    private Animation animacionAtaque;
    private float timerAnimaiconAtaque;
    private int medidaxA,medidayA;
    public Enemigo(String id, int x, int y, String objetivo){

        String dato;
        String[] datos;

        this.posicionInicial[0] = x;
        this.posicionInicial[1] = y;

        Preferences p = Gdx.app.getPreferences("Enemigos");
        dato = p.getString(id);

        datos = dato.split("-");

        this.textura = new Texture (datos[9]);

        this.sprite =  new Sprite(this.textura);
        this.nombre = datos[1];
        this.vitalidad = Integer.parseInt(datos[2]);
        this.velocidadMovimiento = Float.parseFloat(datos[3]);
        this.velocidadAtaque = Float.parseFloat(datos[4]);
        this.danoFisico =  Integer.parseInt(datos[5]);
        this.alcance = datos[6];
        this.estado =  Estado.CAMINANDO;
        this.objetivo = objetivo;
        this.descripcion = datos[7];

        // medidas texturas
        String[] medidas = datos[10].split(",");
        medidax = Integer.parseInt(medidas[0])/2;
        mediday = Integer.parseInt(medidas[1]);
        // texturas caminando
        TextureRegion texturaCompleta = new TextureRegion(this.textura);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(medidax,mediday);
        animacion = new Animation(0.25f, texturaPersonaje[0][0],
                texturaPersonaje[0][1]);
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion = 0;

        String[] medidasA = datos[12].split(",");

        medidaxA = Integer.parseInt(medidasA[0])/3;
        medidayA = Integer.parseInt(medidasA[1]);
        // texturas Ataque
        this.texturaAtacando = new Texture(datos[11]);
        TextureRegion texturaCompletaAtacando = new TextureRegion(texturaAtacando);
        TextureRegion[][] texturaPersonajeAtacando = texturaCompletaAtacando.split(medidaxA,medidayA);
        animacionAtaque = new Animation(0.25f, texturaPersonajeAtacando[0][0],
                texturaPersonajeAtacando[0][1],texturaPersonajeAtacando[0][2],texturaPersonajeAtacando[0][1]);
        animacionAtaque.setPlayMode(Animation.PlayMode.LOOP);

        this.sprite = new Sprite(texturaPersonaje[0][0]);    // quieto
        this.sprite.setX(x);
        this.sprite.setY(y);
        //this.sprite.setScale(.3f);

    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }


    private void actualizar(){
        switch (estado){
            case ATACANDO:
                break;
            case CAMINANDO:
                if(sprite.getX() >= 10 && sprite.getY()>=50){
                    sprite.setX(sprite.getX()-2);
                    sprite.setY(sprite.getY()-1);
                }else
                if(sprite.getX() >= 10 && sprite.getY()<=50){
                    sprite.setX(sprite.getX()-2);
                    sprite.setY(sprite.getY()+1);
                }else
                if(sprite.getX() <= 10 && sprite.getY()>=50){
                    sprite.setX(sprite.getX()+2);
                    sprite.setY(sprite.getY()-1);
                }else
                if(sprite.getX() <= 10 && sprite.getY()<=50){
                    sprite.setX(sprite.getX()+2);
                    sprite.setY(sprite.getY()+1);
                }
                //sprite.setPosition(posicion[0],posicion[1]);
                if(sprite.getX()<=12&&sprite.getX()>=8&&sprite.getY()<=52&&sprite.getY()>=48) {
                    estado = Estado.ATACANDO;
                }

            break;



        }
    }

    public void draw(SpriteBatch batch) {
        actualizar();
        TextureRegion region;
        switch(estado){
            case CAMINANDO:
                timerAnimacion += Gdx.graphics.getDeltaTime();
                region = animacion.getKeyFrame(timerAnimacion);

                batch.draw(region, sprite.getX(), sprite.getY());
                break;
           case ATACANDO:
                timerAnimacion += Gdx.graphics.getDeltaTime();
                region = animacionAtaque.getKeyFrame(timerAnimacion);

                batch.draw(region, sprite.getX(), sprite.getY());
            break;
            default:
                sprite.draw(batch);
            break;
        }

    }

    public boolean contiene(float x, float y){
        return sprite.getBoundingRectangle().contains(x,y);
    }



    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int[] getPosicionInicial() {
        return posicionInicial;
    }

    public String getNombre() {
        return nombre;
    }


    public int getDanoFisico() {
        return danoFisico;
    }

    public String getAlcance() {
        return alcance;
    }

    public float getVelocidadAtaque() {
        return velocidadAtaque;
    }


    public int getVitalidad() {
        return vitalidad;
    }

    public float getVelocidadMovimiento() {
        return velocidadMovimiento;
    }



    public String getDescripcion() {
        return descripcion;
    }

    public Texture getTextura() {
        return textura;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setPosicionInicial(int[] posicionInicial) {
        this.posicionInicial = posicionInicial;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setDanoFisico(int danoFisico) {
        this.danoFisico = danoFisico;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public void setVelocidadAtaque(float velocidadAtaque) {
        this.velocidadAtaque = velocidadAtaque;
    }


    public void setVitalidad(int vitalidad) {
        this.vitalidad = vitalidad;
    }

    public void setVelocidadMovimiento(float velocidadMovimiento) {
        this.velocidadMovimiento = velocidadMovimiento;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTextura(Texture textura) {
        this.textura = textura;
    }

    public Estado getEstado() {
        return estado;
    }

    public enum Estado{
        ATACANDO,
        GOLPEADO,
        CAMINANDO,
        MORIR
    }

}
