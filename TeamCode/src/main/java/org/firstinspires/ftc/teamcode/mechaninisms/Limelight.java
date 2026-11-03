package org.firstinspires.ftc.teamcode.mechaninisms;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.RobotVars;


public class Limelight {
    Limelight3A limelight3a;
//    public Servo cameraArm;

    public boolean validResult = false;
    public int pipeline = 0;




    private final RobotVars robotVars;


    public Limelight(HardwareMap hardwareMapRef, RobotVars robotVarsRef) {
        robotVars = robotVarsRef;

        limelight3a = hardwareMapRef.get(Limelight3A.class, "limelight");
        limelight3a.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)

//        cameraArm = hardwareMapRef.get(Servo.class, "camArm");
    }


    public void startScript() {
        limelight3a.start(); // This tells Limelight to start looking!
        switchPipeline(0);
    }



    public void switchPipeline(int newPipeline) {
        pipeline = newPipeline;
        limelight3a.pipelineSwitch(pipeline);
    }


    public double directionToHive() {
        LLResult result = limelight3a.getLatestResult();

        double tx = 0;


        if (result != null && result.isValid()) {
            tx = result.getTx(); // How far left or right the target is (degrees)
        }

        if (Math.abs(tx) <= 2.5) {
            return 0;
        }

//        return robotVars.constants.hiveCentricRotationSpeed * ((0.25 * Math.signum(tx)) + (tx / 15)); // The range is approx [-15,15], the 0.25 is arbitrary
        return robotVars.constants.hiveCentricRotationSpeed * ( (Math.atan(0.264 * tx) * 2 / Math.PI) + (0.2 * Math.signum(tx))); // The range is approx [-15,15]

    }


    public double getTX() {
        LLResult result = limelight3a.getLatestResult();

        double tx = 0;


        if (result != null && result.isValid()) {
            tx = result.getTx(); // How far left or right the target is (degrees)
        }

        return tx;
    }



    public void updateLimelightLocalizer() {

        if (pipeline != 0) return;

        /*if (result != null && result.isValid()) {
            double tx = result.getTx(); // How far left or right the target is (degrees)
            double ty = result.getTy(); // How far up or down the target is (degrees)
            double ta = result.getTa(); // How big the target looks (0%-100% of the image)
        }*/

        // Update camPose
        LLResult result = limelight3a.getLatestResult();
        validResult = result != null && result.isValid();
        if (validResult) {
            Pose3D rawPose = result.getBotpose();

            if (rawPose != null) {
                // 39.37007874 is to convert meters to inches
                double x = rawPose.getPosition().y * 39.37007874 + 72;
                double y = 0 - (rawPose.getPosition().x * 39.37007874) + 72;
                double rot = rawPose.getOrientation().getYaw() - 90;

                robotVars.camPose = new Pose2D(DistanceUnit.INCH, x,y,AngleUnit.DEGREES, rot);
            }


            /*double newX = camPose.getX(DistanceUnit.INCH);
            double newY = camPose.getY(DistanceUnit.INCH);
            double newRobotHeading = camPose.getHeading(AngleUnit.DEGREES) - currentCameraArmAngle;
            robotPose = new Pose2D(DistanceUnit.INCH, newX, newY , AngleUnit.DEGREES, newRobotHeading);


            double newX = camPose.getX(DistanceUnit.INCH) + pivotToRobotOffsetX - Math.cos(Math.toRadians(currentCameraArmAngle)) * pivotToCamDist;
            double newY = camPose.getY(DistanceUnit.INCH) + pivotToRobotOffsetY  - Math.sin(Math.toRadians(currentCameraArmAngle)) * pivotToCamDist;
            double targetWorldAngle = Math.atan2(goalPos.x - robotPose.getX(DistanceUnit.INCH), goalPos.y - robotPose.getY(DistanceUnit.INCH));
            double newCamArmAngle = targetWorldAngle - robotPose.getHeading(AngleUnit.DEGREES);
            moveCameraArm(newCamArmAngle);*/
        }


    }



    /*public void updateLimelightMotif() {
        if (pipeline != 0) return;

        if (result != null && result.isValid()) {
            double tx = result.getTx(); // How far left or right the target is (degrees)
            double ty = result.getTy(); // How far up or down the target is (degrees)
            double ta = result.getTa(); // How big the target looks (0%-100% of the image)
        }

        // Update camPose
        LLResult result = limelight.getLatestResult();
        validResult = result != null && result.isValid();
        if (validResult) {
            int id = result.getFiducialResults().get(0).getFiducialId();
            switch (id) {
                case 21:
                    greenMotifPos = 0;
                    break;
                case 22:
                    greenMotifPos = 1;
                    break;
                case 23:
                    greenMotifPos = 2;
                    break;
                default:
                    break;
            }
        }
    }*/






}
