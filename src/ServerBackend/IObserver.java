/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBackend;

/**
 *
 * @author mati
 */
public interface IObserver {
    public void Update(IObservable o);
    public void Notify(String clientString, int req_dx, int req_dy);
    public void NotifyAll(String clientString);
    
}
