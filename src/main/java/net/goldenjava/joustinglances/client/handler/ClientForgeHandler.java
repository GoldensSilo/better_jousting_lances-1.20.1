package net.goldenjava.joustinglances.client.handler;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.goldenjava.joustinglances.JoustingLancesMod;
import net.goldenjava.joustinglances.client.Keybindings;
import net.goldenjava.joustinglances.network.PacketHandler;
import net.goldenjava.joustinglances.network.packet.SStabEntityPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = JoustingLancesMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeHandler {
    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event){
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            var animation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer) minecraft.player).get(new ResourceLocation(JoustingLancesMod.MODID, "animation"));

            if (Keybindings.INSTANCE.stab.isDown()) {
                PacketHandler.sendToServer(new SStabEntityPacket());

                //Get the animation for that player
                if (animation != null) {
                    //You can set an animation from anywhere ON THE CLIENT
                    //Do not attempt to do this on a server, that will only fail

                    animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(new ResourceLocation("joustinglances", "lance_stab_animation"))));
                    //You might use  animation.replaceAnimationWithFade(); to create fade effect instead of sudden change
                    //See javadoc for details
                }
            }
        }
    }
}
