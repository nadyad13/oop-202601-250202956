# Catatan Minggu 01: Paradigma Pemrograman dan Setup

## Yang saya kerjakan
Saya memasang JDK 21 dan Maven 3.9.16, lalu mengisi `HelloProsedural`, `HelloOOP`, dan `HelloFungsional` dengan sapaan yang sama. Tes minggu 1 lulus 5/5.

## Keputusan desain
- Kalimat sapaan: "Hallo Aset-Pinjam <nama> (<NIM>)" di ketiga gaya, supaya hasilnya mudah dibandingkan.
- Prosedural: kelebihan sederhana untuk program kecil; keterbatasan data dan fungsi terpisah sehingga susah saat sistem membesar.
- OOP: kelebihan data dan perilaku terbungkus di class; keterbatasan butuh lebih banyak class/kode untuk program kecil.
- Fungsional: kelebihan fungsi bisa disimpan dan digabung, minim state; keterbatasan kurang cocok untuk data yang sering berubah seperti status peminjaman.

## Kendala
Awalnya sempat error karena `JAVA_HOME` belum diarahkan ke JDK 21. Setelah dibantu teman dan dicek dengan `mvn -v`, tes bisa dijalankan.

## Penggunaan AI
- Alat yang dipakai: ChatGPT
- Bagian yang dibantu AI: penyusunan dan perapian catatan minggu 1.
- Prompt utama:
  - "kerjakan catatan/minggu-01.md sesuai docs/minggu-01.md, buat singkat dan jelas"
  - "tambahkan kelebihan dan keterbatasan tiap gaya"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: draf AI terlalu panjang dan menjelaskan teori umum. Saya ringkas agar hanya berisi yang saya kerjakan dan temuan dari tes.
- Satu hal yang saya pahami lebih baik sekarang: program yang sama bisa ditulis dengan tiga gaya berbeda, tetapi hasilnya tetap harus sama.
