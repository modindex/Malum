package malum.registry;

import malum.tileentities.RitualBlockTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities
{
    @ObjectHolder("malum:ritual_block_tile_entity")
    public static TileEntityType<?> ritual_block_tile_entity;

    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> e)
    {
        e.getRegistry().registerAll(
            TileEntityType.Builder.create((Supplier<TileEntity>) RitualBlockTileEntity::new, ModBlocks.ritual_block).build(null).setRegistryName("ritual_block_tile_entity")
        );
    }
}