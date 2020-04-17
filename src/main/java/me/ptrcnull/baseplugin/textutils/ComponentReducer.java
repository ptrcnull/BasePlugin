package me.ptrcnull.baseplugin.textutils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.function.BinaryOperator;

public class ComponentReducer implements BinaryOperator<BaseComponent> {
    private BaseComponent[] join;

    public ComponentReducer() {
    }

    public ComponentReducer(BaseComponent[] join) {
        this.join = join;
    }

    @Override
    public BaseComponent apply(BaseComponent c1, BaseComponent c2) {
        BaseComponent base;
        if (c1.getClickEvent() != null || c1.getHoverEvent() != null) {
            base = new TextComponent();
            base.addExtra(c1);
        } else {
            base = c1;
        }

        if (join != null) {
            for (BaseComponent j : join) {
                base.addExtra(j);
            }
        }
        base.addExtra(c2);
        return base;
    }
}
