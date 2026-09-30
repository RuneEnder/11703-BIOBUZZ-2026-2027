package org.firstinspires.ftc.teamcode.mechaninisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotStateHandler {
    private HardwareMap hardwareMap;

    public enum DriveState {
        FIELD_CENTRIC,
        HIVE_CENTRIC,
        PASSIVE
    }
}
