package net.goldenjava.joustinglances.registry;

import net.goldenjava.joustinglances.JoustingLancesMod;
import net.goldenjava.joustinglances.enchantments.LungeEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentRegistry {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, JoustingLancesMod.MODID);

    public static final RegistryObject<Enchantment> LUNGE = ENCHANTMENTS.register("lunge", () -> new LungeEnchantment(Enchantment.Rarity.UNCOMMON, LungeEnchantment.JOUSTING_ITEM, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
}
