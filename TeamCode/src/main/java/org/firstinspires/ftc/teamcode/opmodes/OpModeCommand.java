package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.pedropathing.ivy.Scheduler;

import org.firstinspires.ftc.teamcode.utils.Globals;

public abstract class OpModeCommand extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException{
        preInit();
        Globals.init_TeleOp(telemetry);
        initialize();
    }
    public void reset() {
        //Scheduler.getInstance().reset();
    }
    public abstract void initialize();
    public void preInit() {}


}
