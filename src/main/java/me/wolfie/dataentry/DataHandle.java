package me.wolfie.dataentry;

import me.wolfie.dataentry.listener.ServerListener;
import me.wolfie.dataentry.util.ThreadedDataReader;
import me.wolfie.dataentry.util.ThreadedDataWriter;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataHandle implements ModInitializer {

    public static Logger DataHandlerMainLogger;

    @Override
    public void onInitialize() {
        DataHandlerMainLogger = LoggerFactory.getLogger(DataHandle.class);
        DataHandlerMainLogger.info("DataHandlingLib is now active!");

        ServerTickEvents.END_SERVER_TICK.register(new ServerListener());
        ThreadedDataWriter writer = new ThreadedDataWriter();
        writer.Write("dataHandlingLib","WhatIsThis","Hello there! If you are wondering what this is, it's a simple data library. see: https://github.com/AnOpenSauceDev/DataHandlerMod");
        ThreadedDataReader dataReader = new ThreadedDataReader();
    }

}
