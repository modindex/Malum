package malum.event;

import malum.capabilities.PlayerProperties;
import malum.items.gadgets.ItemEnderStaff;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class EnderStaffFOVEvents
{
    @SubscribeEvent
    public static void manageFOV(FOVUpdateEvent event)
    {
        PlayerEntity playerEntity = event.getEntity();
        ItemStack stack = playerEntity.getActiveItemStack();
        if (stack.getItem() instanceof ItemEnderStaff)
        {
            event.setNewfov((float) (event.getFov() - PlayerProperties.getTeleportChargeTime(playerEntity)));
        }
    }
    @SubscribeEvent
    public static void resetFOV(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity playerEntity = event.player;
        if (PlayerProperties.getTeleportChargeTime(playerEntity) > 0)
        {
            if (!(playerEntity.getActiveItemStack().getItem() instanceof ItemEnderStaff))
            {
                PlayerProperties.setTeleportChargeTime(playerEntity, PlayerProperties.getTeleportChargeTime(playerEntity) > 0 ? PlayerProperties.getTeleportChargeTime(playerEntity)-1 : 0);
            }
        }
    }
}