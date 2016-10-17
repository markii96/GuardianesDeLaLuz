package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Created by Josep on 17/10/16.
 */
public class Heroe {

    private Estado estado;
    private Sprite sprite;

    private String nombre;
    private String clase;
    private int danoFisico;
    private int danoMagico;
    private float alcance;
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
    private int[] posicionInicial = new int[2];
    private String img;

    public Heroe(int id, int x, int y) {

        String dato;
        String[] datos;

        datos = dato.split("\n");

        for (int i = 0; i < datos.length; i++){
            if ((int) datos[i].charAt(0) == id) {
                dato = datos[i];
                break;
            }
        }
        datos = dato.split("|");

        this.nombre = datos[0];
        this.clase = datos[1];
        this.estado = Estado.PARADO;
        this.nombre = datos[4];
        this.clase = datos[5];
        this.danoFisico = Integer.parseInt(datos[6]);
        this.danoMagico = Integer.parseInt(datos[7]);
        this.alcance = Float.parseFloat(datos[8]);
        this.velocidadAatque = Float.parseFloat(datos[9]);
        this.curacion = Integer.parseInt(datos[10]);
        this.vitalidad = Integer.parseInt(datos[11]);
        this.velocidadMovimiento = Float.parseFloat(datos[12]);
        this.exp = Integer.parseInt(datos[13]);
        this.nivel = Integer.parseInt(datos[14]);
        this.armadura = Integer.parseInt(datos[15]);
        this.precio = Integer.parseInt(datos[16]);
        //this.habilidades = ; Checar este
        if (datos[18]=="1") this.disponibilidad = true;
        else this.disponibilidad = false;
        this.descripcion = datos[19];
        this.textura = new Texture (datos[20]);
        this.posicionInicial[0] = x;
        this.posicionInicial[1] = y;
        this.img = datos[21];

        this.sprite = new Sprite(this.textura);;




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
