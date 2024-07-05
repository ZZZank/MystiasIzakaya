package org.hiedacamellia.mystiasizakaya.content.item.items;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.hiedacamellia.mystiasizakaya.functionals.effects.GiveEffectFromCuisines;
import org.hiedacamellia.mystiasizakaya.functionals.effects.GiveEffectFromIngredientsProcedure;
import org.hiedacamellia.mystiasizakaya.functionals.effects.GiveEffectFromTagsProcedure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseItem extends Item {

    private final UseAnim useAnimation;
    private final int useDuration;
    private final String regname;
    private final List<String> tags;
    private final List<String> ntags;

    public BaseItem(int stacks,int nutrition,float saturation,Rarity rarity,UseAnim useAnimation, int useDuration, String regname, String[] tags, String[] ntags) {
        super(new Item.Properties().stacksTo(stacks).rarity(rarity)
                .food((new FoodProperties.Builder()).nutrition(nutrition).saturationMod(saturation).alwaysEat().build()));
        this.useAnimation = useAnimation;
        this.useDuration = useDuration;
        this.regname = regname;
        List<String> ftags = new ArrayList<>();
        List<String> fntags = new ArrayList<>();
        Arrays.asList(tags).forEach(tag -> ftags.add(gettagprefix()+tag));
        Arrays.asList(ntags).forEach(tag -> fntags.add(gettagprefix()+tag));
        this.tags = ftags;
        this.ntags = fntags;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        String tags = stack.getOrCreateTag().getString("tags");
        if (tags.isEmpty()){
            stack.getOrCreateTag().putString("tags", String.join(",", gettags()));
        }
        String ntags = stack.getOrCreateTag().getString("ntags");
        if (ntags.isEmpty()){
            stack.getOrCreateTag().putString("ntags", String.join(",", getnegativetags()));
        }
    }


    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return useAnimation;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return useDuration;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        if (!Screen.hasShiftDown()) {
            //List<String> tags = gettags();
            //for (String tag : tags) {
           //     list.add(Component.literal("§6+ " + Component.translatable(tag).getString() + "§r"));
           // }
            List<String> tags = itemstack.getOrCreateTag().getString("tags").isEmpty() ? gettags() : Arrays.asList(itemstack.getOrCreateTag().getString("tags").split(","));
            //List<String> tagsfnbt = GetTagsFromNbt.execute(itemstack);
            for (String tag : tags) {
                list.add(Component.literal("§6+ " + Component.translatable(tag).getString() + "§r"));
            }
            //Set<Component> set = new LinkedHashSet<>(list);
            //list.clear();
            //list.addAll(set);
            List<String> negativetags = getnegativetags();
            for (String tag : negativetags) {
                list.add(Component.literal("§4- " + Component.translatable(tag).getString() + "§r"));
            }
            list.add(Component.literal(
                    "§7§o" + Component.translatable("tooltip.mystias_izakaya.press_shift").getString() + "§r"));
        } else {
            String[] description = Component.translatable("tooltip.mystias_izakaya."+this.regname).getString().split("§n");
            for (String line : description) {
                list.add(Component.literal(line));
            }
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        super.finishUsingItem(itemstack, world, entity);
        GiveEffectFromTagsProcedure.execute(world, itemstack, entity);
        GiveEffectFromIngredientsProcedure.execute(world, itemstack, entity);
        GiveEffectFromCuisines.execute(world, itemstack, entity);
        return itemstack;
    }

    public List<String> gettags() {
        return this.tags;
    }

    public List<String> getnegativetags() {
        return this.ntags;
    }

    public String gettagprefix() {
        return "tag.mystias_izakaya.";
    }
}


