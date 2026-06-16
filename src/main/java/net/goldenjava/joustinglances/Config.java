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
            .comment("Horizontal velocity multiplier for the lunge enchantment. Values must have a decimal place, the decimal can be 0. Min is 0 Max is 10000. [Default : 2.0]")
            .defineInRange("horizontalMultiplier", 2.0, 0, 10000);

    private static final ForgeConfigSpec.DoubleValue VERTICAL_MULTIPLIER = BUILDER
            .comment("Vertical velocity multiplier for the lunge enchantment. Values must have a decimal place, the decimal can be 0. Min is 0 Max is 10000. [Default : 1.0]")
            .defineInRange("verticalMultiplier", 1.0, 0, 10000);

    private static final ForgeConfigSpec.IntValue LUNGE_COOLDOWN = BUILDER
            .comment("Cooldown (in ticks) of the lunge enchantment's dash. Must be a whole number. Min is 0 Max is 10000. [Default : 60]")
            .defineInRange("lungeCooldown", 60, 0, 10000);

    private static final ForgeConfigSpec.IntValue STAB_HIT_COOLDOWN = BUILDER
            .comment("How long (in ticks) the spear is unable to stab after a hit. Must be a whole number. Min is 0 Max is 10000. [Default : 20]")
            .defineInRange("stabHitCooldown", 20, 0, 10000);

    private static final ForgeConfigSpec.IntValue SPEAR_STAB_TIMER = BUILDER
            .comment("How long a player can hold out their spear. Stab cooldown is proportional to how long it's held. Must be a whole number. Min is 0 Max is 10000. [Default : 100]")
            .defineInRange("spearStabTimer", 100, 0, 10000);

    private static final ForgeConfigSpec.DoubleValue SPEAR_DAMAGE_MULTIPLIER = BUILDER
            .comment("The multiplier applied on lance movement damage. Values must have a decimal place, the decimal can be 0. Min is 0 Max is 10000. [Default : 1.95]")
            .defineInRange("spearDamageMultiplier", 1.95, 0, 10000);

    private static final ForgeConfigSpec.IntValue SPEAR_BASE_DAMAGE = BUILDER
            .comment("How much damage the lance does regardless of movement. Must be a whole number. Min is -10000 Max is 10000. [Default : 1]")
            .defineInRange("spearBaseDamage", 1, -10000, 10000);

    private static final ForgeConfigSpec.DoubleValue SPEAR_STAB_RANGE = BUILDER
            .comment("How far a lance can stab. Values must have a decimal place, the decimal can be 0. Min is 0 Max is 10000. [Default : 8.0]")
            .defineInRange("spearStabRange", 8.0, 0, 10000);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static double verticalMultiplier;
    public static double horizontalMultiplier;
    public static int lungeCooldown;
    public static int stabHitCooldown;
    public static int spearStabTimer;
    public static double spearDamageMultiplier;
    public static double spearBaseDamage;
    public static double spearStabRange;




    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        verticalMultiplier = VERTICAL_MULTIPLIER.get();
        horizontalMultiplier = HORIZONTAL_MULTIPLIER.get();
        lungeCooldown = LUNGE_COOLDOWN.get();
        stabHitCooldown = STAB_HIT_COOLDOWN.get();
        spearStabTimer = SPEAR_STAB_TIMER.get();
        spearDamageMultiplier = SPEAR_DAMAGE_MULTIPLIER.get();
        spearBaseDamage = SPEAR_BASE_DAMAGE.get();
        spearStabRange = SPEAR_STAB_RANGE.get();
    }
}
