package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Arrays;

import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Created by Josep on 17/10/16.
 */
public class Heroe {

    private Estado estado;
    private Sprite sprite;
    private int[] posicion = new int[2];

    private String nombre;
    private String clase;
    private int danoFisico;
    private int danoMagico;
    private String alcance;
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
    private String img;

    public Heroe(String id, int x, int y) {

        String dato;
        String[] datos;

        Preferences p = Gdx.app.getPreferences("Heroes");
        dato = p.getString(id);

        datos = dato.split("-");



        this.nombre = datos[1];
        this.clase = datos[2];
        this.estado = Estado.PARADO;
        this.danoFisico = Integer.parseInt(datos[3]);
        this.danoMagico = Integer.parseInt(datos[4]);
        this.alcance = datos[5];
        this.velocidadAatque = Float.parseFloat(datos[6]);
        this.curacion = Integer.parseInt(datos[7]);
        this.vitalidad = Integer.parseInt(datos[8]);
        this.velocidadMovimiento = Float.parseFloat(datos[9]);
        this.exp = Integer.parseInt(datos[10]);
        this.nivel = Integer.parseInt(datos[11]);
        this.armadura = Integer.parseInt(datos[12]);
        this.precio = Integer.parseInt(datos[13]);
        //this.habilidades = ; Checar este
        if (datos[15]=="1") this.disponibilidad = true;
        else this.disponibilidad = false;
        this.descripcion = datos[16];
        this.textura = new Texture (datos[17]);
        this.posicion[0] = x;
        this.posicion[1] = y;
        this.img = datos[17];

        this.sprite = new Sprite(this.textura);

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

    public Sprite getSprite() {
        return sprite;
    }

    public int[] getPosicion() {
        return posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClase() {
        return clase;
    }

    public int getDanoFisico() {
        return danoFisico;
    }

    public int getDanoMagico() {
        return danoMagico;
    }

    public String getAlcance() {
        return alcance;
    }

    public float getVelocidadAatque() {
        return velocidadAatque;
    }

    public int getCuracion() {
        return curacion;
    }

    public int getVitalidad() {
        return vitalidad;
    }

    public float getVelocidadMovimiento() {
        return velocidadMovimiento;
    }

    public int getExp() {
        return exp;
    }

    public int getNivel() {
        return nivel;
    }

    public int getArmadura() {
        return armadura;
    }

    public int getPrecio() {
        return precio;
    }

    public Habilidad[] getHabilidades() {
        return habilidades;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Texture getTextura() {
        return textura;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setPosicion(int[] posicionInicial) {
        this.posicion = posicionInicial;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void setDanoFisico(int danoFisico) {
        this.danoFisico = danoFisico;
    }

    public void setDanoMagico(int danoMagico) {
        this.danoMagico = danoMagico;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public void setVelocidadAatque(float velocidadAatque) {
        this.velocidadAatque = velocidadAatque;
    }

    public void setCuracion(int curacion) {
        this.curacion = curacion;
    }

    public void setVitalidad(int vitalidad) {
        this.vitalidad = vitalidad;
    }

    public void setVelocidadMovimiento(float velocidadMovimiento) {
        this.velocidadMovimiento = velocidadMovimiento;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setHabilidades(Habilidad[] habilidades) {
        this.habilidades = habilidades;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTextura(Texture textura) {
        this.textura = textura;
    }
}
