package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

/**
 * @author AnneLaerke
 */

public class CollisionDetection implements IPostEntityProcessingService {

    /**
     * Continuously checks for collision between two entities and applies effects
     * @param gameData
     * @param world
     */
    @Override
    public void process(GameData gameData, World world) {
        for (Entity e1 : world.getEntities()) {
            for (Entity e2 : world.getEntities()) {
                // Checks that it isn't the same entity instance we check for collision on
                if (e1.getID().equals(e2.getID())) { continue; }
                // Entities of the same type won't collide
                if (e1.getClass().equals(e2.getClass())) { continue; }
                if (collided(e1, e2)) {
                    System.out.println(e1.getClass());
                    LifePart e1LifePart = e1.getPart(LifePart.class);
                    e1LifePart.setIsHit(true);
                }
            }
        }
    }

    /**
     * Checks collision between two entities
     * @param e1 The entity that gets hit
     * @param e2 The entity that hits the other entity
     * @return Boolean
     */
    public boolean collided(Entity e1, Entity e2) {
        PositionPart e1Pos = e1.getPart(PositionPart.class);
        PositionPart e2Pos = e2.getPart(PositionPart.class);

        float dy = e1Pos.getX() - e2Pos.getX();
        float dx = e1Pos.getY() - e2Pos.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance < (e1.getRadius() + e2.getRadius());
    }
}
