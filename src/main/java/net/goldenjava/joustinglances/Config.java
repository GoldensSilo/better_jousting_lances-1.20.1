package net.goldenjava.joustinglances;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = JoustingLancesMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue HORIZONTAL_MULTIPLIER = BUILDER
            .comment("Horizontal multiplier for the lunge enchantment. Min is 0 Max is 10000.")
            .defineInRange("horizontalMultiplier", 2, 0, 10000);

    private static final ForgeConfigSpec.IntValue VERTICAL_MULTIPLIER = BUILDER
            .comment("Vertical multiplier for the lunge enchantment. Min is 0 Max is 10000.")
            .defineInRange("verticalMultiplier", 1, 0, 10000);

    private static final ForgeConfigSpec.IntValue LUNGE_COOLDOWN = BUILDER
            .comment("Cooldown (in ticks) of the lunge enchantment's dash. Min is 0 Max is 10000.")
            .defineInRange("lungeCooldown", 60, 0, 10000);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static double verticalMultiplier;
    public static double horizontalMultiplier;
    public static int lungeCooldown;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        verticalMultiplier = VERTICAL_MULTIPLIER.get();
        horizontalMultiplier = HORIZONTAL_MULTIPLIER.get();
        lungeCooldown = LUNGE_COOLDOWN.get();

    }
}
