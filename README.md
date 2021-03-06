# Nats-PubSub-Example

## Introduction

These Plugin example using the NATS PubSub [nats](https://github.com/nats-io/nats.java)

## Maven depends and repo

```xml
<repositories>
    <repository>
        <id>sonatype</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    </repository>
</repositories>

<dependency>
    <groupId>io.nats</groupId>
    <artifactId>jnats</artifactId>
    <version>2.15.3</version>
</dependency>
```

## Usage Example

### Connecting

```java

public Connection connection;

    public static void main(String[] args) {
        try {
            connection = Nats.connect("nats://localhost:4222");
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }   
    }
```
### Publishing

```java
    connection.publish("example", "hii".getBytes());
```

### Listening

Dispatcher can multiple pubsub with a single thread and shared callback
```java
    Dispatcher d = connection.createDispatcher((msg) -> {});

    Subscription s = d.subscribe("example", (msg) -> {
        System.out.println(new String(msg.getData()));
    });
    d.unsubscribe(s, 100);
```

