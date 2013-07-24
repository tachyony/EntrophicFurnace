package entrophicFurnace.generic;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.block.IEnergyStorage;
import universalelectricity.core.electricity.ElectricityNetworkHelper;
import universalelectricity.core.electricity.IElectricityNetwork;
import universalelectricity.core.item.ElectricItemHelper;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.network.PacketManager;
import universalelectricity.prefab.tile.TileEntityElectricalStorage;

import com.google.common.io.ByteArrayDataInput;

/**
 * Tile entity
 *
 * @author Tachyony
 */
@SuppressWarnings("unused")
public class TileEntityEntrophicFurnace extends TileEntityElectricalStorage implements IEnergyStorage, IPacketReceiver, ISidedInventory
{
    /**
     * Max charge level
     */
    public static final long MAX_CHARGE = 120000000; // Value is likely sane now things cost more

    /**
     * The amount of watts required by the electric furnace per tick
     */
    public static final long WATTS_MULTIPLE = 120;

    /**
     * 20 for game tick * 50 smelting time div 3 secs
     */
    public static final long WATTS_PER_TICK = 100;

    /**
     *
     */
    public long addWatts = 0;

    /**
     * The amount of ticks required to smelt this item
     */
    public static final int SMELTING_TIME_REQUIRED = 50;

    /**
     * The amount of ticks required to smelt this item, for high tier furnace
     *
     * public static final int SMELTING_TIME_REQUIRED_OP = 5;
     */

    /**
     * How many ticks has this item been smelting for?
     */
    public int smeltingTicks = 0;

    /**
     * Internal charge
     */
    public double joules = 0;

    /**
     * The ItemStacks that hold the items currently being used in the battery
     * box
     */
    private ItemStack[] containingItems = new ItemStack[3];

    /**
     * Number of players using this
     */
    private int playersUsing = 0;

    /**
     * Watts for current item
     */
    private long watts = 0;

    /**
     * Current block id
     */
    private int smeltId = 0;

    /**
	 *
	 */
    private int blockId = 0;

    /**
	 *
	 */
    private boolean isFull = false;

    /**
     * Is a high tier furnace
     *
     * private boolean isSuperFurnace = false;
     */

    /**
     * Constructor
     */
    public TileEntityEntrophicFurnace()
    {
        super();
    }

    /**
     * Constructor
     * @param level
     * @param blockId
     */
    public TileEntityEntrophicFurnace(int level, int blockId)
    {
        super();
        if (level == 0)
        {
            this.addWatts = WATTS_PER_TICK;
            // this.isSuperFurnace = false;
        } else if (level == 1)
        {
            this.addWatts = WATTS_PER_TICK * 4;
            // this.isSuperFurnace = false;
        } else if (level == 2)
        {
            this.addWatts = WATTS_PER_TICK * 16;
            // this.isSuperFurnace = true;
        }

        this.blockId = blockId;
    }

    /**
     * Get multiplier
     * @param slot
     * @return multiplier
     */
    public long getWattValue(int slot)
    {
        long requiredWatts = 0;
        if (this.containingItems[slot].isItemEqual(new ItemStack(Block.cobblestone))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.dirt))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.sand))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.stone))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.stoneBrick))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.glass))
                || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicOre))
                || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicOre1))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.silk))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.netherrack))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.slowSand))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.mycelium))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.grass))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.cobblestoneMossy)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 0))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 1))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 2))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 3))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 4))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 5))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 6))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 7))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 8))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 9))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 10))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 11))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 12))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 13))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 14))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.cloth, 1, 15))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.gravel))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.flint))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.sandStone))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.netherBrick)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 4;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(Item.feather, 1, 3)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 5;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(Item.egg))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.chickenRaw))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.chickenCooked))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.porkRaw))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.porkCooked)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 6;
	    } else if (this.containingItems[slot].isItemEqual(new ItemStack(Item.netherStalkSeeds, 1, 3))
	            || this.containingItems[slot].isItemEqual(new ItemStack(Block.furnaceIdle))
	            || this.containingItems[slot].isItemEqual(new ItemStack(Item.clay))
	            || this.containingItems[slot].isItemEqual(new ItemStack(Item.brick))
	            || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicOre2)))
	    {
	        requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 8;
	    } else if (this.containingItems[slot].isItemEqual(new ItemStack(Block.melon))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.pumpkin))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.cactus))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.seeds))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.wheat))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.reed))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.carrot))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.potato))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.wood, 1, 0))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.wood, 1, 1))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.wood, 1, 2))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.wood, 1, 3))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.coal, 1, 0))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.coal, 1, 1))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.leather)))
        {
	        requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 16;
        } 
	    else if (this.containingItems[slot].isItemEqual(new ItemStack(Item.gunpowder)))
	    {
	        requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 24;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(Block.blockClay))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Block.brick))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.obsidian))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.waterlily))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.enderPearl)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 32;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(Block.sapling, 1, 0))
            || this.containingItems[slot].isItemEqual(new ItemStack(Block.sapling, 1, 1))
            || this.containingItems[slot].isItemEqual(new ItemStack(Block.sapling, 1, 2))
            || this.containingItems[slot].isItemEqual(new ItemStack(Block.sapling, 1, 3)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 40;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 0))
                || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 1))
                || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 2))
                || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 3)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 48;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(Item.ingotIron))
                || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.ingotCopper))
                || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.ingotTin))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.redstone))
                || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicOre3)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 64;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(Item.blazeRod))
                || this.containingItems[slot].isItemEqual(new ItemStack(Item.slimeBall))
                || this.containingItems[slot].isItemEqual(new ItemStack(Block.glowStone)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 128;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(Item.ingotGold))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Item.emerald)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 256;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicOre4)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 512;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(Item.diamond)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 1024;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(Block.blockGold)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 2304;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 0))
                || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 1))
                || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 2))
                || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 3)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 2496;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicOre5)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 4096;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(Block.blockDiamond))
        		|| this.containingItems[slot].isItemEqual(new ItemStack(Item.netherStar)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 8192;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicPaxel)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 9216;
        } else if (this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 0))
                || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 1))
                || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 2))
                || this.containingItems[slot].isItemEqual(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 3)))
        {
            requiredWatts = TileEntityEntrophicFurnace.WATTS_MULTIPLE * 18176;
        } else
        {
            // Something we can not smelt
            requiredWatts = 0;
        }

        return requiredWatts * SMELTING_TIME_REQUIRED;
    }

    /**
     * Update tick
     */
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if (this.joules < MAX_CHARGE)
        {
            this.joules += this.addWatts;
            if (this.containingItems[0] != null)
            {
                long burn = this.getWattValue(0);
                if ((burn > 0) && ((this.joules + burn) <= TileEntityEntrophicFurnace.MAX_CHARGE))
                {
                    this.joules += burn;
                    --this.containingItems[0].stackSize;
                    if (this.containingItems[0].stackSize == 0)
                    {
                        this.containingItems[0] = this.containingItems[0].getItem().getContainerItemStack(this.containingItems[0]);
                    }
                }
            }
        }

        if (this.containingItems[1] != null)
        {
            if (!this.isDisabled())
            {
                // The left slot contains the item to be smelted
                if (this.smeltId != this.containingItems[1].itemID)
                {
                    this.smeltingTicks = 0;
                }

                if ((this.containingItems[1] != null) && this.canSmelt() && (this.smeltingTicks == 0))
                {
                    /*
                     * if (this.isSuperFurnace) { this.smeltingTicks =
                     * this.SMELTING_TIME_REQUIRED_OP; } else {
                     */
                    this.smeltingTicks = TileEntityEntrophicFurnace.SMELTING_TIME_REQUIRED;
                    /* } */
                    this.watts = this.getWattValue(1);
                    this.smeltId = this.containingItems[1].itemID;
                }

                if ((this.watts > 0) && (this.joules >= this.watts))
                {
                    // Checks if the item can be smelted and if the
                    // smelting time left
                    // is greater than 0, if so, then smelt the
                    // item.
                    if (this.canSmelt() && this.smeltingTicks > 0)
                    {
                        this.smeltingTicks--;

                        // When the item is finished smelting
                        if (this.smeltingTicks < 1)
                        {
                            this.smeltItem();
                            this.smeltingTicks = 0;
                            this.joules -= this.watts;
                        }
                    } else
                    {
                        this.smeltingTicks = 0;
                    }
                }
            }
        } else
        {
            this.smeltingTicks = 0;
        }

        if (!this.isDisabled())
        {
            if (!this.worldObj.isRemote)
            {
                /**
                 * Recharges electric item.
                 *
                this.setJoules(this.joules - ElectricItemHelper.chargeItem(this.containingItems[1], this.joules, this.getVoltage()));

                /**
                 * Decharge electric item.
                 *
                this.setJoules(this.joules + ElectricItemHelper.dechargeItem(this.containingItems[0], this.getMaxJoules() - this.joules, this.getVoltage()));*/

                TileEntity inputTile = VectorHelper.getConnectorFromSide(this.worldObj, new Vector3(this), ForgeDirection.UP);
                TileEntity outputTile = VectorHelper.getConnectorFromSide(this.worldObj, new Vector3(this), ForgeDirection.DOWN);
                
                IElectricityNetwork inputNetwork = ElectricityNetworkHelper.getNetworkFromTileEntity(inputTile, ForgeDirection.UP);
                IElectricityNetwork outputNetwork = ElectricityNetworkHelper.getNetworkFromTileEntity(outputTile, ForgeDirection.DOWN);
                
                if ((outputNetwork != null) && (inputNetwork != outputNetwork))
                {
                    /*double outputWatts = Math.min(outputNetwork.getRequest(this).getWatts(), Math.min(this.joules, 10000));

                    if ((this.joules > 0) && (outputWatts > 0))
                    {
                        outputNetwork.startProducing(this, outputWatts / this.getVoltage(), this.getVoltage());
                        this.joules = this.joules - outputWatts;
                    }
                    else
                    {
                        outputNetwork.stopProducing(this);
                    }*/
                }
            }
        }
    }
    
    /**
     * Get packet
     *
     * @return Packet
     */
    @SuppressWarnings("boxing")
    @Override
    public Packet getDescriptionPacket()
    {
        return PacketManager.getPacket(BlockIds.channel, this, this.smeltingTicks, this.disabledTicks, this.joules, this.addWatts);
    }

    /**
     * Read in data
     */
    @Override
    public void handlePacketData(INetworkManager network, int type, Packet250CustomPayload packet, EntityPlayer player,
            ByteArrayDataInput dataStream)
    {
        try
        {
            this.smeltingTicks = dataStream.readInt();
            this.disabledTicks = dataStream.readInt();
            this.joules = dataStream.readDouble();
            this.addWatts = dataStream.readLong();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * On open chest
     */
    @Override
    public void openChest()
    {
        if (!this.worldObj.isRemote)
        {
            PacketManager.sendPacketToClients(getDescriptionPacket(), this.worldObj, new Vector3(this), 15);
        }

        this.playersUsing++;
    }

    /**
	 *
	 */
    @Override
    public void closeChest()
    {
        this.playersUsing--;
    }

    /**
     * Check all conditions and see if we can start smelting
     *
     * @return Cam smelt it
     */
    public boolean canSmelt()
    {
        if (this.containingItems[1] == null)
        {
            return false;
        }

        if (this.containingItems[2] != null)
        {
            if (!this.containingItems[2].isItemEqual(new ItemStack(this.containingItems[1].getItem(), 0,
                    this.containingItems[1].getItemDamage())))
            {
                return false;
            }

            if (this.containingItems[2].stackSize + 1 > 64)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted
     * item in the furnace result stack
     */
    public void smeltItem()
    {
        ItemStack resultItemStack = new ItemStack(this.containingItems[1].getItem(), 1,
                this.containingItems[1].getItemDamage());
        if (this.containingItems[2] == null)
        {
            this.containingItems[2] = resultItemStack.copy();
        } else if (this.containingItems[2].isItemEqual(resultItemStack))
        {
            this.containingItems[2].stackSize++;
        }

        if (this.containingItems[1].stackSize <= 0)
        {
            this.containingItems[1] = null;
        }
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        this.joules = par1NBTTagCompound.getDouble("internalCharge");
        this.addWatts = par1NBTTagCompound.getLong("addWatts");
        this.smeltingTicks = par1NBTTagCompound.getInteger("smeltingTicks");
        // this.isSuperFurnace =
        // par1NBTTagCompound.getBoolean("isSuperFurnace");
        this.blockId = par1NBTTagCompound.getInteger("blockID");
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.containingItems = new ItemStack[this.getSizeInventory()];
        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");
            if (var5 >= 0 && var5 < this.containingItems.length)
            {
                this.containingItems[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setDouble("internalCharge", this.joules);
        par1NBTTagCompound.setLong("addWatts", this.addWatts);
        par1NBTTagCompound.setInteger("smeltingTicks", this.smeltingTicks);
        // par1NBTTagCompound.setBoolean("isSuperFurnace", this.isSuperFurnace);
        par1NBTTagCompound.setInteger("blockId", this.blockId);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.containingItems.length; ++var3)
        {
            if (this.containingItems[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte) var3);
                this.containingItems[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }

    /**
	 *
	 */
    @Override
    public int getSizeInventory()
    {
        return this.containingItems.length;
    }

    /**
	 *
	 */
    @Override
    public ItemStack getStackInSlot(int par1)
    {
        return this.containingItems[par1];
    }

    /**
     *
     * @return Joules
     */
    public double getInternalCharge()
    {
        return this.joules;
    }

    /**
	 *
	 */
    @Override
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.containingItems[par1] != null)
        {
            ItemStack var3;
            if (this.containingItems[par1].stackSize <= par2)
            {
                var3 = this.containingItems[par1];
                this.containingItems[par1] = null;
                return var3;
            }
            var3 = this.containingItems[par1].splitStack(par2);
            if (this.containingItems[par1].stackSize == 0)
            {
                this.containingItems[par1] = null;
            }

            return var3;
        }
        return null;
    }

    /**
	 *
	 */
    @Override
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.containingItems[par1] != null)
        {
            ItemStack var2 = this.containingItems[par1];
            this.containingItems[par1] = null;
            return var2;
        }
        return null;
    }

    /**
	 *
	 */
    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.containingItems[par1] = par2ItemStack;
        if ((par2ItemStack != null) && (par2ItemStack.stackSize > this.getInventoryStackLimit()))
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
	 *
	 */
    @Override
    public String getInvName()
    {
        return "Quantum Furnace";
    }

    /**
	 *
	 */
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
	 *
	 */
    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
                : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public boolean isInvNameLocalized() {
        return false;
    }

    @Override
    public boolean isStackValidForSlot(int i, ItemStack itemstack) {
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        return null;
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        return false;
    }

    @Override
    public float getMaxEnergyStored() {
        return 0;
    }
}
