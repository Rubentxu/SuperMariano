package com.indignado.games.states.options;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.indignado.games.states.game.component.scene.Level;

import java.util.List;

public class Profile implements Serializable {

    private int lives;
    private int kills;
    private int coinsAquired;
    private int starAquired;
    private List<Level> levels;

    public Profile(List<Level> levels) {
        lives = 2;
        kills = 0;
        coinsAquired = 0;
        starAquired = 0;
        this.levels = levels;
    }

    public int getHighScore(int levelId) {
        return levels.get(levelId).getHighScore();
    }

    public boolean notifyScore(int levelId, int score) {
        if (score > getHighScore(levelId)) {
            levels.get(levelId).setHighScore(score);
            return true;
        }
        return false;
    }

    public int getLives() {
        return lives;
    }

    public void addLives(int lives) {
        this.lives += lives;
    }

    public boolean removeLive() {
        --lives;
        if (lives < 0) lives = 0;
        return lives == 0;
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        levels = json.readValue("levels", List.class, jsonData);
        lives = json.readValue("lives", Integer.class, jsonData);
    }

    @Override
    public void write(Json json) {
        json.writeValue("levels", levels);
        json.writeValue("lives", lives);
    }

    public void resetValues() {
        if (lives <= 0) lives = 1;
        coinsAquired = 0;
        kills = 0;
        starAquired = 0;
    }

    public CharSequence getLivesAsText() {
        return String.valueOf(lives);
    }

    public List<Level> getLevels() {
        return levels;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getCoinsAquired() {
        return coinsAquired;
    }

    public void setCoinsAquired(int coinsAquired) {
        this.coinsAquired = coinsAquired;
    }

    public void addCoinsAquired(int goldAquired) {
        this.coinsAquired += goldAquired;
    }

    public int getStarAquired() {
        return starAquired;
    }

    public void setStarAquired(int starAquired) {
        this.starAquired = starAquired;
    }

    public void addStarAquired(int starAquired) {
        this.starAquired += starAquired;
    }
}