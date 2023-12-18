package io.github.leothawne.LTSleepNStorm.module;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.api.MetricsAPI;

public final class MetricsModule {
	public static final void init() {
		new MetricsAPI(LTSleepNStorm.getInstance(), 3945);
	}
}