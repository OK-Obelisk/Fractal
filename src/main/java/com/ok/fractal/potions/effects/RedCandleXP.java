package com.ok.fractal.potions.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class RedCandleXP extends Effect {
	
	public static int xpToGive = 0;
	
	public RedCandleXP(EffectType typeIn, int liquidColorIn) {
		super(typeIn, liquidColorIn);
	}

	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
		if (entityLivingBaseIn instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
			if(player.experienceLevel <= 30) {
				xpToGive += 200;
			}
		}
	}

}
