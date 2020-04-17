package me.ptrcnull.baseplugin.textutils;


import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.function.BinaryOperator;

public class ComponentArrayReducer implements BinaryOperator<BaseComponent[]> {
    private BaseComponent[] join;

    public ComponentArrayReducer() {
    }

    public ComponentArrayReducer(BaseComponent[] join) {
        this.join = join;
    }

    @Override
    public BaseComponent[] apply(BaseComponent[] c1, BaseComponent[] c2) {
        TextComponent base = new TextComponent();
        for (final BaseComponent c : c1) {
            base.addExtra(c);
        }
        if (join != null) {
            for (BaseComponent j : join) {
                base.addExtra(j);
            }
        }
        for (final BaseComponent c : c2) {
            base.addExtra(c);
        }

        return new TextComponent[]{ base };
    }
}
