package virtualdispatcher.core.request;

public class DeleteAvailabilityRequest {
  private int pilotId;

  public void setPilotId(final int pilotId) {
    this.pilotId = pilotId;
  }

  public int getPilotId() {
    return pilotId;
  }
}
