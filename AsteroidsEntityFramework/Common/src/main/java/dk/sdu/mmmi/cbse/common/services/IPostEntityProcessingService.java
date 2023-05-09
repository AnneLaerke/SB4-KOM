package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * ENTITY POST-PROCESSING SERVICE - Secondary entity processing after initial entity processing
 * (e.g. checking for collisions)
 */
public interface IPostEntityProcessingService  {

        /**
         * PRE-CONDITION:   A game loop and initial entity processing has been completed since last call.
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
