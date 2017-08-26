package pl.setblack.mood;

import pl.setblack.alienw.AlienBridge;
import pl.setblack.alienw.AlienSource;
import pl.setblack.mood.alien.AlienInterface;
import pl.setblack.mood.alien.AlienPlugin;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class MoodSystem implements MoodPlugin {

    final List<MoodPlugin> pluginList = Arrays.asList(new AlienPlugin(new AlienInterface() {
        final AlienBridge alien = new AlienBridge();
        @Override
        public void prepareColor(AlienSource source, Color color) {
                this.alien.prepareColor(source, color);
        }

        @Override
        public void update() {
            this.alien.update();
        }
    }));

    @Override
    public void startEffect(String name, long currentTime) {
        pluginList.forEach( plugin -> plugin.startEffect(name, currentTime));

    }

    @Override
    public void stopEffect(String name) {
        pluginList.forEach( plugin -> plugin.stopEffect(name));
    }

    @Override
    public void update(long currentTime) {
        pluginList.forEach( plugin -> plugin.update(currentTime));
    }
}
