package pl.setblack.mood.alien;

import lfx2.LFX2Library;
import pl.setblack.alienw.AlienSource;

import java.awt.Color;

public interface AlienInterface {
    void prepareColor(AlienSource source, Color color);

    void update();

}
