//https://gist.github.com/pau101/194562af2dc6a9060b2b4be7296879c4

package malum.ingredients;

import com.google.gson.JsonObject;

import com.google.gson.JsonSyntaxException;

import net.minecraft.item.ItemStack;

import net.minecraft.item.Items;

import net.minecraft.item.crafting.Ingredient;

import net.minecraft.nbt.CompoundNBT;

import net.minecraft.network.PacketBuffer;

import net.minecraft.util.JSONUtils;

import net.minecraft.util.ResourceLocation;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.common.crafting.IIngredientSerializer;

import net.minecraftforge.common.util.Constants;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;



import javax.annotation.Nullable;

import java.util.Arrays;

import java.util.Collection;

import java.util.Map;

import java.util.Optional;

import java.util.stream.Collectors;

import java.util.stream.Stream;

public class SpiritIngredient extends Ingredient {
    private static final String SOUL = "soul";


    private static final String GRAVE = "grave";


    private static final Map<String, Biome.Category> CAT_BY_NAME = ObfuscationReflectionHelper.getPrivateValue(Biome.Category.class, null, "field_222354_r");


    private final ResourceLocation soul;


    private final Biome.Category grave;


    protected SpiritIngredient(final ResourceLocation soul, final Biome.Category grave) {

        super(Stream.of(new SpiritList(soul, grave)));

        this.soul = soul;

        this.grave = grave;

    }


    @Override

    public boolean test(final @Nullable ItemStack input) {

        if (input == null || input.getItem() != Items.APPLE) {

            return false;

        }

        final CompoundNBT tag = input.getTag();

        if (tag != null && tag.contains(SOUL, Constants.NBT.TAG_STRING) &&

            this.soul.equals(ResourceLocation.tryCreate(tag.getString(SOUL)))) {

            if (this.grave != Biome.Category.NONE) {

                return tag.contains(GRAVE, Constants.NBT.TAG_STRING) &&

                    getCategoryByName(tag.getString(GRAVE)).filter(this.grave::equals).isPresent();

            }

            return true;

        }

        return false;

    }


    @Override

    public boolean isSimple() {

        return false;

    }


    @Override

    public IIngredientSerializer<? extends Ingredient> getSerializer() {

        return Serializer.INSTANCE;

    }


    public static class Serializer implements IIngredientSerializer<SpiritIngredient> {

        public static final Serializer INSTANCE = new Serializer();


        @Override

        public SpiritIngredient parse(final JsonObject json) {

            final String entity = JSONUtils.getString(json, SOUL);

            final String biome = JSONUtils.getString(json, GRAVE);

            return new SpiritIngredient(

                new ResourceLocation(entity),

                getCategoryByName(biome).orElseThrow(() -> new JsonSyntaxException("Unknown biome category: " + biome))

            );

        }


        @Override

        public void write(final PacketBuffer buffer, final SpiritIngredient ingredient) {

            buffer.writeResourceLocation(ingredient.soul);

            buffer.writeString(ingredient.grave.getName());

        }


        @Override

        public SpiritIngredient parse(final PacketBuffer buffer) {

            final ResourceLocation entity = buffer.readResourceLocation();

            final String biome = buffer.readString(40);

            return new SpiritIngredient(entity, getCategoryByName(biome).orElse(Biome.Category.NONE));

        }

    }


    private static Optional<Biome.Category> getCategoryByName(final String name) {

        return Optional.ofNullable(CAT_BY_NAME.get(name));

    }


    private static final class SpiritList implements IItemList {

        private final ResourceLocation soul;


        private final Biome.Category grave;


        private SpiritList(final ResourceLocation soul, final Biome.Category grave) {

            this.soul = soul;

            this.grave = grave;

        }


        @Override

        public Collection<ItemStack> getStacks() {

            final Stream<Biome.Category> biomes;

            if (this.grave == Biome.Category.NONE) {

                biomes = Arrays.stream(Biome.Category.values()).filter(b -> b != Biome.Category.NONE);

            } else {

                biomes = Stream.of(this.grave);

            }

            return biomes.map(biome -> {

                final ItemStack stack = new ItemStack(Items.APPLE);

                final CompoundNBT tag = stack.getOrCreateTag();

                tag.putString(SOUL, this.soul.toString());

                tag.putString(GRAVE, biome.getName());

                return stack;

            })

                .collect(Collectors.toList());

        }


        @Override

        public JsonObject serialize() {

            final JsonObject json = new JsonObject();

            json.addProperty(SOUL, this.soul.toString());

            json.addProperty(GRAVE, this.grave.getName());

            return json;

        }

    }

}

