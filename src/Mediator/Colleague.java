package Mediator;

/**
 *
 * @author Linas
 */
public abstract class Colleague {

    IMediator mediator;

    /*
    public Colleague(IMediator mediator) {
        this.mediator = mediator;
    }
     */
    public void setMediator(IMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void sendMessage(String message);

    public abstract void receiveMessage(String message);

}
