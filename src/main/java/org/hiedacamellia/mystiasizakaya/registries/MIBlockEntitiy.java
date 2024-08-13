package org.hiedacamellia.mystiasizakaya.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.hiedacamellia.mystiasizakaya.MystiasIzakaya;
import org.hiedacamellia.mystiasizakaya.content.common.block.entities.*;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class MIBlockEntitiy {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MystiasIzakaya.MODID);
	public static final DeferredHolder<BlockEntityType<?>,BlockEntityType<?>> COOKING_RANGE = register("cooking_range", MIBlock.COOKING_RANGE, CookingRange::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> DONATION = register("donation", MIBlock.DONATION, Donation::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> CUTTING_BOARD = register("cutting_board", MIBlock.CUTTING_BOARD, CuttingBoard::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> BOILING_POT = register("boiling_pot", MIBlock.BOILING_POT, BoilingPot::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> FRYING_PAN = register("frying_pan", MIBlock.FRYING_PAN, FryingPan::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> STEAMER = register("steamer", MIBlock.STEAMER, Steamer::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> GRILL = register("grill", MIBlock.GRILL, Grill::new);


	private static DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> register(String registryname, DeferredHolder<Block,? extends Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COOKING_RANGE.get(), (blockEntity, side) -> ((CookingRange ) blockEntity).getItemHandler());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CUTTING_BOARD.get(), (blockEntity, side) -> ((CuttingBoard ) blockEntity).getItemHandler());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BOILING_POT.get(), (blockEntity, side) -> ((BoilingPot ) blockEntity).getItemHandler());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, FRYING_PAN.get(), (blockEntity, side) -> ((FryingPan ) blockEntity).getItemHandler());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, STEAMER.get(), (blockEntity, side) -> ((Steamer ) blockEntity).getItemHandler());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, GRILL.get(), (blockEntity, side) -> ((Grill ) blockEntity).getItemHandler());
	}
}