package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.StringBuilder;

/**
 * Created by LUISRICARDO on 10/20/2016.
 */
public class Enemigo {


    private Sprite sprite;
    private int[] posicionInicial = new int[2];


    private String nombre;
    private int vitalidad;
    private float velocidadMovimiento;
    private float velocidadAtaque;
    private int danoFisico;
    private float alcance;
    private Estado estado;
    private String objetivo;
    private String descripcion;
    private Texture textura;

    public Enemigo(String id, int x, int y, String objetivo){

        String dato;
        String[] datos;

        this.posicionInicial[0] = x;
        this.posicionInicial[1] = y;

        Preferences p = Gdx.app.getPreferences("Enemigos");
        dato = p.getString(id);

        datos = dato.split("-");

        this.textura = new Texture (datos[8]);

        this.sprite =  new Sprite(this.textura);
        this.nombre = datos[1];
        this.vitalidad = Integer.parseInt(datos[2]);
        this.velocidadMovimiento = Float.parseFloat(datos[3]);
        this.velocidadAtaque = Float.parseFloat(datos[4]);
        this.danoFisico =  Integer.parseInt(datos[5]);
        this.alcance = Float.parseFloat(datos[6]);
        this.estado =  Estado.CAMINANDO;
        this.objetivo = objetivo;
        this.descripcion = datos[7];



    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
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



    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int[] getPosicionInicial() {
        return posicionInicial;
    }

    public String getNombre() {
        return nombre;
    }


    public int getDanoFisico() {
        return danoFisico;
    }

    public float getAlcance() {
        return alcance;
    }

    public float getVelocidadAtaque() {
        return velocidadAtaque;
    }


    public int getVitalidad() {
        return vitalidad;
    }

    public float getVelocidadMovimiento() {
        return velocidadMovimiento;
    }



    public String getDescripcion() {
        return descripcion;
    }

    public Texture getTextura() {
        return textura;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setPosicionInicial(int[] posicionInicial) {
        this.posicionInicial = posicionInicial;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setDanoFisico(int danoFisico) {
        this.danoFisico = danoFisico;
    }

    public void setAlcance(float alcance) {
        this.alcance = alcance;
    }

    public void setVelocidadAtaque(float velocidadAtaque) {
        this.velocidadAtaque = velocidadAtaque;
    }


    public void setVitalidad(int vitalidad) {
        this.vitalidad = vitalidad;
    }

    public void setVelocidadMovimiento(float velocidadMovimiento) {
        this.velocidadMovimiento = velocidadMovimiento;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTextura(Texture textura) {
        this.textura = textura;
    }

    public Estado getEstado() {
        return estado;
    }

    public enum Estado{
        ATACANDO,
        GOLPEADO,
        CAMINANDO,
        MORIR
    }

}
