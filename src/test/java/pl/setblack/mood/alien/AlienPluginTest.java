package pl.setblack.mood.alien;


import javaslang.collection.Map;
import org.junit.jupiter.api.Test;
import pl.setblack.alienw.AlienSource;
import pl.setblack.mood.cpu.CpuConfigs;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

import static org.mockito.Matchers.any;

class AlienPluginTest {
    private AlienSource source;
    private Color color;

    @Test
    public void testColor() {
        AlienInterface alienBridge = new AlienInterface() {
            private AlienSource source;
            private Color color;

            @Override
            public void prepareColor(AlienSource source, Color color) {
                this.source = source;
                this.color = color;
            }

            @Override
            public void update() {
                AlienPluginTest.this.color = this.color;
                AlienPluginTest.this.source = this.source;
            }
        };



        final Map<String, AlienEffect> confs = new CpuConfigs().createConfig();
        final AlienPlugin alien = new AlienPlugin(confs, alienBridge);

        alien.startEffect("cpuLight", 0);
        alien.update(500);
        alien.update(1000);

        alien.startEffect("cpuLight", 1500);
        alien.update(1500);
        alien.update(2000);
        alien.startEffect("cpuLight", 2000);
        alien.update(2500);
        alien.update(3000);
        alien.startEffect("cpuLight", 3000);
        alien.update(3500);
        assertTrue( color == Color.BLUE);

    }
}