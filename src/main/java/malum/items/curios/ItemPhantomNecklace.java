package malum.items.curios;

import com.mojang.blaze3d.platform.GlStateManager;
import malum.ClientRefferences;
import malum.MalumMod;
import malum.capabilities.PlayerProperties;
import malum.models.ModelPhantomWingLeft;
import malum.models.ModelPhantomWingRight;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.capability.ICurio;

public class ItemPhantomNecklace extends Item implements ICurio
{
    public ItemPhantomNecklace(Item.Properties builder) {
        super(builder);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
        return CapCurioItem.createProvider(new ICurio() {
            private Object left_wing_model;
            private Object right_wing_model;

            private final ResourceLocation WING_TEXTURE =
                new ResourceLocation(MalumMod.MODID, "textures/armor/phantom_wings.png");
            @Override
            public void playEquipSound(LivingEntity entityLivingBase) {
                entityLivingBase.world.playSound(null, entityLivingBase.getPosition(),
                        SoundEvents.ITEM_ARMOR_EQUIP_GOLD, SoundCategory.NEUTRAL,
                        1.0f, 1.0f);
            }

            @Override
            public boolean hasRender(String identifier, LivingEntity livingEntity)
            {
                return true;
            }

            @Override
            public void doRender(String identifier, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
            {
                Minecraft.getInstance().getTextureManager().bindTexture(WING_TEXTURE);
                ICurio.RenderHelper.rotateIfSneaking(livingEntity);

                if (!(this.right_wing_model instanceof ModelPhantomWingRight))
                {
                    this.right_wing_model = new ModelPhantomWingRight();
                }
                if (!(this.left_wing_model instanceof ModelPhantomWingLeft))
                {
                    this.left_wing_model = new ModelPhantomWingLeft();
                }
                float totalFlightTime = (float)PlayerProperties.getTotalFlightTime((PlayerEntity) livingEntity);
                float rotationX = 60 - (totalFlightTime > 30 ? 60 : totalFlightTime * 2);
                float rotationY = 20f + (totalFlightTime > 25 ? 25 : totalFlightTime);
                float rotationZ = 10f - (totalFlightTime > 20 ? 20 : totalFlightTime)
                                  - (float)Math.sin(livingEntity.world.getGameTime()
                                      * 0.4f)
                                        * (totalFlightTime > 36 ? 36 : totalFlightTime);

                GlStateManager.rotatef(-rotationX, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotatef(-rotationZ, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotatef(rotationY, 0.0F, 1.0F, 0.0F);

                ((ModelPhantomWingRight) right_wing_model).render(livingEntity, limbSwing, limbSwingAmount, ageInTicks,
                    netHeadYaw, headPitch, scale);

                GlStateManager.rotatef(-rotationY, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotatef(rotationZ, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotatef(rotationX, 0.0F, 0.0F, 1.0F);

                GlStateManager.rotatef(rotationX, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotatef(rotationZ, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotatef(-rotationY, 0.0F, 1.0F, 0.0F);

                ((ModelPhantomWingLeft) left_wing_model).render(livingEntity, limbSwing, limbSwingAmount, ageInTicks,
                    netHeadYaw, headPitch, scale);

                GlStateManager.rotatef(rotationY, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotatef(-rotationZ, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotatef(-rotationX, 0.0F, 0.0F, 1.0F);
            }

            @Override
            public void onCurioTick(String identifier, int index, LivingEntity entityLivingBase)
            {
                if (entityLivingBase instanceof PlayerEntity)
                {
                    GameSettings settings = ClientRefferences.getClientSettings();
                    KeyBinding jump = settings.keyBindJump;
                    double flightTime = PlayerProperties.getAvaiableFlightTime((PlayerEntity) entityLivingBase);
                    boolean canFly = PlayerProperties.getCanFly((PlayerEntity) entityLivingBase);

                    if (!entityLivingBase.onGround)
                    {
                        if (!canFly)
                        {
                            if (!jump.isKeyDown())
                            {
                                PlayerProperties.setCanFly((PlayerEntity) entityLivingBase, true);
                            }
                        }
                    }
                    if (entityLivingBase.onGround)
                    {
                        PlayerProperties.setTotalFlightTime((PlayerEntity) entityLivingBase, PlayerProperties.getTotalFlightTime((PlayerEntity) entityLivingBase) > 0 ? PlayerProperties.getTotalFlightTime((PlayerEntity) entityLivingBase) - 2d : 0d);
                        PlayerProperties.setCanFly((PlayerEntity) entityLivingBase, false);
                        PlayerProperties.setAvaiableFlightTime((PlayerEntity) entityLivingBase, 25d);
                    }
                    else
                    {
                        if (canFly)
                        {
                            PlayerProperties.setTotalFlightTime((PlayerEntity) entityLivingBase, PlayerProperties.getTotalFlightTime((PlayerEntity) entityLivingBase) < 50 ? PlayerProperties.getTotalFlightTime((PlayerEntity) entityLivingBase) + 1.5d : 60);
                            if (jump.isKeyDown())
                            {
                                entityLivingBase.world.addParticle(ParticleTypes.MYCELIUM, MalumMod.randomize(entityLivingBase.posX, 0.5), MalumMod.randomize(entityLivingBase.posY + (entityLivingBase.getHeight() * 0.75f), 0.5), MalumMod.randomize(entityLivingBase.posZ, 0.5), 0, 0, 0);
                                if (flightTime > 0d)
                                {
                                    if (entityLivingBase.getMotion().y < 0.2)
                                    {
                                        entityLivingBase.addVelocity(0, 0.1, 0);
                                    }
                                    entityLivingBase.addVelocity(0, 0.05, 0);
                                    PlayerProperties.setAvaiableFlightTime((PlayerEntity) entityLivingBase, flightTime - 1);
                                }
                                else
                                {
                                    if (entityLivingBase.getMotion().y < -0.2f)
                                    {
                                        entityLivingBase.addVelocity(0, 0.1f, 0);
                                    }
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