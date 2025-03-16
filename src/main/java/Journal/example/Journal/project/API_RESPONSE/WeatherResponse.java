package Journal.example.Journal.project.API_RESPONSE;
//import com.fasterxml..jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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

//        public int getTemperature() {
//            return temperature;
//        }
//
//        public void setTemperature(int temperature) {
//            this.temperature = temperature;
//        }

        @Getter
        @JsonProperty("temperature")
        private int temperature;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        @JsonProperty("feelslike")
        private int feelslike;


    }
}








