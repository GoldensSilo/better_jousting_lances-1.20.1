package net.goldenjava.joustinglances.effect;

import net.goldenjava.joustinglances.Config;
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

    private int effectDuration;
    private int multiplier;

    public void applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier){
        this.launchForward(livingEntity);
    }


    private void launchForward(LivingEntity entity) {
        Vec3 forward = entity.getLookAngle();
        var vec = forward.multiply(Config.horizontalMultiplier, Config.verticalMultiplier, Config.horizontalMultiplier).normalize().add(0, 0, 0).scale(multiplier);

        entity.setDeltaMovement(new Vec3(
                Mth.lerp(.65f, entity.getDeltaMovement().x, vec.x),
                Mth.lerp(.6f, entity.getDeltaMovement().y, vec.y),
                Mth.lerp(.65f, entity.getDeltaMovement().z, vec.z)
        ));
    }



    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        multiplier = amplifier;

        this.effectDuration = duration;

        return true;
    }
}
