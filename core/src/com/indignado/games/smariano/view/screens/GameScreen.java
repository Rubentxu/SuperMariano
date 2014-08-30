package com.indignado.games.smariano.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.controller.WorldController;
import com.indignado.games.smariano.model.entities.Hero;
import com.indignado.games.smariano.model.entities.Profile;
import com.indignado.games.smariano.model.entities.World;
import com.indignado.games.smariano.model.states.GameState;
import com.indignado.games.smariano.utils.builders.GuiBuilder;
import com.indignado.games.smariano.utils.gui.ScaleUtil;
import com.indignado.games.smariano.view.WorldRenderer;
import com.indignado.games.smariano.view.inputs.GameInputs;

import javax.inject.Inject;


public class GameScreen extends BaseScreen {
    private Profile profile;
    private Table stats;
    private World world;
    private WorldController worldController;
    private WorldRenderer worldRenderer;
    private GuiBuilder guiBuilder;


    @Inject
    public GameScreen(BaseGame game,World world, WorldController worldController, WorldRenderer worldRenderer,
                      GuiBuilder guiBuilder) {
        super(game);
        this.world= world;
        this.worldController= worldController;
        this.worldRenderer= worldRenderer;
        this.guiBuilder=guiBuilder;

    }


    @Override
    public void show(){
        super.show();

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //updates
        if (!gameStateMachine.getCurrentState().equals(GameState.PAUSED) &&
                !gameStateMachine.getCurrentState().equals(GameState.LEVELWIN) &&
                !gameStateMachine.getCurrentState().equals(GameState.GAME_OVER)) {
            worldController.update(delta);
            updateStats();
        }

        stage.act(delta);
        //render
        worldRenderer.render();
        stage.draw();
    }


    private void updateStats() {
        if(profile==null) profile = profileService.getProfile();
        Hero hero = world.getHero();
        ((Label) stats.findActor(Env.SCORE)).setText(String.valueOf(profile.getCoinsAquired()));
        ((Label) stats.findActor(Env.LIVES)).setText(profile.getLivesAsText());
    }





    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        worldRenderer.resize(width, height);

        stats = guiBuilder.buildStats(stage.getWidth(), 100 * ScaleUtil.getSizeRatio());
        stats.setBounds(0, height - height / 7, width, height / 7);
        stage.addActor(stats);

        if (preferencesService.touchPadEnabled) {
            Touchpad touchPad = guiBuilder.buildTouchPad(350 * ScaleUtil.getSizeRatio(), 350 * ScaleUtil.getSizeRatio(), styles, worldController);
            stage.addActor(touchPad);
        } else {
            stage.addActor(guiBuilder.buildPadButtons(370 * ScaleUtil.getSizeRatio(), 190 * ScaleUtil.getSizeRatio(), styles, worldController));
        }
    }


    @Override
    public void hide() {
        super.hide();
        world.clearWorld();
    }


    @Override
    public void pause() {
        super.pause();
    }


    @Override
    public void resume() {
        super.resume();
    }


    @Override
    public void dispose() {
        super.dispose();
        if(world!=null){
            world.dispose();
            world = null;
        }
        if(worldRenderer!=null){
            worldRenderer.dispose();
            worldRenderer = null;
        }
        if(worldController!=null){
            worldController.dispose();
            worldController = null;
        }

    }


    @Override
    public InputProcessor getInputProcessor() {
        GameInputs gameInputs = new GameInputs(worldController, worldRenderer);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(gameInputs);
        return multiplexer;
    }

}
