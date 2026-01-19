package net.goldenjava.joustinglances.registry;

import net.goldenjava.joustinglances.JoustingLancesMod;
import net.goldenjava.joustinglances.effect.LungeEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class EffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECT_DEFERRED_REGISTER = DeferredRegister.create(Registries.MOB_EFFECT, JoustingLancesMod.MODID);

    public static void register(IEventBus eventBus) {
        MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
    }

    public static final RegistryObject<MobEffect> LUNGE = MOB_EFFECT_DEFERRED_REGISTER.register("lunge", () -> new LungeEnchantmentEffect(MobEffectCategory.NEUTRAL, 0xffef95) );
}