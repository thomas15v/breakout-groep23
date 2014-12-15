package edu.howest.breakout.editor;

import edu.howest.breakout.game.entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by thomas15v on 15/12/14.
 */
public class EditorPannel extends JPanel {

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
    private JSpinner xValue;
    private JSpinner yValue;
    private JSpinner widthValue;
    private JSpinner lengthValue;

    public EditorPannel(EditorGame game){
        setVisible(true);
        this.game = game;
        this.componentList.setModel(game.getListModel());
        add(RootPannel);
    }
}
