package net.goldenjava.joustinglances;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = JoustingLancesMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.DoubleValue HORIZONTAL_MULTIPLIER = BUILDER
            .comment("Horizontal velocity multiplier for the lunge enchantment. Values must have a decimal place, the decimal can be 0. Min is 0 Max is 10000.")
            .defineInRange("horizontalMultiplier", 2.0, 0, 10000);

    private static final ForgeConfigSpec.DoubleValue VERTICAL_MULTIPLIER = BUILDER
            .comment("Vertical velocity multiplier for the lunge enchantment. Values must have a decimal place, the decimal can be 0. Min is 0 Max is 10000.")
            .defineInRange("verticalMultiplier", 1.0, 0, 10000);

    private static final ForgeConfigSpec.DoubleValue SPEAR_DAMAGE_MULTIPLIER = BUILDER
            .comment("Vertical velocity multiplier for the lunge enchantment. Values must have a decimal place, the decimal can be 0. Min is 0 Max is 10000.")
            .defineInRange("spearDamageMultiplier", 1.95, 0, 10000);

    private static final ForgeConfigSpec.IntValue LUNGE_COOLDOWN = BUILDER
            .comment("Cooldown (in ticks) of the lunge enchantment's dash. Must be a whole number. Min is 0 Max is 10000.")
            .defineInRange("lungeCooldown", 60, 0, 10000);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static double verticalMultiplier;
    public static double horizontalMultiplier;
    public static double spearDamageMultiplier;
    public static int lungeCooldown;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        verticalMultiplier = VERTICAL_MULTIPLIER.get();
        horizontalMultiplier = HORIZONTAL_MULTIPLIER.get();
        spearDamageMultiplier = SPEAR_DAMAGE_MULTIPLIER.get();
        lungeCooldown = LUNGE_COOLDOWN.get();

    }
}
