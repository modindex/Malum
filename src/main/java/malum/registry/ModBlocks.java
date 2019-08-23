package malum.registry;

import malum.MalumMod;
import malum.blocks.ModSlabBlock;
import malum.blocks.ModStairsBlock;
import malum.blocks.PolishedStone;
import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
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
    public static Block wooden_beam = null;
    public static Block wooden_casing = null;
    public static Block wooden_casing_stairs = null;
    public static Block wooden_casing_slab = null;

    public static Block.Properties wooden_beam_properties = Block.Properties.create(Material.WOOD).hardnessAndResistance(1F).harvestTool(ToolType.AXE).sound(SoundType.WOOD);
    public static Block.Properties dark_roofing_properties = Block.Properties.create(Material.ROCK).hardnessAndResistance(2F).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL);
    public static Block.Properties refined_stone_properties = Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE);

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();

        dark_roofing = registerBlock(registry, new Block(dark_roofing_properties), "dark_roofing");
        dark_roofing_slab = registerBlock(registry, new ModSlabBlock(() -> dark_roofing.getDefaultState(), dark_roofing_properties), "dark_roofing_slab");
        dark_roofing_stairs = registerBlock(registry, new ModStairsBlock(() -> dark_roofing.getDefaultState(), dark_roofing_properties), "dark_roofing_stairs");

        refined_pathway = registerBlock(registry, new PolishedStone(refined_stone_properties), "refined_pathway");
        refined_pathway_slab = registerBlock(registry, new ModSlabBlock(() -> refined_pathway.getDefaultState(), refined_stone_properties), "refined_pathway_slab");
        refined_pathway_stairs = registerBlock(registry, new ModStairsBlock(() -> refined_pathway.getDefaultState(), refined_stone_properties), "refined_pathway_stairs");

        refined_bricks = registerBlock(registry, new PolishedStone(refined_stone_properties), "refined_bricks");
        refined_bricks_slab = registerBlock(registry, new ModSlabBlock(() -> refined_bricks.getDefaultState(), refined_stone_properties), "refined_bricks_slab");
        refined_bricks_stairs = registerBlock(registry, new ModStairsBlock(() -> refined_bricks.getDefaultState(), refined_stone_properties), "refined_bricks_stairs");

        wooden_beam = registerBlock(registry, new LogBlock(MaterialColor.BROWN, wooden_beam_properties), "wooden_beam");
        wooden_casing = registerBlock(registry, new Block(wooden_beam_properties), "wooden_casing");
        wooden_casing_slab = registerBlock(registry, new ModSlabBlock(() -> wooden_casing.getDefaultState(), wooden_beam_properties), "wooden_casing_slab");
        wooden_casing_stairs = registerBlock(registry, new ModStairsBlock(() -> wooden_casing.getDefaultState(), wooden_beam_properties), "wooden_casing_stairs");

    }

    private static <T extends Block> T registerBlock(IForgeRegistry<Block> registry, T newBlock, String name)
    {
        String prefixedName = MalumMod.MODID + ":" + name;
        newBlock.setRegistryName(prefixedName);
        registry.register(newBlock);
        return newBlock;
    }

}