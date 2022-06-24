package dev.venomcode.vanillify.mixins.generic;

import dev.venomcode.vanillify.VanillifyMod;
import dev.venomcode.vanillify.api.gui.SLOT_CLICK_RESULT;
import dev.venomcode.vanillify.api.gui.ServerScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ClickType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.logging.Level;

@Mixin( ScreenHandler.class )
public abstract class SlotClickMixin
{

    @Inject( method = "onSlotClick", at = @At(value = "HEAD"), cancellable = true )
    void onClickSlotInjection(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci )
    {
        if(player.world.isClient)
        {
            return;
        }
        if( player.currentScreenHandler instanceof ServerScreenHandler )
        {
            ServerScreenHandler serverScreenHandler = (ServerScreenHandler) player.currentScreenHandler;
            ClickType clickType = button == 0 ? ClickType.LEFT : ClickType.RIGHT;

            SLOT_CLICK_RESULT clickResult = serverScreenHandler.checkSlotClick( slotIndex, button, actionType, (ServerPlayerEntity) player );

            if(clickResult == SLOT_CLICK_RESULT.DENY_CLICK)
            {
                // DONT PASS THE CLICK THROUGH
                ci.cancel();
            }
            else if(clickResult == SLOT_CLICK_RESULT.PASSTHROUGH_CLICK)
            {
                // PASS THROUGH THE CLICK TO THE SCREEN HANDLER, so do nothing
            }
            else
            {
                VanillifyMod.LOGGER.log( Level.SEVERE, "[SlotClickMixin] ClickResult isnt handled properly?!?" );
            }
        }

    }
}
