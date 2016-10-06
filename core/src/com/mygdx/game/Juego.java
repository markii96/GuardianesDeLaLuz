package com.mygdx.game;

import com.badlogic.gdx.Game;

/**
 * Created by marco on 01/09/2016.
 */
public class Juego extends Game{


    @Override
    public void create() {
        setScreen(new MenuPrincipal(this));



    }
}
