package malum.items.curios;

import malum.ClientRefferences;
import malum.capabilities.PlayerProperties;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.capability.ICurio;

public class ItemNetherNecklace extends Item implements ICurio
{

    public ItemNetherNecklace(Item.Properties builder) {
        super(builder);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
        return CapCurioItem.createProvider(new ICurio() {
            @Override
            public void playEquipSound(LivingEntity entityLivingBase) {
                entityLivingBase.world.playSound(null, entityLivingBase.getPosition(),
                        SoundEvents.ENTITY_BLAZE_BURN, SoundCategory.NEUTRAL,
                        1.0f, 1.8f);
            }

            @Override
            public void onCurioTick(String identifier, int index, LivingEntity entityLivingBase)
            {
                if (entityLivingBase instanceof PlayerEntity)
                {
                    double[] curioArray = PlayerProperties.getCurioArray((PlayerEntity) entityLivingBase);
                    double jumpStrength = curioArray[2];
                    double canJump = curioArray[3];

                    if (canJump != 0)
                    {
                        PlayerProperties.setCurioArrayArgument((PlayerEntity) entityLivingBase, 2, 0);
                    }
                    if (entityLivingBase.onGround)
                    {
                        if (canJump == 0)
                        {
                            if (ClientRefferences.getClientSettings().keyBindJump.isKeyDown())
                            {
                                if (jumpStrength == 0)
                                {
                                    entityLivingBase.world.playSound(entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ, SoundEvents.ENTITY_CREEPER_PRIMED, SoundCategory.PLAYERS, 2, 2, true);
                                    entityLivingBase.world.playSound(entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.PLAYERS, 2, 2, true);
                                }
                                if (jumpStrength <= 0.5)
                                {
                                    PlayerProperties.setCurioArrayArgument((PlayerEntity) entityLivingBase, 2, jumpStrength + 0.01);
                                }
                            }
                        }
                    }
                    if (entityLivingBase.onGround)
                    {
                        PlayerProperties.setCurioArrayArgument((PlayerEntity) entityLivingBase, 3, 0);
                    }
                    if (entityLivingBase.onGround && jumpStrength != 0 && !ClientRefferences.getClientSettings().keyBindJump.isKeyDown())
                    {
                        ((PlayerEntity) entityLivingBase).jump();
                        PlayerProperties.setCurioArrayArgument((PlayerEntity) entityLivingBase, 3, 1);
                        entityLivingBase.addVelocity(0, 0.5 + jumpStrength, 0);
                        entityLivingBase.world.playSound(entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 2, 2, true);
                        entityLivingBase.world.playSound(entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 2, 2, true);
                        PlayerProperties.setCurioArrayArgument((PlayerEntity) entityLivingBase, 2, 0);
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