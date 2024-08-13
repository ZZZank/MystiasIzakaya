
package org.hiedacamellia.mystiasizakaya.content.common.item.utils;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.hiedacamellia.mystiasizakaya.core.entry.BaseItem;

public class LingXianItem extends BaseItem {
	public LingXianItem() {
		super(new Properties(),64, 1, 0.8f, Rarity.EPIC, UseAnim.NONE, 0,
				"ling_xian", new String[]{"Photogenic","Signature","Specialty","Wonderful"}, new String[]{});
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}

}