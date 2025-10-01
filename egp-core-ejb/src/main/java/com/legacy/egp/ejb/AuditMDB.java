```java
package com.legacy.egp.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Modernized Audit Message-Driven Bean
 * Processes audit messages from JMS queue
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/AuditQueue")
})
public class AuditMDB implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(AuditMDB.class);

    @PersistenceContext(unitName = "egp-pu")
    private EntityManager entityManager;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                processAuditMessage(text);
            } catch (JMSException e) {
                logger.error("Error processing JMS message", e);
            }
        } else {
            logger.warn("Received non-text message: {}", message);
        }
    }

    private void processAuditMessage(String messageText) {
        logger.info("Processing audit message: {}", messageText);
        // Implement audit message processing logic here
        // For example, save the message to the database
    }
}
```

```java