package me.ptrcnull.baseplugin.textutils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.function.BinaryOperator;

public class ComponentReducer implements BinaryOperator<TextComponent> {
    private BaseComponent[] join;

    public ComponentReducer() {
    }

    public ComponentReducer(BaseComponent[] join) {
        this.join = join;
    }

    @Override
    public TextComponent apply(TextComponent c1, TextComponent c2) {
        if (join != null) {
            for (BaseComponent j : join) {
                c1.addExtra(j);
            }
        }
        c1.addExtra(c2);
        return c1;
    }
}