# Catatan Minggu 07: Koleksi

## Yang saya kerjakan
Saya mengisi `bentrok`, `semuaUrutMulai`, `perAset`, dan `hapus` di `JadwalPeminjaman`. Saya juga mengganti tes contoh di `BatasBentrokTest` dengan tes untuk keputusan batas waktu saya. Tes minggu 1-7 lulus.

## Keputusan desain
- Saya tetap memakai satu `List<Peminjaman>` sebagai sumber data. `perAset()` mengelompokkan saat dipanggil, jadi tidak ada Map kedua yang harus dijaga tetap sinkron.
- Batas waktu: selesai sama dengan mulai tidak bentrok (rentang setengah terbuka), karena aset bisa langsung dipakai lagi. Dicatat di `docs/asumsi.md` A-01.
- `semuaUrutMulai()` memakai `Comparator.comparing(Peminjaman::getMulai)` dengan pembeda `id` agar urutan tetap sama saat waktu mulai sama.

## Kendala
Sempat bingung menentukan rumus tumpang tindih. Saya cek dengan empat kasus: tumpang tindih sebagian, terkandung, bersentuhan, dan terpisah. Hasilnya cocok dengan tes.

## Penggunaan AI
- Alat yang dipakai: ChatGPT
- Bagian yang dibantu AI: mengisi empat metode `JadwalPeminjaman` dan merapikan tes batas waktu.
- Prompt utama: "Isi bentrok, semuaUrutMulai, perAset, dan hapus di JadwalPeminjaman sesuai docs/minggu-07.md."
- Yang saya ubah atau tolak dari saran AI, dan alasannya: saya pilih tidak menambah Map/Set, karena data masih kecil dan tiga struktur harus dijaga sinkron.
- Satu hal yang saya pahami lebih baik sekarang: `groupingBy` cukup untuk mengelompokkan data saat dibutuhkan, tanpa menyimpan salinan kedua.
