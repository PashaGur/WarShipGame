import java.io.*;
import java.util.*;

public class GameHelper {
    private static final String alphabet = "qwertyu"; // Буквы разметки поля, для удобства взята верхняя строчка клавиатуры
    private int gridLenght = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    private int comCount = 0;

    public String getUserInput(String promt) {
        String inputLine = null;
        System.out.println(promt + " "); // Выводит строку "Сделайте ход"
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            inputLine = reader.readLine();
            if (inputLine.length() == 0) return null;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine.toLowerCase(); // Перевод в нижний регистр, позволяет играть даже при нажатом CapsLock
    }

    public ArrayList<String> placeDotCom(int comSize) {
        ArrayList<String> alphaCells = new ArrayList<>();
        String[] alphacoords = new String[comSize]; // Хранит координаты
        String temp = null; // Временная переменная
        int[] coords = new int[comSize]; // Координаты текущего сайта
        int attempts = 0; // Счетчик попыток
        boolean success = false; // Промах по умолчанию
        int location = 0; // Текущее начальное местоположение

        comCount++; // Счетчик сайтов для размещения
        int incr = 1; // По умолчанию, сайт будет расположен горизонтально
        if ((comCount % 2) == 1) { // Проверка, если сайт по счету нечетный
            incr = gridLenght; // Распологает вертикально нечетный по счету сайт
        }

        while (!success & attempts++ < 200) { // Пока успех не будет "true" и количество попыток менее 200
            location = (int) (Math.random() * gridSize); // Получаем случайную стартовую точку
            int x = 0; // Первая позиция в сайте, который нужно разместить
            success = true; // Успех :)
            while (success && x < comSize) { // Ищем соседнюю неиспользованную ячейку
                if (grid[location] == 0) {  // Если еще не используется
                    coords[x++] = location; // Сохраняем местоположение
                    location += incr; // пробуем следующую "соседнюю" ячейку
                    if (location >= gridSize) { // Если вышли за рамки - вниз
                        success = false; // Неудача
                    }
                    if (x > 0 && (location % gridLenght) == 0) { // Если вышли за рамки - право
                        success = false; // Неудача
                    }
                } else { // Ячейка уже была использована
                    success = false;
                }
            }
        }
        int x = 0; // переводим местоположение в символьные координаты
        int row = 0;
        int column = 0;
        while (x<comSize){
            grid[coords[x]]=1; // Помечаем ячейки на главной сетке как использованные
            row = (int) (coords[x]/gridLenght); // Координаты строки
            column = coords[x] % gridLenght; // Координаты столбца
            temp = String.valueOf(alphabet.charAt(column)); // Преобразуем в строковый символ

            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
        }
        return alphaCells;
    }

}

