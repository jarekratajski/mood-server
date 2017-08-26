package pl.setblack.mood.alien;

import javaslang.Tuple2;
import javaslang.collection.List;
import javaslang.control.Option;
import pl.setblack.mood.EffectPriority;

/**
 * Created by kanap on 12.12.2016.
 */
public class AlienEffect {
    public final List<AlienEffectStep> steps;

    public final EffectPriority priority;

    public AlienEffect(final List<AlienEffectStep> steps, final EffectPriority priority) {
        this.steps = steps;
        this.priority = priority;
    }

    public Option<AlienEffectStep> getActiveStep(final long distance) {
        final Tuple2<Long, Option<AlienEffectStep>> result =
                steps.foldLeft( new Tuple2<Long, Option<AlienEffectStep>>(distance, Option.<AlienEffectStep>none() ),
                (tuple, effect) -> {
                    final long newDistance = tuple._1 -  effect.duration;
                    if ( newDistance < 0) {
                        return new Tuple2(newDistance, tuple._2.orElse(Option.of(effect)));
                    } else {
                        return new Tuple2( newDistance, Option.<AlienEffectStep>none());
                    }
                }
                );
        return result._2;
    }
}
