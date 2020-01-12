package malum.registry;

import malum.MalumMod;
import malum.blocks.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{
    public static Block dark_roofing = null;
    public static Block dark_roofing_stairs = null;
    public static Block dark_roofing_slab = null;

    public static Block refined_bricks = null;
    public static Block refined_bricks_stairs = null;
    public static Block refined_bricks_slab = null;

    public static Block refined_pathway = null;
    public static Block refined_pathway_stairs = null;
    public static Block refined_pathway_slab = null;

    public static Block refined_smooth_stone = null;
    public static Block refined_smooth_stone_stairs = null;
    public static Block refined_smooth_stone_slab = null;

    public static Block wooden_beam = null;

    public static Block smooth_stone_stairs = null;

    public static Block wooden_planks = null;
    public static Block wooden_planks_stairs = null;
    public static Block wooden_planks_slab = null;

    public static Block wooden_casing = null;

    public static Block catastrophe_bricks = null;
    public static Block catastrophe_stairs = null;
    public static Block catastrophe_slab = null;

    public static Block catastrophe_block = null;
    public static Block spirit_well = null;

    public static Block evil_dirt;
    public static Block evil_grass;
    public static Block evil_pumpkin;
    public static Block lit_evil_pumpkin;

    public static Block refined_glowstone_block;
    public static Block refined_glowstone_lamp;

    public static Block ritual_block;
    public static Block crafting_block;
    public static Block evil_anvil;
    public static Block resource_refinery;

    public static Block resource_crystal_coal;
    public static Block resource_crystal_iron;
    public static Block resource_crystal_gold;
    public static Block resource_crystal_diamond;
    public static Block resource_crystal_lapis;
    public static Block resource_crystal_redstone;
    public static Block resource_crystal_emerald;
    public static Block resource_crystal_soul;

    public static Block.Properties wood_properties = Block.Properties.from(Blocks.OAK_WOOD);
    public static Block.Properties metal_properties = Block.Properties.from(Blocks.IRON_BLOCK);

    public static Block.Properties refined_stone_properties = Block.Properties.create(Material.ROCK).hardnessAndResistance(3F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE);

    public static Block.Properties evil_earth_properties = Block.Properties.create(Material.EARTH).hardnessAndResistance(6F).harvestTool(ToolType.SHOVEL).sound(SoundType.GROUND).harvestLevel(2).lightValue(1);

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();
        ritual_block = registerBlock(registry, new RitualBlock(metal_properties), "ritual_block");
        crafting_block = registerBlock(registry, new CraftingBlock(metal_properties), "crafting_block");

        resource_crystal_coal = registerBlock(registry, new ResourceCrystalBlock(Block.Properties.from(Blocks.GLASS)), "resource_crystal_coal");
        resource_crystal_iron = registerBlock(registry, new ResourceCrystalBlock(Block.Properties.from(Blocks.GLASS)), "resource_crystal_iron");
        resource_crystal_gold = registerBlock(registry, new ResourceCrystalBlock(Block.Properties.from(Blocks.GLASS)), "resource_crystal_gold");
        resource_crystal_diamond = registerBlock(registry, new ResourceCrystalBlock(Block.Properties.from(Blocks.GLASS)), "resource_crystal_diamond");
        resource_crystal_lapis = registerBlock(registry, new ResourceCrystalBlock(Block.Properties.from(Blocks.GLASS)), "resource_crystal_lapis");
        resource_crystal_redstone = registerBlock(registry, new ResourceCrystalBlock(Block.Properties.from(Blocks.GLASS)), "resource_crystal_redstone");
        resource_crystal_emerald = registerBlock(registry, new ResourceCrystalBlock(Block.Properties.from(Blocks.GLASS)), "resource_crystal_emerald");
        resource_crystal_soul = registerBlock(registry, new ResourceCrystalBlock(Block.Properties.from(Blocks.GLASS)), "resource_crystal_soul");

        smooth_stone_stairs = registerBlock(registry, new ModStairsBlock(Blocks.SMOOTH_STONE::getDefaultState, Block.Properties.from(Blocks.SMOOTH_STONE)), "smooth_stone_stairs");

        resource_refinery = registerBlock(registry, new ResourceRefineryBlock(metal_properties), "resource_refinery");

        evil_pumpkin = registerBlock(registry, new ModHorizontalBlock(Block.Properties.from(Blocks.CARVED_PUMPKIN)), "evil_pumpkin");
        lit_evil_pumpkin = registerBlock(registry, new ModHorizontalBlock(Block.Properties.from(Blocks.JACK_O_LANTERN)), "lit_evil_pumpkin");
        dark_roofing = registerBlock(registry, new Block(metal_properties), "dark_roofing");
        dark_roofing_slab = registerBlock(registry, new ModSlabBlock(() -> dark_roofing.getDefaultState(), metal_properties), "dark_roofing_slab");
        dark_roofing_stairs = registerBlock(registry, new ModStairsBlock(() -> dark_roofing.getDefaultState(), metal_properties), "dark_roofing_stairs");

        refined_glowstone_block = registerBlock(registry, new Block(Block.Properties.from(Blocks.GLOWSTONE)), "refined_glowstone_block");
        refined_glowstone_lamp = registerBlock(registry, new RedstoneLampBlock(Block.Properties.from(Blocks.REDSTONE_LAMP)), "refined_glowstone_lamp");



        catastrophe_block = registerBlock(registry, new Block(Block.Properties.from(Blocks.IRON_BLOCK)), "catastrophe_block");

        catastrophe_bricks = registerBlock(registry, new Block(metal_properties), "catastrophe_bricks");
        catastrophe_slab = registerBlock(registry, new ModSlabBlock(() -> dark_roofing.getDefaultState(), metal_properties), "catastrophe_slab");
        catastrophe_stairs = registerBlock(registry, new ModStairsBlock(() -> dark_roofing.getDefaultState(), metal_properties), "catastrophe_stairs");

        refined_smooth_stone = registerBlock(registry, new Block(refined_stone_properties), "refined_smooth_stone");
        refined_smooth_stone_slab = registerBlock(registry, new ModSlabBlock(() -> dark_roofing.getDefaultState(), refined_stone_properties), "refined_smooth_stone_slab");
        refined_smooth_stone_stairs = registerBlock(registry, new ModStairsBlock(() -> dark_roofing.getDefaultState(), refined_stone_properties), "refined_smooth_stone_stairs");

        refined_pathway = registerBlock(registry, new Block(refined_stone_properties), "refined_pathway");
        refined_pathway_slab = registerBlock(registry, new ModSlabBlock(() -> refined_pathway.getDefaultState(), refined_stone_properties), "refined_pathway_slab");
        refined_pathway_stairs = registerBlock(registry, new ModStairsBlock(() -> refined_pathway.getDefaultState(), refined_stone_properties), "refined_pathway_stairs");

        wooden_planks = registerBlock(registry, new Block(wood_properties), "wooden_planks");
        wooden_planks_slab = registerBlock(registry, new ModSlabBlock(() -> wooden_planks.getDefaultState(), wood_properties), "wooden_planks_slab");
        wooden_planks_stairs = registerBlock(registry, new ModStairsBlock(() -> wooden_planks.getDefaultState(), wood_properties), "wooden_planks_stairs");

        refined_bricks = registerBlock(registry, new Block(refined_stone_properties), "refined_bricks");
        refined_bricks_slab = registerBlock(registry, new ModSlabBlock(() -> refined_bricks.getDefaultState(), refined_stone_properties), "refined_bricks_slab");
        refined_bricks_stairs = registerBlock(registry, new ModStairsBlock(() -> refined_bricks.getDefaultState(), refined_stone_properties), "refined_bricks_stairs");

        wooden_beam = registerBlock(registry, new LogBlock(MaterialColor.BROWN, wood_properties), "wooden_beam");
        wooden_casing = registerBlock(registry, new LogBlock(MaterialColor.BROWN, wood_properties), "wooden_casing");

        spirit_well = registerBlock(registry, new SpiritWellBlock(refined_stone_properties), "spirit_well");
        evil_dirt = registerBlock(registry, new Block(evil_earth_properties), "evil_dirt");
        evil_grass = registerBlock(registry, new Block(evil_earth_properties), "evil_grass");

        evil_anvil = registerBlock(registry, new EvilAnvil(evil_earth_properties), "evil_anvil");

    }

    private static <T extends Block> T registerBlock(IForgeRegistry<Block> registry, T newBlock, String name)
    {
        String prefixedName = MalumMod.MODID + ":" + name;
        newBlock.setRegistryName(prefixedName);
        registry.register(newBlock);
        return newBlock;
    }

}