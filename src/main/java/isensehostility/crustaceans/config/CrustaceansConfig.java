package isensehostility.crustaceans.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.io.File;

public class CrustaceansConfig {
    public static ForgeConfigSpec.BooleanValue spawnCrabs;
    public static ForgeConfigSpec.BooleanValue spawnLobsters;
    public static ForgeConfigSpec.IntValue minSpawnSizeCrab;
    public static ForgeConfigSpec.IntValue minSpawnSizeLobster;
    public static ForgeConfigSpec.IntValue maxSpawnSizeCrab;
    public static ForgeConfigSpec.IntValue maxSpawnSizeLobster;
    public static ForgeConfigSpec.IntValue weightCrab;
    public static ForgeConfigSpec.IntValue weightLobster;
    public static ForgeConfigSpec.DoubleValue damageCrab;
    public static ForgeConfigSpec.BooleanValue disableCrabDamageInPeaceful;

    private static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec config;

    static {
        initialize(builder);
        config = builder.build();
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }

    public static void initialize(ForgeConfigSpec.Builder config) {
        spawnCrabs = config
                .comment("Spawn crabs in the world.")
                .define("allowSpawn.crab", true);
        spawnLobsters = config
                .comment("Spawn lobsters in the world.")
                .define("allowSpawn.lobster", true);
        minSpawnSizeCrab = config
                .comment("Minimum size for a group of crabs.")
                .defineInRange("spawnSize.crab.min", 2, 1, Integer.MAX_VALUE);
        minSpawnSizeLobster = config
                .comment("Minimum size for a group of lobsters.")
                .defineInRange("spawnSize.lobster.min", 2, 1, Integer.MAX_VALUE);
        maxSpawnSizeCrab = config
                .comment("""
                Maximum size for a group of crabs.
                Set this higher than the minimum.
                """)
                .defineInRange("spawnSize.crab.max", 4, 1, Integer.MAX_VALUE);
        maxSpawnSizeLobster = config
                .comment("""
                Maximum size for a group of lobsters.
                Set this higher than the minimum.
                """)
                .defineInRange("spawnSize.lobster.max", 4, 1, Integer.MAX_VALUE);
        weightCrab = config
                .comment("Controls how often crabs spawn.")
                .defineInRange("weights.crab", 12, 1, Integer.MAX_VALUE);
        weightLobster = config
                .comment("Maximum size for a group of lobsters.")
                .defineInRange("weights.lobster", 12, 1, Integer.MAX_VALUE);
        damageCrab = config
                .comment("""
                        Amount of damage received by bumping into crabs.
                        2.0D = 1 Heart.
                        """)
                .defineInRange("misc.crabDamageAmount", 2.0D, 0.0D, Double.MAX_VALUE);
        disableCrabDamageInPeaceful = config
                .comment("Determines if bumping into crabs deals damage in peaceful.")
                .define("misc.doCrabDamagePeaceful", false);
    }
}
