package virtualdispatcher.api;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ApiModule extends AbstractModule {

  /**
   * Configures the model.
   */
  @Override
  protected void configure() {
    // Flight factory
    install(new FactoryModuleBuilder()
      .implement(Flight.class, DefaultFlight.class)
      .build(FlightFactory.class));

    install(new FactoryModuleBuilder()
            .implement(Aircraft.class, DefaultAircraft.class)
            .build(AircraftFactory.class));

    install(new FactoryModuleBuilder()
            .implement(Pilot.class, DefaultPilot.class)
            .build(PilotFactory.class));

    install(new FactoryModuleBuilder()
            .implement(Zone.class, DefaultZone.class)
            .build(ZoneFactory.class));

    install(new FactoryModuleBuilder()
      .implement(Availability.class, DefaultAvailability.class)
      .build(AvailabilityFactory.class));
  }
}
