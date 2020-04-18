package me.ptrcnull.baseplugin.textutils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ComponentBuilder {
    private TextComponent base = new TextComponent();
    private TextComponent c;

    public ComponentBuilder() {
        c = new TextComponent();
    }

    public ComponentBuilder(String message) {
        c = new TextComponent(message);
    }

    public ComponentBuilder color(ChatColor color) {
        c.setColor(color);
        return this;
    }

    public ComponentBuilder event(ClickEvent ev) {
        c.setClickEvent(ev);
        return this;
    }

    public ComponentBuilder event(HoverEvent ev) {
        c.setHoverEvent(ev);
        return this;
    }

    public ComponentBuilder text(String text) {
        base.addExtra(c);
        c = new TextComponent(text);
        return this;
    }

    public ComponentBuilder component(TextComponent component) {
        base.addExtra(c);
        c = component;
        return this;
    }

    public ComponentBuilder components(BaseComponent[] components) {
        base.addExtra(c);

        for (final BaseComponent comp : components) {
            base.addExtra(comp);
        }

        c = new TextComponent();

        return this;
    }

    public TextComponent build() {
        base.addExtra(c);

        return base;
    }

    public BaseComponent[] toArray() {
        base.addExtra(c);

        return base.getExtra().toArray(new BaseComponent[]{});
    }
}
