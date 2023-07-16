package org.moon.figura.commands.forge;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.moon.figura.FiguraMod;
import org.moon.figura.commands.FiguraCommands;
import org.moon.figura.utils.FiguraClientCommandSource;

@Mod.EventBusSubscriber(modid = FiguraMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class FiguraCommandsForge {
    @SuppressWarnings({"unchecked", "rawtypes"})
    @SubscribeEvent
    public static void registerCommands(RegisterClientCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        CommandDispatcher<FiguraClientCommandSource> casted = (CommandDispatcher) dispatcher;
        casted.register(FiguraCommands.getCommandRoot());
    }
}
