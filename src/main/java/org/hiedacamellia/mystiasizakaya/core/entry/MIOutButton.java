package org.hiedacamellia.mystiasizakaya.core.entry;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.hiedacamellia.mystiasizakaya.util.ItemStackHolder;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class MIOutButton extends Button {

    private final ItemStackHolder itemStack= new ItemStackHolder();
    private final int x;
    private final int y;

    protected MIOutButton(int x, int y, Component message, OnPress onPress, ItemStack itemStack, @Nullable Tooltip tooltip) {
        super(x, y, 16, 16, message, onPress, Button.DEFAULT_NARRATION);
        this.x = x;
        this.y = y;
        this.setTooltip(tooltip);
        this.itemStack.set(itemStack);
    }

    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int size = Math.min(this.itemStack.get().getCount(), 10);
        for (int i = 0; i < size; i++) {
            guiGraphics.renderItem(this.itemStack.get(), this.x , this.y- 2 * i);
        }
    }

    public ItemStack getItemStack() {
        return this.itemStack.get();
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack.set(itemStack);
    }

    public void enableRender(){
        this.visible=true;
    }

    public void disableRender(){
        this.visible=false;
    }

    public static class builder {
        private ItemStack itemStack=ItemStack.EMPTY;
        private int x;
        private int y;
        private final Component message ;
        private final OnPress onPress ;
        private Tooltip tooltip;

        public builder(Component message, OnPress onPress) {
            this.message = message;
            this.onPress = onPress;
        }

        public builder pos(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public builder itemStack(ItemStack itemStack) {
            this.itemStack = itemStack;
            return this;
        }

        public builder tooltip(@Nullable Tooltip tooltip) {
            this.tooltip = tooltip;
            return this;
        }

        public MIOutButton build(){
            return new MIOutButton(this.x, this.y, this.message, this.onPress, this.itemStack,this.tooltip);
        }
    }
}