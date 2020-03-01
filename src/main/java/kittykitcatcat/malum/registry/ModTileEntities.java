package kittykitcatcat.malum.registry;

import kittykitcatcat.malum.tileentities.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities
{
    @ObjectHolder("malum:ritual_block_tile_entity")
    public static TileEntityType<RitualBlockTileEntity> ritual_block_tile_entity;

    @ObjectHolder("malum:spirit_altar_tile_entity")
    public static TileEntityType<SpiritAltarTileEntity> spirit_altar_tile_entity;

    @ObjectHolder("malum:arcane_bore_tile_entity")
    public static TileEntityType<ArcaneBoreTileEntity> arcane_bore_tile_entity;

    @ObjectHolder("malum:spirit_augmenter_tile_entity")
    public static TileEntityType<SpiritAugmenterTileEntity> spirit_augmenter_tile_entity;

    @ObjectHolder("malum:resource_refinery_tile_entity")
    public static TileEntityType<ResourceRefineryTileEntity> resource_refinery_tile_entity;

    @ObjectHolder("malum:resource_crystal_tile_entity")
    public static TileEntityType<ResourceCrystalTileEntity> resource_crystal_tile_entity;

    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> e)
    {
        e.getRegistry().registerAll(
            TileEntityType.Builder.create((Supplier<TileEntity>) RitualBlockTileEntity::new, ModBlocks.ritual_block).build(null).setRegistryName("ritual_block_tile_entity"),
            TileEntityType.Builder.create((Supplier<TileEntity>) ResourceRefineryTileEntity::new, ModBlocks.resource_refinery).build(null).setRegistryName("resource_refinery_tile_entity"),
            TileEntityType.Builder.create((Supplier<TileEntity>) ResourceCrystalTileEntity::new, ModBlocks.resource_crystal).build(null).setRegistryName("resource_crystal_tile_entity"),
            TileEntityType.Builder.create((Supplier<TileEntity>) SpiritAltarTileEntity::new, ModBlocks.spirit_altar).build(null).setRegistryName("spirit_altar_tile_entity"),
            TileEntityType.Builder.create((Supplier<TileEntity>) SpiritAugmenterTileEntity::new, ModBlocks.spirit_augmenter).build(null).setRegistryName("spirit_augmenter_tile_entity"),
            TileEntityType.Builder.create((Supplier<TileEntity>) ArcaneBoreTileEntity::new, ModBlocks.arcane_bore).build(null).setRegistryName("arcane_bore_tile_entity")
        );
    }
}
