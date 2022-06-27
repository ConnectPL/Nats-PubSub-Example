# Nats-PubSub-Example

## Introduction

These Plugin example using the NATS PubSub [nats](https://github.com/nats-io/nats.java)

## Maven depends and repo

```xml
<repositories>
    <repository>
        <id>sonatype snapshots</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    </repository>
</repositories>

<dependency>
    <groupId>io.nats</groupId>
    <artifactId>jnats</artifactId>
    <version>2.15.3</version>
</dependency>
```

## Usage

1. Connect to NATS server:
```java
        try {
            connection = Nats.connect("nats://localhost:4222");
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }```

