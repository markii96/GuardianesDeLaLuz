package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


/**
 * Created by LUISRICARDO on 10/20/2016.
 */
public class Enemigo extends seudoSprite{


    private Sprite sprite;
    private int[] posicionInicial = new int[2];

    public Sound getSoundAttack() {
        return soundAttack;
    }

    public void setSoundAttack(Sound soundAttack) {
        this.soundAttack = soundAttack;
    }

    private Sound soundAttack = Gdx.audio.newSound(Gdx.files.internal("sword-slash1.mp3"));

    private String nombre;
    private int vitalidad;
    private int maxVitalidad;
    private float velocidadMovimiento;
    private float velocidadAtaque;
    private int danoFisico;
    private String alcance;
    private Estado estado;
    private Object objetivo;
    private String descripcion;
    private Texture textura;

    private float xFinal;
    private float yFinal;

    public boolean isDireccion() {
        return direccion;
    }

    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
    }

    private boolean direccion; //true = izquierda, false = derecha


    private int medidax, mediday;
    private Animation animacion;    // Caminando
    private float timerAnimacion;
    private Vida barraVida;
    private Texture texturaAtacando;
    private Animation animacionAtaque;
    private float timerAnimaiconAtaque;
    private int medidaxA,medidayA;
    public Enemigo(String id, int x, int y, Object objetivo){//debe ser con Cristal o con Heroe a la de egg

        super(y);
        String dato;
        String[] datos;

        this.posicionInicial[0] = x;
        this.posicionInicial[1] = y;

        Preferences p = Gdx.app.getPreferences("Enemigos");
        dato = p.getString(id);

        datos = dato.split("-");

        this.textura = new Texture (datos[9]);

        this.sprite =  new Sprite(this.textura);
        this.nombre = datos[1];
        this.maxVitalidad= Integer.parseInt(datos[2]);
        this.vitalidad = maxVitalidad;
        this.velocidadMovimiento = Float.parseFloat(datos[3]);
        this.velocidadAtaque = Float.parseFloat(datos[4]);
        this.danoFisico =  Integer.parseInt(datos[5]);
        this.alcance = datos[6];
        this.estado =  Estado.CAMINANDO;
        this.objetivo = objetivo;
        this.descripcion = datos[7];
        direccion = true;
        // medidas texturas
        String[] medidas = datos[10].split(",");
        medidax = Integer.parseInt(medidas[0])/2;
        if(id.equals("4")){
            medidax = Integer.parseInt(medidas[0])/3;
        }
        mediday = Integer.parseInt(medidas[1]);
        // texturas caminando
        TextureRegion texturaCompleta = new TextureRegion(this.textura);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(medidax,mediday);
        animacion = new Animation(0.25f, texturaPersonaje[0][0],
                texturaPersonaje[0][1]);
        if(id.equals("4")){
            animacion = new Animation(0.25f, texturaPersonaje[0][0],
                    texturaPersonaje[0][1],texturaPersonaje[0][2]);
        }
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion = 0;

        String[] medidasA = datos[12].split(",");

        medidaxA = Integer.parseInt(medidasA[0])/3;
        medidayA = Integer.parseInt(medidasA[1]);
        // texturas Ataque
        this.texturaAtacando = new Texture(datos[11]);
        TextureRegion texturaCompletaAtacando = new TextureRegion(texturaAtacando);
        TextureRegion[][] texturaPersonajeAtacando = texturaCompletaAtacando.split(medidaxA,medidayA);
        animacionAtaque = new Animation(0.25f, texturaPersonajeAtacando[0][0],
                texturaPersonajeAtacando[0][1],texturaPersonajeAtacando[0][2],texturaPersonajeAtacando[0][1]);
        animacionAtaque.setPlayMode(Animation.PlayMode.LOOP);

        this.sprite = new Sprite(texturaPersonaje[0][0]);    // quieto
        this.sprite.setX(x);
        this.sprite.setY(y);
        barraVida = new Vida(this, new Texture("vidaenemigo.png"), new Texture("vidaVacia.png"));
        //this.sprite.setScale(.3f);

    }

    public Object getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Object objetivo) {
        this.objetivo = objetivo;
    }


    private void actualizar(){
        super.setCompy(this.getSprite().getY());

        switch (estado){

            case PARADO:break;
            case ATACANDO:


                if(objetivo instanceof Heroe){
                    if (!this.contiene(((Heroe)objetivo).getSprite().getBoundingRectangle())){
                        estado = Estado.CAMINANDO;
                        /*if(((Heroe) objetivo).getSprite().getX()>this.getSprite().getX()){
                            direccion = true;
                        }else{
                            direccion = false;
                        }*/
                    }
                }

                if(objetivo instanceof Cristal) {
                    if (this.contiene(((Cristal) objetivo).getSprite().getBoundingRectangle())) {
                        ((Cristal) objetivo).setVitalidad(((Cristal) objetivo).getVitalidad() - this.danoFisico);
                    } else {
                        estado = Estado.CAMINANDO;
                    }
                }
                break;


            case CAMINANDO:

                if(objetivo instanceof Heroe){

                    if (this.contiene(((Heroe)objetivo).getSprite().getBoundingRectangle())){
                        estado = Estado.ATACANDO;

                        /*if(((Heroe) objetivo).getSprite().getX()>this.getSprite().getX()){
                            direccion = true;
                        }else{
                            direccion = false;
                        }*/
                    }
                }

                if(objetivo instanceof Cristal){
                    if (this.contiene(((Cristal)objetivo).getSprite().getBoundingRectangle())){
                        estado = Estado.ATACANDO;
                    }
                }

                if(objetivo instanceof Heroe){
                    xFinal = ((Heroe)objetivo).getSprite().getX();
                    yFinal = ((Heroe)objetivo).getSprite().getY();
                }else{
                    xFinal = ((Cristal)objetivo).getSprite().getX()+((Cristal)objetivo).getSprite().getWidth()-5;
                    yFinal = ((Cristal)objetivo).getSprite().getY();
                }

                if(sprite.getX() >= xFinal && sprite.getY()>=yFinal){
                    sprite.setX(sprite.getX()-velocidadMovimiento);
                    sprite.setY(sprite.getY()-velocidadMovimiento);
                }else
                if(sprite.getX() >= xFinal && sprite.getY()<=yFinal){
                    sprite.setX(sprite.getX()-velocidadMovimiento);
                    sprite.setY(sprite.getY()+velocidadMovimiento);
                }else
                if(sprite.getX() <= xFinal && sprite.getY()>=yFinal){
                    sprite.setX(sprite.getX()+velocidadMovimiento);
                    sprite.setY(sprite.getY()-velocidadMovimiento);
                }else
                if(sprite.getX() <= xFinal && sprite.getY()<=yFinal){
                    sprite.setX(sprite.getX()+velocidadMovimiento);
                    sprite.setY(sprite.getY()+velocidadMovimiento);
                }
                //sprite.setPosition(posicion[0],posicion[1]);
                else{
                    //if (this.contiene(((Cristal) objetivo).getSprite().getBoundingRectangle())) {
                    estado = Estado.ATACANDO;

                    //}
                }


            break;



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
                sprite.draw(batch);
            break;
        }
        barraVida.draw(batch);

    }

    public boolean contiene(float x, float y){
        return sprite.getBoundingRectangle().contains(x,y);
    }
    // comparar con rectangulos contiene
    public boolean contiene(Rectangle rectangulo){
        return sprite.getBoundingRectangle().overlaps(rectangulo);
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

    public String getAlcance() {
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

    public void setAlcance(String alcance) {
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
        MORIR,
        PARADO
    }

    private class Vida {
        private Sprite barraVidaVacia;
        private Sprite barraVidaLlena;
        private Enemigo owner;
        private final short buffer = 10;
        public Vida(Enemigo owner, Texture vidaLlena, Texture vidaVacia){
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
