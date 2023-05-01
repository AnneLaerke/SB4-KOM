package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class ShooterPart implements EntityPart {


    @Override
    public void process(GameData gameData, Entity entity) {

    }


//    private float coolDownTime;
//    private float coolDown;
//    private boolean shooting;
//
//    public ShooterPart(float coolDownTime) {
//        this.coolDownTime = coolDownTime;
//    }
//
//    public void setShooting(boolean shooting) {
//        if (coolDown > 0) {
//            this.shooting = false;
//            return;
//        }
//        this.coolDown = this.coolDownTime;
//    }
//
//    public boolean canShoot() {
//        if (this.coolDown > 0) {
//            this.coolDown -= gameData.getDelta();
//            this.shooting = false;
//    }
//
//    public boolean isShooting() {
//        return this.shooting;
//    }
//
//    @Override
//    public void process(GameData gameData, Entity entity) {
//
//    }
}
