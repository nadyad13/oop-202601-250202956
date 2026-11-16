# Asumsi dan Keputusan Desain

Isi bagian ini sendiri. Satu entri per keputusan. Dinilai dari kejelasan alasan dan konsistensi dengan kode dan tes Anda,
bukan dari jawaban "benar".

Format tiap entri:

```
## A-NN: judul singkat
- Pertanyaan: ...
- Keputusan: ...
- Alasan: ...
- Dampak ke kode: ... (kelas/metode)
- Tes yang membuktikan: ... (nama tes di folder mahasiswa/)
```

## Aturan ambigu dari docs/aturan-bisnis.md

Salin setiap pertanyaan ambigu (nomor 1 sampai 7) ke sini dan jawab. Contoh:

## A-01: batas waktu bentrok
- Pertanyaan: 09:00-11:00 dan 11:00-13:00 pada aset yang sama bentrok atau tidak?
- Keputusan: tidak bentrok. Rentang diperlakukan setengah terbuka, `[mulai, selesai)`.
- Alasan: aset bisa langsung dipakai lagi tepat saat peminjaman sebelumnya selesai, jadi tidak ada waktu menganggur yang tidak perlu. Ini juga tidak menolak pengajuan bersebelahan yang sebenarnya sah.
- Dampak ke kode: `JadwalPeminjaman.bentrok` memakai `mulai < selesaiLain && mulaiLain < selesai`, sehingga batas yang hanya bersentuhan tidak dihitung bentrok.
- Tes yang membuktikan: `BatasBentrokTest.selesaiSamaDenganMulaiTidakBentrok`.

## Asumsi lain

Tambahkan di sini keputusan lain yang Anda ambil: rumus denda progresif, perubahan skema database, format error API, dan seterusnya.

## A-02: rumus denda progresif
- Pertanyaan: bagaimana denda naik setelah beberapa hari terlambat?
- Keputusan: pakai denda flat, lalu tambah tarif harian untuk setiap hari setelah hari ke-7.
- Alasan: tetap sederhana, tidak pernah lebih murah dari flat, dan makin berat untuk keterlambatan lama.
- Dampak ke kode: `DendaProgresif.hitung`.
- Tes yang membuktikan: `Minggu05AbstraksiTest.dendaProgresifMemenuhiSyaratMinimal`.

