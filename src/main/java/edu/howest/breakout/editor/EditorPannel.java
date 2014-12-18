package edu.howest.breakout.editor;

import edu.howest.breakout.game.Database;
import edu.howest.breakout.game.entity.Entity;
import edu.howest.breakout.game.entity.EntityBall;
import edu.howest.breakout.game.entity.EntityBlock;
import edu.howest.breakout.game.info.Level;
import edu.howest.breakout.game.powerup.PowerUp;

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
    private JPanel RootPanel;
    private JPanel AddPannel;
    private JLabel xlabel;
    private JLabel ylabel;
    private JLabel widthLabel;
    private JLabel lengthLabel;
    private JComboBox powerUpBox;
    private JSpinner yValue;
    private JSpinner xValue;
    private JSpinner widthValue;
    private JSpinner heightValue;
    private JTextField LevelName;
    private JButton storeLevelbutton;
    private JButton removeLevelButton;
    private JList LevelList;
    private JButton newLevelButton;
    private JButton loadLevelButton;
    private boolean canchange = true;
    private Database database;

    public EditorPannel(final EditorGame game, final Database database){
        setVisible(true);
        this.game = game;
        this.database = database;
        this.componentList.setModel(game.getListModel());
        this.componentList.addListSelectionListener(this);
        this.componentList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        updateLevels();
        this.yValue.addChangeListener(this);
        this.xValue.addChangeListener(this);
        this.widthValue.addChangeListener(this);
        this.heightValue.addChangeListener(this);
        this.widthValue.setValue(50);
        this.heightValue.setValue(50);
        for (PowerUp powerUp : database.getPowerUpList())
            this.powerUpBox.addItem(powerUp);
        this.powerUpBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!componentList.isSelectionEmpty())
                    for (Entity entity : componentList.getSelectedValuesList())
                        if (entity instanceof EntityBlock)
                            ((EntityBlock) entity).setPowerUp((PowerUp) powerUpBox.getSelectedItem());
            }
        });
        add(RootPanel);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (componentList.getSelectedValue() != null)
                    for (Entity entity : componentList.getSelectedValuesList())
                        game.remove(entity);

            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.add(new EntityBlock(getInteger(yValue), getInteger(xValue), getInteger(widthValue), getInteger(heightValue), (PowerUp) powerUpBox.getSelectedItem()));
                canchange = false;
                yValue.setValue(getInteger(yValue) + getInteger(widthValue) + 2);
                canchange = true;
            }
        });
        loadLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!LevelList.isSelectionEmpty())
                    game.loadLevel((Level) LevelList.getSelectedValue());
            }
        });
        newLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.clearEntities();
            }
        });
        storeLevelbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database.addLevel(game.getLevel(LevelName.getText()));
                updateLevels();
            }
        });
        removeLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!LevelList.isSelectionEmpty())
                    database.removeLevel((Level) LevelList.getSelectedValue());
                updateLevels();
            }
        });
    }

    private void updateLevels() {
        this.LevelList.setListData(database.getLevels().toArray());
    }

    public void newLevel(){
        game.getEntities().clear();
    }

    public void select(Entity entity, boolean ctrlDown){
        if (ctrlDown) {
            System.out.println("more");
            int index = game.getListModel().indexOf(entity);
            componentList.getSelectionModel().addSelectionInterval(index, index);
        }
        else {
            componentList.clearSelection();
            componentList.setSelectedValue(entity, true);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        this.canchange = false;
        if (!componentList.isSelectionEmpty()) {
            xValue.setValue(componentList.getSelectedValue().getX());
            yValue.setValue(componentList.getSelectedValue().getY());
            widthValue.setValue(componentList.getSelectedValue().getWidth());
            heightValue.setValue(componentList.getSelectedValue().getHeight());
        }
        repaint();
        this.canchange = true;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (!componentList.isSelectionEmpty() && canchange == true) {
            moveSelection(getInteger(xValue), getInteger(yValue));
            resizeSelection(getInteger(widthValue), getInteger(heightValue));
        }
    }

    private void resizeSelection(int width, int heigth) {
        for (Entity entity : componentList.getSelectedValuesList()) {
            entity.setWidth(width);
            entity.setHeight(heigth);
        }
    }

    private int getInteger(JSpinner spinner){
      return ((SpinnerNumberModel) spinner.getModel()).getNumber().intValue();
    }

    public void moveSelection(int x2, int y2){
        int x1 = (int) componentList.getSelectedValue().getX();
        int y1 = (int) componentList.getSelectedValue().getY();
        if (!componentList.isSelectionEmpty()) {
            for (Entity entity : componentList.getSelectedValuesList()) {
                int dx = (int) (entity.getX() - x1);
                int dy = (int) (entity.getY() - y1);
                entity.setX(x2 + dx);
                entity.setY(y2 + dy);
            }
        }
    }

    public Entity getSelectedValue(){
        return componentList.getSelectedValue();
    }

}
