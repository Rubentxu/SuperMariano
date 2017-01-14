package com.indignado.games.states.options;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
import com.ilargia.games.egdx.base.interfaces.GameState;
import com.ilargia.games.egdx.base.interfaces.managers.ProfileManager;
import com.ilargia.games.egdx.managers.EGAssetsManager;
import com.ilargia.games.egdx.managers.EGPreferencesManager;
import com.indignado.games.SMEngine;
import com.indignado.games.Styles;

public class OptionsState implements GameState {
    private Label volumeValueSound;
    private Label volumeValueMusic;
    private EGPreferencesManager preferencesManager;
    private ProfileManager<Profile> profileManager;
    private Styles styles;
    private Stage stage;
    private Table mainTable;
    private SMEngine engine;
    private EGAssetsManager assetsManager;

    public OptionsState(Styles styles, SMEngine engine) {
        this.styles = styles;
        this.engine = engine;
        this.stage = new Stage();
        this.preferencesManager = engine.getManager(EGPreferencesManager.class);
        this.profileManager = engine.getManager(ProfileManager.class);
        mainTable = new Table();
        mainTable.setFillParent(true);

    }

    @Override
    public void loadResources() {
        assetsManager = engine.getManager(EGAssetsManager.class);
        stage.clear();
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        InputMultiplexer multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
        multiplexer.addProcessor(stage);
        Gdx.app.log("OptionState", "LoadResources");
    }

    @Override
    public void init() {
        Gdx.app.log("OptionState", "Init");
        preferencesManager.load();
        Label label = new Label("Opciones del Juego", styles.skin, "header", Color.ORANGE);
        label.setAlignment(Align.center, Align.center);
        mainTable.setFillParent(true);
        mainTable.defaults().pad(6 * Styles.ScaleUtil.getSizeRatio());
        mainTable.defaults().padLeft(50 * Styles.ScaleUtil.getSizeRatio());
        mainTable.add(label).colspan(3);
        mainTable.row();
        mainTable.setBackground(new SpriteDrawable(new Sprite((Texture) assetsManager.getTexture(Styles.MENU_BACKGROUND))));

        final CheckBox musicCheckbox = new CheckBox(" Music", styles.skin);
        musicCheckbox.align(Align.left);
        musicCheckbox.setChecked(preferencesManager.MUSIC);
        musicCheckbox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean enabled = musicCheckbox.isChecked();
                preferencesManager.MUSIC = enabled;
                assetsManager.playMusic(Styles.MUSIC_MENU);
            }
        });
        mainTable.add(musicCheckbox);

        Slider volumeSliderMusic = new Slider(0f, 1f, 0.1f, false, styles.skin);
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

        volumeValueMusic = new Label(" Volume ", styles.skin);
        updateVolumeLabelMusic();
        mainTable.add(volumeValueMusic);
        mainTable.row();


        final CheckBox soundCheckbox = new CheckBox(" Sound", styles.skin);
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


        Slider volumeSliderSound = new Slider(0f, 1f, 0.1f, false, styles.skin);
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

        volumeValueSound = new Label(" Volume ", styles.skin);
        updateVolumeLabelSound();
        mainTable.add(volumeValueSound);
        mainTable.row();

        final CheckBox touchPadCheckbox = new CheckBox(" TouchPad Control", styles.skin);
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


        TextButton backButton = new TextButton("Volver Menu", styles.skin);
        backButton.pad(20);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                preferencesManager.save();
//                game.setNextScreen(game.menuScreen.get());
//                gameStateMachine.changeState(GameState.SHOW_NEXT_SCREEN);
            }
        });

        mainTable.add(backButton).colspan(3);

        this.stage.addActor(mainTable);
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
    public void dispose() {
        stage.clear();
        stage = null;
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
