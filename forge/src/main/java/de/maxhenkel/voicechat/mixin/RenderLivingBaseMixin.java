package de.maxhenkel.voicechat.mixin;

import de.maxhenkel.voicechat.intercompatibility.ClientCompatibilityManager;
import de.maxhenkel.voicechat.intercompatibility.ForgeClientCompatibilityManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderLivingBase.class)
public abstract class RenderLivingBaseMixin<T extends EntityLivingBase> {

    @Inject(method = "renderName", at = @At("HEAD"), cancellable = true)
    private void forceRenderName(T entity, double x, double y, double z, CallbackInfo ci) {
        if (!(entity instanceof EntityPlayer)) return;

        EntityPlayer Player = (EntityPlayer) entity;
        if (Player.isSpectator()) {
            return;
        }

        String displayName = entity.getDisplayName().getFormattedText();

        ((ForgeClientCompatibilityManager) ClientCompatibilityManager.INSTANCE)
                .onRenderName(entity, displayName, x, y, z, 64);
    }

}
