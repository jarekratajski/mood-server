package pl.setblack.mood.alien;

import javaslang.collection.*;
import pl.setblack.alienw.AlienSource;

import java.awt.*;

/**
 * Created by kanap on 12.12.2016.
 */
public class AlienEffectStep {
    public final Seq<AlienLightConfig> lights;
    public final long duration;

    public AlienEffectStep(Seq<AlienLightConfig> lights, long duration) {
        this.lights = lights;
        this.duration = duration;
    }

    public AlienEffectStep(AlienSource source, Color color, long duration) {
        this(javaslang.collection.List.of(new AlienLightConfig(source, color)),duration);
    }

    @Override
    public String toString() {
        return "AlienEffectStep{" +
                "lights=" + lights +
                ", duration=" + duration +
                '}';
    }
}
