package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IEntitySpawnService;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class BulletControlSystem implements IEntityProcessingService, IEntitySpawnService {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {

            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);

            movingPart.setUp(true);

            lifePart.reduceExpiration(gameData.getDelta());

            if (lifePart.getExpiration() < 0) {
                world.removeEntity(bullet);
            }

            if (lifePart.isIsHit()) {
                world.removeEntity(bullet);
            }

            lifePart.process(gameData, bullet);
            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);

            updateShape(bullet);

        }
    }

    @Override
    public Entity spawn(GameData gameData, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);

        float x = positionPart.getX();
        float y = positionPart.getY();
        float direction = positionPart.getRadians();
        float speed = 1000;

        Entity bullet = new Bullet();
        bullet.setRadius(2);

        double dx = cos(positionPart.getRadians()) * 7 * bullet.getRadius();
        double dy = sin(positionPart.getRadians()) * 7 * bullet.getRadius();

        bullet.add(new PositionPart((float) (dx + x), (float) (dy + y), direction));
        bullet.add(new MovingPart(0, speed, speed, 0));
        bullet.add(new LifePart(5,2));

        bullet.setShapeX(new float[2]);
        bullet.setShapeY(new float[2]);

        return bullet;
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = x;
        shapey[0] = y;

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5));
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5));

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
