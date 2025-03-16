package Journal.example.Journal.project.Service;

import Journal.example.Journal.project.API_RESPONSE.WeatherResponse;
import Journal.example.Journal.project.Cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@Component
public class Weather_Service {
    @Value("$weather.api.key")
    private String APIkey;
//    private static final String API="https://api.weatherstack.com/current?access_key=API_KEY_key&query=CITY" ;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city) {
        System.out.println(appCache.appcache.get("weather_api"));
        String finalAPI = appCache.appcache.get("weather_api").replace("CITY", city).replace("API_KEY", APIkey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        System.out.println("this change is nothing");
        System.out.println("this is " + APIkey);
        return body;
    }


}
