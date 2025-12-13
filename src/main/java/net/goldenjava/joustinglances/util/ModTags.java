package net.goldenjava.joustinglances.util;

import net.goldenjava.joustinglances.JoustingLancesMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

    public static class Items{

        public static final TagKey<Item> LANCELIKE_ITEM = tag("lancelike_item");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(JoustingLancesMod.MODID, name));
        }

    }
}
