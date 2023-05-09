import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Core {
    requires Common;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires com.badlogic.gdx;

    opens dk.sdu.mmmi.cbse.main;
    exports dk.sdu.mmmi.cbse.main;

    uses IPostEntityProcessingService;
    uses IEntityProcessingService;
    uses IGamePluginService;
}