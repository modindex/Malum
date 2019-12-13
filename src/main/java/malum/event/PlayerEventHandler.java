package malum.event;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import malum.ClientRefferences;
import malum.MalumMod;
import malum.capabilities.PlayerProperties;
import malum.items.curios.*;
import malum.registry.ModItems;
import net.minecraft.client.GameSettings;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;
import top.theillusivec4.curios.api.CuriosAPI;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector3d;
import java.util.*;

import static com.mojang.blaze3d.platform.GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA;
import static com.mojang.blaze3d.platform.GlStateManager.SourceFactor.SRC_ALPHA;
import static net.minecraft.entity.LivingEntity.ENTITY_GRAVITY;
import static net.minecraft.entity.LivingEntity.SWIM_SPEED;

@Mod.EventBusSubscriber
public class PlayerEventHandler
{

    @SubscribeEvent
    public static void AirNecklaceFlight(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity entity = event.player;
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemAirNecklace, entity).isPresent())
        {
            GameSettings settings = ClientRefferences.getClientSettings();
            KeyBinding jump = settings.keyBindJump;
            double[] curioArray = PlayerProperties.getCurioArray(entity);
            double cooldown = curioArray[0];
            double glide = curioArray[1];
            if (cooldown > 0)
            {
                if (!jump.isKeyDown())
                {
                    cooldown = 0;
                }
            }
            if (entity.onGround)
            {
                cooldown = 1;
                glide = 25;
            }
            else
            {
                if (glide != 0)
                {
                    if (jump.isKeyDown())
                    {
                        if (cooldown == 0)
                        {
                            if (entity.getMotion().y < 0.2)
                            {
                                entity.addVelocity(0, 0.1, 0);
                            }
                            entity.addVelocity(0, 0.05, 0);
                            glide -= 1;
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
                        if (entity.getMotion().y < -0.1)
                        {
                            entity.addVelocity(0, 0.1, 0);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void Jump(LivingEvent.LivingJumpEvent event)
    {
        LivingEntity entityLivingBase = event.getEntityLiving();
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemNetherNecklace, entityLivingBase).isPresent())
        {
            entityLivingBase.setVelocity(entityLivingBase.getMotion().x, 0, entityLivingBase.getMotion().z);
        }
    }

    @SubscribeEvent
    public static void Heal(LivingHealEvent event)
    {
        Random random = new Random();
        LivingEntity entity = event.getEntityLiving();
        if (entity instanceof PlayerEntity)
        {
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemShulkerNecklace, entity).isPresent())
            {
                double rand = random.nextDouble();
                double minimum = 0.2;
                minimum += event.getAmount();
                if (rand <= minimum)
                {
                    double x = entity.getPosition().getX();
                    double y = entity.getPosition().getY();
                    double z = entity.getPosition().getZ();
                    AxisAlignedBB bounds = new AxisAlignedBB(x - 10, y - 10, z - 10, x + 10, y + 10, z + 10);
                    List<Entity> targets = entity.world.getEntitiesWithinAABBExcludingEntity(entity, bounds);
                    List<LivingEntity> livingTargets = Lists.newArrayList();
                    if (!targets.isEmpty())
                    {
                        for (Entity e : targets)
                        {
                            if (e instanceof LivingEntity)
                            {
                                livingTargets.add((LivingEntity) e);
                            }
                        }
                    }
                    if (!targets.isEmpty())
                    {
                        LivingEntity target = entity.world.getClosestEntity(livingTargets, EntityPredicate.DEFAULT, entity, x, y, z);
                        if (target != null)
                        {
                            ShulkerBulletEntity shulkerBulletEntity = new ShulkerBulletEntity(entity.world, entity, target, Direction.Axis.Y);
                            double xVel = random.nextDouble() * 0.5 * (random.nextDouble() > 0.5 ? -1 : 1);
                            double zVel = random.nextDouble() * 0.5 * (random.nextDouble() > 0.5 ? -1 : 1);
                            shulkerBulletEntity.posY += 1;
                            shulkerBulletEntity.addVelocity(xVel, 0.1, zVel);
                            entity.world.addEntity(shulkerBulletEntity);
                            entity.setAIMoveSpeed(entity.getAIMoveSpeed() * 2);
                            shulkerBulletEntity.getTags().add("noLevitate");
                        }
                    }
                }
            }
        }
    }

    private static final UUID ID = UUID.fromString("6d3be89e-37e6-453f-8654-2fd37d85b2ab");
    @SubscribeEvent
    public static void Tick(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity playerEntity = ClientRefferences.getClientPlayer();
        PlayerEntity entityLivingBase = event.player;
        if (playerEntity != null)
        {
            double[] curioArray = PlayerProperties.getCurioArray(playerEntity);
            double jumpStrength = curioArray[2];
            double canJump = curioArray[3];
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemNetherNecklace, playerEntity).isPresent())
            {
                if (canJump != 0)
                {
                    PlayerProperties.setCurioArrayArgument(playerEntity, 2, 0);
                }
                if (playerEntity.onGround)
                {
                    if (canJump == 0)
                    {
                        if (ClientRefferences.getClientSettings().keyBindJump.isKeyDown())
                        {
                            if (jumpStrength == 0)
                            {
                                playerEntity.world.playSound(playerEntity.posX, playerEntity.posY, playerEntity.posZ, SoundEvents.ENTITY_CREEPER_PRIMED, SoundCategory.PLAYERS, 2, 2, true);
                                playerEntity.world.playSound(playerEntity.posX, playerEntity.posY, playerEntity.posZ, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.PLAYERS, 2, 2, true);
                            }
                            if (jumpStrength <= 0.5)
                            {
                                PlayerProperties.setCurioArrayArgument(playerEntity, 2, jumpStrength + 0.01);
                            }
                        }
                    }
                }
                if (playerEntity.onGround)
                {
                    PlayerProperties.setCurioArrayArgument(playerEntity, 3, 0);
                }
                if (playerEntity.onGround && jumpStrength != 0 && !ClientRefferences.getClientSettings().keyBindJump.isKeyDown())
                {
                    playerEntity.jump();
                    PlayerProperties.setCurioArrayArgument(playerEntity, 3, 1);
                    playerEntity.addVelocity(0, 0.5 + jumpStrength, 0);
                    playerEntity.world.playSound(playerEntity.posX, playerEntity.posY, playerEntity.posZ, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 2, 2, true);
                    playerEntity.world.playSound(playerEntity.posX, playerEntity.posY, playerEntity.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 2, 2, true);
                    PlayerProperties.setCurioArrayArgument(playerEntity, 2, 0);
                }
            }
        }
        if (entityLivingBase.isInWater())
        {
            IAttributeInstance attributeInstance = entityLivingBase.getAttributes().getAttributeInstance(SWIM_SPEED);
            assert attributeInstance != null;
            double Y = entityLivingBase.getPosition().getY();
            double ValueofY = 110 - Y;
            double swimSpeed = 0.1D;
            if (ValueofY > 0)
            {
                swimSpeed += (float) (ValueofY / 80);
            }
            final AttributeModifier SWIM_SPEED_INCREASE = new AttributeModifier(ID, "Swim Speed modifier", swimSpeed, AttributeModifier.Operation.ADDITION);

            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemWaterNecklace, entityLivingBase).isPresent())
            {
                if (!entityLivingBase.getEntityWorld().isRemote && entityLivingBase.ticksExisted % 19 == 0)
                {
                    if (attributeInstance.getModifier(ID) != null)
                    {
                        attributeInstance.removeModifier(SWIM_SPEED_INCREASE);
                    }
                }
                if (attributeInstance.getModifier(ID) == null)
                {
                    attributeInstance.applyModifier(SWIM_SPEED_INCREASE);
                }
            }
            else
            {
                if (attributeInstance.getModifier(ID) != null)
                {
                    attributeInstance.removeModifier(SWIM_SPEED_INCREASE);
                }
            }
        }
    }
    @SubscribeEvent
    public static void AddPotion(PotionEvent.PotionAddedEvent event)
    {
        if (event.getEntityLiving().getLastDamageSource() != null)
        {
            if (event.getEntityLiving().getLastDamageSource().getImmediateSource() != null)
            {
                Entity applyingProjectile = event.getEntityLiving().getLastDamageSource().getImmediateSource();
                if (applyingProjectile instanceof ShulkerBulletEntity)
                {
                    if (applyingProjectile.getTags().contains("noLevitate"))
                    {
                        MalumMod.LOGGER.info("noLev");
                        event.getEntityLiving().getTags().add("removeLevitate");
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void RemoveLevitate(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntityLiving().getTags().contains("removeLevitate"))
        {
            event.getEntityLiving().getTags().remove("removeLevitate");
            event.getEntityLiving().removePotionEffect(Effects.LEVITATION);
        }
    }
    @SubscribeEvent
    public static void DamageEvent(LivingDamageEvent event)
    {
        LivingEntity attacked = event.getEntityLiving(); // you were attacked by a mob
        Entity attacking = event.getSource().getTrueSource(); //a mob was attacked by you
        Entity attackingP = event.getSource().getImmediateSource(); //you were attacked by a mobs projectile

        if (event.getSource() == DamageSource.FALL)
        {
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemNetherNecklace, attacked).isPresent())
            {
                event.setCanceled(true);
            }
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemAirNecklace, attacked).isPresent())
            {
                event.setCanceled(true);
            }
        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemThornsBelt, attacked).isPresent())
        {
            float damage = 1f;
            float health = attacked.getHealth();
            float value = attacked.getMaxHealth() - health;
            value *= 0.1;
            if (health <= attacked.getMaxHealth() / 4)
            {
                damage *= 2f;
            }
            damage += value;
            Objects.requireNonNull(event.getSource().getTrueSource()).attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) attacked), damage);

        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemWitherNecklace, attacked).isPresent())
        {
            attacked.playSound(SoundEvents.ENTITY_WITHER_HURT, 1, 1);
            attacked.playSound(SoundEvents.ENTITY_WITHER_SKELETON_HURT, 1, 1);
            event.setAmount(event.getAmount() * 0.85f);
        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemNetherNecklace, attacked).isPresent())
        {
            attacked.setSprinting(true);
        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemHealingBelt, attacked).isPresent())
        {
            float maxhealth = attacked.getMaxHealth();
            float health = attacked.getHealth();
            float modifier = 0.5f;
            float value = (maxhealth - health) * modifier;
            attacked.setAbsorptionAmount(attacked.getAbsorptionAmount() + value);
        }
    }

    @SubscribeEvent
    public static void Death(LivingDeathEvent event)
    {
        if (event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof PlayerEntity)
        {
            PlayerEntity entityLivingBase = (PlayerEntity) event.getSource().getTrueSource();
            if (entityLivingBase.swingingHand != null)
            {
                Hand hand = entityLivingBase.swingingHand;

                ItemStack stack = entityLivingBase.getHeldItem(hand);
                if (!stack.isEmpty())
                {
                    if (stack.getItem() == Items.WOODEN_SWORD)
                    {
                        Entity target = event.getEntityLiving();
                        if (target instanceof WitherSkeletonEntity)
                        {
                            entityLivingBase.setHeldItem(hand, ModItems.withering_rapier.getDefaultInstance());
                        }
                    }
                }
            }
        }
    }
}