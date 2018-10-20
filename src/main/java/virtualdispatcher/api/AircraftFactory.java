package virtualdispatcher.api;

import com.google.inject.assistedinject.Assisted;

/**
 * {@link Aircraft} factory.
 *
 * @author Grayson Kuhns
 */
public interface AircraftFactory {

    /**
     * Creates a {@link Aircraft}.
     *
     * @param id The ID.
     * @param operational True if the Aircraft is operational.
     *
     * @return The {@link Aircraft}.
     */
    Aircraft create(
            @Assisted("id") int id,
            @Assisted("operational") boolean operational);
}
