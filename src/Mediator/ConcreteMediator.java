/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mediator;

import java.util.ArrayList;

/**
 *
 * @author Linas
 */
public class ConcreteMediator implements IMediator {

    ArrayList<Colleague> colleagues = new ArrayList<Colleague>();

    @Override
    public void addColleague(Colleague col) {
        colleagues.add(col);
    }

    @Override
    public void broadcast(Colleague col, String message) {

        System.out.println("mediator received message from:" + message);

        for (Colleague c : colleagues) {
            if (c != col) {
                c.receiveMessage(message);
            }
        }

    }

    @Override
    public void broadcast(String message) {
        System.out.println("mediator received message from:" + message);

        for (Colleague c : colleagues) {
            c.receiveMessage(message);
        }
    }
}

