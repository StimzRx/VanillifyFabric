package dev.venomcode.vanillify.mixins.packets;

import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockEntityUpdateS2CPacket.class)
public abstract class BlockEntityUpdatePacketMixin implements Packet<ClientPlayPacketListener>
{

}
