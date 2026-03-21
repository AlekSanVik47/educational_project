package taskCompetitions.participants;

import taskCompetitions.Limits;
import taskCompetitions.Skills;

import java.util.Random;

public class Cat implements Skills {

    Random rnd;

    public Cat() {
        rnd = new Random();
    }

    public int getDistance(){
        return  rnd.nextInt(Limits.LIMITS_FOR_CAT.getMaxDistance());
    }
     public int getHeight () {
        return rnd.nextInt(Limits.LIMITS_FOR_CAT.getMaxHeight());
     }
     
    @Override
    public int getMaxDistance() {
        return Limits.LIMITS_FOR_CAT.getMaxDistance();
    }
    
    @Override
    public int getMaxHeight() {
        return Limits.LIMITS_FOR_CAT.getMaxHeight();
    }
     
    @Override
    public void run(int distanceRun) {
    System.out.println("Кот пробежал " + distanceRun +" м");
    }

    @Override
    public void jump(int heightJumpedUp) {
        System.out.println("Kот прыгнул на высоту "+ heightJumpedUp + " м");
    }

    static void main(String[] args) {
        Cat cat=new Cat();
        cat.jump(cat.getHeight());
        cat.run(cat.getDistance());
    }

    @Override
    public String toString() {
        return "Cat{}";
    }
}
