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
public class EnchantBoxBlue implements StandartBox {
    
      private final Enchantment enchantment;

  public EnchantBoxBlue(Enchantment enchantment) {
    this.enchantment = enchantment;
  }

  @Override
  public void dropenchantment() { 
    enchantment.onActivate();
  }

  @Override
  public void applyenchantment() {
    enchantment.apply();
  }

  @Override
  public void wearoff() {
    enchantment.onDeactivate();
  }

  @Override
  public Enchantment getEnchantment() {
    return enchantment;
  }
}
