# karaf-practice

A repo for gathering all the weird shit I learn about Apache Karaf.

- JDK 11
- apache-karaf-4.2.12
- apache-camel-3.11.3

---

## Installing Apache Camel in Karaf

In the karaf shell:

Add the camel feature repo:

```shell
feature:repo-add camel 3.11.3
```

Install camel features:

```shell
feature:install camel
```

---

## Hawtio in Karaf

https://hawt.io/
https://github.com/hawtio/hawtio

```shell
feature:repo-add hawtio 2.14.1
feature:install hawtio
```

You should be able to access the web frontend via http://localhost:8181/hawtio with login and username `karaf`.

---

## Tips & Gotchas

### Blueprint XML

- The `persistent-id` attribute value in the `property-placeholder` element shouldn't have a `.cfg` suffix.
- If you set `update-strategy` to `reload`, the bundle automatically picks up and applies changes to the `cfg` file.

### camel-karaf-maven-plugin

- To use `mvn camel-karaf:run` you first need to `mvn install` the module.
- Changes **won't** be automatically detected, so you need to make sure you run `mvn install` again.

## Random interesting links
- https://capgemini.github.io/java/cleaning-the-camel/
- https://hawt.io/
- https://karaf.apache.org/manual/latest/#_passwords_encryption
- https://activemq.apache.org/encrypted-passwords