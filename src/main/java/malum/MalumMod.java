package malum;

import malum.event.ServerPlayerEventHandler;
import malum.network.DangerLevelPacket;
import malum.network.HatePacket;
import malum.network.NetworkManager;
import malum.network.PermaHatePacket;
import malum.tileentities.CraftingBlockTileEntity;
import malum.tileentities.RitualBlockTileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


@Mod("malum")
public class MalumMod
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "malum";
    public MalumMod() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void setup(final FMLCommonSetupEvent event)
    {
        // register packets
        int packetID=0;
        NetworkManager.INSTANCE.registerMessage(packetID++,
            DangerLevelPacket.class,
            DangerLevelPacket::encode,
            DangerLevelPacket::decode,
            DangerLevelPacket::whenThisPacketIsReceived
        );
        NetworkManager.INSTANCE.registerMessage(packetID++,
            HatePacket.class,
            HatePacket::encode,
            HatePacket::decode,
            HatePacket::whenThisPacketIsReceived
        );
        NetworkManager.INSTANCE.registerMessage(packetID++,
            PermaHatePacket.class,
            PermaHatePacket::encode,
            PermaHatePacket::decode,
            PermaHatePacket::whenThisPacketIsReceived
        );
        new ServerPlayerEventHandler();
    }
    @OnlyIn(Dist.CLIENT)
    private void doClientStuff(final FMLClientSetupEvent event)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(CraftingBlockTileEntity.class, new malum.CraftingBlockRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(RitualBlockTileEntity.class, new malum.RitualBlockRenderer());
    }
    public static double randomize(double value, double power)
    {
        Random random = new Random();
        double randDouble = random.nextDouble() * power;
        value += randDouble * (random.nextDouble() > 0.5 ? -1 : 1);
        return value;
    }
}
