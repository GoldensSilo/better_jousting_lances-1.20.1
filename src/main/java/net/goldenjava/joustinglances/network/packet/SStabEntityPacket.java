package net.goldenjava.joustinglances.network.packet;

import net.goldenjava.joustinglances.util.ModTags;
import net.goldenjava.joustinglances.util.RayChecks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class SStabEntityPacket {
    public SStabEntityPacket(){}

    //make sure you read and write items in the same order
    public void encoding(FriendlyByteBuf buffer){
    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        //Make sure the player exists
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) {
                return;
            }

            //ServerWork
            Level level = player.level();

            //item checks
            ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND); //Check the item in the mainhand
            Item chosenItem = stack.getItem(); //gets exactly what item the stack found
            double totalDamageRaw = player.getAttributeValue(Attributes.ATTACK_DAMAGE); //grab play atk damage
            float castedDamage = ((float) (totalDamageRaw * 0.9)); //cast the double into a float so we can use it later

            //set up points for raycast detection
            Vec3 rayStart = player.getEyePosition();
            Vec3 rayEnd = rayStart.add(player.getForward().scale(7));
            AABB boundingBox = player.getBoundingBox().expandTowards(rayEnd.subtract(rayStart));

            //Funni spear castedSpeed variables
            //get deltas of all movement directions
            double deltaX = player.getX() - player.xOld;
            double deltaY = player.getY() - player.yOld;
            double deltaZ = player.getZ() - player.zOld;

            //(reminds me of pythagoras..)
            double speedRaw = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2) + Math.pow(deltaZ, 2)); //make the castedSpeed a single number
            float castedSpeed = ((float) (speedRaw * 0.8 + 1)); //cast the double into a float so we can use it later

            List<? extends Entity> entities = level.getEntities(player, boundingBox);


            if (stack.is(ModTags.Items.LANCELIKE_ITEM)) {
                for (Entity target : entities) {
                    HitResult hitResult = RayChecks.EntityCheck(target, rayStart, rayEnd, 0.3f);
                    if (hitResult.getType() == HitResult.Type.ENTITY) {

                        target.hurt(target.damageSources().playerAttack(player),  castedDamage * (castedSpeed));

                        target.hurtMarked = true;

                        //TODO: Lower durability on each hit, more than normal attacks
                    }
                }
               // player.setSecondsOnFire(2);
            }
        });
    }
}
