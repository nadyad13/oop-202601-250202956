# Catatan Minggu 05: Abstraction

## Yang saya kerjakan
Saya menjadikan `Aset` abstract dan mengisi `bisaDipinjam()`: true hanya jika status `TERSEDIA` dan kondisi bukan `RUSAK_BERAT`. Saya juga mengisi `DendaFlat` dan `DendaProgresif`. Tes minggu 2-5 lulus.

## Keputusan desain
- `DendaFlat` langsung memakai `aset.hitungDenda(hari)`.
- `DendaProgresif` menambah tarif harian setelah hari ke-7, jadi tidak pernah lebih murah dari flat dan tetap naik. Rumus dicatat di `docs/asumsi.md`.

## Kendala
Setelah `Aset` abstract, `new Aset(...)` tidak bisa dipakai lagi. Tes lama tetap aman karena `TestSupport.aset(...)` otomatis memakai `Ruang`.

## Penggunaan AI
- Alat yang dipakai: ChatGPT
- Bagian yang dibantu AI: pengecekan `bisaDipinjam()` dan rumus `DendaProgresif`.
- Prompt utama:
  - "Cek bisaDipinjam saya, sudah sesuai aturan TERSEDIA dan bukan RUSAK_BERAT?"
  - "Rumus progresif sederhana yang tetap lolos syarat tes apa ya?"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: AI memberi rumus bertingkat yang rumit. Saya pilih tambahan tarif setelah hari ke-7 karena lebih mudah dicek dan tetap memenuhi syarat.
- Satu hal yang saya pahami lebih baik sekarang: abstract class menampung bagian umum, sedangkan bagian yang berbeda tetap di subclass.
