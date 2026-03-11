package com.example.scheduler;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(category = "infrastructure")
@Meta.OCD(
        id = "com.example.scheduler.SchedulerConfiguration",
        localization = "content/Language",
        name = "liferay-scheduler-configuration"
)
public interface SchedulerConfiguration {

    @Meta.AD(
            deflt = "1",
            name = "interval-in-minutes",
            required = false
    )
    public int interval();
}