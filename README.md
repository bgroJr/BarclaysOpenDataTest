# BarclaysOpenData

Example API tests for datasets from [Barclays Open Data](https://developer.barclays.com/open-banking).

Automated API tests are written in [Kotlin](https://kotlinlang.org) (script), with [OkHttp](https://square.github.io/okhttp) 
for HTTP requests, and [Moshi](https://github.com/square/moshi) for strongly-typed JSON de-serialization.

## Setup

```
# on Mac OS, install Kotlin with Homebrew
$ brew install kotlin
``` 

## Run

```
$ kotlinc -script TestRunner.main.kts
```

