# IntelliJ Idea Plugin by trivago

Internal plugin, quick and dirty implementation.

Features:
* Autogenerated classes to protobuf navigation. For example, by clicking on `// source: foo/bar.proto` the IDE will open the protobuf file.

### Installation

1. Add the URL `https://raw.githubusercontent.com/trivago/intellij-idea-plugin/master/updatePlugins.xml` to the [custom plugin repositories settings](https://www.jetbrains.com/help/idea/custom-plugin-repositories.html)
2. Search for the plugin "trivago" and install it

### Development

*Requirements*

* IntelliJ Idea

*Run the local plugin*

This will start a new session of IDE with loaded the local plugin.

1. Clone the project and import it in IntelliJ Idea
2. Run select `intellij -> runIde` from the IDE's gradle window or run `gradle runIde`.   

### Release

1. Increase the version in `src/main/resources/META-INF/plugin.xml` and `build.gradle.kts`
2. Commit and push
3. Run select `intellij -> jar` or run `gradle jar`. It will create a jar file in `build/libs/intellij-idea-plugin-x.x.x.jar`. E.g. `build/libs/intellij-idea-plugin-0.1.0.jar`.
4. On github create a new release and attach the built jar by uploading it through the form.
5. Increase the version and adjust the URL accordingly in `updatePlugins.xml`
6. Commit and push

The IDE will automatically be notified of the newly available version.