package malum.items.curios;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.LivingEntity;
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
            private int GlideCharge = 20;
            private int Cooldown = 0;
            @Override
            public void onCurioTick(String identifier, int index, LivingEntity entityLivingBase)
            {
                GameSettings settings = Minecraft.getInstance().gameSettings;
                KeyBinding jump = settings.keyBindJump;
                if (Cooldown > 0)
                {
                    if (!jump.isKeyDown())
                    {
                        Cooldown = 0;
                    }
                }
                if (entityLivingBase.onGround)
                {
                    Cooldown = 1;
                    GlideCharge = 25;
                }
                else
                {
                    if (GlideCharge != 0)
                    {
                        if (jump.isKeyDown())
                        {
                            if (Cooldown == 0)
                            {
                                if (entityLivingBase.getMotion().y < 0.2)
                                {
                                    entityLivingBase.addVelocity(0, 0.1, 0);
                                }
                                entityLivingBase.addVelocity(0, 0.05, 0);
                                GlideCharge -= 1;
                            }
                        }
                    }
                }
                if (GlideCharge == 0)
                {
                    if (jump.isKeyDown())
                    {
                        if (Cooldown == 0)
                        {
                            if (entityLivingBase.getMotion().y < -0.1)
                            {
                                entityLivingBase.addVelocity(0, 0.1, 0);
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