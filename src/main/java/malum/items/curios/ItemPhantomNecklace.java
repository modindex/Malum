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
                float rotationX = 0f;//-5f - (float) Math.sin(livingEntity.world.getGameTime() * 0.25f) * 2f;
                float rotationY = 40f - (float) Math.sin(livingEntity.world.getGameTime() * (PlayerProperties.getCanFly((PlayerEntity) livingEntity) && PlayerProperties.getFlightTime((PlayerEntity) livingEntity) != 25 ? 0.4 : 0.25)) * (PlayerProperties.getCanFly((PlayerEntity) livingEntity)  && PlayerProperties.getFlightTime((PlayerEntity) livingEntity) != 25 ? 4 : 1);
                float rotationZ = 10f - (float) Math.sin(livingEntity.world.getGameTime() * (PlayerProperties.getCanFly((PlayerEntity) livingEntity) && PlayerProperties.getFlightTime((PlayerEntity) livingEntity) != 25 ? 0.4 : 0.25)) * (PlayerProperties.getCanFly((PlayerEntity) livingEntity)  && PlayerProperties.getFlightTime((PlayerEntity) livingEntity) != 25 ? 12 : 4);

                GlStateManager.rotatef(rotationX, 1.0F, 0.0F, 0.0F);

                GlStateManager.rotatef(-rotationZ, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotatef(rotationY, 0.0F, 1.0F, 0.0F);

                ((ModelPhantomWingRight) right_wing_model).render(livingEntity, limbSwing, limbSwingAmount, ageInTicks,
                    netHeadYaw, headPitch, scale);

                GlStateManager.rotatef(-rotationY, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotatef(rotationZ, 0.0F, 0.0F, 1.0F);

                GlStateManager.rotatef(rotationZ, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotatef(-rotationY, 0.0F, 1.0F, 0.0F);

                ((ModelPhantomWingLeft) left_wing_model).render(livingEntity, limbSwing, limbSwingAmount, ageInTicks,
                    netHeadYaw, headPitch, scale);

                GlStateManager.rotatef(rotationY, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotatef(-rotationZ, 0.0F, 0.0F, 1.0F);
            }

            @Override
            public void onCurioTick(String identifier, int index, LivingEntity entityLivingBase)
            {
                if (entityLivingBase instanceof PlayerEntity)
                {
                    GameSettings settings = ClientRefferences.getClientSettings();
                    KeyBinding jump = settings.keyBindJump;
                    double flightTime = PlayerProperties.getFlightTime((PlayerEntity) entityLivingBase);
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
                        PlayerProperties.setCanFly((PlayerEntity) entityLivingBase, false);
                        PlayerProperties.setFlightTime((PlayerEntity) entityLivingBase, 25d);
                    }
                    else
                    {
                        if (jump.isKeyDown())
                        {
                            entityLivingBase.world.addParticle(ParticleTypes.MYCELIUM, MalumMod.randomize(entityLivingBase.posX, 0.5), MalumMod.randomize(entityLivingBase.posY + (entityLivingBase.getHeight() * 0.75f), 0.5), MalumMod.randomize(entityLivingBase.posZ, 0.5), 0,0,0);
                            if (canFly)
                            {
                                if (flightTime > 0d)
                                {
                                    if (entityLivingBase.getMotion().y < 0.2)
                                    {
                                        entityLivingBase.addVelocity(0, 0.1, 0);
                                    }
                                    entityLivingBase.addVelocity(0, 0.05, 0);
                                    PlayerProperties.setFlightTime((PlayerEntity) entityLivingBase, flightTime -1);
                                }
                                else
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
            }

            @Override
            public boolean canRightClickEquip() {

                return true;
            }
        });
    }
}