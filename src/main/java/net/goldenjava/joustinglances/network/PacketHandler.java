package net.goldenjava.joustinglances.network;

import net.goldenjava.joustinglances.JoustingLancesMod;
import net.goldenjava.joustinglances.network.packet.CStabHitPacket;
import net.goldenjava.joustinglances.network.packet.SStabEntityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static int packedID = 0;

    private static int id() {
        return packedID++;
    }

    private static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(
            ResourceLocation.fromNamespaceAndPath(JoustingLancesMod.MODID, "main"))
            .serverAcceptedVersions(s -> true)
            .clientAcceptedVersions(s -> true)
            .networkProtocolVersion(() -> "1")
            .simpleChannel();

    public static void register(){
        // Stabby
        INSTANCE.messageBuilder(SStabEntityPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(SStabEntityPacket::encoding)
                .decoder(pos -> new SStabEntityPacket())
                .consumerMainThread(SStabEntityPacket::handle)
                .add();
        //Hit cooldown
        INSTANCE.messageBuilder(CStabHitPacket.class, id() + 1, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(CStabHitPacket::encoding)
                .decoder(pos -> new CStabHitPacket())
                .consumerMainThread(CStabHitPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
