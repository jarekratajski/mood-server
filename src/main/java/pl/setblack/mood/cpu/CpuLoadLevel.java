package pl.setblack.mood.cpu;

import javaslang.collection.Array;

import java.awt.Color;

public enum CpuLoadLevel {
    ULTRA(0.95, "cpuUltra", new Color(255,127,127)),
    HI2( 0.7, "cpuHi2", new Color(255,0,0)),
    HI1( 0.7, "cpuHi1", new Color(127,0,0)),
    WORKING( 0.5, "cpuWork", new Color(255,255,0)),
    MID( 0.4, "cpuMid", new Color(0,255,0)),
    NORMAL2( 0.2, "cpuNorm2", new Color(0,127,0)),
    NORMAL1( 0.2, "cpuNorm1", new Color(0,127,127)),
    LIGHT(0.1, "cpuNone", new Color(0,0,255)),
    NONE(0.0, "cpuLight", new Color(0,0,127));



    private final double threshold;
    public final String name;
    public final Color color;
    CpuLoadLevel(double threshold, String name, Color color) {
        this.threshold = threshold;
        this.name = name;
        this.color = color;
    }

    public static CpuLoadLevel getLevel (final double currentLevel ) {
        return Array.of(CpuLoadLevel.values()).find( val -> val.threshold < currentLevel).getOrElse(LIGHT);
    }

}
