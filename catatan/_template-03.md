# Catatan Minggu 03: Inheritance

## Yang saya kerjakan
Saya membuat `Ruang`, `Laptop`, dan `AlatLab` turunan `Aset`. Semua memanggil `super(kode, nama)`, menyimpan field khusus, dan punya `getJenis()` sendiri. Validasi dari `Aset` tetap berlaku, dan tes minggu 3 lulus.

## Keputusan desain
- Data umum (`kode`, `nama`, `status`, `kondisi`) di `Aset`; data khusus di subclass (`kapasitas` di `Ruang`, `spesifikasi`/`nomorInventaris` di `Laptop`, `laboratorium`/`tingkatRisiko` di `AlatLab`).
- Field khusus dibuat `final` karena tidak perlu berubah.

## Kendala
Saya sempat ragu soal urutan `super` dan validasi. Solusinya: panggil `super` dulu, lalu validasi data khusus subclass.

## Contoh inheritance kurang tepat
Denda dihitung lewat override `hitungDenda()` di tiap subclass. Kalau tarif sering berubah, semua subclass harus diubah. Komposisi lebih tepat: `Aset` menyimpan objek aturan denda yang bisa diganti.

## Penggunaan AI
- Alat yang dipakai: ChatGPT
- Bagian yang dibantu AI: pengecekan konstruktor dan `getJenis()` pada ketiga subclass.
- Prompt utama:
  - "Cek class Ruang, Laptop, dan AlatLab saya, sudah benar belum super dan validasinya?"
  - "Kalau aturan denda sering berubah, mending inheritance atau komposisi?"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: AI menaruh `kapasitas` di `Aset`; saya pindahkan ke `Ruang` karena tidak semua aset punya kapasitas.
- Satu hal yang saya pahami lebih baik sekarang: data yang hanya dimiliki satu jenis sebaiknya tetap di subclass.
