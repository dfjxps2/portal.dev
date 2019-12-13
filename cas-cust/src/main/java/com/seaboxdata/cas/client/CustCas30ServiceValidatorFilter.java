package com.seaboxdata.cas.client;

import org.jasig.cas.client.validation.*;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

public class CustCas30ServiceValidatorFilter extends Cas30ProxyReceivingTicketValidationFilter {

    protected void initInternal(FilterConfig filterConfig) throws ServletException {
        super.initInternal(filterConfig);

        Cas30ServiceTicketValidator ticketValidator =
                (Cas30ServiceTicketValidator) this.getTicketValidator(filterConfig);
        HttpsAllTrustURLConnectionFactory httpsUrlConnectionFactory = new HttpsAllTrustURLConnectionFactory();
        ticketValidator.setURLConnectionFactory(httpsUrlConnectionFactory);

        this.setTicketValidator(ticketValidator);

    }
    
    

}
