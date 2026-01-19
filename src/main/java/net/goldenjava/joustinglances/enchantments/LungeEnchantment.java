package net.goldenjava.joustinglances.enchantments;

import net.goldenjava.joustinglances.JoustingLancesMod;
import net.goldenjava.joustinglances.registry.EffectRegistry;
import net.goldenjava.joustinglances.util.ModTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class LungeEnchantment extends Enchantment {

    public static EnchantmentCategory JOUSTING_ITEM = EnchantmentCategory.create(JoustingLancesMod.MODID + ":joustable", (item) -> item.getDefaultInstance().is(ModTags.Items.LANCELIKE_ITEM));

    public LungeEnchantment(Rarity pRarity, EnchantmentCategory enchantmentCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, JOUSTING_ITEM, pApplicableSlots);
    }

    public boolean canEnchant(ItemStack pStack) {
        return pStack.is(ModTags.Items.LANCELIKE_ITEM);
    }


    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinCost(int pEnchantmentLevel) {
        return 20;
    }

    @Override
    public int getMaxCost(int pEnchantmentLevel) {
        return 50;
    }

    @Override
    public void doPostAttack(LivingEntity pUser, @NotNull Entity pTarget, int pLevel) {
    }

}

