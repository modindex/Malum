package malum.registry;

import malum.MalumMod;
import malum.blocks.ModStairsBlock;
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
    public static Block refined_bricks = null;
    public static Block refined_pathway = null;
    public static Block wooden_beam = null;
    public static Block wooden_casing = null;
    public static Block wooden_casing_stairs = null;

    public static Block.Properties wooden_beam_properties = Block.Properties.create(Material.WOOD).hardnessAndResistance(1F).harvestTool(ToolType.AXE).sound(SoundType.WOOD);
    public static Block.Properties dark_roofing_properties = Block.Properties.create(Material.ROCK).hardnessAndResistance(2F).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL);
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();

        dark_roofing_stairs = registerBlock(registry, new ModStairsBlock(() -> dark_roofing.getDefaultState(), dark_roofing_properties), "dark_roofing_stairs");
        dark_roofing = registerBlock(registry, new Block(dark_roofing_properties), "dark_roofing");
        refined_bricks = registerBlock(registry, new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 6F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), "refined_bricks");
        refined_pathway = registerBlock(registry, new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 6F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), "refined_pathway");
        wooden_beam = registerBlock(registry, new LogBlock(MaterialColor.BROWN, wooden_beam_properties), "wooden_beam");
        wooden_casing = registerBlock(registry, new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(1F, 6F).harvestTool(ToolType.AXE).sound(SoundType.WOOD)), "wooden_casing");
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