package malum.items.gadgets;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemEvilLantern extends Item
{
    public ItemEvilLantern(Item.Properties builder)
    {
        super(builder);
    }
    Block blockType()
    {
        return Blocks.DIRT;
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (raytraceresult.getType() == RayTraceResult.Type.MISS)
        {
            return new ActionResult<>(ActionResultType.PASS, itemstack);
        }
        else
        {
            if (raytraceresult.getType() == RayTraceResult.Type.BLOCK)
            {
                BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
                BlockPos blockpos = blockraytraceresult.getPos();
                Direction direction = blockraytraceresult.getFace();
                if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(direction), direction, itemstack))
                {
                    return new ActionResult<>(ActionResultType.FAIL, itemstack);
                }

                BlockState blockstate = worldIn.getBlockState(blockpos);
                BlockPos blockpos1 = blockpos.offset(direction);
                if ((blockstate.getBlock() != blockType() && worldIn.isAirBlock(blockpos1)))
                {
                    if (playerIn instanceof ServerPlayerEntity)
                    {
                        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) playerIn, blockpos1, itemstack);
                    }
                    if (!playerIn.abilities.isCreativeMode)
                    {
                        itemstack.damageItem(1, playerIn, Entity::extinguish);
                    }
                    worldIn.playSound(playerIn, blockpos, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
                }
            }
            return new ActionResult<>(ActionResultType.FAIL, itemstack);
        }
    }
}