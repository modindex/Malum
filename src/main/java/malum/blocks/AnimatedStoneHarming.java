package malum.blocks;


import malum.ClientRefferences;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AnimatedStoneHarming extends Block
{

    public AnimatedStoneHarming(Properties properties)
    {
        super(properties);
        this.hasTileEntity(this.getDefaultState());
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        if (!entityIn.getEntityWorld().isRemote && entityIn.ticksExisted % 40 == 0)
        {
            entityIn.attackEntityFrom(DamageSource.GENERIC, 2);
        }
        super.onEntityCollision(state, worldIn, pos, entityIn);
    }
}