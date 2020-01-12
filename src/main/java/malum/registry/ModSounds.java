package malum.registry;

import malum.MalumMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = MalumMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModSounds
{
	public static final SoundEvent transmutate = makeSoundEvent("transmutate");
	
	private static SoundEvent makeSoundEvent(String name) {
		ResourceLocation loc = new ResourceLocation(MalumMod.MODID, name);
		return new SoundEvent(loc).setRegistryName(loc);
	}
	
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> evt) {
		IForgeRegistry<SoundEvent> r = evt.getRegistry();
		r.register(transmutate);
	}

	private ModSounds() {}
}
