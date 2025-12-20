package net.goldenjava.joustinglances.network.packet;

import net.goldenjava.joustinglances.util.ModTags;
import net.goldenjava.joustinglances.util.RayChecks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
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

            //set up points for raycast detection
            Vec3 rayStart = player.getEyePosition();
            Vec3 rayEnd = rayStart.add(player.getForward().scale(7));
            AABB boundingBox = player.getBoundingBox().expandTowards(rayEnd.subtract(rayStart));

            //Funni spear speed variables
            float playerSpeedCheck = player.getSpeed() * 0.4f; //takes 40% of your speed

            List<? extends Entity> entities = level.getEntities(player, boundingBox);

            if (stack.is(ModTags.Items.LANCELIKE_ITEM)) {
                for (Entity target : entities) {
                    HitResult hitResult = RayChecks.EntityCheck(target, rayStart, rayEnd, 1f);
                    if (hitResult.getType() == HitResult.Type.ENTITY) {

                        //TODO: setup proper movement multiplier
                        target.hurt(target.damageSources().playerAttack(player),  2f + (playerSpeedCheck));

                        target.hurtMarked = true;

                    }
                }
               // player.setSecondsOnFire(2);

            }
        });

    }
}
