package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;


public class Plane extends Item {
    private Batch batch;
    private Camera camera;
    private Rectangle planeRec;
    private Texture planeImg;

    private int PLANE_SIZE;



    public Plane(int x, int y, int height, int width, int velocityX, int velocityY, Batch batch, Camera camera, int plane_size) {
        super(x, y, height, width, velocityX, velocityY);
        this.batch = batch;
        this.camera = camera;
        this.PLANE_SIZE = plane_size;

        planeImg = new Texture("plane.png");

        planeRec = new Rectangle(x, y, width, height);

    }

    @Override
    public void draw() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(planeImg, getX(), getY(), PLANE_SIZE, PLANE_SIZE);
        batch.end();
        camera.update();
    }

    public Rectangle getPlaneRec() {
        return planeRec;
    }

    public void setPlaneRec(Rectangle planeRec) {
        this.planeRec = planeRec;
    }

    @Override
    public void update() {
            planeRec.x = getX();
            planeRec.y = getY();
    }

    public void setXPlane(int x){
        planeRec.x = x;
        setX(x);
    }

    public void setYPlane(int y){
        planeRec.y = y;
        setY(y);
    }



}
