package com.indignado.games.smariano.view.screens.transitions;

import com.badlogic.gdx.math.Interpolation;
import com.indignado.games.smariano.view.screens.*;


/**
 * Created by Rubentxu on 25/06/14.
 */
public class TransitionFactory {

    public static Transition getTransition(BaseScreen screen) {
        if (screen instanceof SplashScreen) return getSlideTransition();
        if (screen instanceof MenuScreen) return getSlideTransition();
        if (screen instanceof OptionScreen) return getSlideTransition();
        if (screen instanceof SelectLevelScreen) return getFadeTransition();
        if (screen instanceof GameOverScreen) return getSlideTransition();
        if (screen instanceof HighScoresScreen) return getFadeTransition();
        if (screen instanceof ScoreScreen) return getSlideTransition();
        if (screen instanceof GameScreen) return getSliceTransition();
        return getFadeTransition();

    }


    private static Transition getSlideTransition() {
        return new SlideTransition(1.2F, SlideTransition.DOWN, false, Interpolation.bounceOut);

    }


    private static Transition getSliceTransition() {
        return new SliceTransition(1.2F, SliceTransition.UP_DOWN, 14, Interpolation.linear);

    }


    private static Transition getFadeTransition() {
        return new FadeTransition(1.5F);

    }
}
