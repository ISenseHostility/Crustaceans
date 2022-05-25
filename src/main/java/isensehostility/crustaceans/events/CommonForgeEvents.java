package isensehostility.crustaceans.events;

import isensehostility.crustaceans.Crustaceans;
import isensehostility.crustaceans.config.CrustaceansConfig;
import isensehostility.crustaceans.entities.CrabEntity;
import isensehostility.crustaceans.init.EntityInit;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Crustaceans.Utils.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {

    @SubscribeEvent
    public static void onCrabCollision(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof CrabEntity crab && crab.level.getDifficulty() == Difficulty.PEACEFUL && CrustaceansConfig.disableCrabDamageInPeaceful.get()) {
            for (LivingEntity entity : crab.level.getEntitiesOfClass(LivingEntity.class, crab.getBoundingBox())) {
                if (!(entity instanceof CrabEntity)) {
                    entity.hurt(DamageSource.GENERIC, (float) crab.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).getValue());
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoading(final BiomeLoadingEvent event) {
        switch (event.getCategory()) {
            case BEACH:
                if (CrustaceansConfig.spawnCrabs.get()) {
                    event.getSpawns().addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityInit.CRAB.get(), CrustaceansConfig.weightCrab.get(), CrustaceansConfig.minSpawnSizeCrab.get(), CrustaceansConfig.maxSpawnSizeCrab.get()));
                }
            case OCEAN:
                if (CrustaceansConfig.spawnLobsters.get()) {
                    event.getSpawns().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityInit.LOBSTER.get(), CrustaceansConfig.weightLobster.get(), CrustaceansConfig.minSpawnSizeLobster.get(), CrustaceansConfig.maxSpawnSizeLobster.get()));
                }
        }
    }
}
