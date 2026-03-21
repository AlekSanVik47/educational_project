package taskCompetitions.participants;

import taskCompetitions.Limits;
import taskCompetitions.Skills;

import java.util.Random;

public class Human implements Skills {
    Random rnd;

    public Human() {
        rnd = new Random();
    }

    public int getDistance(){
        return  rnd.nextInt(Limits.LIMITS_FOR_HUMAN.getMaxDistance());
    }
    public int getHeight () {
        return rnd.nextInt(Limits.LIMITS_FOR_HUMAN.getMaxHeight());
    }
    
    @Override
    public int getMaxDistance() {
        return Limits.LIMITS_FOR_HUMAN.getMaxDistance();
    }
    
    @Override
    public int getMaxHeight() {
        return Limits.LIMITS_FOR_HUMAN.getMaxHeight();
    }
    
    @Override
    public void run(int distanceRun) {
        System.out.println("Человек пробежал " + distanceRun +" м");
    }

    @Override
    public void jump(int heightJumpedUp) {
        System.out.println("Человек прыгнул на высоту "+ heightJumpedUp + " м");
    }

    @Override
    public String toString() {
        return "Human{}";
    }
}
