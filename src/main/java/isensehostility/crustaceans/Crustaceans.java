package isensehostility.crustaceans;

import isensehostility.crustaceans.config.CrustaceansConfig;
import isensehostility.crustaceans.init.EntityInit;
import isensehostility.crustaceans.init.ItemInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import software.bernie.geckolib3.GeckoLib;

@Mod("crustaceans")
public class Crustaceans {

    public Crustaceans() {
        GeckoLib.initialize();
        CrustaceansConfig.loadConfig(CrustaceansConfig.config, FMLPaths.CONFIGDIR.get().resolve(Utils.MODID + "-config.toml").toString());
        EntityInit.initialize();
        ItemInit.initialize();
    }

    public class Utils {
        public static final String MODID = "crustaceans";

        public static ResourceLocation location(String name) {
            return new ResourceLocation(MODID, name);
        }
    }
}
