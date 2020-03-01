package kittykitcatcat.malum.items.curios;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import kittykitcatcat.malum.ClientRefferences;
import kittykitcatcat.malum.MalumMod;
import kittykitcatcat.malum.capabilities.PlayerProperties;
import kittykitcatcat.malum.models.ModelPhantomWingLeft;
import kittykitcatcat.malum.models.ModelPhantomWingRight;
import kittykitcatcat.malum.spirit_augmentation.SpiritAugmentationData;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Vector3f;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.capability.ICurio;

import java.util.Random;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class ItemPhantomNecklace extends Item implements ICurio
{
    public ItemPhantomNecklace(Item.Properties builder)
    {
        super(builder);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused)
    {
        return CapCurioItem.createProvider(new ICurio()
        {
            private final ResourceLocation WING_TEXTURE =
                new ResourceLocation(MalumMod.MODID, "textures/armor/phantom_wings.png");
            private Object left_wing_model;
            private Object right_wing_model;

            @Override
            public void playEquipSound(LivingEntity entityLivingBase)
            {
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
            public void render(String identifier, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
            {
                if (!(this.right_wing_model instanceof ModelPhantomWingRight))
                {
                    this.right_wing_model = new ModelPhantomWingRight();
                }
                if (!(this.left_wing_model instanceof ModelPhantomWingLeft))
                {
                    this.left_wing_model = new ModelPhantomWingLeft();
                }
                ModelPhantomWingLeft wing_model = (ModelPhantomWingLeft) this.left_wing_model;
                IVertexBuilder vertexBuilder = ItemRenderer.getBuffer(renderTypeBuffer, wing_model.getRenderType(WING_TEXTURE), false, false);


                Minecraft.getInstance().getTextureManager().bindTexture(WING_TEXTURE);

                ICurio.RenderHelper.translateIfSneaking(matrixStack, livingEntity);
                ICurio.RenderHelper.rotateIfSneaking(matrixStack, livingEntity);

                float totalFlightTime = (float) PlayerProperties.getTotalFlightTime((PlayerEntity) livingEntity);
                float rotationX = 60 - (totalFlightTime > 30 ? 60 : totalFlightTime * 2);
                float rotationY = 20f + (totalFlightTime > 25 ? 25 : totalFlightTime);
                float rotationXAgain = 10f - (totalFlightTime > 20 ? 20 : totalFlightTime) - (float) Math.sin(livingEntity.world.getGameTime() * 0.4f) * (totalFlightTime > 36 ? 36 : totalFlightTime);

                matrixStack.rotate(Vector3f.ZP.rotationDegrees(-rotationX));
                matrixStack.rotate(Vector3f.ZP.rotationDegrees(-rotationXAgain));
                matrixStack.rotate(Vector3f.YP.rotationDegrees(rotationY));

                ((ModelPhantomWingRight) right_wing_model).render(matrixStack, vertexBuilder, NO_OVERLAY, light, 1.0F,
                    1.0F, 1.0F, 1.0F);

                matrixStack.rotate(Vector3f.YP.rotationDegrees(-rotationY));
                matrixStack.rotate(Vector3f.ZP.rotationDegrees(rotationXAgain));
                matrixStack.rotate(Vector3f.ZP.rotationDegrees(rotationX));

                matrixStack.rotate(Vector3f.ZP.rotationDegrees(rotationX));
                matrixStack.rotate(Vector3f.ZP.rotationDegrees(rotationXAgain));
                matrixStack.rotate(Vector3f.YP.rotationDegrees(-rotationY));
                ((ModelPhantomWingLeft) left_wing_model).render(matrixStack, vertexBuilder, NO_OVERLAY, light, 1.0F,
                    1.0F, 1.0F, 1.0F);

                matrixStack.rotate(Vector3f.YP.rotationDegrees(rotationY));
                matrixStack.rotate(Vector3f.ZP.rotationDegrees(-rotationXAgain));
                matrixStack.rotate(Vector3f.ZP.rotationDegrees(-rotationX));
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
                        PlayerProperties.setAvaiableFlightTime((PlayerEntity) entityLivingBase, (double) (20 + SpiritAugmentationData.getAugmentAmountFromStack(stack, MalumMod.phantom_necklace_flight_time_augment)));
                    }
                    else
                    {
                        if (canFly)
                        {
                            PlayerProperties.setTotalFlightTime((PlayerEntity) entityLivingBase, PlayerProperties.getTotalFlightTime((PlayerEntity) entityLivingBase) < 50 ? PlayerProperties.getTotalFlightTime((PlayerEntity) entityLivingBase) + 1.5d : 60);
                            if (jump.isKeyDown())
                            {
                                float yaw = entityLivingBase.rotationYaw;
                                float pitch = entityLivingBase.rotationPitch;
                                float f = -MathHelper.sin(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
                                float f1 = -MathHelper.sin(pitch * ((float) Math.PI / 180F));
                                float f2 = MathHelper.cos(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
                                Vec3d direction = new Vec3d(f, f1, f2);
                                Vec3d particlePosition = entityLivingBase.getPositionVec().add(MathHelper.nextDouble(new Random(), -0.5, 0.5), MathHelper.nextDouble(new Random(), -0.15, 0.15), MathHelper.nextDouble(new Random(), -0.5, 0.5)).subtract(direction);
                                entityLivingBase.world.addParticle(ParticleTypes.MYCELIUM, particlePosition.x, particlePosition.y, particlePosition.z, 0, 0, 0);
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
            public boolean canRightClickEquip()
            {

                return true;
            }
        });
    }
}