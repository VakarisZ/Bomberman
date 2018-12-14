/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author Arturas
 */
public class PlacetoVisit implements IAccept{
        @Override
        public void Accept(final IVisitor visitor) {
        visitor.Visit(this);
    }
}
