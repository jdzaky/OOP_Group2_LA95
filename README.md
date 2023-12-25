DOKUMENTASI CODE 
APLIKASI PENCARI DOKTER
GROUP 2 – LA95

1. Main 
• Fungsi Utama: Memulai program, menampilkan menu, dan mengelola interaksi dengan pengguna. Menggunakan kelas-kelas lainnya untuk mengelola informasi dokter, pasien, dan janji temu.
• Fitur:
  - Menampilkan menu untuk Admin dan User.
  - Admin dapat menambah, mengubah, menghapus, melihat daftar, dan mencari dokter.
  - User dapat membuat janji temu, mencari dokter, melihat daftar dokter, dan mendaftar sebagai pasien.
  - Menangani input dari pengguna dan memanggil metode-metode pada kelas terkait.
  - Menggunakan objek DoctorManager untuk mengelola dokter dan AppointmentManager untuk mengelola janji temu.

2. Appointment 
• Fungsi Utama: Mewakili informasi janji temu antara pasien dan dokter.
• Fitur:
  - Menyimpan data seperti NIK pasien, kode dokter, tanggal janji, keluhan pasien, diagnosis dokter, dan resep obat.
  - Metode get dan set untuk mengakses dan mengubah informasi janji temu.

3. AppointmentManager 
• Fungsi Utama: Mengelola proses pemesanan janji temu.
• Fitur:
  - Memeriksa ketersediaan dokter pada tanggal tertentu sebelum membuat janji temu.
  - Menggunakan DoctorManager dan KoneksiDB untuk mengelola data dan operasi terkait janji temu.

4. DoctorManager 
• Fungsi Utama: Mengelola informasi dokter.
• Fitur:
  - Menambah, mengubah, menghapus, mencari, dan melihat daftar dokter.
  - Menggunakan KoneksiDB untuk berinteraksi dengan database.
  - Menyimpan daftar dokter dalam bentuk objek Dokter.
  - Mengelola data dokter, termasuk operasi CRUD.

5. Dokter 
• Fungsi Utama: Menjadi kelas dasar untuk dokter, dengan implementasi umum untuk dokter umum dan spesialis.
• Fitur:
  - Menyediakan properti dan metode umum untuk semua jenis dokter.
  - Menjadi dasar bagi DokterUmum dan DokterSpesialis.

6. DokterSpesialis 
• Fungsi Utama: Mewakili dokter dengan spesialisasi tertentu.
• Fitur:
  - Menambahkan properti bidangSpesialisasi dibandingkan dengan DokterUmum.
  - Mengimplementasikan metode abstrak dari kelas Dokter.

7. DokterUmum 
• Fungsi Utama: Mewakili dokter umum.
• Fitur:
  - Menambahkan properti gelar dibandingkan dengan DokterSpesialis.  
  - Mengimplementasikan metode abstrak dari kelas Dokter.

8. KoneksiDB
• Fungsi Utama: Mengelola koneksi dan operasi database.
• Fitur:
  - Membuat koneksi ke database MySQL menggunakan JDBC.
  - Menangani operasi CRUD untuk tabel dokter dan pasien.
  - Menangani operasi CRUD untuk tabel appointment.

9. Pasien
• Fungsi Utama: Mewakili informasi pasien.
• Fitur:
  - Menyimpan data seperti NIK dan nama pasien.
  - Menggunakan metode addPatient pada KoneksiDB untuk mendaftarkan pasien.

Catatan Penting:
• Struktur program terdiri dari beberapa kelas yang bekerja sama untuk menyediakan fungsionalitas lengkap dari aplikasi manajemen dokter dan pasien.
• Beberapa kelas menggunakan JDBC (Java Database Connectivity) untuk berinteraksi dengan database MySQL.
• Program ini mendemonstrasikan konsep OOP (Object-Oriented Programming) dengan pewarisan, enkapsulasi, dan polimorfisme untuk memodelkan entitas yang berbeda seperti dokter, pasien, dan janji temu.
