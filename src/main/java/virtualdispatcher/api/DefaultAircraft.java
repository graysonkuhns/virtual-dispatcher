package virtualdispatcher.api;

import com.google.inject.assistedinject.Assisted;

public class DefaultAircraft implements Aircraft {

    // properties
    private final int aircraftId;
    private boolean operational;

    public DefaultAircraft(
        @Assisted("id") final int aircraftId,
        @Assisted("operational") boolean operational){

        this.aircraftId = aircraftId;
        this.operational = operational;
    }

    @Override
    public int getId() {
      return aircraftId;
    }

    @Override
    public boolean isOperational() {
      return operational;
    }

    public void setOperational(final boolean operational){
        this.operational = operational;
    }
}
