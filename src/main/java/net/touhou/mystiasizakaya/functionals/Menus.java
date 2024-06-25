package net.touhou.mystiasizakaya.functionals;

import net.touhou.mystiasizakaya.functionals.inventory.CookingRangeUiMenu;
import net.touhou.mystiasizakaya.functionals.inventory.BankUiMenu;
import net.touhou.mystiasizakaya.MystiasIzakaya;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

public class Menus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MystiasIzakaya.MODID);
	public static final RegistryObject<MenuType<CookingRangeUiMenu>> COOKING_RANGE_UI = REGISTRY.register("cooking_range_ui", () -> IForgeMenuType.create(CookingRangeUiMenu::new));
	public static final RegistryObject<MenuType<BankUiMenu>> BANK_UI = REGISTRY.register("bank_ui", () -> IForgeMenuType.create(BankUiMenu::new));
}
