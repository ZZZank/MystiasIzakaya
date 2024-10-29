package org.hiedacamellia.mystiasizakaya.core.network;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import org.hiedacamellia.mystiasizakaya.core.event.MIPlayerEvent;
import org.hiedacamellia.mystiasizakaya.util.cross.Pos;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class OpenMessage {
    private final boolean i;

    public OpenMessage(boolean i) {
        this.i = i;
    }
    public OpenMessage(FriendlyByteBuf buffer) {
        this.i = buffer.readBoolean();
    }
    public static void buffer(OpenMessage message, FriendlyByteBuf buffer) {
        buffer.writeBoolean(message.i);
    }

    public static void handler(OpenMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> handleServer(message.i, context));
        context.setPacketHandled(true);
    }
    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MINetWork.addNetworkMessage(OpenMessage.class, OpenMessage::buffer, OpenMessage::new, OpenMessage::handler);
    }

    public static void handleServer(boolean open, final NetworkEvent.Context context) {
        context.enqueueWork(() -> {
                    ServerPlayer player = context.getSender();
                    if(open) {
                        boolean flag1 = false;
                        boolean flag2 = false;
                        boolean flag3 = false;
                        for (BlockPos blockPos : MIPlayerEvent.getTables(player)) {
                            if(!Objects.equals(blockPos, new BlockPos(-1, -1, -1))){
                                flag1 = true;
                                break;
                            }
                        }
                        for(String s:MIPlayerEvent.getMenus(player)){
                            if(!s.equals("minecraft:air")){
                                flag2 = true;
                                break;
                            }
                        }
                        for(String s:MIPlayerEvent.getMenusBeverages(player)){
                            if(!s.equals("minecraft:air")){
                                flag3 = true;
                                break;
                            }
                        }
                        if(flag1 && flag2 && flag3){
                            MIPlayerEvent.setOnOpen(player, open);
                            MINetWork.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new OpenMessage(open));
                            player.sendSystemMessage(Component.translatable("network.mystiasizakaya.ledger.success").withStyle(ChatFormatting.GREEN));
                        }else {
                            player.sendSystemMessage(Component.translatable("network.mystiasizakaya.ledger.failed").withStyle(ChatFormatting.RED));
                            if(!flag1){
                                player.sendSystemMessage(Component.translatable("network.mystiasizakaya.ledger.failed.table").withStyle(ChatFormatting.GRAY));
                            }
                            if(!flag2){
                                player.sendSystemMessage(Component.translatable("network.mystiasizakaya.ledger.failed.cuisines").withStyle(ChatFormatting.GRAY));
                            }
                            if(!flag3){
                                player.sendSystemMessage(Component.translatable("network.mystiasizakaya.ledger.failed.beverages").withStyle(ChatFormatting.GRAY));
                            }
                            MINetWork.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new OpenMessage(false));
                        }
                    }else {
                        MIPlayerEvent.setOnOpen(player, open);
                        MINetWork.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new OpenMessage(open));
                    }



                })
                .exceptionally(e -> {
                    context.getNetworkManager().disconnect(Component.translatable("network.mystiasizakaya.failed", e.getMessage()));
                    return null;
                });
    }
}
