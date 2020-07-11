package com.ok.fractal.tileentity;

import com.ok.fractal.init.TileEntitiesInit;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class RedCandleTileEntity extends TileEntity {

	public RedCandleTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public RedCandleTileEntity() {
		this(TileEntitiesInit.RED_CANDLE.get());
	}

}
