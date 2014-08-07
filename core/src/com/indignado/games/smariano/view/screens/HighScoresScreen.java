package com.indignado.games.smariano.view.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.indignado.games.smariano.model.entities.Profile;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.model.services.ProfileService;
import com.indignado.games.smariano.utils.gui.ScaleUtil;
import dagger.Lazy;

import javax.inject.Inject;

public class HighScoresScreen extends BaseScreen {

    @Inject
    ProfileService profileService;
    @Inject
    public Lazy<MenuScreen> menuScreen;


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        Profile profile = profileService.getProfile();

        mainTable.setFillParent(true);

        mainTable.defaults().spaceBottom(50 * ScaleUtil.getSizeRatio());
        mainTable.add(label("Puntuaciones: ", Color.ORANGE)).colspan(2);

        String level1Highscore = String.valueOf(profile.getHighScore(0));
        Label episode1HighScore = new Label(level1Highscore, styles.skin);
        mainTable.row();
        mainTable.add(label("Nivel 1", Color.CYAN));
        mainTable.add(episode1HighScore);

        String level2Highscore = String.valueOf(profile.getHighScore(1));
        Label episode2HighScore = new Label(level2Highscore, styles.skin);
        mainTable.row();
        mainTable.add(label("Nivel 2", Color.CYAN)).center();
        mainTable.add(episode2HighScore);

        String level3Highscore = String.valueOf(profile.getHighScore(2));
        Label episode3HighScore = new Label(level3Highscore, styles.skin);
        mainTable.row();
        mainTable.add(label("Nivel 3", Color.CYAN));
        mainTable.add(episode3HighScore);

        TextButton backButton = new TextButton("Volver Menu", styles.skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setNextScreen(menuScreen.get());
                gameStateMachine.changeState(GameState.SHOW_NEXT_SCREEN);
            }
        });

        int pad = (int) (20 * ScaleUtil.getSizeRatio());
        int pad2 = (int) (60 * ScaleUtil.getSizeRatio());
        backButton.pad(pad, pad2, pad, pad2);
        mainTable.row();
        mainTable.add(backButton).colspan(2);

        this.stage.addActor(mainTable);

    }



    private Label label(String text, Color color) {
        Label label = new Label(text, styles.skin, "header", color);
        label.setAlignment(Align.center, Align.center);
        return label;
    }
}