package com.vymalo.keycloak;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.AllArgsConstructor;
import org.keycloak.credential.hash.PasswordHashProvider;
import org.keycloak.models.PasswordPolicy;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.models.credential.dto.PasswordCredentialData;

/**
 * @author <a href="mailto:pro.guillaume.leroy@gmail.com">Guillaume Leroy</a>
 */
@AllArgsConstructor
public class BCryptPasswordHashProvider implements PasswordHashProvider {
    // BCrypt uses min of 4 and max of 30 2**log_rounds
    private static final int MAX_BCRYPT_LOG_ROUNDS = 30;
    private static final int MIN_BCRYPT_LOG_ROUNDS = 4;

    private final String providerId;
    private final int defaultIterations;

    @Override
    public boolean policyCheck(PasswordPolicy policy, PasswordCredentialModel passwordCredentialModel) {
        int policyHashIterations = policy.getHashIterations();
        if (policyHashIterations == -1) {
            policyHashIterations = defaultIterations;
        }

        PasswordCredentialData passwordCredentialData = passwordCredentialModel.getPasswordCredentialData();
        return passwordCredentialData.getHashIterations() == policyHashIterations && providerId.equals(passwordCredentialData.getAlgorithm());
    }

    @Override
    public PasswordCredentialModel encodedCredential(String rawPassword, int iterations) {
        final var bcryptHashString = encode(rawPassword, iterations);
        return PasswordCredentialModel.createFromValues(providerId, new byte[0], iterations, bcryptHashString);
    }

    @Override
    public boolean verify(String rawPassword, PasswordCredentialModel passwordCredentialModel) {
        final var passwordSecretData = passwordCredentialModel.getPasswordSecretData();
        final var result = BCrypt.verifyer().verify(rawPassword.toCharArray(), passwordSecretData.getValue());
        return result.verified;
    }

    @Override
    public String encode(String rawPassword, int iterations) {
        int logRounds = iterations == -1 ? iterationsToLogRounds(defaultIterations) : iterationsToLogRounds(iterations);
        return BCrypt.withDefaults().hashToString(logRounds, rawPassword.toCharArray());
    }

    @Override
    public void close() {

    }

    private static int iterationsToLogRounds(int iterations) {
        // bcrypt uses 2**log2_rounds with a min of 4 and max of 30 log rounds
        return Math.max(MIN_BCRYPT_LOG_ROUNDS, Math.min(MAX_BCRYPT_LOG_ROUNDS,
                (int) Math.round(Math.log(iterations) / Math.log(2) + 1)));
    }
}
