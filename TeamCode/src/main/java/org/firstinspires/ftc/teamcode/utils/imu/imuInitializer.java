package org.firstinspires.ftc.teamcode.utils.imu;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

public class imuInitializer {
    private IMU imu;

    public void imuinit() {
        imu = hardwareMap.get(IMU.class,"imu");

        IMU.Parameters IMUparameters;

        IMUparameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD)
        );

        imu.initialize(IMUparameters);
    }
}
