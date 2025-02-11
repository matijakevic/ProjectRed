package mrtjp.projectred.core

import codechicken.lib.colour.EnumColour
import codechicken.lib.datagen.recipe.{FurnaceRecipeBuilder, ShapedRecipeBuilder, ShapelessRecipeBuilder, RecipeProvider}
import codechicken.lib.datagen.ItemModelProvider
import codechicken.lib.gui.SimpleItemGroup
import codechicken.lib.util.CrashLock
import codechicken.microblock.{EdgeMicroFactory, FaceMicroFactory, ItemMicroBlock, MicroMaterialRegistry, MicroRecipe}
import codechicken.microblock.handler.MicroblockModContent
import mrtjp.projectred.ProjectRedCore.MOD_ID
import mrtjp.projectred.core.CoreContent._
import net.minecraft.data._
import net.minecraft.item.crafting.Ingredient
import net.minecraft.item.{Item, ItemStack, Items}
import net.minecraft.tags.ItemTags.{Wrapper => ItemTag}
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.generators.ExistingFileHelper
import net.minecraftforge.common.Tags
import net.minecraftforge.common.Tags.Items._
import net.minecraftforge.common.crafting.{NBTIngredient => ForgeNBTIngredient}
import net.minecraftforge.eventbus.api.{IEventBus, SubscribeEvent}
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent
import net.minecraftforge.registries.{DeferredRegister, ForgeRegistries}

import java.util.function.{Consumer, Supplier}

object CoreContent {

    private val LOCK = new CrashLock("Already Initialized.")
    private val ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID)
    private val RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID)

    val itemGroupCore = new SimpleItemGroup(MOD_ID, () => new ItemStack(itemScrewdriver.get()))

    //region Items
    /** Gate Parts * */
    val itemPlate = ITEMS.register("plate", simpleItem)
    val itemConductivePlate = ITEMS.register("conductive_plate", simpleItem)

    val itemWiredPlate = ITEMS.register("wired_plate", simpleItem)
    val itemBundledPlate = ITEMS.register("bundled_plate", simpleItem)
    val itemPlatformedPlate = ITEMS.register("platformed_plate", simpleItem)

    val itemAnode = ITEMS.register("anode", simpleItem)
    val itemCathode = ITEMS.register("cathode", simpleItem)
    val itemPointer = ITEMS.register("pointer", simpleItem)
    val itemSiliconChip = ITEMS.register("silicon_chip", simpleItem)
    val itemEnergizedSiliconChip = ITEMS.register("energized_silicon_chip", simpleItem)

    /** Ingots/Dust * */
    val itemCopperIngot = ITEMS.register("copper_ingot", simpleItem)
    val itemTinIngot = ITEMS.register("tin_ingot", simpleItem)
    val itemSilverIngot = ITEMS.register("silver_ingot", simpleItem)
    val itemRedIngot = ITEMS.register("red_ingot", simpleItem)
    val itemElectrotineIngot = ITEMS.register("electrotine_ingot", simpleItem)
    val itemElectrotineDust = ITEMS.register("electrotine_dust", simpleItem)


    /** Gems * */
    val itemRuby = ITEMS.register("ruby", simpleItem)
    val itemSapphire = ITEMS.register("sapphire", simpleItem)
    val itemPeridot = ITEMS.register("peridot", simpleItem)

    /** Go-betweens, To be removed w/ Alloy Smeltery * */
    val itemSandCoalCompound = ITEMS.register("sand_coal_comp", simpleItem)
    val itemRedIronCompound = ITEMS.register("red_iron_comp", simpleItem)
    val itemElectrotineIronCompound = ITEMS.register("electrotine_iron_comp", simpleItem)


    /** Silicons * */
    val itemSiliconBoule = ITEMS.register("boule", simpleItem)
    val itemSilicon = ITEMS.register("silicon", simpleItem)

    val itemRedSiliconCompound = ITEMS.register("red_silicon_comp", simpleItem)
    val itemGlowingSiliconCompound = ITEMS.register("glow_silicon_comp", simpleItem)
    val itemElectrotineSiliconCompound = ITEMS.register("electrotine_silicon_comp", simpleItem)
    val itemInfusedSilicon = ITEMS.register("infused_silicon", simpleItem)
    val itemEnergizedSilicon = ITEMS.register("energized_silicon", simpleItem)
    val itemElectrotineSilicon = ITEMS.register("electrotine_silicon", simpleItem)


    /** Mechanical Pieces * */
    val itemCopperCoil = ITEMS.register("copper_coil", simpleItem)
    val itemIronCoil = ITEMS.register("iron_coil", simpleItem)
    val itemGoldCoil = ITEMS.register("gold_coil", simpleItem)

    val itemMotor = ITEMS.register("motor", simpleItem)

    val itemWovenCloth = ITEMS.register("woven_cloth", simpleItem)
    val itemSail = ITEMS.register("sail", simpleItem)

    /** Illumars * */
    val itemWhiteIllumar = ITEMS.register("white_illumar", simpleItem)
    val itemOrangeIllumar = ITEMS.register("orange_illumar", simpleItem)
    val itemMagentaIllumar = ITEMS.register("magenta_illumar", simpleItem)
    val itemLightBlueIllumar = ITEMS.register("light_blue_illumar", simpleItem)
    val itemYellowIllumar = ITEMS.register("yellow_illumar", simpleItem)
    val itemLimeIllumar = ITEMS.register("lime_illumar", simpleItem)
    val itemPinkIllumar = ITEMS.register("pink_illumar", simpleItem)
    val itemGrayIllumar = ITEMS.register("gray_illumar", simpleItem)
    val itemLightGrayIllumar = ITEMS.register("light_gray_illumar", simpleItem)
    val itemCyanIllumar = ITEMS.register("cyan_illumar", simpleItem)
    val itemPurpleIllumar = ITEMS.register("purple_illumar", simpleItem)
    val itemBlueIllumar = ITEMS.register("blue_illumar", simpleItem)
    val itemBrownIllumar = ITEMS.register("brown_illumar", simpleItem)
    val itemGreenIllumar = ITEMS.register("green_illumar", simpleItem)
    val itemRedIllumar = ITEMS.register("red_illumar", simpleItem)
    val itemBlackIllumar = ITEMS.register("black_illumar", simpleItem)

    lazy val illumars = List(
        itemWhiteIllumar,
        itemOrangeIllumar,
        itemMagentaIllumar,
        itemLightBlueIllumar,
        itemYellowIllumar,
        itemLimeIllumar,
        itemPinkIllumar,
        itemGrayIllumar,
        itemLightGrayIllumar,
        itemCyanIllumar,
        itemPurpleIllumar,
        itemBlueIllumar,
        itemBrownIllumar,
        itemGreenIllumar,
        itemRedIllumar,
        itemBlackIllumar
    ).map(_.get())

    val nullRoutingChip = ITEMS.register("null_routing_chip", simpleItem)

    val itemDrawPlate = ITEMS.register("draw_plate", () => new ItemDrawPlate)
    val itemScrewdriver = ITEMS.register("screwdriver", () => new ItemScrewdriver)
    val itemMultimeter = ITEMS.register("multimeter", () => new ItemMultimeter)
    //endregion

    val shapelessNBTCopyRecipeSerializer = RECIPE_SERIALIZERS.register("backpack_nbt_copy", () => new ShapelessNBTCopyRecipeSerializer())


    //region Tags
    val tagIngotsCopper = new ItemTag("forge:ingots/copper")
    val tagIngotsTin = new ItemTag("forge:ingots/tin")
    val tagIngotsSilver = new ItemTag("forge:ingots/silver")

    val tagIngotsRedAlloy = new ItemTag("forge:ingots/red_alloy")
    val tagIngotsElectrotineAlloy = new ItemTag("forge:ingots/electrotine_alloy")

    val tagGemsRuby = new ItemTag("forge:gems/ruby")
    val tagGemsSapphire = new ItemTag("forge:gems/sapphire")
    val tagGemsPeridot = new ItemTag("forge:gems/peridot")

    val tagDustsElectrotine = new ItemTag("forge:dusts/electrotine")

    val tagIllumars = new ItemTag(new ResourceLocation(MOD_ID, "illumars"))
    val tagIllumarsWhite = new ItemTag(new ResourceLocation(MOD_ID, "illumars/white"))
    val tagIllumarsOrange = new ItemTag(new ResourceLocation(MOD_ID, "illumars/orange"))
    val tagIllumarsMagenta = new ItemTag(new ResourceLocation(MOD_ID, "illumars/magenta"))
    val tagIllumarsLightBlue = new ItemTag(new ResourceLocation(MOD_ID, "illumars/light_blue"))
    val tagIllumarsYellow = new ItemTag(new ResourceLocation(MOD_ID, "illumars/yellow"))
    val tagIllumarsLime = new ItemTag(new ResourceLocation(MOD_ID, "illumars/lime"))
    val tagIllumarsPink = new ItemTag(new ResourceLocation(MOD_ID, "illumars/pink"))
    val tagIllumarsGray = new ItemTag(new ResourceLocation(MOD_ID, "illumars/gray"))
    val tagIllumarsLightGray = new ItemTag(new ResourceLocation(MOD_ID, "illumars/light_gray"))
    val tagIllumarsCyan = new ItemTag(new ResourceLocation(MOD_ID, "illumars/cyan"))
    val tagIllumarsPurple = new ItemTag(new ResourceLocation(MOD_ID, "illumars/purple"))
    val tagIllumarsBlue = new ItemTag(new ResourceLocation(MOD_ID, "illumars/blue"))
    val tagIllumarsBrown = new ItemTag(new ResourceLocation(MOD_ID, "illumars/brown"))
    val tagIllumarsGreen = new ItemTag(new ResourceLocation(MOD_ID, "illumars/green"))
    val tagIllumarsRed = new ItemTag(new ResourceLocation(MOD_ID, "illumars/red"))
    val tagIllumarsBlack = new ItemTag(new ResourceLocation(MOD_ID, "illumars/black"))
    //endregion

    private def simpleItem: Supplier[Item] = () => new Item(new Item.Properties().group(itemGroupCore))

    def register(bus: IEventBus) {
        LOCK.lock()
        ITEMS.register(bus)
        RECIPE_SERIALIZERS.register(bus)
        bus.register(DataGen)
    }

    implicit def toRL(str: String): ResourceLocation = new ResourceLocation(str)
    implicit def rlToString(rl: ResourceLocation): String = rl.toString

    implicit def unwrap[T](sup: Supplier[T]): T = sup.get()
}

private object DataGen {

    @SubscribeEvent
    def gatherDataGenerators(event: GatherDataEvent) {
        val gen = event.getGenerator
        val helper = event.getExistingFileHelper
        if (event.includeClient) {
            gen.addProvider(new ItemModels(gen, helper))
        }
        if (event.includeServer) {
            gen.addProvider(new ItemTags(gen))
            gen.addProvider(new Recipes(gen))
        }
    }
}


private class ItemModels(gen: DataGenerator, helper: ExistingFileHelper) extends ItemModelProvider(gen, MOD_ID, helper) {
    override protected def registerModels() {
        generated(itemPlate)
        generated(itemConductivePlate)
        generated(itemWiredPlate)
        generated(itemBundledPlate)
        generated(itemPlatformedPlate)

        generated(itemAnode)
        generated(itemCathode)
        generated(itemPointer)
        generated(itemSiliconChip)
        generated(itemEnergizedSiliconChip)

        generated(itemCopperIngot)
        generated(itemTinIngot)
        generated(itemSilverIngot)
        generated(itemRedIngot)
        generated(itemElectrotineIngot)
        generated(itemElectrotineDust)

        generated(itemRuby)
        generated(itemSapphire)
        generated(itemPeridot)

        generated(itemSandCoalCompound)
        generated(itemRedIronCompound)
        generated(itemElectrotineIronCompound)

        generated(itemSiliconBoule)
        generated(itemSilicon)

        generated(itemRedSiliconCompound)
        generated(itemGlowingSiliconCompound)
        generated(itemElectrotineSiliconCompound)
        generated(itemInfusedSilicon)
        generated(itemEnergizedSilicon)
        generated(itemElectrotineSilicon)

        generated(itemCopperCoil)
        generated(itemIronCoil)
        generated(itemGoldCoil)

        generated(itemMotor)

        generated(itemWovenCloth)
        generated(itemSail)

        generated(itemWhiteIllumar).folder("illumar")
        generated(itemOrangeIllumar).folder("illumar")
        generated(itemMagentaIllumar).folder("illumar")
        generated(itemLightBlueIllumar).folder("illumar")
        generated(itemYellowIllumar).folder("illumar")
        generated(itemLimeIllumar).folder("illumar")
        generated(itemPinkIllumar).folder("illumar")
        generated(itemGrayIllumar).folder("illumar")
        generated(itemLightGrayIllumar).folder("illumar")
        generated(itemCyanIllumar).folder("illumar")
        generated(itemPurpleIllumar).folder("illumar")
        generated(itemBlueIllumar).folder("illumar")
        generated(itemBrownIllumar).folder("illumar")
        generated(itemGreenIllumar).folder("illumar")
        generated(itemRedIllumar).folder("illumar")
        generated(itemBlackIllumar).folder("illumar")

        generated(nullRoutingChip)

        handheld(itemDrawPlate)
        handheld(itemScrewdriver)
        handheld(itemMultimeter)
    }

    override def getName = "ProjectRed-Core Item Models"
}

private class ItemTags(gen: DataGenerator) extends ItemTagsProvider(gen) {

    override protected def registerTags() {
        getBuilder(Tags.Items.INGOTS)
            .add(tagIngotsCopper)
            .add(tagIngotsTin)
            .add(tagIngotsSilver)
            .add(tagIngotsRedAlloy)
            .add(tagIngotsElectrotineAlloy)
        getBuilder(Tags.Items.GEMS)
            .add(tagGemsRuby)
            .add(tagGemsSapphire)
            .add(tagGemsPeridot)
        getBuilder(Tags.Items.DUSTS)
            .add(tagDustsElectrotine)

        getBuilder(tagIngotsCopper).add(itemCopperIngot)
        getBuilder(tagIngotsTin).add(itemTinIngot)
        getBuilder(tagIngotsSilver).add(itemSilverIngot)

        getBuilder(tagIngotsRedAlloy).add(itemRedIngot)
        getBuilder(tagIngotsElectrotineAlloy).add(itemElectrotineIngot)

        getBuilder(tagGemsRuby).add(itemRuby)
        getBuilder(tagGemsSapphire).add(itemSapphire)
        getBuilder(tagGemsPeridot).add(itemPeridot)

        getBuilder(tagDustsElectrotine).add(itemElectrotineDust)

        getBuilder(tagIllumars)
            .add(tagIllumarsWhite)
            .add(tagIllumarsOrange)
            .add(tagIllumarsMagenta)
            .add(tagIllumarsLightBlue)
            .add(tagIllumarsYellow)
            .add(tagIllumarsLime)
            .add(tagIllumarsPink)
            .add(tagIllumarsGray)
            .add(tagIllumarsLightGray)
            .add(tagIllumarsCyan)
            .add(tagIllumarsPurple)
            .add(tagIllumarsBlue)
            .add(tagIllumarsBrown)
            .add(tagIllumarsGreen)
            .add(tagIllumarsRed)
            .add(tagIllumarsBlack)
        getBuilder(tagIllumarsWhite).add(itemWhiteIllumar)
        getBuilder(tagIllumarsOrange).add(itemOrangeIllumar)
        getBuilder(tagIllumarsMagenta).add(itemMagentaIllumar)
        getBuilder(tagIllumarsLightBlue).add(itemLightBlueIllumar)
        getBuilder(tagIllumarsYellow).add(itemYellowIllumar)
        getBuilder(tagIllumarsLime).add(itemLimeIllumar)
        getBuilder(tagIllumarsPink).add(itemPinkIllumar)
        getBuilder(tagIllumarsGray).add(itemGrayIllumar)
        getBuilder(tagIllumarsLightGray).add(itemLightGrayIllumar)
        getBuilder(tagIllumarsCyan).add(itemCyanIllumar)
        getBuilder(tagIllumarsPurple).add(itemPurpleIllumar)
        getBuilder(tagIllumarsBlue).add(itemBlueIllumar)
        getBuilder(tagIllumarsBrown).add(itemBrownIllumar)
        getBuilder(tagIllumarsGreen).add(itemGreenIllumar)
        getBuilder(tagIllumarsRed).add(itemRedIllumar)
        getBuilder(tagIllumarsBlack).add(itemBlackIllumar)
    }

    override def getName = "ProjectRed-Core Item Tags"
}

private class Recipes(gen: DataGenerator) extends RecipeProvider(gen) {

    override protected def registerRecipes() {
        smelting(itemPlate, 2)
            .ingredient(STONE)

        smelting(itemSiliconBoule)
            .ingredient(itemSandCoalCompound)

        smelting(itemInfusedSilicon)
            .ingredient(itemRedSiliconCompound)

        smelting(itemEnergizedSilicon)
            .ingredient(itemGlowingSiliconCompound)

        smelting(itemRedIngot)
            .ingredient(itemRedIronCompound)

        smelting(itemElectrotineIngot)
            .ingredient(itemElectrotineIronCompound)

        smelting(itemElectrotineSilicon)
            .ingredient(itemElectrotineSiliconCompound)

        shapedRecipe(itemConductivePlate)
            .key('R', DUSTS_REDSTONE)
            .key('P', itemPlate)
            .patternLine("R")
            .patternLine("P")

        shapedRecipe(itemPlatformedPlate)
            .key('R', itemConductivePlate)
            .key('S', RODS_WOODEN)
            .key('P', itemPlate)
            .patternLine(" R ")
            .patternLine("SPS")
            .patternLine("PRP")

        shapedRecipe(itemAnode, 3)
            .key('R', DUSTS_REDSTONE)
            .key('P', itemPlate)
            .patternLine(" R ")
            .patternLine("RRR")
            .patternLine("PPP")

        shapedRecipe(itemCathode)
            .key('T', Items.REDSTONE_TORCH)
            .key('P', itemPlate)
            .patternLine("T")
            .patternLine("P")

        shapedRecipe(itemPointer)
            .key('S', STONE)
            .key('T', Items.REDSTONE_TORCH)
            .key('P', itemPlate)
            .patternLine("S")
            .patternLine("T")
            .patternLine("P")

        shapedRecipe(itemSiliconChip)
            .key('S', itemInfusedSilicon)
            .key('P', itemPlate)
            .patternLine(" S ")
            .patternLine("PPP")

        shapedRecipe(itemEnergizedSiliconChip)
            .key('E', itemEnergizedSilicon)
            .key('P', itemPlate)
            .patternLine(" E ")
            .patternLine("PPP")

        shapedRecipe(itemSandCoalCompound)
            .key('S', SAND)
            .key('C', STORAGE_BLOCKS_COAL)
            .patternLine("SSS")
            .patternLine("SCS")
            .patternLine("SSS")

        shapedRecipe(itemRedIronCompound)
            .key('R', DUSTS_REDSTONE)
            .key('I', INGOTS_IRON)
            .patternLine("RRR")
            .patternLine("RIR")
            .patternLine("RRR")

        shapedRecipe(itemElectrotineIronCompound)
            .key('B', tagDustsElectrotine)
            .key('I', INGOTS_IRON)
            .patternLine("BBB")
            .patternLine("BIB")
            .patternLine("BBB")

        shapedRecipe(itemSilicon, 8)
            .key('S', MicroblockModContent.itemDiamondSaw)
            .key('B', itemSiliconBoule)
            .patternLine("S")
            .patternLine("B")

        shapedRecipe(itemRedSiliconCompound)
            .key('R', DUSTS_REDSTONE)
            .key('S', itemSilicon)
            .patternLine("RRR")
            .patternLine("RSR")
            .patternLine("RRR")

        shapedRecipe(itemGlowingSiliconCompound)
            .key('G', DUSTS_GLOWSTONE)
            .key('S', itemSilicon)
            .patternLine("GGG")
            .patternLine("GSG")
            .patternLine("GGG")

        shapedRecipe(itemElectrotineSiliconCompound)
            .key('E', tagDustsElectrotine)
            .key('S', itemSilicon)
            .patternLine("EEE")
            .patternLine("ESE")
            .patternLine("EEE")

        shapedRecipe(itemCopperCoil)
            .key('C', tagIngotsCopper)
            .key('D', itemDrawPlate)
            .patternLine("CD")

        shapedRecipe(itemIronCoil)
            .key('I', INGOTS_IRON)
            .key('D', itemDrawPlate)
            .patternLine("ID")

        shapedRecipe(itemGoldCoil)
            .key('G', INGOTS_GOLD)
            .key('D', itemDrawPlate)
            .patternLine("GD")

        shapedRecipe(itemMotor)
            .key('I', INGOTS_IRON)
            .key('S', STONE)
            .key('C', itemCopperCoil)
            .key('R', DUSTS_REDSTONE)
            .patternLine(" I ")
            .patternLine("SCS")
            .patternLine("RCR")

        shapedRecipe(itemWovenCloth)
            .key('S', STRING)
            .key('W', RODS_WOODEN)
            .patternLine("SSS")
            .patternLine("SWS")
            .patternLine("SSS")

        shapedRecipe(itemSail)
            .key('S', itemWovenCloth)
            .patternLine("SS")
            .patternLine("SS")
            .patternLine("SS")

        EnumColour.values().foreach(addLumarRecipe)

        shapedRecipe(itemDrawPlate)
            .key('I', new NBTIngredient(ItemMicroBlock.create(EdgeMicroFactory.getFactoryID, 2, MicroMaterialRegistry.findMaterial(new ItemStack(Items.IRON_BLOCK)))))
            .key('D', new NBTIngredient(ItemMicroBlock.create(FaceMicroFactory.getFactoryID, 2, MicroMaterialRegistry.findMaterial(new ItemStack(Items.DIAMOND_BLOCK)))))
            .patternLine(" I ")
            .patternLine("IDI")
            .patternLine(" I ")

        shapedRecipe(itemScrewdriver)
            .key('I', INGOTS_IRON)
            .key('B', DYES_BLUE)
            .patternLine("I  ")
            .patternLine(" IB")
            .patternLine(" BI")

        shapedRecipe(itemMultimeter)
            .key('A', tagIngotsRedAlloy)
            .key('B', DYES_BLACK)
            .key('E', DYES_GREEN)
            .key('R', DYES_RED)
            .key('G', DUSTS_GLOWSTONE)
            .patternLine("A A")
            .patternLine("BER")
            .patternLine("BGR")
    }


    private def addLumarRecipe(colour: EnumColour) {
        shapelessRecipe(illumars(colour.ordinal))
            .addIngredient(DUSTS_GLOWSTONE, 2)
            .addIngredient(new ItemTag(colour.getDyeTagName), 2)
    }


    override def getName = "ProjectRed-Core Recipes"
}

private class NBTIngredient(stack: ItemStack) extends ForgeNBTIngredient(stack)
