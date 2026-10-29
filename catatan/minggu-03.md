# Catatan Minggu 03: Inheritance

## Yang saya kerjakan

Saya melengkapi tiga subclass dari `Aset`, yaitu `Ruang`, `Laptop`, dan `AlatLab`. Masing-masing sekarang punya field khusus, konstruktor yang memanggil `super(kode, nama)`, getter, dan override `getJenis()` yang mengembalikan `RUANG`, `LAPTOP`, atau `ALAT_LAB`. Saya juga merapikan validasi di `Aset` agar string yang hanya berisi spasi (`"   "`) ikut ditolak, karena sebelumnya hanya string kosong yang dicek. Setelah itu saya menjalankan tes minggu 1 sampai 3 dan semuanya hijau.

## Keputusan desain

- Field yang sama untuk semua aset tetap di induk `Aset`: `kode`, `nama`, `status`, dan `kondisi`. Alasannya, keempat data itu dipakai oleh semua jenis aset dan aturan validasinya juga sama, jadi tidak perlu diduplikasi di tiap subclass.
- Field yang hanya bermakna untuk satu jenis aset saya taruh di subclass: `kapasitas` di `Ruang`, `spesifikasi` dan `nomorInventaris` di `Laptop`, serta `laboratorium` dan `tingkatRisiko` di `AlatLab`. Kalau field ini dipaksa masuk ke `Aset`, objek `Ruang` akan menyimpan data laptop yang tidak relevan dan validasinya jadi bercabang berdasarkan jenis.
- Validasi khusus saya letakkan di konstruktor subclass setelah `super(...)`, sedangkan validasi `kode`/`nama` tetap dijalankan oleh `Aset`. Dengan begitu aturan induk otomatis berlaku untuk semua subclass.
- Untuk mengecek string kosong saya memakai `isBlank()`, bukan `isEmpty()`, supaya input berisi spasi saja juga ditolak.

Contoh inheritance yang kurang tepat di sistem ini: perilaku denda. Denda berbeda per jenis aset (Ruang 1x, Laptop 2x, Alat Lab 4x), tetapi kalau perbedaan itu diwujudkan dengan membuat subclass `Peminjaman` seperti `PeminjamanRuang`, `PeminjamanLaptop`, dan `PeminjamanAlatLab`, jumlah class akan meledak dan aturan denda tidak bisa diganti saat program berjalan. Di sini komposisi lebih tepat: `Peminjaman` cukup memiliki field strategi denda (`DendaStrategy`), sehingga varian denda bisa ditukar tanpa membuat hierarki baru. Ini juga sesuai FR-6 yang meminta aturan denda bisa diganti.

## Kendala

Awalnya tes minggu 2 masih gagal pada input berupa spasi karena validasi `Aset` memakai `isEmpty()`. Saya menemukan penyebabnya dari pesan tes, lalu menggantinya dengan `isBlank()`. Kendala lain adalah memastikan validasi subclass tidak menghapus validasi induk; solusinya adalah tetap memanggil `super(kode, nama)` di baris pertama setiap konstruktor.

## Penggunaan AI

- Alat yang dipakai: OpenCode dengan model Big Pickle.
- Bagian yang dibantu AI: `Ruang.java`, `Laptop.java`, `AlatLab.java` (field, konstruktor, getter, `getJenis()`), perbaikan validasi `Aset`, dan draf awal catatan ini.
- Prompt utama:
  1. "kerjakan docs/minggu-03.md"
  2. "jelaskan di mana sebaiknya field ditaruh, di induk atau subclass"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: saya menolak menaruh `kapasitas`, `spesifikasi`, atau `laboratorium` di kelas `Aset` karena field itu hanya relevan untuk satu jenis aset. Saya juga memilih `isBlank()` setelah melihat tes minggu 2 menolak string berisi spasi, bukan hanya mengikuti saran awal yang memakai `isEmpty()`.
- Satu hal yang saya pahami lebih baik sekarang: kelas induk sebaiknya hanya menyimpan data dan perilaku yang benar-benar dimiliki semua subclass; data yang hanya dimiliki satu jenis aset lebih aman dan lebih jelas jika ditaruh di subclass.
