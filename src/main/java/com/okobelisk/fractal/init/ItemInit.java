package com.okobelisk.fractal.init;

import com.okobelisk.fractal.Fractal;
import com.okobelisk.fractal.items.SpyglassItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Fractal.MOD_ID);

    // Items
    public static final RegistryObject<Item> SPYGLASS = ITEMS.register("spyglass", () -> new SpyglassItem(new Item.Properties().group(ItemGroup.TOOLS)));

    // Foods
    public static final RegistryObject<Item> SWEET_BERRY_PIE = ITEMS.register("sweet_berry_pie", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food((new Food.Builder()).hunger(8).saturation(0.8f).build())));
    public static final RegistryObject<Item> FRIED_EGG = ITEMS.register("fried_egg", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food((new Food.Builder()).hunger(2).saturation(0.3f).fastToEat().build())));

    // BlockItems
    public static final RegistryObject<Item> RED_CANDLE_ = ITEMS.register("red_candle", () -> new BlockItem(BlockInit.RED_CANDLE.get(), new Item.Properties()));


}
