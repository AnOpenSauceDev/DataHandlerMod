package me.wolfie.dataentry.util;

import me.wolfie.dataentry.DataHandle;
import me.wolfie.dataentry.listener.ServerListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A multithreaded data writer. Takes care of most of the annoying stuff for you.
 */
public class ThreadedDataWriter {

    /**
     * <p>The most straight-forward method for you to use, allows you to quickly write data using a few arguments. An example for this would be: {@code new ThreadedDataWriter().Write("mymod","myValue",Data); } Do keep note that "Data" can be just about anything.</p>
     * @param modID The ID for the mod you are writing to, which would look like /DataHandle/ModName/LineID.data
     * @param LineID The identifier for the file
     * @param inputData The data you want to write to the file.
     */
    public void Write(String modID, String LineID, Object inputData, boolean client){ // this way we can read into other mods
        new Thread(() ->{

            boolean isClient = false;

            isClient = client;

            String FinalPath;
        //if client
        if(isClient){

           String path = MinecraftClient.getInstance().runDirectory.getAbsolutePath();



            /**
             * pretty simple, checks what OS you are on
             * checks for *nix based file systems
             */
           if(SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_FREE_BSD || SystemUtils.IS_OS_UNIX){

                FinalPath = path + "/DataHandle/" + modID + "/" + LineID + ".data";

               try {
                   File DataFile = new File(FinalPath);
                   DataFile.delete();
                    DataFile.mkdirs();
                   FileWriter writer = new FileWriter(DataFile);
                   writer.write(inputData.toString());
                   writer.close();
               }
               catch (IOException e) {
                   DataHandle.DataHandlerMainLogger.error("ERROR while writing file!");
                   e.printStackTrace();
                   DataHandle.DataHandlerMainLogger.warn("Data may have been corrupted or not saved! Be careful!");
               }

           }else if (SystemUtils.IS_OS_WINDOWS) {



               FinalPath = path + "\\DataHandle\\" + modID + "\\" + LineID + ".data";



               try {
                   File DataFile = new File(FinalPath);
                   DataFile.mkdirs();
                   DataFile.delete();
                   DataFile.createNewFile();
                   FileWriter writer = new FileWriter(DataFile);
                   writer.write(inputData.toString());
                   writer.close();
               }
               catch (IOException e) {
                   DataHandle.DataHandlerMainLogger.error("ERROR while writing file!");
                   e.printStackTrace();
                   DataHandle.DataHandlerMainLogger.warn("Data may have been corrupted or not saved! Be careful!");
               }

           }else {
               DataHandle.DataHandlerMainLogger.error("ERROR: It seems your os isn't supported! Only Unix, Linux, OSX, Windows and BSD are fully supported!");
               DataHandle.DataHandlerMainLogger.warn("Please file an issue on DataHandlingLib's repository if this keeps happening.");
           }



        }
        else { // else if server
            // same stuff really, just for the server.
            if (ServerListener.serverInstance != null) {
                String path = ServerListener.serverInstance.getRunDirectory().getParent();


                if (SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_FREE_BSD || SystemUtils.IS_OS_UNIX) {

                    FinalPath = path + "/DataHandle/" + modID + "/" + LineID + ".data";

                    try {
                        File DataFile = new File(FinalPath);
                        DataFile.delete();
                         DataFile.mkdirs();
                        FileWriter writer = new FileWriter(DataFile);
                        writer.write(inputData.toString());
                        writer.close();
                    } catch (IOException e) {
                        DataHandle.DataHandlerMainLogger.error("ERROR while writing file!");
                        e.printStackTrace();
                        DataHandle.DataHandlerMainLogger.warn("Data may have been corrupted or not saved! Be careful!");
                    }

                } else if (SystemUtils.IS_OS_WINDOWS) {

                    FinalPath = path + "\\DataHandle\\" + modID + "\\" + LineID + ".data";

                    try {
                        File DataFile = new File(FinalPath);
                        DataFile.delete();
                         DataFile.mkdirs();
                        FileWriter writer = new FileWriter(DataFile);
                        writer.write(inputData.toString());
                        writer.close();
                    } catch (IOException e) {
                        DataHandle.DataHandlerMainLogger.error("ERROR while writing file!");
                        e.printStackTrace();
                        DataHandle.DataHandlerMainLogger.warn("Data may have been corrupted or not saved! Be careful!");
                    }

                } else {
                    DataHandle.DataHandlerMainLogger.error("ERROR: It seems your os isn't supported! Only Unix, Linux, OSX, Windows and BSD are fully supported!");
                    DataHandle.DataHandlerMainLogger.warn("Please file an issue on DataHandlingLib's repository if this keeps happening.");
                }
            }
        }
        }).start();
    }



    /**
     * <p>The most straight-forward method for you to use, allows you to quickly write data using a few arguments. An example for this would be: {@code new ThreadedDataWriter().Write("mymod","myValue",Data); } Do keep note that "Data" can be just about anything.</p>
     * @param modID The ID for the mod you are writing to, which would look like /DataHandle/ModName/.LineID.data
     * @param LineID The identifier for the file
     * @param inputData The data you want to write to the file.
     * @param importantData if this is important, repeatedly cycle until it saves (potentially very slow)
     */
    public void Write(String modID, String LineID, Object inputData, boolean importantData, boolean client){ // this way we can read into other mods
        new Thread(() ->{


            boolean isClient = false;


            isClient = client;

            String FinalPath;
            //if client
            if(isClient){

                String path = MinecraftClient.getInstance().runDirectory.getAbsolutePath();



                /**
                 * pretty simple, checks what OS you are on
                 * checks for *nix based file systems
                 */
                if(SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_FREE_BSD || SystemUtils.IS_OS_UNIX){

                    FinalPath = path + "/DataHandle/" + modID + "/" + LineID + ".data";

                    try {
                        File DataFile = new File(FinalPath);
                        DataFile.delete();
                         DataFile.mkdirs();
                        FileWriter writer = new FileWriter(DataFile);
                        writer.write(inputData.toString());
                        writer.close();
                    }
                    catch (IOException e) {
                        DataHandle.DataHandlerMainLogger.error("ERROR while writing file!");
                        e.printStackTrace();
                        if(importantData) {
                            DataHandle.DataHandlerMainLogger.warn("attempting another write due to important data");
                        }
                    }

                }else if (SystemUtils.IS_OS_WINDOWS) {



                    FinalPath = path + "\\DataHandle\\" + modID + "\\" + LineID + ".data";

                    DataHandle.DataHandlerMainLogger.error("PATHDATA (delete) = " +FinalPath );

                    try {
                        File DataFile = new File(FinalPath);
                        DataFile.mkdirs();
                        DataFile.delete();
                        DataFile.createNewFile();
                        FileWriter writer = new FileWriter(DataFile);
                        writer.write(inputData.toString());
                        writer.close();
                        DataHandle.DataHandlerMainLogger.error("buffer written?!?");
                    }
                    catch (IOException e) {
                        DataHandle.DataHandlerMainLogger.error("ERROR while writing file!");
                        e.printStackTrace();
                        if(importantData) {
                            DataHandle.DataHandlerMainLogger.warn("attempting another write due to important data");
                        }
                    }

                }else {
                    DataHandle.DataHandlerMainLogger.error("ERROR: It seems your os isn't supported! Only Unix, Linux, OSX, Windows and BSD are fully supported!");
                    DataHandle.DataHandlerMainLogger.warn("Please file an issue on DataHandlingLib's repository if this keeps happening.");
                }



            }
            else
            { // else if server
                // same stuff really, just for the server.
                String path = ServerListener.serverInstance.getRunDirectory().getParent();


                if(SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_FREE_BSD || SystemUtils.IS_OS_UNIX){

                    FinalPath = path + "/DataHandle/" + modID + "/" + LineID + ".data";

                    try {
                        File DataFile = new File(FinalPath);
                        DataFile.delete();
                         DataFile.mkdirs();
                        FileWriter writer = new FileWriter(DataFile);
                        writer.write(inputData.toString());
                        writer.close();
                    }
                    catch (IOException e) {
                        DataHandle.DataHandlerMainLogger.error("ERROR while writing file!");
                        e.printStackTrace();
                        if(importantData) {
                            DataHandle.DataHandlerMainLogger.warn("attempting another write due to important data");
                        }
                    }

                }else if (SystemUtils.IS_OS_WINDOWS) {

                    FinalPath = path + "\\DataHandle\\" + modID + "\\" + LineID + ".data";

                    try {
                        File DataFile = new File(FinalPath);
                        DataFile.delete();
                         DataFile.mkdirs();
                        FileWriter writer = new FileWriter(DataFile);
                        writer.write(inputData.toString());
                        writer.close();
                    }
                    catch (IOException e) {
                        DataHandle.DataHandlerMainLogger.error("ERROR while writing file!");
                        e.printStackTrace();
                        if(importantData) {
                            DataHandle.DataHandlerMainLogger.warn("attempting another write due to important data");
                        }}

                }else {
                    DataHandle.DataHandlerMainLogger.error("ERROR: It seems your os isn't supported! Only Unix, Linux, OSX, Windows and BSD are fully supported!");
                    DataHandle.DataHandlerMainLogger.warn("Please file an issue on DataHandlingLib's repository if this keeps happening.");
                }
            }
        }).start();
    }


}
