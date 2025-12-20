package net.goldenjava.joustinglances.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class RayChecks {

    public static HitResult EntityCheck(Entity entity, Vec3 start, Vec3 end, float bbInflation) {
        Vec3 hitPos = null;

            hitPos = entity.getBoundingBox().inflate(bbInflation).clip(start, end).orElse(null);

        if (hitPos != null)
            return new EntityHitResult(entity, hitPos);
        else
            return BlockHitResult.miss(end, Direction.UP, BlockPos.containing(end));

    }

}
