# Düzce Güven Test Otomasyonu

Bu proje, web sitesi test otomasyonu için Selenium ve ChromeDriver kullanılarak Java ile oluşturulmuştur.

## Proje Yapısı

```
DuzceGuvenTestAutomation/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── duzceguven/
│   │               ├── pages/       # Sayfa nesneleri (Page Objects)
│   │               └── utils/       # Yardımcı sınıflar
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── duzceguven/
│       │           └── tests/       # Test sınıfları
│       └── resources/                  # Test kaynakları
├── pom.xml                             # Maven yapılandırması
├── testng.xml                          # TestNG yapılandırması
└── README.md                           # Bu dosya
```

## Gereksinimler

- Java 11 veya üzeri
- Maven
- Chrome tarayıcısı

## Kurulum

1. Projeyi bilgisayarınıza klonlayın veya indirin.
2. Maven bağımlılıklarını yüklemek için aşağıdaki komutu çalıştırın:

```bash
mvn clean install -DskipTests
```

## Testleri Çalıştırma

Tüm testleri çalıştırmak için:

```bash
mvn test
```

Belirli bir test sınıfını çalıştırmak için:

```bash
mvn test -Dtest=LoginTest
```

## Proje Bileşenleri

### 1. Page Objects

- `BasePage.java`: Tüm sayfa nesnelerinin temel aldığı ana sınıf
- `LoginPage.java`: Giriş sayfası için örnek sayfa nesnesi

### 2. Yardımcı Sınıflar

- `WebDriverUtils.java`: WebDriver işlemleri için yardımcı metotlar

### 3. Test Sınıfları

- `BaseTest.java`: WebDriver kurulumu ve kapatılmasını yöneten temel test sınıfı
- `LoginTest.java`: Giriş işlevselliği için örnek test sınıfı

## Özelleştirme

1. `BaseTest.java` dosyasında WebDriver yapılandırmasını özelleştirebilirsiniz.
2. `LoginTest.java` dosyasında `BASE_URL` değişkenini test edilecek web sitesinin URL'si ile değiştirin.
3. Yeni sayfalar için `pages` paketinde yeni sayfa nesneleri oluşturun.
4. Yeni testler için `tests` paketinde yeni test sınıfları oluşturun.

## Not

Bu proje, WebDriverManager kullanarak ChromeDriver'ı otomatik olarak yönetir, bu nedenle ChromeDriver'ı manuel olarak indirmenize gerek yoktur.
