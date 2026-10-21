package org.firstinspires.ftc.teamcode.mechaninisms;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotVars;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class Transfer {

    public final DcMotorEx transferMotor;

    private final RobotVars robotVars;

    public Transfer(HardwareMap hardwareMap, RobotVars robotVarsRef) {
        robotVars = robotVarsRef;

        transferMotor = hardwareMap.get(DcMotorEx.class, "transfer");
        transferMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        transferMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    public void runTransfer() {
        robotVars.isTransferActive = robotVars.isIntakeActive;

        if (robotVars.isTransferActive) {
            transferMotor.setPower(robotVars.constants.transferMotorSpeed);
        } else {
            transferMotor.setPower(0);
        }
    }



}
