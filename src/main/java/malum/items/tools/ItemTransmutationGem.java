package malum.items.tools;

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

public class ItemTransmutationGem extends Item
{
  public ItemTransmutationGem(Item.Properties builder)
  {
    super(builder);
  }

    public static double randomize(double value, double power)
    {
        Random random = new Random();
        double randDouble = random.nextDouble() * power;
        value += randDouble * (random.nextDouble() > 0.5 ? -1 : 1);
        return value;
    }
    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        ItemStack itemstack = Objects.requireNonNull(context.getPlayer()).getHeldItem(context.getHand());
        BlockState state = context.getWorld().getBlockState(context.getPos());
        BlockTransmutationRecipe recipe = ModRecipes.getBlockTransmutationRecipe(state);
        if (recipe != null)
        {
            Block replacementBlock = recipe.getReplacementBlock();
            if (state.has(BlockStateProperties.AXIS) && replacementBlock.getDefaultState().has(BlockStateProperties.AXIS))
            {
                BlockState newState = replacementBlock.getDefaultState().with(BlockStateProperties.AXIS, state.get(BlockStateProperties.AXIS));
                context.getWorld().setBlockState(context.getPos(), newState);
            }
            else
            {
                context.getWorld().setBlockState(context.getPos(), replacementBlock.getDefaultState());
            }
            BlockPos pos = context.getPos();
            for (int a = 0; a <= 10; a++)
            {
                double posX = randomize(pos.getX() + 0.5, 0.6);
                double posY = randomize(pos.getY() + 0.5, 0.6);
                double posZ = randomize(pos.getZ() + 0.5, 0.6);
                double velX = randomize(0, 0.1);
                double velY = randomize(0, 0.1);
                double velZ = randomize(0, 0.1);
                context.getPlayer().world.addParticle(ParticleTypes.LARGE_SMOKE, posX, posY, posZ, velX, velY, velZ);
            }
            context.getPlayer().world.playSound(context.getPlayer(), pos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0F, (float)randomize(2.5F, 0.8F));
        }
        return super.onItemUse(context);
    }

 /*   @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

    ItemStack itemstack = playerIn.getHeldItem(handIn);
    RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
    if (raytraceresult.getType() == RayTraceResult.Type.BLOCK)
    {
      BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
      BlockPos blockpos = blockraytraceresult.getPos();
      BlockState block = worldIn.getBlockState(blockpos);
      BlockTransmutationRecipe recipe = ModRecipes.getBlockTransmutationRecipe(block);
      if (recipe != null)
      {
          worldIn.setBlockState(blockpos, recipe.getReplacementBlock().getDefaultState());
          return new ActionResult<>(ActionResultType.SUCCESS, itemstack);

      }
    }
    return new ActionResult<>(ActionResultType.FAIL, itemstack);
  }*/
}