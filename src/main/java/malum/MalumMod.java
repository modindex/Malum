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


@Mod("malum")
public class MalumMod
{
    public static final String MODID = "malum";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String drowned_armor_augment = MODID+"drowned";
    public static final String blaze_armor_augment = MODID+"blaze";
    public static final String ender_dragon_armor_augment = MODID+"ender_dragon";
    public static final String ender_staff_distance_augment = MODID+"ender_staff_distance";
    public static final String ender_staff_cooldown_augment = MODID+"ender_staff_cooldown";
    public static final String phantom_necklace_flight_time_augment = MODID+"phantom_necklace_flight_time";
    public static final String soul_eater_boost_upkeep_augment = MODID+"soul_eater_boost_upkeep_augment";
    public static final String soul_eater_boost_gain_augment = MODID+"soul_eater_boost_gain_augment";
    public MalumMod() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::bindTERenderers);
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void setup(final FMLCommonSetupEvent event)
    {
        new ServerPlayerEventHandler();

    }
    @OnlyIn(Dist.CLIENT)
    private void bindTERenderers(final FMLClientSetupEvent event)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(RitualBlockTileEntity.class, new RitualBlockRenderer());
    }

}
