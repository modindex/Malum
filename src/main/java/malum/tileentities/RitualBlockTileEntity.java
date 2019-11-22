package malum.tileentities;


import malum.MalumMod;
import malum.recipes.RitualRecipe;
import malum.registry.ModRecipes;
import malum.registry.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.item.minecart.TNTMinecartEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.*;

import static net.minecraft.block.Block.spawnAsEntity;

public class RitualBlockTileEntity extends TileEntity implements ITickableTileEntity
{
    public RitualBlockTileEntity()
    {
        super(ModTileEntities.ritual_block_tile_entity);
    }

    public int crafting;
    public UUID uuid = null;
    public int[] position = null;
    public ItemStackHandler inventory = new ItemStackHandler(12)
    {
        
        @Override
        protected void onContentsChanged(int slot)
        {
            RitualBlockTileEntity.this.markDirty();
            if (!world.isRemote)
            {
                updateContainingBlockInfo();
                BlockState state = world.getBlockState(pos);
                world.notifyBlockUpdate(pos, state, state, 3);
            }
        }
    };

    @Override
    public CompoundNBT getUpdateTag()
    {
        return this.write(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag)
    {
        read(tag);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        return new SUpdateTileEntityPacket(pos, 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        handleUpdateTag(pkt.getNbtCompound());
    }


    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        super.write(compound);
        if (position != null)
        {
            compound.putIntArray("position", position);
        }
        if (uuid != null)
        {
            compound.putUniqueId("uuid", uuid);
        }
        compound.put("inventory", inventory.serializeNBT());
        compound.putInt("crafting", crafting);
        return compound;
    }

    @Override
    public void read(CompoundNBT compound)
    {
        if (compound.get("position") != null)
        {
            position = compound.getIntArray("position");
        }
        else
        {
            position = null;
        }
        if (compound.hasUniqueId("uuid"))
        {
            uuid = compound.getUniqueId("uuid");
        }
        else
        {
            uuid = null;
        }
        inventory.deserializeNBT((CompoundNBT) Objects.requireNonNull(compound.get("inventory")));
        crafting = compound.getInt("crafting");
        super.read(compound);
    }
    public ArrayList<Item> listEveryItem(ItemStackHandler inventory)
    {
        ArrayList<Item> ingredients = new ArrayList<>();
        for (int counter=0; counter < inventory.getSlots(); counter++)
        {
            if (!inventory.getStackInSlot(counter).isEmpty())
            {
                ingredients.add(inventory.getStackInSlot(counter).getItem());
            }
        }
        return ingredients;
    }

    @Override
    public void tick()
    {
        if (crafting > 1)
        {
            crafting -= 1;
            MalumMod.LOGGER.info("counting down");
            assert this.world != null;
            this.world.addParticle(ParticleTypes.DRAGON_BREATH, this.pos.getX(), this.pos.getY(), this.pos.getZ(), 0, 0.2, 0);
        }
        if (crafting == 1)
        {
            RitualRecipe recipe = ModRecipes.getRitualRecipe(listEveryItem(inventory));
            if (recipe != null)
            {
                assert this.world != null;
                if (position != null)
                {
                    if (recipe.getRitualID() == 1)
                    {
                        for (int a = 0; a <= 100; a += 1)
                        {
                            this.world.addEntity(new FireballEntity(this.world, position[0], position[1], position[2], 0.0, -0.01, 0.0));
                            this.world.addEntity(new TNTMinecartEntity(this.world, position[0], position[1], position[2]));
                        }
                    }
                }
                if (uuid != null)
                {
                    if (recipe.getRitualID() == 0)
                    {
                        if (this.world instanceof ServerWorld)
                        {
                            ServerWorld worldServer = (ServerWorld) this.world;
                            if (worldServer.getEntityByUuid(uuid) != null && Objects.requireNonNull(worldServer.getEntityByUuid(uuid)).isAlive())
                            {
                                Objects.requireNonNull(worldServer.getEntityByUuid(uuid)).setVelocity(0, 5, 0);
                            }
                        }
                    }
                }
            }
            uuid = null;
            position = null;
            crafting = 0;
        }
    }
}