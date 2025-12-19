package net.goldenjava.joustinglances.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.goldenjava.joustinglances.JoustingLancesMod;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.MouseHandler;
import net.minecraftforge.client.settings.KeyConflictContext;

public class Keybindings {
    public static final Keybindings INSTANCE = new Keybindings();

    private  Keybindings() {}

    private static final String MOD_CATEGORY = "key.categories." + JoustingLancesMod.MODID;


    public final KeyMapping stab = new KeyMapping(
            "key." + JoustingLancesMod.MODID + ".stab",
            KeyConflictContext.IN_GAME, InputConstants.getKey(InputConstants.MOUSE_BUTTON_RIGHT, -1),
            MOD_CATEGORY
    );
}
