package Lesson_8;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DataBaseRepository {

    boolean saveWeatherData(WeatherResponse weatherResponse) throws SQLException;

    List<WeatherResponse> getAllSavedData() throws IOException;
}