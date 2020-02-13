package net.kyrptonaught.commandlog.mixin;

import net.kyrptonaught.commandlog.CommandLogMod;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mixin(ClientPlayerEntity.class)
public abstract class ChatSentMixin {

    @Inject(method = "sendChatMessage", at = @At("HEAD"))
    private void CL$logChat(String string, CallbackInfo ci) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern(CommandLogMod.config.getConfig().DateTimeFormat));
        String log = "[" + time + "] " + string;
        CommandLogMod.config.getConfig().commands.forEach(command -> {
            if (string.trim().startsWith(command.trim())) {
                CommandLogMod.writeToFile(log);
            }
        });
    }
}