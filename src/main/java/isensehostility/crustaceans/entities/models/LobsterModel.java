package isensehostility.crustaceans.entities.models;

import isensehostility.crustaceans.Crustaceans;
import isensehostility.crustaceans.entities.LobsterEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LobsterModel extends AnimatedGeoModel<LobsterEntity> {
    @Override
    public ResourceLocation getModelLocation(LobsterEntity object) {
        return Crustaceans.Utils.location("geo/lobster.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LobsterEntity object) {
        return switch (object.getVariant()) {
            case BLUE -> Crustaceans.Utils.location("textures/entities/lobster_blue.png");
            case BROWN -> Crustaceans.Utils.location("textures/entities/lobster_brown.png");
            default -> Crustaceans.Utils.location("textures/entities/lobster.png");
        };
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LobsterEntity animatable) {
        return Crustaceans.Utils.location("animations/lobster.animation.json");
    }
}
