package virtualdispatcher;

import com.google.inject.Guice;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

public class VirtualDispatcherApplication extends Application<VirtualDispatcherConfiguration> {

    public static void main(final String[] args) throws Exception {
        new VirtualDispatcherApplication().run(args);
    }

    @Override
    public String getName() {
        return "VirtualDispatcher";
    }

    @Override
    public void initialize(final Bootstrap<VirtualDispatcherConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "flightStatus.html"));
    }

    @Override
    public void run(final VirtualDispatcherConfiguration configuration,
                    final Environment environment) {
        // Serve API 
        environment.jersey().setUrlPattern("/api/*");

        Guice
            .createInjector(new ApplicationModule(environment, configuration))
            .getInstance(ApplicationRunner.class)
            .run(environment, configuration);
    }
}
