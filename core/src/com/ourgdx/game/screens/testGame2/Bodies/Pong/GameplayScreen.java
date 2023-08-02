package com.ourgdx.game.screens.testGame2.Bodies.Pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ourgdx.game.GameMain;
import com.ourgdx.game.screens.Menus.MainAssets;
import com.ourgdx.game.screens.testGame2.Bodies.Pong.Bodies.Ball;
import com.ourgdx.game.screens.testGame2.Bodies.Pong.Bodies.Paddle;
import com.ourgdx.game.screens.testGame2.Bodies.Pong.Bodies.PongBody;
import com.ourgdx.game.text.Printer;
import com.ourgdx.game.text.Printout;

import java.util.LinkedList;

public class GameplayScreen implements Screen {
    // parent
    private GameMain parent;


    // Constants
    final static int POINTS_TO_WIN = 5;

    // Game State variables
    boolean isOver;



    // ENGINE VARIABLES
    private PhysicsEngine physics;
    private SimpleKeyboardController controller;
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private SpriteBatch batch;
    private LinkedList<PongBody> toDrawList;
    //Bodies
    private Ball ball;
    private Paddle leftPaddle, rightPaddle;
    private Player leftPlayer, rightPlayer;

    // preferences
    private float soundFxVol, musicVol;

    // ASSETS
    //music
    private Music bgMusic;
    // sound effects
    private Sound hitPaddle;
    private Sound hitWall;
    private Sound serve;
    private Sound score;
    // images
    private Texture paddleTexture;
    private Texture ballTexture;
    // font
    private BitmapFont font;
    private Printer printer;





    public GameplayScreen(GameMain gameMain) {
        // The games hasn't even started
        isOver = false;

        // set parent
        parent = gameMain;
        // set up the camera
        camera = new OrthographicCamera();
        // Stretch veiwport will maintain our size ratio regarless of how the window has changes
        viewport = new StretchViewport(gameMain.SCREEN_WIDTH, gameMain.SCREEN_HEIGHT, camera);
        viewport.apply();
        // Set camera to the center
        camera.position.set(gameMain.SCREEN_WIDTH / 2, gameMain.SCREEN_HEIGHT / 2, 0);
        camera.update();

        //Create our batch and controller
        batch = new SpriteBatch();
        controller = new SimpleKeyboardController();

        // load the assets
        parent.assets.loadAll(PongAssets.values());
        parent.assets.manager.finishLoading();
        // assign the assets to their variables
        bgMusic = parent.assets.manager.get(PongAssets.bgMusic.location());
        hitPaddle = parent.assets.manager.get(PongAssets.hitPaddle.location());
        hitWall = parent.assets.manager.get(PongAssets.hitWall.location());
        serve = parent.assets.manager.get(PongAssets.score.location());
        score = parent.assets.manager.get(PongAssets.score.location());
        paddleTexture = parent.assets.manager.get(PongAssets.paddle.location());
        ballTexture = parent.assets.manager.get(PongAssets.ball.location());
        font = parent.assets.manager.get(MainAssets.text.location());

        // Create the text printer and give it the font
        printer = new Printer(font, batch);

        // Create the players, give them a name and the xCoordinate it will be displayed at.
        leftPlayer = new Player("Player 1", 100);
        rightPlayer = new Player("Player 2", gameMain.SCREEN_WIDTH / 2 + 100);

        // Add our players score info to the printer
        printer.addPrintout(leftPlayer.getScoreText());
        printer.addPrintout(rightPlayer.getScoreText());

        // Create the our bodies for playing the game
        ball = new Ball(ballTexture, batch);
        leftPaddle = new Paddle(paddleTexture, batch, 50);
        rightPaddle = new Paddle(paddleTexture, batch, gameMain.SCREEN_WIDTH - 50 - paddleTexture.getWidth());

        // Set up our list of things to draw and add all bodies to it
        toDrawList = new LinkedList<>();
        toDrawList.add(ball);
        toDrawList.add(leftPaddle);
        toDrawList.add(rightPaddle);

        // Dont forget our physics engine
        physics = new PhysicsEngine(this, ball, leftPaddle, rightPaddle);

    }

    //Called when the screen is switch to this
    @Override
    public void show() {
        // makes sure the controller is ready for new inputs
        controller.reset();
        // tell the game to take input data via our controller
        Gdx.input.setInputProcessor(controller);
        //  update the preferences
        parent.updateActivePreferences();
        // set the volume to our new preference
        bgMusic.setVolume(musicVol);
        bgMusic.play();
    }


    @Override
    public void render(float delta) {
        // set the background to black, the clear the screen of everything that's been drawn
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // if the ball ain't moving serve it
        if (!ball.isInplay()){
           ball.serveRandom();
           playServe();
        }
        // Hey keyboard, did the player want to do anything?
        processInputs();
        // if the game isn't over, check the update the physics
        if (!isOver){ physics.update(); }

        // DRAWING
        // tell the batch to do camera magic
        batch.setProjectionMatrix(camera.combined);
        // Begin the process of rendering
        batch.begin();
        // Tell our printer to print
        printer.draw();
        // Cycle through the list the draw everything
        for (PongBody p : toDrawList) {
            p.draw();
        }
        // End, and actually draw everything to the screen
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    // called when the screen switches
    @Override
    public void hide() {
        bgMusic.pause();
    }

    @Override
    public void dispose() {
        bgMusic.stop();
        // unload our assets
        parent.assets.manager.clear();
        parent.assets.manager.dispose();
        // dispose of our batch
        batch.dispose();
    }

    private void processInputs() {
        // Pause menu on Escape
        if (controller.escape) {
            parent.changeScreen(parent.PAUSE, parent.TEST2);
        }

        // Ask the controller if the left paddle has been told to do anything
        if (controller.leftUp) { leftPaddle.moveUp(); }
        else if (controller.leftDown) { leftPaddle.moveDown(); }
        else { leftPaddle.stayPut(); }
        // same for the right
        if (controller.rightUp) { rightPaddle.moveUp(); }
        else if (controller.rightDown) { rightPaddle.moveDown(); }
        else { rightPaddle.stayPut(); }

    }

    public void updatePreferences(float musicVol, boolean musicEnabled, float soundVol, boolean soundEnabled) {
        // if the music or sound is enables, set the volume,
        // otherwise, set it to 0.
        if (musicEnabled) { this.musicVol = musicVol; }
        else { this.musicVol = 0; }
        if (soundEnabled) { this.soundFxVol = soundVol; }
        else { soundFxVol = 0; }
    }

    // PLAY THE SOUND EFFECTS with the current volume
    public void playHitWall() {
        hitWall.play(soundFxVol);
    }
    public void playHitPaddle() {
        hitPaddle.play(soundFxVol);
    }
    public void playServe() {
        serve.play(soundFxVol);
    }
    public void playScore() {
        score.play(soundFxVol);
    }

    public void pointScored(float x) {
        // reset the ball
        ball.resetPosition();
        // if the ball scored on the left paddle
        if (x <= 0) {
            // give rightPlayer a point
            rightPlayer.scoredPoint();
            //Check for victory
            if (rightPlayer.getScore() == POINTS_TO_WIN){
                victory(rightPlayer);
            }
            // Serve the ball to the left
            ball.serverLeft();
            // play the sound
            playServe();
        }
        // otherwise it scored on rightPlayer
        else {
            leftPlayer.scoredPoint();
            if (leftPlayer.getScore() == POINTS_TO_WIN){
                victory(leftPlayer);
            }
            ball.serveRight();
            playServe();
        }

    }

    private void victory(Player p){
        // Create a new printout for the victory text
        printer.addPrintout(new Printout(p.getPlayerName() + " Wins!",
                GameMain.SCREEN_WIDTH/2, GameMain.SCREEN_HEIGHT/2));
        // remove the ball from the drawing list
        toDrawList.remove(ball);
        // its over.
        isOver = true;
    }


}


