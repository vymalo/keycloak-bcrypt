# Keycloak Bcrypt

bcrypt is a password-hashing function 
designed by Niels Provos and David Mazières, 
based on the Blowfish cipher and presented 
at USENIX in 1999 (Thanks, 
[Wikipedia](https://en.wikipedia.org/wiki/Bcrypt)).

Natively, Keycloak does not support Bcrypt.
So you may need this plugin to add a 
new PasswordHashProvider, now using it.

For more information about this SPI, 
please take a look at [this class](https://www.keycloak.org/docs-api/11.0/javadocs/org/keycloak/credential/hash/PasswordHashProvider.html)
and [this other page from keycloak's doc](https://www.keycloak.org/docs/latest/server_development).


## Usage
1. Download [keycloak-bcrypt-1.0.0.jar](https://github.com/bayamsell/keycloak-bcrypt/releases/download/v1.0.0/keycloak-bcrypt-1.0.0.jar)
2. Now, add it files into `/providers` folder of your keycloak.
3. Start your Keycloak
