package dev.venomcode.vanillify.mixins.packets;

import dev.venomcode.vanillify.api.interfaces.BlockStateProxy;
import it.unimi.dsi.fastutil.shorts.ShortSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.chunk.ChunkSection;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkDeltaUpdateS2CPacket.class)
public abstract class ChunkDeltaPacketMixin implements Packet<ClientPlayPacketListener>
{
    @Mutable
    @Final
    @Shadow
    private BlockState[] blockStates;

    private void doInitProxy()
    {
        BlockState[] replacementStates = new BlockState[blockStates.length];

        for(int i=0; i < blockStates.length; i++ )
        {
            BlockState s = blockStates[ i ];
            if( s.getBlock() instanceof BlockStateProxy)
            {
                replacementStates[ i ] = ((( BlockStateProxy )s.getBlock()).getClientBlockState( s ));
            }
            else
            {
                replacementStates[ i ] = s;
            }
        }

        blockStates = replacementStates;
    }

    @Inject( method = "write", at = @At( value = "HEAD") )
    public void onWriteInjection(PacketByteBuf buf, CallbackInfo ci)
    {
        doInitProxy();
    }

    @Inject( method = "<init>(Lnet/minecraft/network/PacketByteBuf;)V", at = @At("RETURN") )
    public void onInitPacketByteBuf( PacketByteBuf buf, CallbackInfo ci )
    {
        doInitProxy();
    }
    @Inject( method = "<init>(Lnet/minecraft/util/math/ChunkSectionPos;Lit/unimi/dsi/fastutil/shorts/ShortSet;Lnet/minecraft/world/chunk/ChunkSection;Z)V", at = @At( "RETURN" ))
    void onInitStupidlyLongParam( ChunkSectionPos sectionPos, ShortSet positions, ChunkSection section, boolean noLightingUpdates, CallbackInfo ci )
    {
        doInitProxy();
    }
}
