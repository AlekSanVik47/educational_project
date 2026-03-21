package taskCompetitions.participants;

import taskCompetitions.Limits;
import taskCompetitions.Skills;

import java.util.Random;

public class Robot implements Skills {
    Random rnd;

    public Robot() {
        rnd = new Random();
    }

    public int getDistance(){
        return  rnd.nextInt(Limits.LIMITS_FOR_ROBOT.getMaxDistance());
    }
    public int getHeight () {
        return rnd.nextInt(Limits.LIMITS_FOR_ROBOT.getMaxHeight());
    }

    @Override
    public int getMaxDistance() {
        return Limits.LIMITS_FOR_ROBOT.getMaxDistance();
    }

    @Override
    public int getMaxHeight() {
        return Limits.LIMITS_FOR_ROBOT.getMaxHeight();
    }

    @Override
    public void run(int distanceRun) {
        System.out.println("Робот пробежал " + distanceRun +" м");
    }

    @Override
    public void jump(int heightJumpedUp) {
        System.out.println("Робот прыгнул на высоту "+ heightJumpedUp + " м");
    }

    @Override
    public String toString() {
        return "Robot{}";
    }
}
