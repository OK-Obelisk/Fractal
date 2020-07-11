package com.okobelisk.fractal.init;

import com.okobelisk.fractal.Fractal;
import com.okobelisk.fractal.potions.effects.RedCandleXP;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {

    public static final DeferredRegister<Effect> POTIONS = new DeferredRegister<>(ForgeRegistries.POTIONS,
            Fractal.MOD_ID);

    public static final DeferredRegister<Potion> POTION_EFFECTS = new DeferredRegister<>(ForgeRegistries.POTION_TYPES,
            Fractal.MOD_ID);

    public static final RegistryObject<Effect> RED_CANDLE_XP = POTIONS.register("red_candle_xp",
            () -> new RedCandleXP(EffectType.NEUTRAL, 00000000));
}
