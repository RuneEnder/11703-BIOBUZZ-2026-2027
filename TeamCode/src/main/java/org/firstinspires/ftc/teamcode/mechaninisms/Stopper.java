package org.firstinspires.ftc.teamcode.mechaninisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RobotVars;

public class Stopper {

    public final Servo stopperServo;

    private final RobotVars robotVars;

    public Stopper(HardwareMap hardwareMap, RobotVars robotVarsRef) {
        robotVars = robotVarsRef;

        stopperServo = hardwareMap.get(Servo.class, "stopper");
    }


    public void runStopper_ToggleMode(boolean stopperButton) {
        if (stopperButton) {
            robotVars.isStopperDown = !robotVars.isStopperDown;

            if (robotVars.isStopperDown) {
                stopperServo.setPosition(robotVars.constants.stopperDownPosition);
            } else {
                stopperServo.setPosition(robotVars.constants.stopperUpPosition);
            }
        }



    }



}
