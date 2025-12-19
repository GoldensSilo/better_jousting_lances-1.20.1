package net.goldenjava.joustinglances.events.custom;

import net.goldenjava.joustinglances.JoustingLancesMod;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = JoustingLancesMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {
//    @SubscribeEvent
//    public static void commonSetup(FMLCommonSetupEvent event){
//        event.enqueueWork(() -> {
//            PacketHandler.register();
//        });
//    }

//    public int getUseDuration(ItemStack pStack) {
//        return 72000;
//    }
//
//    @SubscribeEvent
//    public static void onRightClick(PlayerInteractEvent event)
//    {
//
//        Player p = event.getEntity();
//
//        ItemStack stack = p.getItemInHand(InteractionHand.MAIN_HAND); //Check the item in the mainhand
//        Item chosenItem = stack.getItem(); //gets exactly what item the stack found
//
//       boolean canJoust = false;
//       final int duration = 100;
//       final int joustCooldownChecks = 5;
//
//        if(stack != null) //Check to make sure the stack  is not a null value, may be redundant but better safe than sorry
//        {
//            if(stack.is(ModTags.Items.LANCELIKE_ITEM) && !p.getCooldowns().isOnCooldown(chosenItem)) //checks if the item is in the tag
//            {
//                //p.setSecondsOnFire(10); light player on fire if it worked
//                //TODO: Redo the logic as a packet, figure out how to make a ray
//                //cooldown hijinks
//                new java.util.Timer().schedule(new TimerTask(){
//                    //used to cancel the timer after all checks are done
//                    int timerCheck = 1;
//                    @Override
//                    public void run() {
//                        System.out.println("Executed...");
//                        //is the user inside the case zone?
//                        p.sendSystemMessage(Component.literal("Lance Jousting"));
//                        timerCheck--;
//
//
//                        if (timerCheck == 0){
//                            p.stopUsingItem();
//                            p.getCooldowns().addCooldown(chosenItem, 100);
//
//                            System.out.println("Joust now on cooldown");
//                            this.cancel();//cancel timer
//                        }
//                    }
//                },0, 2);
//
//
//               // p.level().broadcastEntityEvent(p, (byte)30);
//            }
//
//            if(stack.is(ModTags.Items.LANCELIKE_ITEM) && p.getCooldowns().isOnCooldown(chosenItem)) //checks if the item is in the tag
//            {
//                p.stopUsingItem();
//            }
//        }
//    }

}