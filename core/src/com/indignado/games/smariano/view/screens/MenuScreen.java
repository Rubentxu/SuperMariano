package com.indignado.games.smariano.view.screens;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.model.services.ResourceService;
import com.indignado.games.smariano.utils.gui.ScaleUtil;

import javax.inject.Inject;

public class MenuScreen extends BaseScreen {

    @Inject
    public MenuScreen(BaseGame game) {
        super(game);
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        int pad = (int) (20 * ScaleUtil.getSizeRatio());
        int pad2 = (int) (60 * ScaleUtil.getSizeRatio());
        final TextButton btnStart = new TextButton("Comenzar", styles.skin);
        btnStart.pad(pad, pad2, pad, pad2);
        final TextButton btnOptions = new TextButton("Opciones", styles.skin);
        btnOptions.pad(pad, pad2, pad, pad2);
        final TextButton btnScores = new TextButton("Puntuaciones", styles.skin);
        btnScores.pad(pad, pad2, pad, pad2);
        final TextButton button3 = new TextButton("Creditos", styles.skin);
        button3.pad(pad, pad2, pad, pad2);
        button3.setChecked(false);

        btnStart.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Click Comenzar...");
                game.setNextScreen(game.selectLevelScreen.get());
                gameStateMachine.changeState(GameState.SHOW_NEXT_SCREEN);
            }
        });

        btnOptions.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Click optionScreen...");
                game.setNextScreen(game.optionScreen.get());
                gameStateMachine.changeState(GameState.SHOW_NEXT_SCREEN);
            }
        });

        btnScores.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Click highScoreScreen...");
                game.setNextScreen(game.highScoresScreen.get());
                gameStateMachine.changeState(GameState.SHOW_NEXT_SCREEN);
            }
        });

        Label label = new Label("SUPER MARIANO", styles.skin, "header", Color.CYAN);
        label.setAlignment(Align.center, Align.center);
        mainTable.defaults().padBottom(pad);
        if (height < 480) mainTable.defaults().height(height / 5f - pad);
        mainTable.add(label);
        mainTable.row();
        mainTable.add(btnStart);
        mainTable.row();
        mainTable.add(btnOptions);
        mainTable.row();
        mainTable.add(btnScores);
        mainTable.row();
        mainTable.add(button3);
        mainTable.row();
        mainTable.setBackground(new SpriteDrawable(new Sprite((Texture) resourcesService.getAssetManager().get(ResourceService.MENU_BACKGROUND))));
        mainTable.row();
        this.stage.addActor(mainTable);

    }


}
