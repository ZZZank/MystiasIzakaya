
package org.hiedacamellia.mystiasizakaya.core.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.hiedacamellia.mystiasizakaya.core.config.ClientConfig;

@Mod.EventBusSubscriber
public class Showbalance {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("mystiasizakaya").then(Commands.literal("showbalance")

				.then(Commands.argument("logic", BoolArgumentType.bool()).executes(arguments -> {
					Level world = arguments.getSource().getUnsidedLevel();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null && world instanceof ServerLevel _servLevel)
						entity = FakePlayerFactory.getMinecraft(_servLevel);
					if (entity == null)
						return 0;
					{
						boolean set = BoolArgumentType.getBool(arguments, "logic");

						ClientConfig.SHOW_BALANCE.set(set);

					}
					return 0;
				}))));
	}
}
