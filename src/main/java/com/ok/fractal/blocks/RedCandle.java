package com.ok.fractal.blocks;

import com.ok.fractal.init.TileEntitiesInit;
import com.ok.fractal.potions.effects.RedCandleXP;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class RedCandle extends Block {
	
	private boolean lit = false; // fam
	private int wax = 0;
	
	public RedCandle(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResultType onBlockActivated(final BlockState state, final World worldIn, final BlockPos pos, final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit)
	{
		if(player.getHeldItemMainhand().getItem() == Items.FLINT_AND_STEEL && !lit) {
			lit = true;
			System.out.println("test");
	        worldIn.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, RANDOM.nextFloat() * 0.4F + 0.8F);

			// do model stuff

	        return ActionResultType.SUCCESS;
		}

		if (lit) {


			System.out.println(RedCandleXP.xpToGive);

			// do model stuff

			return ActionResultType.SUCCESS;
		}
		return ActionResultType.SUCCESS;
	}
	
	@Override
	public boolean hasTileEntity() {
		return false; //
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntitiesInit.RED_CANDLE.get().create();
	}

}
