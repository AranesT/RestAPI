import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RunnerTest {

    @Test()
    public void writeMyJson() {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(utils.Configuration.getConfigurationValue("pathToJsonDirectory")));

            Map<String, Object> myJson1 = new HashMap<>();
            myJson1.put("id", 1);
            myJson1.put("name", "Tomato");
            myJson1.put("job", "Eat market");

            Map<String, Object> myJson2 = new HashMap<>();
            myJson2.put("id", 2);
            myJson2.put("name", "Cucumber");
            myJson2.put("job", "Eat market");

            Map<String, Object> myJson0 = new HashMap<>();
            myJson0.put("workers", Arrays.asList(myJson1, myJson2));
            ObjectMapper mapper2 = new ObjectMapper();
            writer.write(mapper2.writeValueAsString(myJson0));
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void readMyJson() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(utils.Configuration.getConfigurationValue("pathToJsonDirectory")));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode parser = objectMapper.readTree(reader);

            for (JsonNode workers: parser.path("workers")){
                System.out.print("id: " + workers.path("id").asLong());
                System.out.print(", name: " + workers.path("name").asText());
                System.out.println(", job: " + workers.path("job").asText() + ";");
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


