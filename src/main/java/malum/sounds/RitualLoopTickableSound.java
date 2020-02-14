package malum.sounds;
import malum.registry.ModSounds;
import malum.tileentities.RitualBlockTileEntity;
import net.minecraft.client.audio.TickableSound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;

public class RitualLoopTickableSound extends TickableSound
{
    private final TileEntity tileEntity;

    public RitualLoopTickableSound(TileEntity tileEntity) {
        super(ModSounds.ritual_loop, SoundCategory.BLOCKS);
        this.tileEntity = tileEntity;
        this.repeat = true;

        this.repeatDelay = 0;
        this.volume = 1.0F;
        this.x = (float)tileEntity.getPos().getX();
        this.y = (float)tileEntity.getPos().getY();
        this.z = (float)tileEntity.getPos().getZ();
    }
    public void tick() {
        if (this.tileEntity.isRemoved()) {
            this.donePlaying = true;
        }
        if (tileEntity instanceof RitualBlockTileEntity)
        {
            if (((RitualBlockTileEntity) tileEntity).crafting == 0)
            {
                this.donePlaying = true;
            }
        }
    }
}