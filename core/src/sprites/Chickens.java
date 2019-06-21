package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Iterator;

public class Chickens {
    private ArrayList<Rectangle> chickens;
    private int CHICK_SIZE;
    private int PLANE_SIZE;
    private int WIDTH;
    private int HEIGHT;

    private Batch batch;
    private Camera camera;
    private Texture chickenImg;

    private long lastChickTime;
    private ArrayList<Rectangle> removeChickens;

    public Chickens(int chick_size, int plane_size, int width, int height, Batch batch, Camera camera){
        chickens = new ArrayList<Rectangle>();
        removeChickens = new ArrayList<Rectangle>();
        spawnChicken();
        this.CHICK_SIZE = chick_size;
        this.PLANE_SIZE = plane_size;
        this.WIDTH = width;
        this.HEIGHT = height;

        this.batch = batch;
        this.camera = camera;

        chickenImg = new Texture(Gdx.files.internal("chicken.png"));


    }

    public void update(){
        if(TimeUtils.nanoTime() - lastChickTime > 1000000000){
            spawnChicken();
        }
        for(Iterator<Rectangle> iter = chickens.iterator(); iter.hasNext();){
            Rectangle chicken = iter.next();
            chicken.y -= 100 * Gdx.graphics.getDeltaTime();
            if(chicken.y<=0){
                iter.remove();
            }



        }
    }

    public ArrayList<Rectangle> getChickens() {
        return chickens;
    }

    public void setChickens(ArrayList<Rectangle> chickens) {
        this.chickens = chickens;
    }

    public void draw(){
        batch.begin();
        for(Rectangle chick: chickens){
            batch.draw(chickenImg, chick.x, chick.y, CHICK_SIZE, CHICK_SIZE);
        }
        batch.end();
    }
    public void spawnChicken(){
        Rectangle newChick = new Rectangle();
        newChick.x = MathUtils.random(0, WIDTH-CHICK_SIZE);
        newChick.y = HEIGHT;
        newChick.height = CHICK_SIZE;
        newChick.width = CHICK_SIZE;
        chickens.add(newChick);
        lastChickTime = TimeUtils.nanoTime();

    }

    public boolean isOverlap(Rectangle obj){
        for(Rectangle chick: chickens){
            if(chick.overlaps(obj)){
                 chickens.remove(chick);
                //removeChickens.add(chick);
                         return true;
            }

        }
        return false;
    }

    public void removeChickenList(){
        for(Rectangle chick: removeChickens){
            chickens.remove(chick);

        }
    }

    public void restart(){
        chickens.clear();
    }



}
