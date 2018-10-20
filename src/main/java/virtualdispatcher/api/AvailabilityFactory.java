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
     * @param pilotId The pilot ID.
     * @param timeCreated The timestamp of time created.
     *
     * @return The {@link Availability}.
     */
    Availability create(
            @Assisted("pilotId") int pilotId,
            @Assisted("timeCreated") Instant timeCreated);
}
