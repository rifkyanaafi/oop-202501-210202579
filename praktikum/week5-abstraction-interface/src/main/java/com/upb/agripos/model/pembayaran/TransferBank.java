package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;

public class TransferBank extends Pembayaran implements Validatable, Receiptable {

    private String noRekening;
    private boolean valid;

    public TransferBank(String invoiceNo, double total, String noRekening, boolean valid) {
        super(invoiceNo, total);
        this.noRekening = noRekening;
        this.valid = valid;
    }

    @Override
    public double biaya() {
        return 3500;
    }

    @Override
    public boolean validasi() {
        return valid;
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi();
    }

    @Override
    public String cetakStruk() {
        return String.format(
            "INVOICE: %s | TOTAL+BIAYA: %.2f | TRANSFER: %s | STATUS: %s",
            invoiceNo,
            totalBayar(),
            noRekening,
            prosesPembayaran() ? "BERHASIL" : "GAGAL"
        );
    }
}
