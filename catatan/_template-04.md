# Catatan Minggu 04: Polymorphism

## Yang saya kerjakan
Saya meng-override `hitungDenda(int)` di `Ruang`, `Laptop`, dan `AlatLab` dengan multiplier 1x, 2x, dan 4x. Di `KatalogAset` saya membuat dua overload `cari()`: berdasarkan kata saja dan berdasarkan kata + jenis. Tes minggu 4 lulus.

## Keputusan desain
- Tarif dasar diambil dari `Varian.aktif().dendaPerHari`, sedangkan multiplier ditentukan tiap subclass lewat override.
- `cari(kata, jenis)` memanggil `cari(kata)` lalu menyaring dengan `getJenis()`, jadi tidak perlu menulis ulang pencocokan nama.

## Kendala
Saya sempat bingung membedakan overloading dan overriding. Saya pahami dari tes: `cari()` dibedakan dari parameternya, sedangkan `hitungDenda()` dipilih dari objek aslinya saat runtime.

## Penggunaan AI
- Alat yang dipakai: ChatGPT
- Bagian yang dibantu AI: pengecekan `hitungDenda()` dan dua overload `cari()`.
- Prompt utama:
  - "Cek override hitungDenda saya, multiplier tiap jenis sudah benar?"
  - "Bedanya overloading cari() dan overriding hitungDenda() apa ya?"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: AI menyarankan cast ke subclass saat menyaring katalog. Saya tolak karena `getJenis()` sudah bisa dipakai langsung tanpa cast.
- Satu hal yang saya pahami lebih baik sekarang: overriding memakai objek sebenarnya, sedangkan overloading dipilih dari jumlah atau tipe parameter.
