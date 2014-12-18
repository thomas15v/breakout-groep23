package edu.howest.breakout.game.info;

import edu.howest.breakout.game.entity.EntityBlock;

import java.util.List;

/**
 * Created by thomas15v on 16/12/14.
 */
public class Level {

    private int id;
    private String name;
    private int width;
    private int height;
    private List<EntityBlock> blockList;

    public Level(int id, String name, int width, int height){
        this(name, width, height, null);
        this.id = id;
    }

    public Level(String name, int width, int height, List<EntityBlock> blockList){
        this.name = name;
        this.width = width;
        this.height = height;
        this.blockList = blockList;
        this.id = 0; //this Level doesn't has a database instance
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<EntityBlock> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<EntityBlock> blockList) {
        this.blockList = blockList;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getName();
    }
}
