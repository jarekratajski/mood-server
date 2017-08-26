package pl.setblack.mood.alien.config;

import javaslang.Tuple2;
import javaslang.collection.*;
import pl.setblack.alienw.AlienSourceDefinifion;
import pl.setblack.mood.EffectBase;
import pl.setblack.mood.EffectPriority;
import pl.setblack.mood.alien.AlienEffect;
import pl.setblack.mood.alien.AlienEffectStep;

import java.awt.Color;

/**
 * Created by kanap on 21.12.2016.
 */
public class BuildConfigs extends EffectBase{
    private AlienEffect makeError() {
        final AlienEffectStep red = new AlienEffectStep(AlienSourceDefinifion.ALL.getSource(), Color.RED, 500);
        final AlienEffectStep blank = new AlienEffectStep(AlienSourceDefinifion.ALL.getSource(), Color.BLACK, 500);

        final javaslang.collection.List<AlienEffectStep> blink = javaslang.collection.List.of(red, blank);
        return new AlienEffect(blink.appendAll(blink).appendAll(blink).appendAll(blink).appendAll(blink), EffectPriority.HIGH);
    }

    private AlienEffect makeOk() {

        List<AlienEffectStep> effects = List.empty();
        for ( float alphaVal = 0f; alphaVal < 1.0f ; alphaVal+= 0.1f) {
            effects = effects.append(new AlienEffectStep(AlienSourceDefinifion.ALL.getSource(), new Color(0f,1f,0f,alphaVal), 100));
        }
        for ( float alphaVal = 1f; alphaVal > 0.0f ; alphaVal-= 0.1f) {
            effects = effects.append(new AlienEffectStep(AlienSourceDefinifion.ALL.getSource(), new Color(0f,1f,0f,alphaVal), 100));
        }

        return new AlienEffect(effects.appendAll(effects).appendAll(effects).appendAll(effects).appendAll(effects), EffectPriority.MID);
    }


    private AlienEffect makeCompiling() {

        List<AlienEffectStep> effects = List.empty();
        for ( float alphaVal = 0f; alphaVal < 1.0f ; alphaVal+= 0.1f) {
            effects = effects.append(new AlienEffectStep(AlienSourceDefinifion.ALL.getSource(), new Color(0f,0f,1f,alphaVal), 100));
        }
        for ( float alphaVal = 1f; alphaVal > 0.0f ; alphaVal-= 0.1f) {
            effects = effects.append(new AlienEffectStep(AlienSourceDefinifion.ALL.getSource(), new Color(0f,0f,1f,alphaVal), 100));
        }

        return new AlienEffect(effects.appendAll(effects).appendAll(effects).appendAll(effects).appendAll(effects), EffectPriority.LOW);
    }


    public Map<String, AlienEffect> createConfig() {
        return HashMap.ofEntries(
                makeEffect("error", makeError()),
                makeEffect("ok", makeOk()),
                makeEffect("compile", makeCompiling())

        );
    }



}
