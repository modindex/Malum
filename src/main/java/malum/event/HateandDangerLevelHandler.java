package malum.event;

import malum.ClientRefferences;
import malum.capabilities.PlayerProperties;
import malum.items.curios.*;
import malum.items.gadgets.ItemVoodoDoll;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosAPI;

import java.util.Random;

@Mod.EventBusSubscriber()
public class HateandDangerLevelHandler
{
    public static float getDangerLevel(PlayerEntity target)
    {
        PlayerEntity player = ClientRefferences.getClientPlayer();
        float returnValue;
        //send a packet here from target to clientPlayer
        returnValue = PlayerProperties.getDangerLevel(target);
        for (int i = 0; i < (target).inventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = (target).inventory.getStackInSlot(i);
            if (!itemstack.isEmpty())
            {
                if (itemstack.getItem() instanceof ItemVoodoDoll)
                {
                    returnValue = 3;
                }
            }
        }
        if (player != null)
        {
            if (player.getDisplayName().getFormattedText().equals(target.getDisplayName().getFormattedText()))
            {
                returnValue = 0;
            }
        }
        return returnValue;
    }
    public static double randomizePos(double value, double power)
    {
        Random random = new Random();
        double randDouble = random.nextDouble() * power;
        value += randDouble * (random.nextDouble() > 0.5 ? -1 : 1);
        return value;
    }
    @SubscribeEvent
    public static void TellDangerLevel(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = ClientRefferences.getClientPlayer();
        if (event.player == null)
        {
            return;
        }

        PlayerEntity targetPlayer = event.player;
        float dangerLevel = getDangerLevel(targetPlayer);
        if (player != null)
        {
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemEnderSightNecklace, player).isPresent())
            {
                double basePosX = targetPlayer.posX;
                double basePosY = targetPlayer.posY + targetPlayer.getHeight();
                double basePosZ = targetPlayer.posZ;
                if (dangerLevel >= 1) {
                    player.getEntityWorld().addParticle(ParticleTypes.PORTAL, randomizePos(basePosX, 0.4D), randomizePos(basePosY, 0.4D), randomizePos(basePosZ, 0.4D), 0, 0, 0);
                }
                if (dangerLevel >= 2) {
                    player.getEntityWorld().addParticle(ParticleTypes.PORTAL, randomizePos(basePosX, 0.4D), randomizePos(basePosY, 0.4D), randomizePos(basePosZ, 0.4D), 0, 0, 0);
                }
                if (dangerLevel >= 3) {
                    player.getEntityWorld().addParticle(ParticleTypes.PORTAL, randomizePos(basePosX, 0.4D), randomizePos(basePosY, 0.4D), randomizePos(basePosZ, 0.4D), 0, 0, 0);
                }
            }
        }
    }
    @SubscribeEvent
    public static void ShowHate(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = ClientRefferences.getClientPlayer();
        if (event.player == null)
        {
            return;
        }

        PlayerEntity targetPlayer = event.player;
        float hate = PlayerProperties.getHate(targetPlayer);
        if (player != null)
        {
            double basePosX = targetPlayer.posX;
            double basePosY = targetPlayer.posY + randomizePos(0, targetPlayer.getHeight());
            double basePosZ = targetPlayer.posZ;
            int value = 100 - (int)hate;
            Random random = new Random();
            int rand = random.nextInt(value);
            if (rand == 0)
            {
                player.getEntityWorld().addParticle(ParticleTypes.SMOKE, randomizePos(basePosX, 0.4D), randomizePos(basePosY, 0.4D), randomizePos(basePosZ, 0.4D), 0, 0, 0);
                if (!player.getDisplayName().getFormattedText().equals(targetPlayer.getDisplayName().getFormattedText()))
                {
                    player.getEntityWorld().addParticle(ParticleTypes.LARGE_SMOKE, randomizePos(basePosX, 0.4D), randomizePos(basePosY, 0.4D), randomizePos(basePosZ, 0.4D), 0, 0, 0);
                }
            }
        }
    }
}