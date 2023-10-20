# property-encryption

Demonstrating how to encrypt senstive values in property files for use in Camel routes.

## Required dependencies

Camel component:

```xml

<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-jasypt</artifactId>
    <version>${camel.version}</version>
</dependency>
```

Jaas in Karaf:

```xml

<dependency>
    <groupId>org.apache.karaf.jaas</groupId>
    <artifactId>org.apache.karaf.jaas.jasypt</artifactId>
    <version>${jaas.version}</version>
</dependency>
```

Wasn't documented but `mvn camel-karaf:run` seems to need this on the classpath to run:

```xml

<dependency>
    <groupId>org.apache.karaf.jaas</groupId>
    <artifactId>org.apache.karaf.jaas.boot</artifactId>
    <version>${jaas.version}</version>
</dependency>
```

## Setup

In order to be able to encrypt a value, you need to use the Jasypt and Camel Jasypt JARs.

Find them and copy them for ease of use

```shell
find ~ -name '*jasypt-*.jar'
cp ~/.m2/repository/org/jasypt/jasypt/1.9.3/jasypt-1.9.3.jar .
cp ~/.m2/repository/org/apache/camel/camel-jasypt/3.11.3/camel-jasypt-3.11.3.jar .
```

## Encrypting a value with a master password

In this example, we want to encrypt the application password `tiger` using the master password `secret` using default
settings.

Remember that the plaintext password will be in your command-line history, so place a [space] character before the
command to prevent it being captured in the history. You might have to activate this feature if you're using `zsh`.

```shell
# If you're using zsh, you might want to make sure you have this set to activate the option for ignoring commands
# starting with a space char 
setopt HIST_IGNORE_SPACE

# Note the first character is a space to prevent the password being captured in history
 java -cp jasypt-1.9.3.jar:camel-jasypt-3.11.3.jar org.apache.camel.component.jasypt.Main -c encrypt -p secret -i tiger
```

## Injecting the master password into the application

### Using a Java system property

Pass the system property via the command-line:

```shell
camel-karaf:run -DCAMEL_ENCRYPTION_PASSWORD=secret -f pom.xml
```

Using a Java system property requires using the following syntax in the blueprint XML file:

```xml

<property name="password" value="sys:CAMEL_ENCRYPTION_PASSWORD"/>
```

### Using an environment variable to store the master password

Place a [space] character before the command to prevent it being captured in the history. You might have to activate
this feature if you're using `zsh`.

```shell
# If you're using zsh, you might want to make sure you have this set to activate the option for ignoring commands
# starting with a space char 
setopt HIST_IGNORE_SPACE

# Note the first character is a space to prevent the password being captured in history
 export CAMEL_ENCRYPTION_PASSWORD=secret
```

If the password is captured by history then you can remove it by editing the history file. `history -d id` didn't work
for me.

Using an environment variable requires using the following syntax in the blueprint XML file:

```xml

<property name="password" value="sysenv:CAMEL_ENCRYPTION_PASSWORD"/>
```

You should unset the environment variable after the application has been started:

```shell
unset CAMEL_ENCRYPTION_PASSWORD
```

### Deployment in Karaf

#### System property

- Karaf is stopped.
- Edit `etc/system.properties` to add `CAMEL_ENCRYPTION_PASSWORD=secret`.
- Start Karaf.
- Edit `etc/system.properties` to remove `CAMEL_ENCRYPTION_PASSWORD=secret`.
- If the Karaf application is stopped, the steps above need to be carried out again.

#### Environment variable

- Karaf is stopped.
- Set an environment variable with the key `CAMEL_ENCRYPTION_PASSWORD` which matches that given in the `blueprint.xml`
  file, setting its value to `secret`.
- Start Karaf.
- Ensure the bundle using the encrypted password has started.
- Unset the `CAMEL_ENCRYPTION_PASSWORD` environment variable.
- If the Karaf application is stopped, the steps above need to be carried out again.
    - TODO: check what happens if the bundle is stopped/uninstalled then started/reinstalled.