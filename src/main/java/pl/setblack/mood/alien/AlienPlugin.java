package pl.setblack.mood.alien;

import javaslang.Tuple2;
import javaslang.collection.HashMap;
import javaslang.collection.List;
import javaslang.control.Option;
import pl.setblack.alienw.AlienSourceDefinifion;
import pl.setblack.mood.MoodPlugin;
import javaslang.collection.Map;
import pl.setblack.mood.alien.config.AlienConfig;

import java.awt.Color;


/**
 * Created by kanap on 12.12.2016.
 */
public class AlienPlugin implements MoodPlugin {

    private final Map<String, AlienEffect> definitions;

    private  Map<String, WorkingEffect> currentEffects = HashMap.empty();

    private final AlienEffectStep blank = new AlienEffectStep(
            AlienSourceDefinifion.ALL.getSource(),
            Color.BLACK,
            0);

    private  final AlienInterface alien;

    public AlienPlugin(Map<String, AlienEffect> definitions, AlienInterface alienBridge) {
        this.definitions = definitions;
        this.alien = alienBridge;
    }

    public AlienPlugin( AlienInterface alienBridge) {
        this( new AlienConfig().createConfig(), alienBridge);
    }

    @Override
    public synchronized void  startEffect(String name, long time) {

        currentEffects = currentEffects.put(name,
                currentEffects
                        .get(name)
                        .map(eff -> eff.withNewTime(time))
                        .getOrElse(
                                () -> new WorkingEffect(time, definitions.get(name).getOrElseThrow(() -> new IllegalArgumentException(name)))
                        ));
        update(time);
    }

    @Override
    public synchronized void stopEffect(String name) {
        currentEffects = currentEffects.remove(name);
    }

    @Override
    public synchronized void update(long time) {
        removeEndedEffects(time);

        if ( currentEffects.values()
                .map(we -> new Tuple2<>(we.getActiveStep(time), we.effect.priority))
                .filter(t -> !t._1.isDefined()).size() > 0 ) {
            System.out.println("Jaja!");
        }



        updateAW(currentEffects.values()
                .map(we -> new Tuple2<>(we.getActiveStep(time), we.effect.priority))
                .filter(t -> t._1.isDefined())
                .sortBy(t -> t._2.ordinal())
                .getOption()
                .flatMap(t -> t._1));

    }

    private void removeEndedEffects(long time) {
        final List<String> ended = currentEffects.
                 toList()
                .filter( we ->we._2.getActiveStep(time).isEmpty())
                .map( we -> we._1);

        currentEffects = currentEffects.filter( we ->!we._2.getActiveStep(time).isEmpty());

    }

    private void updateAW(Option<AlienEffectStep> alienEffectStep) {
        this.updateAW(alienEffectStep.getOrElse(this.blank));
    }

    private void updateAW(final AlienEffectStep alienEffectStep) {
        alienEffectStep.lights.forEach( cfg ->    alien.prepareColor(cfg.light, cfg.color));
        alien.update();
    }

}
