package me.wolfie.dataentry.util;

import java.util.concurrent.atomic.AtomicBoolean;

public class verifyClass {

    public static boolean verify(boolean TestClient){
        AtomicBoolean res = new AtomicBoolean(false);
        new Thread(()-> {

            if (TestClient) {
                try {
                    Class classy = Class.forName("net.minecraft.client.MinecraftClient");

                } catch (ClassNotFoundException exception) {
                    res.set(false);
                }
            }

        }).start();
        return res.get();
    }

}
