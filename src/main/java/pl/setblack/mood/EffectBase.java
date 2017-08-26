package pl.setblack.mood;

import javaslang.Tuple2;
import pl.setblack.mood.alien.AlienEffect;

public abstract class EffectBase {
    protected Tuple2<String, AlienEffect> makeEffect(final String name, AlienEffect eff) {
        return new Tuple2(name, eff);
    }
}
