package net.geforcemods.securitycraft.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

public class SpiritImbuerContainer extends Container {

	public SpiritImbuerContainer(ContainerType<SpiritImbuerContainer> type, int windowId)
	{
		super(type, windowId);
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return true;
	}
}
