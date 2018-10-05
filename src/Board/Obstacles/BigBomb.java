/*

gal reiktų konstruktoriuje tiesiog naudoti konstantas???
ar konstruktoriui paduoti reikšmes iš Bomb Factory???

 */
package Board.Obstacles;

/**
 * Big Bomb class
 * @author Linas
 */
public class BigBomb extends Bomb{
    
    public BigBomb(boolean destructable, boolean walkable, int explosionRadius, float explosionTimer) {
        super(destructable, walkable, explosionRadius, explosionTimer);
    }
    
}
