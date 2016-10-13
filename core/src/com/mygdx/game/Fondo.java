package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by marco on 11/10/2016.
 */
public class Fondo {

    private Sprite sprite;

    public Fondo(Texture textura){
        sprite = new Sprite(textura);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);

    }

}
