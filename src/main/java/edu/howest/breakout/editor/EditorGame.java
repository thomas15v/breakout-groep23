package edu.howest.breakout.editor;

import edu.howest.breakout.game.Game;
import edu.howest.breakout.game.entity.Entity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;

/**
 * Created by thomas15v on 15/12/14.
 */
public class EditorGame extends Game {

    //I know this is far from efficent, but whe don't have mutch time left :(
    DefaultListModel<Entity> defaultListModel = new DefaultListModel<Entity>();

    public EditorGame() {
        super(null);
    }

    private DefaultListModel<Entity> entityList;

    @Override
    public void run() {
        //whe are simulating so no need for running, crash bitch!!
        throw new NotImplementedException();
    }

    @Override
    public void add(Entity entity) {
        defaultListModel.addElement(entity);
        super.add(entity);
    }

    @Override
    public void remove(Entity entity) {
        entity.setDestroyed(true);
        defaultListModel.removeElement(entity);
        super.remove(entity);
    }

    public DefaultListModel<Entity> getListModel(){
        return defaultListModel;
    }
}
