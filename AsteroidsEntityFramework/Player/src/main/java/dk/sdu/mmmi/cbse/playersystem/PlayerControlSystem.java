package dk.sdu.mmmi.cbse.playersystem;

//import dk.sdu.mmmi.cbse.bullet.Bullet;
//import dk.sdu.mmmi.cbse.bullet.BulletControlSystem;
//import dk.sdu.mmmi.cbse.bullet.BulletPlugin;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IEntitySpawnService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;

import java.util.Collection;
import java.util.ServiceLoader;

import static dk.sdu.mmmi.cbse.common.data.GameKeys.*;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author jcs
 */
public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);
            LifePart lifePart = player.getPart(LifePart.class);

            movingPart.setLeft(gameData.getKeys().isDown(LEFT));
            movingPart.setRight(gameData.getKeys().isDown(RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(UP));

            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
//                IEntitySpawnService bullet = new BulletControlSystem();
//                world.addEntity(bullet.spawn(gameData, player));
//                for (IEntitySpawnService bullet : SPILocator.locateAll(IEntitySpawnService.class)) {
//                    world.addEntity(bullet.spawn(gameData, player));
//                }
                Collection<IEntitySpawnService> bullets = SPILocator.locateAll(IEntitySpawnService.class);

                for (IEntitySpawnService bullet : bullets) {
                    world.addEntity(bullet.spawn(gameData, player));
                }
            }

            if (lifePart.isIsHit()) {
                lifePart.setLife(lifePart.getLife() - 1);
                lifePart.setIsHit(false);
                System.out.println(lifePart.getLife());
            }

            if (lifePart.getLife() == 0) {
                world.removeEntity(player);
            }

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);

            updateShape(player);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    private Collection<? extends IEntitySpawnService> getSPIs() {
        return ServiceLoader.load(IEntitySpawnService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
