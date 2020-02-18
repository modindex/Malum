package malum.items.tools;

import malum.capabilities.PlayerProperties;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;

public class SoulEaterSwordItem extends SwordItem
{

    private static final IItemPropertyGetter DAMAGE_BOOST_GETTER = (stack, world, entity) ->
        entity instanceof PlayerEntity ? Math.min((int)PlayerProperties.getSoulEaterDamageBoost((PlayerEntity) entity) / 5, 5) : 0;

    public SoulEaterSwordItem(IItemTier material, int attackDamage, float attackSpeed, Properties properties) {
        super(material, attackDamage+3, attackSpeed-2.4f, properties);
        this.addPropertyOverride(new ResourceLocation("damage"), DAMAGE_BOOST_GETTER);

    }
}

