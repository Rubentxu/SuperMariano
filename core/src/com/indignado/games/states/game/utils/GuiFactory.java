package com.indignado.games.states.game.utils;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.ilargia.games.egdx.managers.EGAssetsManager;
import javafx.scene.control.Skin;

import static com.indignado.games.Styles.GUI_ATLAS;

public class GuiFactory {
    private EGAssetsManager assetsManager;
    private Skin skin;

    public GuiFactory(EGAssetsManager assetsManager, Skin skin) {
        this.assetsManager = assetsManager;
        this.skin = skin;
    }


    Touchpad createTouchPad(float width, float height);

    Table createPadButtons(float width, float height);

    Table createScore(float width, float height) {
        Table tableProfile = new Table();
        tableProfile.setBounds(0, 0, width, height);


        Image imageLives = new Image(((TextureAtlas) assetsManager.getTextureAtlas(GUI_ATLAS)).findRegion("vidas"));
        imageLives.setName(Env.IMAGE_LIVES);

        Label lives = new Label("0", styles.skin, "default", Color.ORANGE);
        lives.setName(Env.LIVES);

        Label labelScore = new Label("Tijeras: ", styles.skin, "default", Color.ORANGE);
        labelScore.setName(Env.LABEL_SCORE);

        Label score = new Label("0000", styles.skin, "default", Color.ORANGE);
        score.setName(Env.SCORE);
        tableProfile.defaults().height(height);
        tableProfile.defaults().width(width / 4.5f);


        tableProfile.add(imageLives).left().padRight(15).width(imageLives.getPrefWidth() * ScaleUtil.getSizeRatio());
        tableProfile.add(lives).expandY().fill();
        tableProfile.add();

        tableProfile.add(labelScore).right().expandY().fill();
        tableProfile.add(score).right().expandY().fill();
        tableProfile.debug();
        return tableProfile;

    }
}
