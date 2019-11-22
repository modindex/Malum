package malum;

import malum.event.ServerPlayerEventHandler;
import malum.ingredients.SpiritIngredient;
import malum.network.DangerLevelPacket;
import malum.network.HatePacket;
import malum.network.NetworkManager;
import malum.network.PermaHatePacket;
import malum.recipes.BlockTransmutationRecipes;
import malum.recipes.RitualRecipes;
import malum.registry.ModBlocks;
import malum.tileentities.RitualBlockTileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;


@Mod("malum")
public class MalumMod
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "malum";
    public MalumMod() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(IRecipeSerializer.class, (RegistryEvent.Register<IRecipeSerializer<?>> e) -> {
            CraftingHelper.register(new ResourceLocation("malum:spirit"), SpiritIngredient.Serializer.INSTANCE);
        });

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
        BlockTransmutationRecipes.initRecipes();
        RitualRecipes.initRecipes();
        new ServerPlayerEventHandler();
    }
    @OnlyIn(Dist.CLIENT)
    private void doClientStuff(final FMLClientSetupEvent event)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(RitualBlockTileEntity.class, new SpriteRenderer());
    }
}
