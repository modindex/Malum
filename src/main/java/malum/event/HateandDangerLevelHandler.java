package malum.event;

import malum.ClientRefferences;
import malum.MalumMod;
import malum.capabilities.PlayerProperties;
import malum.items.curios.*;
import malum.items.gadgets.ItemVoodoDoll;
import malum.registry.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
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
    public static void ShowDangerLevel(TickEvent.PlayerTickEvent event)
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
                if (dangerLevel >= 1)
                {
                    player.getEntityWorld().addParticle(ParticleTypes.PORTAL, randomizePos(basePosX, 0.4D), randomizePos(basePosY, 0.4D), randomizePos(basePosZ, 0.4D), 0, 0, 0);
                }
                if (dangerLevel >= 2)
                {
                    player.getEntityWorld().addParticle(ParticleTypes.PORTAL, randomizePos(basePosX, 0.4D), randomizePos(basePosY, 0.4D), randomizePos(basePosZ, 0.4D), 0, 0, 0);
                }
                if (dangerLevel >= 3)
                {
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
        float permaHate = PlayerProperties.getPermaHate(targetPlayer);
        if (player != null)
        {
            Random random = new Random();
            double basePosX = targetPlayer.posX;
            double basePosY = targetPlayer.posY + targetPlayer.getHeight() / 2 + randomizePos(0, targetPlayer.getHeight() / 2);
            double basePosZ = targetPlayer.posZ;
            double basePosXClient = player.posX;
            double basePosYClient = player.posY + player.getHeight() / 2 + randomizePos(0, player.getHeight() / 2);
            double basePosZClient = player.posZ;
            if (hate > 100)
            {
                hate = 100;
            }
            if (permaHate > 100)
            {
                permaHate = 100;
            }
            if (hate >= 5)
            {
                int particleChance = 101 - (int) hate;
                int particleRand = random.nextInt(particleChance);
                if (particleRand == 0 || particleRand == 1)
                {
                    player.getEntityWorld().addParticle(ParticleTypes.SMOKE, randomizePos(basePosXClient, 0.4D), randomizePos(basePosYClient, 0.4D), randomizePos(basePosZClient, 0.4D), 0, 0, 0);
                    if (!player.getDisplayName().getFormattedText().equals(targetPlayer.getDisplayName().getFormattedText()))
                    {
                        player.getEntityWorld().addParticle(ParticleTypes.LARGE_SMOKE, randomizePos(basePosX, 0.4D), randomizePos(basePosY, 0.4D), randomizePos(basePosZ, 0.4D), 0, 0, 0);
                    }
                }

                int nEffectChange = (3200 - (int) hate * 16) - (int)permaHate * 8;
                int nEffectRand = random.nextInt(nEffectChange);
                if (nEffectRand == 0 || nEffectRand == 1)
                {
                    int typeOfEffect = random.nextInt(6);
                    switch (typeOfEffect)
                    {
                        case (0):
                        {
                            targetPlayer.addPotionEffect(new EffectInstance(Effects.NAUSEA, 30 + (int)hate, (int) hate / 33));
                            break;
                        }
                        case (1):
                        {
                            targetPlayer.addPotionEffect(new EffectInstance(Effects.HUNGER, 30 + (int)hate, 1 + (int) hate / 33));
                            break;
                        }
                        case (2):
                        {
                            targetPlayer.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 30 + (int)hate / 2, 1));
                            break;
                        }
                        case (3):
                        {
                            targetPlayer.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 30 + (int)hate, (int)hate / 33));
                            break;
                        }
                        case (4):
                        {
                            targetPlayer.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 30 + (int)hate, 1 + (int)hate / 33));
                            break;
                        }
                        case (5):
                        {
                            BlockPos playerPos = targetPlayer.getPosition();
                            BlockPos pos1 = playerPos.add(3, 3, 3);
                            BlockPos pos2 = playerPos.add(-3, -3, -3);
                            for(BlockPos pos : BlockPos.getAllInBoxMutable(pos1, pos2))
                            {
                                if (targetPlayer.world.getBlockState(pos) == Blocks.GRASS_BLOCK.getDefaultState())
                                {
                                    targetPlayer.world.setBlockState(pos, ModBlocks.evil_grass.getDefaultState());
                                }
                                if (targetPlayer.world.getBlockState(pos) == Blocks.DIRT.getDefaultState())
                                {
                                    targetPlayer.world.setBlockState(pos, ModBlocks.evil_dirt.getDefaultState());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}