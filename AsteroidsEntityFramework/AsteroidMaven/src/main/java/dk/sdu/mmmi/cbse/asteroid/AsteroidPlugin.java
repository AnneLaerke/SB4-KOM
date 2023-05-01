package dk.sdu.mmmi.cbse.asteroid;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {

    private Entity asteroid;

    public AsteroidPlugin() {

    }

    @Override
    public void start(GameData gameData, World world) {
        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }

    public Entity createAsteroid(GameData gameData) {

        float speed = MathUtils.random(100,150);
        float radians = 3.1415f / 2;
        float rotationSpeed = 5;
        float x = MathUtils.random(0, gameData.getDisplayWidth());
        float y = MathUtils.random(0, gameData.getDisplayHeight());


        Entity asteroid = new Asteroid();
        asteroid.setRadius(20);
        asteroid.add(new MovingPart(0, speed, speed, rotationSpeed));
        asteroid.add(new PositionPart(x, y, radians));
        asteroid.add(new LifePart(2, 2));

        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(asteroid);
    }
}
