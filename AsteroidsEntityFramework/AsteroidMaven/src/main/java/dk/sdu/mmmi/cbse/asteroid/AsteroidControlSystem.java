package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);

            if (lifePart.isIsHit()) {
                lifePart.reduceLife(1);
                lifePart.setIsHit(false);

                if (lifePart.getLife() == 0) {
                    world.removeEntity(asteroid);

                } else {
                    asteroid.setRadius(10);

                    Entity newAsteroid = new AsteroidPlugin().createAsteroid(gameData);

                    newAsteroid.setRadius(asteroid.getRadius());
                    PositionPart newAsteroidPosition = newAsteroid.getPart(PositionPart.class);
                    newAsteroidPosition.setPosition(positionPart.getX(), positionPart.getY());

                    LifePart newLifePart = newAsteroid.getPart(LifePart.class);
                    newLifePart.setLife(lifePart.getLife());

                    world.addEntity(newAsteroid);
                }
            }

            Random random = new Random();

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

            positionPart.process(gameData, asteroid);
            movingPart.process(gameData, asteroid);

            updateShape(asteroid, lifePart.getLife());

            movingPart.setLeft(false);
            movingPart.setRight(false);
            movingPart.setUp(false);
        }
    }

    private void updateShape(Entity entity, float asteroidSize) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        if (asteroidSize == 2) {
            shapex[0] = (float) (x + Math.cos(radians) * 20);
            shapey[0] = (float) (y + Math.sin(radians) * 20);

            shapex[1] = (float) (x + Math.cos(radians - 4) * 20);
            shapey[1] = (float) (y + Math.sin(radians - 4) * 20);

            shapex[2] = (float) (x + Math.cos(radians + 4) * 20);
            shapey[2] = (float) (y + Math.sin(radians + 4) * 20);

            shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * -20);
            shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * -20);

        } else if (asteroidSize == 1) {
            shapex[0] = (float) (x + Math.cos(radians) * 15);
            shapey[0] = (float) (y + Math.sin(radians) * 15);

            shapex[1] = (float) (x + Math.cos(radians - 4) * 15);
            shapey[1] = (float) (y + Math.sin(radians - 4) * 15);

            shapex[2] = (float) (x + Math.cos(radians + 4) * 15);
            shapey[2] = (float) (y + Math.sin(radians + 4) * 15);

            shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * -15);
            shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * -15);

            entity.setShapeX(shapex);
            entity.setShapeY(shapey);
        }
    }
}
