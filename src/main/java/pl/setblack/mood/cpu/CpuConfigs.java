package pl.setblack.mood.cpu;

import javaslang.collection.Array;
import javaslang.collection.HashMap;
import javaslang.collection.Map;
import pl.setblack.alienw.AlienSourceDefinifion;
import pl.setblack.mood.EffectBase;
import pl.setblack.mood.EffectPriority;
import pl.setblack.mood.alien.AlienEffect;
import pl.setblack.mood.alien.AlienEffectStep;

import java.awt.Color;

public class CpuConfigs extends EffectBase {

    public static final long DURATION = 1000;




    private AlienEffect makeForColor(Color color ) {
        final AlienEffectStep step = new AlienEffectStep(AlienSourceDefinifion.ALL_LOWER.getSource(),color, DURATION);
        final javaslang.collection.List<AlienEffectStep>  eff= javaslang.collection.List.of(step);
        return new AlienEffect(eff, EffectPriority.TRIVIAL);
    }

    public Map<String, AlienEffect> createConfig() {
        return Array.of(CpuLoadLevel.values())
                .map( definition -> makeEffect(definition.name, makeForColor(definition.color)))
                .toMap( definition -> definition);
    }

}
