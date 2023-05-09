package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * ENTITY PROCESSING SERVICE - Processing and updating entities
 */
public interface IEntityProcessingService {

    /**
     * PRE-CONDITION:   A game loop has been completed since last call.
     * <br />
     * POST-CONDITION:  The entity has been processed and updated accordingly.
     *
     * @param gameData  Data for the game world
     * @param world     World of the game
     *
     * @see GameData
     * @see World
     */
    void process(GameData gameData, World world);
}
