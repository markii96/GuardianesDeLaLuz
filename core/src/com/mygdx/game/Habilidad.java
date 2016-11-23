package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

/**
 * Created by Josep on 17/10/16.
 */
public  class  Habilidad {

    private String id;
    private String nombre;
    private int tiempo;
    private String tipo;
    private int disponibilidad;
    private String tono;
    private String textura;
    private String animacion;
    private int indice;
    private Texture texture;
    private String texturaReutilizacion;

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    private Sprite sprite;
    private Animation animacionA;
    private float timerAnimacion;
    private Texture texturaAnimacion;
    private Sound soundAttack;
    public Habilidad(String id, String nombre, int tiempo, String tipo, int disponibilidad, String textura, String animacion, int indice,String texturaReutilizacion,String tono) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.tipo = tipo;
        this.disponibilidad = disponibilidad;
        this.textura = textura;
        this.tono = tono;
        soundAttack = Gdx.audio.newSound(Gdx.files.internal(tono));
        this.animacion = animacion;
        this.indice = indice;
        sprite = new Sprite(new Texture(textura));
        this.texturaAnimacion = new Texture(animacion);
        this.texturaReutilizacion = texturaReutilizacion;
        TextureRegion texturaCompleta = new TextureRegion(texturaAnimacion);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(texturaCompleta.getRegionWidth()/6,texturaCompleta.getRegionHeight());
        animacionA = new Animation(0.16f, texturaPersonaje[0][0],texturaPersonaje[0][1],
                texturaPersonaje[0][2], texturaPersonaje[0][3],texturaPersonaje[0][4],texturaPersonaje[0][5]);
        timerAnimacion = 0;
    }

    public Sound getSoundAttack() {
        return soundAttack;
    }

    public void draw (SpriteBatch batch, ArrayList<Habilidad> habilidadUsada, ArrayList<Heroe> heroeHabilidad, int i) {
        sprite.setTexture(new Texture(texturaReutilizacion));
        TextureRegion region;
        timerAnimacion += Gdx.graphics.getDeltaTime();
        region = animacionA.getKeyFrame(timerAnimacion);

        if(timerAnimacion<1) {
            batch.draw(region, sprite.getX(), sprite.getY());
            //soundAttack.play();
        }
        if(timerAnimacion>tiempo){
            timerAnimacion = 0;
            sprite.setTexture(new Texture(textura));
            if(id.equals("2")){
                heroeHabilidad.get(i).setDanoFisico(heroeHabilidad.get(i).getDanoFisico()-30);
            }
            habilidadUsada.remove(i);
            heroeHabilidad.remove(i);

        }
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public String getId() {
        return id;
    }

    public String getTextura() {
        return textura;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTextura(String textura) {
        this.textura = textura;
    }

    public int getIndice() {
        return indice;
    }
}
