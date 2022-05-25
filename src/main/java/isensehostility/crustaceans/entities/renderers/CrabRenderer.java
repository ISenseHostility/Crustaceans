package isensehostility.crustaceans.entities.renderers;

import isensehostility.crustaceans.entities.CrabEntity;
import isensehostility.crustaceans.entities.models.CrabModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CrabRenderer extends GeoEntityRenderer<CrabEntity> {

    public CrabRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CrabModel());
        this.shadowRadius = 0.4F;
    }
}
