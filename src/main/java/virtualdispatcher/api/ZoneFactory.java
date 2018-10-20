package virtualdispatcher.api;

import com.google.inject.assistedinject.Assisted;

/**
 * {@link Zone} factory.
 *
 * @author Grayson Kuhns
 */
public interface ZoneFactory {

    /**
     * Creates a {@link Zone}.
     *
     * @param id The zone ID
     *
     * @return The {@link Zone}.
     */
    Zone create(@Assisted("id") int id);

}
