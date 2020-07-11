package com.okobelisk.fractal.init;

import com.okobelisk.fractal.Fractal;
import com.okobelisk.fractal.tileentity.RedCandleTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntitiesInit {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Fractal.MOD_ID);

    public static final RegistryObject<TileEntityType<RedCandleTileEntity>> RED_CANDLE = TILE_ENTITIES.register("red_candle",
            () -> TileEntityType.Builder.create(RedCandleTileEntity::new, BlockInit.RED_CANDLE.get()).build(null));
}
