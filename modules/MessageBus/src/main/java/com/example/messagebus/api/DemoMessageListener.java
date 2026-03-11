package com.example.messagebus.api;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {"destination.name=demo/message/destination"},
        service = MessageListener.class
)
public class DemoMessageListener extends BaseMessageListener {

    private static final Log log =
            LogFactoryUtil.getLog(DemoMessageListener.class);

    @Override
    protected void doReceive(Message message) throws Exception {
        log.info("Message received!");
        log.info("Emp Name: " + message.getString("employeeName") + "Emp ID" + message.getLong("objectEntryId"));
    }
}