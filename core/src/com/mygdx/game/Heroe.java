package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Josep on 17/10/16.
 */
public class Heroe {

    private Estado estado;
    private Sprite sprite;

    int danoFisico;
    int danoMagico;
    float alcance;
    float velocidadAatque;
    int curacion;
    int vitalidad;
    float velocidadMovimiento;
    String nombre;
    int exp;
    int nivel;
    int armadura;
    String clase;
    int precio;
    Habilidad[] habilidades;
    Boolean disponibilidad;
    String descripcion;
    Texture textura;
    int[] posicionInicial = new int[2];
    String img;

    public Heroe(int id){

        this.textura = new Texture(img);

    }

    private void actualizar(){
        //estados
    }

    public void draw(SpriteBatch batch) {
        actualizar();
        sprite.draw(batch);
    }

    public boolean contiene(float x, float y){
        return sprite.getBoundingRectangle().contains(x,y);
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
}
