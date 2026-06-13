package net.goldenjava.joustinglances.client.handler;

import dev.kosmx.playerAnim.api.firstPerson.FirstPersonMode;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.goldenjava.joustinglances.Config;
import net.goldenjava.joustinglances.JoustingLancesMod;
import net.goldenjava.joustinglances.client.Keybindings;
import net.goldenjava.joustinglances.network.PacketHandler;
import net.goldenjava.joustinglances.network.packet.SStabEntityPacket;
import net.goldenjava.joustinglances.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

//TODO Add Cooldown Indicator?
@Mod.EventBusSubscriber(modid = JoustingLancesMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeHandler {
    static int stabTimer = Config.spearStabTimer;
    static boolean canStab = true;

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event){
        Minecraft minecraft = Minecraft.getInstance();
        //tickTracker++;
        if (minecraft.player != null) {
            var animation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer) minecraft.player).get(ResourceLocation.fromNamespaceAndPath(JoustingLancesMod.MODID, "animation"));
            if (Keybindings.INSTANCE.stab.isDown() && canStab){
                    PacketHandler.sendToServer(new SStabEntityPacket());

                    ItemStack handCheck = minecraft.player.getItemInHand(InteractionHand.MAIN_HAND);

                    //Get the animation for that player
                    if (animation != null) {
                        if (handCheck.is(ModTags.Items.LANCELIKE_ITEM)){
                            //You can set an animation from anywhere ON THE CLIENT
                            //Do not attempt to do this on a server, that will only fail

                            animation.setAnimation(new KeyframeAnimationPlayer(Objects.requireNonNull(PlayerAnimationRegistry.getAnimation(ResourceLocation.fromNamespaceAndPath("joustinglances", "lance_stab_animation")))).setFirstPersonMode(FirstPersonMode.THIRD_PERSON_MODEL));
                            //You might use  animation.replaceAnimationWithFade(); to createSpace fade effect instead of sudden change
                            //See javadoc for details
                        }
                    }

                if (handCheck.is(ModTags.Items.LANCELIKE_ITEM)){
                    //Decrease stab timer
                    stabTimer--;
                   // minecraft.player.sendSystemMessage(Component.literal(String.valueOf(stabTimer)));
                }
            }


            //trigger cooldown even when end of stab timer isn't reached
            //Count it down
            if (stabTimer <= 0 || !Keybindings.INSTANCE.stab.isDown() && stabTimer < Config.spearStabTimer){
                canStab = false;
               // minecraft.player.sendSystemMessage(Component.literal("CAN'T STAB!"));
            }

            //Restore Stab Counter
            if (!canStab){
                stabTimer++;
                if (stabTimer == Config.spearStabTimer){
                    canStab = true;
                }
            }
        }
    }
}
