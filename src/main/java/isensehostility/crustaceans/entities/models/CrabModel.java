package isensehostility.crustaceans.entities.models;

import isensehostility.crustaceans.Crustaceans;
import isensehostility.crustaceans.entities.CrabEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CrabModel extends AnimatedGeoModel<CrabEntity> {
    @Override
    public ResourceLocation getModelLocation(CrabEntity object) {
        return Crustaceans.Utils.location("geo/crab.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CrabEntity object) {
        return switch (object.getVariant()) {
            case ORANGE -> Crustaceans.Utils.location("textures/entities/crab_orange.png");
            case YELLOW -> Crustaceans.Utils.location("textures/entities/crab_yellow.png");
            default -> Crustaceans.Utils.location("textures/entities/crab.png");
        };
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CrabEntity animatable) {
        return Crustaceans.Utils.location("animations/crab.animation.json");
    }
}
