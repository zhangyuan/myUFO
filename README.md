# myUFO
Universal Food Ordering.

## Problems

### How to upgrade gradle?

Since gradle 2.4 does not support checkstyle report as HTML, it must be upgraded first.

Set the version in `build.gradle` as below:

```
task wrapper(type: Wrapper) {
    gradleVersion = '2.14'
}
```

Then run the command in terminal. The new gradle would be downloaded automatically, which may take several minutes.

```
./gradlew wrapper
```
