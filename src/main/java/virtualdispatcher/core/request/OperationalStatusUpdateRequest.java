package virtualdispatcher.core.request;

public class OperationalStatusUpdateRequest {
  private boolean operational;

  public void setOperational(final boolean operational) {
    this.operational = operational;
  }

  public boolean getOperational() {
    return operational;
  }
}
