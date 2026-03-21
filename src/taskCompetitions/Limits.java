package taskCompetitions;

public enum Limits {
    LIMITS_FOR_CAT(1000, 3),
    LIMITS_FOR_HUMAN(3000,2),
    LIMITS_FOR_ROBOT(2000, 4);

    private final int maxDistance;
    private final int maxHeight;

    Limits(int maxDistance,int maxHeight) {
        this.maxDistance =maxDistance;
        this.maxHeight=maxHeight;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

}
