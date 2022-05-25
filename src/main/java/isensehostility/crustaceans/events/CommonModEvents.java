package isensehostility.crustaceans.events;

import isensehostility.crustaceans.Crustaceans;
import isensehostility.crustaceans.entities.CrabEntity;
import isensehostility.crustaceans.entities.LobsterEntity;
import isensehostility.crustaceans.ingredients.PotionIngredient;
import isensehostility.crustaceans.init.EntityInit;
import isensehostility.crustaceans.init.ItemInit;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Crustaceans.Utils.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void onSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(EntityInit.CRAB.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, CrabEntity::canSpawnOnGround);
            SpawnPlacements.register(EntityInit.LOBSTER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, LobsterEntity::canSpawnUnderWater);
            BrewingRecipeRegistry.addRecipe(new PotionIngredient(Potions.AWKWARD), Ingredient.of(new ItemStack(ItemInit.CLAW.get())), PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER_BREATHING));
        });
    }

    @SubscribeEvent
    public static void onAttributeCreation(final EntityAttributeCreationEvent event) {
        event.put(EntityInit.CRAB.get(), CrabEntity.initializeAttributes().build());
        event.put(EntityInit.LOBSTER.get(), LobsterEntity.initializeAttributes().build());
    }


}
