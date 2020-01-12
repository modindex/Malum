package malum.registry;

import malum.tileentities.CraftingBlockTileEntity;
import malum.tileentities.ResourceRefineryTileEntity;
import malum.tileentities.RitualBlockTileEntity;
import malum.tileentities.SpiritWellTileEntity;
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

    @ObjectHolder("malum:spirit_well_tile_entity")
    public static TileEntityType<?> spirit_well_tile_entity;
    @ObjectHolder("malum:crafting_block_tile_entity")
    public static TileEntityType<?> crafting_block_tile_entity;

    @ObjectHolder("malum:resource_refinery_tile_entity")
    public static TileEntityType<?> resource_refinery_tile_entity;
    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> e)
    {
        e.getRegistry().registerAll(
            TileEntityType.Builder.create((Supplier<TileEntity>) RitualBlockTileEntity::new, ModBlocks.ritual_block).build(null).setRegistryName("ritual_block_tile_entity"),
            TileEntityType.Builder.create((Supplier<TileEntity>) ResourceRefineryTileEntity::new, ModBlocks.resource_refinery).build(null).setRegistryName("resource_refinery_tile_entity"),
            TileEntityType.Builder.create((Supplier<TileEntity>) CraftingBlockTileEntity::new, ModBlocks.crafting_block).build(null).setRegistryName("crafting_block_tile_entity"),
            TileEntityType.Builder.create((Supplier<TileEntity>) SpiritWellTileEntity::new, ModBlocks.spirit_well).build(null).setRegistryName("spirit_well_tile_entity")
        );
    }
}
