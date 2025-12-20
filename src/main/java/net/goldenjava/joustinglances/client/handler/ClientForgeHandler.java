package net.goldenjava.joustinglances.client.handler;

import net.goldenjava.joustinglances.JoustingLancesMod;
import net.goldenjava.joustinglances.client.Keybindings;
import net.goldenjava.joustinglances.network.PacketHandler;
import net.goldenjava.joustinglances.network.packet.SStabEntityPacket;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = JoustingLancesMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeHandler {
    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event){
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player != null && Keybindings.INSTANCE.stab.isDown()) {
            PacketHandler.sendToServer(new SStabEntityPacket());
        }
    }
}
