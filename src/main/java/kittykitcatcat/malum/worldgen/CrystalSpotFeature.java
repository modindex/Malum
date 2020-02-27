package kittykitcatcat.malum.worldgen;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class CrystalSpotFeature extends Feature<CrystalSpotConfig>
{
    public CrystalSpotFeature(Function<Dynamic<?>, ? extends CrystalSpotConfig> p_i49869_1_)
    {
        super(p_i49869_1_);
    }

    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, CrystalSpotConfig config)
    {
        return true;
    }
}