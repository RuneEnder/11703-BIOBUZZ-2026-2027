package org.firstinspires.ftc.teamcode.utils.commands;

public enum AllianceColor {
    None(10),
    Blue(10),
    Red(14);
    final int tagID;
    AllianceColor(int tagID) {
        this.tagID = tagID;
    }
    public int getTagID() {
        return tagID;
    }

    }
