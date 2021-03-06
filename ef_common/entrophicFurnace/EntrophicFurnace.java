package entrophicFurnace;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.UniversalElectricity;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import entrophicFurnace.block.BlockDarkWater;
import entrophicFurnace.block.BlockEntrophicCrop;
import entrophicFurnace.block.BlockEntrophicFurnace1;
import entrophicFurnace.block.BlockEntrophicFurnace2;
import entrophicFurnace.block.BlockEntrophicFurnace3;
import entrophicFurnace.core.proxy.CommonProxy;
import entrophicFurnace.generic.EfBucketHandler;
import entrophicFurnace.generic.ItemStackValues;
import entrophicFurnace.generic.WorldProviderEntrophic;
import entrophicFurnace.item.ItemDarkWaterBucket;
import entrophicFurnace.item.ItemEntrophicOre;
import entrophicFurnace.item.ItemEntrophicOre1;
import entrophicFurnace.item.ItemEntrophicOre2;
import entrophicFurnace.item.ItemEntrophicOre3;
import entrophicFurnace.item.ItemEntrophicOre4;
import entrophicFurnace.item.ItemEntrophicOre5;
import entrophicFurnace.item.ItemEntrophicPaxel;
import entrophicFurnace.item.ItemEntrophicTeleporter;
import entrophicFurnace.item.ItemIngotCopper;
import entrophicFurnace.item.ItemIngotTin;
import entrophicFurnace.tileentity.TileEntrophicFurnace;

/**
 *
 * @author Tachyony
 *
 */
@Mod(modid = "EntrophicFurnace", name = "Entrophic Furnace", version = "1.6.4_1", useMetadata = true, certificateFingerprint="", dependencies="after:EnergyManipulator;after:BuildCraft|Energy;after:Forestry")
@NetworkMod(channels = "EntrophicFurnace", clientSideRequired = true, serverSideRequired = false, packetHandler = universalelectricity.prefab.network.PacketManager.class)
public class EntrophicFurnace
{
    /**
	 *
	 */
    @Instance("EntrophicFurnace")
    public static EntrophicFurnace instance;

    /**
	 *
	 */
    @SidedProxy(clientSide = "entrophicFurnace.generic.ClientProxy", serverSide = "entrophicFurnace.generic.CommonProxy")
    public static CommonProxy proxy;

    /**
	 *
	 */
    public static final Configuration CONFIGURATION = new Configuration(new File(Loader.instance().getConfigDir(),
            "EntrophicFurnace.cfg"));

    /**
     * Logger
     */
    private static Logger efLogger = Logger.getLogger("EntrophicFurnace");
    
    /**
	 *
	 */
    public static Block entrophicFurnace1;

    /**
	 *
	 */
    public static Block entrophicFurnace2;

    /**
	 *
	 */
    public static Block entrophicFurnace3;

    /**
	 *
	 */
    public static Item entrophicOre;

    /**
	 *
	 */
    public static Item entrophicOre1;

    /**
	 *
	 */
    public static Item entrophicOre2;

    /**
	 *
	 */
    public static Item entrophicOre3;

    /**
	 *
	 */
    public static Item entrophicOre4;

    /**
	 *
	 */
    public static Item entrophicOre5;

    /**
	 * Copper ingot
	 */
    public static Item ingotCopper;

    /**
	 * Tin ingot
	 */
    public static Item ingotTin;

    /**
     * Super paxel
     */
    public static Item entrophicPaxel;

    /**
     * Entrophic crop seed
     */
    public static Item entrophicSeed;
   
    /**
     * 
     */
    public static Item entrophicTeleporter;

    /**
     * 
     */
    public static Block blockDarkWater;
    
    /**
     * 
     */
    public static Item bucketDarkWater;
    
    /**
     * 
     */
    public static Fluid fluidDarkWater;
    
    /**
     * 
     */
    private static Fluid efFluidEntrophic;
    
    /**
     * Entrophic crop
     */
    public static Block entrophicCrop;
    
    /**
     * Hard mode
     */
    private boolean hardMode;
    
    /**
     * Material for quantum paxel
     */
    public static final EnumToolMaterial entrophicMaterial = EnumHelper.addToolMaterial("Entrophic", 3, 4000, 24.0F, 6, 22);

    /**
     * Values of stuff
     */
    public static ItemStackValues itemValues;
    
    /**
     * Pre init
     * @param event
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        efLogger.setParent(FMLLog.getLogger());
        NetworkRegistry.instance().registerGuiHandler(this, EntrophicFurnace.proxy);
        EntrophicFurnace.CONFIGURATION.load();
        this.hardMode = EntrophicFurnace.CONFIGURATION.get("Config", "HardMode", false).getBoolean(false);
        int qFurnace1 = EntrophicFurnace.CONFIGURATION.getBlock("EntrophicFurnace1", 410)
                .getInt();
        int qFurnace2 = EntrophicFurnace.CONFIGURATION.getBlock("EntrophicFurnace2", 411)
                .getInt();
        int qFurnace3 = EntrophicFurnace.CONFIGURATION.getBlock("EntrophicFurnace3", 412)
                .getInt();
        int qOre = EntrophicFurnace.CONFIGURATION.getItem("EntrophicOre", 414).getInt();
        int qOre1 = EntrophicFurnace.CONFIGURATION.getItem("EntrophicOre1", 415).getInt();
        int qOre2 = EntrophicFurnace.CONFIGURATION.getItem("EntrophicOre2", 516).getInt();
        int qOre3 = EntrophicFurnace.CONFIGURATION.getItem("EntrophicOre3", 417).getInt();
        int qOre4 = EntrophicFurnace.CONFIGURATION.getItem("EntrophicOre4", 418).getInt();
        int qOre5 = EntrophicFurnace.CONFIGURATION.getItem("EntrophicOre5", 419).getInt();
        int inCopper = EntrophicFurnace.CONFIGURATION.getItem("ingotCopper", 420).getInt();
        int inTin = EntrophicFurnace.CONFIGURATION.getItem("ingotTin", 421).getInt();
        int qPaxel = EntrophicFurnace.CONFIGURATION.getItem("EntrophicPaxel", 413).getInt();
        int seedEntrophic = EntrophicFurnace.CONFIGURATION.getItem("EntrophicSeed", 422).getInt();
        int entTeleporter = EntrophicFurnace.CONFIGURATION.getItem("EntrophicTeleporter", 423).getInt();
        int bucketDarkWaterId = EntrophicFurnace.CONFIGURATION.getItem("DarkWaterBucket", 424).getInt();
        int cropEntrophic = EntrophicFurnace.CONFIGURATION.getBlock("EntrophicCrop", 423).getInt();
        int blockDarkWaterId = EntrophicFurnace.CONFIGURATION.getItem("BlockDarkWater", 424).getInt();
        
        entrophicFurnace1 = new BlockEntrophicFurnace1(qFurnace1, UniversalElectricity.machine)
                .setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setCreativeTab(CreativeTabs.tabMaterials);
        entrophicFurnace2 = new BlockEntrophicFurnace2(qFurnace2, UniversalElectricity.machine)
                .setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setCreativeTab(CreativeTabs.tabMaterials);
        entrophicFurnace3 = new BlockEntrophicFurnace3(qFurnace3, UniversalElectricity.machine)
                .setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setCreativeTab(CreativeTabs.tabMaterials);
        entrophicPaxel = new ItemEntrophicPaxel(qPaxel, entrophicMaterial, "entrophicPaxel");
        entrophicOre = new ItemEntrophicOre(qOre);
        entrophicOre1 = new ItemEntrophicOre1(qOre1);
        entrophicOre2 = new ItemEntrophicOre2(qOre2);
        entrophicOre3 = new ItemEntrophicOre3(qOre3);
        entrophicOre4 = new ItemEntrophicOre4(qOre4);
        entrophicOre5 = new ItemEntrophicOre5(qOre5);
        ingotCopper = new ItemIngotCopper(inCopper);
        ingotTin = new ItemIngotTin(inTin);
        entrophicCrop = new BlockEntrophicCrop(cropEntrophic);
        entrophicSeed = new ItemSeeds(seedEntrophic, EntrophicFurnace.entrophicCrop.blockID, Block.tilledField.blockID).setUnlocalizedName("entrophicSeed").setMaxStackSize(64);
        entrophicTeleporter = new ItemEntrophicTeleporter(entTeleporter);
        efFluidEntrophic = new Fluid("darkWater");
        FluidRegistry.registerFluid(efFluidEntrophic);
        fluidDarkWater = FluidRegistry.getFluid("darkWater");
        blockDarkWater = new BlockDarkWater(blockDarkWaterId, fluidDarkWater, Material.water).setFlammable(true).setFlammability(5).setParticleColor(0.7F, 0.7F, 0.0F);
        blockDarkWater.setUnlocalizedName("blockDarkWater");
        fluidDarkWater.setBlockID(blockDarkWater);
        bucketDarkWater = new ItemDarkWaterBucket(bucketDarkWaterId, blockDarkWater.blockID);
        bucketDarkWater.setUnlocalizedName("bucketFuel").setContainerItem(Item.bucketEmpty);
        
        GameRegistry.registerBlock(EntrophicFurnace.entrophicFurnace1, "EntrophicFurnace1");
        GameRegistry.registerBlock(EntrophicFurnace.entrophicFurnace2, "EntrophicFurnace2");
        GameRegistry.registerBlock(EntrophicFurnace.entrophicFurnace3, "EntrophicFurnace3");
        GameRegistry.registerBlock(EntrophicFurnace.entrophicCrop, "EntrophicCrop");
        GameRegistry.registerBlock(blockDarkWater, "DarkWater");
        GameRegistry.registerTileEntity(TileEntrophicFurnace.class, "EntrophicFurnace1");
        GameRegistry.registerTileEntity(TileEntrophicFurnace.class, "EntrophicFurnace2");
        GameRegistry.registerTileEntity(TileEntrophicFurnace.class, "EntrophicFurnace3");
        
        LanguageRegistry.addName(EntrophicFurnace.entrophicFurnace1, "Entrophic Furnace 1");
        LanguageRegistry.addName(EntrophicFurnace.entrophicFurnace2, "Entrophic Furnace 2");
        LanguageRegistry.addName(EntrophicFurnace.entrophicFurnace3, "Entrophic Furnace 3");
        LanguageRegistry.addName(EntrophicFurnace.entrophicCrop, "Entrophic Crop");
        LanguageRegistry.addName(EntrophicFurnace.entrophicOre, "Entrophic Ore");
        LanguageRegistry.addName(EntrophicFurnace.entrophicOre1, "Entrophic Ore 1");
        LanguageRegistry.addName(EntrophicFurnace.entrophicOre2, "Entrophic Ore 2");
        LanguageRegistry.addName(EntrophicFurnace.entrophicOre3, "Entrophic Ore 3");
        LanguageRegistry.addName(EntrophicFurnace.entrophicOre4, "Entrophic Ore 4");
        LanguageRegistry.addName(EntrophicFurnace.entrophicOre5, "Entrophic Ore 5");
        LanguageRegistry.addName(EntrophicFurnace.ingotCopper, "Copper");
        LanguageRegistry.addName(EntrophicFurnace.ingotTin, "Tin");
        LanguageRegistry.addName(EntrophicFurnace.entrophicPaxel, "Entrophic Paxel");
        LanguageRegistry.addName(EntrophicFurnace.entrophicSeed, "Entrophic Seed");
        LanguageRegistry.addName(EntrophicFurnace.entrophicTeleporter, "Entrophic Teleporter");
        LanguageRegistry.addName(EntrophicFurnace.blockDarkWater, "Dark Water");
        LanguageRegistry.addName(EntrophicFurnace.bucketDarkWater, "Dark Water Bucket");
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("darkWater", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucketDarkWater), new ItemStack(Item.bucketEmpty));
        EfBucketHandler.INSTANCE.buckets.put(blockDarkWater, bucketDarkWater);
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * 
     * @param event
     */
    @SuppressWarnings("static-method")
    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void textureHook(TextureStitchEvent.Post event) {
        if (event.map.textureType == 0) {
            fluidDarkWater.setIcons(blockDarkWater.getBlockTextureFromSide(1), blockDarkWater.getBlockTextureFromSide(2));
        }
    }
    
    /**
     *
     * @param event
     */
    @SuppressWarnings("boxing")
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        OreDictionary.registerOre("ingotCopper", new ItemStack(ingotCopper));
        OreDictionary.registerOre("ingotTin", new ItemStack(ingotTin));
        OreDictionary.registerOre("quantumOre", new ItemStack(entrophicOre));
        OreDictionary.registerOre("quantumOre1", new ItemStack(entrophicOre1));
        OreDictionary.registerOre("quantumOre2", new ItemStack(entrophicOre2));
        OreDictionary.registerOre("quantumOre3", new ItemStack(entrophicOre3));
        OreDictionary.registerOre("quantumOre4", new ItemStack(entrophicOre4));
        OreDictionary.registerOre("quantumOre5", new ItemStack(entrophicOre5));
        OreDictionary.registerOre("EntrophicFurnace1", new ItemStack(EntrophicFurnace.entrophicFurnace1));
        OreDictionary.registerOre("EntrophicFurnace2", new ItemStack(EntrophicFurnace.entrophicFurnace2));
        OreDictionary.registerOre("EntrophicFurnace3", new ItemStack(EntrophicFurnace.entrophicFurnace3));

        // Get the values of stuff
        itemValues = new ItemStackValues(this.hardMode);
        EntrophicFurnace.CONFIGURATION.save();
        
        // Add the entrophic ore, this is used for making other stuff and getting hard to find stuff,
        // like blaze rods or feathers
        GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicOre1, new Object[] { "   ", "   ", " o ",
                'o', EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicOre2, new Object[] { "ooo", "ooo", "oo ",
                'o', EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicOre3, new Object[] { "ooo", "ooo", "oo ",
                'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicOre4, new Object[] { "ooo", "ooo", "oo ",
                'o', EntrophicFurnace.entrophicOre3 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicOre5, new Object[] { "ooo", "ooo", "oo ",
                'o', EntrophicFurnace.entrophicOre4 }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicOre, new Object[] { "   ", "   ", "o  ",
                'o', EntrophicFurnace.entrophicOre1 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EntrophicFurnace.entrophicOre, 8), new Object[] { "o  ", "   ", "   ",
                'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EntrophicFurnace.entrophicOre2, 8), new Object[] { "o  ", "   ", "   ",
                'o', EntrophicFurnace.entrophicOre3 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EntrophicFurnace.entrophicOre3, 8), new Object[] { "o  ", "   ", "   ",
                'o', EntrophicFurnace.entrophicOre4 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EntrophicFurnace.entrophicOre4, 8), new Object[] { "o  ", "   ", "   ",
                'o', EntrophicFurnace.entrophicOre5 }));
        ItemStack paxelStack = new ItemStack(EntrophicFurnace.entrophicPaxel, 1);
        paxelStack.addEnchantment(Enchantment.silkTouch, 1);
        paxelStack.addEnchantment(Enchantment.unbreaking, 3);
        paxelStack.addEnchantment(Enchantment.fortune, 3);
        paxelStack.addEnchantment(Enchantment.efficiency, 5);
        if (this.hardMode)
        {
            // Hard mode recipes, inspired by GregTech to make your life difficult.
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EntrophicFurnace.entrophicSeed, 9), new Object[] {
                    "III", "IFI", "III", 'I', Block.wood, 'F', Block.workbench }));
            
            // Add in the entrophic furnaces
            GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicFurnace1, new Object[] {
                    "TCT", "CEC", "TCT", 'T', "ingotTin", 'C', "ingotCopper", 'E', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicFurnace2, new Object[] {
                    "IOI", "OFO", "IOI", 'I', EntrophicFurnace.entrophicFurnace1, 'F', Block.blockDiamond, 'O', Block.obsidian }));
            GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicFurnace3, new Object[] {
                    "IOI", "OFO", "IOI", 'I', EntrophicFurnace.entrophicFurnace2, 'F', Item.netherStar, 'O', Item.diamond }));
        }
        else
        {
            // Older easy recipes, best for sky block or super flat worlds
            // Old easy entrophic ore recipe
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EntrophicFurnace.entrophicOre, 36), new Object[] {
                "III", "IFI", "III", 'I', Block.workbench, 'F', EntrophicFurnace.entrophicFurnace1 }));
            
            // Add in the entrophic furnaces
            GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicFurnace1, new Object[] {
            		"III", "IFI", "III", 'I', Block.wood, 'F', Block.workbench }));
            GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicFurnace2, new Object[] {
            		"I I", " F ", "I I", 'I', EntrophicFurnace.entrophicFurnace1, 'F', Block.blockGold }));
            GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicFurnace3, new Object[] {
            		"I I", " F ", "I I", 'I', EntrophicFurnace.entrophicFurnace2, 'F', Item.netherStar }));

            // Add alternate recipes for those (un)lucky enough to be using GregTech.
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.blockGold, new Object[] { "aab", "aab", "bb ", 'a',
                    EntrophicFurnace.entrophicOre4, 'b', EntrophicFurnace.entrophicOre3 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.blockDiamond, new Object[] { " o ", "   ", " o ", 'o',
                    EntrophicFurnace.entrophicOre5 }));
            
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.cobblestone, new Object[] { "o  ", "   ", "   ", 'o',
                    EntrophicFurnace.entrophicOre }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.dirt, new Object[] { " o ", "   ", "   ", 'o',
                    EntrophicFurnace.entrophicOre }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.sand, new Object[] { "   ", "   ", "o  ", 'o',
                    EntrophicFurnace.entrophicOre }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.stone, new Object[] { "   ", "o  ", "   ", 'o',
                    EntrophicFurnace.entrophicOre }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.cobblestoneMossy, new Object[] { "   ", "   ", " o ", 'o',
                    EntrophicFurnace.entrophicOre1 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.silk, new Object[] { "   ", " o ", "   ", 'o',
                    EntrophicFurnace.entrophicOre1 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.netherrack, new Object[] { "   ", "o  ", "   ", 'o',
                    EntrophicFurnace.entrophicOre1 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.slowSand, new Object[] { "   ", " o ", "   ", 'o',
                    EntrophicFurnace.entrophicOre1 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.mycelium, new Object[] { "o  ", "   ", "   ", 'o',
                    EntrophicFurnace.entrophicOre1 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.grass, new Object[] { " o ", "   ", "   ", 'o',
                    EntrophicFurnace.entrophicOre1 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.gravel, new Object[] { " o ", "o o", " o ", 'o',
                    EntrophicFurnace.entrophicOre1 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.flint, new Object[] { " o ", "o o", "  o", 'o',
                    EntrophicFurnace.entrophicOre1 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.netherBrick, new Object[] { "o  ", " o ", "o o", 'o',
                    EntrophicFurnace.entrophicOre }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.cloth, 1, 0), new Object[] { "o  ", "ooo",
                    "   ", 'o', EntrophicFurnace.entrophicOre }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.feather, 1, 3), new Object[] { " oo", "oo ",
                    "o  ", 'o', EntrophicFurnace.entrophicOre }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.egg, new Object[] { " oo", " oo",
                " oo", 'o', EntrophicFurnace.entrophicOre }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.chickenRaw, new Object[] { "o o", " oo",
                " oo", 'o', EntrophicFurnace.entrophicOre }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.chickenCooked, new Object[] { " oo", " oo",
                "o o", 'o', EntrophicFurnace.entrophicOre }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.porkRaw, new Object[] { "ooo", " oo",
                    "o  ", 'o', EntrophicFurnace.entrophicOre }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.porkCooked, new Object[] { "ooo", "oo ",
                    " o ", 'o', EntrophicFurnace.entrophicOre }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.sapling, 1, 0), new Object[] { "o o", " o ",
                    "o o", 'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.sapling, 1, 1), new Object[] { " o ", "o o",
                    "o o", 'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.sapling, 1, 2), new Object[] { "ooo", "o o",
                    "   ", 'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.sapling, 1, 3), new Object[] { "o  ", "ooo",
                    "  o", 'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.netherStalkSeeds, 1, 3), new Object[] { "   ",
                    " o ", "   ", 'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.furnaceIdle, new Object[] { " o ", "   ", "   ", 'o',
                    EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.coal, 1, 0), new Object[] { "   ", " o ", "  o",
                    'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.coal, 1, 1), new Object[] { "   ", " o ", " o ",
                    'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.wood, 1, 0), new Object[] { "o o", "   ", "   ",
                    'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.wood, 1, 1), new Object[] { "   ", "  o", " o ",
                    'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.wood, 1, 2), new Object[] { "   ", "  o", "  o",
                    'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.wood, 1, 3), new Object[] { "o  ", "   ", "  o",
                    'o', EntrophicFurnace.entrophicOre2 }));

            GameRegistry.addRecipe(new ShapedOreRecipe(Item.seeds, new Object[] {
                    "o  ", "o  ", "   ", 'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.wheat, new Object[] {
                    "o  ", "  o", "   ", 'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.reed, new Object[] {
                    "o  ", "   ", "o  ", 'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.cactus, new Object[] {
                    " o ", " o ", "   ", 'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.melon, new Object[] {
                    "oo ", "   ", "   ", 'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.pumpkin, new Object[] {
                    "   ", "oo ", "   ", 'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.carrot, new Object[] {
                    " o ", "   ", "o  ", 'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.potato, new Object[] {
                    " o ", "   ", " o ", 'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.gunpowder, new Object[] {
                    "   ", " o ", "o o", 'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.leather, new Object[] { "o  ", " o ", "   ", 'o',
                    EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.blockClay, new Object[] { "oo ", " oo", "   ", 'o',
                    EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.enderPearl, new Object[] { "oo ", "oo ", "   ", 'o',
                    EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.obsidian, new Object[] { " o ", "o o", " o ", 'o',
                    EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.waterlily, new Object[] { "   ", "oo ", "oo ", 'o',
                    EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.ingotIron, new Object[] { "ooo", "oo ", "ooo", 'o',
                    EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.redstone, new Object[] { "ooo", "ooo", "o o", 'o',
                    EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Block.glowStone, new Object[] { "   ", " oo", "   ", 'o',
                    EntrophicFurnace.entrophicOre3 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.slimeBall, new Object[] { "oo ", "   ", "   ", 'o',
                    EntrophicFurnace.entrophicOre3 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.blazeRod, new Object[] { "   ", "o o", "   ", 'o',
                    EntrophicFurnace.entrophicOre3 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.ingotGold, new Object[] { " o ", "o  ", " oo", 'o',
                    EntrophicFurnace.entrophicOre3 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.netherQuartz, new Object[] { "   ", " o ", "   ", 'o',
                    EntrophicFurnace.entrophicOre3 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.emerald, new Object[] { "   ", " o ", " o ", 'o',
                    EntrophicFurnace.entrophicOre3 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(Item.diamond, new Object[] { "   ", " o ", " o ", 'o',
                    EntrophicFurnace.entrophicOre4 }));
            
            // Generic tin and copper definition, because every forge tech mod uses these. Forge compatible!
            GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.ingotCopper, new Object[] { "ooo", "o o", "ooo",
                    'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.ingotTin, new Object[] { "oo ", "ooo", "ooo", 'o',
                    EntrophicFurnace.entrophicOre2 }));
        }

        // Add the UBer paxel, because every mod has to have a bad ass weapon/ tool
        GameRegistry.addRecipe(new ShapedOreRecipe(paxelStack, new Object[] { "  o", " x ", "x  ", 'x', EntrophicFurnace.entrophicOre4, 'o', EntrophicFurnace.entrophicOre5 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicTeleporter, new Object[] { "ooo", " o ", " o ", 'o', EntrophicFurnace.entrophicOre }));
        
        DimensionManager.registerProviderType(143, WorldProviderEntrophic.class, false);
        DimensionManager.registerDimension(143, 143);
    }

    /**
     *
     * @param event
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        //
    }
    
    /**
     * 
     * @param event
     */
    @EventHandler
    public void handleIMCMessages(IMCEvent event) {
        //
    }
    
    /**
     * 
     * @param logLevel
     * @param message
     */
    public static void log(Level logLevel, String message) {
        efLogger.log(logLevel, message);
    }
}