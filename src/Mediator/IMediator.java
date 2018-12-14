
package Mediator;

/**
 *
 * @author Linas
 */
public interface IMediator {
    public void addColleague( Colleague col);
    public void broadcast( Colleague col, String message);
    public void broadcast(String message);
}
