package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Josep on 20/10/16.
 */
public class Nivel {

    private String nombre;
    private String descripcion;
    private String[] enemigos;
    private ArrayList<Heroe> heroes = new ArrayList<Heroe>();
    private Integer recompensaOro;
    private Integer recompensaExp;
    private Boolean disponibilidad;
    private Texture textura;
    private Enum estado;
    private Cristal cristal;
    private int cantEnemigos;
    private int id;

    public Nivel( String id,String[] heroes) {

        String dato;
        String[] datos;
        this.id = Integer.parseInt(id);

        Preferences p = Gdx.app.getPreferences("Niveles");
        dato = p.getString(id);

        datos = dato.split("-");

        this.nombre = datos[1];
        this.descripcion = datos[2];
        this.enemigos = new String[datos[3].split(",").length];
        this.enemigos = datos[3].split(",");


        for(int i=0; i<heroes.length; i++){
            this.heroes.add(new Heroe(heroes[i],300,(i+1)*200-100));
        }

        this.recompensaOro = Integer.parseInt(datos[4]);
        this.recompensaExp = Integer.parseInt(datos[5]);
        if (datos[6] == "1") this.disponibilidad = true;
        else this.disponibilidad = false;
        this.textura = new Texture (datos[7]);
        this.cantEnemigos = Integer.parseInt(datos[8]);
        this.cristal = new Cristal();

        this.estado = Estado.JUGANDO;


    }

    public int getId() {
        return id;
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

    public ArrayList<Heroe> getHeroes() {
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

    public enum Estado{
        JUGANDO,
        GANAR,
        PERDER
    }
}
