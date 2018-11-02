package Board.Obstacles;

/**
 * Obstacle abstract class
 *
 * @author Linas
 */
public abstract class Obstacle {

    //can the obstacle be destroyed?
    private boolean destructable;
    //can the player walk on the obstacle? (most likely to pick up smth)
    private boolean walkable;

    //constructor
    public Obstacle(boolean destructable, boolean walkable) {
        this.destructable = destructable;
        this.walkable = walkable;
    }

    /**
     * getter Checks if the obstacle is destructable (useful for destroying
     * boxes?)
     *
     * @return
     */
    public boolean isDestructable() {
        return destructable;
    }

    /**
     * setter Sets whether obstacle is destructable or not (most likely to be
     * used after bomb explosions and box destruction)
     *
     * @param destructable
     */
    public void setDestructable(boolean destructable) {
        this.destructable = destructable;
    }

    /**
     * getter Checks if the obstacle is destructable or not
     *
     * @return
     */
    public boolean isWalkable() {
        return walkable;
    }

    /**
     * setter Sets whether the obstacle is walkable or not (useful for movement
     * and powerups?)
     *
     * @param walkable
     */
    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

}
