package pl.setblack.mood;

import javaslang.Lazy;
import pl.setblack.mood.cpu.CpuLoad;
import pl.setblack.mood.tray.TrayMenu;
import ratpack.handling.Chain;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;

import java.util.Timer;
import java.util.TimerTask;

/**
 * /mood/:id/start
 * /mood/:id/end
 */
public class Server {

    private final Lazy<MoodSystem> sys = Lazy.of(() -> new MoodSystem());

    private final Timer timer = new Timer();


    private RatpackServer ratpackServer;


    public static void main(String... args) throws Exception {

        final Server srv = new Server();
        srv.run();

        new TrayMenu().createAndShowGUI();;


    }

    private void run() throws Exception {
        final CpuLoad cpuLoad = new CpuLoad();
        cpuLoad.monitor(sys);

        ratpackServer = RatpackServer.start(server -> server
                .serverConfig(cfg -> cfg.port(44718))
                .handlers(chain -> chain
                        .prefix("mood", this::defineActions)
                )
        );
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sys.get().update(System.currentTimeMillis());
            }
        }, 1000, 100);
    }

    private void stop() throws Exception {
        this.ratpackServer.stop();
        this.timer.cancel();
    }

    private void defineActions(Chain chain) {
        chain.get("/:id/start", ctx -> {
            final String id = ctx.getPathTokens().get("id");
            System.out.println("starting " + id);
            sys.get().startEffect(id, System.currentTimeMillis());
            ctx.render("ok");
        }).get("/:id/stop", ctx -> {
            final String id = ctx.getPathTokens().get("id");
            System.out.println("stoping " + id);
            sys.get().stopEffect(id);
            ctx.render("ok");
        });
    }

}
