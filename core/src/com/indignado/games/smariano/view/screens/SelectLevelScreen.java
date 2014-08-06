package com.indignado.games.smariano.view.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.model.services.LevelService;
import com.indignado.games.smariano.model.services.ProfileService;
import com.indignado.games.smariano.model.services.ResourceService;
import com.indignado.games.smariano.model.entities.Level;
import com.indignado.games.smariano.utils.gui.ScaleUtil;
import com.indignado.games.smariano.utils.gui.mtx.ButtonLevel;

import javax.inject.Inject;
import java.util.List;

public class SelectLevelScreen extends BaseScreen {

    @Inject
    ProfileService profileService;
    @Inject
    LevelService levelService;

    private Label label(String text, Color color) {
        Label label = new Label(text, styles.skin, "header", color);
        label.setAlignment(Align.center, Align.center);
        return label;
    }

    @Override
    public void resize(int width, int height) {
        Skin skin = styles.skin;
        profileService.getProfile().resetValues();
        super.resize(width, height);
        final List<Level> levels = levelService.getLevels();
        int size = (int) (350 * ScaleUtil.getSizeRatio());
        int pad = (int) (30 * ScaleUtil.getSizeRatio());
        for (int i = 0; i < levels.size(); i++) {

            final ButtonLevel levelButton = new ButtonLevel();
            if (!levels.get(i).isActive()) {
                levelButton.setTextureLocked(skin.getRegion("gui_candado"), true);
            }

            levelButton.setLevelNumber(levels.get(i).getNum(), styles.font2);

            levelButton.setLevelStars(((TextureAtlas) resourcesManager.getAssetManager().get(ResourceService.GUI_ATLAS)).findRegion("estrellaZocalo"),
                    skin.getRegion("gui_estrella"), 5, levels.get(i).getAchievements(), size);

            levelButton.addListener(new ActorGestureListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    super.touchUp(event, x, y, pointer, button);
                    Gdx.app.log(Env.LOG, "Numero de boton presionado: " + levelButton.getLevelNumber());
                    levelService.setCurrentLevel(levels.get(levelButton.getLevelNumber() - 1));
                    gameStateMachine.changeState(GameState.SHOW_NEXT_SCREEN);
                }
            });

            if (i % 5 == 0) {
                mainTable.row();
            }

            mainTable.add(levelButton).size(size, size).pad(pad).expand();
        }

        mainTable.setFillParent(true);
        mainTable.row();
        mainTable.setBackground(skin.getDrawable("debug"));

        this.stage.addActor(mainTable);

    }



}
