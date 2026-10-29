# Catatan Minggu 04: Polymorphism

## Yang saya kerjakan

Saya menambahkan override `hitungDenda(int)` pada `Ruang`, `Laptop`, dan `AlatLab`. Masing-masing memakai multiplier jenisnya (1x, 2x, dan 4x) dikali tarif harian dari `Varian.aktif().dendaPerHari`, serta menolak hari negatif dengan `IllegalArgumentException`. Saya juga melengkapi dua overload `cari(...)` di `KatalogAset`: satu mencari berdasarkan nama saja dan satu lagi menyaring berdasarkan `JenisAset`. Setelah itu saya menjalankan tes minggu 1 sampai 4 dan semua tes (27 buah) lulus.

## Keputusan desain

- Perhitungan denda saya taruh sebagai override di tiap subclass, bukan satu method dengan `if` berdasarkan jenis. Dengan begitu pemanggilan `aset.hitungDenda(3)` lewat referensi `Aset` otomatis memakai implementasi kelas aslinya (dynamic binding).
- Multiplier ditulis langsung di kelasnya masing-masing karena aturannya sudah pasti: Ruang 1x, Laptop 2x, Alat Lab 4x. Tarif dasar tetap dibaca dari `Varian` agar mengikuti NIM.
- Hasil dihitung sebagai `long` dengan urutan `(long) hari * multiplier * tarif` supaya tidak terjadi overflow `int` sebelum hasilnya diperlebar.
- Tarif dibaca saat method dipanggil, bukan disimpan di konstruktor. Alasannya, `Varian` adalah konfigurasi bersama dan nilai tarif tidak perlu diduplikasi di setiap objek.
- Validasi hari negatif saya tulis di tiap override karena aturan itu harus berlaku untuk semua jenis aset. `Aset` sendiri belum menghitung apa pun karena kelas induk tidak punya jenis aset; method itu baru akan dijadikan abstrak pada minggu 5.
- `cari(kata)` mengubah kata kunci dan nama ke huruf kecil dengan `Locale.ROOT` sebelum memakai `contains`, sehingga pencarian tidak terpengaruh pengaturan bahasa komputer.
- `cari(kata, jenis)` memanggil `cari(kata)` lebih dulu, lalu menyaring hasilnya dengan `getJenis()`. Dengan cara ini logika pencocokan nama hanya ditulis sekali dan perbedaan kedua method benar-benar hanya pada penyaringan jenis.
- Kata kunci atau jenis yang bernilai `null` saya perlakukan sebagai pencarian tanpa hasil (daftar kosong), bukan error, agar pemanggil tidak menerima `NullPointerException` yang tidak dijelaskan di aturan.
- Perbedaan overloading dan overriding: dua `cari(...)` adalah overloading karena dipilih saat compile berdasarkan jumlah/tipe parameter, sedangkan `hitungDenda(int)` adalah overriding karena implementasi yang dijalankan dipilih saat runtime berdasarkan objek sebenarnya.

## Kendala

Bagian yang paling perlu diperhatikan adalah memastikan `cari(kata, jenis)` memanggil overload yang benar. Karena method di dalamnya memanggil `cari(kata)` dengan satu argumen, Java memilih versi satu parameter, bukan memanggil dirinya sendiri, sehingga tidak terjadi rekursi tanpa akhir. Saya juga harus menambahkan import `Varian` di ketiga subclass. Tes dijalankan dengan `.\scripts\uji.ps1 -Minggu 4` karena `minggu-aktif.txt` di repo masih berisi 1.

## Penggunaan AI

- Alat yang dipakai: OpenCode dengan model Big Pickle.
- Bagian yang dibantu AI: override `hitungDenda` di `Ruang`, `Laptop`, dan `AlatLab`; dua overload `cari` di `KatalogAset`; perapian komentar; dan draf awal catatan ini.
- Prompt utama:
  1. "kerjakan docs/minggu-04.md"
  2. "jelaskan perbedaan overloading dan overriding pada kode ini"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: saya menolak ide menaruh semua perhitungan denda dalam satu method `Aset` yang bercabang berdasarkan `getJenis()`, karena itu bukan latihan overriding dan menghilangkan manfaat dynamic binding. Saya juga menambahkan penanganan `null` pada `cari` agar tidak melempar `NullPointerException`.
- Satu hal yang saya pahami lebih baik sekarang: overloading dipilih dari tanda tangan method saat compile, sedangkan overriding dipilih dari tipe objek sebenarnya saat runtime; itulah sebabnya satu variabel bertipe `Aset` bisa menghasilkan denda yang berbeda untuk `Ruang` dan `Laptop`.
