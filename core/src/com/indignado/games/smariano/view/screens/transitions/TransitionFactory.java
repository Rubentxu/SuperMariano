package com.indignado.games.smariano.view.screens.transitions;

import com.badlogic.gdx.math.Interpolation;
import com.indignado.games.smariano.view.screens.BaseScreen;
import com.indignado.games.smariano.view.screens.MenuScreen;
import com.indignado.games.smariano.view.screens.OptionScreen;
import com.indignado.games.smariano.view.screens.SplashScreen;


/**
 * Created by Rubentxu on 25/06/14.
 */
public class TransitionFactory {

    public static Transition getTransition(BaseScreen screen) {
        if (screen instanceof SplashScreen) return getSlideTransition();
        if (screen instanceof MenuScreen) return getSlideTransition();
        if (screen instanceof OptionScreen) return getSlideTransition();
        return getFadeTransition();

    }


    private static Transition getSlideTransition() {
        return new SlideTransition(2F, SlideTransition.DOWN, false, Interpolation.swing);

    }


    private static Transition getSliceTransition() {
        return new SliceTransition(2F, SliceTransition.UP_DOWN, 14, Interpolation.linear);

    }


    private static Transition getFadeTransition() {
        return new FadeTransition(3F);

    }
}
