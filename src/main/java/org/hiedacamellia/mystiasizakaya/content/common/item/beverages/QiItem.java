package org.hiedacamellia.mystiasizakaya.content.common.item.beverages;

import net.minecraft.world.item.Rarity;
import org.hiedacamellia.mystiasizakaya.content.common.item.items.Beverages;

public class QiItem extends Beverages {
	public QiItem() {
		super(4, 0.8f, Rarity.COMMON, "qi",
				new String[]{"low_alcohol", "chillable", "sake", "cocktail", "soda", "sweet", "dry", "bitter"});
	}
}
