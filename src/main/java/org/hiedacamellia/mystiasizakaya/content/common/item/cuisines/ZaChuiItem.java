
package org.hiedacamellia.mystiasizakaya.content.common.item.cuisines;

import net.minecraft.world.item.Rarity;
import org.hiedacamellia.mystiasizakaya.content.common.item.items.Cuisines;

public class ZaChuiItem extends Cuisines {
	public ZaChuiItem() {
		super(6, 1.2f, Rarity.UNCOMMON, "za_chui",
				new String[]{"Meat", "Homecooking", "Fresh", "Hot"},
				new String[]{},60);
	}
}