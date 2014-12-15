package edu.howest.breakout.editor;

import edu.howest.breakout.client.GameGrid;
import edu.howest.breakout.game.entity.EntityBlock;
import edu.howest.breakout.game.info.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

public class Editor extends JFrame implements Observer, KeyListener {

    private EditorGame game;
    private EditorPannel editorPannel;
    private JPanel RootPannel;
    private GameGrid gameGrid;

    public Editor(){
        setVisible(true);
        addKeyListener(this);
        setLayout(new GridBagLayout());
        setContentPane(RootPannel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    public static void main(String[] args){
        Editor frame = new Editor();
    }

    public GameGrid getGameGrid() {
        return gameGrid;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null)
        {
            if (arg.equals(GameState.Errored))
                close(true);
            else if (arg.equals(GameState.closed))
                close(false);
        }
    }

    public void close(Boolean error){
        setVisible(false);
        dispose();
        if (error)
            System.out.println("Program exited unexpectedly, lets hope this didn't happen during demonstration!!");
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void tweakStyle(){
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if (info.getName().equals("GTK+"))
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
        }
    }

    private void createUIComponents() throws Exception {
        tweakStyle();
        this.game = new EditorGame();
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 4; y++)
                game.add(new EntityBlock(x * 52 + 10, y * 52 + 10, Color.black, 50, 50));
        this.editorPannel = new EditorPannel(game);
        this.gameGrid = new GameGrid(game);
        new Thread(gameGrid).start();
    }
}
