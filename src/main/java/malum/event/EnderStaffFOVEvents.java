package malum.event;

import malum.MalumMod;
import malum.capabilities.PlayerProperties;
import malum.items.gadgets.ItemEnderStaff;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EnderStaffFOVEvents
{
    @SubscribeEvent
    public static void updateFOV(FOVUpdateEvent event)
    {
        PlayerEntity playerEntity = event.getEntity();
        if (playerEntity.getHeldItemMainhand().getItem() instanceof ItemEnderStaff)
        {
            if (PlayerProperties.getTeleportChargeTime(playerEntity) >= 0)
            {
                event.setNewfov((float) (1f - PlayerProperties.getTeleportChargeTime(playerEntity) / 15));
            }
            else
            {
                event.setNewfov(1f);
            }
        }
    }
    @SubscribeEvent
    public static void handleFOV(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity playerEntity = event.player;
        if (PlayerProperties.getTeleportChargeTime(playerEntity) > 0)
        {
            if (!(playerEntity.getHeldItemMainhand().getItem() instanceof ItemEnderStaff))
            {
                PlayerProperties.setTeleportChargeTime(playerEntity, PlayerProperties.getTeleportChargeTime(playerEntity) > 0 ? PlayerProperties.getTeleportChargeTime(playerEntity) - 1 : 0);
            }
        }
        if (PlayerProperties.getTeleportChargeTime(playerEntity) >= 15)
        {
            float yaw = playerEntity.rotationYawHead;
            float pitch = playerEntity.rotationPitch;
            float f = -MathHelper.sin(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
            float f1 = -MathHelper.sin(pitch * ((float) Math.PI / 180F));
            float f2 = MathHelper.cos(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));

            Vec3d direction = new Vec3d(f, f1, f2);
            Vec3d newPosition = (playerEntity.getPositionVec());
            for (int i = 1; i < 30; i++)
            {
                Vec3d testPosition = (playerEntity.getPositionVec()).add(direction.mul(i,i,i));
                MalumMod.LOGGER.info(testPosition);
                MalumMod.LOGGER.info(playerEntity.world.checkBlockCollision(new AxisAlignedBB(testPosition.subtract(0.5,0.5,0.5), testPosition.add(0.5,0.5,0.5))));
                if (!playerEntity.world.checkBlockCollision(new AxisAlignedBB(testPosition.subtract(0.5,0.5,0.5), testPosition.add(0.5,0.5,0.5))))
                {
                    newPosition = testPosition;
                }
            }
            playerEntity.teleportKeepLoaded(newPosition.x, newPosition.y, newPosition.z);
            PlayerProperties.setTeleportChargeTime(playerEntity, -200);
            playerEntity.world.playSound(playerEntity, playerEntity.getPosition(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1f, 1.5f);
            playerEntity.addStat(Stats.ITEM_USED.get(playerEntity.getActiveItemStack().getItem()));
        }
        if (PlayerProperties.getTeleportChargeTime(playerEntity) < 0)
        {
            PlayerProperties.setCanTeleport(playerEntity, false);
            PlayerProperties.setTeleportChargeTime(playerEntity, PlayerProperties.getTeleportChargeTime(playerEntity)+1);
        }
    }
}