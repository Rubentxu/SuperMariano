package com.indignado.games.states.game.gen;

import com.ilargia.games.entitas.matcher.Matcher;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class SmcoreMatcher {

	private static Matcher _matcherAnimationView;
	private static Matcher _matcherBackground;
	private static Matcher _matcherDelay;
	private static Matcher _matcherKeyboardSensor;
	private static Matcher _matcherLabel;
	private static Matcher _matcherMotion;
	private static Matcher _matcherTransformCollection;
	private static Matcher _matcherViewCollection;

	public static Matcher AnimationView() {
		if (_matcherAnimationView == null) {
			Matcher matcher = (Matcher) Matcher
					.AllOf(SmcoreComponentIds.AnimationView);
			matcher.componentNames = SmcoreComponentIds.componentNames();
			_matcherAnimationView = matcher;
		}
		return _matcherAnimationView;
	}

	public static Matcher Background() {
		if (_matcherBackground == null) {
			Matcher matcher = (Matcher) Matcher
					.AllOf(SmcoreComponentIds.Background);
			matcher.componentNames = SmcoreComponentIds.componentNames();
			_matcherBackground = matcher;
		}
		return _matcherBackground;
	}

	public static Matcher Delay() {
		if (_matcherDelay == null) {
			Matcher matcher = (Matcher) Matcher.AllOf(SmcoreComponentIds.Delay);
			matcher.componentNames = SmcoreComponentIds.componentNames();
			_matcherDelay = matcher;
		}
		return _matcherDelay;
	}

	public static Matcher KeyboardSensor() {
		if (_matcherKeyboardSensor == null) {
			Matcher matcher = (Matcher) Matcher
					.AllOf(SmcoreComponentIds.KeyboardSensor);
			matcher.componentNames = SmcoreComponentIds.componentNames();
			_matcherKeyboardSensor = matcher;
		}
		return _matcherKeyboardSensor;
	}

	public static Matcher Label() {
		if (_matcherLabel == null) {
			Matcher matcher = (Matcher) Matcher.AllOf(SmcoreComponentIds.Label);
			matcher.componentNames = SmcoreComponentIds.componentNames();
			_matcherLabel = matcher;
		}
		return _matcherLabel;
	}

	public static Matcher Motion() {
		if (_matcherMotion == null) {
			Matcher matcher = (Matcher) Matcher
					.AllOf(SmcoreComponentIds.Motion);
			matcher.componentNames = SmcoreComponentIds.componentNames();
			_matcherMotion = matcher;
		}
		return _matcherMotion;
	}

	public static Matcher TransformCollection() {
		if (_matcherTransformCollection == null) {
			Matcher matcher = (Matcher) Matcher
					.AllOf(SmcoreComponentIds.TransformCollection);
			matcher.componentNames = SmcoreComponentIds.componentNames();
			_matcherTransformCollection = matcher;
		}
		return _matcherTransformCollection;
	}

	public static Matcher ViewCollection() {
		if (_matcherViewCollection == null) {
			Matcher matcher = (Matcher) Matcher
					.AllOf(SmcoreComponentIds.ViewCollection);
			matcher.componentNames = SmcoreComponentIds.componentNames();
			_matcherViewCollection = matcher;
		}
		return _matcherViewCollection;
	}
}