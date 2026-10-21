package org.firstinspires.ftc.teamcode.mechaninisms;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotVars;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class Intake {

    public final DcMotorEx intakeMotor;

    private final RobotVars robotVars;

    public Intake(HardwareMap hardwareMap, RobotVars robotVarsRef) {
        robotVars = robotVarsRef;

        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");
        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    public void runIntake(boolean intakeButton) {
        if (intakeButton) {
            robotVars.isIntakeActive = !robotVars.isIntakeActive;
        }


        if (robotVars.isIntakeActive) {
            intakeMotor.setPower(robotVars.constants.intakeMotorSpeed);
        } else {
            intakeMotor.setPower(0);
        }
    }



}
