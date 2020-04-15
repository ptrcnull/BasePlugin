package me.ptrcnull.baseplugin.textutils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ComponentBuilder {
    private TextComponent base = new TextComponent();
    private TextComponent c;

    ComponentBuilder() {
        c = new TextComponent();
    }

    ComponentBuilder(String message) {
        c = new TextComponent(message);
    }

    ComponentBuilder color(ChatColor color) {
        c.setColor(color);
        return this;
    }

    ComponentBuilder clickEvent(ClickEvent ev) {
        c.setClickEvent(ev);
        return this;
    }

    ComponentBuilder hoverEvent(HoverEvent ev) {
        c.setHoverEvent(ev);
        return this;
    }

    ComponentBuilder text(String text) {
        base.addExtra(c);
        c = new TextComponent(text);
        return this;
    }

    ComponentBuilder component(TextComponent component) {
        base.addExtra(c);
        c = component;
        return this;
    }

    TextComponent build() {
        base.addExtra(c);

        return base;
    }
}
