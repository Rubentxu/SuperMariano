package com.indignado.games.states.score;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.ilargia.games.egdx.api.GameState;
import com.ilargia.games.egdx.api.base.commands.ChangeStateCommand;
import com.ilargia.games.egdx.api.managers.ProfileManager;
import com.ilargia.games.egdx.managers.EGAssetsManager;
import com.ilargia.games.egdx.managers.EGProfileManager;
import com.indignado.games.SMEngine;
import com.indignado.games.SMGame;
import com.indignado.games.SkinManager;
import com.indignado.games.states.game.component.scene.Level;
import com.indignado.games.states.options.Profile;

public class ScoresState implements GameState {
    private ProfileManager<Profile> profileManager;
    private Skin skin;
    private Stage stage;
    private Table mainTable;
    private SMEngine engine;
    private EGAssetsManager assetsManager;

    private Profile profile;
    private Level currentLevel;
    private int coins;
    private int kills;
    private int stars;

    public ScoresState(SMEngine engine) {
        this.skin = engine.getManager(SkinManager.class).skin;
        this.engine = engine;
        this.profileManager = engine.getManager(EGProfileManager.class);
        this.assetsManager = engine.getManager(EGAssetsManager.class);
    }

    @Override
    public void loadResources() {
        this.stage = new Stage();
        stage.clear();
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        mainTable = new Table();
        mainTable.setFillParent(true);
        profile = profileManager.getProfile();
        Gdx.input.setInputProcessor(stage);
        Gdx.app.log("ScoresState", "LoadResources");
    }

    @Override
    public void init() {
        Gdx.app.log("ScoresState", "Init");
        currentLevel = new Level();//levelService.getCurrentLevel();
        coins = profile.getCoinsAquired();
        kills = profile.getKills();
        stars = profile.getStarAquired();

        mainTable.defaults().spaceBottom(50 * SkinManager.ScaleUtil.getSizeRatio());
        mainTable.setFillParent(true);

        Label killsLabel = new Label("Kills: ", skin);
        killsLabel.setText(killsLabel.getText() + String.valueOf(kills));

        Label coinsLabel = new Label("Gold: ", skin);
        coinsLabel.setText(coinsLabel.getText() + String.valueOf(coins));

        Label starsLabel = new Label("Stars: ", skin);
        starsLabel.setText(starsLabel.getText() + String.valueOf(stars));

        int score = calculateScore();

        Label scoreLabel = new Label("Label: ", skin);
        scoreLabel.setText(scoreLabel.getText() + String.valueOf(score));

        Label highScoreLabel = new Label("HighScoreLabel: ", skin);
        highScoreLabel.setText(highScoreLabel.getText() + String.valueOf(currentLevel.getHighScore()));


        TextButton levelMenuButton = new TextButton("LevelMenu", skin);
        levelMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SMGame.ebus.post((ChangeStateCommand<SMGame>) (nameState, game) -> {
                    game.changeState(game.getMenuState(), game.getFadeTransition());
                });
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
        if (profile.getLives() > 0 && profile.getLevels().size() > 0)
            profile.getLevels().get(currentLevel.getNum() + 1).setActive(true);
        profile.resetValues();
        if (score > currentLevel.getHighScore()) currentLevel.setHighScore(score);
        if (currentLevel.getAchievements() < profile.getStarAquired())
            currentLevel.setAchievements(profile.getStarAquired());

        profileManager.persist(profileManager.getProfile());
    }


    @Override
    public void update(float deltaTime) {
        Gdx.gl.glClearColor(0.1f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(deltaTime);

    }

    @Override
    public void render() {
        stage.draw();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void unloadResources() {
        stage.clear();
        mainTable.clear();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage = null;
        mainTable = null;
    }

    private int calculateScore() {
        int score = 0;
        score = coins * 10;
        score += kills * 25;
        score += stars * 50;
        return score;
    }
}
