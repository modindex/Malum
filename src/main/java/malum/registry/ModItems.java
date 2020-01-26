package malum.registry;

import com.google.common.base.Preconditions;
import malum.items.armor.ItemArmorCatastrophe;
import malum.items.armor.ItemArmorNuminousCatastrophe;
import malum.items.curios.*;
import malum.items.gadgets.ItemEnderArtifact;
import malum.items.gadgets.ItemEvilLantern;
import malum.items.gadgets.ItemSpiritContainer;
import malum.items.tools.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;

import static malum.MalumMod.MODID;
import static malum.registry.ModItemTiers.*;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
    //armor
    public static Item catastrophe_hood;
    public static Item catastrophe_chestplate;
    public static Item catastrophe_leggings;
    public static Item catastrophe_shoes;

    public static Item numinous_catastrophe_hood;
    public static Item numinous_catastrophe_chestplate;
    public static Item numinous_catastrophe_leggings;
    public static Item numinous_catastrophe_shoes;
    //weapons
    public static Item withering_rapier;
    public static Item catastrophe_hoe;
    public static Item catastrophe_sword;
    public static Item catastrophe_axe;
    public static Item catastrophe_pickaxe;
    public static Item catastrophe_shovel;

    public static Item soul_crystal_hoe;
    public static Item soul_crystal_sword;
    public static Item soul_crystal_axe;
    public static Item soul_crystal_pickaxe;
    public static Item soul_crystal_shovel;

    public static Item numinous_catastrophe_hoe;
    public static Item numinous_catastrophe_sword;
    public static Item numinous_catastrophe_axe;
    public static Item numinous_catastrophe_pickaxe;
    public static Item numinous_catastrophe_shovel;
    //crafting
    public static Item end_forged_ingot;
    public static Item end_forged_nugget;


    public static Item leather_ring;
    public static Item metal_ring;

    public static Item leather_necklace;
    public static Item metal_necklace;

    public static Item leather_belt;
    public static Item metal_belt;


    public static Item bone_stick;

    public static Item soul_fire;

    public static Item soul_dust;
    public static Item soul_crystal;
    public static Item soul_crystal_cluster;

    //curios
    public static Item water_necklace;
    public static Item air_necklace;
    public static Item thorns_belt;
    public static Item healing_belt;
    public static Item nether_necklace;
    public static Item wither_necklace;
    public static Item arcane_sight_ring;
    public static Item spirit_ring;
    public static Item arcane_spirit_ring;

    public static Item rotten_belt;
    public static Item shulker_necklace;
    //gadgets

    public static Item spirit_bottle;

    public static Item ender_artifact;
    public static Item transmutation_gem;
    public static Item evil_lantern;

    //blocks
    public static Item evil_pumpkin;
    public static Item lit_evil_pumpkin;

    public static Item dark_roofing;
    public static Item dark_roofing_stairs;
    public static Item dark_roofing_slab;

    public static Item refined_bricks;
    public static Item refined_bricks_stairs;
    public static Item refined_bricks_slab;

    public static Item refined_pathway;
    public static Item refined_pathway_stairs;
    public static Item refined_pathway_slab;

    public static Item wooden_planks;
    public static Item wooden_planks_stairs;
    public static Item wooden_planks_slab;

    public static Item wooden_beam;
    public static Item spirit_well;

    public static Item refined_smooth_stone;
    public static Item refined_smooth_stone_stairs;
    public static Item refined_smooth_stone_slab;

    public static Item wooden_casing;

    public static Item catastrophe_bricks;
    public static Item catastrophe_stairs;
    public static Item catastrophe_slab;

    public static Item refined_glowstone_block;
    public static Item refined_glowstone_lamp;

    public static Item catastrophe_block;

    public static Item smooth_stone_stairs;

    public static Item ritual_block;
    public static Item crafting_block;
    public static Item ritual_activator;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        final IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(
            catastrophe_shoes = setup(new ItemArmorCatastrophe(ArmorMaterial.DIAMOND, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_shoes"),
            catastrophe_leggings = setup(new ItemArmorCatastrophe(ArmorMaterial.DIAMOND, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_leggings"),
            catastrophe_chestplate = setup(new ItemArmorCatastrophe(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_chestplate"),
            catastrophe_hood = setup(new ItemArmorCatastrophe(ArmorMaterial.DIAMOND, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_hood"),

            numinous_catastrophe_shoes = setup(new ItemArmorNuminousCatastrophe(ArmorMaterial.DIAMOND, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "numinous_catastrophe_shoes"),
            numinous_catastrophe_leggings = setup(new ItemArmorNuminousCatastrophe(ArmorMaterial.DIAMOND, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "numinous_catastrophe_leggings"),
            numinous_catastrophe_chestplate = setup(new ItemArmorNuminousCatastrophe(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "numinous_catastrophe_chestplate"),
            numinous_catastrophe_hood = setup(new ItemArmorNuminousCatastrophe(ArmorMaterial.DIAMOND, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "numinous_catastrophe_hood"),

            withering_rapier = setup(new ItemWitheringRapier(ItemTier.IRON, 2, -1.8f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "withering_rapier"),

            catastrophe_hoe = setup(new ModHoeItem(CATASTROPHE, 0f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_hoe"),
            catastrophe_axe = setup(new ModAxeItem(CATASTROPHE, 0, -3f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_axe"),
            catastrophe_sword = setup(new ModSwordItem(CATASTROPHE, 0, -2.4f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_sword"),
            catastrophe_shovel = setup(new ModShovelItem(CATASTROPHE, 0, -3.0f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_shovel"),
            catastrophe_pickaxe = setup(new ModPickaxeItem(CATASTROPHE, 0, -2.8f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_pickaxe"),

            soul_crystal_hoe = setup(new ModHoeItem(SOUL_CRYSTAL, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_hoe"),
            soul_crystal_axe = setup(new ModAxeItem(SOUL_CRYSTAL, 0, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_axe"),
            soul_crystal_sword = setup(new ModSwordItem(SOUL_CRYSTAL, 0, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_sword"),
            soul_crystal_shovel = setup(new ModShovelItem(SOUL_CRYSTAL, 0, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_shovel"),
            soul_crystal_pickaxe = setup(new ModPickaxeItem(SOUL_CRYSTAL, 0, 0, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_pickaxe"),
            numinous_catastrophe_hoe = setup(new ModHoeItem(NUMINOUS_CATASTROPHE, 3f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "numinous_catastrophe_hoe"),
            numinous_catastrophe_axe = setup(new ModAxeItem(NUMINOUS_CATASTROPHE, 6, -3f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "numinous_catastrophe_axe"),
            numinous_catastrophe_sword = setup(new ModSwordItem(NUMINOUS_CATASTROPHE, 3, -2.4f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "numinous_catastrophe_sword"),
            numinous_catastrophe_shovel = setup(new ModShovelItem(NUMINOUS_CATASTROPHE, 1, -3.0f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "numinous_catastrophe_shovel"),
            numinous_catastrophe_pickaxe = setup(new ModPickaxeItem(NUMINOUS_CATASTROPHE, 1, -2.8f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "numinous_catastrophe_pickaxe"),

            ritual_activator = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "ritual_activator"),
            transmutation_gem = setup(new TransmutationGemItem(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "transmutation_gem"),
            evil_lantern = setup(new ItemEvilLantern(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "evil_lantern"),

            ender_artifact = setup(new ItemEnderArtifact(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxDamage(250)), "ender_artifact"),

            leather_necklace = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "leather_necklace"),
            leather_belt = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "leather_belt"),
            leather_ring = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "leather_ring"),

            metal_necklace = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "metal_necklace"),
            metal_belt = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "metal_belt"),
            metal_ring = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "metal_ring"),

            arcane_sight_ring = setup(new ItemArcaneSightRing(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "arcane_sight_ring"),
            water_necklace = setup(new ItemWaterNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "water_necklace"),
            thorns_belt = setup(new ItemThornsBelt(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "thorns_belt"),
            healing_belt = setup(new ItemHealingBelt(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "healing_belt"),
            air_necklace = setup(new ItemAirNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "air_necklace"),
            wither_necklace = setup(new ItemWitherNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "wither_necklace"),
            nether_necklace = setup(new ItemNetherNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "nether_necklace"),
            shulker_necklace = setup(new ItemShulkerNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "shulker_necklace"),
            rotten_belt = setup(new ItemRottenBelt(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "rotten_belt"),
            arcane_spirit_ring = setup(new ItemArcaneSpiritRing(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "arcane_spirit_ring"),
            spirit_ring = setup(new ItemSpiritRing(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxStackSize(1)), "spirit_ring"),

            end_forged_ingot = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "end_forged_ingot"),
            end_forged_nugget = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "end_forged_nugget"),

            bone_stick = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "bone_stick"),

            soul_fire = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_fire"),
            soul_crystal = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal"),
            soul_crystal_cluster = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_crystal_cluster"),
            soul_dust = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "soul_dust"),

            spirit_bottle = setup(new ItemSpiritContainer(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "spirit_bottle"),

            ritual_block = setup(new BlockItem(ModBlocks.ritual_block, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "ritual_block"),
            crafting_block = setup(new BlockItem(ModBlocks.crafting_block, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "crafting_block"),

            dark_roofing = setup(new BlockItem(ModBlocks.dark_roofing, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "dark_roofing"),
            wooden_beam = setup(new BlockItem(ModBlocks.wooden_beam, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wooden_beam"),
            wooden_casing = setup(new BlockItem(ModBlocks.wooden_casing, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wooden_casing"),
            refined_bricks = setup(new BlockItem(ModBlocks.refined_bricks, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_bricks"),

            refined_pathway = setup(new BlockItem(ModBlocks.refined_pathway, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_pathway"),
            refined_pathway_slab = setup(new BlockItem(ModBlocks.refined_pathway_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_pathway_slab"),
            refined_pathway_stairs = setup(new BlockItem(ModBlocks.refined_pathway_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_pathway_stairs"),

            wooden_planks = setup(new BlockItem(ModBlocks.wooden_planks, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wooden_planks"),
            wooden_planks_slab = setup(new BlockItem(ModBlocks.wooden_planks_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wooden_planks_slab"),
            wooden_planks_stairs = setup(new BlockItem(ModBlocks.wooden_planks_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wooden_planks_stairs"),

            catastrophe_bricks = setup(new BlockItem(ModBlocks.catastrophe_bricks, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_bricks"),
            catastrophe_slab = setup(new BlockItem(ModBlocks.catastrophe_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_slab"),
            catastrophe_stairs = setup(new BlockItem(ModBlocks.catastrophe_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_stairs"),

            refined_smooth_stone = setup(new BlockItem(ModBlocks.refined_smooth_stone, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_smooth_stone"),
            refined_smooth_stone_slab = setup(new BlockItem(ModBlocks.refined_smooth_stone_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_smooth_stone_slab"),
            refined_smooth_stone_stairs = setup(new BlockItem(ModBlocks.refined_smooth_stone_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_smooth_stone_stairs"),

            catastrophe_block = setup(new BlockItem(ModBlocks.catastrophe_block, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_block"),

            smooth_stone_stairs = setup(new BlockItem(ModBlocks.smooth_stone_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "smooth_stone_stairs"),

            evil_pumpkin = setup(new BlockItem(ModBlocks.evil_pumpkin, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "evil_pumpkin"),
            lit_evil_pumpkin = setup(new BlockItem(ModBlocks.lit_evil_pumpkin, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "lit_evil_pumpkin"),

            dark_roofing_slab = setup(new BlockItem(ModBlocks.dark_roofing_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "dark_roofing_slab"),
            refined_bricks_slab = setup(new BlockItem(ModBlocks.refined_bricks_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_bricks_slab"),

            spirit_well = setup(new BlockItem(ModBlocks.spirit_well, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "spirit_well"),
            dark_roofing_stairs = setup(new BlockItem(ModBlocks.dark_roofing_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "dark_roofing_stairs"),
            refined_bricks_stairs = setup(new BlockItem(ModBlocks.refined_bricks_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_bricks_stairs"),


            refined_glowstone_block = setup(new BlockItem(ModBlocks.refined_glowstone_block, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_glowstone_block"),
            refined_glowstone_lamp = setup(new BlockItem(ModBlocks.refined_glowstone_lamp, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_glowstone_lamp")

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