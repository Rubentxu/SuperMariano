package com.indignado.games.states.options;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.ilargia.games.egdx.api.GameState;
import com.ilargia.games.egdx.base.commands.ChangeStateCommand;
import com.ilargia.games.egdx.api.managers.ProfileManager;
import com.ilargia.games.egdx.base.managers.BaseAssetsManager;
import com.ilargia.games.egdx.base.managers.BasePreferencesManager;
import com.indignado.games.SMEngine;
import com.indignado.games.SMGame;
import com.indignado.games.manager.SMGUIManager;

public class OptionsState implements GameState {
    private Label volumeValueSound;
    private Label volumeValueMusic;
    private BasePreferencesManager preferencesManager;
    private ProfileManager<Profile> profileManager;
    private Skin skin;
    private Stage stage;
    private Table mainTable;
    private SMEngine engine;
    private BaseAssetsManager assetsManager;

    public OptionsState(SMEngine engine) {
        this.skin = engine.getManager(SMGUIManager.class).skin;
        this.engine = engine;
        this.preferencesManager = engine.getManager(BasePreferencesManager.class);
        this.profileManager = engine.getManager(ProfileManager.class);

    }

    @Override
    public void loadResources() {
        assetsManager = engine.getManager(BaseAssetsManager.class);
        this.stage = new Stage();
        stage.clear();
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        mainTable = new Table();
        mainTable.setFillParent(true);
        Gdx.input.setInputProcessor(stage);
        Gdx.app.log("OptionState", "LoadResources");
    }

    @Override
    public void init() {
        Gdx.app.log("OptionState", "Init");
        preferencesManager.load();
        Label label = new Label("Opciones del Juego", skin, "header", Color.ORANGE);
        label.setAlignment(Align.center, Align.center);
        mainTable.setFillParent(true);
        mainTable.defaults().pad(6 * SMGUIManager.ScaleUtil.getSizeRatio());
        mainTable.defaults().padLeft(50 * SMGUIManager.ScaleUtil.getSizeRatio());
        mainTable.add(label).colspan(3);
        mainTable.row();
        mainTable.setBackground(new SpriteDrawable(new Sprite((Texture) assetsManager.getTexture(SMGUIManager.MENU_BACKGROUND))));

        final CheckBox musicCheckbox = new CheckBox(" Music", skin);
        musicCheckbox.align(Align.left);
        musicCheckbox.setChecked(preferencesManager.MUSIC);
        musicCheckbox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean enabled = musicCheckbox.isChecked();
                preferencesManager.MUSIC = enabled;
                assetsManager.playMusic(SMGUIManager.MUSIC_MENU);
            }
        });
        mainTable.add(musicCheckbox);

        Slider volumeSliderMusic = new Slider(0f, 1f, 0.1f, false, skin);
        volumeSliderMusic.setValue(preferencesManager.VOL_MUSIC);
        volumeSliderMusic.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Slider slider = (Slider) actor;
                float value = slider.getValue();
                preferencesManager.VOL_MUSIC = value;
                updateVolumeLabelMusic();
            }

        });
        mainTable.add(volumeSliderMusic);

        volumeValueMusic = new Label(" Volume ", skin);
        updateVolumeLabelMusic();
        mainTable.add(volumeValueMusic);
        mainTable.row();


        final CheckBox soundCheckbox = new CheckBox(" Sound", skin);
        soundCheckbox.align(Align.left);
        soundCheckbox.setChecked(preferencesManager.SOUND);
        soundCheckbox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean enabled = soundCheckbox.isChecked();
                preferencesManager.SOUND = enabled;

            }
        });
        mainTable.add(soundCheckbox);


        Slider volumeSliderSound = new Slider(0f, 1f, 0.1f, false, skin);
        volumeSliderSound.setValue(preferencesManager.VOL_SOUND);
        volumeSliderSound.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Slider slider = (Slider) actor;
                float value = slider.getValue();
                preferencesManager.VOL_SOUND = value;
                updateVolumeLabelSound();
            }

        });
        mainTable.add(volumeSliderSound);

        volumeValueSound = new Label(" Volume ", skin);
        updateVolumeLabelSound();
        mainTable.add(volumeValueSound);
        mainTable.row();

        final CheckBox touchPadCheckbox = new CheckBox(" TouchPad Control", skin);
        touchPadCheckbox.align(Align.left);
        touchPadCheckbox.setChecked(preferencesManager.TOUCH_PAD_ENABLED);
        touchPadCheckbox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean enabled = touchPadCheckbox.isChecked();
                preferencesManager.TOUCH_PAD_ENABLED = enabled;
            }
        });
        mainTable.add(touchPadCheckbox).colspan(3);
        mainTable.row();


        TextButton backButton = new TextButton("Volver Menu", skin);
        backButton.pad(20);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                preferencesManager.save();
                SMGame.ebus.post((ChangeStateCommand<SMGame>) (nameState, game) -> {
                    game.changeState(game.getMenuState(), game.getFadeTransition());
                });
            }
        });

        mainTable.add(backButton).colspan(3);

        this.stage.addActor(mainTable);
    }

    @Override
    public void onResume() {

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

    private void updateVolumeLabelMusic() {
        int volume = (int) (preferencesManager.VOL_MUSIC * 100);
        volumeValueMusic.setText("Volume " + Integer.toString(volume));
    }

    private void updateVolumeLabelSound() {
        int volume = (int) (preferencesManager.VOL_SOUND * 100);
        volumeValueSound.setText("Volume " + Integer.toString(volume));
    }
}
