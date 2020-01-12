package malum.items.tools;

import malum.recipes.BlockTransmutationRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemTransmutationGem extends Item
{
  public ItemTransmutationGem(Item.Properties builder)
  {
    super(builder);
  }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        PlayerEntity playerEntity = context.getPlayer();
        assert playerEntity != null;
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState state = world.getBlockState(pos);
        BlockTransmutationRecipe.transmutateBlock(state, world, pos);
        BlockTransmutationRecipe.makeTransmutationVisuals(state, world, pos);
        return super.onItemUse(context);
    }
}