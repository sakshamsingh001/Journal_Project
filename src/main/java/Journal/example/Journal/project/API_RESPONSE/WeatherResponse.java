package Journal.example.Journal.project.API_RESPONSE;
//import com.fasterxml..jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WeatherResponse {

    @JsonProperty("current") // Ensure the mapping is correct
    private Current current;

    // Getter and Setter
    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    // Make this class static or move it outside
    public static class Current {
        @JsonProperty("observation_time")
        private String observationTime;

        @JsonProperty("temperature")
        private int temperature;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        @JsonProperty("feelslike")
        private int feelslike;


    }
}








