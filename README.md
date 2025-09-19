# Quick Tiles

A simple Android application that provides Quick Settings tiles for easier access to developer functionalities.

## Features

*   **Developer Options Tile:** A tile to quickly enable or disable Developer Options.
*   **USB Debugging Tile:** A tile to toggle USB Debugging on and off.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

*   An Android development environment (Android Studio recommended).
*   Git
*   Android Debug Bridge (adb)

### Installation

1.  Clone the repo:
    ```sh
    git clone https://github.com/velgun/quick_tiles.git
    ```
2.  Open the project in Android Studio.
3.  Build and run the app on your Android device.

## Usage

For the tiles to function, you must grant the application special permissions using ADB. This is a one-time setup step.

Connect your device to your computer and run the following command in your terminal:

```sh
adb shell pm grant com.velgun.quicktiles android.permission.WRITE_SECURE_SETTINGS
```

After granting the permission, you can add the "Developer Options" and "USB Debugging" tiles to your Quick Settings panel and start using them.
