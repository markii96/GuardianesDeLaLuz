package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by marco2 y josep Y hector4 y Richo2323  on 01/09/2016.
 */
public class Juego extends Game{


    @Override
    public void create() {

        setScreen(new pantallaSplash(this));



    }
}
