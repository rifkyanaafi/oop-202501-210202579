# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)

Topik: Desain Arsitektur Sistem dengan UML dan Prinsip SOLID

## Identitas

- Nama : Muhammad Rifqi An Naafi
- NIM : 210202579
- Kelas : 3IKKA

---

## Deskripsi Singkat Sistem

Agri-POS adalah sistem point-of-sale untuk produk pertanian (benih, pupuk, alat, obat). Sistem mendukung manajemen produk, transaksi penjualan oleh kasir, beberapa metode pembayaran (tunai, e-wallet), dan pencetakan struk/laporan. Desain difokuskan pada maintainability dan extensibility menggunakan prinsip SOLID.

---

## Diagram UML yang Dibuat

1. Use Case Diagram (`docs/uml_usecase.png`)

   - Menjelaskan aktor: Kasir, Admin, Payment Gateway, Customer.
   - Use case: Kelola Produk, Buat Transaksi, Checkout/Pembayaran, Cetak Struk, Lihat Laporan.

2. Activity Diagram — Checkout (`docs/uml_activity.png`)

   - Swimlane: Kasir / Sistem / Payment Gateway.
   - Menyertakan skenario normal (sukses) dan alternatif (tunai tidak cukup, OTP invalid, saldo kurang).

3. Sequence Diagram — Pembayaran (`docs/uml_sequence.png`)

   - Dua variasi: Cash vs E-Wallet.
   - Menambahkan guard/alt untuk gagal (saldo tidak cukup / OTP invalid).

4. Class Diagram (`docs/uml_class.png`)
   - Package: `com.upb.agripos.product`, `com.upb.agripos.payment`, `com.upb.agripos.transaction`.
   - Menampilkan atribut (dengan tipe & visibility), method (signature & visibility), relasi (association/composition/inheritance), dan multiplicity.

---

## Penerapan Prinsip SOLID

- **SRP:** `ProductService` (manajemen produk), `PaymentService` (pengelolaan proses pembayaran), `ProductRepository` (penyimpanan) — setiap class punya tanggung jawab tunggal.
- **OCP:** `PaymentMethod` interface memungkinkan penambahan metode pembayaran baru (mis. TransferBankPayment) tanpa mengubah `PaymentService`.
- **ISP:** Memisahkan interface jika diperlukan (mis. `Refundable`) agar implementor tidak dipaksa metode yang tidak relevan.
- **DIP:** `ProductService` bergantung pada abstraksi `ProductRepository` — memudahkan unit testing & swapping implementasi.

---

## Traceability (FR → Diagram → Realisasi)

(Lampirkan tabel traceability yang ada di laporan; pastikan setiap FR dipetakan)

---

## Kesimpulan & Refleksi

Desain Agri-POS menggunakan prinsip SOLID dan pemodelan UML untuk memastikan sistem mudah dikembangkan dan dipelihara. Dengan memisahkan tanggung jawab dan mengandalkan abstraksi, penambahan fitur baru (mis. metode pembayaran/discount engine) dapat dilakukan minim perubahan pada kode inti.

---

## Quiz

(1. [Jelaskan perbedaan aggregation dan composition serta berikan contoh penerapannya pada desain Anda]  
 **Jawaban:** Aggregation dan Composition sama-sama menunjukkan hubungan has-a, tetapi berbeda pada ketergantungan siklus hidup objek.
Aggregation
- Objek bagian dapat berdiri sendiri tanpa objek utama.
- Bersifat hubungan lemah (weak relationship).
- Digambarkan dengan diamond putih (◇) pada UML.
Contoh pada desain Agri-POS:
ProductService o-- Product
Produk tetap ada walaupun ProductService tidak digunakan (misalnya saat service dimatikan atau diganti).

Composition
- Objek bagian tidak dapat eksis tanpa objek utama.
- Bersifat hubungan kuat (strong relationship).
- Digambarkan dengan diamond hitam (◆) pada UML.
Contoh pada desain Agri-POS:
Transaction \*-- Item
Item transaksi hanya ada sebagai bagian dari satu transaksi. Jika transaksi dihapus, maka seluruh item di dalamnya ikut hilang.

2. [Bagaimana prinsip Open/Closed dapat memastikan sistem mudah dikembangkan?]  
   **Jawaban:** Prinsip Open/Closed Principle (OCP) menyatakan bahwa :
Software entities harus terbuka untuk perluasan (extension) tetapi tertutup untuk perubahan (modification).
Artinya :
Kelas tidak perlu diubah meskipun fitur baru ditambahkan.
Perubahan dilakukan dengan menambah class baru, bukan mengubah class lama.

Contoh pada desain Agri-POS:
Terdapat interface PaymentMethod.
Implementasi awal: CashPayment, EWalletPayment.
Jika ingin menambahkan TransferBankPayment, cukup:
Membuat class baru TransferBankPayment
Mengimplementasikan PaymentMethod
Tanpa mengubah kode PaymentService

Dengan cara ini:
Risiko bug berkurang
Sistem lebih fleksibel
Cocok untuk pengembangan jangka panjang

3. [Mengapa Dependency Inversion Principle (DIP) meningkatkan testability? Berikan contoh penerapannya]  
   **Jawaban:** Prinsip Dependency Inversion Principle (DIP) menyatakan bahwa :
Modul tingkat tinggi tidak bergantung pada implementasi konkret
Semua dependensi mengarah ke abstraksi (interface)

Manfaat untuk testability:
- Ketergantungan dapat di-mock / diganti saat pengujian
- Tidak perlu bergantung pada implementasi asli (database, payment gateway, dsb.)

Contoh penerapan pada desain Agri-POS:
- ProductService tidak langsung bergantung pada InMemoryProductRepository
- ProductService bergantung pada interface ProductRepository

Hal ini memungkinkan:
- Saat testing → gunakan FakeProductRepository atau MockProductRepository
- Saat produksi → gunakan implementasi nyata
- ProductRepository repo = new FakeProductRepository();
- ProductService service = new ProductService(repo);


Dengan demikian :
- Unit test lebih mudah dibuat
- Test lebih cepat dan terisolasi
- Kode lebih bersih dan modular )
