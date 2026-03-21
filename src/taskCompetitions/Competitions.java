package taskCompetitions;

import taskCompetitions.impediments.Treadmill;
import taskCompetitions.impediments.Wall;
import taskCompetitions.participants.Cat;
import taskCompetitions.participants.Human;
import taskCompetitions.participants.Robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Competitions {

    private final List<Object> impediments = new ArrayList<>();
    private final List<Skills> participants = new ArrayList<>();

    public void fillArrays(List<Object> objectList, Object... objects) {
        objectList.addAll(Arrays.asList(objects));
        System.out.println(objectList);
    }
    
    public List<Skills> getParticipants() {
        return participants;
    }

    // Метод для получения свойств препятствий на основе возможностей участника (для демонстрации)
    protected int[] generateObstacleProperties(Skills participant) {
        Random random = new Random();
        Treadmill treadmill = new Treadmill();
        Wall wall = new Wall();
        treadmill.setDistance(random.nextInt(participant.getMaxDistance()));
        int distanceToTreadmill = treadmill.getDistance();
        wall.setHeight(random.nextInt(participant.getMaxHeight()));
        int heightWall = wall.getHeight();
        return new int[]{distanceToTreadmill, heightWall};
    }

    @Override
    public String toString() {
        return "Competitions{" +
                "participants=" + participants +
                '}';
    }

    // Метод для проведения соревнований
    public void runCompetition(List<Skills> participants, List<Object> obstacles) {
        // Преобразуем obstacles в типизированные списки
        List<Treadmill> treadmills = new ArrayList<>();
        List<Wall> walls = new ArrayList<>();
        for (Object obj : obstacles) {
            if (obj instanceof Treadmill) {
                treadmills.add((Treadmill) obj);
            } else if (obj instanceof Wall) {
                walls.add((Wall) obj);
            }
        }
        
        // Проходим по каждому участнику
        for (Skills participant : participants) {
            System.out.println("\nУчастник " + participant.getClass().getSimpleName() + " начинает прохождение:");
            boolean canContinue = true;
            int obstacleIndex = 0;
            
            // Чередуем препятствия: дорожка, стена, дорожка, стена... (пример)
            for (int i = 0; i < obstacles.size() && canContinue; i++) {
                Object obstacle = obstacles.get(i);
                if (obstacle instanceof Treadmill) {
                    Treadmill treadmill = (Treadmill) obstacle;
                    int distance = treadmill.getDistance();
                    if (distance <= participant.getMaxDistance()) {
                        participant.run(distance);
                        System.out.println("  Успешно пробежал " + distance + " м");
                    } else {
                        System.out.println("  Не смог пробежать " + distance + " м (максимум " + participant.getMaxDistance() + " м)");
                        canContinue = false;
                    }
                } else if (obstacle instanceof Wall) {
                    Wall wall = (Wall) obstacle;
                    int height = wall.getHeight();
                    if (height <= participant.getMaxHeight()) {
                        participant.jump(height);
                        System.out.println("  Успешно перепрыгнул " + height + " м");
                    } else {
                        System.out.println("  Не смог перепрыгнуть " + height + " м (максимум " + participant.getMaxHeight() + " м)");
                        canContinue = false;
                    }
                }
                if (!canContinue) {
                    System.out.println("  Участник выбывает.");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Competitions competitions = new Competitions();
        
        // Создаем препятствия
        List<Object> obstacles = new ArrayList<>();
        obstacles.add(new Treadmill(competitions.generateObstacleProperties(new Cat())[0]));   // дорожка 500 м
        obstacles.add(new Wall(competitions.generateObstacleProperties(new Cat())[1]));          // стена 2 м
        obstacles.add(new Treadmill(competitions.generateObstacleProperties(new Human())[0]));  // дорожка 1500 м
        obstacles.add(new Wall(competitions.generateObstacleProperties(new Human())[1]));          // стена 3 м
        obstacles.add(new Treadmill(competitions.generateObstacleProperties(new Robot())[0]));  // дорожка 1500 м
        obstacles.add(new Wall(competitions.generateObstacleProperties(new Robot())[1]));

        // Создаем участников
        List<Skills> participants = new ArrayList<>();
        participants.add(new taskCompetitions.participants.Cat());
        participants.add(new taskCompetitions.participants.Human());
        participants.add(new taskCompetitions.participants.Robot());
        
        // Запускаем соревнование
        competitions.runCompetition(participants, obstacles);
    }
}
