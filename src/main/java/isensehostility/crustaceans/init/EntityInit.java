package isensehostility.crustaceans.init;

import isensehostility.crustaceans.Crustaceans;
import isensehostility.crustaceans.entities.CrabEntity;
import isensehostility.crustaceans.entities.LobsterEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Crustaceans.Utils.MODID);

    public static void initialize() {
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<EntityType<CrabEntity>> CRAB = ENTITIES.register("crab",
            () -> EntityType.Builder.of(CrabEntity::new, MobCategory.CREATURE)
                    .sized(0.8F, 0.4F)
                    .build(Crustaceans.Utils.location("crab").toString()));
    public static final RegistryObject<EntityType<LobsterEntity>> LOBSTER = ENTITIES.register("lobster",
            () -> EntityType.Builder.of(LobsterEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.8F, 0.3F)
                    .build(Crustaceans.Utils.location("lobster").toString()));
}
