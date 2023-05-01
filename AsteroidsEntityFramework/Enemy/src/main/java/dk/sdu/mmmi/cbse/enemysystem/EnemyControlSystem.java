package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ShooterPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IEntitySpawnService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;

import java.util.Collection;
import java.util.Random;

/**
 * @author AnneLaerke
 */

public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);
            ShooterPart shooterPart = enemy.getPart(ShooterPart.class);

            Random random = new Random();
            Random random2 = new Random();

            int range = random.nextInt(3);

            if (range == 0) {
                movingPart.setLeft(true);
            }
            if (range == 1) {
                movingPart.setRight(true);
            }
            if (range == 2) {
                movingPart.setUp(true);
            }

            if (lifePart.isIsHit()) {
                lifePart.setLife(lifePart.getLife() - 1);
                lifePart.setIsHit(false);
            }

            if (lifePart.getLife() == 0) {
                world.removeEntity(enemy);
            }

            int range2 = random2.nextInt(30);

            if (range2 == 1) {
                Collection<IEntitySpawnService> bullets = SPILocator.locateAll(IEntitySpawnService.class);

                for (IEntitySpawnService bullet : bullets) {
                    world.addEntity(bullet.spawn(gameData, enemy));
                }
            }

            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);

            movingPart.setLeft(false);
            movingPart.setRight(false);
            movingPart.setUp(false);
        }
    }

    /**
     * Updates the entity's shape continuously
     * @param entity the entity to be shaped
     */
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
}
