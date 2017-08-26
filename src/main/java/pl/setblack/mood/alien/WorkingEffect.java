package pl.setblack.mood.alien;

import javaslang.control.Option;

/**
 * Created by kanap on 12.12.2016.
 */
public class WorkingEffect {
    public final long startTime;
    public final AlienEffect effect;

    public WorkingEffect(long startTime, AlienEffect effect) {
        this.startTime = startTime;
        this.effect = effect;
    }

    public WorkingEffect withNewTime(long time) {
        return new WorkingEffect(time, this.effect);
    }

    Option<AlienEffectStep>  getActiveStep(final long time) {
        final long distance = time - startTime;
        return effect.getActiveStep(distance);
    }
}
