package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;

import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Created by Josep on 17/10/16.
 */
public class Heroe extends seudoSprite {
    private float b;
    private Estado estado;
    private Sprite sprite;
    private float[] posicion = new float[2];
    public Sound getSoundAttack() {
        return soundAttack;
    }
    private Sound soundAttack = Gdx.audio.newSound(Gdx.files.internal("sword-gesture2.mp3"));
    float x, y;
    private float xFinal, yFinal;
    private String nombre;
    private String clase;
    private int danoFisico;
    private int danoMagico;
    private String alcance;
    private float velocidadAatque;
    private int curacion;
    private int maxVitalidad;
    private int vitalidad;
    private float velocidadMovimiento;
    private int exp;
    private int nivel;
    private int armadura;
    private int precio;
    private ArrayList<Habilidad> habilidades;
    private Boolean disponibilidad;
    private String descripcion;
    private Texture textura;
    private String img;

    private Vida barraVida;
    private int medidax, mediday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Animación
    private Animation animacion;    // Caminando
    private float timerAnimacion;   // tiempo para calcular el frame

    private boolean direccion; //true = derecha, false = izquierda
    private int id;
    public Enemigo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Enemigo objetivo) {
        this.objetivo = objetivo;
    }

    private Enemigo objetivo;

    // animación atacando
    private Texture texturaAtacando;
    private Animation animacionAtaque;

    public int getMaxVitalidad() {
        return maxVitalidad;
    }

    public Heroe(String id, int x, int y) {

        super(y);

        String dato;
        String[] datos;

        Preferences p = Gdx.app.getPreferences("Heroes");
        dato = p.getString(id);

        datos = dato.split("-");
        this.id = Integer.parseInt(id);
        this.nombre = datos[1];
        this.clase = datos[2];
        this.estado = Estado.PARADO;
        this.danoFisico = Integer.parseInt(datos[3]);
        this.danoMagico = Integer.parseInt(datos[4]);
        this.alcance = datos[5];
        this.velocidadAatque = Float.parseFloat(datos[6]);
        this.curacion = Integer.parseInt(datos[7]);
        this.maxVitalidad = Integer.parseInt(datos[8]);
        this.vitalidad = maxVitalidad;
        this.velocidadMovimiento = Float.parseFloat(datos[9]);
        this.exp = Integer.parseInt(datos[10]);
        this.nivel = Integer.parseInt(datos[11]);
        this.armadura = Integer.parseInt(datos[12]);
        this.precio = Integer.parseInt(datos[13]);

        String var = datos[14];
        String[] vars = var.split(",");
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (int i=0;i<var.length()-1;i++){
            ids.add(Integer.parseInt(vars[i]));
        }

        Preferences h = Gdx.app.getPreferences("Habilidades");

        habilidades = new ArrayList<Habilidad>();

        for (int i=0;i< ids.size();i++){
            String dh = h.getString(ids.get(i).toString());
            String[] dhs = dh.split("-");
            habilidades.add(new Habilidad(dhs[0],dhs[1],Integer.parseInt(dhs[2]),dhs[3],Integer.parseInt(dhs[4]),dhs[5],dhs[6],Integer.parseInt(dhs[7]),dhs[8],dhs[9]));
        }




        if (datos[15]=="1") this.disponibilidad = true;
        else this.disponibilidad = false;
        this.descripcion = datos[16];
        this.textura = new Texture (datos[17]);
        this.posicion[0] = x;
        this.posicion[1] = y;
        this.img = datos[18];
        this.direccion = true;
        String[] medidas = datos[19].split(",");
        medidax = Integer.parseInt(medidas[0])/4;
        mediday = Integer.parseInt(medidas[1]);
        TextureRegion texturaCompleta = new TextureRegion(this.textura);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(medidax,mediday);
        animacion = new Animation(0.25f, texturaPersonaje[0][1],
                texturaPersonaje[0][2], texturaPersonaje[0][3]);
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion = 0;

        // texturas Ataque
        this.texturaAtacando = new Texture(datos[20]);
        TextureRegion texturaCompletaAtacando = new TextureRegion(texturaAtacando);
        TextureRegion[][] texturaPersonajeAtacando = texturaCompletaAtacando.split(medidax,mediday);
        animacionAtaque = new Animation(0.25f, texturaPersonajeAtacando[0][0],
                texturaPersonajeAtacando[0][1],texturaPersonajeAtacando[0][2],texturaPersonajeAtacando[0][3]);
        animacionAtaque.setPlayMode(Animation.PlayMode.LOOP);


        this.sprite = new Sprite(texturaPersonaje[0][0]);    // quieto
        this.sprite.setX(this.posicion[0]);
        this.sprite.setY(this.posicion[1]);

        barraVida = new Vida(this, new Texture("vidaLlena.png"), new Texture("vidaVacia.png"));
    }


    private void actualizar(){
        super.setCompy(this.getSprite().getY());
        //estados
        switch (estado){
            case PARADO:break;
            case CAMINANDO:
                if(objetivo!= null) {
                    xFinal = objetivo.getSprite().getX()-5;
                    yFinal = objetivo.getSprite().getY();
                }
                if(sprite.getX() >= xFinal) sprite.setX(sprite.getX()-velocidadMovimiento);

                if(sprite.getY() >= yFinal) sprite.setY(sprite.getY()-velocidadMovimiento);

                if(sprite.getX() <= xFinal) sprite.setX(sprite.getX()+velocidadMovimiento);

                if(sprite.getY() <= yFinal) sprite.setY(sprite.getY()+velocidadMovimiento);

                //sprite.setPosition(posicion[0],posicion[1]);
                if(sprite.getX()<=xFinal+2&&sprite.getX()>=xFinal-2&&sprite.getY()<=yFinal+2&&sprite.getY()>=yFinal-2) {
                    sprite.setY(yFinal);
                    sprite.setX(xFinal);
                    estado = Estado.PARADO;
                }

        }
        barraVida.update();


    }

    public void draw(SpriteBatch batch) {
        actualizar();
        TextureRegion region;
        switch(estado){
            case CAMINANDO:
                timerAnimacion += Gdx.graphics.getDeltaTime();
                region = animacion.getKeyFrame(timerAnimacion);
                if (direccion) {
                    if (region.isFlipX()) {
                        region.flip(true,false);
                    }
                } else {
                    if (!region.isFlipX()) {
                        region.flip(true,false);
                    }
                }
                batch.draw(region, sprite.getX(), sprite.getY());
                break;
            case ATACANDO:
                timerAnimacion += Gdx.graphics.getDeltaTime();
                region = animacionAtaque.getKeyFrame(timerAnimacion);
                if (direccion) {
                    if (region.isFlipX()) {
                        region.flip(true,false);
                    }
                } else {
                    if (!region.isFlipX()) {
                        region.flip(true,false);
                    }
                }
                batch.draw(region, sprite.getX(), sprite.getY());
                break;
            default:
                TextureRegion texturaCompleta = new TextureRegion(textura);
                TextureRegion[][] texturaPersonaje = texturaCompleta.split(medidax,mediday);
                region = texturaPersonaje[0][0];
                if (direccion) {
                    if (region.isFlipX()) {
                        region.flip(true,false);
                    }
                } else {
                    if (!region.isFlipX()) {
                        region.flip(true,false);
                    }
                }
                batch.draw(region, sprite.getX(), sprite.getY());
                break;
        }
        barraVida.draw(batch);
    }

    public boolean contiene(float x, float y){
        return sprite.getBoundingRectangle().contains(x,y);
    }
    public boolean contiene(Rectangle rectangulo){
        return sprite.getBoundingRectangle().overlaps(rectangulo);
    }
    public Estado getEstado() {
        return estado;
    }

    public enum Estado{
        ATACANDO,
        CAMINANDO,
        PARADO,
        MORIR,
        DESELECCIONADO,
        SELECCIONADO //ESTA SE USARA PARA MOSTRAR LAS HABILIDADES DEL HEROE
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getxFinal() {
        return xFinal;
    }

    public void setxFinal(float xFinal) {
        this.xFinal = xFinal;
    }

    public float getyFinal() {
        return yFinal;
    }

    public int getDanoFisico() {
        return danoFisico;
    }


    public int getVitalidad() {
        return vitalidad;
    }

    public ArrayList<Habilidad> getHabilidades() {
        return habilidades;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public Texture getTextura() {
        return textura;
    }


    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }


    public int getMedidax() {
        return medidax;
    }

    public void setDanoFisico(int danoFisico) {
        this.danoFisico = danoFisico;
    }


    public void setVitalidad(int vitalidad) {
        this.vitalidad = vitalidad;
    }


    public void setyFinal(float yFinal1){ this.yFinal = yFinal1;}

    public void setTextura(Texture textura) {
        this.textura = textura;
    }

    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
    }

    private class Vida {
        private Sprite barraVidaVacia;
        private Sprite barraVidaLlena;
        private Heroe owner;
        private final short buffer = 10;
        public Vida(Heroe owner, Texture vidaLlena, Texture vidaVacia){
            this.owner = owner;

            barraVidaVacia = new Sprite(vidaVacia);
            barraVidaLlena = new Sprite(vidaLlena);
            barraVidaLlena.setX(owner.getSprite().getX());
            barraVidaLlena.setY(owner.getSprite().getY()+owner.mediday+buffer);
            barraVidaVacia.setX(owner.getSprite().getX());
            barraVidaVacia.setY(owner.getSprite().getY()+owner.mediday+buffer);
            barraVidaLlena.setOrigin(0,0);

        }
        public void update(){
            barraVidaLlena.setX(owner.getSprite().getX());
            barraVidaLlena.setY(owner.getSprite().getY()+owner.mediday+buffer);
            barraVidaVacia.setX(owner.getSprite().getX());
            barraVidaVacia.setY(owner.getSprite().getY()+owner.mediday+buffer);
            barraVidaLlena.setScale(owner.vitalidad/(float)owner.maxVitalidad,1);
        }
        public void draw(SpriteBatch batch){
            update();
            barraVidaVacia.draw(batch);
            barraVidaLlena.draw(batch);
        }



    }
}
