package net.goldenjava.joustinglances.network.packet;

import net.goldenjava.joustinglances.util.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.*;
import net.minecraftforge.network.NetworkEvent;

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
            Vec3 playerPos = player.getPosition(1);
            Vec3 playerView = player.getViewVector(1);
            ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND); //Check the item in the mainhand
            Item chosenItem = stack.getItem(); //gets exactly what item the stack found
            LivingEntity pLivingEntity;
            //AABB lanceHitbox = player.getBoundingBox();



            if (stack.is(ModTags.Items.LANCELIKE_ITEM)) {
                player.setSecondsOnFire(2);

            }
        });

    }
}
