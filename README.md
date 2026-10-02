# Soleventory

<p align="center">
  <img src="app/src/main/res/drawable/soleventory_logos_white.png" alt="Soleventory logo" width="240">
</p>

<p align="center">
  A simple Android inventory manager for keeping track of footwear products.
</p>

## About

Soleventory is a native Android application built in Java. It combines Firebase Authentication with Firebase Realtime Database to give users a personal space where they can register, sign in, add product details, and view an inventory summary.

The project was created as a lightweight foundation for a shoe inventory workflow and can be extended with richer catalog, search, editing, and reporting features.

## Features

- User registration with name, email, and password
- Email verification before account access
- Email and password sign-in
- Password-reset emails
- Add products with a name, SKU, price, color, and size
- View an inventory item count and total value
- Sign out securely through Firebase Authentication

## Tech stack

- **Language:** Java 8
- **UI:** Android XML layouts, Material Components, and ConstraintLayout
- **Authentication:** Firebase Authentication
- **Database:** Firebase Realtime Database
- **Build system:** Gradle 7.2 with Android Gradle Plugin 7.1.3
- **Android support:** API 21 and later; compiled against API 32

## Getting started

### Prerequisites

Install the following before opening the project:

- [Android Studio](https://developer.android.com/studio)
- JDK 11 (required by Android Gradle Plugin 7.1.x)
- Android SDK 32
- A [Firebase](https://firebase.google.com/) project

### 1. Clone the repository

```bash
git clone https://github.com/MahianK/Soleventory.git
cd Soleventory
```

### 2. Configure Firebase

1. Create a Firebase project in the [Firebase console](https://console.firebase.google.com/).
2. Add an Android app with the package name `com.example.project`.
3. Download the generated `google-services.json` file.
4. Place it at `app/google-services.json`, replacing the existing configuration if necessary.
5. In **Authentication → Sign-in method**, enable **Email/Password**.
6. Create a **Realtime Database** and configure rules appropriate for authenticated users.

> Never commit service-account credentials or private keys. The Android `google-services.json` file identifies your Firebase project but is not a substitute for secure database rules.

### 3. Run the app

Open the repository in Android Studio, allow Gradle to finish syncing, select an emulator or connected Android device, and click **Run**.

You can also build a debug APK from the command line:

```bash
bash gradlew assembleDebug
```

On Windows, use:

```powershell
gradlew.bat assembleDebug
```

The generated APK will be available under `app/build/outputs/apk/debug/`.

## How to use

1. Create an account with your name, email address, and a password of at least six characters.
2. Open the verification message sent to your email address.
3. Return to Soleventory and sign in.
4. Choose **Add Product** to save product details.
5. Choose **View Inventory** to see the current item count and total value.
6. Use **Logout** when you are finished.

## Project structure

```text
Soleventory/
├── app/
│   ├── google-services.json
│   ├── build.gradle
│   └── src/
│       ├── main/
│       │   ├── java/com/example/project/  # Activities and data models
│       │   ├── res/                       # Layouts, images, themes, and strings
│       │   └── AndroidManifest.xml
│       ├── test/                           # Local unit tests
│       └── androidTest/                    # Instrumented Android tests
├── gradle/wrapper/
├── build.gradle
└── settings.gradle
```

## Firebase data layout

User profiles are stored by Firebase user ID, while inventory records currently use the signed-in user's email address with periods removed:

```text
Users/
├── <firebase-user-id>/
│   ├── fullName
│   └── email
└── <sanitized-email>/
    ├── Items/
    └── ItemBySKU/
        └── <sku>/
```

If you extend the app, consider standardizing all user-owned data under the Firebase user ID and enforcing that ownership in your Realtime Database rules.

## Testing

Run local unit tests with:

```bash
bash gradlew test
```

Run instrumented tests on a connected device or emulator with:

```bash
bash gradlew connectedAndroidTest
```

## Ideas for future development

- Display individual products in the inventory screen
- Edit and delete existing products
- Store every item under a unique database key
- Search and filter products by SKU, color, or size
- Add product photos and low-stock alerts
- Migrate user inventory paths to Firebase user IDs
- Add automated tests for authentication and inventory flows

## Contributing

Contributions are welcome. Fork the repository, create a branch for your change, and open a pull request with a clear description of what you changed.
