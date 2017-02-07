package com.indignado.games.states.game.gen;

import com.ilargia.games.entitas.matcher.Matcher;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class InputMatcher {

	private static Matcher _matcherPadButtons;
	private static Matcher _matcherPlayerInputController;
	private static Matcher _matcherTouchPad;

	public static Matcher PadButtons() {
		if (_matcherPadButtons == null) {
			Matcher matcher = (Matcher) Matcher
					.AllOf(InputComponentIds.PadButtons);
			matcher.componentNames = InputComponentIds.componentNames();
			_matcherPadButtons = matcher;
		}
		return _matcherPadButtons;
	}

	public static Matcher PlayerInputController() {
		if (_matcherPlayerInputController == null) {
			Matcher matcher = (Matcher) Matcher
					.AllOf(InputComponentIds.PlayerInputController);
			matcher.componentNames = InputComponentIds.componentNames();
			_matcherPlayerInputController = matcher;
		}
		return _matcherPlayerInputController;
	}

	public static Matcher TouchPad() {
		if (_matcherTouchPad == null) {
			Matcher matcher = (Matcher) Matcher
					.AllOf(InputComponentIds.TouchPad);
			matcher.componentNames = InputComponentIds.componentNames();
			_matcherTouchPad = matcher;
		}
		return _matcherTouchPad;
	}
}