```java
package com.legacy.egp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.soap.*;
import java.net.URL;

/**
 * SOAP Web Service Client
 * Demonstrates typical SOAP client code.
 * 
 * @version 2.0
 * @since 2023
 */
@Service
public class LegacySoapClient {

    private static final Logger logger = LoggerFactory.getLogger(LegacySoapClient.class);
    private static final String SOAP_ENDPOINT = "http://legacy-services.internal.corp:8080/LegacyService";
    private static final String NAMESPACE = "http://example.com/namespace";

    public SOAPMessage callSoapWebService(String soapAction, SOAPMessage requestMessage) {
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            URL endpoint = new URL(SOAP_ENDPOINT);
            SOAPMessage soapResponse = soapConnection.call(requestMessage, endpoint);

            soapConnection.close();
            return soapResponse;
        } catch (Exception e) {
            logger.error("Error calling SOAP Web Service", e);
            throw new RuntimeException("SOAP request failed", e);
        }
    }

    public SOAPMessage createSOAPRequest(String action) throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("example", NAMESPACE);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement(action, "example");

        soapMessage.saveChanges();

        logger.info("SOAP Request Message created");
        return soapMessage;
    }
}
```