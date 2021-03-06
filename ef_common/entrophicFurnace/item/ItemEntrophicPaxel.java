package entrophicFurnace.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import universalelectricity.core.electricity.ElectricityDisplay;
import universalelectricity.core.electricity.ElectricityDisplay.ElectricUnit;
import universalelectricity.core.item.ElectricItemHelper;
import universalelectricity.core.item.IItemElectric;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import entrophicFurnace.EntrophicFurnace;

/**
 * @author tachyony
 */
public class ItemEntrophicPaxel extends ItemTool implements IItemElectric
{
    private static final Block[] blocksEffectiveAgainst = new Block[] {
        Block.grass, Block.dirt, Block.sand, Block.gravel,
        Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField,
        Block.slowSand, Block.mycelium, Block.planks, Block.bookShelf,
        Block.wood, Block.chest, Block.stoneDoubleSlab, Block.stoneSingleSlab,
        Block.pumpkin, Block.pumpkinLantern, Block.cobblestone, Block.stone,
        Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockIron,
        Block.oreCoal, Block.blockGold, Block.oreGold, Block.oreDiamond,
        Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis,
        Block.blockLapis, Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail,
        Block.railDetector, Block.railPowered };

    /**
     * 
     * @param ID
     * @param material
     * @param name
     */
    public ItemEntrophicPaxel(int ID, EnumToolMaterial material, String name)
    {
        super(ID, 3, material, blocksEffectiveAgainst);
        this.damageVsEntity = 6 + this.toolMaterial.getDamageVsEntity();
        this.setUnlocalizedName(name);
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
        this.setCreativeTab(CreativeTabs.tabTools);
        this.setNoRepair();
    }

    /**
     * 
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean par4)
    {
        String color = "";
        float joules = this.getElectricityStored(itemStack);

        if (joules <= this.getMaxElectricityStored(itemStack) / 3)
        {
            color = "\u00a74";
        }
        else if (joules > this.getMaxElectricityStored(itemStack) * 2 / 3)
        {
            color = "\u00a72";
        }
        else
        {
            color = "\u00a76";
        }

        list.add(color + ElectricityDisplay.getDisplayShort(joules, ElectricUnit.JOULES) + "/" + ElectricityDisplay.getDisplayShort(this.getMaxElectricityStored(itemStack), ElectricUnit.JOULES));
    }
    
    /**
     * Makes sure the item is uncharged when it is crafted and not charged. Change this if you do
     * not want this to happen!
     */
    @Override
    public void onCreated(ItemStack itemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        this.setElectricity(itemStack, 0);
    }
    
    /**
     * 
     */
    @Override
    public float recharge(ItemStack itemStack, float energy, boolean doReceive)
    {
        float rejectedElectricity = Math.max((this.getElectricityStored(itemStack) + energy) - this.getMaxElectricityStored(itemStack), 0);
        float energyToReceive = energy - rejectedElectricity;
        if (doReceive)
        {
            this.setElectricity(itemStack, this.getElectricityStored(itemStack) + energyToReceive);
        }

        return energyToReceive;
    }

    /**
     * 
     */
    @Override
    public float discharge(ItemStack itemStack, float energy, boolean doTransfer)
    {
        float energyToTransfer = Math.min(this.getElectricityStored(itemStack), energy);
        if (doTransfer)
        {
            this.setElectricity(itemStack, this.getElectricityStored(itemStack) - energyToTransfer);
        }

        return energyToTransfer;
    }
    
    /**
     * 
     */
    @Override
    public void setElectricity(ItemStack itemStack, float joules)
    {
        // Saves the frequency in the ItemStack
        if (itemStack.getTagCompound() == null)
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        float electricityStored = Math.max(Math.min(joules, this.getMaxElectricityStored(itemStack)), 0);
        itemStack.getTagCompound().setFloat("electricity", electricityStored);

        /** Sets the damage as a percentage to render the bar properly. */
        itemStack.setItemDamage((int) (100 - (electricityStored / getMaxElectricityStored(itemStack)) * 100));
    }

    /**
     * 
     */
    @Override
    public float getTransfer(ItemStack itemStack)
    {
        return this.getMaxElectricityStored(itemStack) - this.getElectricityStored(itemStack);
    }
    
    /** Gets the energy stored in the item. Energy is stored using item NBT */
    @Override
    public float getElectricityStored(ItemStack itemStack)
    {
        if (itemStack.getTagCompound() == null)
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        float energyStored = 0f;
        if (itemStack.getTagCompound().hasKey("electricity"))
        {
            NBTBase obj = itemStack.getTagCompound().getTag("electricity");
            if (obj instanceof NBTTagDouble)
            {
                energyStored = (float) ((NBTTagDouble) obj).data;
            }
            else if (obj instanceof NBTTagFloat)
            {
                energyStored = ((NBTTagFloat) obj).data;
            }
        }

        /** Sets the damage as a percentage to render the bar properly. */
        itemStack.setItemDamage((int) (100 - (energyStored / getMaxElectricityStored(itemStack)) * 100));
        return energyStored;
    }
    
    /**
     * 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(ElectricItemHelper.getUncharged(new ItemStack(this)));
        par3List.add(ElectricItemHelper.getWithCharge(new ItemStack(this), this.getMaxElectricityStored(new ItemStack(this))));
    }
    
    /**
     * 
     */
    @Override
    public boolean canHarvestBlock(Block par1Block)
    {
        if ((par1Block.blockMaterial == Material.anvil || par1Block.blockMaterial == Material.cactus
                || par1Block.blockMaterial == Material.cake || par1Block.blockMaterial == Material.circuits
                || par1Block.blockMaterial == Material.clay || par1Block.blockMaterial == Material.cloth
                || par1Block.blockMaterial == Material.coral || par1Block.blockMaterial == Material.craftedSnow
                || par1Block.blockMaterial == Material.dragonEgg || par1Block.blockMaterial == Material.fire
                || par1Block.blockMaterial == Material.grass || par1Block.blockMaterial == Material.ground
                || par1Block.blockMaterial == Material.ice || par1Block.blockMaterial == Material.iron
                || par1Block.blockMaterial == Material.lava || par1Block.blockMaterial == Material.leaves
                || par1Block.blockMaterial == Material.piston || par1Block.blockMaterial == Material.pumpkin
                || par1Block.blockMaterial == Material.redstoneLight || par1Block.blockMaterial == Material.rock
                || par1Block.blockMaterial == Material.sand || par1Block.blockMaterial == Material.snow
                || par1Block.blockMaterial == Material.sponge || par1Block.blockMaterial == Material.tnt
                || par1Block.blockMaterial == Material.vine || par1Block.blockMaterial == Material.water
                || par1Block.blockMaterial == Material.web || par1Block.blockMaterial == Material.wood))
        {
            return true;
        }

        return false;
    }

    /**
     * 
     */
    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        if ((par2Block != null)
                && (par2Block.blockMaterial == Material.anvil || par2Block.blockMaterial == Material.cactus
                || par2Block.blockMaterial == Material.cake || par2Block.blockMaterial == Material.circuits
                || par2Block.blockMaterial == Material.clay || par2Block.blockMaterial == Material.cloth
                || par2Block.blockMaterial == Material.coral || par2Block.blockMaterial == Material.craftedSnow
                || par2Block.blockMaterial == Material.dragonEgg || par2Block.blockMaterial == Material.fire
                || par2Block.blockMaterial == Material.grass || par2Block.blockMaterial == Material.ground
                || par2Block.blockMaterial == Material.ice || par2Block.blockMaterial == Material.iron
                || par2Block.blockMaterial == Material.lava || par2Block.blockMaterial == Material.leaves
                || par2Block.blockMaterial == Material.piston || par2Block.blockMaterial == Material.pumpkin
                || par2Block.blockMaterial == Material.redstoneLight || par2Block.blockMaterial == Material.rock
                || par2Block.blockMaterial == Material.sand || par2Block.blockMaterial == Material.snow
                || par2Block.blockMaterial == Material.sponge || par2Block.blockMaterial == Material.tnt
                || par2Block.blockMaterial == Material.vine || par2Block.blockMaterial == Material.water
                || par2Block.blockMaterial == Material.web || par2Block.blockMaterial == Material.wood))
        {
            return this.efficiencyOnProperMaterial;
        }

        return  super.getStrVsBlock(par1ItemStack, par2Block);
    }

    /**
     * 
     */
    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving,
            EntityLivingBase par3EntityLiving)
    {
        par1ItemStack.damageItem(1, par3EntityLiving);
        return true;
    }

    /**
     * 
     */
    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4,
            int par5, int par6, EntityLivingBase par7EntityLiving)
    {
        if (Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D)
        {
            par1ItemStack.damageItem(1, par7EntityLiving);
        }

        return true;
    }

    /**
     * Return the itemDamage represented by this ItemStack. Defaults to the itemDamage field on ItemStack, but can be overridden here for other sources such as NBT.
     *
     * @param stack The itemstack that is damaged
     * @return the damage value
     */
    @Override
    public int getDamage(ItemStack stack)
    {
        return super.getDamage(stack);
    }

    /**
     * 
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * 
     */
    @Override
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

    /**
     * 
     */
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }

    /**
     * 
     */
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }
    
    /**
     * @return ToString
     */
    @Override
    public String getToolMaterialName()
    {
        return this.toolMaterial.toString();
    }

    /**
     * 
     */
    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return EntrophicFurnace.entrophicOre4.itemID == par2ItemStack.itemID ? true : super.getIsRepairable(
                par1ItemStack, par2ItemStack);
    }

    /**
     * 
     */
    @Override
    public float getStrVsBlock(ItemStack stack, Block block, int meta)
    {
        if (ForgeHooks.isToolEffective(stack, block, meta))
        {
            return this.efficiencyOnProperMaterial;
        }

        return getStrVsBlock(stack, block);
    }

    /**
     * 
     */
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer,
            World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }
        UseHoeEvent event = new UseHoeEvent(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6);
        if (MinecraftForge.EVENT_BUS.post(event))
        {
            return false;
        }
        
        if (event.getResult() == Result.ALLOW)
        {
            par1ItemStack.damageItem(1, par2EntityPlayer);
            return true;
        }
        
        int i1 = par3World.getBlockId(par4, par5, par6);
        boolean air = par3World.isAirBlock(par4, par5 + 1, par6);
        
        //Forge: Change 0 to air, also BugFix: parens mismatch causing you to be able to hoe dirt under dirt/grass
        if (par7 == 0 || !air || (i1 != Block.grass.blockID && i1 != Block.dirt.blockID))
        {
            return false;
        }
        
        Block var13 = Block.tilledField;
        par3World.playSoundEffect(par4 + 0.5F, par5 + 0.5F, par6 + 0.5F, var13.stepSound.getStepSound(),
                (var13.stepSound.getVolume() + 1.0F) / 2.0F, var13.stepSound.getPitch() * 0.8F);
        if (par3World.isRemote)
        {
            return true;
        }
        
        par3World.setBlock(par4, par5, par6, var13.blockID);
        par1ItemStack.damageItem(1, par2EntityPlayer);
        return true;
    }

    /**
     * 
     */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }

    /**
     * 
     */
    @Override
    public float getVoltage(ItemStack itemStack)
    {
        return 0.120f;
    }

    /**
     * 
     */
    @Override
    public float getMaxElectricityStored(ItemStack theItem) {
        return getVoltage(theItem) * 1000000;
    }
}
