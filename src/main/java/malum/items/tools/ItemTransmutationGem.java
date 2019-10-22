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
        BlockState state = context.getWorld().getBlockState(context.getPos());
        BlockTransmutationRecipe recipe = ModRecipes.getBlockTransmutationRecipe(state);
        if (recipe != null)
        {
            Block replacementBlock = recipe.getReplacementBlock();
            if (state.has(BlockStateProperties.AXIS))
            {
                BlockState newState = replacementBlock.getDefaultState().with(BlockStateProperties.AXIS, state.get(BlockStateProperties.AXIS));
                context.getWorld().setBlockState(context.getPos(), newState);
            }
            else
            {
                context.getWorld().setBlockState(context.getPos(), replacementBlock.getDefaultState());
            }
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