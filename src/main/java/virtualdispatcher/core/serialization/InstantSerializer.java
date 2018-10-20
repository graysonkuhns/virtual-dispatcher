package virtualdispatcher.core.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.Instant;

public class InstantSerializer extends JsonSerializer<Instant> {

  @Override
  public void serialize(
      final Instant instant,
      final JsonGenerator jsonGenerator,
      final SerializerProvider serializerProvider) throws IOException {

    jsonGenerator.writeNumber(instant.toEpochMilli());
  }
}
