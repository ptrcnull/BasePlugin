package me.ptrcnull.baseplugin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BasePlugin extends Plugin {
    private Configuration config;
    private static Configuration messages;

    public Configuration getConfig() {
        return config;
    }

    public void reloadConfig() {
        config = reload(new File(getDataFolder(), "config.yml"));
    }

    public void reloadMessages() {
        messages = reload(new File(getDataFolder(), "messages.yml"));
    }

    @Override
    public void onLoad() {
        if (getResourceAsStream("config.yml") != null) {
            try {
                reloadConfig();
            } catch (Exception ex) {
                getLogger().warning("Failed to load config!");
                ex.printStackTrace();
            }
        }

        if (getResourceAsStream("messages.yml") != null) {
            try {
                reloadMessages();
            } catch (Exception ex) {
                getLogger().warning("Failed to load messages!");
                ex.printStackTrace();
            }
        }
    }

    public static BaseComponent[] trans(String message, Object... args) {
        return TextComponent.fromLegacyText(
            ChatColor.translateAlternateColorCodes(
                '&',
                String.format(message, args)
            )
        );
    }

    public static BaseComponent[] tl(String key, Object... args) {
        return trans(messages.getString(key, key), args);
    }

    private Configuration reload(File file) {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();

                InputStream in = getResourceAsStream(file.getName());
                if (in != null) {
                    Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    in.close();
                }
            } catch (IOException e) {
                // Should never happen, unless some idiot^W user messes with permissions inside plugins folder
                e.printStackTrace();
            }
        }

        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Config failed to load");
    }

    public void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.config, new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            // Should never happen, unless some idiot^W user messes with permissions inside plugins folder
            e.printStackTrace();
        }
    }
}
