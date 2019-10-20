package malum;

import malum.capabilities.*;
import malum.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.logging.Logger;

@Mod.EventBusSubscriber
public class ForgeEventHandlers
{
    @SubscribeEvent
    public static void onEntityConstructing(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof PlayerEntity) {
            if (!event.getObject().getCapability(PlayerProperties.PLAYER_MADE_DOLL).isPresent()) {
                event.addCapability(new ResourceLocation(MalumMod.MODID, "properties"), new PropertiesDispatcher());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            // We need to copyFrom the capabilities
            LazyOptional<PlayerMadeDoll> capability = event.getOriginal().getCapability(PlayerProperties.PLAYER_MADE_DOLL);
            capability.ifPresent(oldStore -> {
                event.getEntityPlayer().getCapability(PlayerProperties.PLAYER_MADE_DOLL).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
    @SubscribeEvent
    public static void onCraft(PlayerEvent.ItemCraftedEvent event) {

        event.getPlayer().getCapability(PlayerProperties.PLAYER_MADE_DOLL).ifPresent(note ->
        {
            if (note.hasPlayerMadeDoll() < 1)
            {
                note.setPlayerMadeDoll(1);
            }
        });
        if (event.getPlayer().isServerWorld())
        {
            int returnValue = event.getPlayer().getCapability(PlayerProperties.PLAYER_MADE_DOLL).map(PlayerMadeDoll::hasPlayerMadeDoll).orElse(0);
            TranslationTextComponent serverSideMessage = new TranslationTextComponent("server_side: " + returnValue);
            event.getPlayer().sendMessage(serverSideMessage);
        }
    }
}