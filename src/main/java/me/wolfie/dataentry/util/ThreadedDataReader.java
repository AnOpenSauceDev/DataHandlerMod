package me.wolfie.dataentry.util;

import me.wolfie.dataentry.DataHandle;
import net.minecraft.client.MinecraftClient;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class ThreadedDataReader {

    public String output;
    public void Read(String modID, String lineID){
        new Thread(() ->{
            String path;

                if(SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_FREE_BSD || SystemUtils.IS_OS_UNIX) {
                    path = MinecraftClient.getInstance().runDirectory.getAbsolutePath() + "/DataHandle/" + modID + "/" + lineID + ".data";

                    File file = new File(path);
                    try {
                        Scanner reader = new Scanner(file);
                        while (reader.hasNext()){
                            output = reader.nextLine();
                        }

                        reader.close();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    path = MinecraftClient.getInstance().runDirectory.getAbsolutePath() + "\\DataHandle\\" + modID + "\\" + lineID + ".data";
                    File file = new File(path);
                    try {
                        Scanner reader = new Scanner(file);
                        while (reader.hasNext()){
                            output = reader.nextLine();
                        }

                        reader.close();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }


        }).start();

    }
}
