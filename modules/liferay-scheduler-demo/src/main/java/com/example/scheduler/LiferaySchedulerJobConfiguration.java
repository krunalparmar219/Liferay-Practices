package com.example.scheduler;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.scheduler.SchedulerJobConfiguration;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import java.util.Map;

@Component(configurationPid = "com.example.scheduler.LiferaySchedulerJobConfiguration", service = SchedulerJobConfiguration.class)

public class LiferaySchedulerJobConfiguration implements SchedulerJobConfiguration {

    SchedulerConfiguration _schedulerConfiguration;

    @Override
    public UnsafeRunnable<Exception> getJobExecutorUnsafeRunnable() {
        return () -> {
            System.out.println("Hello Ignek Viewers :)");
        };
    }

    @Override
    public TriggerConfiguration getTriggerConfiguration() {
        return TriggerConfiguration.createTriggerConfiguration(_schedulerConfiguration.interval(), TimeUnit.MINUTE);
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        _schedulerConfiguration = ConfigurableUtil.createConfigurable(SchedulerConfiguration.class, properties);
    }
}
