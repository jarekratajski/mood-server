package pl.setblack.mood.cpu;

import java.lang.management.ManagementFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sun.management.OperatingSystemMXBean;
import javaslang.Lazy;
import pl.setblack.mood.MoodSystem;

public class CpuLoad {

    public static void main(final String ...  args) throws InterruptedException {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        while (true ) {
            System.out.println(operatingSystemMXBean.getSystemCpuLoad());
            Thread.sleep(500);

        }
    }


    public void  monitor( Lazy<MoodSystem> sys) {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            final double currentLevel = operatingSystemMXBean.getSystemCpuLoad();

            final CpuLoadLevel level = CpuLoadLevel.getLevel(currentLevel);
            sys.get().startEffect(level.name, System.currentTimeMillis());
        }, 0, CpuConfigs.DURATION, TimeUnit.MILLISECONDS);

    }



}
