package id.ac.upb.asetpinjam.katalog;

import id.ac.upb.asetpinjam.model.Aset;
import id.ac.upb.asetpinjam.model.JenisAset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Katalog aset di memori. tambah() dan semua() DISEDIAKAN; Anda mengerjakan dua overload cari() (minggu 4). */
public class KatalogAset {

    private final List<Aset> daftar = new ArrayList<>();

    public void tambah(Aset aset) {
        daftar.add(aset);
    }

    public List<Aset> semua() {
        return List.copyOf(daftar);
    }

    /** Aset yang namanya memuat kata, tanpa membedakan huruf besar/kecil. */
    public List<Aset> cari(String kata) {
        if (kata == null) {
            return List.of();
        }
        String kunci = kata.toLowerCase(Locale.ROOT);
        return daftar.stream()
                .filter(aset -> aset.getNama().toLowerCase(Locale.ROOT).contains(kunci))
                .toList();
    }

    /** Seperti cari(kata), tetapi hanya untuk jenis yang diminta (overloading). */
    public List<Aset> cari(String kata, JenisAset jenis) {
        if (jenis == null) {
            return List.of();
        }
        return cari(kata).stream()
                .filter(aset -> aset.getJenis() == jenis)
                .toList();
    }
}
