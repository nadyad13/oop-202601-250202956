# Catatan Minggu 02: Struktur OOP: Class, Object, Encapsulation

## Yang saya kerjakan
Saya mengisi class `Aset` dengan field private, konstruktor bervalidasi, getter, dan setter untuk `nama`, `status`, serta `kondisi`. Nilai awalnya `TERSEDIA` dan `BAIK`. Tes minggu 2 lulus 6/6.

## Keputusan desain
- `kode` tanpa setter karena tidak boleh berubah setelah dibuat.
- Validasi memakai `isBlank()` dan tanpa trim/uppercase; nilai yang sah disimpan apa adanya.

## Kendala
Saya sempat bingung antara validasi "kosong" dan normalisasi. Setelah membaca tes, ternyata yang diminta hanya menolak null/blank.

## Penggunaan AI
- Alat yang dipakai: ChatGPT
- Bagian yang dibantu AI: pengecekan validasi konstruktor dan setter `Aset`.
- Prompt utama:
  - "Tolong cek class Aset saya, validasinya sudah benar belum?"
  - "Sebaiknya cek kosongnya pakai `isEmpty()` atau `isBlank()`?"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: AI menyarankan `isEmpty()`, tapi saya pakai `isBlank()` agar input yang isinya hanya spasi juga ditolak.
- Satu hal yang saya pahami lebih baik sekarang: encapsulation berarti field private dan perubahan nilai hanya lewat method yang sudah divalidasi.
