package pl.setblack.mood.alien.config;

import javaslang.Tuple2;
import javaslang.collection.HashMap;
import javaslang.collection.List;
import javaslang.collection.Map;
import pl.setblack.alienw.AlienSourceDefinifion;
import pl.setblack.mood.EffectPriority;
import pl.setblack.mood.alien.AlienEffect;
import pl.setblack.mood.alien.AlienEffectStep;
import pl.setblack.mood.cpu.CpuConfigs;

import java.awt.*;

/**
 * Created by kanap on 12.12.2016.
 */
public class AlienConfig {
    public Map<String,AlienEffect> createConfig() {
        Map<String,AlienEffect> builds = new BuildConfigs().createConfig();
        Map<String,AlienEffect> cpu = new CpuConfigs().createConfig();
        return builds.merge(cpu);

    }
}
