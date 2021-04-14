package Lesson_8;

import java.io.IOException;
import java.util.ArrayList;

public interface WeatherModel {

    ArrayList<WeatherResponse> getWeather() throws IOException;

}






