package me.wolfie.dataentry.listener;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;


// exists purely so we can grab the server.
public class ServerListener implements ServerTickEvents.EndTick{

    public static MinecraftServer serverInstance;
    @Override
    public void onEndTick(MinecraftServer server) {
        serverInstance = server;
    }
}
