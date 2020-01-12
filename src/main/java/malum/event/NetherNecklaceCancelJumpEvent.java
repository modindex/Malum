package malum.event;

import malum.items.curios.ItemNetherNecklace;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosAPI;

@Mod.EventBusSubscriber
public class NetherNecklaceCancelJumpEvent
{

    @SubscribeEvent
    public static void NetherNecklaceCancelJumpEvent(LivingEvent.LivingJumpEvent event)
    {
        LivingEntity entityLivingBase = event.getEntityLiving();
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemNetherNecklace, entityLivingBase).isPresent())
        {
            entityLivingBase.setVelocity(entityLivingBase.getMotion().x, 0, entityLivingBase.getMotion().z);
        }
    }
}