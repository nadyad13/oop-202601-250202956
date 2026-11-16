package id.ac.upb.asetpinjam.jadwal;

import id.ac.upb.asetpinjam.model.Peminjaman;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Jadwal peminjaman di memori.
 *
 * <p>Minggu 7: bentrok, semuaUrutMulai, perAset, hapus. Minggu 9: ajukan (melempar exception).
 * tambah() DISEDIAKAN dan sengaja TIDAK memeriksa apa pun.
 */
public class JadwalPeminjaman {

    private final List<Peminjaman> daftar = new ArrayList<>();
    private final int kuotaLaptop;

    public JadwalPeminjaman(int kuotaLaptop) {
        this.kuotaLaptop = kuotaLaptop;
    }

    protected int kuotaLaptop() {
        return kuotaLaptop;
    }

    protected List<Peminjaman> daftarMutable() {
        return daftar;
    }

    /** Menambah apa adanya tanpa pemeriksaan. */
    public void tambah(Peminjaman p) {
        daftar.add(p);
    }

    /**
     * True jika {@code baru} bentrok dengan peminjaman yang sudah ada untuk ASET YANG SAMA.
     * Batas waktu diperlakukan sebagai rentang setengah terbuka: selesai sama dengan mulai
     * dianggap TIDAK bentrok (docs/asumsi.md A-01).
     */
    public boolean bentrok(Peminjaman baru) {
        if (baru == null) {
            throw new IllegalArgumentException("Peminjaman yang diperiksa tidak boleh null");
        }
        String kodeAset = baru.getAset().getKode();
        return daftar.stream()
                .anyMatch(lama -> lama.getAset().getKode().equals(kodeAset) && tumpangTindih(lama, baru));
    }

    /** Dua rentang [mulai, selesai) tumpang tindih jika saling memotong, bukan hanya bersentuhan. */
    private static boolean tumpangTindih(Peminjaman a, Peminjaman b) {
        return a.getMulai().isBefore(b.getSelesai()) && b.getMulai().isBefore(a.getSelesai());
    }

    /** Semua peminjaman, diurutkan menaik menurut waktu mulai. */
    public List<Peminjaman> semuaUrutMulai() {
        return daftar.stream()
                .sorted(Comparator.comparing(Peminjaman::getMulai).thenComparing(Peminjaman::getId))
                .toList();
    }

    /** Peminjaman dikelompokkan menurut kode aset, urutan kunci mengikuti urutan penambahan. */
    public Map<String, List<Peminjaman>> perAset() {
        return daftar.stream().collect(Collectors.groupingBy(
                p -> p.getAset().getKode(),
                LinkedHashMap::new,
                Collectors.toList()));
    }

    /** Hapus peminjaman berdasarkan id. True jika ada yang terhapus. */
    public boolean hapus(String id) {
        return daftar.removeIf(p -> p.getId().equals(id));
    }

    /**
     * TODO minggu 9: periksa lalu tambahkan.
     * Bentrok: PeminjamanBentrokException. Peminjam sudah memegang {@link #kuotaLaptop()} laptop yang
     * aktif (status DISETUJUI atau DIPINJAM) dan mengajukan laptop lagi: KuotaHabisException.
     */
    public void ajukan(Peminjaman baru) {
        throw new UnsupportedOperationException("TODO minggu 9: ajukan");
    }
}
