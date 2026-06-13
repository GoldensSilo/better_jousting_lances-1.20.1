package net.goldenjava.joustinglances.network.packet;

import net.goldenjava.joustinglances.Config;
import net.goldenjava.joustinglances.registry.EffectRegistry;
import net.goldenjava.joustinglances.registry.EnchantmentRegistry;
import net.goldenjava.joustinglances.util.ModTags;
import net.goldenjava.joustinglances.util.RayChecks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
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
            float castedDamage = ((float) ((totalDamageRaw) + Config.spearBaseDamage)); //cast the double into a float so we can use it later

            //set up points for raycast detection
            Vec3 rayStart = player.getEyePosition();
            Vec3 rayEnd = rayStart.add(player.getForward().scale(Config.spearStabRange));
            AABB boundingBox = player.getBoundingBox().expandTowards(rayEnd.subtract(rayStart));

            //Funni spear castedSpeed variables
            //get deltas of all movement directions
            double deltaX = player.getX() - player.xOld;
            double deltaY = player.getY() - player.yOld;
            double deltaZ = player.getZ() - player.zOld;

            //(reminds me of pythagoras..)
            double speedRaw = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2) + Math.pow(deltaZ, 2)); //make the castedSpeed a single number
            float castedSpeed = ((float) (speedRaw * Config.spearDamageMultiplier)); //cast the double into a float so we can use it later

            List<? extends Entity> entities = level.getEntities(player, boundingBox);

            //Lance logic
            if (stack.is(ModTags.Items.LANCELIKE_ITEM)) {
                for (Entity target : entities) {
                    HitResult hitResult = RayChecks.EntityCheck(target, rayStart, rayEnd, 0.1f);
                    if (hitResult.getType() == HitResult.Type.ENTITY && (player.tickCount % 5) == 0) {

                        //get target's speed
                        double tdeltaX = target.getX() - target.xOld;
                        double tdeltaY = target.getY() - target.yOld;
                        double tdeltaZ = target.getZ() - target.zOld;

                        //(reminds me of pythagoras..)
                        double tspeedRaw = Math.sqrt(Math.pow(tdeltaX, 2) + Math.pow(tdeltaY, 2) + Math.pow(tdeltaZ, 2)); //make the castedSpeed a single number
                        float tcastedSpeed = ((float) (tspeedRaw * Config.spearDamageMultiplier)); //cast the double into a float so we can use it later

                        /* ****************************************************************************************** */

                        if (player.getControlledVehicle() != null){
                            if (player.getLookAngle().y > -0.35){
                                target.hurt(target.damageSources().playerAttack(player), castedDamage + (castedDamage * castedSpeed) + (castedDamage * tcastedSpeed));
                                target.hurtMarked = true;

                                if ( !player.isCreative() && stack.isDamageableItem()){
                                    stack.setDamageValue(stack.getDamageValue() + 1);

                                    if(stack.isDamageableItem() && stack.getDamageValue() >= stack.getMaxDamage()){
                                        stack.setCount(0);
                                    }
                                }
                            }
                        } else {
                            target.hurt(target.damageSources().playerAttack(player), castedDamage + (castedDamage * castedSpeed) + (castedDamage * tcastedSpeed));
                            target.hurtMarked = true;

                            if ( !player.isCreative() && stack.isDamageableItem()){
                                stack.setDamageValue(stack.getDamageValue() + 1);

                                if(stack.isDamageableItem() && stack.getDamageValue() >= stack.getMaxDamage()){
                                    stack.setCount(0);
                                }
                            }
                        }
                    }

                }
                //Lunge Enchant stuff
                int joustLevel = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.LUNGE.get(), player);
                if (joustLevel > 0 && !player.getCooldowns().isOnCooldown(chosenItem)){
                    player.addEffect(new MobEffectInstance(EffectRegistry.LUNGE.get(), 2, joustLevel, false, false, false));
                    player.getCooldowns().addCooldown(chosenItem, Config.lungeCooldown);
                    if (!player.isCreative()){
                        chosenItem.setDamage(stack, chosenItem.getDamage(stack) + 1);
                    }
                }
            }
        });
    }
}
