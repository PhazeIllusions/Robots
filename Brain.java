package org.jointheleague.iaroc;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import org.wintrisstech.irobot.ioio.IRobotCreateAdapter;
import org.wintrisstech.irobot.ioio.IRobotCreateInterface;
import org.jointheleague.iaroc.sensors.UltraSonicSensors;

public class Brain extends IRobotCreateAdapter {
    private final Dashboard dashboard;
    public UltraSonicSensors sonar;

    public Brain(IOIO ioio, IRobotCreateInterface create, Dashboard dashboard)
            throws ConnectionLostException {
        super(create);
        sonar = new UltraSonicSensors(ioio);
        this.dashboard = dashboard;
    }

    int speed = 100;

    /* This method is executed when the robot first starts up. */
    public void initialize() throws ConnectionLostException {
        dashboard.log("Hello! I'm a Clever Robot!");
        //what would you like me to do, Clever Human?


        driveDirect(speed, speed);


    }

    /* This method is called repeatedly. */
    public void loop() throws ConnectionLostException, InterruptedException {
//              try {
//                  sonar.read();
//                  int getFront = sonar.getFrontDistance();
//                  int getRight = sonar.getRightDistance();
//                  int getLeft = sonar.getLeftDistance();
//                  dashboard.log("" + getFront + "" + getLeft + "" + getRight);
//              } catch (Exception e)       {
//
//                      dashboard.log("" + e.getMessage());
//
//              }

        readSensors(SENSORS_INFRARED_BYTE);
        int getInfared = getInfraredByte();
        dashboard.log("" + getInfared);
        if (getInfared != 255) {
            speed = speed - 50;
            driveDirect(speed, speed);
        }
        if (getInfared == 255) {
            speed = speed + 20;
            driveDirect(speed, speed);

        }
    }
}