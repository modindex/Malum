package kittykitcatcat.malum.registry;

import com.google.common.base.Preconditions;
import kittykitcatcat.malum.items.armor.ItemArmorSoulCrystal;
import kittykitcatcat.malum.items.armor.ItemArmorSoulSteel;
import kittykitcatcat.malum.items.curios.*;
import kittykitcatcat.malum.items.gadgets.*;
import kittykitcatcat.malum.items.tools.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;

import static kittykitcatcat.malum.MalumMod.MODID;
import static kittykitcatcat.malum.registry.ModItemTiers.CATASTROPHE;
import static kittykitcatcat.malum.registry.ModItemTiers.SOUL_CRYSTAL;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
    public static Item drained_shard;
    public static Item spirit_bottle;
    public static Item leather_necklace;
    public static Item leather_belt;
    public static Item leather_ring;
    public static Item phantom_feather;
    public static Item soul_dust;
    public static Item soul_crystal;
    public static Item soul_crystal_cluster;
    public static Item drained_steel_ingot;
    public static Item drained_steel_nugget;
    public static Item soul_steel_ingot;
    public static Item soul_steel_nugget;
    public static Item soul_steel_necklace;
    public static Item soul_steel_belt;
    public static Item soul_steel_ring;
    public static Item sacrificial_dagger;
    public static Item ritual_activator;
    public static Item transmutation_gem;
    public static Item soul_crystal_hoe;
    public static Item soul_crystal_axe;
    public static Item soul_crystal_sword;
    public static Item soul_crystal_shovel;
    public static Item soul_crystal_pickaxe;
    public static Item soul_crystal_shoes;
    public static Item soul_crystal_leggings;
    public static Item soul_crystal_chestplate;
    public static Item soul_crystal_helm;
    public static Item soul_steel_hoe;
    public static Item soul_steel_axe;
    public static Item soul_steel_sword;
    public static Item soul_steel_shovel;
    public static Item soul_steel_pickaxe;
    public static Item soul_steel_shoes;
    public static Item soul_steel_leggings;
    public static Item soul_steel_chestplate;
    public static Item soul_steel_helm;
    public static Item soul_eater;
    public static Item ender_staff;
    public static Item ender_artifact;
    public static Item thorns_belt;
    public static Item healing_belt;
    public static Item rotten_belt;
    public static Item water_necklace;
    public static Item phantom_necklace;
    public static Item nether_necklace;
    public static Item wither_necklace;
    public static Item shulker_necklace;
    public static Item spirit_ring;
    public static Item berry_ring;
    public static Item phantom_ring;
    public static Item arcane_bore;
    public static Item spirit_altar;
    public static Item resource_refinery;
    public static Item ritual_block;
    public static Item spirit_augmenter;
    public static Item drained_steel_block;
    public static Item soul_steel_block;
    public static Item wooden_planks;
    public static Item wooden_planks_slab;
    public static Item wooden_planks_stairs;
    public static Item wooden_beam;
    public static Item wooden_casing;
    public static Item refined_bricks;
    public static Item refined_bricks_slab;
    public static Item refined_bricks_stairs;
    public static Item refined_pathway;
    public static Item refined_pathway_slab;
    public static Item refined_pathway_stairs;
    public static Item refined_smooth_stone;
    public static Item refined_smooth_stone_slab;
    public static Item refined_smooth_stone_stairs;
    public static Item dark_roofing;
    public static Item dark_roofing_slab;
    public static Item dark_roofing_stairs;
    public static Item evil_pumpkin;
    public static Item lit_evil_pumpkin;
    public static Item refined_glowstone_block;
    public static Item refined_glowstone_lamp;
    public static Item healing_flower;
    public static Item smooth_stone_stairs;

    public static Item spirit;
    public static Item spirit_augment;
    public static Item drill_render;
    public static Item crystal_render;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        final IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(

            //MATERIALS
            drained_shard = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "drained_shard"),

            spirit_bottle = setup(new ItemSpiritContainer(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "spirit_bottle"),

            leather_necklace = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "leather_necklace"),
            leather_belt = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "leather_belt"),
            leather_ring = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "leather_ring"),

            phantom_feather = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "phantom_feather"),

            soul_dust = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_dust"),
            soul_crystal = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal"),
            soul_crystal_cluster = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_cluster"),

            drained_steel_ingot = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "drained_steel_ingot"),
            drained_steel_nugget = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "drained_steel_nugget"),

            soul_steel_ingot = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_steel_ingot"),
            soul_steel_nugget = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_steel_nugget"),

            soul_steel_necklace = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "soul_steel_necklace"),
            soul_steel_belt = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "soul_steel_belt"),
            soul_steel_ring = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "soul_steel_ring"),

            //PROGRESSION TIED TOOLS
            sacrificial_dagger = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "sacrificial_dagger"),
            ritual_activator = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "ritual_activator"),
            transmutation_gem = setup(new TransmutationGemItem(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "transmutation_gem"),

            //TOOLS AND ARMOR
            soul_crystal_hoe = setup(new ModHoeItem(SOUL_CRYSTAL, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_hoe"),
            soul_crystal_axe = setup(new ModAxeItem(SOUL_CRYSTAL, 0, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_axe"),
            soul_crystal_sword = setup(new ModSwordItem(SOUL_CRYSTAL, 0, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_sword"),
            soul_crystal_shovel = setup(new ModShovelItem(SOUL_CRYSTAL, 0, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_shovel"),
            soul_crystal_pickaxe = setup(new ModPickaxeItem(SOUL_CRYSTAL, 0, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_pickaxe"),

            soul_crystal_shoes = setup(new ItemArmorSoulCrystal(ArmorMaterial.DIAMOND, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_shoes"),
            soul_crystal_leggings = setup(new ItemArmorSoulCrystal(ArmorMaterial.DIAMOND, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_leggings"),
            soul_crystal_chestplate = setup(new ItemArmorSoulCrystal(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_chestplate"),
            soul_crystal_helm = setup(new ItemArmorSoulCrystal(ArmorMaterial.DIAMOND, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_helm"),

            soul_steel_hoe = setup(new ModHoeItem(CATASTROPHE, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_steel_hoe"),
            soul_steel_axe = setup(new ModAxeItem(CATASTROPHE, 0, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_steel_axe"),
            soul_steel_sword = setup(new ModSwordItem(CATASTROPHE, 0, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_steel_sword"),
            soul_steel_shovel = setup(new ModShovelItem(CATASTROPHE, 0, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_steel_shovel"),
            soul_steel_pickaxe = setup(new ModPickaxeItem(CATASTROPHE, 0, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_steel_pickaxe"),

            soul_steel_shoes = setup(new ItemArmorSoulSteel(ArmorMaterial.DIAMOND, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_steel_shoes"),
            soul_steel_leggings = setup(new ItemArmorSoulSteel(ArmorMaterial.DIAMOND, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_steel_leggings"),
            soul_steel_chestplate = setup(new ItemArmorSoulSteel(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_steel_chestplate"),
            soul_steel_helm = setup(new ItemArmorSoulSteel(ArmorMaterial.DIAMOND, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_steel_helm"),

            //USEFUL TOOLS
            soul_eater = setup(new SoulEaterSwordItem(CATASTROPHE, 2, -0.1f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_eater"),

            ender_staff = setup(new ItemEnderStaff(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "ender_staff"),

            ender_artifact = setup(new ItemEnderArtifact(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "ender_artifact"),

            //BELTS

            thorns_belt = setup(new ItemThornsBelt(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "thorns_belt"),
            healing_belt = setup(new ItemHealingBelt(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "healing_belt"),
            rotten_belt = setup(new ItemRottenBelt(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "rotten_belt"),

            //NECKLACES

            water_necklace = setup(new ItemWaterNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "water_necklace"),
            phantom_necklace = setup(new ItemPhantomNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "phantom_necklace"),
            nether_necklace = setup(new ItemNetherNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "nether_necklace"),
            wither_necklace = setup(new ItemWitherNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "wither_necklace"),
            shulker_necklace = setup(new ItemShulkerNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "shulker_necklace"),

            //RINGS

            spirit_ring = setup(new ItemSpiritRing(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "spirit_ring"),
            berry_ring = setup(new ItemSpiritRing(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "berry_ring"),
            phantom_ring = setup(new ItemPhantomRing(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "phantom_ring"),
            //PROGRESSION BLOCKS

            arcane_bore = setup(new BlockItem(ModBlocks.arcane_bore, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "arcane_bore"),
            spirit_altar = setup(new BlockItem(ModBlocks.spirit_altar, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "spirit_altar"),
            resource_refinery = setup(new BlockItem(ModBlocks.resource_refinery, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "resource_refinery"),
            ritual_block = setup(new BlockItem(ModBlocks.ritual_block, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "ritual_block"),
            spirit_augmenter = setup(new BlockItem(ModBlocks.spirit_augmenter, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "spirit_augmenter"),

            //BUILDING BLOCKS

            drained_steel_block = setup(new BlockItem(ModBlocks.drained_steel_block, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "drained_steel_block"),
            soul_steel_block = setup(new BlockItem(ModBlocks.soul_steel_block, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_steel_block"),

            wooden_planks = setup(new BlockItem(ModBlocks.wooden_planks, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wooden_planks"),
            wooden_planks_slab = setup(new BlockItem(ModBlocks.wooden_planks_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wooden_planks_slab"),
            wooden_planks_stairs = setup(new BlockItem(ModBlocks.wooden_planks_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wooden_planks_stairs"),

            wooden_beam = setup(new BlockItem(ModBlocks.wooden_beam, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wooden_beam"),
            wooden_casing = setup(new BlockItem(ModBlocks.wooden_casing, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wooden_casing"),

            refined_bricks = setup(new BlockItem(ModBlocks.refined_bricks, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_bricks"),
            refined_bricks_slab = setup(new BlockItem(ModBlocks.refined_bricks_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_bricks_slab"),
            refined_bricks_stairs = setup(new BlockItem(ModBlocks.refined_bricks_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_bricks_stairs"),

            refined_pathway = setup(new BlockItem(ModBlocks.refined_pathway, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_pathway"),
            refined_pathway_slab = setup(new BlockItem(ModBlocks.refined_pathway_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_pathway_slab"),
            refined_pathway_stairs = setup(new BlockItem(ModBlocks.refined_pathway_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_pathway_stairs"),

            refined_smooth_stone = setup(new BlockItem(ModBlocks.refined_smooth_stone, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_smooth_stone"),
            refined_smooth_stone_slab = setup(new BlockItem(ModBlocks.refined_smooth_stone_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_smooth_stone_slab"),
            refined_smooth_stone_stairs = setup(new BlockItem(ModBlocks.refined_smooth_stone_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_smooth_stone_stairs"),

            dark_roofing = setup(new BlockItem(ModBlocks.dark_roofing, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "dark_roofing"),
            dark_roofing_slab = setup(new BlockItem(ModBlocks.dark_roofing_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "dark_roofing_slab"),
            dark_roofing_stairs = setup(new BlockItem(ModBlocks.dark_roofing_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "dark_roofing_stairs"),

            evil_pumpkin = setup(new BlockItem(ModBlocks.evil_pumpkin, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "evil_pumpkin"),
            lit_evil_pumpkin = setup(new BlockItem(ModBlocks.lit_evil_pumpkin, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "lit_evil_pumpkin"),

            refined_glowstone_block = setup(new BlockItem(ModBlocks.refined_glowstone_block, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_glowstone_block"),
            refined_glowstone_lamp = setup(new BlockItem(ModBlocks.refined_glowstone_lamp, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_glowstone_lamp"),

            //NOT MOD TAB ITEMS
            healing_flower = setup(new BlockItem(ModBlocks.healing_flower, new Item.Properties().group(ItemGroup.DECORATIONS)), "healing_flower"),
            smooth_stone_stairs = setup(new BlockItem(ModBlocks.smooth_stone_stairs, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), "smooth_stone_stairs"),
            spirit = setup(new ItemSpirit(new Item.Properties()), "spirit"),
            spirit_augment = setup(new ItemSpirit(new Item.Properties()), "spirit_augment"),
            drill_render = setup(new Item(new Item.Properties()), "drill_render"),
            crystal_render = setup(new ItemCrystalRender(new Item.Properties()), "crystal_render")
        );
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name)
    {
        Preconditions.checkNotNull(name, "Name to assign to entry cannot be null!");
        return setup(entry, new ResourceLocation(MODID, name));
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName)
    {
        Preconditions.checkNotNull(entry, "Entry cannot be null!");
        Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
        entry.setRegistryName(registryName);
        return entry;
    }

}