package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Josep on 20/10/16.
 */
public class Nivel {

    private String nombre;
    private String descripcion;
    private String[] enemigos;
    private Heroe[] heroes;
    private Integer recompensaOro;
    private Integer recompensaExp;
    private Boolean disponibilidad;
    private Texture textura;
    private Enum estado;
    private Cristal cristal;

    public Nivel(String[] heroes, String id) {

        String dato;
        String[] datos;

        Preferences p = Gdx.app.getPreferences("Niveles");
        dato = p.getString(id);

        datos = dato.split("|");

        this.nombre = datos[0];
        this.descripcion = datos[1];
        this.enemigos = datos[2].split(",");
        this.heroes[0] = new Heroe(heroes[0],50,50);
        this.heroes[1] = new Heroe(heroes[1],100,100);
        this.heroes[2] = new Heroe(heroes[2],200,200);
        this.recompensaOro = Integer.parseInt(datos[3]);
        this.recompensaExp = Integer.parseInt(datos[4]);
        if (datos[5]=="1") this.disponibilidad = true;
        else this.disponibilidad = false;
        this.textura = new Texture (datos[6]);
        this.cristal = new Cristal();


    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String[] getEnemigos() {
        return enemigos;
    }

    public Heroe[] getHeroes() {
        return heroes;
    }

    public Integer getRecompensaOro() {
        return recompensaOro;
    }

    public Integer getRecompensaExp() {
        return recompensaExp;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public Texture getTextura() {
        return textura;
    }

    public Enum getEstado() {
        return estado;
    }

    public Cristal getCristal() {
        return cristal;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public void setHeroes(Heroe[] heroes) {
        this.heroes = heroes;
    }

    public void setRecompensaOro(Integer recompensaOro) {
        this.recompensaOro = recompensaOro;
    }

    public void setRecompensaExp(Integer recompensaExp) {
        this.recompensaExp = recompensaExp;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setTextura(Texture textura) {
        this.textura = textura;
    }

    public void setEstado(Enum estado) {
        this.estado = estado;
    }

    public void setCristal(Cristal cristal) {
        this.cristal = cristal;
    }
}
