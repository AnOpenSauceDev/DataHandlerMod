package me.wolfie.dataentry.util;

import me.wolfie.dataentry.DataHandle;
import me.wolfie.dataentry.listener.ServerListener;
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
    public void Read(String modID, String lineID, boolean Client){
        new Thread(() ->{
            String path;
            if(Client) {
                if (SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_FREE_BSD || SystemUtils.IS_OS_UNIX) {
                    path = MinecraftClient.getInstance().runDirectory.getAbsolutePath() + "/DataHandle/" + modID + "/" + lineID + ".data"; // getAbsolutePath()'s return is fine on runDirectory, but not on server

                    File file = new File(path);
                    try {
                        Scanner reader = new Scanner(file);
                        while (reader.hasNext()) {
                            output = reader.nextLine();
                        }

                        reader.close();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    path = MinecraftClient.getInstance().runDirectory.getAbsolutePath() + "\\DataHandle\\" + modID + "\\" + lineID + ".data";
                    File file = new File(path);
                    try {
                        Scanner reader = new Scanner(file);
                        while (reader.hasNext()) {
                            output = reader.nextLine();
                        }

                        reader.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }else {
                if (SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_FREE_BSD || SystemUtils.IS_OS_UNIX) {
                    path = ServerListener.serverInstance.getRunDirectory().getParent() + "/DataHandle/" + modID + "/" + lineID + ".data";

                    File file = new File(path);
                    try {
                        Scanner reader = new Scanner(file);
                        while (reader.hasNext()) {
                            output = reader.nextLine();
                        }

                        reader.close();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    path = ServerListener.serverInstance.getRunDirectory().getParent() + "\\DataHandle\\" + modID + "\\" + lineID + ".data";
                    File file = new File(path);
                    try {
                        Scanner reader = new Scanner(file);
                        while (reader.hasNext()) {
                            output = reader.nextLine();
                        }

                        reader.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("something didn't read correctly!");
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
