package malum.registry;

import com.google.common.base.Preconditions;
import malum.MalumMod;
import malum.items.armor.ItemArmorCatastrophe;
import malum.items.curios.*;
import malum.items.gadgets.*;
import malum.items.special.ItemEvilSpirit;
import malum.items.special.ItemWeaponAttunementCore;
import malum.items.tools.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;

import java.util.function.Supplier;

import static malum.MalumMod.MODID;
import static malum.registry.ModItemTiers.CATASTROPHE;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
    //armor
    public static Item catastrophe_hood = null;
    public static Item catastrophe_chestplate = null;
    public static Item catastrophe_leggings = null;
    public static Item catastrophe_shoes = null;

    //weapons
    public static Item withering_rapier = null;
    public static Item catastrophe_hoe = null;
    public static Item catastrophe_sword = null;
    public static Item catastrophe_axe = null;
    public static Item catastrophe_pickaxe = null;
    public static Item catastrophe_shovel = null;
    //crafting
    public static Item end_forged_ingot = null;
    public static Item end_forged_nugget = null;
    public static Item metal_necklace = null;
    public static Item metal_belt = null;
    public static Item cursed_flare = null;
    public static Item evil_spirit = null;
    public static Item weapon_attunement_core = null;
    public static Item weapon_attunement_core_strong = null;

    //curios
    public static Item leather_ring = null;
    public static Item luck_ring = null;
    public static Item ender_sight_necklace = null;
    public static Item dark_arts_ring = null;
    public static Item water_necklace = null;
    public static Item air_necklace = null;
    public static Item thorns_belt = null;
    public static Item healing_belt = null;
    public static Item nether_necklace = null;
    public static Item wither_necklace = null;
    public static Item arcane_sight_ring = null;
    //gadgets
    public static Item evil_lantern = null;
    public static Item voodoo_doll = null;
    public static Item rending_doll = null;
    public static Item control_doll = null;
    public static Item enderman_doll = null;
    public static Item ender_artifact = null;
    public static Item transmutation_gem = null;

    //blocks
    public static Item dark_roofing = null;
    public static Item dark_roofing_stairs = null;
    public static Item dark_roofing_slab = null;
    public static Item refined_bricks = null;
    public static Item refined_bricks_stairs = null;
    public static Item refined_bricks_slab = null;
    public static Item refined_pathway = null;
    public static Item refined_pathway_stairs = null;
    public static Item refined_pathway_slab = null;
    public static Item wooden_beam = null;
    public static Item wooden_casing = null;
    public static Item wooden_casing_stairs = null;
    public static Item wooden_casing_slab = null;
    public static Item catastrophe_bricks = null;
    public static Item catastrophe_stairs = null;
    public static Item catastrophe_slab = null;
    public static Item catastrophe_block = null;
    public static Item infuser_block = null;
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        final IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(
                catastrophe_shoes = setup(new ItemArmorCatastrophe(ArmorMaterial.DIAMOND, EquipmentSlotType.FEET,  new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_shoes"),
                catastrophe_leggings = setup(new ItemArmorCatastrophe(ArmorMaterial.DIAMOND, EquipmentSlotType.LEGS,  new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_leggings"),
                catastrophe_chestplate = setup(new ItemArmorCatastrophe(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST,  new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_chestplate"),
                catastrophe_hood = setup(new ItemArmorCatastrophe(ArmorMaterial.DIAMOND, EquipmentSlotType.HEAD,  new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_hood"),

                withering_rapier = setup(new ItemWitheringRapier(ItemTier.IRON, 2, -1.8f,  new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "withering_rapier"),

                catastrophe_axe = setup(new ItemCatastropheAxe(CATASTROPHE, 6, -3f,  new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_axe"),
                catastrophe_sword = setup(new ItemCatastropheSword(CATASTROPHE, 3, -2.4f,  new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_sword"),
                catastrophe_shovel = setup(new ItemCatastropheShovel(CATASTROPHE, 1, -3.0f,  new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_shovel"),
                catastrophe_pickaxe = setup(new ItemCatastrophePickaxe(CATASTROPHE, 1, -2.8f,  new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_pickaxe"),

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

                end_forged_ingot = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "end_forged_ingot"),
                end_forged_nugget = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "end_forged_nugget"),
                metal_necklace = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "metal_necklace"),
                metal_belt = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "metal_belt"),
                cursed_flare = setup(new Item(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "cursed_flare"),
                evil_spirit = setup(new ItemEvilSpirit(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "evil_spirit"),
                weapon_attunement_core = setup(new ItemWeaponAttunementCore(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "weapon_attunement_core"),
                weapon_attunement_core_strong = setup(new ItemWeaponAttunementCore(new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "weapon_attunement_core_strong"),

                dark_roofing = setup(new BlockItem(ModBlocks.dark_roofing, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "dark_roofing"),
                wooden_beam = setup(new BlockItem(ModBlocks.wooden_beam, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wooden_beam"),
                wooden_casing = setup(new BlockItem(ModBlocks.wooden_casing, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wooden_casing"),
                refined_bricks = setup(new BlockItem(ModBlocks.refined_bricks, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_bricks"),
                refined_pathway = setup(new BlockItem(ModBlocks.refined_pathway, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_pathway"),
                catastrophe_bricks = setup(new BlockItem(ModBlocks.catastrophe_bricks, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_bricks"),
                infuser_block = setup(new BlockItem(ModBlocks.infuser, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "infuser_block"),

                dark_roofing_slab = setup(new BlockItem(ModBlocks.dark_roofing_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "dark_roofing_slab"),
                wooden_casing_slab = setup(new BlockItem(ModBlocks.wooden_casing_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wooden_casing_slab"),
                refined_pathway_slab = setup(new BlockItem(ModBlocks.refined_pathway_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_pathway_slab"),
                refined_bricks_slab = setup(new BlockItem(ModBlocks.refined_bricks_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_bricks_slab"),
                catastrophe_slab = setup(new BlockItem(ModBlocks.catastrophe_slab, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_slab"),

                dark_roofing_stairs = setup(new BlockItem(ModBlocks.dark_roofing_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "dark_roofing_stairs"),
                wooden_casing_stairs = setup(new BlockItem(ModBlocks.wooden_casing_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "wooden_casing_stairs"),
                refined_bricks_stairs = setup(new BlockItem(ModBlocks.refined_bricks_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_bricks_stairs"),
                refined_pathway_stairs = setup(new BlockItem(ModBlocks.refined_pathway_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "refined_pathway_stairs"),
                catastrophe_stairs = setup(new BlockItem(ModBlocks.catastrophe_stairs, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_stairs"),
                catastrophe_block = setup(new BlockItem(ModBlocks.catastrophe_block, new Item.Properties().group(ModItemGroups.MALUM_MOD_GROUP)), "catastrophe_block")

                );
    }
    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name) {
        Preconditions.checkNotNull(name, "Name to assign to entry cannot be null!");
        return setup(entry, new ResourceLocation(MODID, name));
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
        Preconditions.checkNotNull(entry, "Entry cannot be null!");
        Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
        entry.setRegistryName(registryName);
        return entry;
    }

}