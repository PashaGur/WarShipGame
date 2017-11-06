import java.util.*;

public class DotComBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        // Создадим несколько "сайтов" и присвоим им адреса
        DotCom one = new DotCom();
        one.setName("javarush.ru");
        DotCom two = new DotCom();
        two.setName("billing.ru");
        DotCom three = new DotCom();
        three.setName("github.com");
        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);

        System.out.println("Ваша цель - потопить три сайта.");
        System.out.println("Чтобы отбить расходы на рекламу, само собой.");
        System.out.println("Попытайтесь потопить сайты за минимальное количество ходов.");

        for (DotCom dotComToSet : dotComsList) { // Пробегаемся по объектам DotCom в списке dotComList
            ArrayList<String> newLocation = helper.placeDotCom(3);
            dotComToSet.setLocationCells(newLocation); /* Вызываем сеттер из объекта DotCom, чтобы передать ему
            местоположение, которое только что получили от вспомогательного объекта
             */
        } // Конец цикла
    } // Конец метода setUpGame

    private void startPlaying() {
        while (!dotComsList.isEmpty()) { // Игра идет, пока есть сайты в dotComList

            String userGuess = helper.getUserInput("Сделайте ход");
            checkUserGuess(userGuess); // Вызываем метод chechUserGuess
        } // Конец while
        finishGame(); // Когда while станет пустым, запустит метод завершения игры
    } // Конец метода startPlaying

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "Мимо"; // Промах по умолчанию, если не доказано обратного
        for (DotCom dotComToTest : dotComsList) { // Проходимся по объектам DotCom
            result = dotComToTest.checkYourself(userGuess);
            if (result.equals("Попал")) {
                break;
            }
            if (result.equals("Потопил")) {
                dotComsList.remove(dotComToTest);
                break;
            }
        } // Конец for
        System.out.println(result);
    } // Конец метода

    private void finishGame() {
        System.out.println("Поздравляем, все сайты ушли по дну.");
        if (numOfGuesses <= 18) {
            System.out.println("Это заняло у вас всего " + numOfGuesses + " попыток.");
            System.out.println("Вы успели отбить затраты на рекламу.");
        } else {
            System.out.println("Однако, это заняло у вас довольно много времени. Целых " + numOfGuesses + " попыток.");
            System.out.println("Рыбы водят хороводы вокруг ваших вложений.");
        }
    } // Конец метода

    public static void main(String[] args) {
        DotComBust game = new DotComBust(); // Создаем игровой объект
        game.setUpGame(); // Говорим объекту подготовить игру
        game.startPlaying();
    } // Конец метода
}
