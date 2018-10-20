package virtualdispatcher.api;

import com.google.inject.assistedinject.Assisted;

import java.time.Instant;

/**
 * {@link Availability} factory.
 *
 * @author Grayson Kuhns
 */
public interface AvailabilityFactory {

    /**
     * Creates a {@link Availability}.
     *
     * @param created The timestamp of time created.
     * @param pilotId The pilot ID.
     *
     * @return The {@link Availability}.
     */
    Availability create(
            @Assisted("created") Instant created,
            @Assisted("pilotId") int pilotId);
}
