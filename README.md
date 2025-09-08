

FinRateX: A Financial Rates Android Application
This project is a native Android application developed using Jetpack Compose and the MVVM architecture. The main goal of this application is to display real-time financial rates and information from financial markets, including currency, gold, and cryptocurrencies.

🚀 Key Features
Splash Screen: A beautiful introductory screen with an animation for a better user experience.

Live Rates Display: Access to up-to-date financial information for currency, gold, and cryptocurrency.

API Integration: Fetches financial data from an external API using the powerful Retrofit and OkHttp libraries.

Dependency Management: Utilizes Hilt for standard and efficient dependency injection throughout the project.

Navigation: Manages navigation between screens using Jetpack Compose Navigation.

Modern UI: The user interface is designed with a Compose-first approach, making the UI interactive and responsive.

📸 Gallery

|  |  |
| :------------------------------: | :--------------------------------: |
| ![Date Screen](screenshots/date.png) | ![Details Screen](screenshots/details.png) |
| ![More Details](screenshots/details2.png) | ![Home Crypto](screenshots/homeCrypto.png) |
| ![Home Currency](screenshots/homeCurrency.png) | ![Home Gold](screenshots/homeGold.png) |
| ![Splash Error](screenshots/splashError.png) | ![Splash Loading](screenshots/splashLoading.png) |


🛠️ Technologies Used
Programming Language:

Kotlin

Frameworks and Libraries:

Jetpack Compose (for UI)

Compose Navigation (for navigation)

Hilt (for dependency injection)

Retrofit and OkHttp (for networking and API calls)

Coroutines (for asynchronous operations)

Architecture:

MVVM (Model-View-ViewModel)

⚙️ Project Setup Guide
To run this project on your system, follow these steps:

Clone the Repository:

git clone https://github.com/IrajNajafi/FinRateX.git
Open in Android Studio:

Open the project folder using Android Studio.

Gradle Sync:

Wait for Gradle to automatically sync the project files.

Configure API:

Open the local.properties file in the project's root directory.

Add your API base URL to the end of the file as follows:

BASE_URL="https://tools.daneshjooyar.com/api/v1/"
Run:

Run the project on an emulator or a physical device.

🤝 Contribution

Any contributions or improvements to this project are welcome. To contribute, please first open an Issue on GitHub, and then submit a Pull Request.

👨‍💻 Contact the Developer

Name: Iraj Najafi

Email: irajnajafi1988@gmail.com