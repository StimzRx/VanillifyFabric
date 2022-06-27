package dev.venomcode.vanillify;

import dev.venomcode.vanillify.api.VanillaUtils;
import dev.venomcode.vanillify.api.item.ServerBlockItem;
import dev.venomcode.vanillify.commands.VanillifyCommand;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.logging.Logger;

public class VanillifyMod implements DedicatedServerModInitializer
{
    @Override
    public void onInitializeServer( )
    {
        CommandRegistrationCallback.EVENT.register( (dispatcher, dedicated, environment) ->
        {
            VanillifyCommand.register(dispatcher);
        });

    }


    public static final String MODID = "vanillify";
    public static final Logger LOGGER = Logger.getLogger( MODID );
}
