package edu.howest.breakout.editor;

import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBlock;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by thomas15v on 15/12/14.
 */
public class ClickManager implements MouseListener, MouseMotionListener {

    private Editor editor;
    private Entity pressedentity;
    private int xd;
    private int yd;
    private Point startpoint;

    public ClickManager(Editor editor){
        this.editor = editor;
    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Entity entity : editor.getGame().getEntities())
            if (entity instanceof EntityBlock)
                if (e.getPoint().getX() > entity.getX() && e.getPoint().getX() < entity.getX() + entity.getWidth() &&
                        e.getPoint().getY() > entity.getY() && e.getPoint().getY() < entity.getY() + entity.getHeight()) {
                    this.pressedentity = entity;
                    this.startpoint = e.getPoint();
                    editor.getEditorPannel().select(pressedentity, e.isControlDown());
                    this.xd = (int) (e.getX() - entity.getX());
                    this.yd = (int) (e.getY() - entity.getY());
                    break;
                }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.editor.getEditorPannel().select(pressedentity, e.isControlDown());
        this.pressedentity = null;
        this.startpoint = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override

    //ik this sucks balls, but its the freaking editor for us so yeah.
    public void mouseDragged(MouseEvent e) {
        if (pressedentity != null ) {
            editor.getEditorPannel().valueChanged(null);
            if (e.isShiftDown()) {
                int xd = (int) (e.getX() - startpoint.getX());
                int yd = (int) (e.getY() - startpoint.getX());
                //most shitty part, its almost over .... I am so sorry
                if (xd > yd)
                    editor.getEditorPannel().moveSelection(e.getX() - this.xd, (int) editor.getEditorPannel().getSelectedValue().getY());
                else
                    editor.getEditorPannel().moveSelection((int) editor.getEditorPannel().getSelectedValue().getX(), e.getY() - this.yd);
            }
            else {
                editor.getEditorPannel().moveSelection(e.getX() - xd, e.getY() - yd);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
