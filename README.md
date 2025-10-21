# ✨AAB ProGuard Map Remover Plugin
A Gradle plugin that automatically removes proguard.map files from Android App Bundle (AAB) build outputs. This plugin helps reduce AAB file size and simplifies the release process when mapping files are not needed.

## 🚀 Usage
```
plugins {
    id("com.scalified.plugins.gradle.proguard") version "$version"
}
```

# Important Notes
⚠️ Critical Warnings:
Deleting proguard.map files makes it impossible to deobfuscate crash stack traces

## Contributing
Contributions are welcome! Please read our contributing guidelines before submitting pull requests.
