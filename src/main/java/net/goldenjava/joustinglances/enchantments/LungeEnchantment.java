package net.goldenjava.joustinglances.enchantments;

import net.goldenjava.joustinglances.JoustingLancesMod;
import net.goldenjava.joustinglances.util.ModTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

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
    public int getMinCost(int pLevel) {
        return 2 + pLevel * 5;
    }

    @Override
    public int getMaxCost(int pLevel) {
        return this.getMinCost(pLevel) * 2 + 7;
    }
}

