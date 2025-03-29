package net.zervancer.deadlysnowgolems.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.zervancer.deadlysnowgolems.config.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SnowballEntity.class)
public class MixinSnowballEntity extends ThrownItemEntity {
	public MixinSnowballEntity(EntityType<? extends SnowballEntity> entityType, World world) {
		super(entityType, world);
	}

	public MixinSnowballEntity(World world, LivingEntity owner) {
		super(EntityType.SNOWBALL, owner, world);
	}

	public MixinSnowballEntity(World world, double x, double y, double z) {
		super(EntityType.SNOWBALL, x, y, z, world);
	}

    @Override
	public Item getDefaultItem() {
		return Items.SNOWBALL;
	}
	@ModifyConstant(method = "onEntityHit(Lnet/minecraft/util/hit/EntityHitResult;)V", constant = @Constant(intValue = 0))
    private int injected(int value) {
		ConfigSettings configSettings = ConfigHandler.readConfig();
        return this.getOwner() instanceof SnowGolemEntity ? configSettings.defaultDMG : 0;
    }
}