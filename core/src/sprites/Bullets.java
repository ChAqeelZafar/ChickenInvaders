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

public class Bullets {
    private ArrayList<Rectangle> bullets;
    private int BULLET_SIZE;
    private int PLANE_SIZE;
    private int WIDTH;
    private int HEIGHT;

    private Batch batch;
    private Camera camera;
    private Texture bulletImg;

    private long lastBulletTime;


    public Bullets(int bullet_size, int plane_size, int width, int height, Batch batch, Camera camera){
        bullets = new ArrayList<Rectangle>();
        this.BULLET_SIZE = bullet_size;
        this.PLANE_SIZE = plane_size;
        this.WIDTH = width;
        this.HEIGHT = height;
        spawnBullet();
        this.batch = batch;
        this.camera = camera;

        bulletImg = new Texture(Gdx.files.internal("bullet.png"));


    }

    public void update(){
        if(TimeUtils.nanoTime() - lastBulletTime > 20000000000L){
            spawnBullet();
        }
        for(Iterator<Rectangle> iter = bullets.iterator(); iter.hasNext();){
            Rectangle bullet = iter.next();
            bullet.y -= 100 * Gdx.graphics.getDeltaTime();
            if(bullet.y<=0){
                iter.remove();
            }



        }
    }

    public ArrayList<Rectangle> getChickens() {
        return bullets;
    }

    public void setChickens(ArrayList<Rectangle> chickens) {
        this.bullets = chickens;
    }

    public void draw(){
        batch.begin();
        for(Rectangle bullet: bullets){
            batch.draw(bulletImg, bullet.x, bullet.y, BULLET_SIZE, BULLET_SIZE);
        }
        batch.end();
    }
    public void spawnBullet(){
        Rectangle newBullet = new Rectangle();
        newBullet.x = MathUtils.random(0, WIDTH-BULLET_SIZE);
        newBullet.y = HEIGHT;
        newBullet.height = BULLET_SIZE;
        newBullet.width = BULLET_SIZE;
        bullets.add(newBullet);
        lastBulletTime = TimeUtils.nanoTime();

    }

    public ArrayList<Rectangle> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Rectangle> bullets) {
        this.bullets = bullets;
    }

    public boolean isOverlap(Rectangle obj){
        for(Rectangle bullet: bullets){
            if(bullet.overlaps(obj)){
                bullets.remove(bullet);
                //removeChickens.add(chick);
                return true;
            }

        }
        return false;
    }





}
