<div align="center">
  <!-- Logo Utama Tanami -->
  <img src="./docs/assets/Tanami_text%20hitam%20.png" width="350">
  
  <!-- Penambahan Jarak (Line Break) -->
  <br><br>
  
  <p><b>Smart Urban Farming Solution for a Sustainable Future</b></p>
  <p><i>"Menanam tumbuhan, Menyiram harapan, Menumbuhkan masa depan."</i></p>

  <p>
    <img src="https://img.shields.io/badge/Kotlin-1.9%2B-blue?style=for-the-badge&logo=kotlin" alt="Kotlin">
    <img src="https://img.shields.io/badge/JDK-21-red?style=for-the-badge&logo=openjdk" alt="JDK 21">
    <img src="https://img.shields.io/badge/Architecture-MVVM-orange?style=for-the-badge" alt="MVVM">
    <img src="https://img.shields.io/badge/Methodology-RAD-green?style=for-the-badge" alt="RAD">
  </p>
</div>

---

### 📖 Tentang Tanami
**Tanami** adalah platform *Smart Urban Farming* revolusioner yang menjembatani kesenjangan antara teknologi modern dan kebutuhan bercocok tanam di lahan sempit. Menggunakan integrasi **IoT** untuk pemantauan real-time dan **AI** untuk deteksi kesehatan tanaman, Tanami dirancang dengan standar **Premium UI/UX** untuk memberikan pengalaman berkebun digital yang intuitif[cite: 1].

---

### ✨ Fitur Unggulan (Core Modules)

<table width="100%">
  <tr>
    <td width="50%">
      <b>📊 Smart Dashboard & IoT</b><br>
      Visualisasi data sensor tanah (pH, kelembapan, temperatur) secara real-time dan kontrol alat penyiraman otomatis[cite: 3, 4].
    </td>
    <td width="50%">
      <b>🔍 AI Plant Pathologist</b><br>
      Identifikasi penyakit tanaman secara instan menggunakan kamera smartphone berbasis Machine Learning (ML Kit)[cite: 4].
    </td>
  </tr>
  <tr>
    <td width="50%">
      <b>🛒 Tanami Marketplace</b><br>
      Ekosistem perdagangan hasil panen dan alat pertanian urban yang terintegrasi langsung dalam aplikasi[cite: 1, 4].
    </td>
    <td width="50%">
      <b>📚 Encyclopedia & Care Guide</b><br>
      Panduan langkah-demi-langkah menanam berbagai komoditas sayuran dan tanaman hias[cite: 1, 4].
    </td>
  </tr>
</table>

---

### 🏗️ Arsitektur & Penjelasan Layer
Aplikasi ini dibangun di atas fondasi **Clean Architecture** untuk memastikan skalabilitas dan kemudahan pengujian[cite: 4]:

*   **🛡️ Core Layer**: Fondasi infrastruktur global. Berisi konfigurasi Network (Retrofit), Base Classes, dan Utility Helpers[cite: 4].
*   **💾 Data Layer**: *Single Source of Truth*. Mengelola alur data dari API Remote dan Local Database (Room) melalui pola Repository[cite: 4].
*   **🎨 UI Layer**: Presentasi modular. Setiap fitur memiliki **UiState** mandiri untuk menangani state UI secara reaktif[cite: 4].

---

### 👥 Tim Pengembang (The Squad)
Bekerja secara kolaboratif menggunakan metodologi **Rapid Application Development (RAD)**[cite: 1, 4].

| Developer | Peran | Domain Modul | Fokus Teknis[cite: 1, 4] |
| :--- | :--- | :--- | :--- |
| **Adam (PM)** | Infrastructure Lead | `ui/dashboard`, `ui/iot` | IoT Real-time, Network Core, & Dashboard. |
| **Azfa** | Security Specialist | `ui/auth` | Authentication flow & Session management. |
| **Evan** | AI & Farming Lead | `ui/farming` | **ML Kit Detection & Plant Encyclopedia.** |
| **Joya** | Commerce Specialist | `ui/marketplace` | **Marketplace Flow, Cart, & User Profile.** |

---

### 📸 Showcase Antarmuka (Feature Preview)

#### ✨ Pengalaman Pengguna (Onboarding Flow)
<div align="center">
  <table width="100%">
    <tr>
      <td align="center"><img src="./docs/assets/Onboarding%20-%201.png" width="250"><br><i>AI Scan Intro</i></td>
      <td align="center"><img src="./docs/assets/Onboarding%20-%202.png" width="250"><br><i>Marketplace Intro</i></td>
      <td align="center"><img src="./docs/assets/Onboarding%20-%203.png" width="250"><br><i>Condition Intro</i></td>
    </tr>
  </table>
</div>

---

### 🚦 Persyaratan Sistem
*   **Android Studio**: Ladybug 2024.1.1 atau terbaru[cite: 4].
*   **JDK**: Versi 21 (Wajib)[cite: 4].
*   **Gradle**: v9.3.1[cite: 4].
*   **Minimum SDK**: Android 21+[cite: 4].

> [!TIP]
> Untuk instruksi teknis pengerjaan file dan standar coding bagi AI/Developer, silakan baca [**GUIDE.md**](./GUIDE.md).

---

<div align="center">
  <p><b>TanamiApps Project Management © 2026</b></p>
  <p><i>"Membangun kode yang sehat, untuk ekosistem yang berkelanjutan."</i></p>
</div>
