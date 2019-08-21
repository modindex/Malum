package malum.items.tools;

import malum.recipes.BlockTransmutationRecipe;
import malum.registry.ModRecipes;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemTransmutationPowder extends Item
{
  public ItemTransmutationPowder(Item.Properties builder)
  {
    super(builder);
  }


  @Override
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
  }
}