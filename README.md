# Keycloak Bcrypt

## ⚠️ Depreciation notice ⚠️
![img.png](img.png)

I'm not working on this plugin anymore, because I think, given the direction Keycloak is taking, it would be better to have this functionality as an external service, outside Keycloak.

I suggest implementing an SPI for user and user credentials. Then call an external service with those credentials and force https.

## Usage

After you'll install this, you'll be able to choose this Hash provider as default in the console. Keep in mind [this article](https://security.stackexchange.com/questions/133239/what-is-the-specific-reason-to-prefer-bcrypt-or-pbkdf2-over-sha256-crypt-in-pass), where the question about bcrypt or PBKDF2 over SHA256-crypt is discussed. Please read it, before using this plugin.

This plugin might be useful if you imported your users from a system who have Bcrypt as password hashing provider.

## Under the hood

1. Password Hash Provider.
2. Bcrypt from [patrickfav](https://github.com/patrickfav/bcrypt).

These providers are used to keep the plugin configuration running.

## Links

- https://medium.com/nerd-for-tech/sha-2-and-bcrypt-encryption-algorithms-e0c0599b0da
- https://stackoverflow.com/questions/64063528/failsafe-error-invalid-signature-file-digest-for-manifest-main-attributes-whe
