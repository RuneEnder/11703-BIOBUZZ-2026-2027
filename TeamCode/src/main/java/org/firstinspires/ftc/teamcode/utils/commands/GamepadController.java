package org.firstinspires.ftc.teamcode.utils.commands;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadController {

    public GamepadController(Gamepad input1, Gamepad input2){
        gamepad1 = input1;
        gamepad2 = input2;
    }


    private final Gamepad gamepad1;
    private final Gamepad gamepad2;

    public boolean setStartPoseToWall() { return gamepad1.dpad_down; }
    public boolean setStartPoseToCorner() { return gamepad1.dpad_up; }
    public boolean setTeamToBlue() { return gamepad1.b; }
    public boolean setTeamToRed() { return gamepad1.x; }


}
