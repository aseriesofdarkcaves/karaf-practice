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
command to prevent it being captured in the history.

```shell
# Note the first character is a space to prevent the password being captured in history
 java -cp jasypt-1.9.3.jar:camel-jasypt-3.11.3.jar org.apache.camel.component.jasypt.Main -c encrypt -p secret -i tiger
```

## Using an environment variable to store the master password

Java needs to be able to see the environment variable - so the variable needs to be added for the user that runs
the `java` command, or system-wide.

Remember that the plaintext password will be in your command-line history, so place a [space] character before the
command to prevent it being captured in the history.

```shell
# Note the first character is a space to prevent the password being captured in history
 export CAMEL_ENCRYPTION_PASSWORD=secret
```

If the password is captured by history then you can remove it by editing the history file. `history -d id` didn't work
for me.

You should probably unset it afterwards too.

```shell
unset CAMEL_ENCRYPTION_PASSWORD
```
