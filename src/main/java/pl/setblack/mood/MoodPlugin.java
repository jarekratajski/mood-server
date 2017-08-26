package pl.setblack.mood;

/**
 * Created by kanap on 12.12.2016.
 */
public interface MoodPlugin {
    void startEffect(final String name, long time);

    void stopEffect(final String name);

    void update(long time);
}
