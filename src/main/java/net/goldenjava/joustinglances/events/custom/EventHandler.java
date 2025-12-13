package net.goldenjava.joustinglances.events.custom;

import net.goldenjava.joustinglances.JoustingLancesMod;
import net.goldenjava.joustinglances.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.Console;

@Mod.EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent event)
    {
        Player p = event.getEntity();
        ItemStack stack = p.getInventory().getSelected();


        if(stack != null) //Check to make sure the ItemStack gotten is not null.
        {
            //if(stack.is(ModTags.Items.LANCELIKE_ITEM))

            if(stack.is(ModTags.Items.LANCELIKE_ITEM)) // this is correct, well as long as you registered the item #cell_phone correctly
            {
                p.setSecondsOnFire(3); //Honestly I prefer Console.out().Println("Am I holding a Phone?!?"); rather than directly causing things to happen
            }
        }
    }

}