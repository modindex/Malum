package malum.event;

import malum.ClientRefferences;
import malum.capabilities.PlayerMadeDoll;
import malum.capabilities.PlayerProperties;
import malum.items.curios.*;
import malum.items.gadgets.ItemVoodoDoll;
import malum.network.NetworkManager;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosAPI;

import java.util.Random;

@Mod.EventBusSubscriber()
public class ClientPlayerEventHandler
{
    static float Cooldown = 0;
    static float GlideCharge = 0;
    @SubscribeEvent
    public static void AirNecklaceFlight(TickEvent.PlayerTickEvent event) {
        PlayerEntity entity = event.player;
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemAirNecklace, entity).isPresent()) {

            GameSettings settings = ClientRefferences.getClientSettings();
            KeyBinding jump = settings.keyBindJump;
            if (Cooldown > 0) {
                if (!jump.isKeyDown()) {
                    Cooldown = 0;
                }
            }
            if (entity.onGround) {
                Cooldown = 1;
                GlideCharge = 25;
            } else {
                if (GlideCharge != 0) {
                    if (jump.isKeyDown()) {
                        if (Cooldown == 0) {
                            if (entity.getMotion().y < 0.2) {
                                entity.addVelocity(0, 0.1, 0);
                            }
                            entity.addVelocity(0, 0.05, 0);
                            GlideCharge -= 1;
                        }
                    }
                }
            }
            if (GlideCharge == 0) {
                if (jump.isKeyDown()) {
                    if (Cooldown == 0) {
                        if (entity.getMotion().y < -0.1) {
                            entity.addVelocity(0, 0.1, 0);
                        }
                    }
                }
            }
        }
    }
    public static float getDangerLevel(PlayerEntity target)
    {
        PlayerEntity player = ClientRefferences.getClientPlayer();
        float returnValue;
        //send a packet here from target to clientPlayer
        returnValue = target.getCapability(PlayerProperties.PLAYER_MADE_DOLL).map(PlayerMadeDoll::hasPlayerMadeDoll).orElse(0);
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
    public static double randomizePos(double value)
    {
        Random random = new Random();
        double randDouble = random.nextDouble() * 0.4D;
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
                    player.getEntityWorld().addParticle(ParticleTypes.SMOKE, randomizePos(basePosX), randomizePos(basePosY), randomizePos(basePosZ), 0, 0, 0);
                }
                if (dangerLevel >= 2) {
                    player.getEntityWorld().addParticle(ParticleTypes.LARGE_SMOKE, randomizePos(basePosX), randomizePos(basePosY), randomizePos(basePosZ), 0, 0, 0);
                }
                if (dangerLevel >= 3) {
                    player.getEntityWorld().addParticle(ParticleTypes.FLAME, randomizePos(basePosX), randomizePos(basePosY), randomizePos(basePosZ), 0, 0, 0);
                }
            }
        }
    }
}