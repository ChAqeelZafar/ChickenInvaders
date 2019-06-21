package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.badlogic.gdx.math.Rectangle;


import java.util.ArrayList;
import java.util.Iterator;

public class Fire {
    private ArrayList<Rectangle> fires;
    private int FIRE_SIZE;
    private int PLANE_SIZE;
    private int WIDTH;
    private int HEIGHT;

    private Batch batch;
    private Camera camera;
    private Texture fireImg;

    private ArrayList<Rectangle> removeList;




    public Fire(int fire_size, int plane_size, int width, int height, Batch batch, Camera camera){
        fires = new ArrayList<Rectangle>();
        removeList = new ArrayList<Rectangle>();
        this.FIRE_SIZE = fire_size;
        this.PLANE_SIZE = plane_size;
        this.WIDTH = width;
        this.HEIGHT = height;

        this.batch = batch;
        this.camera = camera;

        fireImg = new Texture(Gdx.files.internal("fire.png"));


    }

    public ArrayList<Rectangle> getFires() {
        return fires;
    }

    public void setFires(ArrayList<Rectangle> fires) {
        this.fires = fires;
    }

    public int removeIfOverlap(Chickens chickens){
        for(Rectangle fire: fires){
            if(chickens.isOverlap(fire)){
                removeList.add(fire);
            }
        }

        //chickens.removeChickenList();
        for(Rectangle fire: removeList){
            fires.remove(fire);

        }
        int size=removeList.size();
        removeList.clear();
        return size;



    }
    public void update(){
        for(Iterator<Rectangle> iter = fires.iterator(); iter.hasNext();){
            Rectangle fire = iter.next();
            fire.y += 300 * Gdx.graphics.getDeltaTime();

            if(fire.y>=HEIGHT){
                iter.remove();
            }


        }



    }

    public void draw(){
        batch.begin();
        for(Rectangle fire: fires){
            batch.draw(fireImg, fire.x, fire.y, FIRE_SIZE, FIRE_SIZE);
        }
        batch.end();
    }
    public void spawnFire(int x){
        Rectangle newFire = new Rectangle();
        newFire.x = x;
        newFire.y = 0 + PLANE_SIZE;
        newFire.height = FIRE_SIZE;
        newFire.width = FIRE_SIZE;
        fires.add(newFire);


    }

    public void restart(){
        fires.clear();
    }



}
