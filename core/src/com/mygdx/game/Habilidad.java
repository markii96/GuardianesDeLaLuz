package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Josep on 17/10/16.
 */
public abstract class  Habilidad {

    private String id;
    private String nombre;
    private int tiempo;
    private String tipo;
    private int disponibilidad;
    private String textura;
    private String animacion;
    private int indice;
    private Texture texture;

    public Habilidad(String id, String nombre, int tiempo, String tipo, int disponibilidad, String textura, String animacion, int indice) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.tipo = tipo;
        this.disponibilidad = disponibilidad;
        this.textura = textura;
        this.animacion = animacion;
        this.indice = indice;

        texture = new Texture(textura);
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

    public String getNombre() {
        return nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public String getTextura() {
        return textura;
    }

    public String getAnimacion() {
        return animacion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setTextura(String textura) {
        this.textura = textura;
    }

    public void setAnimacion(String animacion) {
        this.animacion = animacion;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    abstract int  accion();
}
