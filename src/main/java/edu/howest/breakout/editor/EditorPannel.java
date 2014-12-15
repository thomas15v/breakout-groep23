package edu.howest.breakout.editor;

import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBlock;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thomas15v on 15/12/14.
 */
public class EditorPannel extends JPanel implements ListSelectionListener, ChangeListener {

    private EditorGame game;
    private JList<Entity> componentList;
    private JButton addButton;
    private JButton removeButton;
    private JPanel RootPannel;
    private JPanel AddPannel;
    private JLabel xlabel;
    private JLabel ylabel;
    private JLabel widthLabel;
    private JLabel lengthLabel;
    private JComboBox comboBox1;
    private JSpinner yValue;
    private JSpinner xValue;
    private JSpinner widthValue;
    private JSpinner heightValue;
    private boolean canchange = true;

    public EditorPannel(final EditorGame game){
        setVisible(true);
        this.game = game;
        this.componentList.setModel(game.getListModel());
        this.componentList.addListSelectionListener(this);
        this.yValue.addChangeListener(this);
        this.xValue.addChangeListener(this);
        this.widthValue.setValue(50);
        this.heightValue.setValue(50);
        add(RootPannel);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (componentList.getSelectedValue() != null)
                    game.remove(componentList.getSelectedValue());
                    repaint();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.add(new EntityBlock(getInteger(yValue), getInteger(xValue), Color.black, getInteger(widthValue), getInteger(heightValue)));
            }
        });
    }

    public void select(Entity entity){
        componentList.setSelectedValue(entity, true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        this.canchange = false;
        if (!componentList.isSelectionEmpty()) {
            yValue.setValue(componentList.getSelectedValue().getX());
            xValue.setValue(componentList.getSelectedValue().getY());
        }
        this.canchange = true;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (!componentList.isSelectionEmpty() && canchange == true){
            for (Entity entity : componentList.getSelectedValuesList()) {
                entity.setX(componentList.getSelectedValue().getX() + entity.getX() + getInteger(xValue));
                entity.setY(componentList.getSelectedValue().getY() + entity.getY() + getInteger(yValue));
            }
        }
    }

    private int getInteger(JSpinner spinner){
      return  ((SpinnerNumberModel) spinner.getModel()).getNumber().intValue();
    }


}
