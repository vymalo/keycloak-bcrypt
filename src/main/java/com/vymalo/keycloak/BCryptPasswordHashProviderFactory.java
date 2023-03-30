package com.vymalo.keycloak;

import org.keycloak.Config;
import org.keycloak.credential.hash.PasswordHashProvider;
import org.keycloak.credential.hash.PasswordHashProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ServerInfoAwareProviderFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:pro.guillaume.leroy@gmail.com">Guillaume Leroy</a>
 */
public class BCryptPasswordHashProviderFactory implements PasswordHashProviderFactory, ServerInfoAwareProviderFactory {
    public static final String ID = "bcrypt";
    public static final int DEFAULT_ITERATIONS = 8192;

    private static final Map<String, String> infos = new HashMap<>();

    static {
        infos.put("version", "2.0.0");
    }

    @Override
    public PasswordHashProvider create(KeycloakSession session) {
        return new BCryptPasswordHashProvider(ID, DEFAULT_ITERATIONS);
    }

    @Override
    public void init(Config.Scope config) {
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void close() {
    }

    /**
     * Return actual info about the provider. This info contains informations about providers configuration and operational conditions (eg. errors in connection to remote systems etc) which is
     * shown on "Server Info" page then.
     *
     * @return Map with keys describing value and relevant values itself
     */
    @Override
    public Map<String, String> getOperationalInfo() {
        return infos;
    }
}
