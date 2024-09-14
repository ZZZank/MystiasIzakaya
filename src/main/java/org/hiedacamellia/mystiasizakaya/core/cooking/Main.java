package org.hiedacamellia.mystiasizakaya.core.cooking;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.hiedacamellia.mystiasizakaya.core.cooking.get.GetTargets;
import org.hiedacamellia.mystiasizakaya.integration.youkaihomecoming.IngredientsCompact;
import org.hiedacamellia.mystiasizakaya.registries.MIItem;
import org.hiedacamellia.mystiasizakaya.util.GetItemStack;
import org.hiedacamellia.mystiasizakaya.util.GetValue;
import org.hiedacamellia.mystiasizakaya.util.SetSlotItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Main {
    public static void execute(LevelAccessor world, BlockPos pos, BlockState blockState) {
        double time;
        ItemStack util;
        ItemStack target;
        time = GetValue.getDouble(world, pos, "timeleft");
        if (time > 1) {
            if (!world.isClientSide()) {
                BlockEntity _blockEntity = world.getBlockEntity(pos);
                BlockState _bs = world.getBlockState(pos);
                if (_blockEntity != null)
                    _blockEntity.getPersistentData().putDouble("timeleft", (time - 1));
                if (world instanceof Level _level)
                    _level.sendBlockUpdated(pos, _bs, _bs, 3);
            }
        } else if (time == 1) {
            if (!world.isClientSide()) {
                BlockEntity _blockEntity = world.getBlockEntity(pos);
                BlockState _bs = world.getBlockState(pos);
                if (_blockEntity != null)
                    _blockEntity.getPersistentData().putDouble("timeleft", 0);
                if (world instanceof Level _level)
                    _level.sendBlockUpdated(pos, _bs, _bs, 3);
            }
            target = GetItemStack.getItemStack(world, pos, 12);
            SetSlotItem.setSlotItem(world, pos, target, 6, 1);

//            MITags miTags = GetItemStack.getItemStack(world,pos, 12).get(MIDatacomponet.MI_TAGS.get());
//            target.set(MIDatacomponet.MI_TAGS.get(), miTags);

            SetSlotItem.setEmptySlot(world, pos, 12);
        } else {

            if (ItemStack.EMPTY == GetItemStack.getItemStack(world, pos, 4)
                    && !(ItemStack.EMPTY == GetItemStack.getItemStack(world, pos, 5))) {
                SetSlotItem.setSlotItem(world, pos, GetItemStack.getItemStack(world, pos, 5), 4, 1);
                SetSlotItem.setEmptySlot(world, pos, 5);
                resetpage(world, pos);
                SetSlotItem.setEmptySlot(world, pos, new int[]{7, 8, 9, 10, 11, 12});
            }
            if (ItemStack.EMPTY == GetItemStack.getItemStack(world, pos, 3)
                    && !(ItemStack.EMPTY == GetItemStack.getItemStack(world, pos, 4))) {
                SetSlotItem.setSlotItem(world, pos, GetItemStack.getItemStack(world, pos, 4), 3, 1);
                SetSlotItem.setEmptySlot(world, pos, 4);
                resetpage(world, pos);
                SetSlotItem.setEmptySlot(world, pos, new int[]{7, 8, 9, 10, 11, 12});
            }
            if (ItemStack.EMPTY == GetItemStack.getItemStack(world, pos, 2)
                    && !(ItemStack.EMPTY == GetItemStack.getItemStack(world, pos, 3))) {
                SetSlotItem.setSlotItem(world, pos, GetItemStack.getItemStack(world, pos, 3), 2, 1);
                SetSlotItem.setEmptySlot(world, pos, 3);
                resetpage(world, pos);
                SetSlotItem.setEmptySlot(world, pos, new int[]{7, 8, 9, 10, 11, 12});
            }
            if (ItemStack.EMPTY == GetItemStack.getItemStack(world, pos, 1)
                    && !(ItemStack.EMPTY == GetItemStack.getItemStack(world, pos, 2))) {
                SetSlotItem.setSlotItem(world, pos, GetItemStack.getItemStack(world, pos, 2), 1, 1);
                SetSlotItem.setEmptySlot(world, pos, 2);
                resetpage(world, pos);
                SetSlotItem.setEmptySlot(world, pos, new int[]{7, 8, 9, 10, 11, 12});
            }

            if (ItemStack.EMPTY == GetItemStack.getItemStack(world, pos, 1)) {
                SetSlotItem.setEmptySlot(world, pos, new int[]{7, 8, 9, 10, 11, 12});
            }

            util = GetItemStack.getItemStack(world, pos, 0);
//            Debug.send(GetItemStack.getItemStack(world, pos, 1).getDescriptionId());
//            Debug.send(GetItemStack.getItemStack(world, pos, 2).getDescriptionId());
//            Debug.send(GetItemStack.getItemStack(world, pos, 3).getDescriptionId());
//            Debug.send(GetItemStack.getItemStack(world, pos, 4).getDescriptionId());
//            Debug.send(GetItemStack.getItemStack(world, pos, 5).getDescriptionId());

            if (util == ItemStack.EMPTY) {
                Block utilblcok = world.getBlockState(pos).getBlock();
                //Debug.send(utilblcok.getDescriptionId());
                switch (utilblcok.getDescriptionId()) {
                    case "block.mystias_izakaya.cutting_board":
                        util = MIItem.CUTTING_BOARD.get().getDefaultInstance();
                        break;
                    case "block.mystias_izakaya.boiling_pot":
                        util = MIItem.BOILING_POT.get().getDefaultInstance();
                        break;
                    case "block.mystias_izakaya.frying_pan":
                        util = MIItem.FRYING_PAN.get().getDefaultInstance();
                        break;
                    case "block.mystias_izakaya.steamer":
                        util = MIItem.STEAMER.get().getDefaultInstance();
                        break;
                    case "block.mystias_izakaya.grill":
                        util = new ItemStack(MIItem.GRILL.get());
                        break;
                }
            }
            //Debug.send(util.getDescriptionId());

            List<String> raws = new ArrayList<>();
            List<ItemStack> ingredients = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                if (!(GetItemStack.getItemStack(world, pos, i).getItem() == ItemStack.EMPTY.getItem())) {
                    ItemStack raw = GetItemStack.getItemStack(world, pos, i);
                    raw = IngredientsCompact.execute(raw);
                    ingredients.add(raw);
                    raws.add((Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(raw.getItem())).toString()));
                } else {
                    ingredients.add(ItemStack.EMPTY);
                }
            }

            List<String> targets = GetTargets.getTargets(raws, util);

            if (!world.isClientSide()) {
                BlockPos _bp = pos;
                BlockEntity _blockEntity = world.getBlockEntity(_bp);
                BlockState _bs = world.getBlockState(_bp);
                if (_blockEntity != null) {
                    _blockEntity.getPersistentData().putDouble("targets", targets.size());
                }
                if (world instanceof Level _level)
                    _level.sendBlockUpdated(_bp, _bs, _bs, 3);
            }
            int page = GetValue.getInt(world, pos, "page");

            for (int i = 0; i < 5; i++) {
                if (i < targets.size()) {
                    ItemStack taget = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(((targets.get(i + page))).toLowerCase(Locale.ENGLISH)))));
                    SetSlotItem.setSlotItem(world, pos, BuildTags.execute(taget, util, ingredients), 7 + i, 1);
                } else {
                    SetSlotItem.setEmptySlot(world, pos, 7 + i);
                }
            }

        }
    }

    private static void resetpage(LevelAccessor world, BlockPos ps) {
        if (!world.isClientSide()) {
            BlockEntity _blockEntity = world.getBlockEntity(ps);
            BlockState _bs = world.getBlockState(ps);
            if (_blockEntity != null) {
                _blockEntity.getPersistentData().putInt("page", 0);
            }
            if (world instanceof Level _level)
                _level.sendBlockUpdated(ps, _bs, _bs, 3);
        }
    }
}
