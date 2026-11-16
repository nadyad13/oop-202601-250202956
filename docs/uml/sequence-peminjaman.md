# Sequence Diagram: Pengajuan Peminjaman

Alur memakai kelas dan metode yang benar-benar ada di kode: `Peminjaman` (konstruktor),
`JadwalPeminjaman.ajukan()`, `JadwalPeminjaman.bentrok()`, dan `JadwalPeminjaman.tambah()`.
Fragmen `alt` memisahkan skenario sukses dan skenario bentrok.

```mermaid
sequenceDiagram
    autonumber
    actor P as Peminjam
    participant PJ as Peminjaman
    participant J as JadwalPeminjaman

    P->>PJ: new Peminjaman(id, peminjam, aset, mulai, selesai)
    activate PJ
    PJ->>PJ: validasi selesai.isAfter(mulai)
    PJ-->>P: objek peminjaman, status DIAJUKAN
    deactivate PJ

    P->>J: ajukan(baru)
    activate J

    J->>J: bentrok(baru)
    activate J
    Note right of J: aset sama dan rentang waktu tumpang tindih
    J-->>J: hasil boolean
    deactivate J

    alt hasil = true (bentrok)
        J--xP: lempar PeminjamanBentrokException
        Note over P,J: pengajuan ditolak,<br/>daftar peminjaman tidak berubah
    else hasil = false (tidak bentrok)
        J->>J: cek kuota laptop peminjam
        Note right of J: hanya untuk laptop,<br/>status DISETUJUI atau DIPINJAM<br/>dihitung, batas = kuotaLaptop()
        alt kuota habis
            J--xP: lempar KuotaHabisException
            Note over P,J: pengajuan ditolak,<br/>daftar peminjaman tidak berubah
        else kuota masih cukup
            J->>J: tambah(baru)
            J-->>P: selesai, peminjaman masuk jadwal
        end
    end

    deactivate J
```

## Skenario

| Skenario | Yang terjadi |
|---|---|
| Sukses | `bentrok()` mengembalikan `false`, kuota masih cukup, `tambah()` menyimpan peminjaman. |
| Bentrok | `bentrok()` mengembalikan `true`; `ajukan()` melempar `PeminjamanBentrokException` dan tidak memanggil `tambah()`. |
| Kuota habis | Tidak bentrok, tetapi laptop aktif peminjam sudah mencapai `kuotaLaptop()`; `KuotaHabisException` dilempar. |

Catatan: `ajukan()` masih bertanda TODO minggu 9, sedangkan `bentrok()` sudah diisi pada minggu 7.
Diagram ini mengikuti kontrak di Javadoc dan nama kelas/metode yang sudah ada, bukan menambah kelas baru.
