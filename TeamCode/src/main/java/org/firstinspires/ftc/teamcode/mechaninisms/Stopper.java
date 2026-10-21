package org.firstinspires.ftc.teamcode.mechaninisms;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RobotVars;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class Stopper {

    public final Servo stopperServo;

    private final RobotVars robotVars;

    public Stopper(HardwareMap hardwareMap, RobotVars robotVarsRef) {
        robotVars = robotVarsRef;

        stopperServo = hardwareMap.get(Servo.class, "stopper");
    }


    public void runStopper(boolean stopperButton) {
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
