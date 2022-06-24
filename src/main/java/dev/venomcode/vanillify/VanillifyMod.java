package dev.venomcode.vanillify;

import dev.venomcode.vanillify.commands.VanillifyCommand;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;

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
