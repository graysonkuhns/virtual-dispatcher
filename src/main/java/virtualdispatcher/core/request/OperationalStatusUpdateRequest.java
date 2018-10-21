package virtualdispatcher.core.request;

public class OperationalStatusUpdateRequest {
  private boolean operational;

  public void OperationalStatusUpdateRequest(final boolean operational) {
    this.operational = operational;
  }

  public boolean getStatus() {
    return operational;
  }
}
