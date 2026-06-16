package net.goldenjava.joustinglances.network.packet;

import net.goldenjava.joustinglances.Config;
import net.goldenjava.joustinglances.client.handler.ClientForgeHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CStabHitPacket {
    public CStabHitPacket(){}

    //make sure you read and write items in the same order
    public void encoding(FriendlyByteBuf buffer){
    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        //Make sure the player exists
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player == null) {
                return;
            }
            //Clienside
            Level level = player.level();

            ClientForgeHandler.stabTimer = Config.spearStabTimer - Config.stabHitCooldown;
            ClientForgeHandler.canStab = false;
            //player.displayClientMessage(Component.literal(String.valueOf(ClientForgeHandler.stabTimer)), true);
            //player.displayClientMessage(Component.literal(String.valueOf(ClientForgeHandler.canStab)), true);

        });
    }
}
