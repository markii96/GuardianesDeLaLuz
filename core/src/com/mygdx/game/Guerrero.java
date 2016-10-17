package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by marco on 10/10/2016.
 */
public class Guerrero {


    private Estado estado;
    private Sprite sprite;

    int danoFisico = 50;
    int danoMagico = 0;
    int alcance = 2;
    int curacion = 0;
    int vida = 1000;


    public Guerrero(Texture textura){

        sprite = new Sprite(textura);
    }

    private void actualizar(){
        //estados
    }




    public Guerrero(Texture Textura, float x, float y){
        this(Textura);
        sprite.setPosition(x,y);
    }

    public void draw(SpriteBatch batch) {
        actualizar();
        sprite.draw(batch);
    }

    public boolean contiene(float x, float y){
        return sprite.getBoundingRectangle().contains(x,y);
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    }
