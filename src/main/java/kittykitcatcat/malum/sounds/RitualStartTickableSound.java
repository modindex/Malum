package kittykitcatcat.malum.sounds;

import kittykitcatcat.malum.registry.ModSounds;
import net.minecraft.client.audio.TickableSound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;

public class RitualStartTickableSound extends TickableSound
{
    private final TileEntity tileEntity;

    public RitualStartTickableSound(TileEntity tileEntity)
    {
        super(ModSounds.ritual_start, SoundCategory.BLOCKS);
        this.tileEntity = tileEntity;
        this.repeat = false;
        this.volume = 1.0F;
        this.x = (float) tileEntity.getPos().getX();
        this.y = (float) tileEntity.getPos().getY();
        this.z = (float) tileEntity.getPos().getZ();
    }

    public void tick()
    {
        if (this.tileEntity.isRemoved())
        {
            this.donePlaying = true;
        }
    }
}