package org.firstinspires.ftc.teamcode.mechaninisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.imu.getHeading;

public class Drivetrain {
    private HardwareMap hardwareMap;
    public getHeading heading;

    public final DcMotorEx leftFront, leftBack, rightBack, rightFront;

    public Drivetrain(HardwareMap hardwareMapRef) {
        hardwareMap = hardwareMapRef;

        leftFront = hardwareMap.get(DcMotorEx.class, "fl");
        rightFront = hardwareMap.get(DcMotorEx.class, "fr");
        leftBack = hardwareMap.get(DcMotorEx.class, "bl");
        rightBack = hardwareMap.get(DcMotorEx.class, "br");


        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void runDriveCode(float left_stick_x, float left_stick_y, float right_stick_x) {

        // May need to mess with signs
        double strafe = left_stick_x;
        double drive = left_stick_y;
        double rotation = right_stick_x;

        double frontLeftPower = drive - rotation - strafe;
        double frontRightPower = drive + rotation + strafe;
        double backLeftPower = drive - rotation + strafe;
        double backRightPower = drive + rotation - strafe;

        // Send calculated power to wheels
        setPowers(frontLeftPower , frontRightPower, backLeftPower,backRightPower );

    }
    
    public void fieldCentricDrive(float left_stick_x, float left_stick_y, float right_stick_x) {
        float yaw = heading.heading.secondAngle;

        double x = left_stick_x*(Math.cos(yaw))+left_stick_y*(Math.sin(yaw));
        double y = left_stick_y*(Math.cos(yaw))-left_stick_x*(Math.sin(yaw));
        double rotation = right_stick_x;

        double frontLeftPower = y - rotation - x;
        double frontRightPower = y + rotation + x;
        double backLeftPower = y - rotation + x;
        double backRightPower = y + rotation - x;

        setPowers(frontLeftPower , frontRightPower, backLeftPower,backRightPower);

    }
    
    public void setPowers(double lf, double rf, double lb, double rb) {
        leftFront.setPower(lf);
        rightFront.setPower(rf);
        leftBack.setPower(lb);
        rightBack.setPower(rb);
    }
}
