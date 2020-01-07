package malum.registry;

import com.google.common.base.Preconditions;
import malum.items.armor.ItemArmorCatastrophe;
import malum.items.armor.ItemArmorNuminousCatastrophe;
import malum.items.curios.*;
import malum.items.gadgets.*;
import malum.items.special.ItemEvilSpirit;
import malum.items.special.ItemModifier;
import malum.items.special.ItemRitualActivator;
import malum.items.special.ItemWeaponAttunementCore;
import malum.items.tools.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;

import static malum.MalumMod.MODID;
import static malum.registry.ModItemTiers.CATASTROPHE;
import static malum.registry.ModItemTiers.NUMINOUS_CATASTROPHE;

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

    public static Item numinous_catastrophe_hoe;
    public static Item numinous_catastrophe_sword;
    public static Item numinous_catastrophe_axe;
    public static Item numinous_catastrophe_pickaxe;
    public static Item numinous_catastrophe_shovel;
    //crafting
    public static Item end_forged_ingot;
    public static Item end_forged_nugget;
    public static Item metal_necklace;
    public static Item metal_belt;
    public static Item cursed_flare;
    public static Item evil_spirit;
    public static Item weapon_attunement_core;
    public static Item weapon_attunement_core_strong;

    //curios
    public static Item leather_ring;
    public static Item luck_ring;
    public static Item ender_sight_necklace;
    public static Item dark_arts_ring;
    public static Item water_necklace;
    public static Item air_necklace;
    public static Item thorns_belt;
    public static Item healing_belt;
    public static Item nether_necklace;
    public static Item wither_necklace;
    public static Item arcane_sight_ring;
    public static Item rotten_belt;
    public static Item shulker_necklace;
    //gadgets
    public static Item voodoo_doll;
    public static Item rending_doll;
    public static Item control_doll;
    public static Item zombie_doll;
    public static Item enderman_doll;

    public static Item ender_artifact;
    public static Item transmutation_gem;
    public static Item evil_lantern;


    //modifiers
    public static Item channeled_anomaly_modifier;
    public static Item tainted_heart_modifier;
    public static Item ancient_hieroglyph_modifier;
    //blocks

    public static Item evil_grass;
    public static Item evil_dirt;
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

            catastrophe_hoe = setup(new ItemCatastropheHoe(CATASTROPHE, 3f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_hoe"),
            catastrophe_axe = setup(new ItemCatastropheAxe(CATASTROPHE, 6, -3f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_axe"),
            catastrophe_sword = setup(new ItemCatastropheSword(CATASTROPHE, 3, -2.4f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_sword"),
            catastrophe_shovel = setup(new ItemCatastropheShovel(CATASTROPHE, 1, -3.0f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_shovel"),
            catastrophe_pickaxe = setup(new ItemCatastrophePickaxe(CATASTROPHE, 1, -2.8f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_pickaxe"),

            numinous_catastrophe_hoe = setup(new ItemCatastropheHoe(NUMINOUS_CATASTROPHE, 3f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "numinous_catastrophe_hoe"),
            numinous_catastrophe_axe = setup(new ItemCatastropheAxe(NUMINOUS_CATASTROPHE, 6, -3f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "numinous_catastrophe_axe"),
            numinous_catastrophe_sword = setup(new ItemCatastropheSword(NUMINOUS_CATASTROPHE, 3, -2.4f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "numinous_catastrophe_sword"),
            numinous_catastrophe_shovel = setup(new ItemCatastropheShovel(NUMINOUS_CATASTROPHE, 1, -3.0f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "numinous_catastrophe_shovel"),
            numinous_catastrophe_pickaxe = setup(new ItemCatastrophePickaxe(NUMINOUS_CATASTROPHE, 1, -2.8f, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "numinous_catastrophe_pickaxe"),

            ritual_activator = setup(new ItemRitualActivator(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "ritual_activator"),
            transmutation_gem = setup(new ItemTransmutationGem(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "transmutation_gem"),
            evil_lantern = setup(new ItemEvilLantern(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "evil_lantern"),

            voodoo_doll = setup(new ItemVoodoDoll(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "voodoo_doll"),
            rending_doll = setup(new ItemRendingDoll(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxDamage(200)), "rending_doll"),
            enderman_doll = setup(new ItemEndermanDoll(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxDamage(8)), "enderman_doll"),
            control_doll = setup(new ItemControlDoll(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxDamage(500)), "control_doll"),

            ender_artifact = setup(new ItemEnderArtifact(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP).maxDamage(250)), "ender_artifact"),

            leather_ring = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "leather_ring"),
            dark_arts_ring = setup(new ItemDarkArtsRing(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "dark_arts_ring"),
            ender_sight_necklace = setup(new ItemEnderSightNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "ender_sight_necklace"),
            luck_ring = setup(new ItemLuckRing(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "luck_ring"),
            arcane_sight_ring = setup(new ItemArcaneSightRing(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "arcane_sight_ring"),
            water_necklace = setup(new ItemWaterNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "water_necklace"),
            thorns_belt = setup(new ItemThornsBelt(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "thorns_belt"),
            healing_belt = setup(new ItemHealingBelt(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "healing_belt"),
            air_necklace = setup(new ItemAirNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "air_necklace"),
            wither_necklace = setup(new ItemWitherNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wither_necklace"),
            nether_necklace = setup(new ItemNetherNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "nether_necklace"),
            shulker_necklace = setup(new ItemShulkerNecklace(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "shulker_necklace"),
            rotten_belt = setup(new ItemRottenBelt(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "rotten_belt"),



            channeled_anomaly_modifier = setup(new ItemModifier(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "channeled_anomaly_modifier"),
            tainted_heart_modifier = setup(new ItemModifier(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "tainted_heart_modifier"),
            ancient_hieroglyph_modifier = setup(new ItemModifier(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "ancient_hieroglyph_modifier"),

            end_forged_ingot = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "end_forged_ingot"),
            end_forged_nugget = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "end_forged_nugget"),
            metal_necklace = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "metal_necklace"),
            metal_belt = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "metal_belt"),
            cursed_flare = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "cursed_flare"),
            evil_spirit = setup(new ItemEvilSpirit(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "evil_spirit"),
            weapon_attunement_core = setup(new ItemWeaponAttunementCore(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "weapon_attunement_core"),
            weapon_attunement_core_strong = setup(new ItemWeaponAttunementCore(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "weapon_attunement_core_strong"),

            ritual_block = setup(new BlockItem(ModBlocks.ritual_block, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "ritual_block"),
            crafting_block = setup(new BlockItem(ModBlocks.crafting_block, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "crafting_block"),
            evil_dirt = setup(new BlockItem(ModBlocks.evil_dirt, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "evil_dirt"),
            evil_grass = setup(new BlockItem(ModBlocks.evil_grass, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "evil_grass"),

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