# Catatan Minggu 06: UML dan SOLID

## Yang saya kerjakan
Saya membuat class diagram dan sequence diagram di `docs/uml/` dengan Mermaid, lalu menulis `solid.md`.
Isi diagram saya samakan dengan kelas dan metode yang ada di `core`, bukan rancangan baru.
Saya juga membereskan sisa konflik merge di `Aset.java` supaya proyek bisa dikompilasi lagi.

## Keputusan desain
- Mermaid saya taruh di berkas `.md` agar sumber dan gambarnya jadi satu, langsung tampil di GitHub.
- Saya pilih empat prinsip yang paling kelihatan: SRP di `KatalogAset`/`JadwalPeminjaman`, OCP di `AturanDenda`, DIP di `JdbcPeminjamanDAO`, dan LSP di `Ruang`/`Laptop`/`AlatLab`.
- Relasi saya gambar sebagai agregasi karena `Peminjaman` dan `Aset` dibuat di luar kelas yang menyimpannya.

## Kendala
`Aset.java` masih menyimpan penanda konflik merge, jadi kode tidak bisa dikompilasi. Saya pilih versi `hitungDenda` abstrak (sesuai minggu 5), lalu tes dijalankan ulang.

## Penggunaan AI
- Alat yang dipakai: OpenCode
- Bagian yang dibantu AI: membaca struktur kode, menyusun diagram Mermaid, dan merapikan `solid.md`.
- Prompt utama: "Baca kode di repo ini, lalu buat class diagram dan sequence pengajuan peminjaman yang sesuai kode."
- Yang saya ubah atau tolak dari saran AI, dan alasannya: saya tidak menambah kelas layanan baru yang belum ada di kode, supaya diagram tidak jadi sketsa ideal.
- Satu hal yang saya pahami lebih baik sekarang: relasi diagram harus mengikuti cara objek benar-benar dibuat di kode.
