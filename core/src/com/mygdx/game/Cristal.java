package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by LUISRICARDO on 10/20/2016.
 */
public class Cristal {

    private int vitalidad;
    private int armadura;
    private Texture textura;
    private Estado estado;

    public Cristal(){
        this.vitalidad = 1000;
        this.armadura = 0;
        this.textura = new Texture("torre.png");
        this.estado = Estado.VIVIR;
    }

    public int getVitalidad() {
        return vitalidad;
    }

    public void setVitalidad(int vitalidad) {
        this.vitalidad = vitalidad;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public Texture getTextura() {
        return textura;
    }

    public void setTextura(Texture textura) {
        this.textura = textura;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    private enum Estado{
        DESTRUIDO,
        VIVIR,
        DAÑADO
    }

}
