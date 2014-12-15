package edu.howest.breakout.editor;

import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;
import edu.howest.breakout.game.entity.EntityBlock;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by thomas15v on 15/12/14.
 */
public class ClickManager implements MouseListener {

    private Editor editor;

    public ClickManager(Editor editor){
        this.editor = editor;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getPoint());
        for (Entity entity : editor.getGame().getEntities())
            if (entity instanceof EntityBlock)
                if (e.getPoint().getX() > entity.getX() && e.getPoint().getX() < entity.getX() + entity.getSizex() &&
                        e.getPoint().getY() > entity.getY() && e.getPoint().getY() < entity.getY() + entity.getSizey())
                    editor.getEditorPannel().select(entity);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
