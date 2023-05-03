import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IEntitySpawnService;

module Bullet {
    requires Common;

    provides IGamePluginService with dk.sdu.mmmi.cbse.bullet.BulletPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.bullet.BulletControlSystem;
//    provides IEntitySpawnService with dk.sdu.mmmi.cbse.bullet.BulletPlugin;
}