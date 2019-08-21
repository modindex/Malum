package malum.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static net.minecraftforge.event.TickEvent.Phase.START;

@Mod.EventBusSubscriber
public class ChunkloadCursedEntity
{
    static Random random;
    @SubscribeEvent
    public static void Load(TickEvent.WorldTickEvent event)
    {
        random = new Random();
        if (event.phase == START && event.world instanceof ServerWorld)
        {
            List<LivingEntity> entities = new ArrayList<>();
            for (Entity e : ((ServerWorld) event.world).getEntities().collect(Collectors.toList()))
            {
                if (e instanceof LivingEntity)
                {
                    entities.add((LivingEntity) e);
                }
            }

            for (LivingEntity entity : entities)
            {
                if (entity.getTags().contains("cursed"))
                {
                    entity.world.getBlockState(entity.getPosition());
                }
            }
        }
    }
}