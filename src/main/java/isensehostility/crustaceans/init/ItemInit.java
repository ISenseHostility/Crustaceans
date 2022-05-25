package isensehostility.crustaceans.init;

import isensehostility.crustaceans.Crustaceans;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Crustaceans.Utils.MODID);

    public static void initialize() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Item> CLAW = ITEMS.register("claw", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BREWING).stacksTo(16)));

    public static final RegistryObject<Item> RAW_CRAB = ITEMS.register("crab", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(3).meat().build())));
    public static final RegistryObject<Item> COOKED_CRAB = ITEMS.register("cooked_crab", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(6).meat().build())));
    public static final RegistryObject<Item> RAW_LOBSTER = ITEMS.register("lobster", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(3).meat().build())));
    public static final RegistryObject<Item> COOKED_LOBSTER = ITEMS.register("cooked_lobster", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(6).meat().build())));

    public static final RegistryObject<Item> CRAB_BUCKET = ITEMS.register("crab_bucket", () -> new MobBucketItem(EntityInit.CRAB, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
    public static final RegistryObject<Item> LOBSTER_BUCKET = ITEMS.register("lobster_bucket", () -> new MobBucketItem(EntityInit.LOBSTER, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));

}
