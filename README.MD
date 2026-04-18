# 🚀 Yeni Ödeme Yöntemi Entegrasyonu (SOLID Prensipleri)

Bu proje, bir ödeme sistemine mevcut kod yapısını bozmadan yeni bir ödeme yönteminin nasıl eklenebileceğini göstermektedir. Projede nesne yönelimli programlama (OOP) ve **SOLID** prensipleri (Özellikle SRP, OCP ve DIP) temel alınmıştır. Tasarım deseni olarak **Strategy Pattern** kullanılmıştır.

## 🛠️ Tasarım Kararları ve SOLID Yaklaşımı

Sistem tasarlanırken aşağıdaki prensiplere sıkı sıkıya bağlı kalınmıştır:

### 1. Open/Closed Principle (OCP - Açık/Kapalı Prensibi)
Sistem genişlemeye açık, değişime kapalı olacak şekilde kurgulanmıştır.
* `IPaymentMethod` adında bir interface (arayüz) oluşturulmuştur.
* Sisteme yeni bir ödeme yöntemi eklendiğinde (örn: Apple Pay, Kripto vb.), mevcut olan `PaymentProcessor` veya `CreditCardPayment` sınıflarında **hiçbir kod değişikliği yapılmasına gerek yoktur**.
* Sadece `IPaymentMethod` interface'ini implemente eden yeni bir sınıf oluşturmak entegrasyon için yeterlidir.

### 2. Single Responsibility Principle (SRP - Tek Sorumluluk Prensibi)
Her sınıfın değişmek için tek bir nedeni vardır:
* `CreditCardPayment` sınıfı sadece kredi kartı operasyonlarından sorumludur.
* `PayPalPayment` sınıfı sadece PayPal API işlemlerinden sorumludur.
* `PaymentProcessor` sınıfı ödemenin **nasıl** yapılacağı ile ilgilenmez, sadece doğru ödeme kanalını tetiklemekle (işlemi başlatmakla) yükümlüdür.

### 3. Dependency Inversion Principle (DIP - Bağımlılıkların Ters Çevrilmesi Prensibi)
`PaymentProcessor` (Yüksek seviyeli modül), doğrudan `CreditCardPayment` veya `PayPalPayment` (Düşük seviyeli modüller) sınıflarına bağımlı değildir. Her iki taraf da `IPaymentMethod` soyutlamasına (abstraction) bağımlıdır. İlgili bağımlılık, sınıfa Constructor (Yapıcı Metot) üzerinden **Dependency Injection** ile verilmektedir.
### 🎯 Strategy Pattern (Strateji Tasarım Deseni) Kullanımı

Sistemdeki if/else veya switch/case bloklarının (Ödeme tipi Kredi Kartı ise şunu yap, PayPal ise bunu yap gibi) önüne geçmek ve algoritmaları çalışma zamanında (runtime) dinamik olarak değiştirebilmek için **Strategy Pattern** uygulanmıştır.

* **Strategy (Strateji):** `IPaymentMethod` arayüzü, tüm ödeme algoritmalarının uyması gereken ortak sözleşmeyi tanımlar.
* **Concrete Strategies (Somut Stratejiler):** `CreditCardPayment` ve `PayPalPayment` sınıflarıdır. Her biri ödeme işleminin kendi iş mantığını (business logic) barındırır.
* **Context (Bağlam):** `PaymentProcessor` sınıfıdır. İstemci (Client) tarafından kendisine verilen stratejiyi çalıştırır. Ödemenin "nasıl" yapılacağı ile değil, sadece "yapılması" ile ilgilenir.

Bu desen sayesinde, kodun karmaşıklığı azaltılmış ve yeni ödeme yöntemlerinin (yeni stratejilerin) sisteme tak-çalıştır (plug-and-play) mantığıyla eklenmesi sağlanmıştır.

## ⚙️ Nasıl Çalıştırılır?
`Test.java` dosyası üzerinden uygulamanın temel akışını ve polimorfik yapısını test edebilirsiniz.

Program çalıştırıldığında terminal üzerinden etkileşimli olarak:

```
=== Ödeme Sistemi ===

Lütfen ödeme yöntemi seçiniz:
1. Kredi Kartı
2. PayPal

Seçiminiz (1 veya 2): 1

Ödenecek tutar (TL): 500

--- İşlem Gerçekleştiriliyor ---
500.0TL Kredi kartı yoluyla başarıyla çekildi.
--- İşlem Tamamlandı ---
```

## 🔄 Clean Code Optimizasyonları

### PaymentValidator Sınıfı
Tutar validasyonu SRP'ye uygun olarak ayrı bir sınıfta yönetilmektedir:
* Validation logic'in yeniden kullanılabilirliği sağlanır
* Unit test'lenmesi kolaydır
* Business logic'ten ayrıştırılmıştır

### If Statement Ortadan Kaldırılması
`Test.java` `main` methodu **0 if statement** içerir:
* **Seçim:** `Map.getOrDefault()` ile handle edilir
* **Validasyon:** Exception throw ederek failure case'ler yönetilir
* **Try-with-resources:** Scanner otomatik kapatılır

### Proje Yapısı
```
src/com/n11/oop/solid/
├── Test.java                 # UI/Flow yönetimi (0 if/else)
├── PaymentValidator.java     # Validasyon (Utility/Validator pattern)
├── PaymentProcessor.java     # Ödeme işlemi yönetimi (Context)
├── IPaymentMethod.java       # Sözleşme (Strategy interface)
├── CreditCardPayment.java    # Kredi kartı implementasyonu
└── PayPalPayment.java       # PayPal implementasyonu
```

## 🚀 Genişletme (Plug-and-Play)

Yeni ödeme yöntemi eklemek için:

```java
// 1. Yeni class oluştur
public class GooglePayPayment implements IPaymentMethod {
    @Override
    public boolean pay(double amount) {
        System.out.println(amount + "TL Google Pay yoluyla başarıyla çekildi.");
        return true;
    }
}

// 2. Test.java'ya 1 satır ekle
paymentMethods.put(3, new GooglePayPayment());
```

Bu kadar! Mevcut kodlar hiç değişmez (OCP ✅)
