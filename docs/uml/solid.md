# Prinsip SOLID di Kode Aset-Pinjam

Di bawah ini prinsip yang benar-benar terlihat di kode sekarang, bukan rencana.

## 1. Single Responsibility Principle — `KatalogAset`, `JadwalPeminjaman`, `Aset`

- `katalog/KatalogAset.java` hanya mengurus kumpulan aset: menyimpan (`tambah`), membaca (`semua`), dan mencari (`cari`).
- `jadwal/JadwalPeminjaman.java` hanya mengurus jadwal: menyimpan peminjaman (`tambah`) dan memeriksa bentrok (`bentrok`, `ajukan`).
- `model/Aset.java` hanya menyimpan data aset dan menjawab aturan dasarnya sendiri (`bisaDipinjam`).

Ketiganya punya satu alasan untuk berubah. Kalau aturan pencarian berubah, hanya `KatalogAset` yang disentuh;
kalau aturan bentrok berubah, hanya `JadwalPeminjaman`. Keduanya tidak saling bergantung.

## 2. Open/Closed Principle — `kontrak.AturanDenda`, `DendaFlat`, `DendaProgresif`

`AturanDenda` adalah kontrak dengan satu metode, `hitung(Aset, int)`. `DendaFlat` dan `DendaProgresif`
mengimplementasikan kontrak itu. Kalau nanti perlu aturan baru (misalnya denda khusus akhir pekan), saya cukup
menambah kelas baru yang `implements AturanDenda`; `Aset`, `DendaFlat`, dan `DendaProgresif` tidak perlu diubah.

Prinsip yang sama terlihat pada `Aset`: menambah jenis aset baru berarti membuat subclass baru yang mengisi
`getJenis()` dan `hitungDenda()`. `KatalogAset` tetap memakai `List<Aset>` tanpa perubahan.

## 3. Dependency Inversion Principle — `JdbcPeminjamanDAO`, `JdbcAsetDAO`

`JdbcPeminjamanDAO` menerima `Supplier<Connection>` dan `AsetDAO` (interface) lewat konstruktor, bukan membuat
`Koneksi` atau `JdbcAsetDAO` sendiri di dalam kelas. Artinya kelas ini bergantung pada abstraksi, bukan detail JDBC.
Dampaknya untuk pengujian: DAO palsu yang mengimplementasikan `AsetDAO` bisa disuntikkan tanpa database sungguhan,
sehingga logika peminjaman dapat diuji terpisah dari PostgreSQL.

`JdbcAsetDAO` juga menerima `Supplier<Connection>`, jadi sumber koneksi dapat diganti saat tes.

## 4. Liskov Substitution Principle — `Ruang`, `Laptop`, `AlatLab`

Ketiga kelas dapat menggantikan `Aset` di mana pun, misalnya di `List<Aset>` milik `KatalogAset` atau di field
`Peminjaman.aset`. Semuanya tetap memenuhi janji `Aset`: `bisaDipinjam()` benar hanya untuk status `TERSEDIA`
dan kondisi bukan `RUSAK_BERAT`, `getJenis()` mengembalikan jenisnya sendiri, dan `hitungDenda()` menolak hari negatif.
Tidak ada subclass yang melempar kejutan di luar kontrak induknya.

## Catatan refactor

Struktur kode sudah memperlihatkan keempat prinsip di atas, jadi tidak perlu refactor besar. Perbaikan yang saya
lakukan hanya menyelesaikan sisa penanda konflik merge di `Aset.java` agar `hitungDenda` kembali abstrak dan
proyek bisa dikompilasi.
