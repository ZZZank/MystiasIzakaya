package net.touhou.mystiasizakaya.procedures;

import net.touhou.mystiasizakaya.network.MystiasIzakayaModVariables;

import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class CommandDebug1Procedure {
	public static void execute(CommandContext<CommandSourceStack> arguments) {
		MystiasIzakayaModVariables.orders.set((int) DoubleArgumentType.getDouble(arguments, "id"), (ItemArgument.getItem(arguments, "cuisines").getItem().getDefaultInstance()));
	}
}
