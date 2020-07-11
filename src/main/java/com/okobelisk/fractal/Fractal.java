package com.okobelisk.fractal;

import com.okobelisk.fractal.init.BlockInit;
import com.okobelisk.fractal.init.EnchantmentInit;
import com.okobelisk.fractal.init.ItemInit;
import com.okobelisk.fractal.init.PotionInit;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Fractal.MOD_ID)
public class Fractal {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "fractal";
    public static Fractal instance;


    public Fractal() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        instance = this;

        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        EnchantmentInit.ENCHANTMENTS.register(modEventBus);
        PotionInit.POTIONS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }


    private void setup(final FMLCommonSetupEvent event) {
        doReflection();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    static void doReflection() {
        try {

            // Buff pumpkin pie
            ObfuscationReflectionHelper.findField(Food.class, "field_221471_b").set(Foods.PUMPKIN_PIE, 0.8F);

        } catch (IllegalArgumentException | IllegalAccessException e) {
            LOGGER.error(e);
        }
    }

}
