package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Josep on 20/10/16.
 */
public class Nivel {

    private String nombre;
    private String descripcion;
    private Enemigo[] enemigos;
    private Heroe[] heroes;
    private Integer recompensaOro;
    private Integer recompensaExp;
    private Boolean disponibilidad;
    private Texture textura;
    private Enum estado;
    private Cristal cristal;

    public Nivel(Heroe[] heroes) {
        this.heroes = heroes;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Enemigo[] getEnemigos() {
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

    public void setEnemigos(Enemigo[] enemigos) {
        this.enemigos = enemigos;
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
