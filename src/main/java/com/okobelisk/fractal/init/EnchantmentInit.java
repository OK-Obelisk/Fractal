package com.okobelisk.fractal.init;

import com.okobelisk.fractal.Fractal;
import com.okobelisk.fractal.enchantments.spyglass.MagnifyEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentInit {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = new DeferredRegister<>(
            ForgeRegistries.ENCHANTMENTS, Fractal.MOD_ID);

    public static final RegistryObject<Enchantment> MAGNIFY = ENCHANTMENTS.register("magnify", () ->
            new MagnifyEnchantment(Rarity.RARE,
                    EnchantmentType.create("spyglass", item -> item.equals(ItemInit.SPYGLASS.get())),
                    EquipmentSlotType.MAINHAND));
}
