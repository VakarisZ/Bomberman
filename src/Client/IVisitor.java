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
public interface IVisitor {
    void Visit(PlacetoVisit placetovisit);
    void Visit(AnotherPlaceToVisit anotherplacetovisit);
}
