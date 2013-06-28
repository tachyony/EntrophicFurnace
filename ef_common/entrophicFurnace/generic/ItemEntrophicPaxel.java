package entrophicFurnace.generic;

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
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author tachyony
 */
public class ItemEntrophicPaxel extends ItemTool
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

    private int weaponDamage;

    protected ItemEntrophicPaxel(int ID, EnumToolMaterial material, int tex, String name)
    {
        super(ID, 3, material, blocksEffectiveAgainst);
        this.weaponDamage = 4 + this.toolMaterial.getDamageVsEntity();
        this.setUnlocalizedName(name);
        this.maxStackSize = 1;
        this.setMaxDamage(material.getMaxUses());
        this.setCreativeTab(CreativeTabs.tabTools);
    }

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

    // How well it mines a block
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

    // What happens when you hit an entity
    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving,
            EntityLiving par3EntityLiving)
    {
        par1ItemStack.damageItem(1, par3EntityLiving);
        return true;
    }

    // What happens when you break a block
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

    // How much damage the sword deals
    @Override
    public int getDamageVsEntity(Entity par1Entity)
    {
        return this.weaponDamage;
    }

    @Override
    @SideOnly(Side.CLIENT)
    // Makes it have a slightly different render
    public boolean isFull3D()
    {
        return true;
    }

    // How well it enchants
    @Override
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

    // Allows you to block on right click
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }

    // The max use time of the action
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

    // Allows for repair in an anvil
    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return EntrophicFurnace.entrophicOre4.itemID == par2ItemStack.itemID ? true : super.getIsRepairable(
                par1ItemStack, par2ItemStack);
    }

    // How fast it mines a block (Compatibility edition)
    @Override
    public float getStrVsBlock(ItemStack stack, Block block, int meta)
    {
        if (ForgeHooks.isToolEffective(stack, block, meta))
        {
            return this.efficiencyOnProperMaterial;
        }

        return getStrVsBlock(stack, block);
    }

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

    // Called on right clicking anywhere
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }
}
