Půjčovna kempingového vybavení

Aplikace pro evidenci půjčovny kempingového vybavení. Umožňující spravovat vybavení rozdělené do kategorií, evidovat uživatele a jejich výpůjčky. 
Kostra projektu vychází z přednášky a byla rozšířena o vlastní téma a další funkce. 

Funkce:
- Evidence vybavení, kategorie, výpůjček, a uživatelů( vytvoření, úprava, zobrazení, smazání)
- Filtrování vybavení podle kategorie
- Přihlašování a rozlišení rolí - admin spravuje uživ., běžný uživatel má omezený přístup
- Hesla se ukládají zahashovaná přes BCrypt
- Přihlašovací jméno je v databázi uděláno jako jedinečné
-Jednotkové testy servisní vrstvy ( JUnit, Mockito)

Aplikace obsahuje 4 třídy 



Spuštění: 
- Spustit třídu "Pro2kf2026Application"
- Otevřít "http://localhost:8080"
- Přihlásit se výchozím účtem: admin / heslo

Databáze H2 se vytvoří automaticky. Při prvním spuštění se založí uživatel "admin" ostatní data se zadávají přes webové rozhraní. 

