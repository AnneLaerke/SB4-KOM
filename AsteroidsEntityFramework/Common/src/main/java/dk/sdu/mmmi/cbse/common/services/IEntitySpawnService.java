package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public interface IEntitySpawnService {
    Entity spawn(GameData gameData, Entity entity);
}
