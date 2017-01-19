package com.indignado.games.states.game.component;

import com.ilargia.games.entitas.codeGenerator.Component;
import com.ilargia.games.entitas.interfaces.IComponent;

@Component(pools = {"SMCore"})
public class Label implements IComponent {
    public String text;
    public String font;

    public Label(String text, String font) {
        this.text = text;
        this.font = font;
    }

}
