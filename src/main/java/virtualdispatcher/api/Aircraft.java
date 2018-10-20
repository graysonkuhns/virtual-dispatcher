package virtualdispatcher.api;

/**
 * Aircraft model.
 *
 * @author Grayson Kuhns
 */
public interface Aircraft {

  /**
   * Gets the ID.
   *
   * @return The ID.
   */
  int getId();

  /**
   * Checks if the aircraft is operational.
   *
   * @return True if the aircraft is operational.
   */
  boolean isOperational();
}
