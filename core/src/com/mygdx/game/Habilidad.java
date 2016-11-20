package com.mygdx.game;

/**
 * Created by Josep on 17/10/16.
 */
public abstract class  Habilidad {

    private String id;
    private String nombre;

    public Habilidad(String id, String nombre, int indice) {
        this.id = id;
        this.nombre = nombre;
    }

    abstract int  accion();
}
