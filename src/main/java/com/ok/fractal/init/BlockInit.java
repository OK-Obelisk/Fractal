package com.ok.fractal.init;

import com.ok.fractal.Fractal;
import com.ok.fractal.blocks.RedCandle;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Fractal.MOD_ID);

    public static final RegistryObject<Block> RED_CANDLE = BLOCKS.register("red_candle", () -> new RedCandle(Block.Properties.create(Material.MISCELLANEOUS)));


}
