# mrs_isa

Tema projekta je kreiranje web aplikacije za pregled bioskopa i pozorista i rezervisanje karata za gledanje filmova/predstava. Takođe postoji i fan zona, u kojoj korisnici mogu da kupuju rekvizite, i postavljaju oglase za prodaju rekvizita.

### Installing

Za pokretanje ovog projekta potrebni su eclipse i MySql. Podatke MySql baze treba uneti u fajl application.properties u projektu.
spring.datasource.url je trenutno localhost:3306 i treba ga podesiti prema MySql-u na Vašem računaru. 
spring.datasource.username i spring.datasource.password podesiti prema Vašem username-u i password-u za MySql. 
spring.jpa.hibernate.ddl-auto=create za prvo pokretanje baze, a posle spring.jpa.hibernate.ddl-auto=update.
ako je spring.jpa.hibernate.ddl-auto=create, ubaciti data.sql datoteku na putanju src/main/resources. Sledećim pokretanjem aplikacije potrebno je da se taj isti fajl izmesti, i spring.jpa.hibernate.ddl-auto promeni na update.
port na kom će se aplikacija izvršavati je trenutno 8080. 
Takođe za slanje email-a tj za registraciju guest korisnika i pozivanje prijatelja u rezervacije treba onemogućiti antivirus programu da blokira slanje email-a. Projekat se pokreće tako što se ProjekatIsaMrsApplication klasa pokrene kao Java aplikacija.

## Authors

* **Ognjen Vladisavljević**
* **Damir Hadžić**
* **Dejan Šorgić**
