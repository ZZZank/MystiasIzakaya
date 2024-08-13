
package org.hiedacamellia.mystiasizakaya.content.common.item.cuisines;

import net.minecraft.world.item.Rarity;
import org.hiedacamellia.mystiasizakaya.content.common.item.items.Cuisines;

public class MaoFanItem extends Cuisines {
	public MaoFanItem() {
		super(6, 1.2f, Rarity.UNCOMMON, "mao_fan",
				new String[]{"Aquatic", "Fresh", "Small_Portion"},
				new String[]{"Greasy"},60);
	}
}