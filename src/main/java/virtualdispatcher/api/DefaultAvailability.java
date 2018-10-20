package virtualdispatcher.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.time.Instant;
import virtualdispatcher.core.serialization.InstantSerializer;

public class DefaultAvailability implements Availability {

  // Properties
  private final int pilotId;
  private final Instant timeCreated;

  @Inject
  DefaultAvailability(
      @Assisted("pilotId") int pilotId,
      @Assisted("timeCreated") Instant timeCreated) {

    this.pilotId = pilotId;
    this.timeCreated = timeCreated;
  }

  /**
   * Gets the pilot ID.
   *
   * @return The pilot ID.
   */
  @Override
  public int getPilotId() {
    return pilotId;
  }

  /**
   * Gets the time when the availability was created.
   *
   * @return The time the availability was created.
   */
  @JsonSerialize(using = InstantSerializer.class)
  @Override
  public Instant getTimeCreated() {
    return timeCreated;
  }
}
