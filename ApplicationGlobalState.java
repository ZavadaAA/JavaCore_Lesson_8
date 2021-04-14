package Lesson_8;

public final class ApplicationGlobalState {

    private static ApplicationGlobalState INSTANCE;
    private String selectedCity = null;
    private final String API_KEY = "bY9sFyXGO5fB2bwx9ueDnA6mmqcow3Hn";
    private final String DataBase_FILENAME = "weather.database";
    private DatabaseRepositorySQLiteImpl myDataBase = null;

    private ApplicationGlobalState() {
    }

    public static ApplicationGlobalState getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ApplicationGlobalState();
        }
        return INSTANCE;
    }

    //public String getSelectedCity() {
    //    return selectedCity;
    //}

    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }

   // public String getApiKey() {
     //   return this.API_KEY;
    //}

    public String getDataBaseFilename() {
        return this.DataBase_FILENAME;
    }

    public DatabaseRepositorySQLiteImpl getDataBase() {
        return this.myDataBase;
    }

    public void setDB(DatabaseRepositorySQLiteImpl myDataBase) {
        this.myDataBase = myDataBase;
    }
}