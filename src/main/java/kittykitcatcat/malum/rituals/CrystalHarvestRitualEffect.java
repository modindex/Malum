package kittykitcatcat.malum.rituals;

import kittykitcatcat.malum.blocks.ResourceCrystalBlock;
import kittykitcatcat.malum.registry.ModItems;
import kittykitcatcat.malum.tileentities.ResourceCrystalTileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class CrystalHarvestRitualEffect implements RitualEffect
{
    @Override
    public void doRitualEffect(int strenght, BlockPos position, TileEntity tileEntity)
    {
        World world = tileEntity.getWorld();
        assert world != null;
        for (BlockPos pos : BlockPos.getAllInBoxMutable(position.subtract(new Vec3i(strenght, strenght, strenght)), position.add(new Vec3i(strenght, strenght, strenght))))
        {
            if (world.getBlockState(pos).getBlock() instanceof ResourceCrystalBlock)
            {
                if (world.getTileEntity(pos) instanceof ResourceCrystalTileEntity)
                {
                    ResourceCrystalTileEntity tileEntity1 = (ResourceCrystalTileEntity) world.getTileEntity(pos);
                    tileEntity1.progress = 1500;
                    tileEntity1.markDirty();
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                    if (tileEntity1.inventory.getStackInSlot(0).getItem().equals(ModItems.soul_crystal))
                    {
                        world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), ModItems.soul_crystal_cluster.getDefaultInstance()));
                    }
                }
            }
        }
    }
}