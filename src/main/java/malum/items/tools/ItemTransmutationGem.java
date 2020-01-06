package malum.items.tools;

import malum.MalumMod;
import malum.recipes.BlockTransmutationRecipe;
import malum.registry.ModRecipes;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Random;

import static malum.MalumMod.randomize;

public class ItemTransmutationGem extends Item
{
  public ItemTransmutationGem(Item.Properties builder)
  {
    super(builder);
  }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        ItemStack itemstack = Objects.requireNonNull(context.getPlayer()).getHeldItem(context.getHand());
        context.getPlayer().world.playSound(context.getPlayer(), context.getPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0F, (float)randomize(2.5F, 0.8F));
        BlockTransmutationRecipe.transmutateBlock(context.getWorld().getBlockState(context.getPos()), context.getWorld(), context.getPos());
        for (int a = 0; a <= 10; a++)
        {
            BlockPos pos = context.getPos();
            double posX = randomize(pos.getX() + 0.5, 0.6);
            double posY = randomize(pos.getY() + 0.5, 0.6);
            double posZ = randomize(pos.getZ() + 0.5, 0.6);
            double velX = randomize(0, 0.1);
            double velY = randomize(0, 0.1);
            double velZ = randomize(0, 0.1);
            context.getWorld().addParticle(ParticleTypes.LARGE_SMOKE, posX, posY, posZ, velX, velY, velZ);
        }
        return super.onItemUse(context);
    }
}