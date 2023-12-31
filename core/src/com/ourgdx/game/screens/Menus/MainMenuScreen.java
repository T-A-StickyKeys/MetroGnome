package com.ourgdx.game.screens.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ourgdx.game.GameMain;

public class MainMenuScreen implements Screen {
    //instance variables
    private GameMain parent;
    private Stage stage;
    Skin menuSkin;

    public MainMenuScreen(GameMain gameMain) {
        // Set parent to the orchestrator
        parent = gameMain;

        // initialize our stage
        stage = new Stage(new ScreenViewport());

        // load our skin from the asset manger and set it
        parent.assets.loadAll(MainAssets.values());
        parent.assets.manager.finishLoading();
        menuSkin = parent.assets.manager.get(MainAssets.skin.location());
    }

    @Override
    public void show() {
        // set the input to stage, it'll understand mouse clicks implicitly
        Gdx.input.setInputProcessor(stage);

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table(menuSkin);
        table.setFillParent(true);

        // this shows borders of the table
        //table.setDebug(true);

        // this is a background option made available by our skin, not an implicit option of tables
        table.background("window");

        // Tell the stage that the table is ready for it's scene
        stage.addActor(table);

        // create our buttons
        TextButton pong = new TextButton("Pong", menuSkin);
        TextButton test1 = new TextButton("Test Game 1", menuSkin);
        TextButton test2 = new TextButton("Test Game 2", menuSkin);
        TextButton preferences = new TextButton("Preferences", menuSkin);
        TextButton exit = new TextButton("Exit", menuSkin);

        // add the newGame button and fill out the current row
        table.add(pong).fillX().uniformX();
        // then table.row() bumps us down to the next row
        // .pad() simultaneously sets so preferences for us
        table.row().pad(10, 0, 10, 0);

        table.add(test1).fillX().uniformX();
        table.row();
        table.add(test2).fillX().uniformX();
        table.row();

        // repeat
        table.add(preferences).fillX().uniformX();
        // the .pad()ding is carried over
        table.row();
        table.add(exit).fillX().uniformX();




        // create button listeners

        // we create a new ChangeListener, overriding its function to tell it
        // what to actually do when a change occurs
        // then add that listener to exit
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // GDX's exit function
                Gdx.app.exit();
            }
        });

        pong.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // change screen to gameplay
                parent.changeScreen(GameMain.PONG, GameMain.MAIN_MENU);
            }
        });
        test1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // change screen to gameplay
                parent.changeScreen(GameMain.TEST1, GameMain.MAIN_MENU);
            }
        });
        test2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // change screen to gameplay
                parent.changeScreen(GameMain.TEST2, GameMain.MAIN_MENU);
            }
        });

        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // change screen to preferences
                parent.changeScreen(GameMain.PREFERENCES, GameMain.MAIN_MENU);
            }
        });




    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        // dispose of assets when not needed anymore
        stage.dispose();
    }
}

