package entrophicFurnace.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import universalelectricity.core.electricity.ElectricityDisplay;
import universalelectricity.core.electricity.ElectricityPack;
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
        this.setMaxDamage(material.getMaxUses());
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
        double joules = this.getJoules(itemStack);

        if (joules <= this.getMaxJoules(itemStack) / 3)
        {
            color = "\u00a74";
        }
        else if (joules > this.getMaxJoules(itemStack) * 2 / 3)
        {
            color = "\u00a72";
        }
        else
        {
            color = "\u00a76";
        }

        list.add(color + ElectricityDisplay.getDisplay(joules, ElectricUnit.JOULES) + "/" + ElectricityDisplay.getDisplay(this.getMaxJoules(itemStack), ElectricUnit.JOULES));
    }
    
    /**
     * Makes sure the item is uncharged when it is crafted and not charged. Change this if you do
     * not want this to happen!
     */
    @Override
    public void onCreated(ItemStack itemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        this.setJoules(0, itemStack);
    }
    
    /**
     * 
     */
    @Override
    public ElectricityPack onReceive(ElectricityPack electricityPack, ItemStack itemStack)
    {
        double rejectedElectricity = Math.max((this.getJoules(itemStack) + electricityPack.getWatts()) - this.getMaxJoules(itemStack), 0);
        double joulesToStore = electricityPack.getWatts() - rejectedElectricity;
        this.setJoules(this.getJoules(itemStack) + joulesToStore, itemStack);
        return ElectricityPack.getFromWatts(joulesToStore, this.getVoltage(itemStack));
    }
    
    /**
     * 
     */
    @Override
    public ElectricityPack onProvide(ElectricityPack electricityPack, ItemStack itemStack)
    {
        double electricityToUse = Math.min(this.getJoules(itemStack), electricityPack.getWatts());
        this.setJoules(this.getJoules(itemStack) - electricityToUse, itemStack);
        return ElectricityPack.getFromWatts(electricityToUse, this.getVoltage(itemStack));
    }
    
    /**
     * 
     */
    @Override
    public ElectricityPack getReceiveRequest(ItemStack itemStack)
    {
        return ElectricityPack.getFromWatts(Math.min(this.getMaxJoules(itemStack) - this.getJoules(itemStack), this.getTransferRate(itemStack)), this.getVoltage(itemStack));
    }

    /**
     * 
     */
    @Override
    public ElectricityPack getProvideRequest(ItemStack itemStack)
    {
        return ElectricityPack.getFromWatts(Math.min(this.getJoules(itemStack), this.getTransferRate(itemStack)), this.getVoltage(itemStack));
    }

    /**
     * 
     * @param itemStack
     * @return Transfer rate
     */
    public double getTransferRate(ItemStack itemStack)
    {
        return this.getMaxJoules(itemStack) * 0.01;
    }
    
    /**
     * This function sets the electricty. Do not directly call this function. Try to use
     * onReceiveElectricity or onUseElectricity instead.
     * @param joules - The amount of electricity in joules
     */
    @Override
    public void setJoules(double joules, ItemStack itemStack)
    {
        // Saves the frequency in the ItemStack
        if (itemStack.getTagCompound() == null)
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        double electricityStored = Math.max(Math.min(joules, this.getMaxJoules(itemStack)), 0);
        itemStack.getTagCompound().setDouble("electricity", electricityStored);

        /**
         * Sets the damage as a percentage to render the bar properly.
         */
        itemStack.setItemDamage((int) (100 - (electricityStored / getMaxJoules(itemStack)) * 100));
    }
    
    /**
     * This function is called to get the electricity stored in this item
     * 
     * @return - The amount of electricity stored in watts
     */
    @Override
    public double getJoules(ItemStack itemStack)
    {
        if (itemStack.getTagCompound() == null)
        {
            return 0;
        }

        double electricityStored = itemStack.getTagCompound().getDouble("electricity");

        /**
         * Sets the damage as a percentage to render the bar properly.
         */
        itemStack.setItemDamage((int) (100 - (electricityStored / getMaxJoules(itemStack)) * 100));
        return electricityStored;
    }
    
    /**
     * 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        // Add an uncharged version of the electric item
        par3List.add(ElectricItemHelper.getUncharged(new ItemStack(this)));
        // Add an electric item to the creative list that is fully charged
        ItemStack chargedItem = new ItemStack(this);
        par3List.add(ElectricItemHelper.getWithCharge(chargedItem, this.getMaxJoules(chargedItem)));
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
     * @return ToString
     */
    public String func_77825_f()
    {
        return this.toolMaterial.toString();
    }

    /**
     * @return ToString
     */
    public String func_77842_f()
    {
        return this.toolMaterial.toString();
    }

    /**
     * 
     */
    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving,
            EntityLiving par3EntityLiving)
    {
        par1ItemStack.damageItem(1, par3EntityLiving);
        return true;
    }

    /**
     * 
     */
    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4,
            int par5, int par6, EntityLiving par7EntityLiving)
    {
        if (Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D)
        {
            par1ItemStack.damageItem(1, par7EntityLiving);
        }

        return true;
    }

    /**
     * @return Damage
     */
    public int func_82803_g()
    {
        return this.toolMaterial.getDamageVsEntity();
    }

    /**
     * 
     */
    @Override
    public int getDamageVsEntity(Entity par1Entity)
    {
        return this.damageVsEntity;
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
    public double getMaxJoules(ItemStack itemStack) {
        return 120000000;
    }

    /**
     * 
     */
    @Override
    public double getVoltage(ItemStack itemStack) {
        return 120;
    }
}
