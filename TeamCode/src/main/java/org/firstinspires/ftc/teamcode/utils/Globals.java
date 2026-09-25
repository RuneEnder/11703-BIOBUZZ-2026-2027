package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.utils.commands.AllianceColor;
import org.firstinspires.ftc.teamcode.utils.imu.imuInitializer;
public final class Globals {
    private Globals() {
        throw new UnsupportedOperationException("Globals is a utility class and cannot be instantiated");
    }
    public static Telemetry telemetry;
    public static RobotConstants constants;
    public static imuInitializer imu;
    public static AllianceColor allianceColor = AllianceColor.None;

    public static boolean isTeleOp = true;

    public static void init(Telemetry telemetry) {
        isTeleOp = true;
        constants.build();
        imu.imuinit();
        Globals.telemetry = telemetry;
    }
    public static void setAllianceColor(AllianceColor allianceColor) {
        Globals.allianceColor = allianceColor;
    }
}
