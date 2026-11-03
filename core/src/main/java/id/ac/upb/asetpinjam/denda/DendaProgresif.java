package id.ac.upb.asetpinjam.denda;

import id.ac.upb.asetpinjam.kontrak.AturanDenda;
import id.ac.upb.asetpinjam.model.Aset;

/**
 * Minggu 5: denda yang makin berat bila makin lama terlambat.
 *
 * <p>Rumus: denda flat, ditambah tarif harian untuk setiap hari setelah hari ke-7.
 */
public class DendaProgresif implements AturanDenda {

    private static final int BATAS_HARI_NORMAL = 7;

    @Override
    public long hitung(Aset aset, int hariTerlambat) {
        if (hariTerlambat < 0) {
            throw new IllegalArgumentException("Hari terlambat tidak boleh negatif");
        }
        long dendaFlat = aset.hitungDenda(hariTerlambat);
        long dendaPerHari = aset.hitungDenda(1);
        long hariBerlebih = Math.max(0, hariTerlambat - BATAS_HARI_NORMAL);
        return dendaFlat + hariBerlebih * dendaPerHari;
    }
}
