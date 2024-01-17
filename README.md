# Crypto Today Android App

## Overview

Android app written in Kotlin that displays real-time Crypto Currency Exchange Rates.

## Features

- ✅ Live Crypto App with automatic Server polling
- ✅ MVVM Architecture
- ✅ ComposeUI
- ✅ DI with Hilt
- ✅ Unit and Instrumentation tests

## Technologies Used

- Kotlin
- Android Jetpack Compose
- Retrofit for API communication
- Dagger Hilt for dependency injection

  
## Video

[Crypto Today Screen Recording](https://github.com/Joaquin144/Crypto-Today/assets/80385154/9de34b4b-b9e8-4f5b-8f41-c389bec3d072)

## Screenshots

<img src="https://github.com/Joaquin144/Crypto-Today/assets/80385154/bd13d123-0b54-4375-9868-181a87bbf8b5" width=200 />&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://github.com/Joaquin144/Crypto-Today/assets/80385154/202e3ac0-893c-435d-85b8-666507e4f1d7" width=200 />


## Usage

1. Clone the repository:
   ```bash
   git clone https://github.com/Joaquin144/Crypto-Today.git


## Project Structure

- **Package Name:** `com.joaquin.cryptotoday`
- **Main Activity:** `MainActivity`
- **Data Layer:**
    - `data.remote`: Contains code for network calls and API interactions.
    - `domain.remote.repository`: Contains the model representing the coin feed data.
- **Presentation Layer:**
    - `presentation`: Contains code for UI elements and logic.
    - `common`: Likely holds common UI components or utilities.
    - `feed_screen`: Likely holds the code for the main feed screen.
    - `ui.theme`: Likely holds custom themes and styles.
- **DI Layer:**
    - `di`: Contains dependency injection modules for managing dependencies.
- **Utils:**
    - `utils`: Contains utility classes and constants.
- **Application Class:** `CryptoApplication`
- **Resources:**
    - `res`: Contains resources like layouts, drawables, and values.
- **Gradle Scripts:**
    - `gradle`: Contains Gradle build scripts.
