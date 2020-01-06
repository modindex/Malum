package malum.items.special;

import malum.MalumMod;
import malum.capabilities.PlayerProperties;
import malum.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static malum.capabilities.PlayerProperties.setDangerLevelCapped;

public class ItemRitualActivator extends Item
{
    public ItemRitualActivator(Properties builder)
    {
        super(builder);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack stack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (raytraceresult.getType() == RayTraceResult.Type.MISS)
        {
            return new ActionResult<>(ActionResultType.PASS, stack);
        }
        else
        {
            if (raytraceresult.getType() == RayTraceResult.Type.BLOCK)
            {
                BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
                BlockPos blockpos = blockraytraceresult.getPos();

                BlockState blockstate = worldIn.getBlockState(blockpos);
                if (blockstate == ModBlocks.ritual_block.getDefaultState())
                {
                    return new ActionResult<>(ActionResultType.SUCCESS, stack);
                }
                if (stack.hasTag())
                {
                    if (stack.getTag().hasUniqueId("uuid") || stack.getTag().getIntArray("data") != null)
                    {
                        return new ActionResult<>(ActionResultType.FAIL, stack);
                    }
                }
                stack.setTag(new CompoundNBT());
                CompoundNBT nbt = stack.getTag();
                int[] position = {blockpos.getX(), blockpos.getY(), blockpos.getZ()};

                assert nbt != null;
                nbt.putIntArray("data", position);

                return new ActionResult<>(ActionResultType.SUCCESS, stack);

            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand)
    {
        if (stack.hasTag())
        {
            if (stack.getTag().hasUniqueId("uuid") || stack.getTag().getIntArray("data") != null)
            {
                return false;
            }
        }
        stack.setTag(new CompoundNBT());
        CompoundNBT nbt = stack.getTag();
        assert nbt != null;
        UUID uuid = nbt.getUniqueId("uuid");
        if (uuid != null)
        {
            if (uuid == target.getUniqueID())
            {
                return false;
            }
        }
        nbt.putUniqueId("uuid", target.getUniqueID());
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }
}