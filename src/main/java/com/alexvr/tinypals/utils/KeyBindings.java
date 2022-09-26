package com.alexvr.tinypals.utils;

import com.alexvr.tinypals.TinyPals;
import net.minecraft.client.KeyMapping;

public class KeyBindings {
    public static KeyMapping toggleMode;

    public static String getKey(String name) {
        return String.join(".", TinyPals.MODID,"key", name);
    }

}
