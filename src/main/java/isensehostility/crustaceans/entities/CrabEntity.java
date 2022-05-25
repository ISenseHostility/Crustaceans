package isensehostility.crustaceans.entities;

import isensehostility.crustaceans.config.CrustaceansConfig;
import isensehostility.crustaceans.init.ItemInit;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class CrabEntity extends Animal implements IAnimatable, Bucketable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(CrabEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(CrabEntity.class, EntityDataSerializers.BOOLEAN);

    public CrabEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0F);
    }

    public static AttributeSupplier.Builder initializeAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.15D)
                .add(Attributes.ATTACK_DAMAGE, CrustaceansConfig.damageCrab.get());
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.SEAGRASS), false));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static boolean canSpawnOnGround(EntityType<CrabEntity> entity, ServerLevelAccessor accessor, MobSpawnType type, BlockPos pos, Random random) {
        return pos.getY() < accessor.getSeaLevel() + 4 && TurtleEggBlock.onSand(accessor, pos) && isBrightEnoughToSpawn(accessor, pos);
    }

    private <E extends IAnimatable> PlayState movement(AnimationEvent<E> event) {
        if (this.getX() == this.xo && this.getZ() == this.zo) {
            return PlayState.STOP;
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation(
                    "animation.crab.walk",
                    true));
            return PlayState.CONTINUE;
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "movement", 0, this::movement));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_27587_) {
        super.addAdditionalSaveData(p_27587_);
        p_27587_.putInt("Variant", this.getVariant().getId());
        p_27587_.putBoolean("FromBucket", this.fromBucket());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_149145_) {
        this.setVariant(Variant.BY_ID[p_149145_.getInt("Variant")]);
        this.setFromBucket(p_149145_.getBoolean("FromBucket"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_, MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_, @Nullable CompoundTag p_146750_) {
        this.setVariant(Variant.getSpawnVariant(this.level.random));

        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(FROM_BUCKET, false);
    }

    public Variant getVariant() {
        return Variant.BY_ID[this.entityData.get(VARIANT)];
    }

    public void setVariant(Variant variant) {
        this.entityData.set(VARIANT, variant.getId());
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean p_148834_) {
        this.entityData.set(FROM_BUCKET, p_148834_);
    }

    @Override
    public void saveToBucketTag(ItemStack p_148833_) {
        if (this.hasCustomName()) {
            p_148833_.setHoverName(this.getCustomName());
        }
        Bucketable.saveDefaultDataToBucketTag(this, p_148833_);
        CompoundTag compoundtag = p_148833_.getOrCreateTag();
        compoundtag.putInt("Variant", this.getVariant().getId());
    }

    @Override
    public void loadFromBucketTag(CompoundTag p_148832_) {
        Bucketable.loadDefaultDataFromBucketTag(this, p_148832_);
        int i = p_148832_.getInt("Variant");
        if (i >= 0 && i < Variant.BY_ID.length) {
            this.setVariant(Variant.BY_ID[i]);
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ItemInit.CRAB_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    @Override
    public boolean removeWhenFarAway(double p_149183_) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
    }

    public static enum Variant {
        RED(0, "red"),
        ORANGE(1, "orange"),
        YELLOW(2, "yellow");

        private final int id;
        private final String name;

        public static final Variant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(Variant::getId)).toArray((p_149255_) -> {
            return new Variant[p_149255_];
        });

        private Variant(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        private static Variant getSpawnVariant(Random random) {
            return Util.getRandom(Arrays.stream(BY_ID).toArray((p_149244_) -> {
                return new Variant[p_149244_];
            }), random);
        }
    }
}
