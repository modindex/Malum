package malum.event;

import malum.MalumMod;
import malum.capabilities.crystalSpotCapability.CrystalSpotCapabilityProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= MalumMod.MODID, bus= Mod.EventBusSubscriber.Bus.FORGE)
public class AttachCapabilitiesEvents
{

    public static final ResourceLocation VOID_CRYSTAL_DRAIN = new ResourceLocation(MalumMod.MODID, "void_crystal_drain");
    @SubscribeEvent
    public static void AttachCapabilities(AttachCapabilitiesEvent<Chunk> event)
    {
        Chunk chunk = event.getObject();
        int x = (int)(Math.random() * 16);
        int z = (int)(Math.random() * 16);
        int y = chunk.getTopBlockY(Heightmap.Type.MOTION_BLOCKING, x,z);

        BlockPos drainSpotPos = new BlockPos(x + chunk.getPos().x * 16,y,z + chunk.getPos().z * 16);
        /*CrystalSpotCapability instance = new CrystalSpotCapability();
        instance.setBlockPos(this.pos);
        instance.setPotency(this.potency);
        MalumMod.LOGGER.info(instance.pos());
        MalumMod.LOGGER.info(instance.potency());
        */
        event.addCapability(VOID_CRYSTAL_DRAIN, new CrystalSpotCapabilityProvider());
    }
}
