package net.goldenjava.joustinglances.effect;

import net.goldenjava.joustinglances.enchantments.LungeEnchantment;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class LungeEnchantmentEffect extends MobEffect {
    public LungeEnchantmentEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    //gotta figure out the nausea shader at some point, it would make the spell perfect

    private int effectDuration;
    private int multiplier;

    public void applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier){
        this.launchForward(livingEntity);
    }


    private void launchForward(LivingEntity entity) {
//        double newMotionDirection = 0.6;

//        this.motionDirection = 0.4 * newMotionDirection + 0.8 * this.motionDirection;
//        entity.setDeltaMovement(entity.getDeltaMovement().add(this.motionDirection.x, 0, this.motionDirection.y));

        Vec3 forward = entity.getLookAngle();
        var vec = forward.multiply(2, 0, 2).normalize().add(0, 0, 0).scale(multiplier);

        entity.setDeltaMovement(new Vec3(
                Mth.lerp(.85f, entity.getDeltaMovement().x, vec.x),
                Mth.lerp(.0f, entity.getDeltaMovement().y, vec.y),
                Mth.lerp(.85f, entity.getDeltaMovement().z, vec.z)
        ));
    }



    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        multiplier = amplifier + 1;

        this.effectDuration = duration;

        return true;
    }
}
