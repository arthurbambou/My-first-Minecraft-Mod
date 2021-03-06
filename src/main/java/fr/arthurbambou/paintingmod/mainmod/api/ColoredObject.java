package fr.arthurbambou.paintingmod.mainmod.api;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class ColoredObject {
    public String name;

    public Block black;
    public Block red;
    public Block green;
    public Block brown;
    public Block blue;
    public Block purple;
    public Block cyan;
    public Block lightgray;
    public Block gray;
    public Block pink;
    public Block lime;
    public Block yellow;
    public Block lightblue;
    public Block magenta;
    public Block orange;
    public Block white;

    private Block replace;
    public Identifier replaceName;

    public Block.Settings settings;
    public String modid;

    private Map<TextureFace,Identifier> textureFaceIdentifierMap = new HashMap<>();

    public ColoredObject(String name, Block replace, String modid) {
        this.name = name;
        this.replace = replace;
        if (this.replace != null) {
            this.settings = Block.Settings.copy(this.replace);
        }
        this.modid = modid;
    }

    public ColoredObject(Identifier replaceName, String modid) {
        this(replaceName.getPath(),Registry.BLOCK.get(replaceName), modid);
        this.replaceName = replaceName;
    }

    public Block getReplace() {
        if (this.replace == null) {
            return Registry.BLOCK.get(this.replaceName);
        } else {
            return this.replace;
        }
    }

    public String getName() {
        return name;
    }

    public String getModid() {
        return modid;
    }

    public Identifier getRegisteryName() {
        return new Identifier(modid, name);
    }

    public abstract void createBlocks();

    public Map<TextureFace, Identifier> getTextureMap() {
        return this.textureFaceIdentifierMap;
    }

    public ArrayList<Block> getArrayList() {
        ArrayList<Block> list = new ArrayList<>();
        list.add(this.black);
        list.add(this.red);
        list.add(this.green);
        list.add(this.brown);
        list.add(this.blue);
        list.add(this.purple);
        list.add(this.cyan);
        list.add(this.lightgray);
        list.add(this.gray);
        list.add(this.pink);
        list.add(this.lime);
        list.add(this.yellow);
        list.add(this.lightblue);
        list.add(this.magenta);
        list.add(this.orange);
        list.add(this.white);

        return list;
    }

    public Block getBlockbyColor(Color color) {
        switch (color) {
            case BLACK:
                return this.black;
            case BLUE:
                return this.blue;
            case BROWN:
                return this.brown;
            case CYAN:
                return this.cyan;
            case GRAY:
                return this.gray;
            case GREEN:
                return this.green;
            case LIGHT_BLUE:
                return this.lightblue;
            case LIGHT_GRAY:
                return this.lightgray;
            case LIME:
                return this.lime;
            case MAGENTA:
                return this.magenta;
            case ORANGE:
                return this.orange;
            case PINK:
                return this.pink;
            case PURPLE:
                return this.purple;
            case RED:
                return this.red;
            case WHITE:
                return this.white;
            case YELLOW:
                return this.yellow;
            default:
                return null;
        }
    }


    public enum Color {
        BLACK(40,40,40),
        BLUE(0,51,121),
        BROWN(153,67,0),
        CYAN(51,153,204),
        GRAY(100,100,100),
        GREEN(72,91,0),
        LIGHT_BLUE(0,153,255),
        LIGHT_GRAY(152,152,152),
        LIME(0,255,0),
        MAGENTA(204,51,204),
        ORANGE(255, 153, 51),
        PINK(255,0,255),
        PURPLE(102,0,153),
        RED(153,0,0),
        WHITE(255,255,255),
        YELLOW(255,255,0);

        private java.awt.Color color;
        Color(int r, int g, int b) {
            this.color = new java.awt.Color(r,g,b);
        }

        public java.awt.Color getColor() {
            return this.color;
        }
    }

    public enum TextureFace {
        ALL,
        TOP,
        BOTTOM,
        SIDE;
    }
}
