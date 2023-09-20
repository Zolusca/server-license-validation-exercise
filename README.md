# Dokumentasi Cara Kerja License Software Sederhana

<img title="" src="file:///home/haslam/Pictures/AntMask.gif" alt="image" data-align="center">

## Deskripsi Singkat

Ini merupakan sebuah aplikasi yang mengimplementasikan bagaimana aplikasi dengan aktifasi license key bekerja
seperti produk Micros***, adob*, dsb. Dimana aplikasi tersebut akan berjalan jika pengguna atau costumer berlangganan
dengan membeli license key, Disini saya ingin membuat implementasi sederhana dari cara kerja aplikasi tersebut.
Project ini terdiri dari 2 aplikasi yakni Desktop(client side) dan aplikasi server side, dimana untuk desktop menggunakan JAVAFX
dan untuk server side dengan SpringBoot(java).

## JAVAFX Client Side

![Diagram](/home/haslam/Documents/developmentTraining/training-Java/project lisensi/MarkdownImage/Untitled Diagram.png)

## SpringBoot Server Side

![alt text](/home/haslam/Documents/developmentTraining/training-Java/project%20lisensi/MarkdownImage/diagrams.png "Title")

### Api Path

1. 'license/create' 
   
   -> method get 
   
   -> untuk mendapatkan licensekey
   
   -> format response
   
   ```json
   {"message":"sukses","statusResponse":"OK","data":null}
   ```

2. 'license/find'
   
   -> method post
   
   -> untuk mecari license key
   
   -> format request
   
   ```json
   {'license':'licensekey'}
   ```

        -> format response 

```json
{"message":"sukses","statusResponse":"OK","data":{
'licenseKey':'licenseKey data',
'status':'status licenseKey'    
}
}
```

3. 'license/activated'
   
   -> method post
   
   -> aktivasi licenseKey
   
   ->format request
   
   ```json
   {'license':'licensekey'}
   ```

## Instalasi

- unduh 2 repository [GitHub - haslam17/javafx-simple-license-software: this a ui for licensekey software work](https://github.com/haslam17/javafx-simple-license-software) dan https://github.com/haslam17/rest-json-license-software-work 

- buat database dengan mysql dibawah ini merupakan sql query, dengan nama database licensedata dan table license
  
  ```sql
  create database licensedata
  CREATE TABLE `license` (
    `id` varchar(40) NOT NULL,
    `licensekey` varchar(15) DEFAULT NULL,
    `status` enum('AKTIF','MATI') DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `licensekey` (`licensekey`),
    KEY `licensekey_index` (`licensekey`)
  ) ENGINE=InnoDB 
  ```

- konfigurasi database pada ConfigurationPack.java ubah username dan password sesuai yang anda miliki

- pastikan anda memiliki maven versi 3+ dan java min versi 11

- mulai service rest-json-license, dengan installasi kebutuhan dependency
  
  ```java
  mvn install
  ```

- mulai service rest-json-license, dengan perintah
  
  ```java
  ./mvnw spring-boot:run
  ```

- coba buat request untuk membuat license 

- mulai eksekusi program javafx dan coba inputkan licensekey yang telah didapat melalui query sebelumnya, Sebetulnya saya bisa membuat native image dengan bantuan graalvm agar mudah untuk dicoba. Tetapi ada bug error pada java 17 berkaitan dengan javafx. 
