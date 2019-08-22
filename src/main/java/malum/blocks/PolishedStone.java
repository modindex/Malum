package malum.blocks;


import malum.tileentities.AscenderTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.world.NoteBlockEvent;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class PolishedStone extends Block {

    public PolishedStone(Properties properties)
    {
        super(properties);
        this.hasTileEntity(this.getDefaultState());
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if (entityIn instanceof PlayerEntity)
        {
            if (!entityIn.getEntityWorld().isRemote && entityIn.ticksExisted % 40 == 0)
            {
                ((PlayerEntity) entityIn).addPotionEffect(new EffectInstance(Effects.SPEED, 40, 2));
            }
        }
        super.onEntityWalk(worldIn, pos, entityIn);
    }
}