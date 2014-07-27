package com.indignado.games.smariano.pantallas.transitions;

import com.badlogic.gdx.math.Interpolation;
import com.indignado.games.borcenas.screens.BaseScreen;
import com.indignado.games.borcenas.screens.MenuScreen;
import com.indignado.games.borcenas.screens.SplashScreen;

/**
 * Created by Rubentxu on 25/06/14.
 */
public class TransitionFactory {

    public static Transition getTransition(BaseScreen screen) {
        if (screen instanceof SplashScreen) return getSlideTransition();
        if (screen instanceof MenuScreen) return getSliceTransition();
        return getFadeTransition();

    }


    private static Transition getSlideTransition() {
        return new SlideTransition(2F, SlideTransition.DOWN, true, Interpolation.swing);

    }


    private static Transition getSliceTransition() {
        return new SliceTransition(2F, SliceTransition.UP_DOWN, 14, Interpolation.linear);

    }


    private static Transition getFadeTransition() {
        return new FadeTransition(3F);

    }
}
