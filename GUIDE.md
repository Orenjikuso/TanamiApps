# 📖 PANDUAN PENGEMBANGAN - TanamiApps

Dokumentasi ini adalah aturan main **WAJIB** bagi seluruh developer Tanami agar pengerjaan cepat (**RAD**) dan kode tetap terstruktur (**MVVM**).

---

## 📋 Daftar Isi

1. [Konfigurasi Teknis](#-1-konfigurasi-teknis)
2. [Struktur Folder &amp; Fungsi](#-2-struktur-folder--fungsi-setiap-layer)
3. [Jobdesk Developer](#-3-jobdesk-developer--task-assignment)
4. [Aturan Coding &amp; Git](#-4-aturan-coding--git)
5. [Cara Setup Lokal](#-5-cara-setup-lokal)
6. [Dependency &amp; Library](#-6-dependency--library)

---

## ⚙️ 1. KONFIGURASI TEKNIS

### Build Configuration

- **Android Gradle Plugin (AGP)**: `9.1.1`
- **Target SDK**: Android 21+ (JDK 21)
- **Gradle Version**: `9.3.1`
- **Kotlin Code Style**: `official`
- **Min SDK**: Android 21+

### Environment Setup

```bash
# Java Version
jdk-name: jbr-21
target: JDK_21

# Gradle Memory
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
```

### IDE & Tools

- **IDE**: Android Studio (2024.1+)
- **VCS**: Git
- **Build System**: Gradle with Kotlin DSL
- **Project Type**: Android Application (MVVM)
- **Architecture Pattern**: Clean Architecture + MVVM

> ⚠️ **PENTING**: Pastikan Java JDK 21 sudah terinstall di mesin lokal Anda.

---

## 📂 2. STRUKTUR FOLDER & FUNGSI (SETIAP LAYER)

Wajib mengikuti pemetaan folder di bawah ini agar arsitektur tetap terjaga:

```
TanamiApps/
│
├── gradle/                           # ⚙️ GRADLE CONFIGURATION LAYER
│   ├── wrapper/
│   │   └── gradle-wrapper.properties # Gradle version & distribution configuration
│   ├── gradle-daemon-jvm.properties  # JVM toolchain URLs & version (21)
│   └── libs.versions.toml            # Centralized dependency version management
│
├── app/                              # 📱 MAIN APPLICATION MODULE
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/tanami/app/  # ========== SOURCE CODE ==========
│   │   │   │   │
│   │   │   │   ├── core/             # 🏗️ FOUNDATION LAYER (Shared Infrastructure)
│   │   │   │   │   ├── network/      # API & Network Configuration
│   │   │   │   │   │   ├── RetrofitClient.kt
│   │   │   │   │   │   ├── ApiService.kt
│   │   │   │   │   │   ├── TokenInterceptor.kt
│   │   │   │   │   │   ├── HeaderInterceptor.kt
│   │   │   │   │   │   └── NetworkConstants.kt
│   │   │   │   │   │
│   │   │   │   │   ├── utils/        # Utility Functions & Helpers
│   │   │   │   │   │   ├── Constants.kt       # App-wide constants
│   │   │   │   │   │   ├── Extensions.kt     # Kotlin extensions (String, View, etc)
│   │   │   │   │   │   ├── DateFormatter.kt  # Date formatting utilities
│   │   │   │   │   │   ├── Logger.kt         # Logging utility
│   │   │   │   │   │   └── Validators.kt     # Input validation helpers
│   │   │   │   │   │
│   │   │   │   │   └── base/         # Base Classes & Abstract Components
│   │   │   │   │       ├── BaseActivity.kt       # Parent Activity (lifecycle management)
│   │   │   │   │       ├── BaseFragment.kt       # Parent Fragment (common UI setup)
│   │   │   │   │       ├── BaseViewModel.kt      # Parent ViewModel (state management)
│   │   │   │   │       └── BaseRepository.kt     # Parent Repository (data access pattern)
│   │   │   │   │
│   │   │   │   ├── data/             # 💾 DATA LAYER (Single Source of Truth)
│   │   │   │   │   ├── local/        # Local Data Storage
│   │   │   │   │   │   ├── database/
│   │   │   │   │   │   │   ├── TanamiDatabase.kt     # Room Database (main DB)
│   │   │   │   │   │   │   ├── dao/                  # Data Access Objects
│   │   │   │   │   │   │   │   ├── UserDao.kt        # User CRUD operations
│   │   │   │   │   │   │   │   ├── SensorDataDao.kt  # Sensor CRUD operations
│   │   │   │   │   │   │   │   └── ProductDao.kt     # Product CRUD operations
│   │   │   │   │   │   │   └── entity/               # Database Entities
│   │   │   │   │   │   │       ├── UserEntity.kt
│   │   │   │   │   │   │       ├── SensorDataEntity.kt
│   │   │   │   │   │   │       └── ProductEntity.kt
│   │   │   │   │   │   │
│   │   │   │   │   │   └── preferences/
│   │   │   │   │   │       ├── UserPreferences.kt         # User session preferences
│   │   │   │   │   │       ├── AppSettingsPreferences.kt  # App settings storage
│   │   │   │   │   │       └── CachePreferences.kt        # Cache management
│   │   │   │   │   │
│   │   │   │   │   ├── remote/       # API Data Models & Services
│   │   │   │   │   │   ├── dto/                  # Data Transfer Objects
│   │   │   │   │   │   │   ├── request/          # Request models (untuk POST/PUT)
│   │   │   │   │   │   │   │   ├── LoginRequest.kt
│   │   │   │   │   │   │   │   ├── RegisterRequest.kt
│   │   │   │   │   │   │   │   └── ProductOrderRequest.kt
│   │   │   │   │   │   │   │
│   │   │   │   │   │   │   └── response/         # Response models (dari API)
│   │   │   │   │   │   │       ├── LoginResponse.kt
│   │   │   │   │   │   │       ├── UserResponse.kt
│   │   │   │   │   │   │       ├── SensorDataResponse.kt
│   │   │   │   │   │   │       ├── ProductResponse.kt
│   │   │   │   │   │   │       └── ApiErrorResponse.kt
│   │   │   │   │   │   │
│   │   │   │   │   │   └── service/  # API Service Definitions
│   │   │   │   │   │       ├── AuthApiService.kt
│   │   │   │   │   │       ├── SensorApiService.kt
│   │   │   │   │   │       ├── ProductApiService.kt
│   │   │   │   │   │       └── ApiClient.kt (Retrofit instance)
│   │   │   │   │   │
│   │   │   │   │   └── repository/   # Business Logic & Data Mediation
│   │   │   │   │       ├── UserRepository.kt         # User login/register logic
│   │   │   │   │       ├── SensorRepository.kt       # Real-time sensor data
│   │   │   │   │       ├── ProductRepository.kt      # Product catalog logic
│   │   │   │   │       ├── OrderRepository.kt        # Order/transaction logic
│   │   │   │   │       └── SessionRepository.kt      # Session & token management
│   │   │   │   │
│   │   │   │   ├── ui/               # 🎨 PRESENTATION LAYER (User Interface)
│   │   │   │   │   │
│   │   │   │   │   ├── auth/         # 👤 AUTHENTICATION MODULE (Azfa's Domain)
│   │   │   │   │   │   ├── login/
│   │   │   │   │   │   │   ├── LoginActivity.kt           # Login entry point
│   │   │   │   │   │   │   ├── LoginFragment.kt          # Login UI
│   │   │   │   │   │   │   ├── LoginViewModel.kt         # Login state & logic
│   │   │   │   │   │   │   ├── LoginUiState.kt           # UI state data class
│   │   │   │   │   │   │   └── [See layout: fragment_login.xml]
│   │   │   │   │   │   │
│   │   │   │   │   │   ├── register/
│   │   │   │   │   │   │   ├── RegisterActivity.kt
│   │   │   │   │   │   │   ├── RegisterFragment.kt
│   │   │   │   │   │   │   ├── RegisterViewModel.kt
│   │   │   │   │   │   │   ├── RegisterUiState.kt
│   │   │   │   │   │   │   └── [See layout: fragment_register.xml]
│   │   │   │   │   │   │
│   │   │   │   │   │   └── onboarding/
│   │   │   │   │   │       ├── OnboardingActivity.kt
│   │   │   │   │   │       ├── OnboardingFragment.kt
│   │   │   │   │   │       ├── OnboardingViewModel.kt
│   │   │   │   │   │       └── OnboardingPagerAdapter.kt
│   │   │   │   │   │
│   │   │   │   │   ├── dashboard/    # 📊 IOT DASHBOARD MODULE (Adam's Domain)
│   │   │   │   │   │   ├── home/
│   │   │   │   │   │   │   ├── HomeActivity.kt           # Dashboard container
│   │   │   │   │   │   │   ├── HomeFragment.kt          # Main dashboard UI
│   │   │   │   │   │   │   ├── HomeViewModel.kt         # Dashboard state & logic
│   │   │   │   │   │   │   ├── HomeUiState.kt
│   │   │   │   │   │   │   ├── SensorDataAdapter.kt     # RecyclerView adapter
│   │   │   │   │   │   │   └── [See layout: fragment_home.xml]
│   │   │   │   │   │   │
│   │   │   │   │   │   ├── siram/
│   │   │   │   │   │   │   ├── SiramActivity.kt
│   │   │   │   │   │   │   ├── SiramFragment.kt         # Water control UI
│   │   │   │   │   │   │   ├── SiramViewModel.kt        # Actuator control logic
│   │   │   │   │   │   │   ├── SiramUiState.kt
│   │   │   │   │   │   │   └── [See layout: fragment_siram.xml]
│   │   │   │   │   │   │
│   │   │   │   │   │   └── components/
│   │   │   │   │   │       ├── SensorCard.kt            # Reusable sensor display
│   │   │   │   │   │       ├── ActuatorToggle.kt        # Reusable control button
│   │   │   │   │   │       └── RealTimeChart.kt         # Data visualization
│   │   │   │   │   │
│   │   │   │   │   ├── farming/      # 🌱 SMART FARMING MODULE (Evan's Domain)
│   │   │   │   │   │   ├── guide/
│   │   │   │   │   │   │   ├── GuideActivity.kt
│   │   │   │   │   │   │   ├── GuideFragment.kt         # Plant encyclopedia UI
│   │   │   │   │   │   │   ├── GuideViewModel.kt        # Guide data logic
│   │   │   │   │   │   │   ├── GuideUiState.kt
│   │   │   │   │   │   │   ├── GuideAdapter.kt          # Plant list adapter
│   │   │   │   │   │   │   └── [See layout: fragment_guide.xml]
│   │   │   │   │   │   │
│   │   │   │   │   │   ├── disease/
│   │   │   │   │   │   │   ├── DiseaseDetectionActivity.kt
│   │   │   │   │   │   │   ├── DiseaseDetectionFragment.kt  # Camera & ML UI
│   │   │   │   │   │   │   ├── DiseaseDetectionViewModel.kt # ML logic
│   │   │   │   │   │   │   ├── DiseaseUiState.kt
│   │   │   │   │   │   │   ├── CameraManager.kt             # CameraX wrapper
│   │   │   │   │   │   │   ├── MLKitAnalyzer.kt             # ML Kit integration
│   │   │   │   │   │   │   └── [See layout: fragment_disease_detection.xml]
│   │   │   │   │   │   │
│   │   │   │   │   │   └── components/
│   │   │   │   │   │       ├── PlantCard.kt             # Plant info display
│   │   │   │   │   │       ├── DiseaseResultCard.kt     # Detection result UI
│   │   │   │   │   │       └── CareInstructionCard.kt   # Care tips display
│   │   │   │   │   │
│   │   │   │   │   ├── commerce/     # 💼 E-COMMERCE MODULE (Joya's Domain)
│   │   │   │   │   │   ├── marketplace/
│   │   │   │   │   │   │   ├── MarketplaceActivity.kt
│   │   │   │   │   │   │   ├── MarketplaceFragment.kt   # Product list UI
│   │   │   │   │   │   │   ├── MarketplaceViewModel.kt  # Product listing logic
│   │   │   │   │   │   │   ├── MarketplaceUiState.kt
│   │   │   │   │   │   │   ├── ProductListAdapter.kt    # Product grid/list adapter
│   │   │   │   │   │   │   └── [See layout: fragment_marketplace.xml]
│   │   │   │   │   │   │
│   │   │   │   │   │   ├── detail/
│   │   │   │   │   │   │   ├── DetailActivity.kt
│   │   │   │   │   │   │   ├── DetailFragment.kt        # Product detail UI
│   │   │   │   │   │   │   ├── DetailViewModel.kt       # Detail & review logic
│   │   │   │   │   │   │   ├── DetailUiState.kt
│   │   │   │   │   │   │   ├── ReviewAdapter.kt         # Review list display
│   │   │   │   │   │   │   └── [See layout: fragment_detail_product.xml]
│   │   │   │   │   │   │
│   │   │   │   │   │   ├── cart/
│   │   │   │   │   │   │   ├── CartActivity.kt
│   │   │   │   │   │   │   ├── CartFragment.kt          # Shopping cart UI
│   │   │   │   │   │   │   ├── CartViewModel.kt         # Cart operations logic
│   │   │   │   │   │   │   ├── CartUiState.kt
│   │   │   │   │   │   │   ├── CartItemAdapter.kt       # Cart items display
│   │   │   │   │   │   │   └── [See layout: fragment_cart.xml]
│   │   │   │   │   │   │
│   │   │   │   │   │   ├── profile/
│   │   │   │   │   │   │   ├── ProfileActivity.kt
│   │   │   │   │   │   │   ├── ProfileFragment.kt       # User profile UI
│   │   │   │   │   │   │   ├── ProfileViewModel.kt      # Profile & settings logic
│   │   │   │   │   │   │   ├── ProfileUiState.kt
│   │   │   │   │   │   │   ├── OrderHistoryAdapter.kt   # Purchase history display
│   │   │   │   │   │   │   └── [See layout: fragment_profile.xml]
│   │   │   │   │   │   │
│   │   │   │   │   │   └── components/
│   │   │   │   │   │       ├── ProductCard.kt           # Product preview card
│   │   │   │   │   │       ├── PriceDisplay.kt          # Price formatting
│   │   │   │   │   │       ├── CartBadge.kt             # Cart notification badge
│   │   │   │   │   │       └── RatingWidget.kt          # Star rating display
│   │   │   │   │   │
│   │   │   │   │   └── components/   # 🎨 GLOBAL REUSABLE UI KIT
│   │   │   │   │       ├── TanamiButton.kt              # Custom primary button
│   │   │   │   │       ├── TanamiOutlinedButton.kt      # Outlined button variant
│   │   │   │   │       ├── TanamiTextField.kt           # Custom input field
│   │   │   │   │       ├── TanamiCard.kt                # Rounded card container
│   │   │   │   │       ├── TanamiDivider.kt             # Styled divider
│   │   │   │   │       ├─�� LoadingIndicator.kt          # Loading animation
│   │   │   │   │       ├── EmptyStateView.kt            # Empty state UI
│   │   │   │   │       ├── ErrorSnackbar.kt             # Error notification
│   │   │   │   │       └── ProgressIndicator.kt         # Progress tracking
│   │   │   │   │
│   │   │   │   └── di/               # 🔧 DEPENDENCY INJECTION (DI Modules)
│   │   │   │       ├── NetworkModule.kt         # Retrofit & HTTP client setup
│   │   │   │       ├── RepositoryModule.kt      # Repository instance binding
│   │   │   │       ├── ViewModelModule.kt       # ViewModel factory setup
│   │   │   │       ├── DatabaseModule.kt        # Room database binding
│   │   │   │       ├── PreferencesModule.kt     # SharedPreferences/DataStore
│   │   │   │       └── AppModule.kt             # App-level DI bindings
│   │   │   │
│   │   │   └── res/                  # 📦 RESOURCE FILES
│   │   │       ├── layout/           # XML Layout Files
│   │   │       │   ├── activity_main.xml
│   │   │       │   ├── activity_login.xml
│   │   │       │   ├── fragment_login.xml
│   │   │       │   ├── fragment_register.xml
│   │   │       │   ├── fragment_home.xml
│   │   │       │   ├── fragment_siram.xml
│   │   │       │   ├── fragment_guide.xml
│   │   │       │   ├── fragment_disease_detection.xml
│   │   │       │   ├── fragment_marketplace.xml
│   │   │       │   ├── fragment_detail_product.xml
│   │   │       │   ├── fragment_cart.xml
│   │   │       │   ├── fragment_profile.xml
│   │   │       │   ├── item_sensor_card.xml
│   │   │       │   ├── item_product.xml
│   │   │       │   ├── item_cart_item.xml
│   │   │       │   └── ...other layouts
│   │   │       │
│   │   │       ├── drawable/         # Images, Vectors, Drawables
│   │   │       │   ├── ic_launcher.xml          # App icon
│   │   │       │   ├── ic_home.xml              # Navigation icons
│   │   │       │   ├── ic_dashboard.xml
│   │   │       │   ├── ic_shopping.xml
│   │   │       │   ├── ic_profile.xml
│   │   │       │   ├── btn_background.xml       # Button backgrounds
│   │   │       │   ├── card_background.xml      # Card shape
│   │   │       │   └── ...other drawables
│   │   │       │
│   │   │       ├── values/           # Value Resources
│   │   │       │   ├── strings.xml   # 📝 Text strings (Multi-language support)
│   │   │       │   │                    Contoh:
│   │   │       │   │                    <string name="app_name">Tanami</string>
│   │   │       │   │                    <string name="login_title">Masuk ke Akun</string>
│   │   │       │   │
│   │   │       │   ├── colors.xml    # 🎨 Color Palette
│   │   │       │   │                    Contoh:
│   │   │       │   │                    <color name="primary_green">#4CAF50</color>
│   │   │       │   │                    <color name="secondary_teal">#009688</color>
│   │   │       │   │                    <color name="error_red">#F44336</color>
│   │   │       │   │
│   │   │       │   ├── dimens.xml    # 📏 Spacing & Typography (USE THESE!)
│   │   │       │   │                    Contoh:
│   │   │       │   │                    <dimen name="spacing_xsmall">4dp</dimen>
│   │   │       │   │                    <dimen name="spacing_small">8dp</dimen>
│   │   │       │   │                    <dimen name="spacing_medium">16dp</dimen>
│   │   │       │   │                    <dimen name="spacing_large">24dp</dimen>
│   │   │       │   │                    <dimen name="text_small">12sp</dimen>
│   │   │       │   │                    <dimen name="text_body">14sp</dimen>
│   │   │       │   │                    <dimen name="text_title">18sp</dimen>
│   │   │       │   │                    <dimen name="text_heading">24sp</dimen>
│   │   │       │   │
│   │   │       │   ├── themes.xml    # 🎭 App Theme
│   │   │       │   │                    Material Design 3 theme definition
│   │   │       │   │
│   │   │       │   └── attrs.xml     # Custom attributes
│   │   │       │
│   │   │       ├── values-night/     # 🌙 Dark Mode Resources
│   │   │       │   ├── colors.xml    # Dark mode colors
│   │   │       │   └── themes.xml    # Dark mode theme
│   │   │       │
│   │   │       └── menu/             # 📋 Menu Definitions
│   │   │           ├── bottom_nav_menu.xml     # Bottom navigation menu
│   │   │           ├── overflow_menu.xml       # Overflow menu items
│   │   │           └── ...other menus
│   │   │
│   │   ├── test/                     # 🧪 Unit Tests
│   │   │   └── java/...
│   │   │       ├── UserRepositoryTest.kt
│   │   │       ├── LoginViewModelTest.kt
│   │   │       └── ...other unit tests
│   │   │
│   │   └── androidTest/              # 🧪 Instrumented Tests (UI Testing)
│   │       └── androidTest/...
│   │           ├── LoginFragmentTest.kt
│   │           ├── DashboardFragmentTest.kt
│   │           └── ...other UI tests
│   │
│   └── build.gradle.kts              # 🔨 Module-level Build Configuration
│       ├── plugins                   # Apply gradle plugins
│       ├── android { ... }           # Android SDK configuration
│       ├── dependencies { ... }      # Module dependencies
│       └── tasks { ... }             # Custom build tasks
│
├── .idea/                            # 🔨 Android Studio Configuration (DO NOT COMMIT)
│   ├── .gitignore                    # Ignore IDE cache files
│   │   /shelf/
│   │   /workspace.xml
│   │
│   ├── gradle.xml                    # Gradle settings (test runner, module paths)
│   ├── misc.xml                      # Project settings (JDK 21, project type)
│   ├── compiler.xml                  # Compiler bytecode target
│   ├── vcs.xml                       # VCS Configuration (Git mapping)
│   ├── AndroidProjectSystem.xml      # Android project system settings
│   ├── appInsightsSettings.xml       # Firebase Crashlytics settings
│   ├── deploymentTargetSelector.xml  # Deployment target configuration
│   └── runConfigurations.xml         # Run configuration settings
│
├── .gitignore                        # 📝 Git Ignore Rules (IDE & Build artifacts)
│   ├── *.iml                         # Ignore IDE project files
│   ├── .gradle/                      # Ignore gradle cache
│   ├── /.idea/                       # Ignore IDE settings
│   ├── /build/                       # Ignore build output
│   ├── /local.properties             # Ignore local config
│   └── ...other ignored files
│
├── build.gradle.kts                  # 🔨 Top-level Build Configuration
│   ├── plugins                       # Define global plugins
│   └── tasks { ... }                 # Global build tasks
│
├── settings.gradle.kts               # ⚙️ Gradle Settings & Module Definition
│   ├── pluginManagement {}           # Plugin repositories
│   ├── dependencyResolutionManagement {} # Dependency repositories
│   └── include(":app")               # Include app module
│
├── gradle.properties                 # 📋 Global Gradle Properties
│   ├── org.gradle.jvmargs            # JVM memory allocation
│   ├── kotlin.code.style             # Kotlin style preference
│   └── ...other gradle properties
│
├── gradlew                           # 🚀 Gradle Wrapper (Linux/Mac)
│   └── Gradle wrapper script untuk build tanpa install gradle
│
├── gradlew.bat                       # 🚀 Gradle Wrapper (Windows)
│   └── Gradle wrapper script untuk Windows
│
└── README.md                         # 📚 Project Documentation

```

---

### 📖 PENJELASAN SETIAP LAYER

#### **🏗️ CORE LAYER** - Fondasi Publik (Shared Infrastructure)

| Folder            | File                     | Fungsi                                                      | Digunakan Oleh                         |
| ----------------- | ------------------------ | ----------------------------------------------------------- | -------------------------------------- |
| `core/network/` | `RetrofitClient.kt`    | Membuat Retrofit instance dengan base URL, timeout, logging | Semua Repository                       |
|                   | `ApiService.kt`        | Interface API endpoints (abstract)                          | Setiap service (Auth, Sensor, Product) |
|                   | `TokenInterceptor.kt`  | Menambahkan JWT token ke setiap request                     | Semua HTTP requests                    |
|                   | `HeaderInterceptor.kt` | Menambahkan custom headers (User-Agent, API-Key)            | Semua HTTP requests                    |
|                   | `NetworkConstants.kt`  | Base URL, endpoints, timeout values                         | Network module                         |
| `core/utils/`   | `Constants.kt`         | App-wide constants (regex, status codes, error messages)    | Semua module                           |
|                   | `Extensions.kt`        | Helper functions (View visibility, String formatting)       | Semua UI layer                         |
|                   | `DateFormatter.kt`     | Date parsing & formatting utilities                         | Repository, ViewModel                  |
|                   | `Logger.kt`            | Logging untuk debug & tracking                              | Development only                       |
|                   | `Validators.kt`        | Email, password, phone validation                           | Auth & registration                    |
| `core/base/`    | `BaseActivity.kt`      | Lifecycle management, permission handling, theme setup      | Semua Activity                         |
|                   | `BaseFragment.kt`      | Fragment setup, view binding, common UI logic               | Semua Fragment                         |
|                   | `BaseViewModel.kt`     | State management, error handling, common observers          | Semua ViewModel                        |
|                   | `BaseRepository.kt`    | Database transaction handling, error mapping                | Data repositories                      |

---

#### **💾 DATA LAYER** - Single Source of Truth

| Folder                        | File                          | Fungsi                                                   | Pemilik        |
| ----------------------------- | ----------------------------- | -------------------------------------------------------- | -------------- |
| `data/local/database/`      | `TanamiDatabase.kt`         | Room database instance, migration management             | Adam           |
|                               | `UserDao.kt`                | Database operations: getUser(), saveUser(), deleteUser() | Adam           |
|                               | `SensorDataDao.kt`          | Sensor data CRUD: insertData(), queryByTime()            | Adam           |
|                               | `ProductDao.kt`             | Product CRUD: getProducts(), filterByCategory()          | Joya           |
|                               | `UserEntity.kt`             | Database model untuk users table                         | Adam           |
|                               | `SensorDataEntity.kt`       | Database model untuk sensor readings                     | Adam           |
|                               | `ProductEntity.kt`          | Database model untuk products table                      | Joya           |
| `data/local/preferences/`   | `UserPreferences.kt`        | Store: userId, userToken, lastLogin                      | Adam           |
|                               | `AppSettingsPreferences.kt` | Store: theme, language, notifications preference         | Adam           |
|                               | `CachePreferences.kt`       | Manage: cache expiry, cache data                         | Adam           |
| `data/remote/dto/request/`  | `LoginRequest.kt`           | { email: String, password: String }                      | Azfa           |
|                               | `RegisterRequest.kt`        | { name, email, password, phone }                         | Azfa           |
|                               | `ProductOrderRequest.kt`    | { productId, quantity, address }                         | Joya           |
| `data/remote/dto/response/` | `LoginResponse.kt`          | { token, user: UserData, expiresIn }                     | Azfa           |
|                               | `UserResponse.kt`           | { id, name, email, phone, createdAt }                    | Azfa           |
|                               | `SensorDataResponse.kt`     | { sensorId, value, unit, timestamp }                     | Adam           |
|                               | `ProductResponse.kt`        | { id, name, price, image, category, rating }             | Joya           |
|                               | `ApiErrorResponse.kt`       | { status, message, errors[] }                            | Semua          |
| `data/remote/service/`      | `AuthApiService.kt`         | Retrofit interface: login(), register(), logout()        | Azfa           |
|                               | `SensorApiService.kt`       | Interface: getSensorData(), getSensorStatus()            | Adam           |
|                               | `ProductApiService.kt`      | Interface: getProducts(), searchProducts()               | Joya           |
|                               | `ApiClient.kt`              | Retrofit instance holder (singleton)                     | Network module |
| `data/repository/`          | `UserRepository.kt`         | Combine: local user cache + API login flow               | Azfa           |
|                               | `SensorRepository.kt`       | Real-time sensor data fetch & cache logic                | Adam           |
|                               | `ProductRepository.kt`      | Product fetching, filtering, caching logic               | Joya           |
|                               | `OrderRepository.kt`        | Order creation, history retrieval                        | Joya           |
|                               | `SessionRepository.kt`      | Token refresh, session timeout handling                  | Adam           |

---

#### **🎨 UI LAYER** - Presentation & User Interaction

##### **👤 AUTH MODULE (AZFA'S DOMAIN)**

| Folder                  | File                          | Fungsi                                                | Status           |
| ----------------------- | ----------------------------- | ----------------------------------------------------- | ---------------- |
| `ui/auth/login/`      | `LoginActivity.kt`          | Container activity, navigation setup                  | Core entry point |
|                         | `LoginFragment.kt`          | Login form UI (email, password, forgot password link) | Main UI          |
|                         | `LoginViewModel.kt`         | Handle login logic, validation, state management      | Business logic   |
|                         | `LoginUiState.kt`           | Data class: { isLoading, error, success }             | State holder     |
|                         | `fragment_login.xml`        | Layout: TanamiTextField x2, TanamiButton, signup link | UI layout        |
| `ui/auth/register/`   | `RegisterActivity.kt`       | Container activity                                    | Entry point      |
|                         | `RegisterFragment.kt`       | Registration form (name, email, password, phone)      | Form UI          |
|                         | `RegisterViewModel.kt`      | Validation, API call, error handling                  | Business logic   |
|                         | `RegisterUiState.kt`        | { isLoading, validationErrors, success }              | State            |
|                         | `fragment_register.xml`     | Layout with 4 input fields                            | UI layout        |
| `ui/auth/onboarding/` | `OnboardingActivity.kt`     | Onboarding container                                  | Entry point      |
|                         | `OnboardingFragment.kt`     | Individual onboarding page                            | Page UI          |
|                         | `OnboardingViewModel.kt`    | Manage onboarding flow                                | Flow logic       |
|                         | `OnboardingPagerAdapter.kt` | ViewPager adapter for multiple pages                  | Adapter          |

---

##### **📊 DASHBOARD MODULE (ADAM'S DOMAIN)**

| Folder                       | File                     | Fungsi                                            | Status         |
| ---------------------------- | ------------------------ | ------------------------------------------------- | -------------- |
| `ui/dashboard/home/`       | `HomeActivity.kt`      | Dashboard container, bottom nav setup             | Main container |
|                              | `HomeFragment.kt`      | Display sensors data grid, real-time updates      | Main dashboard |
|                              | `HomeViewModel.kt`     | Fetch sensor data, manage real-time subscriptions | Data logic     |
|                              | `HomeUiState.kt`       | { sensors: List, isLoading, error }               | State          |
|                              | `SensorDataAdapter.kt` | RecyclerView adapter untuk sensor cards           | List display   |
|                              | `fragment_home.xml`    | Layout: RecyclerView + RefreshLayout              | UI layout      |
| `ui/dashboard/siram/`      | `SiramActivity.kt`     | Watering control container                        | Activity       |
|                              | `SiramFragment.kt`     | Actuator controls, manual/auto toggle             | Control UI     |
|                              | `SiramViewModel.kt`    | Send control commands, monitor status             | Control logic  |
|                              | `SiramUiState.kt`      | { isOn, duration, lastActivated }                 | State          |
|                              | `fragment_siram.xml`   | Layout: Toggle, duration picker, status display   | UI layout      |
| `ui/dashboard/components/` | `SensorCard.kt`        | Reusable sensor display component                 | Composable     |
|                              | `ActuatorToggle.kt`    | On/off toggle for actuators                       | Control        |
|                              | `RealTimeChart.kt`     | Line chart untuk sensor trend                     | Visualization  |

---

##### **🌱 FARMING MODULE (EVAN'S DOMAIN)**

| Folder                     | File                               | Fungsi                                    | Status           |
| -------------------------- | ---------------------------------- | ----------------------------------------- | ---------------- |
| `ui/farming/guide/`      | `GuideActivity.kt`               | Plant encyclopedia container              | Entry point      |
|                            | `GuideFragment.kt`               | Display plant encyclopedia, search/filter | List UI          |
|                            | `GuideViewModel.kt`              | Fetch plant data, manage filters          | Data logic       |
|                            | `GuideUiState.kt`                | { plants: List, filters, isLoading }      | State            |
|                            | `GuideAdapter.kt`                | RecyclerView adapter untuk plant list     | List display     |
|                            | `fragment_guide.xml`             | Layout: SearchBar + RecyclerView          | UI layout        |
| `ui/farming/disease/`    | `DiseaseDetectionActivity.kt`    | Disease detection container               | Entry point      |
|                            | `DiseaseDetectionFragment.kt`    | Camera preview + detection result         | Detection UI     |
|                            | `DiseaseDetectionViewModel.kt`   | Image analysis, ML Kit integration        | ML logic         |
|                            | `DiseaseUiState.kt`              | { result, confidence, recommendations }   | Detection result |
|                            | `CameraManager.kt`               | CameraX setup, frame capture              | Camera control   |
|                            | `MLKitAnalyzer.kt`               | ML Kit disease detection integration      | Image analysis   |
|                            | `fragment_disease_detection.xml` | Layout: PreviewView + result overlay      | UI layout        |
| `ui/farming/components/` | `PlantCard.kt`                   | Plant information display                 | Component        |
|                            | `DiseaseResultCard.kt`           | Disease detection result display          | Result display   |
|                            | `CareInstructionCard.kt`         | Plant care tips display                   | Tips display     |

---

##### **💼 E-COMMERCE MODULE (JOYA'S DOMAIN)**

| Folder                       | File                            | Fungsi                                            | Status        |
| ---------------------------- | ------------------------------- | ------------------------------------------------- | ------------- |
| `ui/commerce/marketplace/` | `MarketplaceActivity.kt`      | Product listing container                         | Entry point   |
|                              | `MarketplaceFragment.kt`      | Product grid/list, filters, search                | Product list  |
|                              | `MarketplaceViewModel.kt`     | Fetch products, manage filters & sorting          | List logic    |
|                              | `MarketplaceUiState.kt`       | { products: List, filters, isLoading }            | State         |
|                              | `ProductListAdapter.kt`       | RecyclerView/GridView adapter                     | Grid display  |
|                              | `fragment_marketplace.xml`    | Layout: Toolbar + GridView + filters              | UI layout     |
| `ui/commerce/detail/`      | `DetailActivity.kt`           | Product detail container                          | Entry point   |
|                              | `DetailFragment.kt`           | Product image, description, reviews, add to cart  | Detail view   |
|                              | `DetailViewModel.kt`          | Fetch product details, manage quantity            | Detail logic  |
|                              | `DetailUiState.kt`            | { product, quantity, reviews, isLoading }         | State         |
|                              | `ReviewAdapter.kt`            | RecyclerView adapter untuk reviews                | Review list   |
|                              | `fragment_detail_product.xml` | Layout: ViewPager + details + reviews             | UI layout     |
| `ui/commerce/cart/`        | `CartActivity.kt`             | Shopping cart container                           | Entry point   |
|                              | `CartFragment.kt`             | Cart items list, quantity edit, checkout          | Cart view     |
|                              | `CartViewModel.kt`            | Add/remove items, calculate total, checkout logic | Cart logic    |
|                              | `CartUiState.kt`              | { items: List, total, isLoading }                 | State         |
|                              | `CartItemAdapter.kt`          | RecyclerView adapter untuk cart items             | Item list     |
|                              | `fragment_cart.xml`           | Layout: Items list + total + checkout button      | UI layout     |
| `ui/commerce/profile/`     | `ProfileActivity.kt`          | User profile container                            | Entry point   |
|                              | `ProfileFragment.kt`          | User info, order history, settings, logout        | Profile view  |
|                              | `ProfileViewModel.kt`         | Update profile, fetch orders, handle logout       | Profile logic |
|                              | `ProfileUiState.kt`           | { user, orders, isEditing, isLoading }            | State         |
|                              | `OrderHistoryAdapter.kt`      | RecyclerView adapter untuk order history          | Order list    |
|                              | `fragment_profile.xml`        | Layout: User info + order history + settings      | UI layout     |
| `ui/commerce/components/`  | `ProductCard.kt`              | Reusable product preview                          | Product card  |
|                              | `PriceDisplay.kt`             | Formatted price with currency                     | Price display |
|                              | `CartBadge.kt`                | Cart count badge                                  | Badge         |
|                              | `RatingWidget.kt`             | Star rating display & interaction                 | Rating        |

---

##### **🎨 GLOBAL COMPONENTS (Semua Developer Gunakan)**

| File                        | Fungsi                                        | Usage                     |
| --------------------------- | --------------------------------------------- | ------------------------- |
| `TanamiButton.kt`         | Custom primary button dengan standard styling | Semua action buttons      |
| `TanamiOutlinedButton.kt` | Secondary button variant                      | Cancel, secondary actions |
| `TanamiTextField.kt`      | Custom input field dengan validation support  | Semua form inputs         |
| `TanamiCard.kt`           | Rounded card container dengan shadow          | Container components      |
| `TanamiDivider.kt`        | Styled divider line                           | List separators           |
| `LoadingIndicator.kt`     | Spinning progress animation                   | Loading states            |
| `EmptyStateView.kt`       | Empty list state display                      | No data screens           |
| `ErrorSnackbar.kt`        | Error notification display                    | Error feedback            |
| `ProgressIndicator.kt`    | Progress bar/circle                           | Upload/download progress  |

---

#### **🔧 DI MODULE** - Dependency Injection

| File                     | Fungsi                                    | Setup                 |
| ------------------------ | ----------------------------------------- | --------------------- |
| `NetworkModule.kt`     | Provide Retrofit instance, OkHttp client  | @Module, @Provides    |
| `RepositoryModule.kt`  | Bind repository implementations           | @Binds, @Singleton    |
| `ViewModelModule.kt`   | Setup ViewModel factory (jika pakai Hilt) | @Module, @Provides    |
| `DatabaseModule.kt`    | Provide Room database instance            | @Provides, @Singleton |
| `PreferencesModule.kt` | Provide SharedPreferences/DataStore       | @Provides, @Singleton |
| `AppModule.kt`         | App-level bindings, common utilities      | @Module               |

---

#### **📦 RES LAYER** - Resource Files

| Folder                     | Fungsi                                       | Contoh                                        |
| -------------------------- | -------------------------------------------- | --------------------------------------------- |
| `res/layout/`            | XML layout files untuk Activity & Fragment   | `fragment_login.xml`, `item_product.xml`  |
| `res/drawable/`          | Images, vectors, drawable resources          | App icons, button backgrounds                 |
| `res/values/strings.xml` | Multi-language text resources                | `<string name="login_title">Masuk</string>` |
| `res/values/colors.xml`  | Color palette definition                     | `<color name="primary">#4CAF50</color>`     |
| `res/values/dimens.xml`  | **WAJIB GUNAKAN** spacing & typography | `<dimen name="spacing_medium">16dp</dimen>` |
| `res/values/themes.xml`  | Material Design 3 theme setup                | Color, typography, shape                      |
| `res/values-night/`      | Dark mode color overrides                    | Alternative dark colors                       |
| `res/menu/`              | Menu definitions                             | Bottom navigation, overflow menu              |

---

#### **⚙️ GRADLE LAYER** - Build Configuration

| File                                         | Fungsi                                  | Maintained By |
| -------------------------------------------- | --------------------------------------- | ------------- |
| `build.gradle.kts` (root)                  | Global plugin definitions               | Adam          |
| `build.gradle.kts` (app)                   | App module dependencies, build config   | Adam          |
| `settings.gradle.kts`                      | Module definitions, plugin repositories | Adam          |
| `gradle.properties`                        | Global gradle properties                | Adam          |
| `gradle/libs.versions.toml`                | Centralized dependency versions         | Adam          |
| `gradle/wrapper/gradle-wrapper.properties` | Gradle version (9.3.1)                  | Adam          |

---

---

## 👥 3. JOBDESK DEVELOPER & TASK ASSIGNMENT

### 🎯 ADAM - Dashboard & IoT Control Lead

**Posisi**: Senior Android Developer (Infrastructure & IoT)
**Modul Utama**: `ui/dashboard/` + `core/network/` + `data/`
**Fokus**: Real-time data handling, sensor integration, actuator control

#### Tanggung Jawab (Responsibility)

```
┌─────────────────────────────────────────────────────────────┐
│                    ADAM'S RESPONSIBILITY                     │
│─────────────────────────────────────────────────────────────│
│ 1. INFRASTRUCTURE & SETUP                                   │
│    ├─ Setup Retrofit + OkHttp configuration (NetworkModule) │
│    ├─ Create API interceptors (token, headers)              │
│    ├─ Setup Room database architecture                      │
│    ├─ Initialize Hilt DI container                          │
│    └─ Create base classes (BaseActivity, BaseFragment)      │
│                                                              │
│ 2. DATA LAYER                                               │
│    ├─ Database design & migration (Room)                    │
│    ├─ API service interfaces definition (ApiService.kt)     │
│    ├─ Create repositories:                                  │
│    │  ├─ SensorRepository (real-time data)                 │
│    │  ├─ SessionRepository (token management)              │
│    │  └─ UserRepository (basic user operations)            │
│    └─ DTO/Entity models untuk semua modules                │
│                                                              │
│ 3. DASHBOARD MODULE                                         │
│    ├─ HomeFragment & HomeViewModel                         │
│    │  ├─ Real-time sensor data display                     │
│    │  ├─ Auto-refresh mechanism                            │
│    │  ├─ WebSocket integration (jika ada)                  │
│    │  └─ Data visualization (charts)                       │
│    │                                                         │
│    ├─ SiramFragment & SiramViewModel                        │
│    │  ├─ Actuator control UI                               │
│    │  ├─ Manual/auto toggle logic                          │
│    │  ├─ Control command send to API                       │
│    │  └─ Status monitoring                                 │
│    │                                                         │
│    └─ Dashboard components (SensorCard, ActuatorToggle)    │
│                                                              │
│ 4. QUALITY ASSURANCE                                        │
│    ├─ Write unit tests untuk repositories                  │
│    ├─ Write instrumented tests untuk dashboard             │
│    └─ Monitor API performance & logs                       │
│                                                              │
│ 5. TEAM SUPPORT                                             │
│    ├─ Code review untuk data layer changes                 │
│    ├─ Assist other developers dengan API integration       │
│    └─ Maintain core utilities & helpers                    │
└─────────────────────────────────────────────────────────────┘
```

#### File yang Dikerjakan

```
core/network/
├─ RetrofitClient.kt          ✅ Create
├─ ApiService.kt               ✅ Create
├─ TokenInterceptor.kt         ✅ Create
├─ HeaderInterceptor.kt        ✅ Create
└─ NetworkConstants.kt         ✅ Create

core/base/
├─ BaseActivity.kt             ✅ Create
├─ BaseFragment.kt             ✅ Create
├─ BaseViewModel.kt            ✅ Create
└─ BaseRepository.kt           ✅ Create

core/utils/
├─ Constants.kt                ✅ Create
├─ Extensions.kt               ✅ Create
└─ DateFormatter.kt            ✅ Create

data/local/
├─ TanamiDatabase.kt           ✅ Create
├─ UserDao.kt                  ✅ Create
├─ SensorDataDao.kt            ✅ Create
└─ *Preferences.kt             ✅ Create

data/remote/
├─ ApiClient.kt                ✅ Create
├─ SensorApiService.kt         ✅ Create
├─ All DTOs                    ✅ Create
└─ Request/Response models     ✅ Create

data/repository/
├─ SensorRepository.kt         ✅ Create
├─ SessionRepository.kt        ✅ Create
└─ UserRepository.kt           ✅ Create

di/
├─ NetworkModule.kt            ✅ Create
├─ DatabaseModule.kt           ✅ Create
├─ RepositoryModule.kt         ✅ Create
└─ AppModule.kt                ✅ Create

ui/dashboard/home/
├─ HomeActivity.kt             ✅ Create
├─ HomeFragment.kt             ✅ Create
├─ HomeViewModel.kt            ✅ Create
├─ HomeUiState.kt              ✅ Create
├─ SensorDataAdapter.kt        ✅ Create
└─ fragment_home.xml           ✅ Create

ui/dashboard/siram/
├─ SiramActivity.kt            ✅ Create
├─ SiramFragment.kt            ✅ Create
├─ SiramViewModel.kt           ✅ Create
├─ SiramUiState.kt             ✅ Create
└─ fragment_siram.xml          ✅ Create

ui/dashboard/components/
├─ SensorCard.kt               ✅ Create
├─ ActuatorToggle.kt           ✅ Create
└─ RealTimeChart.kt            ✅ Create

ui/components/ (shared)
├─ TanamiButton.kt             ✅ Create
├─ TanamiTextField.kt          ✅ Create
├─ TanamiCard.kt               ✅ Create
├─ LoadingIndicator.kt         ✅ Create
└─ EmptyStateView.kt           ✅ Create
```

#### Key Metrics

- **Modules Owned**: 6 (core, data, dashboard, components, di, base)
- **Classes to Create**: ~40
- **Tests Required**: 15+ unit tests, 5+ UI tests
- **Deadline Impact**: Critical (foundational work)

---

### 🔐 AZFA - Authentication & Session Management Lead

**Posisi**: Senior Android Developer (Auth & Security)
**Modul Utama**: `ui/auth/` + `data/repository/UserRepository`
**Fokus**: Secure login/register, session management, token handling

#### Tanggung Jawab (Responsibility)

```
┌─────────────────────────────────────────────────────────────┐
│                    AZFA'S RESPONSIBILITY                     │
│─────────────────────────────────────────────────────────────│
│ 1. AUTHENTICATION FLOW                                      │
│    ├─ LoginFragment & LoginViewModel                        │
│    │  ├─ Email/password validation                          │
│    │  ├─ Call UserRepository.login()                        │
│    │  ├─ Handle success/error states                        │
│    │  └─ Redirect to dashboard on success                  │
│    │                                                         │
│    ├─ RegisterFragment & RegisterViewModel                  │
│    │  ├─ Form validation (name, email, password, phone)    │
│    │  ├─ Call UserRepository.register()                     │
│    │  ├─ Show confirmation message                          │
│    │  └─ Navigate to login after success                   │
│    │                                                         │
│    └─ OnboardingFlow                                        │
│       ├─ Create onboarding pages                            │
│       ├─ Manage page transitions                            │
│       └─ Store onboarding completion flag                  │
│                                                              │
│ 2. DATA LAYER (Auth-related)                                │
│    ├─ AuthApiService interface                              │
│    │  ├─ login(email, password): LoginResponse              │
│    │  ├─ register(...): RegisterResponse                    │
│    │  └─ logout(): Unit                                     │
│    │                                                         │
│    ├─ LoginRequest & RegisterRequest DTOs                   │
│    ├─ LoginResponse DTO                                     │
│    └─ UserRepository implementation                         │
│       ├─ login(): Store token in preferences                │
│       ├─ register(): Validate then call API                 │
│       ├─ logout(): Clear session                            │
│       └─ isLoggedIn(): Check token validity                 │
│                                                              │
│ 3. SESSION MANAGEMENT                                       │
│    ├─ Token storage in UserPreferences                      │
│    ├─ Token refresh on expiry (via SessionRepository)       │
│    ├─ Auto-logout on token expiration                       │
│    └─ Remember-me functionality (optional)                  │
│                                                              │
│ 4. SECURITY                                                 │
│    ├─ Password encryption (hashing)                         │
│    ├─ Input validation & sanitization                       │
│    ├─ Prevent SQL injection in local storage                │
│    └─ HTTPS-only API communication                          │
│                                                              │
│ 5. UI/UX                                                    │
│    ├─ Loading states during auth                            │
│    ├─ Error messages (invalid credentials, network)         │
│    ├─ Forgot password link (optional)                       │
│    └─ Sign up flow redirection                              │
│                                                              │
│ 6. TESTING                                                  │
│    ├─ Unit tests untuk LoginViewModel                       │
│    ├─ Unit tests untuk RegisterViewModel                    │
│    ├─ UI tests untuk LoginFragment                          │
│    └─ API mock tests untuk auth endpoints                   │
└─────────────────────────────────────────────────────────────┘
```

#### File yang Dikerjakan

```
ui/auth/login/
├─ LoginActivity.kt            ✅ Create
├─ LoginFragment.kt            ✅ Create
├─ LoginViewModel.kt           ✅ Create
├─ LoginUiState.kt             ✅ Create
└─ fragment_login.xml          ✅ Create

ui/auth/register/
├─ RegisterActivity.kt         ✅ Create
├─ RegisterFragment.kt         ✅ Create
├─ RegisterViewModel.kt        ✅ Create
├─ RegisterUiState.kt          ✅ Create
└─ fragment_register.xml       ✅ Create

ui/auth/onboarding/
├─ OnboardingActivity.kt       ✅ Create
├─ OnboardingFragment.kt       ✅ Create
├─ OnboardingViewModel.kt      ✅ Create
├─ OnboardingPagerAdapter.kt   ✅ Create
└─ fragment_onboarding_*.xml   ✅ Create

data/remote/service/
├─ AuthApiService.kt           ✅ Create
└─ LoginRequest/Response.kt     ✅ Create

data/repository/
├─ UserRepository.kt           ✅ Create
└─ (Koordinasi dengan Adam)

res/layout/ (Auth-related)
├─ activity_login.xml          ✅ Create
├─ activity_register.xml       ✅ Create
└─ activity_onboarding.xml     ✅ Create
```

#### Key Metrics

- **Modules Owned**: 1 (auth)
- **Classes to Create**: ~15
- **Tests Required**: 8+ unit tests, 3+ UI tests
- **Security Focus**: High (critical for user data)

---

### 🌱 EVAN - Smart Farming & Detection Lead

**Posisi**: Android Developer (ML & Computer Vision)
**Modul Utama**: `ui/farming/`
**Fokus**: Plant encyclopedia, disease detection via ML Kit, camera integration

#### Tanggung Jawab (Responsibility)

```
┌─────────────────────────────────────────────────────────────┐
│                    EVAN'S RESPONSIBILITY                     │
│─────────────────────────────────────────────────────────────│
│ 1. PLANT ENCYCLOPEDIA (GUIDE)                               │
│    ├─ GuideFragment & GuideViewModel                        │
│    │  ├─ Fetch plant data from API                          │
│    │  ├─ Display in RecyclerView grid                       │
│    │  ├─ Search/filter plants functionality                 │
│    │  └─ Click item → navigate to detail                    │
│    │                                                         │
│    ├─ Plant detail view                                     │
│    │  ├─ Plant image carousel                               │
│    │  ├─ Description & characteristics                      │
│    │  ├─ Growing requirements                               │
│    │  ├─ Care tips & best practices                         │
│    │  └─ Pest/disease info                                  │
│    │                                                         │
│    └─ Components (PlantCard, GuideAdapter)                  │
│       ├─ Reusable plant card layout                         │
│       └─ Smooth image loading                               │
│                                                              │
│ 2. DISEASE DETECTION (ML KIT)                               │
│    ├─ DiseaseDetectionFragment                              │
│    │  ├─ Camera preview (CameraX)                           │
│    │  ├─ Capture/upload functionality                       ��
│    │  └─ Real-time detection overlay                        │
│    │                                                         │
│    ├─ DiseaseDetectionViewModel                             │
│    │  ├─ ML Kit image analysis                              │
│    │  ├─ Confidence score handling                          │
│    │  ├─ Disease identification                             │
│    │  └─ Treatment recommendations                          │
│    │                                                         │
│    ├─ CameraManager utility                                 │
│    │  ├─ CameraX setup & configuration                      │
│    │  ├─ Frame capture for analysis                         │
│    │  └─ Camera permission handling                         │
│    │                                                         │
│    └─ MLKitAnalyzer                                         │
│       ├─ Image detection logic                              │
│       ├─ Disease classification                             │
│       └─ Confidence calculation                             │
│                                                              │
│ 3. DATA LAYER (Farming-related)                             │
│    ├─ PlantApiService interface                             │
│    │  ├─ getPlants(): List<Plant>                           │
│    │  ├─ searchPlants(query): List<Plant>                   │
│    │  └─ getPlantDetails(id): Plant                         │
│    │                                                         │
│    ├─ PlantResponse & PlantEntity DTOs                      │
│    │                                                         │
│    └─ PlantRepository                                       │
│       ├─ Fetch plants list                                  │
│       ├─ Cache plants locally                               │
│       ├─ Search & filter logic                              │
│       └─ Disease detection API call                         │
│                                                              │
│ 4. ML KIT INTEGRATION                                       │
│    ├─ Add ML Kit dependencies                               │
│    ├─ Download ML model (if needed)                         │
│    ├─ Implement image labeling                              │
│    └─ Parse & display results                               │
│                                                              │
│ 5. COMPONENTS                                               │
│    ├─ PlantCard (plant preview)                             │
│    ├─ DiseaseResultCard (detection result)                  │
│    └─ CareInstructionCard (tips display)                    │
│                                                              │
│ 6. TESTING                                                  │
│    ├─ Unit tests untuk GuideViewModel                       │
│    ├─ Unit tests untuk DiseaseDetectionViewModel            │
│    ├─ UI tests untuk GuideFragment                          │
│    └─ Camera integration tests                              │
└─────────────────────────────────────────────────────────────┘
```

#### File yang Dikerjakan

```
ui/farming/guide/
├─ GuideActivity.kt            ✅ Create
├─ GuideFragment.kt            ✅ Create
├─ GuideViewModel.kt           ✅ Create
├─ GuideUiState.kt             ✅ Create
├─ GuideAdapter.kt             ✅ Create
└─ fragment_guide.xml          ✅ Create

ui/farming/disease/
├─ DiseaseDetectionActivity.kt ✅ Create
├─ DiseaseDetectionFragment.kt ✅ Create
├─ DiseaseDetectionViewModel.kt✅ Create
├─ DiseaseUiState.kt           ✅ Create
├─ CameraManager.kt            ✅ Create
├─ MLKitAnalyzer.kt            ✅ Create
└─ fragment_disease_detection.xml✅ Create

ui/farming/components/
├─ PlantCard.kt                ✅ Create
├─ DiseaseResultCard.kt        ✅ Create
└─ CareInstructionCard.kt      ✅ Create

data/remote/service/
├─ PlantApiService.kt          ✅ Create
└─ PlantResponse.kt            ✅ Create

data/repository/
├─ PlantRepository.kt          ✅ Create
└─ (Koordinasi dengan Adam)

res/layout/ (Farming-related)
├─ fragment_guide.xml          ✅ Create
├─ item_plant_card.xml         ✅ Create
├─ fragment_disease_detection.xml✅ Create
└─ item_disease_result.xml     ✅ Create
```

#### Key Metrics

- **Modules Owned**: 1 (farming)
- **Classes to Create**: ~15
- **Tests Required**: 8+ unit tests, 3+ UI tests
- **ML Integration**: High (ML Kit + CameraX)

---

### 💼 JOYA - E-Commerce & Marketplace Lead

**Posisi**: Android Developer (UI/UX & Commerce)
**Modul Utama**: `ui/commerce/`
**Fokus**: Marketplace, product details, shopping cart, user profile

#### Tanggung Jawab (Responsibility)

```
┌─────────────────────────────────────────────────────────────┐
│                    JOYA'S RESPONSIBILITY                     │
│─────────────────────────────────────────────────────────────│
│ 1. MARKETPLACE / PRODUCT LISTING                            │
│    ├─ MarketplaceFragment & MarketplaceViewModel           │
│    │  ├─ Fetch products from API                            │
│    │  ├─ Display in GridView/RecyclerView                  │
│    │  ├─ Search functionality                               │
│    │  ├─ Category filtering                                 │
│    │  ├─ Sorting (price, rating, newest)                    │
│    │  ├─ Pagination/infinite scroll                         │
│    │  └─ Pull-to-refresh                                    │
│    │                                                         │
│    ├─ ProductListAdapter                                    │
│    │  ├─ Async image loading (Coil/Glide)                   │
│    │  ├─ Price formatting                                   │
│    │  ├─ Click listeners                                    │
│    │  └─ Item animations                                    │
│    │                                                         │
│    └─ UI components (ProductCard, RatingWidget)             │
│       ├─ Reusable product card                              │
│       └─ Star rating display                                │
│                                                              │
│ 2. PRODUCT DETAIL VIEW                                      │
│    ├─ DetailFragment & DetailViewModel                     │
│    │  ├─ Fetch product details from API                     │
│    │  ├─ Image carousel/gallery                             │
│    │  ├─ Product description & specs                        │
│    │  ├─ Price & discount display                           │
│    │  ├─ Customer reviews & ratings                         │
│    │  ├─ Quantity selector                                  │
│    │  ├─ Add to cart button                                 │
│    │  └─ Wishlist toggle (optional)                         │
│    │                                                         │
│    ├─ ReviewAdapter                                         │
│    │  ├─ Display customer reviews                           │
│    │  ├─ Show star rating per review                        │
│    │  └─ Pagination for reviews                             │
│    │                                                         │
│    └─ Components (PriceDisplay, RatingWidget)               │
│       ├─ Formatted price with currency                      │
│       └─ Clickable star rating                              │
│                                                              │
│ 3. SHOPPING CART                                            │
│    ├─ CartFragment & CartViewModel                         │
│    │  ├─ Display cart items list                            │
│    │  ├─ Quantity edit per item                             │
│    │  ├─ Remove item functionality                          │
│    │  ├─ Calculate subtotal & total                         │
│    │  ├─ Discount/coupon code (optional)                    │
│    │  ├─ Proceed to checkout button                         │
│    │  └─ Empty cart state                                   │
│    │                                                         │
│    ├─ CartItemAdapter                                       │
│    │  ├─ Edit quantity controls                             │
│    │  ├─ Remove button                                      │
│    │  ├─ Price calculation                                  │
│    │  └─ Product image                                      │
│    │                                                         │
│    └─ Components (CartBadge, PriceDisplay)                  │
│       ├─ Cart count badge                                   │
│       └─ Real-time price updates                            │
│                                                              │
│ 4. USER PROFILE & ORDERS                                    │
│    ├─ ProfileFragment & ProfileViewModel                   │
│    │  ├─ Display user info (name, email, phone)             │
│    │  ├─ Edit profile form                                  │
│    │  ├─ Show order history                                 │
│    │  ├─ Order tracking (if available)                      │
│    │  ├─ Address management                                 │
│    │  ├─ Payment methods                                    │
│    │  ├─ App settings                                       │
│    │  ├─ Logout button                                      │
│    │  └─ Notifications preferences                          │
│    │                                                         │
│    ├─ OrderHistoryAdapter                                   │
│    │  ├─ Show past orders list                              │
│    │  ├─ Order status display                               │
│    │  ├─ Click to view details                              │
│    │  └─ Reorder functionality                              │
│    │                                                         │
│    └─ EditProfileDialog/Form                                │
│       ├─ Form fields validation                             │
│       └─ Update API call                                    │
│                                                              │
│ 5. DATA LAYER (Commerce-related)                            │
│    ├─ ProductApiService interface                           │
│    │  ├─ getProducts(): List<Product>                       │
│    │  ├─ searchProducts(query): List<Product>               │
│    │  ├─ getProductDetails(id): Product                     │
│    │  └─ submitOrder(order): OrderResponse                  │
│    │                                                         │
│    ├─ DTOs:                                                 │
│    │  ├─ ProductResponse                                    │
│    │  ├─ ProductOrderRequest                                │
│    │  └─ OrderResponse                                      │
│    │                                                         │
│    └─ Repositories:                                         │
│       ├─ ProductRepository (fetch, cache, search)           │
│       ├─ OrderRepository (create order, history)            │
│       └─ CartRepository (local cart management)             │
│                                                              │
│ 6. COMPONENTS                                               │
│    ├─ ProductCard (product preview)                         │
│    ├─ PriceDisplay (formatted price)                        │
│    ├─ CartBadge (cart item count)                           │
│    ├─ RatingWidget (star rating)                            │
│    └─ DiscountBadge (sale label)                            │
│                                                              │
│ 7. TESTING                                                  │
│    ├─ Unit tests untuk MarketplaceViewModel                 │
│    ├─ Unit tests untuk CartViewModel                        │
│    ├─ Unit tests untuk DetailViewModel                      │
│    ├─ UI tests untuk MarketplaceFragment                    │
│    └─ API mock tests untuk product endpoints                │
└─────────────────────────────────────────────────────────────┘
```

#### File yang Dikerjakan

```
ui/commerce/marketplace/
├─ MarketplaceActivity.kt      ✅ Create
├─ MarketplaceFragment.kt      ✅ Create
├─ MarketplaceViewModel.kt     ✅ Create
├─ MarketplaceUiState.kt       ✅ Create
├─ ProductListAdapter.kt       ✅ Create
└─ fragment_marketplace.xml    ✅ Create

ui/commerce/detail/
├─ DetailActivity.kt           ✅ Create
├─ DetailFragment.kt           ✅ Create
├─ DetailViewModel.kt          ✅ Create
├─ DetailUiState.kt            ✅ Create
├─ ReviewAdapter.kt            ✅ Create
└─ fragment_detail_product.xml ✅ Create

ui/commerce/cart/
├─ CartActivity.kt             ✅ Create
├─ CartFragment.kt             ✅ Create
├─ CartViewModel.kt            ✅ Create
├─ CartUiState.kt              ✅ Create
├─ CartItemAdapter.kt          ✅ Create
└─ fragment_cart.xml           ✅ Create

ui/commerce/profile/
├─ ProfileActivity.kt          ✅ Create
├─ ProfileFragment.kt          ✅ Create
├─ ProfileViewModel.kt         ✅ Create
├─ ProfileUiState.kt           ✅ Create
├─ OrderHistoryAdapter.kt      ✅ Create
├─ fragment_profile.xml        ✅ Create
└─ dialog_edit_profile.xml     ✅ Create

ui/commerce/components/
├─ ProductCard.kt              ✅ Create
├─ PriceDisplay.kt             ✅ Create
├─ CartBadge.kt                ✅ Create
├─ RatingWidget.kt             ✅ Create
└─ DiscountBadge.kt            ✅ Create

data/remote/service/
├─ ProductApiService.kt        ✅ Create
├─ ProductResponse.kt          ✅ Create
└─ ProductOrderRequest.kt      ✅ Create

data/repository/
├─ ProductRepository.kt        ✅ Create
├─ OrderRepository.kt          ✅ Create
└─ CartRepository.kt           ✅ Create

res/layout/ (Commerce-related)
├─ fragment_marketplace.xml    ✅ Create
├─ item_product.xml            ✅ Create
├─ fragment_detail_product.xml ✅ Create
├─ fragment_cart.xml           ✅ Create
├─ item_cart_item.xml          ✅ Create
├─ fragment_profile.xml        ✅ Create
├─ item_order_history.xml      ✅ Create
└─ dialog_edit_profile.xml     ✅ Create
```

#### Key Metrics

- **Modules Owned**: 1 (commerce)
- **Classes to Create**: ~25
- **Tests Required**: 10+ unit tests, 4+ UI tests
- **UI Complexity**: High (multiple screens & adapters)

---

### 📊 TASK MATRIX SUMMARY

| Developer      | Role                  | Modules | Classes | Tests | Priority    | Start Date |
| -------------- | --------------------- | ------- | ------- | ----- | ----------- | ---------- |
| **Adam** | Lead (Infrastructure) | 6       | ~40     | 20    | 🔴 Critical | Week 1     |
| **Azfa** | Auth Lead             | 1       | ~15     | 11    | 🔴 Critical | Week 1     |
| **Evan** | Farming Lead          | 1       | ~15     | 11    | 🟡 High     | Week 2     |
| **Joya** | Commerce Lead         | 1       | ~25     | 14    | 🟡 High     | Week 2     |

---

### 🔄 DEPENDENCIES & WORKFLOW

```
Week 1 (Critical Path):
├─ ADAM: Setup core infrastructure (network, base, DI, database)
│  └─ All other developers blocked until this is done
├─ AZFA: Setup auth system using Adam's infrastructure
│  └─ Depends on: UserRepository from Adam
│
Week 2 (Feature Development):
├─ ADAM: Continue with dashboard implementation
├─ AZFA: Complete auth flow & session management
├─ EVAN: Implement farming module (guide + detection)
│  └─ Depends on: PlantRepository setup
├─ JOYA: Implement commerce module (marketplace + cart)
│  └─ Depends on: ProductRepository setup
│
Week 3+ (Polish & Testing):
├─ All: Write comprehensive tests
├─ All: Code review & refactoring
├─ All: Performance optimization
└─ All: Integration testing & bug fixes
```

---

---

## 🚦 4. ATURAN CODING & GIT

### 4.1 Standar Kode

#### ✅ **WAJIB MENGIKUTI**

**1. Gunakan Component Global**

```kotlin
// ✅ BENAR
TanamiButton(
    text = "Login",
    onClick = { viewModel.login() }
)

TanamiTextField(
    value = email,
    onValueChange = { email = it },
    label = "Email"
)

// ❌ SALAH
Button(text = "Login") // Jangan gunakan material Button langsung
TextField(...) // Jangan langsung gunakan TextField
```

**2. Gunakan Resource Dimensions**

```kotlin
// ✅ BENAR
val textSize = resources.getDimension(R.dimen.text_body)
val spacing = resources.getDimension(R.dimen.spacing_medium)

// ❌ SALAH
val textSize = 16f
val padding = 16.dp
```

**3. Struktur Fragment dengan ViewModel**

```kotlin
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
  
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      
        // Observe ViewModel data
        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            // Handle UI state
        }
    }
}
```

**4. Naming Convention**

```
Activities       : [Feature]Activity.kt      → LoginActivity.kt
Fragments        : [Feature]Fragment.kt      → LoginFragment.kt
ViewModels       : [Feature]ViewModel.kt     → LoginViewModel.kt
Repositories     : [Feature]Repository.kt    → UserRepository.kt
Adapters         : [Feature]Adapter.kt       → ProductListAdapter.kt
Layouts          : fragment_[name].xml       → fragment_login.xml
                   activity_[name].xml       → activity_login.xml
                   item_[name].xml           → item_product.xml
                   dialog_[name].xml         → dialog_edit_profile.xml
```

**5. ViewModel State Pattern**

```kotlin
// ✅ BENAR - Data class untuk state
data class LoginUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

class LoginViewModel : ViewModel() {
    private val _uiState = MutableLiveData<LoginUiState>()
    val uiState: LiveData<LoginUiState> = _uiState
}

// ❌ SALAH - Multiple LiveData untuk setiap state
class LoginViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()
}
```

---

### 4.2 Git Workflow

#### **Sebelum Mulai Ngoding**

```bash
# 1. Update branch master
git fetch origin
git pull origin master

# 2. Buat branch fitur (dari master)
git checkout -b feature/[modul]-[deskripsi]
# Contoh: 
# feature/auth-login-page
# feature/dashboard-home-screen
# feature/farming-disease-detection
# feature/commerce-cart-system
```

#### **Selama Ngoding**

```bash
# 1. Commit dengan pesan jelas
git add .
git commit -m "[MODULE] Deskripsi perubahan"
# Contoh: 
# git commit -m "[AUTH] Implementasi LoginFragment & LoginViewModel"
# git commit -m "[DASHBOARD] Tambah real-time sensor display"
# git commit -m "[FARMING] Integrasi ML Kit untuk disease detection"
# git commit -m "[COMMERCE] Setup product listing dengan filter"

# 2. Push ke branch fitur
git push origin feature/[modul]-[deskripsi]

# 3. Commit regularly (setiap 1-2 jam)
# Jangan commit terlalu besar (1000+ lines perubahan)
```

#### **Selesai Ngoding - Minta Review**

```bash
# 1. Pastikan branch sudah up-to-date
git fetch origin
git rebase origin/master

# 2. Push final changes
git push origin feature/[modul]-[deskripsi] --force-with-lease

# 3. Buat Pull Request di GitHub
#    - Title: [MODULE] Feature description
#    - Body: Jelaskan perubahan & testing yang sudah dilakukan
#    - Assign ke: PM (Adam untuk review)
#    - Reviewers: Sesuai domain expertise

# 4. Setelah approval, merge ke master
git checkout master
git pull origin master
git merge feature/[modul]-[deskripsi]
git push origin master

# 5. Delete branch setelah merge
git branch -d feature/[modul]-[deskripsi]
git push origin --delete feature/[modul]-[deskripsi]
```

#### **Git Commit Message Format**

```
[MODULE] Short description (max 50 chars)

Optional body:
- Detail 1
- Detail 2
- Testing notes
- Fixes #issue-number (jika ada)
```

**Contoh:**

```
[AUTH] Implementasi LoginFragment & LoginViewModel

- Buat UI login dengan TanamiTextField & TanamiButton
- Integrasi dengan UserRepository untuk validasi
- Tambah error handling & loading state
- Tested: Login berhasil, error message muncul, email validation bekerja
- Fixes #15
```

---

### 4.3 Pull Request Checklist

Sebelum submit PR, pastikan:

- [ ] ✅ Code sudah di-test (manual UI testing)
- [ ] ✅ Tidak ada hardcoded values (gunakan resources)
- [ ] ✅ Menggunakan TanamiButton & TanamiTextField (jika UI)
- [ ] ✅ Commit message jelas & descriptive
- [ ] ✅ Tidak ada merge conflicts
- [ ] ✅ Branch sudah rebase dengan master terbaru
- [ ] ✅ Docs updated (jika ada API changes)
- [ ] ✅ Unit tests written & passing
- [ ] ✅ No console errors/warnings
- [ ] ✅ Code formatted dengan Kotlin style
- [ ] ✅ No commented-out code
- [ ] ✅ No debugging println statements

---

## 💻 5. CARA SETUP LOKAL

### Prerequisites

- **Android Studio**: 2024.1 or latest
- **Java JDK**: 21 (jbr-21)
- **Gradle**: 9.3.1 (automatic via wrapper)
- **Git**: Latest version
- **RAM**: Minimum 8GB (recommended 16GB)

### Setup Steps

```bash
# 1. Clone Repository
git clone https://github.com/Orenjikuso/TanamiApps.git
cd TanamiApps

# 2. Pastikan menggunakan JDK 21
# File > Project Structure > SDK Location
# Pastikan JDK path menunjuk ke jbr-21

# 3. Sync Gradle
# Android Studio akan otomatis men-download dependencies
# Atau manual: ./gradlew sync

# 4. Setup Local Properties (jika diperlukan)
# Buat file local.properties di root project:
# sdk.dir=/path/to/Android/SDK
# ndk.dir=/path/to/Android/NDK (jika digunakan)

# 5. Run Gradle Build
./gradlew build

# 6. Run App (pilih satu)
# Via Android Studio: Run > Run 'app'
# Via CLI: ./gradlew installDebug
```

### Troubleshooting

| Error                                       | Solusi                                        |
| ------------------------------------------- | --------------------------------------------- |
| `JAVA_HOME not found`                     | Set JAVA_HOME ke path JDK 21                  |
| `Gradle sync failed`                      | Delete `.gradle` folder & sync ulang        |
| `Module not found`                        | Check `settings.gradle.kts` include modules |
| `Build failed: unsupported class version` | Pastikan JDK 21 di gunakan                    |
| `Dependency not found`                    | Run `./gradlew clean build`                 |
| `Android SDK not found`                   | Setup Android SDK di Android Studio           |

---

## 📚 6. DEPENDENCY & LIBRARY

### Centralized Version Management

Semua dependency versions ada di `gradle/libs.versions.toml`:

```toml
[versions]
agp = "9.1.1"                    # Android Gradle Plugin
coreKtx = "1.10.1"               # AndroidX Core
appcompat = "1.6.1"              # AppCompat
material = "1.10.0"              # Material Design 3
activity = "1.8.0"               # Activity Library
constraintlayout = "2.1.4"       # ConstraintLayout
junit = "4.13.2"                 # Unit Testing
junitVersion = "1.1.5"           # AndroidX JUnit
espressoCore = "3.5.1"           # UI Testing

[libraries]
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
androidx-appcompat = { group = "androidx.appcompat", name = "appcompat", version.ref = "appcompat" }
material = { group = "com.google.android.material", name = "material", version.ref = "material" }
androidx-activity = { group = "androidx.activity", name = "activity", version.ref = "activity" }
androidx-constraintlayout = { group = "androidx.constraintlayout", name = "constraintlayout", version.ref = "constraintlayout" }
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
```

### Menggunakan Dependencies

```kotlin
// Di build.gradle.kts
dependencies {
    // Gunakan version catalog
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
  
    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
```

### Library Recommendations (Coming Soon)

Untuk fitur-fitur yang akan digunakan:

| Fitur                          | Library               | Version                                  | Note                      |
| ------------------------------ | --------------------- | ---------------------------------------- | ------------------------- |
| **Networking**           | Retrofit              | Latest                                   | Add ke libs.versions.toml |
| **HTTP Client**          | OkHttp                | Latest                                   | Add ke libs.versions.toml |
| **JSON Parsing**         | Kotlinx Serialization | Latest                                   | Add ke libs.versions.toml |
| **Image Loading**        | Coil / Glide          | Latest                                   | Add ke libs.versions.toml |
| **Database**             | Room                  | androidx.room:room-runtime               | Add ke libs.versions.toml |
| **Async**                | Coroutines            | org.jetbrains.kotlinx:kotlinx-coroutines | Add ke libs.versions.toml |
| **Dependency Injection** | Hilt                  | com.google.dagger:hilt-android           | Add ke libs.versions.toml |
| **Camera**               | CameraX               | androidx.camera:camera-core              | For Evan                  |
| **ML Kit**               | ML Kit Vision         | com.google.mlkit:vision-common           | For Evan                  |
| **Local Storage**        | DataStore             | androidx.datastore:datastore             | Add ke libs.versions.toml |
| **State Management**     | Flow                  | org.jetbrains.kotlinx:kotlinx-coroutines | Built-in                  |
| **Navigation**           | Navigation Component  | androidx.navigation:navigation-fragment  | Add ke libs.versions.toml |
| **Work Scheduling**      | WorkManager           | androidx.work:work-runtime               | Add ke libs.versions.toml |

> ℹ️ **Tambah library**: Update `gradle/libs.versions.toml` → Sync Gradle → Gunakan di code

---

## 📝 NOTES & BEST PRACTICES

### Do's ✅

- ✅ Selalu gunakan Repository pattern untuk data access
- ✅ Gunakan ViewModel untuk business logic
- ✅ Implement proper error handling & user feedback
- ✅ Test UI locally sebelum commit
- ✅ Communicate changes dalam team
- ✅ Review code dari rekan sebelum merge
- ✅ Keep components reusable & modular
- ✅ Use DataBinding untuk complex UIs
- ✅ Implement proper lifecycle management
- ✅ Document complex logic dengan comments

### Don'ts ❌

- ❌ Jangan hardcode values (Strings, Colors, Dimens)
- ❌ Jangan commit ke master langsung
- ❌ Jangan merge PR tanpa review
- ❌ Jangan modify file di folder orang lain tanpa koordinasi
- ❌ Jangan gunakan deprecated APIs
- ❌ Jangan push ke repository dengan credentials
- ❌ Jangan use nested fragments (use navigation component)
- ❌ Jangan perform network requests di main thread
- ❌ Jangan commit large debug logs atau temp files
- ❌ Jangan ignore compiler warnings

---

## 🔗 Referensi Berguna

- [Android Architecture Components](https://developer.android.com/topic/architecture)
- [Android MVVM Pattern](https://developer.android.com/jetpack/architecture/mvvm)
- [Kotlin Documentation](https://kotlinlang.org/docs/)
- [Material Design 3](https://m3.material.io/)
- [Git Workflow Guide](https://git-scm.com/book/en/v2/Git-Branching-Workflow)
- [CameraX Documentation](https://developer.android.com/training/camerax)
- [ML Kit Documentation](https://developers.google.com/ml-kit)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)
- [Retrofit Documentation](https://square.github.io/retrofit/)
- [Coroutines Guide](https://kotlinlang.org/docs/coroutines-overview.html)

---

## 📞 COMMUNICATION & ESCALATION

### Daily Standup (Recommended)

- **Time**: 09:00 AM (or team agreement)
- **Duration**: 15 minutes
- **Topics**: Progress, blockers, upcoming tasks
- **Platform**: Discord / Google Meet

### Code Review Process

1. Developer submits PR
2. Assigned reviewers check code quality
3. Provide feedback & request changes
4. Developer addresses feedback
5. Approve & merge

### Escalation Path

- **Development Issues**: Contact Adam (Infrastructure Lead)
- **Auth Issues**: Contact Azfa
- **Farming/ML Issues**: Contact Evan
- **Commerce Issues**: Contact Joya
- **Major Decisions**: Contact PM / Project Lead

---

## 📊 PROJECT TIMELINE (ESTIMATED)

```
Week 1: Infrastructure Setup (CRITICAL)
├─ Adam: Core setup, database, network config
├─ Azfa: Auth UI & integration
└─ Deliverable: Base project ready for feature development

Week 2: Core Features
├─ Adam: Dashboard home & siram control
├─ Azfa: Complete auth flow
├─ Evan: Plant guide implementation
├─ Joya: Product listing & detail
└─ Deliverable: MVP with all 4 main modules

Week 3: Polish & Testing
├─ All: Unit tests & UI tests
├─ All: Bug fixes & optimizations
└─ Deliverable: Stable release candidate

Week 4: Final Polish
├─ All: Performance optimization
├─ All: Security review
├─ All: Final testing & UAT
└─ Deliverable: Production ready
```

---

**Last Updated**: 2026-05-01
**Version**: 1.0
**Status**: ✅ Active & Final
