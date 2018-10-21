package virtualdispatcher;

import com.google.inject.Guice;
import io.dropwizard.Application;
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
        // TODO: application initialization
    }

    @Override
    public void run(final VirtualDispatcherConfiguration configuration,
                    final Environment environment) {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
            environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        Guice
            .createInjector(new ApplicationModule(environment, configuration))
            .getInstance(ApplicationRunner.class)
            .run(environment, configuration);
    }

}
