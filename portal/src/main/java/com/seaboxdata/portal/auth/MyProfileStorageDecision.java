package com.seaboxdata.portal.auth;

import org.pac4j.core.client.Client;
import org.pac4j.core.client.DirectClient;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.engine.decision.DefaultProfileStorageDecision;
import org.pac4j.core.profile.CommonProfile;

import java.util.List;

public class MyProfileStorageDecision<C extends WebContext> extends DefaultProfileStorageDecision<C> {

    @Override
    public boolean mustSaveProfileInSession(C context, List<Client> currentClients, DirectClient directClient, CommonProfile profile) {
        return true;
    }

    @Override
    public boolean mustLoadProfilesFromSession(C context, List<Client> currentClients) {
        return true;
//        return CommonHelper.isEmpty(currentClients) || currentClients.get(0) instanceof IndirectClient || currentClients.get(0) instanceof AnonymousClient;
    }


}
