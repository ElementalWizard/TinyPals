package com.alexvr.tinypals.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class NBTHelper {

    public static void setInteger(ItemStack stack,String key, int amount) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putInt(key,amount);
    }

    public static void addInteger(ItemStack stack,String key, int amount) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putInt(key,nbt.getInt(key) + amount);
    }

    public static int getInt(ItemStack stack,String key) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getInt(key);
    }
    public static Tag getTag(ItemStack stack,String key) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.get(key);
    }

    public static void setString(ItemStack stack,String key, String string) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putString(key,string);
    }

    public static String getStirng(ItemStack stack,String key) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getString(key);
    }
    public static boolean getBoolean(ItemStack stack,String key) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getBoolean(key);
    }

    public static void setBoolean(ItemStack stack,String key, boolean state) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putBoolean(key,state);

    }
    public static void setTag(ItemStack stack,String key, Tag tag) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.put(key,tag);

    }
    public static void flipBoolean(ItemStack stack,String key) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putBoolean(key,!nbt.getBoolean(key));
    }

    public static void setDouble(ItemStack stack,String key, double amount) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putDouble(key,amount);
    }

    public static void addDouble(ItemStack stack,String key, double amount) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putDouble(key,nbt.getInt(key) + amount);
    }

    public static double getDouble(ItemStack stack,String key) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getDouble(key);
    }
    public static void setUUID(ItemStack stack,String key, UUID uuid) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putUUID(key,uuid);
    }

    public static UUID getUUID(ItemStack stack,String key) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.hasUUID(key) ? nbt.getUUID(key) : null;
    }
}
