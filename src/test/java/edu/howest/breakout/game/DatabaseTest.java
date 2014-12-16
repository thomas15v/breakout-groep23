package edu.howest.breakout.game;

import edu.howest.breakout.game.entity.EntityBlock;
import edu.howest.breakout.game.info.Level;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTest {

    @Test
    public void TestStoreLevel(){
        Database database = new Database("root", "", "jdbc:mysql://localhost:3306/breakout");
        List<EntityBlock> blocks = new ArrayList<EntityBlock>();
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 4; y++)
                blocks.add(new EntityBlock(x * 52 + 10, y * 52 + 10, 50, 50, Color.black));
        database.addLevel(new Level("lol",0,0, blocks));
    }

}