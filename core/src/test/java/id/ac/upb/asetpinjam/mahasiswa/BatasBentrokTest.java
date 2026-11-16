package id.ac.upb.asetpinjam.mahasiswa;

import static id.ac.upb.asetpinjam.TestSupport.pinjam;
import static org.assertj.core.api.Assertions.assertThat;

import id.ac.upb.asetpinjam.TestSupport;
import id.ac.upb.asetpinjam.jadwal.JadwalPeminjaman;
import id.ac.upb.asetpinjam.model.Peminjaman;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Keputusan saya: peminjaman diperlakukan sebagai rentang setengah terbuka [mulai, selesai).
 * Jadi 09:00-11:00 dan 11:00-13:00 pada aset yang sama TIDAK bentrok (docs/asumsi.md A-01).
 */
@Tag("week7")
class BatasBentrokTest {

    private final JadwalPeminjaman jadwal = new JadwalPeminjaman(1);

    @Test
    void selesaiSamaDenganMulaiTidakBentrok() {
        jadwal.tambah(pinjam("1", TestSupport.peminjam("P1"), TestSupport.ruang("R-1"), 9, 11));
        Peminjaman baru = pinjam("2", TestSupport.peminjam("P2"), TestSupport.ruang("R-1"), 11, 13);

        assertThat(jadwal.bentrok(baru)).isFalse();
    }

    @Test
    void bersambungDariArahSebaliknyaJugaTidakBentrok() {
        jadwal.tambah(pinjam("1", TestSupport.peminjam("P1"), TestSupport.ruang("R-1"), 11, 13));
        Peminjaman baru = pinjam("2", TestSupport.peminjam("P2"), TestSupport.ruang("R-1"), 9, 11);

        assertThat(jadwal.bentrok(baru)).isFalse();
    }

    @Test
    void selisihSatuMenitTetapBentrok() {
        jadwal.tambah(pinjam("1", TestSupport.peminjam("P1"), TestSupport.ruang("R-1"), 9, 11));
        Peminjaman baru = new Peminjaman(
                "2", TestSupport.peminjam("P2"), TestSupport.ruang("R-1"),
                TestSupport.jam(10).plusMinutes(59), TestSupport.jam(12));

        assertThat(jadwal.bentrok(baru)).isTrue();
    }
}
