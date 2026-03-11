package com.example.messagebus.api;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = Object.class)
public class MessageBusConfigurator {

    @Activate
    protected void activate(BundleContext bundleContext) {
        _bundleContext = bundleContext;

        DestinationConfiguration destinationConfiguration =
                DestinationConfiguration.createSerialDestinationConfiguration(
                        "demo/message/destination");

        Destination destination =
                _destinationFactory.createDestination(destinationConfiguration);

        Dictionary<String, Object> props =
                HashMapDictionaryBuilder.<String, Object>put(
                        "destination.name", destination.getName()
                ).build();

        _serviceRegistration =
                bundleContext.registerService(Destination.class, destination, props);
    }

    @Deactivate
    protected void deactivate() {
        if (_serviceRegistration != null) {
            _serviceRegistration.unregister();
        }
    }

    private BundleContext _bundleContext;

    @Reference
    private DestinationFactory _destinationFactory;

    private ServiceRegistration<Destination> _serviceRegistration;
}