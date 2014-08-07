package com.indignado.games.smariano.view.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.model.services.ResourceService;
import dagger.Lazy;

import javax.inject.Inject;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SplashScreen extends BaseScreen {
    private Texture splashTexture;
    @Inject
    Lazy<MenuScreen> menuScreen;

    @Override
    public void show() {
        super.show();
        splashTexture = resourcesManager.getAssetManager().get(ResourceService.SPLASH);
        splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        mainTable.getColor().a = 0f;
        mainTable.setBackground(new SpriteDrawable(new Sprite(splashTexture)));
        mainTable.addAction(sequence(fadeIn(0.5f),  delay(1f,  run(new Runnable() {
            public void run() {
                game.setNextScreen(menuScreen.get());
                gameStateMachine.changeState(GameState.SHOW_NEXT_SCREEN);
            }
        }))));

        stage.addActor(mainTable);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void showDialog() {

    }

}
