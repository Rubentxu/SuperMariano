package com.indignado.games.smariano.utils.debug;

import com.badlogic.gdx.Gdx;

/**
 * Created by Rubentxu on 25/06/14.
 */
public class GameLogger {

    public static void info(String tag, String message, Object... params) {
        if (Gdx.app != null) {
            Gdx.app.log(tag, String.format(message, params));
        } else {
            System.out.printf(tag.concat("--").concat(message).concat("\n"), params);
        }

    }


    public static void error(String tag, String message, Object... params) {
        if (Gdx.app != null) {
            Gdx.app.error(tag, String.format(message, params));
        } else {
            System.out.printf(tag.concat("--").concat(message).concat("\n"), params);
        }

    }


    public static void debug(String tag, String message, Object... params) {
        if (Gdx.app != null) {
            Gdx.app.debug(tag, String.format(message, params));
        } else {
            System.out.printf(tag.concat("--").concat(message).concat("\n"), params);
        }

    }
}
