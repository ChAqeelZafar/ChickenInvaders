package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector3;

import sprites.Bullets;
import sprites.Chickens;
import sprites.Fire;
import sprites.Plane;



public class GameController {
    private SpriteBatch batch;
    private OrthographicCamera camera;


    private boolean gameoverFlag=false;

    private Plane plane;
    private Chickens chickens;
    private Fire fires;
    private Bullets bullets;

    private int WIDTH;
    private int HEIGHT;
    private int PLANE_SIZE = 150;
    private int CHICKEN_SIZE = 70;
    private int BULLET_SIZE = 50;
    private int FIRE_SIZE = 10;

    private long score;
    BitmapFont scoreFont;



    private int bullet;




    public GameController(int width, int height){



        this.WIDTH = width;
        this.HEIGHT = height;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        batch = new SpriteBatch();


        scoreFont = new BitmapFont();



        scoreFont.getData().setScale(2);

        score =0;

        bullet = 20;


        chickens = new Chickens(CHICKEN_SIZE, PLANE_SIZE, WIDTH, HEIGHT,batch, camera);
        plane = new Plane(WIDTH/2, 20, PLANE_SIZE, PLANE_SIZE, 0,0,batch, camera, PLANE_SIZE);
        fires = new Fire(FIRE_SIZE, PLANE_SIZE, WIDTH,HEIGHT, batch, camera);
        bullets = new Bullets(BULLET_SIZE, PLANE_SIZE, WIDTH, HEIGHT,batch, camera);




    }



    public void draw(){
        if(gameoverFlag==false) {
            drawBackground();
            chickens.draw();
            plane.draw();
            fires.draw();
            bullets.draw();
            showScore();
        }else{
            drawBackground();
            showGameOver();

        }


    }


    public void showScore(){
        batch.begin();

        scoreFont.getData().setScale(2);
        scoreFont.draw(batch, "SCORE :" + score, 10, HEIGHT-10);
        scoreFont.draw(batch, "BULLET :" + bullet, WIDTH-170, HEIGHT-10);
        //scoreFont.draw(batch, "PLANE S:" + plane.getX() + " M:" + plane.getX()+PLANE_SIZE/2 + " E:" + plane.getX()+PLANE_SIZE, 0, HEIGHT-60);
        batch.end();
    }

    public void update(){
        if(gameoverFlag==false) {
            chickens.update();
            fires.update();
            bullets.update();
            plane.update();
            score += fires.removeIfOverlap(chickens);

            isGameOver();
        }

    }

    public void drawBackground(){
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
    }

    public void onFire(){
        if(bullet>0) {
            fires.spawnFire(plane.getX() + PLANE_SIZE/2);
            bullet--;
        }
    }

    public void onTouched(){
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(),0);
        camera.unproject(touchPos);
        plane.setXPlane((int) (touchPos.x - 64/2));
        bound();
    }
    public void moveRight(){
        int temp = plane.getX();
        temp += 300 * Gdx.graphics.getDeltaTime();
        plane.setXPlane(temp);
        bound();

    }



    public void moveLeft(){
        int temp = plane.getX();
        temp -= 300 * Gdx.graphics.getDeltaTime();
        plane.setXPlane(temp);
        bound();
    }

    public void bound(){
        if(plane.getX() < 0)
            plane.setX(0);
        if(plane.getX() > WIDTH - PLANE_SIZE)
            plane.setX(WIDTH - PLANE_SIZE);
    }

    public void isGameOver(){
        if(bullets.isOverlap(plane.getPlaneRec())){
            bullet += 20;

        }
        if(chickens.isOverlap(plane.getPlaneRec())){
            gameoverFlag = true;
        }


    }

    public void showGameOver(){
        batch.begin();
        scoreFont.getData().setScale(3);
        scoreFont.draw(batch, "GAME OVER!\nPress any key to continue...", WIDTH/2-200, HEIGHT/2 + 25);
        batch.end();
    }

    public void startNewGame(){
        setGameoverFlag(false);

    }



    public void setGameoverFlag(boolean flag){
        if(gameoverFlag==true) {
            gameoverFlag = false;
            bullet = 20;
            score = 0;
            chickens.restart();
        }
    }


}
