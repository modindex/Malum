package kittykitcatcat.malum.event;

import kittykitcatcat.malum.MalumMod;
import kittykitcatcat.malum.capabilities.PlayerProperties;
import kittykitcatcat.malum.items.gadgets.ItemEnderStaff;
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
public class EnderStaffEvents
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
        }
    }

    @SubscribeEvent
    public static void handleTeleport(TickEvent.PlayerTickEvent event)
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


            int cooldown = -250;
            int distance = 10;
            if (playerEntity.getHeldItemMainhand().getTag() != null)
            {
                if (playerEntity.getHeldItemMainhand().getTag().contains(MalumMod.ender_staff_cooldown_augment))
                {
                    cooldown += 20 * playerEntity.getHeldItemMainhand().getTag().getInt(MalumMod.ender_staff_cooldown_augment);
                }
                if (playerEntity.getHeldItemMainhand().getTag().contains(MalumMod.ender_staff_distance_augment))
                {
                    distance += playerEntity.getHeldItemMainhand().getTag().getInt(MalumMod.ender_staff_distance_augment);
                }
            }

            if (playerEntity.isCrouching())
            {
                for (int i = 8 + (distance) * 4; i >= 8; i--)
                {
                    newPosition = attemptTeleport(playerEntity, direction, newPosition, i / 4);
                }
            }
            else
            {
                for (int i = 0; i <= distance * 4; i++)
                {
                    newPosition = attemptTeleport(playerEntity, direction, newPosition, i / 4);
                }
            }
            playerEntity.teleportKeepLoaded(newPosition.x, newPosition.y, newPosition.z);
            PlayerProperties.setTeleportChargeTime(playerEntity, cooldown);
            playerEntity.world.playSound(playerEntity, playerEntity.getPosition(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1f, 1.5f);
            playerEntity.addStat(Stats.ITEM_USED.get(playerEntity.getActiveItemStack().getItem()));
        }
        if (PlayerProperties.getTeleportChargeTime(playerEntity) < 0)
        {
            PlayerProperties.setIsTeleporting(playerEntity, false);
            PlayerProperties.setTeleportChargeTime(playerEntity, PlayerProperties.getTeleportChargeTime(playerEntity) + 1);
        }
    }

    public static Vec3d attemptTeleport(PlayerEntity playerEntity, Vec3d direction, Vec3d newPosition, int i)
    {
        Vec3d testPosition = (playerEntity.getPositionVec()).add(direction.mul(i, i, i));
        if (!playerEntity.world.checkBlockCollision(new AxisAlignedBB(new Vec3d(testPosition.x, testPosition.y, testPosition.z), new Vec3d(testPosition.x, testPosition.y, testPosition.z))))
        {
            newPosition = testPosition;
        }
        return newPosition;
    }
}