package entrophicFurnace.generic;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import entrophicFurnace.EntrophicFurnace;

/**
 * 
 * @author tachyony
 *
 */
public class ItemStackValues {
    /**
     * List of stuff
     */
    private Map<ItemStack, Integer> itemStacks;
    
    /**
     * Hard mode active
     */
    private boolean hardModeActive;
    
    /**
     * Build a list of stuff for resource values
     * @param hardModeActive Hard mode active
     */
    @SuppressWarnings("unused")
    public ItemStackValues(boolean hardModeActive)
    {
        this.hardModeActive = hardModeActive;
        this.itemStacks = new HashMap<ItemStack, Integer>();
        this.addStack(new ItemStack(Block.cobblestone), "cobblestone", 1);
        this.addStack(new ItemStack(Block.dirt), "dirt", 1);
        this.addStack(new ItemStack(Block.sand), "sand", 1);
        this.addStack(new ItemStack(Block.stone), "stone", 1);
        this.addStack(new ItemStack(Block.stoneBrick), "stoneBrick", 1);
        this.addStack(new ItemStack(Block.glass), "glass", 1);
        this.addStack(new ItemStack(Item.silk), "itemSilk", 64);
        this.addStack(new ItemStack(Block.netherrack), "netherrack", 1);
        this.addStack(new ItemStack(Block.slowSand), "slowSand", 1);
        this.addStack(new ItemStack(Block.mycelium), "mycelium", 1);
        this.addStack(new ItemStack(Block.grass), "grass", 1);
        this.addStack(new ItemStack(Block.cobblestoneMossy), "cobblestoneMossy", 16);  
        this.addStack(new ItemStack(Block.cloth, 1, 0), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 1), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 2), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 3), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 4), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 5), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 6), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 7), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 8), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 9), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 10), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 11), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 12), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 13), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 14), "cloth", 8);
        this.addStack(new ItemStack(Block.cloth, 1, 15), "cloth", 8);
        this.addStack(new ItemStack(Block.gravel), "blockGravel", 8);
        this.addStack(new ItemStack(Item.flint), "itemFlint", 8);
        this.addStack(new ItemStack(Block.sandStone), "sandStone", 4);
        this.addStack(new ItemStack(Block.netherBrick), "netherBrick", 4);
        this.addStack(new ItemStack(Item.feather), "feather", 64);
        this.addStack(new ItemStack(Item.egg), "egg", 64);
        this.addStack(new ItemStack(Item.chickenRaw), "chickenRaw", 64);
        this.addStack(new ItemStack(Item.chickenCooked), "chickenCooked", 64);
        this.addStack(new ItemStack(Item.porkRaw), "porkRaw", 64);
        this.addStack(new ItemStack(Item.porkCooked), "porkCooked", 64);
        this.addStack(new ItemStack(Item.netherStalkSeeds, 1, 3), "netherStalkSeeds", 64);
        this.addStack(new ItemStack(Block.furnaceIdle), "furnace", 8);
        this.addStack(new ItemStack(Item.clay), "itemClay", 8);
        this.addStack(new ItemStack(Item.brick), "itemBrick", 8);
        this.addStack(new ItemStack(Block.melon), "blockMelon", 576);
        this.addStack(new ItemStack(Block.pumpkin), "pumpkin", 16);
        this.addStack(new ItemStack(Block.cactus), "cactus", 32);
        this.addStack(new ItemStack(Item.seeds), "seeds", 8);
        this.addStack(new ItemStack(Item.wheat), "wheat", 16);
        this.addStack(new ItemStack(Item.reed), "sugarCane", 16);
        this.addStack(new ItemStack(Item.carrot), "carrot", 32);
        this.addStack(new ItemStack(Item.potato), "potato", 32);
        this.addStack(new ItemStack(Block.wood, 1, 0), "wood", 16);
        this.addStack(new ItemStack(Block.wood, 1, 1), "wood", 16);
        this.addStack(new ItemStack(Block.wood, 1, 2), "wood", 16);
        this.addStack(new ItemStack(Block.wood, 1, 3), "wood", 16);
        this.addStack(new ItemStack(Item.coal, 1, 0), "itemCoal", 128);
        this.addStack(new ItemStack(Item.coal, 1, 1), "itemCharcoal", 32);
        this.addStack(new ItemStack(Item.leather), "leather", 64);
        this.addStack(new ItemStack(Item.gunpowder), "gunpowder", 64);
        this.addStack(new ItemStack(Block.blockClay), "blockClay", 32);
        this.addStack(new ItemStack(Block.brick), "blockBrick", 32);
        this.addStack(new ItemStack(Block.obsidian), "obsidian", 64);
        this.addStack(new ItemStack(Block.waterlily), "waterlily", 1);
        this.addStack(new ItemStack(Item.enderPearl), "enderPearl", 64);
        this.addStack(new ItemStack(Block.sapling, 1, 0), "sapling", 48);
        this.addStack(new ItemStack(Block.sapling, 1, 1), "sapling", 48);
        this.addStack(new ItemStack(Block.sapling, 1, 2), "sapling", 48);
        this.addStack(new ItemStack(Block.sapling, 1, 3), "sapling", 48);
        this.addStack(new ItemStack(Item.ingotIron), "ingotIron", 256);
        this.addStack(new ItemStack(Item.redstone), "itemRedstone", 16);
        this.addStack(new ItemStack(Item.blazeRod), "blazeRod", 320);
        this.addStack(new ItemStack(Item.slimeBall), "slimeBall", 64);
        this.addStack(new ItemStack(Block.glowStone), "blockGlowStone", 512);
        this.addStack(new ItemStack(Item.ingotGold), "ingotGold", 4096);
        this.addStack(new ItemStack(Item.emerald), "itemEmerald", 16384);
        this.addStack(new ItemStack(Item.diamond), "itemDiamond", 8192);
        this.addStack(new ItemStack(Block.blockGold), 36864, true);
        this.addStack(new ItemStack(Block.blockDiamond), 73728, true);
        this.addStack(new ItemStack(Item.netherStar), 32768, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 0), 32, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 1), 32, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 2), 32, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 3), 32, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 4), 128, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 5), 32, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 6), 32, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 7), 32, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 8), 32, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 9), 32, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 10), 32, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 11), 32, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 12), 32, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 13), 32, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 14), 32, true);
        this.addStack(new ItemStack(Item.dyePowder, 1, 15), 32, true);
        this.addStack(new ItemStack(Item.bucketEmpty), 256, true);
        this.addStack(new ItemStack(Item.bucketWater), 257, true);
        this.addStack(new ItemStack(Item.bucketLava), 272, true);
        
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre), 1, true);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre1), 1, true);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre2), 8, true);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre3), 64, true);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre4), 512, true);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre5), 4096, true);
        this.addStack(new ItemStack(EntrophicFurnace.darkWaterBucket), 1536, true);
        this.addStack(new ItemStack(EntrophicFurnace.ingotCopper), 256, true);
        this.addStack(new ItemStack(EntrophicFurnace.ingotTin), 256, true);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicTeleporter), 5, true);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicPaxel), 5120, true);
        
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 0), 48, false);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 1), 48, false);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 2), 48, false);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 3), 48, false);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 0), 2496, false);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 1), 2496, false);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 2), 2496, false);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 3), 2496, false);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 0), 18176, false);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 1), 18176, false);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 2), 18176, false);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 3), 18176, false);
        
        /*for (Object recipeObject : CraftingManager.getInstance().getRecipeList()) {
            if (recipeObject instanceof IRecipe) {
                IRecipe recipe = (IRecipe)recipeObject;
                ItemStack recipeOutput = recipe.getRecipeOutput();
                if (recipeOutput != null) {
                    if (recipe instanceof ShapedRecipes) {
                        ShapedRecipes shapedRecipe = (ShapedRecipes)recipe;
                    }
                    else if (recipe instanceof ShapelessRecipes) {
                        ShapelessRecipes shapelessRecipe = (ShapelessRecipes)recipe;
                    }
                    else if (recipe instanceof ShapedOreRecipe) {
                        ShapedOreRecipe shapedOreRecipe = (ShapedOreRecipe)recipe;
                    }
                    else if (recipe instanceof ShapelessOreRecipe) {
                        ShapelessOreRecipe shapelessOreRecipe = (ShapelessOreRecipe) recipe;
                    }
                    else {
                        //
                    }
                }
            }
            else {
                //
            }
        }*/
    }

    /**
     * Add something to the value stack
     * @param stack Item
     * @param valueName Config value name
     * @param addHardMode Add if hard mode is on
     */
    private void addStack(ItemStack stack, int defaultValue, boolean addHardMode) {
        if (!this.hardModeActive || (this.hardModeActive && addHardMode)) {
            int itemValue = EntrophicFurnace.CONFIGURATION.get("itemvalues", stack.getItemName(), defaultValue).getInt();
            this.itemStacks.put(stack, Integer.valueOf(itemValue));
            /*if (Loader.isModLoaded("EnergyManipulator"))
            {
                int emValue = EnergyManipulator.getEnergyValueByItemStack(stack);
                if (itemValue != emValue)
                {
                    EntrophicFurnace.log(Level.INFO, "EF value: " + stack.getItemName() + ":" + stack.getItemDamage() + ": " + itemValue + ":" + emValue);
                }
            }*/
        }
    }
    
    /**
     * Add something to the value stack
     * @param stack Item
     * @param valueName Config value name
     */
    private void addStack(ItemStack stack, String valueName, int defaultValue) {
        this.addStack(stack, defaultValue, true);
    }
    
    /**
     * Get an items value
     * @param sourceStack Source item
     * @return Item value
     */
    public int getItemValue(ItemStack sourceStack) {
        int itemValue = 0;
        for (Map.Entry<ItemStack, Integer> stackEntry : this.itemStacks.entrySet()) {
            ItemStack compareStack = stackEntry.getKey();
            if (sourceStack.isItemEqual(compareStack)) {
                if (sourceStack.hasTagCompound() || compareStack.hasTagCompound()) {
                    if (!(sourceStack.hasTagCompound() && compareStack.hasTagCompound())) {
                        break;
                    }
                    
                    itemValue = stackEntry.getValue().intValue();
                    break;
                }
                
                itemValue = stackEntry.getValue().intValue();
                break;
            }
        }
        
        return itemValue;
    }
}
