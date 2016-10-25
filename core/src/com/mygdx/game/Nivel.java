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
    private Heroe[] heroes = new Heroe[3];
    private Integer recompensaOro;
    private Integer recompensaExp;
    private Boolean disponibilidad;
    private Texture textura;
    private Enum estado;
    private Cristal cristal;
    private int cantEnemigos;

    public Nivel( String id,String[] heroes) {

        String dato;
        String[] datos;

        Preferences p = Gdx.app.getPreferences("Niveles");
        dato = p.getString(id);

        datos = dato.split("-");

        this.nombre = datos[1];
        this.descripcion = datos[2];
        this.enemigos = datos[3].split(",");


        for(int i=0; i<heroes.length; i++){
            this.heroes[i] = new Heroe(heroes[i],(i+1)*50,(i+1)*50);
        }

        this.recompensaOro = Integer.parseInt(datos[4]);
        this.recompensaExp = Integer.parseInt(datos[5]);
        if (datos[6] == "1") this.disponibilidad = true;
        else this.disponibilidad = false;
        this.textura = new Texture (datos[7]);
        this.cantEnemigos = Integer.parseInt(datos[8]);
        this.cristal = new Cristal();


    }

    public void setEnemigos(String[] enemigos) {
        this.enemigos = enemigos;
    }

    public int getCantEnemigos() {
        return cantEnemigos;
    }

    public void setCantEnemigos(int cantEnemigos) {
        this.cantEnemigos = cantEnemigos;
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
