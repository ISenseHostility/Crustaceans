package isensehostility.crustaceans.entities.renderers;

import isensehostility.crustaceans.entities.LobsterEntity;
import isensehostility.crustaceans.entities.models.LobsterModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LobsterRenderer extends GeoEntityRenderer<LobsterEntity> {

    public LobsterRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LobsterModel());
        this.shadowRadius = 0.4F;
    }
}
