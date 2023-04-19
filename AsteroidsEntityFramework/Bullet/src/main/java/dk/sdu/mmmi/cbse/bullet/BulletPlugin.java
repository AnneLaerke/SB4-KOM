package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {

    private Entity bullet;
    float x;
    float y;

    public BulletPlugin(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void start(GameData gameData, World world) {
        bullet = createBullet(gameData, x, y);
        world.addEntity(bullet);
    }

    private Entity createBullet(GameData gameData, float x, float y) {
        float speed = 200;
        float radians = 3.1415f / 2;

        Entity bullet = new Bullet();
        bullet.add(new MovingPart(0, speed, speed, 0));
        bullet.add(new PositionPart(x, y, radians));

        return null;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(bullet);
    }
}
