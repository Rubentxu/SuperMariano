package com.indignado.games.smariano.view.screens;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.model.services.AudioService;
import com.indignado.games.smariano.model.services.PreferencesService;
import com.indignado.games.smariano.model.services.ResourceService;
import com.indignado.games.smariano.utils.gui.ScaleUtil;
import dagger.Lazy;

import javax.inject.Inject;

public class OptionScreen extends BaseScreen {

    private Label volumeValueSound;
    private Label volumeValueMusic;
    @Inject
    PreferencesService preferencesService;
    @Inject
    AudioService audioService;
    @Inject
    public Lazy<MenuScreen> menuScreen;


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        preferencesService.load();
        Label label = new Label("Opciones del Juego", styles.skin, "header", Color.ORANGE);
        label.setAlignment(Align.center, Align.center);
        mainTable.setFillParent(true);
        mainTable.defaults().pad(6 * ScaleUtil.getSizeRatio());
        mainTable.defaults().padLeft(50 * ScaleUtil.getSizeRatio());
        mainTable.add(label).colspan(3);
        mainTable.row();
        mainTable.setBackground(new SpriteDrawable(new Sprite((Texture) resourcesManager.getAssetManager().get(ResourceService.MENU_BACKGROUND))));

        final CheckBox musicCheckbox = new CheckBox(" Music", styles.skin);
        musicCheckbox.align(Align.left);
        musicCheckbox.setChecked(preferencesService.music);
        musicCheckbox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean enabled = musicCheckbox.isChecked();
                preferencesService.music = enabled;
                audioService.playMusic(ResourceService.MUSIC_MENU);
            }
        });
        mainTable.add(musicCheckbox);


        Slider volumeSliderMusic = new Slider(0f, 1f, 0.1f, false, styles.skin);
        volumeSliderMusic.setValue(preferencesService.volMusic);
        volumeSliderMusic.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Slider slider = (Slider) actor;
                float value = slider.getValue();
                preferencesService.volMusic = value;
                updateVolumeLabelMusic();
            }

        });
        mainTable.add(volumeSliderMusic);

        volumeValueMusic = new Label(" Volume ", styles.skin);
        updateVolumeLabelMusic();
        mainTable.add(volumeValueMusic);
        mainTable.row();


        final CheckBox soundCheckbox = new CheckBox(" Sound", styles.skin);
        soundCheckbox.align(Align.left);
        soundCheckbox.setChecked(preferencesService.sound);
        soundCheckbox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean enabled = soundCheckbox.isChecked();
                preferencesService.sound = enabled;

            }
        });
        mainTable.add(soundCheckbox);


        Slider volumeSliderSound = new Slider(0f, 1f, 0.1f, false, styles.skin);
        volumeSliderSound.setValue(preferencesService.volSound);
        volumeSliderSound.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Slider slider = (Slider) actor;
                float value = slider.getValue();
                preferencesService.volSound = value;
                updateVolumeLabelSound();
            }

        });
        mainTable.add(volumeSliderSound);

        volumeValueSound = new Label(" Volume ", styles.skin);
        updateVolumeLabelSound();
        mainTable.add(volumeValueSound);
        mainTable.row();

        final CheckBox touchPadCheckbox = new CheckBox(" TouchPad Control", styles.skin);
        touchPadCheckbox.align(Align.left);
        touchPadCheckbox.setChecked(preferencesService.touchPadEnabled);
        touchPadCheckbox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean enabled = touchPadCheckbox.isChecked();
                preferencesService.touchPadEnabled = enabled;
            }
        });
        mainTable.add(touchPadCheckbox).colspan(3);
        mainTable.row();


        TextButton backButton = new TextButton("Volver Menu", styles.skin);
        backButton.pad(20);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                preferencesService.save();
                game.setNextScreen(menuScreen.get());
                gameStateMachine.changeState(GameState.SHOW_NEXT_SCREEN);
            }
        });

        mainTable.add(backButton).colspan(3);

        this.stage.addActor(mainTable);

    }



    private void updateVolumeLabelMusic() {
        int volume = (int) (preferencesService.volMusic * 100);
        volumeValueMusic.setText("Volume " + Integer.toString(volume));
    }

    private void updateVolumeLabelSound() {
        int volume = (int) (preferencesService.volSound * 100);
        volumeValueSound.setText("Volume " + Integer.toString(volume));
    }

}
