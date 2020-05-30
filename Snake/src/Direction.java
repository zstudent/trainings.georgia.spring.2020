public enum Direction {
    UP   (1),
    DOWN (2),
    LEFT (3),
    RIGHT(4);
    private final int directionCode;
    private Direction(int directionCode) {
        this.directionCode = directionCode;
    }
}