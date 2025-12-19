package net.goldenjava.joustinglances.network;

import net.goldenjava.joustinglances.JoustingLancesMod;
import net.goldenjava.joustinglances.network.packet.SStabEntityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static int packedID = 0;

    private static int id() {
        return packedID++;
    }

    private static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(
            new ResourceLocation(JoustingLancesMod.MODID, "main"))
            .serverAcceptedVersions(s -> true)
            .clientAcceptedVersions(s -> true)
            .networkProtocolVersion(() -> "1")
            .simpleChannel();

    public static void register(){
        INSTANCE.messageBuilder(SStabEntityPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(SStabEntityPacket::encoding)
                .decoder(pos -> new SStabEntityPacket())
                .consumerMainThread(SStabEntityPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }
}
