package pl.setblack.mood.alien;

import pl.setblack.alienw.AlienSource;

import java.awt.*;

/**
 * Created by kanap on 19.12.2016.
 */
public class AlienLightConfig {
    public final AlienSource light;
    public final Color color;

    public AlienLightConfig(AlienSource light, Color color) {
        this.light = light;
        this.color = color;
    }

    @Override
    public String toString() {
        return "AlienLightConfig{" +
                "light=" + light +
                ", color=" + color +
                '}';
    }
}
