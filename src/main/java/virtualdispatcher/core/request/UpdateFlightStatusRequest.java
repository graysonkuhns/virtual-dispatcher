package virtualdispatcher.core.request;

public class UpdateFlightStatusRequest {
  private Boolean started;
  private Boolean completed;

  public Boolean getStarted() {
    return started;
  }

  public void setStarted(final Boolean started) {
    this.started = started;
  }

  public Boolean getCompleted() {
    return completed;
  }

  public void setCompleted(final Boolean completed) {
    this.completed = completed;
  }
}
