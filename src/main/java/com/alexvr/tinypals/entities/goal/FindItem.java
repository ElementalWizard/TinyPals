package com.alexvr.tinypals.entities.goal;

import com.alexvr.tinypals.entities.TreckingCreeperEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.pathfinder.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

//From https://github.com/baileyholl/Ars-Nouveau goals
public class FindItem extends Goal {
    private TreckingCreeperEntity treckingCreeper;
    boolean itemStuck;
    int timeFinding;
    int stuckTicks;
    List<ItemEntity> destList = new ArrayList<>();
    ItemEntity dest;


    private final Predicate<ItemEntity> NONTAMED_TARGET_SELECTOR = itemEntity -> !itemEntity.hasPickUpDelay() && itemEntity.isAlive() && itemEntity.getItem().is(Items.TNT);
    private final Predicate<ItemEntity> TAMED_TARGET_SELECTOR = itemEntity -> !itemEntity.hasPickUpDelay() && itemEntity.isAlive();

    @Override
    public void stop() {
        super.stop();
        itemStuck = false;
        timeFinding = 0;
        destList = new ArrayList<>();
        dest = null;
        stuckTicks = 0;
    }

    @Override
    public void start() {
        super.start();
        timeFinding = 0;
        itemStuck = false;
        stuckTicks = 0;
    }

    public FindItem(TreckingCreeperEntity treckingCreeperEntity) {
        this.treckingCreeper = treckingCreeperEntity;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    public List<ItemEntity> nearbyItems() {
        return treckingCreeper.level().getEntitiesOfClass(ItemEntity.class, treckingCreeper.getAABB(),treckingCreeper.isTamed()? TAMED_TARGET_SELECTOR: NONTAMED_TARGET_SELECTOR);
    }

    @Override
    public boolean canContinueToUse() {
        return timeFinding <= 20 * 15 && !itemStuck && treckingCreeper.getHeldStack().isEmpty();
    }

    @Override
    public boolean canUse() {
        if (!treckingCreeper.getHeldStack().isEmpty())
            return false;
        ItemStack itemstack = treckingCreeper.getHeldStack();
        List<ItemEntity> list = nearbyItems();
        itemStuck = false;
        destList = new ArrayList<>();
        if (itemstack.isEmpty() && !list.isEmpty()) {
            destList.addAll(list);
        }
        if (destList.isEmpty()) {
            return false;
        }
        Collections.shuffle(destList);
        for (ItemEntity e : destList) {
            Path path = treckingCreeper.getNavigation().createPath(new BlockPos((int) e.position().x, (int) e.position().y, (int) e.position().z), 1, 9);
            if (path != null && path.canReach()) {
                this.dest = e;
                break;
            }
        }
        return dest != null && !nearbyItems().isEmpty();
    }

    @Override
    public void tick() {
        super.tick();
        if (dest == null || dest.getItem().isEmpty() || dest.isRemoved()) {
            itemStuck = true;
            return;
        }
        timeFinding++;
        treckingCreeper.getNavigation().stop();
        Path path = treckingCreeper.getNavigation().createPath(new BlockPos((int) dest.position().x, (int) dest.position().y, (int) dest.position().z), 1, 9);
        if (path == null || !path.canReach()) {
            stuckTicks++;
            if (stuckTicks > 20 * 5) { // Give up after 5 seconds of being unpathable, in case we fall or jump into the air
                itemStuck = true;
            }
            return;
        }
        ItemStack itemstack = treckingCreeper.getHeldStack();
        if (!itemstack.isEmpty()) {
            itemStuck = true;
            return;
        }
        treckingCreeper.getNavigation().moveTo(dest, 1.4d);
    }

    @Override
    public boolean isInterruptable() {
        return false;
    }
}