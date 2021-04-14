package Lesson_8;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private final Controller controller = new Controller();

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите название города на русском языке");
            String city = scanner.nextLine();

            setGlobalCity(city);

            System.out.println("Выберите опцию:\n 1 - Получить погоду на текущие 5 дней в городе " + city +
                    "\n 2 - Вывести результаты прошлых запросов из базы данных" +
                    "\n выход (exit) - завершить работу\n");

            String result = scanner.nextLine();

            checkIsExit(result);

            try {
                validateUserInput(result);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            try {
                notifyController(result);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void checkIsExit(String result) {
        if (result.toLowerCase().equals("выход") || result.toLowerCase().equals("exit")) {
            System.out.println("Сеанс завершен");
            DatabaseRepositorySQLiteImpl myDataBase = ApplicationGlobalState.getInstance().getDataBase();
            if(myDataBase != null) myDataBase.finalizeDataBase();
            System.exit(0);
        }
    }

    private void setGlobalCity(String city) {
        ApplicationGlobalState.getInstance().setSelectedCity(city);
    }


    private void validateUserInput(String userInput) throws IOException {
        if (userInput == null || userInput.length() != 1) {
            throw new IOException("Ввод выполнен некорректно: введите 1 или 2");
        }
        int answer = 0;
        try {
            answer = Integer.parseInt(userInput);
            if(answer != 1 && answer != 2) throw new IOException("Ввод выполнен некорректно: введите 1 или 2 для правильной обработки программы");
        } catch (NumberFormatException e) {
            throw new IOException("Ввод выполнен некорректно: необходимо ввести цифры");
        }
    }

    private void notifyController(String input) throws IOException {
        controller.onUserInput(input);
    }
}