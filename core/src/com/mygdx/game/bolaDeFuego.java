package com.mygdx.game;

/**
 * Created by Josep on 20/11/16.
 */
public class bolaDeFuego extends Habilidad {

    public bolaDeFuego(String id, String nombre, int tiempo, String tipo, int disponibilidad, String textura, String animacion, int indice) {
        super(id, nombre, tiempo, tipo, disponibilidad, textura, animacion, indice);
    }

    @Override
    int accion() {
        return super.getIndice();
    }
}
