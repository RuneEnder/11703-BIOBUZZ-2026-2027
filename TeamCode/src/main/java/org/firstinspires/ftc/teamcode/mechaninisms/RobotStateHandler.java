package org.firstinspires.ftc.teamcode.mechaninisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotStateHandler {
    private HardwareMap hardwareMap;

    public enum DriveState {
        FEILD_CENTRIC,
        HIVE_CENTRIC,
        PASSIVE
    }
}
