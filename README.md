# SampleCacheApp

This app is used to test simple POCs on EHCache, Redis & Coherence caches. 

## Installation

Import maven project in Java support IDE. Set Java path & Install Maven dependencies. 

> Run application
```bash
To run Spring-Boot app use main class Application.java
```

## Configuration
As this app is designed to have 3 type of cache so you may need to turn on and off basic on requirement.

> ### Cache config in application.properties
```bash
To use EHCache, its default configured no need to change anything 
```
```bash
To use Redis, in application.properties  make change here
comment EHCache configuration
remove commented or enable Redis Cache configuration
```

> ### Redis Server

In resources folder Redis CLI is zipped (Redis-Server.zip) extract it in some location out of project workspace.

```bash
Run server by double-click redis-server.exe
Open CMD in Redis-Server dir and use command redis-server.exe
```
```bash
To test use Redis client redis-cli.exe
Run command 
KEYS *
SET mykey "Hello"
KEYS *
get mykey
```
You would be able to see keys stored in cache server

## Coherence cache

Coherence config is commented as of now, If you want to enable make application changes and enable services.

```bash
Removed commented code for coherence cache and dependancies.
```
```bash
If you are unable to install coherence jar
Use maven install jar file using command
jar provided in resources folder

mvn install:install-file –Dfile=SampleCacheApp\src\main\resources\coherence-3.7.0.jar
-DgroupId=coherence-3.7.0.jar
-DartifactId=coherence
-Dversion=3.7.0

```
