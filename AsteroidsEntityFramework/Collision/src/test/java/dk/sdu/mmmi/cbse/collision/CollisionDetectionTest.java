package dk.sdu.mmmi.cbse.collision;


import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionDetectionTest {
    private CollisionDetection collisionDetection;
    private Entity entity1;
    private Entity entity2;
    private Entity entity3;

    @BeforeEach
    public void setUp() {
        this.collisionDetection = new CollisionDetection();

        this.entity1 = new Entity();
        this.entity2 = new Entity();
        this.entity3 = new Entity();

        PositionPart pos1 = new PositionPart(1, 1, 0);
        PositionPart pos2 = new PositionPart(1, 0, 0);
        PositionPart pos3 = new PositionPart(15, 15, 0);

        entity1.add(pos1);
        entity2.add(pos2);
        entity3.add(pos3);

        entity1.setRadius(5);
        entity2.setRadius(5);
        entity3.setRadius(5);
    }

    @Test
    public void testCollision() {
        assertTrue(collisionDetection.collided(entity1, entity2));
        assertFalse(collisionDetection.collided(entity1, entity3));
        assertFalse(collisionDetection.collided(entity2, entity3));
    }
}
