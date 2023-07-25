package com.ourgdx.game.screens.mapEditor;

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
import com.ourgdx.game.text.Printer;
import com.ourgdx.game.text.Printout;

import java.util.LinkedList;

public class mapEditor implements Screen {
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
    //private LinkedList<e> toDrawList;


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





    public mapEditor(GameMain gameMain) {


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

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        processInputs();

        if (!isOver){ physics.update(); }

        // DRAWING
        // tell the batch to do camera magic
        batch.setProjectionMatrix(camera.combined);
        // Begin the process of rendering
        batch.begin();

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

    }



}


