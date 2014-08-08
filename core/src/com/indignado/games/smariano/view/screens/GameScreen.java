package com.indignado.games.smariano.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.controller.WorldController;
import com.indignado.games.smariano.model.entities.Hero;
import com.indignado.games.smariano.model.entities.Profile;
import com.indignado.games.smariano.model.entities.World;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.model.services.PreferencesService;
import com.indignado.games.smariano.model.services.interfaces.IProfileService;
import com.indignado.games.smariano.model.services.interfaces.IResourcesService;
import com.indignado.games.smariano.utils.builders.GuiBuilder;
import com.indignado.games.smariano.utils.gui.ScaleUtil;
import com.indignado.games.smariano.view.WorldRenderer;
import com.indignado.games.smariano.view.inputs.GameInputs;

import javax.inject.Inject;


public class GameScreen extends BaseScreen {
    private Profile profile;
    private Table stats;
    @Inject
    protected IProfileService profileService;
    @Inject
    protected IResourcesService resourceService;
    @Inject
    protected PreferencesService preferencesService;
    @Inject
    protected World world;
    @Inject
    protected WorldController worldController;
    @Inject
    protected WorldRenderer worldRenderer;
    @Inject
    protected GuiBuilder guiBuilder;



    @Override
    public void render(float delta) {
        super.render(delta);

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
    public void showDialog() {
        if (dialog == null) {
            dialog = new Window("Que desea hacer ?", styles.skin);

            TextButton btnSalir = new TextButton("Salir", styles.skin);
            TextButton btnContinuar = new TextButton("Continuar", styles.skin);
            btnSalir.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("Click Salir...");
                    gameStateMachine.changeState(GameState.SHOW_NEXT_SCREEN);

                }
            });
            btnContinuar.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("Click Continuar...");
                    gameStateMachine.changeState(GameState.RUNNING);
                    dialog.remove();
                    dialog = null;
                }
            });

            dialog.defaults().spaceBottom(10);
            dialog.row().fill().expandX();
            dialog.add(btnContinuar);
            dialog.add(btnSalir);
            dialog.pack();
            dialog.setPosition(Gdx.graphics.getWidth() / 2 - dialog.getWidth() / 2, Gdx.graphics.getHeight() / 2 - dialog.getHeight() / 2);
            stage.addActor(dialog);
        }
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
        dispose();
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


    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public WorldRenderer getRenderer() {
        return worldRenderer;
    }

    public void setRenderer(WorldRenderer renderer) {
        this.worldRenderer = renderer;
    }

    public WorldController getController() {
        return worldController;
    }

    public void setController(WorldController controller) {
        this.worldController = controller;
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
