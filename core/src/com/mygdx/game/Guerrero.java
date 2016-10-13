package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by marco on 10/10/2016.
 */
public class Guerrero {

    private Sprite sprite;

    int danoFisico = 50;
    int danoMagico = 0;
    int alcance = 2;
    int curacion =0;
    int vida = 1000;


    public Guerrero(Texture textura){
        sprite = new Sprite(textura);
    }

    public Guerrero(Texture Textura, float x, float y){
        this(Textura);
        sprite.setPosition(x,y);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    }
