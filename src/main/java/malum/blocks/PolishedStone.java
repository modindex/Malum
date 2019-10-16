package malum.blocks;


import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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