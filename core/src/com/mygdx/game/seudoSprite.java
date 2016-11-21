package com.mygdx.game;

/**
 * Created by Josep on 20/11/16.
 */
public  class seudoSprite implements Comparable<seudoSprite> {

    private float compy;

    public seudoSprite(float compy) {
        this.compy = compy;
    }

    @Override
    public int compareTo(seudoSprite o) {
        if (o.getCompy()>= compy){
            return 1;

        }else{
            return -1;
        }

    }

    public float getCompy() {
        return compy;
    }

    public void setCompy(float compy) {
        this.compy = compy;
    }
}
