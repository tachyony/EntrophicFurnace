package entrophicFurnace.tileentity;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.item.ElectricItemHelper;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.network.PacketManager;
import universalelectricity.prefab.tile.TileEntityElectrical;

import com.google.common.io.ByteArrayDataInput;

import entrophicFurnace.EntrophicFurnace;

/**
 * Tile entity
 *
 * @author Tachyony
 */
public class TileEntrophicFurnace extends TileEntityElectrical implements IPacketReceiver, net.minecraft.inventory.ISidedInventory
{
    /**
     * Max charge level
     */
    public static final long MAX_CHARGE = 120000000; // Value is likely sane now things cost more

    /**
     * The amount of watts required by the electric furnace per tick
     */
    public static final float WATTS_MULTIPLE = 120f;

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
     * The ItemStacks that hold the items currently being used in the battery
     * box
     */
    private ItemStack[] containingItems = new ItemStack[3];

    /**
     * Watts for current item
     */
    private float watts = 0;

    /**
     * Current block id
     */
    private int smeltId = 0;

    /**
	 *
	 */
    private int blockId = 0;

    /**
     * Constructor
     */
    public TileEntrophicFurnace()
    {
        super();
    }

    /**
     * Constructor
     * @param level
     * @param blockId
     * @param metadata
     */
    public TileEntrophicFurnace(int level, int blockId, int metadata)
    {
        this(level, blockId);
    }
    /**
     * Constructor
     * @param level
     * @param blockId
     */
    public TileEntrophicFurnace(int level, int blockId)
    {
        super();
        if (level == 0)
        {
            this.addWatts = WATTS_PER_TICK;
        } else if (level == 1)
        {
            this.addWatts = WATTS_PER_TICK * 4;
        } else if (level == 2)
        {
            this.addWatts = WATTS_PER_TICK * 16;
        }

        this.blockId = blockId;
    }

    /**
     * Get multiplier
     * @param slot
     * @return multiplier
     */
    public float getWattValue(int slot)
    {
        return EntrophicFurnace.itemValues.getItemValue(this.containingItems[slot]) * TileEntrophicFurnace.WATTS_MULTIPLE * SMELTING_TIME_REQUIRED;
    }
    
    /**
     * Update tick
     */
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if (!this.worldObj.isRemote)
        {
            if (this.getJoules() < MAX_CHARGE)
            {
                this.setJoules(this.getJoules() + this.addWatts);
                if (this.containingItems[0] != null)
                {
                    float burn = this.getWattValue(0);
                    if ((burn > 0) && ((this.getJoules() + burn) <= TileEntrophicFurnace.MAX_CHARGE))
                    {
                        this.setJoules(this.getJoules() + this.addWatts);
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
                // The left slot contains the item to be smelted
                if (this.smeltId != this.containingItems[1].itemID)
                {
                    this.smeltingTicks = 0;
                }

                if ((this.containingItems[1] != null) && this.canSmelt() && (this.smeltingTicks == 0))
                {
                    this.smeltingTicks = TileEntrophicFurnace.SMELTING_TIME_REQUIRED;
                    this.watts = this.getWattValue(1);
                    this.smeltId = this.containingItems[1].itemID;
                }

                if ((this.watts > 0) && (this.getEnergyStored() >= this.watts))
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
                            this.setEnergyStored(this.getEnergyStored() - this.watts);
                        }
                    } else
                    {
                        this.smeltingTicks = 0;
                    }
                }
            } else
            {
                this.smeltingTicks = 0;
            }
            
            /**
             * Recharges electric item.
             */
            this.setJoules(this.getJoules() - ElectricItemHelper.chargeItem(this.containingItems[1], this.getJoules(), this.getVoltage()));

            /**
             * Decharge electric item.
             */
            this.setJoules(this.getEnergyStored() + ElectricItemHelper.dechargeItem(this.containingItems[0], this.getMaxJoules() - this.getEnergyStored(), this.getVoltage()));
            ElectricityPack powerRequested = new ElectricityPack(1, getVoltage());
            ElectricityPack powerPack = ElectricityNetworkHelper.consumeFromMultipleSides(this, EnumSet.of(ForgeDirection.UP), powerRequested);
            this.setEnergyStored(this.getEnergyStored() + powerPack.getWatts());
            if (this.getEnergyStored() > this.getVoltage())
            {
                ElectricityPack powerRemaining = ElectricityNetworkHelper.produceFromMultipleSides(this, EnumSet.of(ForgeDirection.DOWN), new ElectricityPack(1, getVoltage()));
                this.setEnergyStored(this.getEnergyStored() - powerRemaining.getWatts());
            }
        }
    }
    
    /**
     * Get packet
     *
     * @return Packet
     */
    @Override
    public Packet getDescriptionPacket()
    {
        return PacketManager.getPacket("EntrophicFurnace", this, Integer.valueOf(this.smeltingTicks), Float.valueOf(this.getEnergyStored()), Long.valueOf(this.addWatts));
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
            this.setEnergyStored(dataStream.readFloat());
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
    }

    /**
	 *
	 */
    @Override
    public void closeChest()
    {
        //
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
        this.addWatts = par1NBTTagCompound.getLong("addWatts");
        this.smeltingTicks = par1NBTTagCompound.getInteger("smeltingTicks");
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
        par1NBTTagCompound.setLong("addWatts", this.addWatts);
        par1NBTTagCompound.setInteger("smeltingTicks", this.smeltingTicks);
        par1NBTTagCompound.setInteger("blockId", this.blockId);
        NBTTagList var2 = new NBTTagList();
        NBTTagCompound var4;
        for (int var3 = 0; var3 < this.containingItems.length; ++var3)
        {
            if (this.containingItems[var3] != null)
            {
                var4 = new NBTTagCompound();
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
	 * Stack thing, from EE3
	 */
    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            if (itemStack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            }
            else {
                itemStack = itemStack.splitStack(amount);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }

        return itemStack;
    }

    /**
	 * Slot closing, updated from EE3
	 */
    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            setInventorySlotContents(slot, null);
        }
        
        return itemStack;
    }

    /**
	 * Set inventory slot
	 */
    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        this.containingItems[slot] = itemStack;
        if ((itemStack != null) && (itemStack.stackSize > this.getInventoryStackLimit()))
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
	 *
	 */
    @Override
    public String getInvName()
    {
        if (this.blockId == EntrophicFurnace.entrophicFurnace1.blockID)
        {
            return "entrophicFurnace1";
        }
        else if (this.blockId == EntrophicFurnace.entrophicFurnace2.blockID)
        {
            return "entrophicFurnace2";
        }
        else if (this.blockId == EntrophicFurnace.entrophicFurnace3.blockID)
        {
            return "entrophicFurnace3";
        }
        else
        {
            return "entrophicFurnace";
        }
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

    /**
     * 
     */
    @Override
    public boolean isInvNameLocalized() {
        return false;
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        if (i == 0)
        {
            return true;
        }
        
        return false;
    }

    /**
     * 
     * @return voltage
     */
    @Override
    public float getVoltage() {
        return WATTS_MULTIPLE;
    }
    
    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, int side)
    {
        return this.isStackValidForSlot(i, itemStack);
    }
    
    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int side)
    {
        if (i == 2)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return new int[] { 0, 2 };
    }

    /**
     * 
     */
    @Override
    public float getEnergyStored()
    {
        return super.getEnergyStored();
    }
    
    /**
     * 
     */
    @Override
    public void setEnergyStored(float energy)
    {
        super.setEnergyStored(energy);
    }
    
    /**
     * 
     */
    @Override
    public float getMaxEnergyStored() {
        return TileEntrophicFurnace.MAX_CHARGE;
    }
    
    /**
     * 
     */
    @Override
    public boolean canConnect(ForgeDirection direction) {
        if ((direction == ForgeDirection.UP) || (direction == ForgeDirection.DOWN))
        {
            return true;
        }
        
        return false;
    }

    /**
     * 
     */
    @Override
    public float getRequest(ForgeDirection direction) {
        return 0;
    }

    /**
     * 
     */
    @Override
    public float getProvide(ForgeDirection direction) {
        return 0;
    }
}
