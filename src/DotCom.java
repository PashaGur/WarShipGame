import java.util.*;

public class DotCom {
    private ArrayList<String> locationCells;  // Лист с ячейками описывающими местоположение
    private String name; // Имя сайта

    public void setLocationCells(ArrayList<String> loc) {  // Сеттер, который обновляет местоположение сайта
        locationCells = loc;
    }

    public void setName(String n) {
        name = n;
    }

    public String checkYourself(String userInput) {
        String result = "Мимо";
        int index = locationCells.indexOf(userInput);
        if (index >= 0) {
            locationCells.remove(index);
            if (locationCells.isEmpty()) {
                result = "Потопил";
                System.out.println("Ой, вы потопили " + name + " :( ");
            } else {
                result = "Попал";
            } // Конец if
        } // Конец if
        return result; // Возвращаем попал или потопил

    } // Конец метода

} // Конец класса