package malum;

import malum.event.ServerPlayerEventHandler;
import malum.renderer.RitualBlockRenderer;
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
        /* register packets
        int packetID=0;
        NetworkManager.INSTANCE.registerMessage(packetID++,
            DummyPacket.class,
            DummyPacket::encode,
            DummyPacket::decode,
            DummyPacket::whenThisPacketIsReceived
        );*/
        new ServerPlayerEventHandler();
    }
    @OnlyIn(Dist.CLIENT)
    private void doClientStuff(final FMLClientSetupEvent event)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(RitualBlockTileEntity.class, new RitualBlockRenderer());
    }
    public static double randomize(double value, double power)
    {
        Random random = new Random();
        double randDouble = random.nextDouble() * power;
        value += randDouble * (random.nextDouble() > 0.5 ? -1 : 1);
        return value;
    }
}
