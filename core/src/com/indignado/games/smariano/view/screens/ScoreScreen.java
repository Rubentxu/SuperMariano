package com.indignado.games.smariano.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.entities.Level;
import com.indignado.games.smariano.model.entities.Profile;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.model.services.interfaces.ILevelService;
import com.indignado.games.smariano.model.services.interfaces.IProfileService;
import com.indignado.games.smariano.utils.gui.ScaleUtil;

import javax.inject.Inject;

public class ScoreScreen extends BaseScreen {

    private Profile profile;
    private Level currentLevel;
    private int coins;
    private int kills;
    private int stars;
    @Inject
    IProfileService profileService;
    @Inject
    ILevelService levelService;



    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        profile = profileService.getProfile();
        currentLevel = levelService.getCurrentLevel();
        coins = profile.getCoinsAquired();
        kills = profile.getKills();
        stars = profile.getStarAquired();

        Skin skin = styles.skin;

        mainTable.defaults().spaceBottom(50 * ScaleUtil.getSizeRatio());
        mainTable.setFillParent(true);

        Label killsLabel = new Label("Kills: ", skin);
        killsLabel.setText(killsLabel.getText() + String.valueOf(kills));

        Label coinsLabel = new Label("Gold: ", skin);
        coinsLabel.setText(coinsLabel.getText() + String.valueOf(coins));

        Label starsLabel = new Label("Stars: ", skin);
        starsLabel.setText(starsLabel.getText() + String.valueOf(stars));

        int score = calculateScore();

        Label scoreLabel = new Label("Score: ", skin);
        scoreLabel.setText(scoreLabel.getText() + String.valueOf(score));

        Label highScoreLabel = new Label("HighScoreLabel: ", skin);
        highScoreLabel.setText(highScoreLabel.getText() + String.valueOf(currentLevel.getHighScore()));


        TextButton levelMenuButton = new TextButton("LevelMenu", skin);
        levelMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameStateMachine.changeState(GameState.SHOW_NEXT_SCREEN);
            }
        });

        mainTable.add(killsLabel);
        mainTable.row();
        mainTable.add(coinsLabel);
        mainTable.row();
        mainTable.add(starsLabel);
        mainTable.row();
        mainTable.add(starsLabel);
        mainTable.row();
        mainTable.add(levelMenuButton);
        stage.addActor(mainTable);
        if (profile.getLives() > 0) profile.getLevels().get(currentLevel.getNum() + 1).setActive(true);
        profile.resetValues();
        if (score > currentLevel.getHighScore()) currentLevel.setHighScore(score);
        if (currentLevel.getAchievements() < profile.getStarAquired())
            currentLevel.setAchievements(profile.getStarAquired());
        Gdx.app.log(Env.LOG, "ScoreScreen, score " + score + " vidas: " + profile.getLives() + " coins: " + profile.getCoinsAquired());
        profileService.persist();

    }

    private int calculateScore() {
        int score = 0;
        score = coins * 10;
        score += kills * 25;
        score += stars * 50;
        return score;
    }


}