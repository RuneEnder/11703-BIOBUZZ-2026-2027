package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotVars;
//import com.pedropathing.ivy.Scheduler;


public abstract class OpModeCommand extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException{
        preInit();
        RobotVars robotVars = new RobotVars();
        robotVars.init_TeleOp(telemetry);
        initialize();
    }
    public void reset() {
        //Scheduler.getInstance().reset();
    }
    public abstract void initialize();
    public void preInit() {}


}
