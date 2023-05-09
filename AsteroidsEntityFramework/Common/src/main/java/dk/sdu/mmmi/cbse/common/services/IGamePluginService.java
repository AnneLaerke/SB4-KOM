package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * GAME PLUGIN - Adding entities to the game world
 */
public interface IGamePluginService {

    /**
     * PRE-CONDITION:   The game is starting up and the plugin has not yet been initialized.
     * <br />
     * POST-CONDITION:  The game has started and all entity plugins have been loaded into the game world.
     *
     * @param gameData  Data for the game world
     * @param world     World of the game
     *
     * @see GameData
     * @see World
     */
    void start(GameData gameData, World world);

    /**
     * PRE-CONDITION:   The game is running, but has been called to shut down, and the plugin have not yet been removed from the game world.
     * <br />
     * POST-CONDITION:  The plugin have stopped and entities removed from the game world.
     *
     * @param gameData  Data for the game world
     * @param world     World of the game
     *
     * @see GameData
     * @see World
     */
    void stop(GameData gameData, World world);
}
