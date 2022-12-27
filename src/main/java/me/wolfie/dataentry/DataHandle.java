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
        writer.Write("dataHandlingLib","WhatIsThis","hello there! If you are wondering what this is, its a data storage library");
        ThreadedDataReader dataReader = new ThreadedDataReader();
        dataReader.Read("dataHandlingLib","WhatIsThis");
        /* debug stuff
        new Thread( () -> {
            try {
                Thread.sleep(300);
                DataHandlerMainLogger.warn(dataReader.output);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();


         */
    }

}
