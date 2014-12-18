package edu.howest.breakout.game;


import com.googlecode.flyway.core.Flyway;
import static edu.howest.breakout.database.Tables.*;

import edu.howest.breakout.game.entity.EntityBlock;
import edu.howest.breakout.game.info.Level;
import edu.howest.breakout.game.powerup.PowerUp;
import edu.howest.breakout.game.score.Player;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Database {

    private Connection conn = null;
    private String username;
    private String password;
    private String url;
    private DSLContext create = null;

    public Database(String username, String password, String url){
        this.username = username;
        this.password = password;
        this.url = url;
        checkConnect();
        checkDatabaseSetup();
    }

    private void checkDatabaseSetup() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, username, password);
        flyway.migrate();
        this.create = DSL.using(conn, SQLDialect.MYSQL);
    }

    public void checkConnect(){
        try {
            if (conn == null || conn.isClosed()){
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                this.conn = DriverManager.getConnection(url, username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addLevel(Level level){
        int id = create.insertInto(LEVELS).set(create.newRecord(LEVELS, level)).returning(LEVELS.ID).fetchOne().getValue(LEVELS.ID);
        for (EntityBlock block : level.getBlockList())
            create.insertInto(LEVEL_MAP).set(create.newRecord(LEVEL_MAP, block)).set(LEVEL_MAP.ID, id).execute();
    }

    public Level getLevel(int id){
        Level level = create.select().from(LEVELS).where(LEVELS.ID.eq(id)).fetchOne().into(Level.class);
        List<EntityBlock> blocks = create.select().from(LEVEL_MAP).where(LEVEL_MAP.ID.eq(level.getId())).fetch().map(new RecordMapper<Record, EntityBlock>() {
            @Override
            public EntityBlock map(Record record) {
                return new EntityBlock(record.getValue(LEVEL_MAP.X).intValue(),
                        record.getValue(LEVEL_MAP.Y).intValue(),
                        record.getValue(LEVEL_MAP.WIDTH).intValue(),
                        record.getValue(LEVEL_MAP.HEIGHT).intValue(),
                        getPowerUp(record.getValue(LEVEL_MAP.POWERUPTYPE).intValue()));
            }
        });
        level.setBlockList(blocks);
        System.out.println(blocks);
        return level;
    }

    public List<Level> getLevels(){
        List<Level> levels = create.select().from(LEVELS).fetch().into(Level.class);
        for (Level level : levels){
            List<EntityBlock> blocks = create.select().from(LEVEL_MAP).where(LEVEL_MAP.ID.eq(level.getId())).fetch().map(new RecordMapper<Record, EntityBlock>() {
                @Override
                public EntityBlock map(Record record) {
                    return new EntityBlock(record.getValue(LEVEL_MAP.X).intValue(),
                            record.getValue(LEVEL_MAP.Y).intValue(),
                            record.getValue(LEVEL_MAP.WIDTH).intValue(),
                            record.getValue(LEVEL_MAP.HEIGHT).intValue(),
                            getPowerUp(record.getValue(LEVEL_MAP.POWERUPTYPE).intValue()));
                }
            });
            level.setBlockList(blocks);
        }
        return levels;
    }

    public void removeLevel(Level level) {
        if (level.getId() == -1)
            return;
        create.delete(LEVELS).where(LEVELS.ID.eq(level.getId())).execute();
        create.delete(LEVEL_MAP).where(LEVEL_MAP.ID.eq(level.getId())).execute();
    }

    public List<PowerUp> getPowerUpList(){
        return create.select().from(POWERUPS).fetch().into(PowerUp.class);
    }

    public void storePowerup(PowerUp powerUp){
        create.insertInto(POWERUPS).set(create.newRecord(POWERUPS, powerUp));
    }

    public PowerUp getPowerUp(int id){
        System.out.println(id);
        return create.select().from(POWERUPS).where(POWERUPS.ID.eq(id)).fetchOne().into(PowerUp.class);
    }

    public void addPlayer(Player player){

    }
}
