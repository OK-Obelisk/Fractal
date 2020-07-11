package com.okobelisk.fractal.items;

import com.okobelisk.fractal.Fractal;
import com.okobelisk.fractal.init.EnchantmentInit;
import com.okobelisk.fractal.init.ItemInit;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Fractal.MOD_ID, bus = Bus.FORGE)
public class SpyglassItem extends Item {

    private static float magnifyAmount = 0.75F;

    public SpyglassItem(Properties properties) {
        super(properties);
    }

    public static boolean isSpyglassActive(PlayerEntity player) {
        return player.isHandActive() && (player.getHeldItemMainhand().getItem() == ItemInit.SPYGLASS.get()
                || player.getHeldItemOffhand().getItem() == ItemInit.SPYGLASS.get());
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (Minecraft.getInstance().gameSettings.thirdPersonView != 0) {
            return ActionResult.resultFail(playerIn.getHeldItem(handIn));
        }

        switch (EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.MAGNIFY.get(),
                playerIn.getHeldItem(handIn).getStack())) {
            case 1:
                magnifyAmount = 0.85F;
                break;
            case 2:
                magnifyAmount = 1F;
                break;
            default:
                magnifyAmount = 0.75F;
        }

        Minecraft.getInstance().gameSettings.smoothCamera = true;

        worldIn.playSound((PlayerEntity) null, playerIn.getPosX(), playerIn.getPosY(),
                playerIn.getPosZ(), SoundEvents.ITEM_ARMOR_EQUIP_IRON, SoundCategory.PLAYERS, 1.0F,
                1.0F / (random.nextFloat() * 0.4F + 1.2F) * 1.8F);

        playerIn.setActiveHand(handIn);

        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        Minecraft.getInstance().gameSettings.smoothCamera = false;
    }

    @SubscribeEvent
    public static void makeSureItsStillInHand(PlayerTickEvent event) {
        if (!isSpyglassActive(event.player)) {
            event.player.stopActiveHand();
        }
    }

    @SubscribeEvent
    public static void updateFOV(FOVUpdateEvent event) {
        if (!event.getEntity().isHandActive())
            return;

        event.setNewfov(event.getFov() - magnifyAmount);
    }

    @SubscribeEvent
    public static void renderSpyglassOverlay(RenderGameOverlayEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null)
            return;

        if (!isSpyglassActive(mc.player))
            return;

        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR)
            event.setCanceled(true);

        if (event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
            if (mc.player.inventory.armorItemInSlot(3).getItem() == Blocks.CARVED_PUMPKIN.asItem())
                return;

            int scaledHeight = event.getWindow().getScaledHeight();
            int scaledWidth = event.getWindow().getScaledWidth();

            mc.getTextureManager().bindTexture(new ResourceLocation(Fractal.MOD_ID + ":textures/misc/spyglass_overlay.png"));
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            bufferbuilder.pos(0.0D, (double) scaledHeight, -90.0D).tex(0.0F, 1.0F).endVertex();
            bufferbuilder.pos((double) scaledWidth, (double) scaledHeight, -90.0D).tex(1.0F, 1.0F).endVertex();
            bufferbuilder.pos((double) scaledWidth, 0.0D, -90.0D).tex(1.0F, 0.0F).endVertex();
            bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0F, 0.0F).endVertex();
            tessellator.draw();
        }
    }

    @SubscribeEvent
    public static void hidePlayerHand(RenderHandEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null)
            return;

        if (!isSpyglassActive(mc.player))
            return;

        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void disableKeys(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null)
            return;

        if (!isSpyglassActive(mc.player))
            return;

        while (mc.gameSettings.keyBindTogglePerspective.isPressed()) {
            mc.gameSettings.thirdPersonView = 0;
        }

        while (mc.gameSettings.keyBindSmoothCamera.isPressed()) {
            mc.gameSettings.smoothCamera = true;
        }
    }

}
