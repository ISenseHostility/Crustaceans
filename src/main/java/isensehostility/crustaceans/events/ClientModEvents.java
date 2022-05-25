package isensehostility.crustaceans.events;

import isensehostility.crustaceans.Crustaceans;
import isensehostility.crustaceans.entities.renderers.CrabRenderer;
import isensehostility.crustaceans.entities.renderers.LobsterRenderer;
import isensehostility.crustaceans.init.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Crustaceans.Utils.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.CRAB.get(), CrabRenderer::new);
        event.registerEntityRenderer(EntityInit.LOBSTER.get(), LobsterRenderer::new);
    }
}
