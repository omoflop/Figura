package org.moon.figura.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import org.moon.figura.FiguraMod;
import org.moon.figura.avatar.Avatar;
import org.moon.figura.avatar.AvatarManager;
import org.moon.figura.backend2.BackendCommands;
import org.moon.figura.lua.FiguraLuaRuntime;
import org.moon.figura.lua.docs.FiguraDocsManager;
import org.moon.figura.model.rendering.AvatarRenderer;
import org.moon.figura.utils.FiguraClientCommandSource;
import org.moon.figura.utils.FiguraText;

public class FiguraCommands {

    public static LiteralArgumentBuilder<FiguraClientCommandSource> getCommandRoot() {
        //root
        LiteralArgumentBuilder<FiguraClientCommandSource> root = LiteralArgumentBuilder.literal(FiguraMod.MOD_ID);

        //docs
        root.then(FiguraDocsManager.getCommand());

        //links
        root.then(LinkCommand.getCommand());

        //run
        root.then(RunCommand.getCommand());

        //load
        root.then(LoadCommand.getCommand());

        //reload
        root.then(ReloadCommand.getCommand());

        //debug
        root.then(DebugCommand.getCommand());

        //export
        root.then(ExportCommand.getCommand());

        if (FiguraMod.DEBUG_MODE) {
            //backend debug
            root.then(BackendCommands.getCommand());

            //set avatar command
            root.then(AvatarManager.getCommand());
        }

        return root;
    }

    protected static Avatar checkAvatar(CommandContext<FiguraClientCommandSource> context) {
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if (avatar == null) {
            context.getSource().figura$sendError(FiguraText.of("command.no_avatar_error"));
            return null;
        }
        return avatar;
    }

    protected static FiguraLuaRuntime getRuntime(CommandContext<FiguraClientCommandSource> context) {
        Avatar avatar = checkAvatar(context);
        if (avatar == null)
            return null;
        if (avatar.luaRuntime == null || avatar.scriptError) {
            context.getSource().figura$sendError(FiguraText.of("command.no_script_error"));
            return null;
        }
        return avatar.luaRuntime;
    }

    protected static AvatarRenderer getRenderer(CommandContext<FiguraClientCommandSource> context) {
        Avatar avatar = checkAvatar(context);
        if (avatar == null)
            return null;
        if (avatar.renderer == null) {
            context.getSource().figura$sendError(FiguraText.of("command.no_renderer_error"));
            return null;
        }
        return avatar.renderer;
    }
}
