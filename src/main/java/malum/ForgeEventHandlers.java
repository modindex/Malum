package malum;

import malum.capabilities.*;
import malum.event.ServerPlayerEventHandler;
import malum.recipes.BlockTransmutationRecipes;
import malum.recipes.CraftingRecipes;
import malum.recipes.ResourceFormingRecipes;
import malum.recipes.RitualRecipes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

import static malum.capabilities.PlayerProperties.*;

@Mod.EventBusSubscriber
public class ForgeEventHandlers
{
    @SubscribeEvent
    public static void onEntityConstructing(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof PlayerEntity)
        {
            if (!event.getObject().getCapability(PlayerProperties.CAPABILITY).isPresent())
            {
                event.addCapability(new ResourceLocation(MalumMod.MODID, "properties"), new PropertiesDispatcher());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            // We need to copyFrom the capabilities
            LazyOptional<Capabilities> capability = event.getOriginal().getCapability(PlayerProperties.CAPABILITY);
            capability.ifPresent(oldStore -> {
                event.getEntityPlayer().getCapability(PlayerProperties.CAPABILITY).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void registerRecipes(FMLServerStartedEvent event)
    {
        BlockTransmutationRecipes.initRecipes();
        RitualRecipes.initRecipes();
        CraftingRecipes.initRecipes();
        ResourceFormingRecipes.initRecipes();
    }
    @SubscribeEvent
    public static void onCraft(PlayerEvent.ItemCraftedEvent event) {
        setDangerLevelCapped(event.getPlayer(), 1, 1);
        setHate(event.getPlayer(), getHate(event.getPlayer()) + 1);
    }
}