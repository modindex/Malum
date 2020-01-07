package malum.items.curios;

import malum.ClientRefferences;
import malum.capabilities.PlayerProperties;
import net.minecraft.client.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.capability.ICurio;

public class ItemAirNecklace extends Item implements ICurio
{
    public ItemAirNecklace(Item.Properties builder) {
        super(builder);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
        return CapCurioItem.createProvider(new ICurio() {
            @Override
            public void playEquipSound(LivingEntity entityLivingBase) {
                entityLivingBase.world.playSound(null, entityLivingBase.getPosition(),
                        SoundEvents.ITEM_ARMOR_EQUIP_GOLD, SoundCategory.NEUTRAL,
                        1.0f, 1.0f);
            }

            @Override
            public void onCurioTick(String identifier, int index, LivingEntity entityLivingBase)
            {
                if (entityLivingBase instanceof PlayerEntity)
                {
                    GameSettings settings = ClientRefferences.getClientSettings();
                    KeyBinding jump = settings.keyBindJump;
                    double[] curioArray = PlayerProperties.getCurioArray((PlayerEntity) entityLivingBase);
                    double cooldown = curioArray[0];
                    double glide = curioArray[1];
                    if (cooldown > 0)
                    {
                        if (!jump.isKeyDown())
                        {
                            PlayerProperties.setCurioArrayArgument((PlayerEntity) entityLivingBase, 0, 0);
                        }
                    }
                    if (entityLivingBase.onGround)
                    {
                        PlayerProperties.setCurioArrayArgument((PlayerEntity) entityLivingBase, 0, 1);
                        PlayerProperties.setCurioArrayArgument((PlayerEntity) entityLivingBase, 1, 25);
                    }
                    else
                    {
                        if (glide != 0)
                        {
                            if (jump.isKeyDown())
                            {
                                if (cooldown == 0)
                                {
                                    if (entityLivingBase.getMotion().y < 0.2)
                                    {
                                        entityLivingBase.addVelocity(0, 0.1, 0);
                                    }
                                    entityLivingBase.addVelocity(0, 0.05, 0);
                                    PlayerProperties.setCurioArrayArgument((PlayerEntity) entityLivingBase, 1, glide -= 1);
                                }
                            }
                        }
                    }
                    if (glide == 0)
                    {
                        if (jump.isKeyDown())
                        {
                            if (cooldown == 0)
                            {
                                if (entityLivingBase.getMotion().y < -0.1)
                                {
                                    entityLivingBase.addVelocity(0, 0.1, 0);
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public boolean canRightClickEquip() {

                return true;
            }
        });
    }
}