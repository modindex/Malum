package malum;

import malum.capabilities.Capabilities;
import malum.capabilities.PlayerProperties;
import malum.capabilities.PropertiesDispatcher;
import malum.recipes.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.minecraftforge.event.TickEvent.Phase.START;

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
        SpiritInfusionRecipes.initRecipes();
    }
    @SubscribeEvent
    public static void ChunkLoad(TickEvent.WorldTickEvent event)
    {
        if (event.phase == START && event.world instanceof ServerWorld)
        {
            List<LivingEntity> entities = new ArrayList<>();
            for (Entity e : ((ServerWorld) event.world).getEntities().collect(Collectors.toList()))
            {
                if (e instanceof LivingEntity)
                {
                    entities.add((LivingEntity) e);
                }
            }

            for (LivingEntity entity : entities)
            {
                if (entity.getTags().contains("cursed"))
                {
                    ((ServerWorld) event.world).forceChunk(entity.chunkCoordX, entity.chunkCoordZ, true);
                }
            }
        }
    }
}