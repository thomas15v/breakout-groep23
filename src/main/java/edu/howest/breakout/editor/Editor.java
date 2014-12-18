package edu.howest.breakout.editor;

import edu.howest.breakout.client.GameGrid;
import edu.howest.breakout.game.Database;
import edu.howest.breakout.game.entity.EntityBlock;
import edu.howest.breakout.game.info.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

public class Editor extends JFrame implements Observer, KeyListener {

    private EditorGame game;
    private EditorPannel editorPannel;
    private JPanel RootPannel;
    private GameGrid gameGrid;
    private ClickManager clickManager;
    private Database database;

    public Editor(){
        setVisible(true);
        addKeyListener(this);
        setContentPane(RootPannel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                game.setGameState(GameState.EndGame);
            }
        });
        this.clickManager = new ClickManager(this);
        gameGrid.addMouseListener(clickManager);
        gameGrid.addMouseMotionListener(clickManager);
        new Thread(gameGrid).start();
    }

    public static void main(String[] args){
        Editor frame = new Editor();
    }

    public GameGrid getGameGrid() {
        return gameGrid;
    }

    public EditorGame getGame() {
        return game;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null){
            if (arg.equals(GameState.Errored))
                close(true);
            else if (arg.equals(GameState.EndGame))
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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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

    private void createUIComponents() throws Exception {
        tweakStyle();
        this.game = new EditorGame();
        this.database = new Database("root", "", "jdbc:mysql://localhost:3306/breakout");
        this.editorPannel = new EditorPannel(game, database);
        this.gameGrid = new GameGrid(game);
    }

    public EditorPannel getEditorPannel() {
        return editorPannel;
    }
}