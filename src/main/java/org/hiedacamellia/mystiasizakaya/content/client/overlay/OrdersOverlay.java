
package org.hiedacamellia.mystiasizakaya.content.client.overlay;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import org.hiedacamellia.mystiasizakaya.content.orders.GetBeveragesTexture;
import org.hiedacamellia.mystiasizakaya.content.orders.GetCuisinesTexture;

@EventBusSubscriber({ Dist.CLIENT })
public class OrdersOverlay {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int h = event.getGuiGraphics().guiHeight();
		Player entity = Minecraft.getInstance().player;
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		RenderSystem.setShaderColor(1, 1, 1, 1);
		ItemStack cuisines;
		ItemStack beverages;
		int reali = 0;
		for (int i = 0; i < 7; i++) {
			cuisines = GetCuisinesTexture.execute(i, entity);
			beverages = GetBeveragesTexture.execute(i, entity);
			if (!cuisines.isEmpty() || !beverages.isEmpty()) {
				event.getGuiGraphics().blit(ResourceLocation.parse("mystias_izakaya:textures/overlay/page.png"),
						0 + reali * 34,
						h - 32, 0, 0, 36, 32, 36, 32);
				if (!cuisines.isEmpty()) {
					event.getGuiGraphics().renderItem(cuisines,
							2 + reali * 34, h - 30, 0, 0);
				}
				if (!beverages.isEmpty()) {
					event.getGuiGraphics().renderItem(beverages,
							18 + reali * 34, h - 30, 0, 0);
				}
				event.getGuiGraphics().drawString(Minecraft.getInstance().font,
						new java.text.DecimalFormat("#######").format(i) + "\u53f7\u684c", 8 + reali * 34, h - 10,
						-16777216,
						false);
				reali++;
			}
		}
		RenderSystem.depthMask(true);
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}
}
