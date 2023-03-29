# TacoAPI - Fork of Vault designed for speed and more support! -
How to include the API with Maven: 
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>com.github.coderFlameyosFlow</groupId>
        <artifactId>TacoAPI</artifactId>
        <version>1.1.0</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

How to include the API with Gradle:
```kotlin
repositories {
    maven("https://jitpack.io")
}
dependencies {
    compileOnly("com.github.coderFlameyosFlow:TacoAPI:1.1.0")
}
```

## Why Taco instead of Vault?
Here are the Pros and Cons of using Taco instead of Vault:

### Pros:
 * Most of Vault's pros
 * Compatibility with all Vault Plugins
 * UUID support
 * "World" usage instead of "String worldName"
 * Actively maintained and updated
 * Asynchronous economy Tasks
 * and more always coming!
### Cons:
 * With asynchronous code, comes less readable code.
 
## License
Copyright (C) 2011-2018 Morgan Humes <morgan@lanaddict.com>
Copyright (C) 2023-present FlameyosFlow

Vault is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Vault is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with Vault.  If not, see <http://www.gnu.org/licenses/>.
