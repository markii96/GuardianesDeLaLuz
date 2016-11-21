package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by LUISRICARDO on 10/20/2016.
 */
public class Cristal extends seudoSprite{

    private int vitalidad;
    private int armadura;
    private Texture textura;
    private Estado estado;
    private Sprite sprite;
    private int maxVitalidad;
    private Vida barraVida;

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Cristal(){
        super(85);
        this.vitalidad = 5000;
        maxVitalidad = vitalidad;
        this.armadura = 0;
        this.textura = new Texture("torre.png");
        this.estado = Estado.VIVIR;
        this.sprite = new Sprite(textura);
        this.sprite.setX(10);
        this.sprite.setY(50);

        barraVida = new Vida(this, new Texture("vidaLlena.png"), new Texture("vidaVacia.png"));
    }
    private void actualizar(){


        //estados
        barraVida.update();
    }
    public void draw(SpriteBatch batch) {
        actualizar();
        sprite.draw(batch);
        barraVida.draw(batch);
    }
    public boolean contiene(Rectangle rectangulo){
        return sprite.getBoundingRectangle().overlaps(rectangulo);
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
    private class Vida {
        private Sprite barraVidaVacia;
        private Sprite barraVidaLlena;
        private Cristal owner;
        private final short buffer = 10;
        public Vida(Cristal owner, Texture vidaLlena, Texture vidaVacia){
            this.owner = owner;

            barraVidaVacia = new Sprite(vidaVacia);
            barraVidaLlena = new Sprite(vidaLlena);
            barraVidaLlena.setX(owner.getSprite().getX()+owner.getSprite().getWidth()/3);
            barraVidaLlena.setY(owner.getSprite().getY()+owner.getSprite().getHeight()-buffer);
            barraVidaVacia.setX(owner.getSprite().getX()+owner.getSprite().getWidth()/3);
            barraVidaVacia.setY(owner.getSprite().getY()+owner.getSprite().getHeight()-buffer);
            barraVidaLlena.setOrigin(0,0);

        }
        public void update(){
            barraVidaLlena.setX(owner.getSprite().getX()+owner.getSprite().getWidth()/3);
            barraVidaLlena.setY(owner.getSprite().getY()+owner.getSprite().getHeight()-buffer);
            barraVidaVacia.setX(owner.getSprite().getX()+owner.getSprite().getWidth()/3);
            barraVidaVacia.setY(owner.getSprite().getY()+owner.getSprite().getHeight()-buffer);
            barraVidaLlena.setScale(owner.vitalidad/(float)owner.maxVitalidad,1);
        }
        public void draw(SpriteBatch batch){
            update();
            barraVidaVacia.draw(batch);
            barraVidaLlena.draw(batch);
        }


    }
}
