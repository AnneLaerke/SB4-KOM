package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * ENTITY SPAWN SERVICE - Spawning entities
 * (e.g. particularly used for creating bullets in the game)
 */
public interface IEntitySpawnService {

    /**
     * (Explained in the use-case of spawning bullets for shooting)
     * <br />
     * PRE-CONDITION:   Game is running and a call to shoot a bullet has been made.
     * <br />
     * POST-CONDITION:  A bullet has been created, ready to be added to the game world.
     *
     * @param gameData  Data for the game world
     * @param entity    The entity to be spawned
     *
     * @return Entity entity (e.g. bullet)
     */
    Entity spawn(GameData gameData, Entity entity);
}
