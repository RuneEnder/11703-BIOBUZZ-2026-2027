package org.firstinspires.ftc.teamcode.mechaninisms;


import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {
    private HardwareMap hardwareMap;
    private Follower follower;
    public final DcMotorEx leftFront, leftBack, rightBack, rightFront;

    public Drivetrain(HardwareMap hardwareMapRef) {
        hardwareMap = hardwareMapRef;

        leftFront = hardwareMap.get(DcMotorEx.class, "lf");
        rightFront = hardwareMap.get(DcMotorEx.class, "rf");
        leftBack = hardwareMap.get(DcMotorEx.class, "lr");
        rightBack = hardwareMap.get(DcMotorEx.class, "rr");


        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
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
        DrivePowers powers = ManualDrive.fieldCentric(
                -left_stick_y,
                left_stick_x ,
                right_stick_x,
                follower.pose().heading()
        );

        follower.manual(powers);
        follower.update();
    }
    
    public void setPowers(double lf, double rf, double lb, double rb) {
        leftFront.setPower(lf);
        rightFront.setPower(rf);
        leftBack.setPower(lb);
        rightBack.setPower(rb);
    }
}
