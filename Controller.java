package Lesson_8;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    WeatherModel weatherProvider = new AccuWeatherModel();

    public Controller() {
    }

    public void onUserInput(String input) throws IOException {
        int command = Integer.parseInt(input);
        switch (command) {
            case 1:
                getWeatherForecast();
                break;
            case 2:
                readDataBase();
                break;
        }
    }

    public void getWeatherForecast() throws IOException {
        List<WeatherResponse> weatherResponses = weatherProvider.getWeather();
        if ( weatherResponses == null ) {throw new IOException("Произошла ошибка. Не удалось получить результат запроса погоды");}
        DatabaseRepositorySQLiteImpl myDB = ApplicationGlobalState.getInstance().getDataBase();
        if(myDB == null){
            myDB = new DatabaseRepositorySQLiteImpl();
            ApplicationGlobalState.getInstance().setDB(myDB);
        }

        for (WeatherResponse weatherResponse : weatherResponses){
            try {
                myDB.saveWeatherData(weatherResponse);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void readDataBase() throws IOException {
        DatabaseRepositorySQLiteImpl myDB = ApplicationGlobalState.getInstance().getDataBase();
        if(myDB == null){ throw new IOException("К сожалению, база данных пуста"); }
        List<WeatherResponse> DataBaseData = myDB.getAllSavedData();
        for (WeatherResponse weatherResponse : DataBaseData) {
            System.out.println(weatherResponse.toString());
        }
    }
}