package com.ulfric.spigot.commons.entity;

import org.apache.commons.lang3.mutable.MutableDouble;
import org.bukkit.entity.LivingEntity;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.ulfric.verify.Verify;

@RunWith(JUnitPlatform.class)
class EntityUtilsTest {

	@Test
	void testKillOnNullDoesNotThrowNullPointerException()
	{
		EntityUtils.kill(null);
	}

	@Test
	void testKill()
	{
		MutableDouble health = new MutableDouble(10);

		LivingEntity mockEntity = Mockito.mock(LivingEntity.class);

		Mockito.doAnswer(invocation ->
			{
				health.setValue(invocation.getArgumentAt(0, double.class));
				return null;
			}).when(mockEntity).setHealth(Matchers.anyDouble());

		Mockito.when(mockEntity.getHealth()).then(ignore -> health.doubleValue());

		Verify.that(mockEntity.getHealth()).isEqualTo(10.0);
		EntityUtils.kill(mockEntity);
		Verify.that(mockEntity.getHealth()).isEqualTo(0.0);
	}

}