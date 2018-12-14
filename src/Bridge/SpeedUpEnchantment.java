/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bridge;

/**
 *
 * @author Arturas
 */
public class SpeedUpEnchantment implements Enchantment {
    
  @Override
  public void onActivate() {
  //  LOGGER.info("enchanment gives speed.");
  }

  @Override
  public void apply() {
   // LOGGER.info("speed up.");
  }

  @Override
  public void onDeactivate() {
  //  LOGGER.info("speed buff dissappears.");
  }
}
